package com.schoolmanagement.tests.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.schoolmanagement.listeners.TestListener;

@Listeners(TestListener.class)
public abstract class ApiBaseTest {
    // API tests use absolute URLs so a context path in BASE_URL is preserved.
    @BeforeClass(alwaysRun = true)
    public void configureRestAssured() {
        io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
