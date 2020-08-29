package com.github.meters.metersautomation.kyivteploenergo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CountersPage {

    By coldWaterPreviousLocator = By.id("edit-counter-prev-215487-1");
    By hotWaterPreviousLocator = By.id("edit-counter-prev-243683-2");

    By coldWaterNextLocator = By.id("edit-counter-last-215487-1");
    By hotWaterNextLocator = By.id("edit-counter-last-243683-2");

    By submitLocator = By.id("edit-next");
    By successPanelLocator = By.xpath("//*[@class='success']");

    WebDriver webDriver;

    public CountersPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getColdCounterPreviousData(){
        String text = webDriver.findElement(coldWaterPreviousLocator).getText();
        String[] lines = text.split("\n");
        if(lines.length > 0)
            return lines[0];
        return "";
    }

    public String getHotCounterPreviousData(){
        String text = webDriver.findElement(hotWaterPreviousLocator).getText();
        String[] lines = text.split("\n");
        if(lines.length > 0)
            return lines[0];
        return "";
    }

    public CountersPage setColdCounterData(String data){
        webDriver.findElement(coldWaterNextLocator).clear();
        webDriver.findElement(coldWaterNextLocator).sendKeys(data);
        return this;
    }

    public CountersPage setHotCounterData(String data){
        webDriver.findElement(hotWaterNextLocator).clear();
        webDriver.findElement(hotWaterNextLocator).sendKeys(data);
        return this;
    }

    public CountersPage copyColdCounterFromPrevious(){
        setColdCounterData(getColdCounterPreviousData());
        return this;
    }

    public CountersPage copyHotCounterFromPrevious(){
        setHotCounterData(getHotCounterPreviousData());
        return this;
    }

    public CountersPage copyCountersDataFromPrevious(){
        copyColdCounterFromPrevious();
        copyHotCounterFromPrevious();
        return this;
    }

    public CountersPage submit(){
        webDriver.findElement(submitLocator).click();
        return this;
    }

    public String getSuccessPopupText(){
        return webDriver.findElement(successPanelLocator).getAttribute("innerHTML");
    }
}
