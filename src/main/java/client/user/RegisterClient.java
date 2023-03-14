package client.user;

import client.BaseClient;
import io.restassured.response.Response;
import model.RegisterDto;

import static io.restassured.RestAssured.given;

public class RegisterClient extends BaseClient {

    private static final String REGISTER_PATH = "/register";

    public Response registerUserResponse(RegisterDto payload) {
        return given()
                .body(payload)
                .post(REGISTER_PATH)
                .then()
                .spec(payload.getEmail() != null && payload.getPassword() != null ? responseSpec(STATUS_OK) : responseSpec(400))
                .extract().response();
    }
}
