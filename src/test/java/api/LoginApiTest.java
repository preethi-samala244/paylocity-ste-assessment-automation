package api;

import base.BaseAPI;
import config.Config;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginApiTest extends BaseAPI {

    @Test
    public void test_login_with_basic_auth_header() {

        Response response =
                given()
                        .header("Authorization", Config.AUTH_TOKEN)
                        .header("Content-Type", "application/json")
                .when()
                        .post("/Account/Login");

        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void test_login_with_basic_auth_method() {

        Response response =
                given()
                        .auth()
                        .preemptive()
                        .basic(Config.USERNAME, Config.PASSWORD)
                .when()
                        .post("/Account/Login");

        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}