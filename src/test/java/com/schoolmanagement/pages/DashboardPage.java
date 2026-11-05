package com.schoolmanagement.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private static final String PROFILE_FORM = "//form[@action='/school-management-system/profile']//label[starts-with(normalize-space(.),";

    public DashboardPage(WebDriver driver) { super(driver); }

    public boolean myDashboardHeaderVisible() {
        return isVisible(By.xpath("//h1[normalize-space()='My dashboard'] | //h2[normalize-space()='My dashboard']"));
    }

    public boolean saveProfileButtonVisible() {
        return isVisible(By.xpath("//button[normalize-space()='Save profile']"));
    }

    public boolean firstNameLabelVisible() {
        return isVisible(By.xpath(PROFILE_FORM + "'First name')]"));
    }

    public boolean lastNameLabelVisible() {
        return isVisible(By.xpath(PROFILE_FORM + "'Last name')]"));
    }

    public boolean usernameLabelVisible() {
        return isVisible(By.xpath(PROFILE_FORM + "'Username')]"));
    }

    public boolean newPasswordLabelVisible() {
        return isVisible(By.xpath(PROFILE_FORM + "'New password')]"));
    }
}
