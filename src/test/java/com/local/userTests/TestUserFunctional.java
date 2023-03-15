package com.local.userTests;

import com.local.client.user.LoginClient;
import com.local.client.user.RegisterClient;
import com.local.client.user.UserClient;
import com.local.model.LoginDto;
import com.local.model.RegisterDto;
import com.local.model.UserDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.local.utils.TestSuiteTags.FUNCTIONAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestUserFunctional {

    private final UserClient userClient = new UserClient();
    private final RegisterClient registerClient = new RegisterClient();
    private final LoginClient loginClient = new LoginClient();

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify user's avatar path contains id value")
    public void testUserAvatarMatchingIdValue() {
        Response response = userClient.fetchUsersListResponse(20, 0);

        int totalUsersCount = response.path("total");
        List<UserDto> userList = response.jsonPath().getList("data", UserDto.class);

        assertThat(totalUsersCount, equalTo(userList.size()));

        userList.forEach(user -> {
            assertThat(user.getAvatar(), containsString(user.getId().toString()));
        });
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify existing user fetching")
    public void testExistingUserFetch() {
        UserDto user = userClient.fetchSingleUserResponse(2, true)
                .jsonPath()
                .getObject("data", UserDto.class);
        assertThat(user, notNullValue());
        assertThat(user.getEmail(), equalTo("janet.weaver@reqres.in"));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify not existing user fetching")
    public void testNotExistingUserFetch() {
        Response response = userClient.fetchSingleUserResponse(23, false);

        assertThat(response.getStatusCode(), equalTo(404));
        assertThat(response.getStatusLine(), containsString("Not Found"));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify user successful registration")
    public void testUserSuccessfulRegistration() {
        RegisterDto successfulPayload = RegisterDto.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        Response response = registerClient.registerUserResponse(successfulPayload);
        assertThat(response.path("token"), notNullValue());
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify user unsuccessful registration")
    public void testUserUnsuccessfulRegistration() {
        RegisterDto unsuccessfulPayload = RegisterDto.builder()
                .email("eve.holt@reqres.in")
                .build();

        Response response = registerClient.registerUserResponse(unsuccessfulPayload);

        assertThat(response.getStatusCode(), equalTo(400));
        assertThat(response.path("error"), equalTo("Missing password"));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify user successful login")
    public void testUserSuccessfulLogin() {
        LoginDto payload = (LoginDto) LoginDto.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        Response response = loginClient.loginResponse(payload);
        assertThat(response.path("token"), notNullValue());
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify user unsuccessful login")
    public void testUserUnsuccessfulLogin() {
        LoginDto payload = (LoginDto) LoginDto.builder()
                .email("peter@klaven")
                .build();

        Response response = loginClient.loginResponse(payload);

        assertThat(response.getStatusCode(), equalTo(400));
        assertThat(response.path("error"), equalTo("Missing password"));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Verify fetching all users with delayed response")
    public void testDelayedResponse() {
        Response response = userClient.fetchUsersListResponse(20, 3);

        int totalUsersCount = response.path("total");
        List<UserDto> userList = response.jsonPath().getList("data", UserDto.class);

        assertThat(totalUsersCount, equalTo(userList.size()));
    }
}
