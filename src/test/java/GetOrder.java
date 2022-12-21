import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.Praktikum.OrderClient;
import ru.Praktikum.Orders;

import static org.hamcrest.Matchers.notNullValue;


public class GetOrder {

    private OrderClient orderclient;

    private Orders orders;

    @Before
    public void setUp() {
        orders = new Orders();
        orderclient = new OrderClient();
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";

    }

    @Test
    @Description("Получение списка заказов")
    public void getOrder() {
        Response response = orders.getCreateNewOrder();
        response.then().assertThat().
                statusCode(200). // проверь статус ответа
                and().body("orders", notNullValue());
    }
}
