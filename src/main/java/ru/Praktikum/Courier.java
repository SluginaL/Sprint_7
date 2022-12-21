package ru.Praktikum;


import io.qameta.allure.Description;
import io.restassured.response.Response;
import ru.Praktikum.EndPoints;

import static io.restassured.RestAssured.*;


public class Courier {
    @Description("Зарос на создание курьера")
    public Response createPostRequestForCreateNewCourier(ConfigCourier json) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(EndPoints.POST);

    }

    @Description("Зарос на создание курьера с пустым телом")
    public Response createPostRequestWithoutKeys() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .post(EndPoints.POST);
    }

    @Description("Запрос на получение id курьера")
    public Response returnIdCourier(LoginCourier loginCourier) {
        return given()
                .header("Content-type", "application/json")
                .body(loginCourier)
                .when()
                .post(EndPoints.POST_LOGIN);
// попросили представить результат как объект типа SimpleExample


    }

    @Description("Удаление курьера")
    public Response deleteCourier(String id) {
        return given()
                .and()
                .when()
                .delete(EndPoints.DELETE + id);
    }

    @Description("Зарос на авторизацию курьера с пустым телом")
    public Response PostRequestLoginWithoutKeys() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .post(EndPoints.POST_LOGIN);
    }


}
