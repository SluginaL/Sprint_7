import io.qameta.allure.Description;
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


    }

    @Test
    @Description("Получение в тело ответа списка заказов")
    public void getListOrders() {
        Response response = orders.getCreatedOrders();
        response.then().assertThat().
                statusCode(200). // проверь статус ответа
                and().body("orders", notNullValue());
    }


}
