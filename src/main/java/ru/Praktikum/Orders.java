package ru.Praktikum;

import io.qameta.allure.Description;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Orders {
    @Description("Создание заказа")
    public Response createNewOrder(OrderClient orderClient) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(orderClient)
                .when()
                .post(EndPoints.POST_ORDER);

    }

    @Description("Получение заказа")
    public Response getCreateNewOrder() {
        return given()
                .get(EndPoints.GET_ORDER);

    }
}
