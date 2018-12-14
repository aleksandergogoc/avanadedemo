package org.avanade.demo.pageobject.Careers;

import org.avanade.demo.pageobject.BaseObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LocationsList extends BaseObjectClass {

    public LocationsList(WebDriver driver) {
        super(driver);
        String URL = driver.getCurrentUrl();
        if (!URL.contains("/locations")) {
            try {
                wait.until(ExpectedConditions.urlContains("/locations"));
                if (!URL.contains("/locations"))
                    throw new IllegalStateException("/locations");
            } catch (Exception e) {
            }
        }
    }

    public CareerSearchResults clickLocation(String cityName){
        waitForElementClickable(By.linkText(cityName));
        clickElement(By.linkText(cityName));
        return new CareerSearchResults(driver);
    }

}
