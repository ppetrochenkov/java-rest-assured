import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import pojo.User;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestUserOperations {

    @Test
    public void testUserCreation() {
        User user = new User();

        user.setId(1232312);
        user.setUsername("testRIO");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("test@mail.com");
        user.setPassword("!Welcome123");
        user.setPhone("5555555");
        user.setUserStatus(0);

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
