package com.local.client.user;

import com.local.client.BaseClient;
import com.local.model.LoginDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    public static final String LOGIN_PATH = "/login";

    @Step("Login with user {payload.email} / {payload.password}")
    public Response loginResponse(LoginDto payload) {
        return given()
                .body(payload)
                .post(LOGIN_PATH)
                .then()
                .spec(payload.getEmail() != null && payload.getPassword() != null ? responseSpec(STATUS_OK) : responseSpec(400))
                .extract().response();
    }
}
