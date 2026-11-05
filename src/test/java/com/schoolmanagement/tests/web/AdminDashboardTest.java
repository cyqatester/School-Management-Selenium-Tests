package com.schoolmanagement.tests.web;

import com.schoolmanagement.config.Config;
import com.schoolmanagement.pages.*;
import com.schoolmanagement.tests.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class AdminDashboardTest extends BaseTest {
    private void signInAdmin() {
        if (Config.E2E_USERNAME.isBlank() || Config.E2E_PASSWORD.isBlank()) {
            throw new SkipException("Set E2E_USERNAME and E2E_PASSWORD for admin dashboard tests.");
        }
        LoginPage login = new LoginPage(driver);
        login.open();
        login.signIn(Config.E2E_USERNAME, Config.E2E_PASSWORD);
        Assert.assertTrue(login.isSignedIn());
    }

    @Test(groups = "web")
    public void adminDashboardDisplaysExpectedSectionsAndControls() {
        signInAdmin();
        AdminDashboardPage admin = new AdminDashboardPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);

        Assert.assertTrue(admin.adminHeaderVisible());
        Assert.assertTrue(admin.adminDashboardHeaderVisible());
        Assert.assertTrue(admin.profileHeaderVisible());
        Assert.assertTrue(dashboard.firstNameLabelVisible());
        Assert.assertTrue(dashboard.lastNameLabelVisible());
        Assert.assertTrue(dashboard.usernameLabelVisible());
        Assert.assertTrue(dashboard.newPasswordLabelVisible());
        Assert.assertTrue(dashboard.saveProfileButtonVisible());
        Assert.assertTrue(admin.manageAccountsHeaderVisible());
        Assert.assertTrue(admin.createAccountHeaderVisible());
        Assert.assertTrue(admin.accountTabsVisible());
        Assert.assertTrue(admin.createFormVisible());
    }

    @Test(groups = "web")
    public void adminCanCreateAndDeleteAnotherAdmin() {
        signInAdmin();
        AdminDashboardPage admin = new AdminDashboardPage(driver);
        String suffix = String.valueOf(System.currentTimeMillis());
        String username = "autoAdmin" + suffix;
        admin.createAccount("Automation", "Admin", username, "Test1234!", "Admin");
        Assert.assertTrue(admin.adminRowVisible(username, "Automation Admin"),
                "New admin should appear in the admins table");
        admin.deleteAdmin(username);
        Assert.assertFalse(admin.adminRowVisible(username, "Automation Admin"),
                "Deleted admin should no longer appear");
    }
}
