package com.github.meters.metersautomation.kyivvodokanal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final int TIME_OUT_IN_SECONDS = 30;

    WebDriver webDriver;

    By countersLinkLocator = By.className("custom-select");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;

        new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(countersLinkLocator));
    }

    public CountersPage openCountersPage() {
        webDriver.get("https://my.vodokanal.kiev.ua/dashboard/counters");
        return new CountersPage(webDriver);
    }
}
