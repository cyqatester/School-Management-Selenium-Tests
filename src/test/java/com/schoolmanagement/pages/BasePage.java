package com.schoolmanagement.pages;

import com.schoolmanagement.config.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    public void openPath(String path) {
        driver.get(Config.url(path));
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String value) {
        WebElement element = visible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected boolean isVisible(By locator) {
        try {
            return visible(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException ex) {
            return false;
        }
    }

    protected By label(String formCss, String labelText) {
        return By.cssSelector(formCss + " label");
    }

    protected By inputInsideLabel(String formCss, String labelText) {
        return By.xpath("//form[" + xpathFormActionPredicate(formCss) + "]//label[normalize-space(string(.)[1])='" + labelText + "']//input");
    }

    private String xpathFormActionPredicate(String css) {
        // The application forms use stable action URLs; map the CSS selector to its action attribute.
        if (css.contains("/admin/users")) return "@action='/school-management-system/admin/users'";
        if (css.contains("/profile")) return "@action='/school-management-system/profile'";
        throw new IllegalArgumentException("Unsupported form selector: " + css);
    }

    public boolean bodyHasText(String text) {
        return driver.findElement(By.tagName("body")).getText().contains(text);
    }
}
