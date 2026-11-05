package com.schoolmanagement.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TeacherDashboardPage extends BasePage {
    private final By portalHeader = By.xpath("//*[contains(normalize-space(.),'TEACHER PORTAL')]");
    private final By profileHeader = By.xpath("//*[self::h1 or self::h2][normalize-space()='My profile · TEACHER']");
    private final By assignedStudentsHeader = By.xpath("//*[self::h2 or self::h3][normalize-space()='Assigned students']");
    private final By addStudentSummary = By.xpath("//summary[normalize-space()='Add student']");

    public TeacherDashboardPage(WebDriver driver) { super(driver); }
    public boolean portalHeaderVisible() { return isVisible(portalHeader); }
    public boolean profileHeaderVisible() { return isVisible(profileHeader); }
    public boolean assignedStudentsHeaderVisible() { return isVisible(assignedStudentsHeader); }
    public boolean addStudentSummaryVisible() { return isVisible(addStudentSummary); }
}
