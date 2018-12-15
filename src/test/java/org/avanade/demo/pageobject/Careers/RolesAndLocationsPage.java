package org.avanade.demo.pageobject.Careers;

import org.avanade.demo.pageobject.BaseObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RolesAndLocationsPage extends BaseObjectClass {

    private By locationsDropDown = By.xpath("//span[@id='countries']");
    private By searchButton = By.id("jobsearchclick");

    public RolesAndLocationsPage(WebDriver driver) {
        super(driver);
        String URL = driver.getCurrentUrl();
        if (!URL.contains("/roles-and-locations")) {
            try {
                wait.until(ExpectedConditions.urlContains("/roles-and-locations"));
                if (!URL.contains("/roles-and-locations"))
                    throw new IllegalStateException("This is not the Roles and Locations page!");
            } catch (Exception e) {
            }
        }
    }

    public RolesAndLocationsPage selectLocation(String location){
        By el = By.xpath("//div[@id='list3']//a[contains(text(), '" + location + "')]");

        waitForElementClickable(locationsDropDown);
        clickElement(locationsDropDown);
        clickElement(el);
        return this;
    }

    public CareerSearchResults clickSearchButton(){
        clickElement(searchButton);
        return new CareerSearchResults(driver);
    }
}
