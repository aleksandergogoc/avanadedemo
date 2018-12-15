package org.avanade.demo.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseObjectClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseObjectClass(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,30);
    }

    protected void waitForElementVisibility(By element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void waitForElementClickable(By element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElement(By locator) {
        try {
            driver.findElement(locator).click();
        } catch (Exception e){
            WebElement element = driver.findElement(locator);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    protected void moveToElement(By locator){
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(locator);
        action.moveToElement(we).perform();
    }

    protected String getElementText(By locator){
        return driver.findElement(locator).getText();
    }

}
