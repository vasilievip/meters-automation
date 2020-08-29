package com.github.meters.metersautomation.kyivteploenergo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    By usernameLocator = By.id("edit-name");
    By passwordLocator = By.id("edit-pass");
    By loginButtonLocator = By.id("edit-submit");

    WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        webDriver.get("https://kte.kmda.gov.ua/my-cabinet/");
        this.webDriver = webDriver;
    }

    public LoginPage typeUsername(String username) {
        webDriver.findElement(usernameLocator).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        webDriver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        webDriver.findElement(loginButtonLocator).click();
        return new HomePage(webDriver);
    }

    public HomePage loginAs(String username, String password) {
        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
}
