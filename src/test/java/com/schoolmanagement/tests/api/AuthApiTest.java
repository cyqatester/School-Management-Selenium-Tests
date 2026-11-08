package com.schoolmanagement.tests.api;

import com.schoolmanagement.config.Config;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class AuthApiTest extends ApiBaseTest {
    @Test(groups = "api", description = "API - Verify that unauthenticated requests to GET api/me redirect to the login page")
    public void unauthenticatedCurrentUserRequestRedirectsToLogin() {
        given()
            .redirects().follow(false)
        .when()
            .get(Config.url("api/me"))
        .then()
            .statusCode(302)
            .header("Location", containsString("/login"));
    }
}
