package com.github.meters.metersautomation.kyivvodokanal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    By usernameLocator = By.id("account");
    By passwordLocator = By.id("password");
    By loginButtonLocator = By.className("submit-button");

    WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        webDriver.get("https://my.vodokanal.kiev.ua/dashboard/sign-in");
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
