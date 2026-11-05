package com.schoolmanagement.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StudentDashboardPage extends BasePage {
    private final By portalHeader = By.xpath("//*[contains(normalize-space(.),'STUDENT PORTAL')]");
    private final By profileHeader = By.xpath("//*[self::h1 or self::h2][normalize-space()='My profile · STUDENT']");
    private final By assignedTeachersHeader = By.xpath("//*[self::h2 or self::h3][normalize-space()='My assigned teachers']");

    public StudentDashboardPage(WebDriver driver) { super(driver); }
    public boolean portalHeaderVisible() { return isVisible(portalHeader); }
    public boolean profileHeaderVisible() { return isVisible(profileHeader); }
    public boolean assignedTeachersHeaderVisible() { return isVisible(assignedTeachersHeader); }
}
