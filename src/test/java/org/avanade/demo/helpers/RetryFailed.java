package org.avanade.demo.helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailed implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 1;
    private boolean lastRetry = false;

    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        lastRetry = true;
        return false;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public boolean isLastRetry(){
        return lastRetry;
    }
}