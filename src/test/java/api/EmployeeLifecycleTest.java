package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class EmployeeLifecycleTest {

    @Test
    public void test_employee_full_lifecycle() {

        String randomUser = "user_" + UUID.randomUUID();

        JSONObject payload = new JSONObject();
        payload.put("username", randomUser);
        payload.put("firstName", "Jane");
        payload.put("lastName", "Smith");
        payload.put("dependants", 1);
        payload.put("salary", 60000.0);

        // CREATE
        Response create = EmployeeAPI.createEmployee(payload.toString());
        Assert.assertEquals(create.statusCode(), 200);

        String empId = create.jsonPath().getString("id");

        // GET
        Response get = EmployeeAPI.getById(empId);
        Assert.assertEquals(get.statusCode(), 200);
        Assert.assertEquals(get.jsonPath().getString("id"), empId);

        // UPDATE
        JSONObject updatePayload = new JSONObject();
        updatePayload.put("id", empId);
        updatePayload.put("username", get.jsonPath().getString("username"));
        updatePayload.put("firstName", get.jsonPath().getString("firstName"));
        updatePayload.put("lastName", get.jsonPath().getString("lastName"));
        updatePayload.put("dependants", 3);
        updatePayload.put("salary", get.jsonPath().getFloat("salary"));

        Response update = EmployeeAPI.updateEmployee(updatePayload.toString());
        Assert.assertEquals(update.statusCode(), 200);
        Assert.assertEquals(update.jsonPath().getInt("dependants"), 3);

        // DELETE
        Response delete = EmployeeAPI.deleteEmployee(empId);
        Assert.assertEquals(delete.statusCode(), 200);
    }
}