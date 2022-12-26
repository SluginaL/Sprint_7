import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.Praktikum.ConfigCourier;
import ru.Praktikum.Courier;
import ru.Praktikum.JSON;
import ru.Praktikum.LoginCourier;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;


public class LoginTest {
    private Courier courier;
    private Faker faker;
    private ConfigCourier configCourier;
    private LoginCourier logincourier;
    private String Message = "Недостаточно данных для входа";
    private String notFoundMessage = "Учетная запись не найдена";

    @Before
    public void setUp() {
        courier = new Courier();
        configCourier = new ConfigCourier();
        faker = new Faker();
        logincourier = new LoginCourier();
        Response response = courier.createPostRequestForCreateNewCourier(configCourier);
    }

    @Test
    @Description("курьер может авторизоваться")
    public void getAuthorization() {
        logincourier.setLogin(configCourier.getLogin());
        logincourier.setPassword(configCourier.getPassword());
        Response response = courier.returnIdCourier(logincourier);
        response.then().assertThat().
                statusCode(200). // проверь статус ответа
                and().body("id", notNullValue());
    }

    @Test
    @Description("для авторизации нужно передать все обязательные поля;")
    public void requiredFieldInAuthorization() {
        Response response = courier.postRequestLoginWithoutKeys();
        response.then().assertThat().
                statusCode(400). // проверь статус ответа
                and().body("message", equalTo(Message));
    }

    @Test
    @Description("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;")
    public void tryAuthorizationNotExistUser() {
        String notExistName = faker.name().nameWithMiddle();
        logincourier.setLogin(notExistName);
        Response response = courier.returnIdCourier(logincourier);
        response.then().assertThat().
                statusCode(404). // проверь статус ответа
                and().body("message", equalTo(notFoundMessage));


    }

    @Test
    @Description("система вернёт ошибку, если неправильно указать логин или пароль")
    public void wrongLoginOrPassword() {
        String wrongName = faker.name().lastName();
        logincourier.setLogin(wrongName);
        Response response = courier.returnIdCourier(logincourier);
        response.then().assertThat().
                statusCode(404). // проверь статус ответа
                and().body("message", equalTo(notFoundMessage));

    }

    @Test
    @Description("если какого-то поля нет, запрос возвращает ошибку;")
    public void emptyLogin() {
        logincourier.setLogin(null);
        Response response = courier.returnIdCourier(logincourier);
        response.then().assertThat().
                statusCode(400). // проверь статус ответа
                and().body("message", equalTo(Message));

    }

    @After
    @Description("Удаление тестовых данных")
    public void cleanTestData() {
        logincourier = new LoginCourier();
        logincourier.setLogin(configCourier.getLogin());
        logincourier.setPassword(configCourier.getPassword());
        Response response = courier.returnIdCourier(logincourier);
        JSON jsonanswer = response.body().as(JSON.class);
        Response delete = courier.deleteCourier(jsonanswer.getId());


    }
}
