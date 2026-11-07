package com.schoolmanagement.tests.web;

import com.schoolmanagement.config.Config;
import com.schoolmanagement.pages.LoginPage;
import com.schoolmanagement.tests.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(groups = "web", description = "Verify is the login elements are visible")
    public void loginPageDisplaysUsernamePasswordAndSignIn() {
        LoginPage login = new LoginPage(driver);
        login.open();
        Assert.assertTrue(login.loginFormVisible(), "Username, password and Sign in controls should be visible");
    }

    @Test(groups = "web", description = "Verify if a user can login with valid credentials")
    public void configuredUserCanSignIn() {
        if (Config.E2E_USERNAME.isBlank() || Config.E2E_PASSWORD.isBlank()) {
            throw new SkipException("Set E2E_USERNAME and E2E_PASSWORD to enable this authenticated login test.");
        }
        LoginPage login = new LoginPage(driver);
        login.open();
        login.signIn(Config.E2E_USERNAME, Config.E2E_PASSWORD);
        Assert.assertTrue(login.isSignedIn(), "Expected successful login to leave the login page");
    }
}
