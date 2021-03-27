package com.github.meters.metersautomation.kyivvodokanal;

import co.boorse.seleniumtable.SeleniumTable;
import co.boorse.seleniumtable.SeleniumTableRow;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;

@Slf4j
public class CountersPage {

    static final int TEN_TIMES = 10;
    static final int TIME_OUT_IN_SECONDS = 30;

    static final int COUNTER_NAME_COLUMN_INDEX = 2;
    static final int COUNTER_PREVIOUS_COLUMN_INDEX = 5;
    static final int COUNTER_NEXT_COLUMN_INDEX = 6;

    static final String HOT_COUNTER_NAME = "гарячої";
    static final String COLD_COUNTER_NAME = "Холодна";

    By metersTableLocator = By.xpath("//*[@class=\"table\"]");
    By submitButtonLocator = By.xpath("//*[@class='btn btn-info btn-submit']");

    By changeViewButtonLocator = By.cssSelector(".btn-sm");

    WebDriver webDriver;

    public CountersPage(WebDriver webDriver) {

        new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(changeViewButtonLocator));

        this.webDriver = webDriver;
        if (webDriver.findElements(submitButtonLocator).isEmpty()) {
            webDriver.findElement(changeViewButtonLocator).click();
        }
        new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(submitButtonLocator));
    }

    private SeleniumTable getCountersTable() {
        WebElement tableElement = webDriver.findElement(metersTableLocator);
        return SeleniumTable.getInstance(tableElement);
    }

    private String getCounterData(String counterName) {
        Optional<SeleniumTableRow> counterRow = getCounterRow(counterName);
        if (counterRow.isEmpty()) return "";

        return counterRow.get().get(COUNTER_PREVIOUS_COLUMN_INDEX).getText();
    }

    private void setCounterData(String counterName, String newData) {

        Optional<SeleniumTableRow> counterRow = getCounterRow(counterName);

        if (counterRow.isEmpty()) return;

        WebElement element = counterRow.get().get(COUNTER_NEXT_COLUMN_INDEX).getElement();
        WebElement input = element.findElement(By.tagName("input"));

        clearElement(input);

        input.sendKeys(newData);
    }

    private void clearElement(WebElement element) {
        element.sendKeys(Keys.END);
        for (int i = 0; i < TEN_TIMES; i++)
            element.sendKeys(Keys.BACK_SPACE);
    }

    private Optional<SeleniumTableRow> getCounterRow(String counterName) {
        SeleniumTable table = getCountersTable();
        for (SeleniumTableRow row : table.rows())
            if (isNeededCounterRow(row, counterName))
                return Optional.of(row);
        return Optional.empty();
    }

    private boolean isNeededCounterRow(SeleniumTableRow row, String name) {
        return row.get(COUNTER_NAME_COLUMN_INDEX).getText().contains(name);
    }

    public CountersPage copyHotCounterDataFromPrevious() {
        setHotCounterData(getHotCounterData());
        return this;
    }

    private String getHotCounterData() {
        return getCounterData(HOT_COUNTER_NAME);
    }

    public CountersPage setHotCounterData(String newData) {
        setCounterData(HOT_COUNTER_NAME, newData);
        return this;
    }

    public CountersPage copyColdCounterDataFromPrevious() {
        setColdCounterData(getColdCounterData());
        return this;
    }

    private String getColdCounterData() {
        return getCounterData(COLD_COUNTER_NAME);
    }

    public CountersPage setColdCounterData(String newData) {
        setCounterData(COLD_COUNTER_NAME, newData);
        return this;
    }

    public CountersPage submitCounters() {
        clickSubmitButton(submitButtonLocator);
        return this;
    }

    private void clickSubmitButton(By submitLocator) {
        WebElement submitButton = webDriver.findElement(submitLocator);
        submitButton.click();

        new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.alertIsPresent());

        log.info(webDriver.switchTo().alert().getText());

        webDriver.switchTo().alert().accept();
    }
}
