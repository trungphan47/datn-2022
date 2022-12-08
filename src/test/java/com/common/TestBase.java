package com.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.utility.TestReporter;
import com.utility.Utility;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import static com.common.GlobalVariables.*;

public class TestBase extends Utility {
    protected String pathFile = System.getProperty("user.dir");

    public static ExtentTest logSuite;
    public ExtentTest logClass;
    public ExtentTest logMethod;
    public ExtentTest logStep;

    public String testCaseName;
    public String testNameWithStatus;
    public static ArrayList<String> testCaseList = new ArrayList<String>();

    @BeforeSuite
    public synchronized void beforeSuite(ITestContext context){
        log4jConfiguration();
        DOMConfigurator.configure(PROJECT_PATH + "/resources/suites/log4j.xml");

        log4j.info("BeforeSuite - starts");

        try {
            htmlReporter = new ExtentHtmlReporter(reportFilePath);
            htmlReporter.loadXMLConfig(new File(PROJECT_PATH + "/resources/suites/config.xml"));
            report = new ExtentReports();
            report.attachReporter(htmlReporter);
            logSuite = TestReporter.createTestForExtentReport(report, "Initial Setup");
        } catch (Exception e) {
            log4j.error("ERROR while initializing Extend report: " + e.getStackTrace());
            TestReporter.logException(logSuite, "ERROR while initializing Extend report", e);
        }

        //Create report folder
        TestReporter.logInfo(logSuite, "Thư mục chứa báo cáo: " + reportLocation);
        File folder = new File(reportLocation);
        folder.mkdirs();

        TestReporter.logInfo(logSuite, "Trình duyệt: " + BROWSER);
        TestReporter.logInfo(logSuite, "Số luồng thực thi: " + THREAD_COUNT);
    }

    @BeforeClass
    public synchronized void beforeClass() {
        log4j.info("BeforeClass method - Starts");
        testCaseName = this.getClass().getSimpleName();
    }

    @BeforeMethod
    public synchronized void beforeMethod(Object[] data){
        log4j.info("BeforeMethod - Starts");
        logStep = null;
        TOTAL_EXECUTED++;

        if(data != null && data.length > 0){
            //Get test data for test case
            Hashtable<String, String> dataTest = (Hashtable<String, String>) data[0];
            testNameWithStatus = testCaseName + ": " + dataTest.get("Data");
        }
        else {
            testNameWithStatus = testCaseName;
        }

        //Initiate logClass
        logClass = TestReporter.createTestForExtentReport(report, testNameWithStatus);

        //Initiate logMethod
        logMethod = TestReporter.createNodeForExtentReport(logClass, testCaseName);

        initializeDriver(logMethod);

        log4j.info("BeforeMethod - Ends");
    }

    @AfterMethod
    public synchronized void afterMethod(){
        log4j.info("AfterMethod - Starts");

        //update test execution status to the testCaseList
        testCaseList.add(testNameWithStatus + ": " + logMethod.getStatus());

        quit(logMethod);
        logMethod = null;

        log4j.info("AfterMethod - Ends");
    }

    @DataProvider
    public Object[][] getDataForTest() {
        String DataFilePath = JSON_FILE_PATH + this.getClass().getPackage().getName()
                .replace(".", "/") + "/data.json";
        Object[][] data = getData(testCaseName, DataFilePath);
        if (data.length == 0) {
            logClass = TestReporter.createTestForExtentReport(report, testCaseName);
            logClass.fail("Data: "+ testCaseName + " không tồn tại trong file data.json");
            TOTAL_FAILED++;
        }
        return data;
    }

    @DataProvider
    public Object[][] getDataForSeparatingTest(ITestNGMethod context) {
        String testData = testCaseName + "_" + context.getMethodName();
        String DataFilePath = JSON_FILE_PATH + this.getClass().getPackage().getName().replace(".", "/") + "/data.json";
        Object[][] data = getData(testData, DataFilePath);
        if (data.length == 0) {
            logClass = TestReporter.createTestForExtentReport(report, testCaseName);
            logClass.fail("Data: " + testData + " không tồn tại trong file data.json");
            TOTAL_FAILED++;
        }
        return data;
    }

    @AfterClass
    public void afterClass() {
        log4j.info("AfterClass - Starts");

        report.flush();
        logClass = null;

        log4j.info("AfterClass - Ends");
    }

    @AfterSuite
    public void afterSuite() {
        getTestCaseExecutionCount(testCaseList);
    }
}
