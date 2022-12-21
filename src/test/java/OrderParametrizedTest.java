import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.Praktikum.OrderClient;
import ru.Praktikum.Orders;

import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderParametrizedTest {

    private final String[] color;
    private OrderClient orderclient;
    private Orders orders;

    public OrderParametrizedTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderColor() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        };
    }

    @Before
    public void setUp() {
        orderclient = new OrderClient();
        orders = new Orders();
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        orderclient.setColor(color);
    }

    @Test()
    public void orderCreateValidCredentials() {
        Response response = orders.createNewOrder(orderclient);
        response.then().assertThat().
                statusCode(201). // проверь статус ответа
                and().body("track", notNullValue());
    }
}


