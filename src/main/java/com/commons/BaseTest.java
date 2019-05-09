package com.commons;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//import org.apache.log4j.BasicConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

public class BaseTest
{
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    protected TestUtils util = new TestUtils();
     
    @BeforeSuite
    public void setUp()
    {
    	//BasicConfigurator.configure();
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Reports/extentTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
         
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setReportName("Extent Test Report");
                
    }
    
    @BeforeMethod
    public void beforeMethod(Method method) {
    	test = extent.createTest((this.getClass().getSimpleName() +"::"+method.getName()), method.getName());
    }
     
    @AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED with", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
     
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
    
    public String getBaseUrl() {
    	
    	Map<String, String> baseUrls = new HashMap<String, String>();
    	baseUrls.put("QA","http://jsonplaceholder.typicode.com");
    	baseUrls.put("Prod","http://jsonplaceholder.typicode.com");
    	
    	return baseUrls.get(System.getProperty("environment"));
    }
}