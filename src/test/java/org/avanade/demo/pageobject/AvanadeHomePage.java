package org.avanade.demo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AvanadeHomePage extends BaseObjectClass {

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

}
