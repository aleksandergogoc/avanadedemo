package org.avanade.demo.test;

import org.avanade.demo.helpers.RetryFailed;
import org.avanade.demo.pageobject.AvanadeHomePage;
import org.testng.annotations.Test;

public class CareerTest extends TestBase {

    @Test(enabled = true, retryAnalyzer = RetryFailed.class, groups = {"default"})
    public void careerTest() throws Exception {
        AvanadeHomePage avanadeHomePage = loadHomePage();
    }

}
