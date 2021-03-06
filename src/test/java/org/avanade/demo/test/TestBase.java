package org.avanade.demo.test;

import org.avanade.demo.helpers.Utils;
import org.avanade.demo.pageobject.AvanadeHomePage;
import org.avanade.demo.pageobject.Careers.RolesAndLocationsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class TestBase {

    private WebDriver driver;
    private static final String startPageUrl = "https://www.avanade.com/en";

    @BeforeClass(alwaysRun = true)
    public void setUpDriver(){
        String browser;
        if(System.getProperty("browser") == "" || System.getProperty("browser") == null){
            browser = "FIREFOX";
        } else browser = System.getProperty("browser").toUpperCase();

        switch (browser) {
            case "FIREFOX" :
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckoDriver.exe");
                driver = new FirefoxDriver();
                break;
            case "CHROME" :
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromeDriver.exe");
                driver = new ChromeDriver();
                break;
            default :
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckoDriver.exe");
                driver = new FirefoxDriver();
                break;
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownTest() {
        getDriver().close();
    }

    public AvanadeHomePage loadHomePage() {
        driver.navigate().to(getStartPageUrl());
        return new AvanadeHomePage(getDriver());
    }

    //Method used to take a screenshot in case of failed test:
    @AfterMethod(alwaysRun=true)
    public void catchExceptions(ITestResult result) throws IOException {
        if(!result.isSuccess()) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            String methodName = result.getName();
            Utils.takeSnapShot(driver, "build/reports/tests/guiTest/TestFailureScreenshots/"+methodName+"_"+formatter.format(calendar.getTime())+".png");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getStartPageUrl() {
        return startPageUrl;
    }

    RolesAndLocationsPage navigateToRolesAndLocationsPage(){
        AvanadeHomePage avanadeHomePage = loadHomePage();
        RolesAndLocationsPage rolesAndLocationsPage = avanadeHomePage
                .moveMouseToCareers()
                .moveMouseToRolesAndLocations()
                .clickRolesAndLocations();
        return rolesAndLocationsPage;
    }

}
