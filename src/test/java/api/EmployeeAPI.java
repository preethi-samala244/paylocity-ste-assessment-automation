package api;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EmployeeAPI {

    static {
        RestAssured.baseURI = Config.BASE_URL;

        // Perform login (like Python client.py)
        Response loginResponse =
                given()
                        .header("Authorization", Config.AUTH_TOKEN)
                        .header("Content-Type", "application/json")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .body("{\"username\":\"" + Config.USERNAME +
                                "\",\"password\":\"" + Config.PASSWORD + "\"}")
                .when()
                        .post("/Account/Login");

        if (loginResponse.statusCode() != 200) {
            throw new RuntimeException("Login Failed: " + loginResponse.asString());
        }
    }

    public static Response createEmployee(Object payload) {
        return given()
                .header("Authorization", Config.AUTH_TOKEN)
                .header("Content-Type", "application/json")
                .header("X-Requested-With", "XMLHttpRequest")
                .body(payload)
                .post("/api/Employees");
    }

    public static Response getAll() {
        return given()
                .header("Authorization", Config.AUTH_TOKEN)
                .get("/api/Employees");
    }

    public static Response getById(String id) {
        return given()
                .header("Authorization", Config.AUTH_TOKEN)
                .get("/api/Employees/" + id);
    }

    public static Response updateEmployee(Object payload) {
        return given()
                .header("Authorization", Config.AUTH_TOKEN)
                .header("Content-Type", "application/json")
                .header("X-Requested-With", "XMLHttpRequest")
                .body(payload)
                .put("/api/Employees");
    }

    public static Response deleteEmployee(String id) {
        return given()
                .header("Authorization", Config.AUTH_TOKEN)
                .delete("/api/Employees/" + id);
    }
}
