package org.avanade.demo.test;

import org.avanade.demo.helpers.RetryFailed;
import org.avanade.demo.pageobject.AvanadeHomePage;
import org.avanade.demo.pageobject.Careers.CareerSearchResults;
import org.avanade.demo.pageobject.Careers.JobOfferDetails;
import org.avanade.demo.pageobject.Careers.LocationsList;
import org.avanade.demo.pageobject.Careers.RolesAndLocationsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CareerTest extends TestBase {

    @Test(enabled = true, retryAnalyzer = RetryFailed.class, groups = {"default"})
    public void testMoreThan10ResultsForCanada() {
        String country = "Canada";

        RolesAndLocationsPage rolesAndLocationsPage = navigateToRolesAndLocationsPage();
        CareerSearchResults careerSearchResults =
                rolesAndLocationsPage
                        .selectLocation(country)
                        .clickSearchButton();
        assertTrue(careerSearchResults.getResultCount() > 10, "Search result number for Canada should be more than 10!");
    }

    @Test(enabled = true, retryAnalyzer = RetryFailed.class, groups = {"default"})
    public void testMoreThan1ResultsForDenmark() {
        String country = "Denmark";

        RolesAndLocationsPage rolesAndLocationsPage = navigateToRolesAndLocationsPage();
        CareerSearchResults careerSearchResults =
                rolesAndLocationsPage
                        .selectLocation(country)
                        .clickSearchButton();
        assertTrue(careerSearchResults.getResultCount() > 1, "Search result number for Denmark should be more than 1!");
    }

    @Test(enabled = true, retryAnalyzer = RetryFailed.class, groups = {"default"})
    public void testQualificationForJobOfferRaleigh() {
        String country = "United States";
        String city = "Raleigh";
        String job = "QA Test";
        String expectedQualification = "Experience with Agile/Scrum methodology";

        RolesAndLocationsPage rolesAndLocationsPage = navigateToRolesAndLocationsPage();
        CareerSearchResults careerSearchResults =
                rolesAndLocationsPage
                        .selectLocation(country)
                        .clickSearchButton();
        LocationsList locationsList = careerSearchResults.clickLocationsLink();
        careerSearchResults = locationsList.clickLocation(city);
        JobOfferDetails jobOfferDetails = careerSearchResults.clickJobOffer(job);
        assertTrue(jobOfferDetails.verifyIfQualificationIsDisplayed(expectedQualification), "Expected qualification is not displayed in job offer!");
    }

}
