package com.local.client.user;

import com.local.client.BaseClient;
import com.local.model.LoginDto;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    public static final String LOGIN_PATH = "/login";

    public Response loginResponse(LoginDto payload) {
        return given()
                .body(payload)
                .post(LOGIN_PATH)
                .then()
                .spec(payload.getEmail() != null && payload.getPassword() != null ? responseSpec(STATUS_OK) : responseSpec(400))
                .extract().response();
    }
}
