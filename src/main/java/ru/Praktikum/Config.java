package ru.Praktikum;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Config {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();
    }
}