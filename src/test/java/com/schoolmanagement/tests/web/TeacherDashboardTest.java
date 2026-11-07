package com.schoolmanagement.tests.web;

import com.schoolmanagement.config.Config;
import com.schoolmanagement.pages.*;
import com.schoolmanagement.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeacherDashboardTest extends BaseTest {
    @Test(groups = "web", description = "Verify if a Teacher can login and loads the Dashboard loads correctly")
    public void teacherDashboardDisplaysExpectedSections() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.signIn(Config.TEACHER_USERNAME, Config.TEACHER_PASSWORD);
        Assert.assertTrue(login.isSignedIn());

        TeacherDashboardPage teacher = new TeacherDashboardPage(driver);
        DashboardPage dashboard = new DashboardPage(driver);
        Assert.assertTrue(teacher.portalHeaderVisible());
        Assert.assertTrue(teacher.profileHeaderVisible());
        Assert.assertTrue(dashboard.myDashboardHeaderVisible());
        Assert.assertTrue(dashboard.firstNameLabelVisible());
        Assert.assertTrue(dashboard.lastNameLabelVisible());
        Assert.assertTrue(dashboard.usernameLabelVisible());
        Assert.assertTrue(dashboard.newPasswordLabelVisible());
        Assert.assertTrue(dashboard.saveProfileButtonVisible());
        Assert.assertTrue(teacher.addStudentSummaryVisible());
        Assert.assertTrue(teacher.assignedStudentsHeaderVisible());
    }
}
