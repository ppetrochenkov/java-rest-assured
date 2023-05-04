package com.local.resourceTests;

import com.local.BaseTest;
import com.local.client.resource.ResourceClient;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.local.utils.TestSuiteTags.CONTRACT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Feature("Verify Resource JSON contract")
public class TestResourcesContract extends BaseTest {

    @Test
    @Tag(CONTRACT)
    @DisplayName("Validate resources response Json contract")
    public void testResourcesContract() {
        given()
                .get(ResourceClient.FETCH_ALL_RESOURCES_PATH)
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/resources_schema.json"));
    }
}
