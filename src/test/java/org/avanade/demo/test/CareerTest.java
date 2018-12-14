package org.avanade.demo.test;

import org.avanade.demo.helpers.RetryFailed;
import org.avanade.demo.pageobject.AvanadeHomePage;
import org.avanade.demo.pageobject.Careers.CareerSearchResults;
import org.avanade.demo.pageobject.Careers.RolesAndLocationsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CareerTest extends TestBase {

    @Test(enabled = true, retryAnalyzer = RetryFailed.class, groups = {"default"})
    public void testMoreThan10ResultsForCanada() {
        String location = "Canada";

        RolesAndLocationsPage rolesAndLocationsPage = navigateToRolesAndLocationsPage();
        CareerSearchResults careerSearchResults =
                rolesAndLocationsPage
                        .selectLocation(location)
                        .clickSearchButton();
        
    }

}
