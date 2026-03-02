package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeCasesTest {

    @Test
    public void test_missing_required_fields() {

        JSONObject payload = new JSONObject();
        payload.put("firstName", "John");

        Response response = EmployeeAPI.createEmployee(payload.toString());

        Assert.assertNotEquals(response.statusCode(), 200);
    }

    @Test
    public void test_invalid_dependants() {

        JSONObject payload = new JSONObject();
        payload.put("username", "bad_user");
        payload.put("firstName", "John");
        payload.put("lastName", "Doe");
        payload.put("dependants", 40);
        payload.put("salary", 50000);

        Response response = EmployeeAPI.createEmployee(payload.toString());

        Assert.assertNotEquals(response.statusCode(), 200);
    }

    @Test
    public void test_negative_salary_behavior() {

        JSONObject payload = new JSONObject();
        payload.put("username", "bad_user2");
        payload.put("firstName", "John");
        payload.put("lastName", "Doe");
        payload.put("dependants", 2);
        payload.put("salary", -100);

        Response response = EmployeeAPI.createEmployee(payload.toString());

        // API incorrectly accepts negative salary
        Assert.assertEquals(response.statusCode(), 200);

        float returnedSalary = response.jsonPath().getFloat("salary");

        // BUG validation (same as Python)
        Assert.assertNotEquals(returnedSalary, -100f);
    }
}