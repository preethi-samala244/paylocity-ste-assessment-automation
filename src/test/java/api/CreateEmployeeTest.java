package api;

import config.Config;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class CreateEmployeeTest {

    @Test
    public void test_create_employee() {

        String randomUser = "user_" + UUID.randomUUID();

        JSONObject payload = new JSONObject();
        payload.put("username", randomUser);
        payload.put("firstName", "John");
        payload.put("lastName", "Doe");
        payload.put("dependants", 2);
        payload.put("salary", 52000.0);

        Response response = EmployeeAPI.createEmployee(payload.toString());

        Assert.assertEquals(response.statusCode(), 200);

        JsonPath data = response.jsonPath();

        Assert.assertNotNull(data.get("id"));

        // API overrides username with authenticated user
        Assert.assertEquals(data.getString("username"), Config.USERNAME);

        Assert.assertEquals(data.getString("firstName"), "John");
        Assert.assertEquals(data.getString("lastName"), "Doe");
        Assert.assertEquals(data.getInt("dependants"), 2);
        Assert.assertEquals(data.getFloat("salary"), 52000.0f);

        float gross = data.getFloat("gross");
        float benefits = data.getFloat("benefitsCost");
        float net = data.getFloat("net");

        Assert.assertTrue(gross > 0);
        Assert.assertTrue(benefits >= 0);

        float expectedNet = gross - benefits;
        Assert.assertTrue(Math.abs(net - expectedNet) < 0.01);
    }
}