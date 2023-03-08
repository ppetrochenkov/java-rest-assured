import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.User;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestUserOperations {

    @Test
    @DisplayName("Test User creation")
    public void testUserCreation() {
        User user = User.builder()
                .username("restAssuredTestUser")
                .firstName("Test")
                .lastName("Test")
                .email("restAssuredTest@mail.com")
                .password("!Welcome123")
                .phone("5555555")
                .userStatus(0)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .log().all()
                .post("https://petstore.swagger.io/v2/user")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

        String url = "https://petstore.swagger.io/v2/user/" + user.getUsername();

        User receivedUser = get(url)
                .then().extract().body().as(User.class);

        assertThat(receivedUser.getUsername(), equalTo(user.getUsername()));
    }
}
