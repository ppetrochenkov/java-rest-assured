package com.local.userTests;

import com.local.BaseTest;
import com.local.client.user.UserClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.local.utils.TestSuiteTags.CONTRACT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestUsersContract extends BaseTest {

    @Test
    @Tag(CONTRACT)
    @DisplayName("Validate users response Json contract")
    public void testUsersContract() {
        given()
                .get(UserClient.FETCH_ALL_USERS_PATH)
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/users_schema.json"));
    }
}
