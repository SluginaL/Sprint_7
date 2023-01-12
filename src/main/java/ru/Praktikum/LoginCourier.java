package ru.Praktikum;

import com.github.javafaker.Faker;

public class LoginCourier {

    Faker faker = new Faker();

    private String login = faker.name().username();
    private String password = faker.name().nameWithMiddle();


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
