package client.user;

import client.BaseClient;
import io.restassured.response.Response;
import model.LoginDto;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    private static final String LOGIN_PATH = "/login";

    public Response loginResponse(LoginDto payload) {
        return given()
                .body(payload)
                .post(LOGIN_PATH)
                .then()
                .spec(payload.getEmail() != null && payload.getPassword() != null ? responseSpec(STATUS_OK) : responseSpec(400))
                .extract().response();
    }
}
