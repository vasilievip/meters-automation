package com.github.meters.metersautomation.kyivteploenergo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CountersPage {

    private static final int TIME_OUT_IN_SECONDS = 30;

    By hotWaterTextLocator = By.xpath("//div[contains(text(),'Гаряча')]");

    By hotWaterPreviousLocator;
    By hotWaterNextLocator;

    By submitLocator = By.id("edit-next");
    By submitBigDifferenceLocator = By.id("edit-next-big-counters");
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
        List<WebElement> elements = webDriver.findElements(submitBigDifferenceLocator);
        if(elements.size() > 0 && elements.get(0).isDisplayed()){
            throw new IllegalStateException("Зважаючи на зазначені Вами показання, гарячої води було спожито понад 20 куб.");
        }
        return this;
    }

    public String getSuccessPopupText(){
        new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(successPanelLocator));

        return webDriver.findElement(successPanelLocator).getAttribute("innerHTML");
    }
}
