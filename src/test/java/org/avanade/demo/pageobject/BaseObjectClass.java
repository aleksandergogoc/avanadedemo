package org.avanade.demo.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

import static org.testng.Assert.assertEquals;

public abstract class BaseObjectClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait longWait;

    public BaseObjectClass(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,20);

        //Longer wait to use in particular situations:
        longWait = new WebDriverWait(driver,60);
    }

    protected void waitForElementVisibility(By element){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void waitForElementClickable(By element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clearElement(By element) {
        driver.findElement(element).click();
        driver.findElement(element).clear();
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

    protected boolean hasAnyTextDisplayed(By locator){
        return (driver.findElement(locator).getText() != null && driver.findElement(locator).getText() != "");
    }

    protected void moveToElement(By locator){
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(locator);
        action.moveToElement(we).perform();
    }

    protected String getElementText(By locator){
        return driver.findElement(locator).getText();
    }

    protected String getElementValue(By locator){
        return driver.findElement(locator).getAttribute("value");
    }

    protected boolean isElementCountEqualTo(By locator, int count){
        List<WebElement> elements = driver.findElements(locator);
        return (elements.size() == count);
    }
}
