package com.todoapp.web.wrapper;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;


public class wrapperMethods {

    public static WebDriver driver = null;
    private void createBrowserDriver(String browser) {

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
            default:
                System.out.println("Configuration not found for given browser - " + browser);
        }
    }

    public WebDriver openBrowser(String browser) {
        if (driver == null) {
            createBrowserDriver(browser);
        }
        driver = getDriver();
        return driver;
    }

    private static WebDriver getDriver() {
        return driver;
    }

    public static String getElementText(WebElement element) {
        return element.getText();
    }

    public static void setElementText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public static void doubleClickOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).build().perform();
    }
}
