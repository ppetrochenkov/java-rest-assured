package com.local.client.resource;

import com.local.client.BaseClient;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ResourceClient extends BaseClient {

    public static final String FETCH_ALL_RESOURCES_PATH = "/unknown";
    public static final String FETCH_SINGLE_RESOURCE_PATH = "/unknown/{id}";

    public Response fetchAllResourcesResponse(int limitPerPage) {
        return given()
                .queryParam("per_page", limitPerPage)
                .get(FETCH_ALL_RESOURCES_PATH)
                .then()
                .spec(responseSpec(STATUS_OK))
                .extract().response();
    }

    public Response fetchSingleResourceResponse(int resourceId, boolean exist) {
        return given()
                .pathParam("id", resourceId)
                .get(FETCH_SINGLE_RESOURCE_PATH)
                .then()
                .spec(exist ? responseSpec(STATUS_OK) : responseSpec(404))
                .extract().response();
    }
}
