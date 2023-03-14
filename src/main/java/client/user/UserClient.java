package client.user;

import client.BaseClient;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseClient {

    private static final String FETCH_ALL_USERS_PATH = "/users";
    private static final String FETCH_SINGLE_USER_PATH = "/users/{id}";

    public Response fetchUsersListResponse(int limitPerPage, int delay) {
        Map<String, Integer> queryParams = new HashMap<>();
        queryParams.put("per_page", limitPerPage);

        if(delay > 0) {
            queryParams.put("delay", delay);
        }

        return given()
                .queryParams(queryParams)
                .get(FETCH_ALL_USERS_PATH)
                .then()
                .spec(responseSpec(STATUS_OK))
                .extract().response();
    }

    public Response fetchSingleUserResponse(int userId, boolean exist) {
        return given()
                .pathParam("id", userId)
                .get(FETCH_SINGLE_USER_PATH)
                .then()
                .spec(exist ? responseSpec(STATUS_OK) : responseSpec(404))
                .extract().response();
    }
}
