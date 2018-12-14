package org.avanade.demo.pageobject.Careers;

import org.avanade.demo.pageobject.BaseObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CareerSearchResults extends BaseObjectClass {

    private By resultsSummary = By.xpath("//div/span/span[contains(text(), 'results')]");
    private By locationsLink = By.linkText("Locations");

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

    public int getResultCount(){
        String resultCount = (getElementText(resultsSummary).replaceAll("\\D+",""));
        return Integer.parseInt(resultCount);
    }

    public LocationsList clickLocationsLink(){
        clickElement(locationsLink);
        return new LocationsList(driver);
    }

    public JobOfferDetails clickJobOffer(String jobTitle){
        By jobOfferLocator = By.xpath("//a[contains(text(), '" + jobTitle + "')]");
        waitForElementClickable(jobOfferLocator);
        clickElement(jobOfferLocator);
        return new JobOfferDetails(driver);
    }
}
