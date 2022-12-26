package ru.Praktikum;


import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Courier extends Config {
    @Step("Зарос на создание курьера")
    public Response createPostRequestForCreateNewCourier(ConfigCourier json) {
        return given()
                .header("Content-type", "application/json")
                .spec(getBaseSpec())
                .and()
                .body(json)
                .when()
                .post(EndPoints.POST);

    }

    @Step("Запрос на создание курьера с пустым телом")
    public Response createPostRequestWithoutKeys() {
        return given()
                .header("Content-type", "application/json")
                .spec(getBaseSpec())
                .when()
                .post(EndPoints.POST);
    }

    @Step("Запрос на получение id курьера")
    public Response returnIdCourier(LoginCourier loginCourier) {
        return given()
                .header("Content-type", "application/json")
                .spec(getBaseSpec())
                .body(loginCourier)
                .when()
                .post(EndPoints.POST_LOGIN);
// попросили представить результат как объект типа SimpleExample


    }

    @Step("Удаление курьера")
    public Response deleteCourier(String id) {
        return given()
                .spec(getBaseSpec())
                .and()
                .when()
                .delete(EndPoints.DELETE + id);
    }

    @Step("Зарос на авторизацию курьера с пустым телом")
    public Response postRequestLoginWithoutKeys() {
        return given()
                .header("Content-type", "application/json")
                .spec(getBaseSpec())
                .when()
                .post(EndPoints.POST_LOGIN);
    }

}
