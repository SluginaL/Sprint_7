import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.Praktikum.*;

import static org.hamcrest.core.IsEqual.equalTo;


public class CreateCourierTest {
    private Courier courier;
    private ConfigCourier configCourier;
    private String Message = "Недостаточно данных для создания учетной записи";
    private String isUsedLogin = "Этот логин уже используется. Попробуйте другой.";


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courier = new Courier();
        configCourier = new ConfigCourier();
        Response response = courier.createPostRequestForCreateNewCourier(configCourier);
    }

    @Test
    @Description("курьера можно создать/запрос возвращает правильный код ответа;\n" +
            "успешный запрос возвращает ok: true")
    public void checkCreateNewCourier() {
        configCourier.setLogin(GenerateLoginCourier.generateString());
        Response response = courier.createPostRequestForCreateNewCourier(configCourier);
        response.then().assertThat().
                statusCode(201). // проверь статус ответа
                and().body("ok", equalTo(true));
    }

    @Test
    @Description("чтобы создать курьера, нужно передать в ручку все обязательные поля")
    public void checkCreateNewCourierWithoutKeys() {
        Response response = courier.createPostRequestWithoutKeys();
        response.then().assertThat().
                statusCode(400). // проверь статус ответа
                and().body("message", equalTo(Message));
    }

    @Test
    @Description("если одного из полей нет, запрос возвращает ошибку")
    public void checkCreateNewCourierWithoutOneField() {
        configCourier.setLogin(null);
        Response response = courier.createPostRequestForCreateNewCourier(configCourier);

        response.then().assertThat().
                statusCode(400). // проверь статус ответа
                and().body("message", equalTo(Message));
    }

    @Test
    @Description("если создать пользователя с логином, который уже есть, возвращается ошибка")
    public void checkCreateNewCourierLoginExist() {
        Response response = courier.createPostRequestForCreateNewCourier(configCourier);
        response.then().assertThat().
                statusCode(409). // проверь статус ответа
                and().body("message", equalTo(isUsedLogin));
    }

    @Test
    @Description("нельзя создать двух одинаковых курьеров")
    public void checkCreateNewCourierTwice() {
        String repeatLogin = configCourier.getLogin();
        Response response = courier.createPostRequestForCreateNewCourier(configCourier);
        response.then().assertThat().
                statusCode(409). // проверь статус ответа
                and().body("message", equalTo(isUsedLogin));

    }

    @After
    @Description("Удаление тестовых данных")
    public void Clean() {
        LoginCourier loginCourier = new LoginCourier();
        Response request = courier.returnIdCourier(loginCourier);
        JSON jsonanswer = request.body().as(JSON.class);
        Response delete = courier.deleteCourier(jsonanswer.getId());

    }


}