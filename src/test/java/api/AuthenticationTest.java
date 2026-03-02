package api;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest {

    @Test
    public void test_api_without_auth_should_fail() {

        RestAssured.baseURI = Config.BASE_URL;

        Response response =
                RestAssured
                        .given()
                        .get("/api/Employees");

        Assert.assertTrue(
                response.statusCode() == 401 ||
                response.statusCode() == 403
        );
    }
}