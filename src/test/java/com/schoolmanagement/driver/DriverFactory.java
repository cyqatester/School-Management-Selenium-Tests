package com.schoolmanagement.driver;

import com.schoolmanagement.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {
    private DriverFactory() {}

    public static WebDriver createDriver() {
        WebDriver driver;
        
        switch (Config.BROWSER.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                if (Config.HEADLESS) 
                    options.addArguments("-headless");
                driver = new FirefoxDriver(options);
            }
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (Config.HEADLESS) 
                    options.addArguments("--headless=new");
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1440,1000");
                driver = new ChromeDriver(options);
            }
            default -> throw new IllegalArgumentException("Unsupported BROWSER: " + Config.BROWSER);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        if (!Config.HEADLESS) 
            driver.manage().window().setSize( new Dimension(1280, 1000));
        return driver;
    }
}
