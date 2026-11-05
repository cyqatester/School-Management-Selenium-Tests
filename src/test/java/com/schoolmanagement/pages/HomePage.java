package com.schoolmanagement.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) { super(driver); }

    public void open() { openPath("./"); }

    public boolean isLoaded() {
        return wait.until(d -> d.findElement(By.tagName("body")).getText().trim().length() > 0);
    }
}
