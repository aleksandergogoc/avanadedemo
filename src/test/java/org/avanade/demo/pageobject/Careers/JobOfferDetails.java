package org.avanade.demo.pageobject.Careers;

import org.avanade.demo.pageobject.BaseObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class JobOfferDetails extends BaseObjectClass{

    public JobOfferDetails(WebDriver driver) {
        super(driver);
        String URL = driver.getCurrentUrl();
        if (!URL.contains("/jobs")) {
            try {
                wait.until(ExpectedConditions.urlContains("/jobs"));
                if (!URL.contains("/jobs"))
                    throw new IllegalStateException("/jobs");
            } catch (Exception e) {
            }
        }
    }

    public boolean verifyIfQualificationIsDisplayed(String qualification){
        try {
            return driver.findElement(By.xpath("//h4[contains(text(), 'Qualifications')]/following-sibling::ul/li[text() = '" + qualification + "']")).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
