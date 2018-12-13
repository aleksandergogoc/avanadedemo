package org.avanade.demo.test;

import org.avanade.demo.helpers.Utils;
import org.avanade.demo.pageobject.AvanadeHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public abstract class TestBase {

    private WebDriver driver;
    private String startPageUrl;


    /**
     * Setup the chrome driver before any test is run
     */
    @BeforeClass(alwaysRun = true)
    public static void setUpClass() {
        getDrivers();
    }

    private static void getDrivers() {
        FirefoxDriverManager.getInstance().setup();
    }

     public void setUpDriver(String browser) throws MalformedURLException {
            switch (browser.toUpperCase()) {
                case "FIREFOX" :
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/geckoDriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case "CHROME" :
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromeDriver.exe");
                    driver = new ChromeDriver();
                    break;
                case "IE" :
                    System.setProperty("webdriver.ie.driver", "src/test/resources/ieDriver32.exe");
                    driver = new InternetExplorerDriver();
                    break;
                default :
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/geckoDriver.exe");
                    driver = new FirefoxDriver();
                    break;
            }

    }


    @BeforeClass(alwaysRun = true)
    public void setUp(String browser, String startPageUrl) throws Exception {
        setUpDriver(browser);
        setStartUrl(startPageUrl);
    }


    @AfterClass(alwaysRun = true)
    public void tearDownTest() {
        getDriver().close();
        getDriver().quit();
    }

    public AvanadeHomePage loadHomePage() {
        return new AvanadeHomePage(getDriver());
    }

    //Method used to take a screenshot in case of failed test:
    @AfterMethod(alwaysRun=true)
    public void catchExceptions(ITestResult result) throws IOException {
        if(!result.isSuccess()) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            String methodName = result.getName();
            Utils.takeSnapShot(driver, "build/reports/tests/TestFailureScreenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getStartPageUrl() {
        return startPageUrl;
    }

    public void setStartUrl(String url) {
        startPageUrl = url;
    }



}
