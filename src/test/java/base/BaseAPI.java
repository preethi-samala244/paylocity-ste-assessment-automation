package base;

import config.Config;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseAPI {

    @BeforeClass
    public void setupAPI() {
        RestAssured.baseURI = Config.BASE_URL;
    }
}
