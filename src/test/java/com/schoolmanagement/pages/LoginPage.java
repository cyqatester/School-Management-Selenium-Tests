package com.schoolmanagement.pages;

import com.schoolmanagement.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By username = By.cssSelector("input[name='username']");
    private final By password = By.cssSelector("input[name='password']");
    private final By signInButton = By.xpath("//button[normalize-space()='Sign in']");

    public LoginPage(WebDriver driver) { super(driver); }

    public void open() { openPath(Config.LOGIN_PATH); }

    public boolean loginFormVisible() {
        return isVisible(username) && isVisible(password) && isVisible(signInButton);
    }

    public void signIn(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(signInButton);
    }

    public boolean isSignedIn() {
        return wait.until(d -> !d.getCurrentUrl().matches(".*?/login(?:\\?.*)?$"));
    }
}
