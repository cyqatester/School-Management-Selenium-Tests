package com.schoolmanagement.tests.web;

import com.schoolmanagement.config.Config;
import com.schoolmanagement.pages.HomePage;
import com.schoolmanagement.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {
    @Test(groups = "web")
    public void applicationBasePageResponds() {
        HomePage home = new HomePage(driver);
        home.open();
        Assert.assertTrue(home.isLoaded(), "Expected the application page body to contain content");
        Assert.assertTrue(driver.getCurrentUrl().startsWith(Config.BASE_URL));
    }
}
