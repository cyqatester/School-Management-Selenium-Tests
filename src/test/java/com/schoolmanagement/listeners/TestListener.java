package com.schoolmanagement.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.print("STARTING: " + result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(" => PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.print(" => FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.print(" => SKIPPED");
    }
}
