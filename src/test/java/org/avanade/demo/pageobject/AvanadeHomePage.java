package org.avanade.demo.pageobject;

import org.avanade.demo.pageobject.Careers.RolesAndLocationsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AvanadeHomePage extends BaseObjectClass {

    private By careersMenuLink = By.xpath("//div[@id='main-navbar']//a[contains(text(), 'Careers')]");
    private By rolesLocationsMenuLink = By.xpath("//div[@id='main-navbar']//a[contains(text(), 'Roles and Locations')]");

    public AvanadeHomePage (WebDriver driver){
        super(driver);
        String URL = driver.getCurrentUrl();
        if (!URL.contains("avanade.com/en")) {
            try{
                wait.until(ExpectedConditions.urlContains("avanade.com/en"));
                if(!URL.contains("avanade.com/en")) throw new IllegalStateException("This is not the Avanade home page!");
            } catch (Exception e){}

        }
    }

    public AvanadeHomePage moveMouseToCareers(){
        waitForElementVisibility(careersMenuLink);
        moveToElement(careersMenuLink);
        return this;
    }

    public AvanadeHomePage moveMouseToRolesAndLocations(){
        waitForElementVisibility(rolesLocationsMenuLink);
        moveToElement(rolesLocationsMenuLink);
        return this;
    }

    public RolesAndLocationsPage clickRolesAndLocations(){
        clickElement(rolesLocationsMenuLink);
        return new RolesAndLocationsPage(driver);
    }
}
