package com.schoolmanagement.tests.web;

import com.schoolmanagement.config.Config;
import com.schoolmanagement.pages.*;
import com.schoolmanagement.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StudentDashboardTest extends BaseTest {
    @Test(groups = "web")
    public void studentDashboardDisplaysExpectedSections() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.signIn(Config.STUDENT_USERNAME, Config.STUDENT_PASSWORD);
        Assert.assertTrue(login.isSignedIn());

        StudentDashboardPage student = new StudentDashboardPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(student.portalHeaderVisible());
        Assert.assertTrue(student.profileHeaderVisible());
        Assert.assertTrue(dashboard.myDashboardHeaderVisible());
        Assert.assertTrue(dashboard.firstNameLabelVisible());
        Assert.assertTrue(dashboard.lastNameLabelVisible());
        Assert.assertTrue(dashboard.usernameLabelVisible());
        Assert.assertTrue(dashboard.newPasswordLabelVisible());
        Assert.assertTrue(dashboard.saveProfileButtonVisible());
        Assert.assertTrue(student.assignedTeachersHeaderVisible());
    }
}
