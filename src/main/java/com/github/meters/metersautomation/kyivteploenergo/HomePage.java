package com.github.meters.metersautomation.kyivteploenergo;

import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public CountersPage openCountersPage(){
        webDriver.get("https://kte.kmda.gov.ua/my-cabinet/send_counters/send_current_new/870300");
        return new CountersPage(webDriver);
    }
}
