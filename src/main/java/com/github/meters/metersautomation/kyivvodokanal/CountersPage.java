package com.github.meters.metersautomation.kyivvodokanal;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CountersPage {

    private static final int TIME_OUT_IN_SECONDS = 30;

    By hotCounterHiDigitsLocator = By.xpath("//*[@class='counter-window counter-hot']/*/*[@class='input-container whole']/*[@class='input']/input");
    By hotCounterLoDigitsLocator = By.xpath("//*[@class='counter-window counter-hot']/*/*[@class='input-container fractional']/*[@class='input']/input");
    By activateHotButtonLocator = By.xpath("//*[@class='row counter-hot']/..");
    By submitHotButtonLocator = By.xpath("//*[@class='counter-window counter-hot']/*/*[@name='single_submit']");

    By coldCounterHiDigitsLocator = By.xpath("//*[@class='counter-window counter-cold']/*/*[@class='input-container whole']/*[@class='input']/input");
    By coldCounterLoDigitsLocator = By.xpath("//*[@class='counter-window counter-cold']/*/*[@class='input-container fractional']/*[@class='input']/input");
    By activateColdButtonLocator = By.xpath("//*[@class='row counter-cold']/..");
    By submitColdButtonLocator = By.xpath("//*[@class='counter-window counter-cold']/*/*[@name='single_submit']");

    WebDriver webDriver;

    public CountersPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public CounterData getHotCounterData() {
        return getCounterData(findCounterElements(hotCounterHiDigitsLocator, hotCounterLoDigitsLocator));
    }

    public CounterData getColdCounterData() {
        return getCounterData(findCounterElements(coldCounterHiDigitsLocator, coldCounterLoDigitsLocator));
    }

    public CountersPage activateColdCounter() {
        webDriver.findElement(activateColdButtonLocator).click();
        return this;
    }

    public CountersPage activateHotCounter() {
        webDriver.findElement(activateHotButtonLocator).click();
        return this;
    }

    public CountersPage setColdCounterData(CounterData newData){
        activateColdCounter();
        setCounterData(newData, coldCounterHiDigitsLocator, coldCounterLoDigitsLocator);
        return this;
    }

    public CountersPage setHotCounterData(CounterData newData){
        activateHotCounter();
        setCounterData(newData, hotCounterHiDigitsLocator, hotCounterLoDigitsLocator);
        return this;
    }

    public CountersPage copyHotCounterDataFromPrevious(){
        setHotCounterData(getHotCounterData());
        return this;
    }

    public CountersPage copyColdCounterDataFromPrevious(){
        setColdCounterData(getColdCounterData());
        return this;
    }

    public CountersPage submitColdCounter(){
        activateColdCounter();
        clickSubmitButton(submitColdButtonLocator);
        return this;
    }

    public CountersPage submitHotCounter(){
        activateHotCounter();
        clickSubmitButton(submitHotButtonLocator);
        return this;
    }

    private void clickSubmitButton(By submitLocator) {
        WebElement submitButton = webDriver.findElement(submitLocator);
        submitButton.click();

        WebDriverWait wait1 = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        wait1.until(ExpectedConditions.alertIsPresent());

        log.info(webDriver.switchTo().alert().getText());

        webDriver.switchTo().alert().accept();

        WebDriverWait wait2 = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        wait2.until(ExpectedConditions.alertIsPresent());

        log.info(webDriver.switchTo().alert().getText());

        webDriver.switchTo().alert().accept();
    }

    private CountersPage setCounterData(CounterData newData, By hiDigitsLocator, By loDigitsLocator) {
        List<WebElement> counterElements = findCounterElements(hiDigitsLocator, loDigitsLocator);

        String[] digits = newData.asArray();

        for (int i = 0; i < digits.length; i++) {
            counterElements.get(i).sendKeys(Keys.END);
            counterElements.get(i).sendKeys(Keys.BACK_SPACE);
            counterElements.get(i).sendKeys(digits[i]);
        }

        return this;
    }

    private List<WebElement> findCounterElements(By hiDigitsLocator, By loDigitsLocator) {

        List<WebElement> elementsHi = webDriver.findElements(hiDigitsLocator);
        List<WebElement> elementsLo = webDriver.findElements(loDigitsLocator);

        if (elementsHi.size() != 5) {
            throw new IllegalStateException("Counter hi digits found by xpath size " + elementsHi.size() + " is not equal to 5");
        }

        if (elementsLo.size() != 3) {
            throw new IllegalStateException("Counter lo digits found by xpath size " + elementsLo.size() + " is not equal to 3");
        }

        return Stream.concat(elementsHi.stream(), elementsLo.stream())
                .collect(Collectors.toList());
    }

    private CounterData getCounterData(List<WebElement> elements) {

        String digit1 = elements.get(0).getAttribute("value");
        String digit2 = elements.get(1).getAttribute("value");
        String digit3 = elements.get(2).getAttribute("value");
        String digit4 = elements.get(3).getAttribute("value");
        String digit5 = elements.get(4).getAttribute("value");
        String digit6 = elements.get(5).getAttribute("value");
        String digit7 = elements.get(6).getAttribute("value");
        String digit8 = elements.get(7).getAttribute("value");

        return CounterData.builder()
                .digit1(digit1)
                .digit2(digit2)
                .digit3(digit3)
                .digit4(digit4)
                .digit5(digit5)
                .digit6(digit6)
                .digit7(digit7)
                .digit8(digit8)
                .build();
    }
}
