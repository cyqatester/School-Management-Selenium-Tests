package com.schoolmanagement.tests.api;

import com.schoolmanagement.config.Config;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class HealthApiTest extends ApiBaseTest {
    @Test(groups = "api")
    public void applicationRootIsReachable() {
        given()
            .redirects().follow(true)
        .when()
            .get(Config.BASE_URL)
        .then()
            .statusCode(lessThan(500));
    }
}
