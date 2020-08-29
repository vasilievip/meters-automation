package com.github.meters.metersautomation.kyivvodokanal;

import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public CountersPage openCountersPage(){
        webDriver.get("https://my.vodokanal.kiev.ua/dashboard/counters");
        return new CountersPage(webDriver);
    }
}
