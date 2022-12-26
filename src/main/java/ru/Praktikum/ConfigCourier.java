package ru.Praktikum;

import com.github.javafaker.Faker;

public class ConfigCourier {
    Faker faker = new Faker();

    private String login = faker.name().fullName();
    private String password = faker.name().name();
    private String firstName = faker.name().firstName();

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


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
