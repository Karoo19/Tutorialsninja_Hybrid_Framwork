package com.tutorialsninja.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;

public class MyListeners implements ITestListener {
	
	private static final ExtentTest extentTest = null;
	ExtentReports ExtentReport;
	
	
	@Override
	public void onStart(ITestContext context) {
		
	 ExtentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		String testName = result.getName();
	    ExtentTest ExtentTest = ExtentReport.createTest(testName);
		ExtentTest.log(Status.INFO, testName+" started executed");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		
	
		
		extentTest.log(Status.PASS, testName+"got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"got failed");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"got skipped");
		System.out.println(result.getThrowable());
	}


	@Override
	public void onFinish(ITestContext context) {
		System.out.println("finished executing project tests");
	}

}
