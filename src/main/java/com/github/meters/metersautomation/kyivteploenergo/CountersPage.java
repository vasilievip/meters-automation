package com.github.meters.metersautomation.kyivteploenergo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CountersPage {

    By hotWaterTextLocator = By.xpath("//div[contains(text(),'Гаряча')]");

    By hotWaterPreviousLocator;
    By hotWaterNextLocator;

    By submitLocator = By.id("edit-next");
    By successPanelLocator = By.xpath("//*[@class='success']");


    WebDriver webDriver;

    public CountersPage(WebDriver webDriver) {
        this.webDriver = webDriver;

        initHotCounterLocators(webDriver);
    }

    private void initHotCounterLocators(WebDriver webDriver) {
        WebElement hotWaterTextElement = webDriver.findElement(hotWaterTextLocator);

        String hotWaterTextElementID = hotWaterTextElement.getAttribute("id");

        String hotWaterPreviousID = hotWaterTextElementID.replace("edit-service-name", "edit-counter-prev");
        String hotWaterNextID = hotWaterTextElementID.replace("edit-service-name", "edit-counter-last");

        hotWaterPreviousLocator = By.id(hotWaterPreviousID);
        hotWaterNextLocator = By.id(hotWaterNextID);
    }

    public String getHotCounterPreviousData(){
        String text = webDriver.findElement(hotWaterPreviousLocator).getText();
        String[] lines = text.split("\n");
        if(lines.length > 0)
            return lines[0];
        return "";
    }

    public CountersPage setHotCounterData(String data){
        webDriver.findElement(hotWaterNextLocator).clear();
        webDriver.findElement(hotWaterNextLocator).sendKeys(data);
        return this;
    }

    public CountersPage copyHotCounterFromPrevious(){
        setHotCounterData(getHotCounterPreviousData());
        return this;
    }

    public CountersPage copyCountersDataFromPrevious(){
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
