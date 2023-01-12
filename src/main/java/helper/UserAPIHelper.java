package helper;

import io.restassured.response.Response;
import pojo.User;

import static io.restassured.RestAssured.given;

public class UserAPIHelper {

    private static final String USER_URL = "/api/auth/user/";
    private static final String LOGIN_URL = "/api/auth/login/";

    public Response deleteUser(String token) {
        Response response =
                given()
                        .header("Authorization", token)
                        .delete(USER_URL);
        return response;
    }

    public void deleteUser(User user) {
        Response response = loginUser(user);
        String accessToken = response.path("accessToken");
        deleteUser(accessToken);
    }

    public Response loginUser(User user) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(user)
                        .when()
                        .post(LOGIN_URL);
        return response;
    }
}
