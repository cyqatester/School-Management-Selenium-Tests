package com.schoolmanagement.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AdminDashboardPage extends BasePage {
    private static final String CREATE_FORM = "//form[@action='/school-management-system/admin/users']";
    private final By adminHeading = By.xpath("//*[self::h1 or self::h2][normalize-space()='Admin dashboard']");
    private final By profileHeading = By.xpath("//*[self::h1 or self::h2][normalize-space()='My profile · ADMIN']");
    private final By administrationHeader = By.cssSelector("p.eyebrow");   
    private final By manageAccountsHeader = By.xpath("//*[self::h2 or self::h3][normalize-space()='Manage accounts']");
    private final By createAccountHeader = By.xpath("//*[self::h2 or self::h3][normalize-space()='Create account']");
    private final By adminsButton = By.xpath("//button[normalize-space()='Admins']");
    private final By teachersButton = By.xpath("//button[normalize-space()='Teachers']");
    private final By studentsButton = By.xpath("//button[normalize-space()='Students']");
    private final By firstName = By.xpath(CREATE_FORM + "//label[starts-with(normalize-space(.),'First name')]/input");
    private final By lastName = By.xpath(CREATE_FORM + "//label[starts-with(normalize-space(.),'Last name')]/input");
    private final By username = By.xpath(CREATE_FORM + "//label[starts-with(normalize-space(.),'Username')]/input");
    private final By password = By.xpath(CREATE_FORM + "//label[starts-with(normalize-space(.),'Password')]/input");
    private final By role = By.id("role");
    private final By createButton = By.xpath(CREATE_FORM + "//button[normalize-space()='Create']");

    public AdminDashboardPage(WebDriver driver) { super(driver); }

    public boolean adminHeaderVisible() { return isVisible(administrationHeader); }
    public boolean adminDashboardHeaderVisible() { return isVisible(adminHeading); }
    public boolean profileHeaderVisible() { return isVisible(profileHeading); }
    public boolean manageAccountsHeaderVisible() { return isVisible(manageAccountsHeader); }
    public boolean createAccountHeaderVisible() { return isVisible(createAccountHeader); }
    public boolean accountTabsVisible() {
        return isVisible(adminsButton) && isVisible(teachersButton) && isVisible(studentsButton);
    }
    public boolean createFormVisible() {
        return isVisible(firstName) && isVisible(lastName) && isVisible(username)
                && isVisible(password) && isVisible(role) && isVisible(createButton);
    }

    public void createAccount(String first, String last, String user, String pass, String roleName) {
        type(firstName, first);
        type(lastName, last);
        type(username, user);
        type(password, pass);
        new Select(visible(role)).selectByVisibleText(roleName);
        click(createButton);
    }

    public boolean adminRowVisible(String user, String fullName) {
        By row = By.xpath("//*[@id='admins']//tr[contains(normalize-space(.),'" + user + "')]");
        return isVisible(row) && driver.findElement(row).getText().contains(fullName);
    }

    public void deleteAdmin(String user) {
        By row = By.xpath("//*[@id='admins']//tr[contains(normalize-space(.),'" + user + "')]");
        click(By.xpath("//*[@id='admins']//tr[contains(normalize-space(.),'" + user + "')]//button[normalize-space()='Delete']"));
        wait.until(d -> d.findElements(row).isEmpty() || !d.findElement(row).isDisplayed());
    }
}
