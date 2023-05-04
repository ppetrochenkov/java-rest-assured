package com.local.client.user;

import com.local.client.BaseClient;
import com.local.model.RegisterDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegisterClient extends BaseClient {

    public static final String REGISTER_PATH = "/register";

    @Step("Signing up with user {payload.email} / {payload.password}")
    public Response registerUserResponse(RegisterDto payload) {
        return given()
                .body(payload)
                .post(REGISTER_PATH)
                .then()
                .spec(payload.getEmail() != null && payload.getPassword() != null ? responseSpec(STATUS_OK) : responseSpec(400))
                .extract().response();
    }
}
