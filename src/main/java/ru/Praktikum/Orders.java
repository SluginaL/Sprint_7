package ru.Praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Orders extends Config {
    @Step("Создание заказа")
    public Response createNewOrder(OrderClient orderClient) {
        return given()
                .header("Content-type", "application/json")
                .spec(getBaseSpec())
                .and()
                .body(orderClient)
                .when()
                .post(EndPoints.POST_ORDER);

    }

    @Step("Получение списка заказов")
    public Response getCreatedOrders() {
        return given()
                .spec(getBaseSpec())
                .get(EndPoints.GET_ORDER);
    }
}
