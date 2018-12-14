package org.avanade.demo.pageobject.Careers;

import org.avanade.demo.pageobject.BaseObjectClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CareerSearchResults extends BaseObjectClass {

    public CareerSearchResults(WebDriver driver) {
        super(driver);
        String URL = driver.getCurrentUrl();
        if (!URL.contains("/jobs?page=")) {
            try {
                wait.until(ExpectedConditions.urlContains("/jobs?page="));
                if (!URL.contains("/jobs?page="))
                    throw new IllegalStateException("/jobs?page=");
            } catch (Exception e) {
            }
        }
    }
}
