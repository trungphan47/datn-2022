package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.gson.*;
import com.utility.webDrivers.DriverFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import static com.common.GlobalVariables.*;

public class Utility {
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

    //Add initialization of drivers
    public static String subWindowHandler = null;
    public static ExtentReports report = null;
    public static ExtentHtmlReporter htmlReporter = null;

    //initiate log4j
    public static Log log4j;

    //initiate variable for generating time stamp
    public static String timeStampString = DataFaker.generatetimeStampString("yyyy-MM-dd-HH-mm-ss");

    //Initiate local variable for file path
    public static String reportLocation = OUTPUT_PATH + "report-" + timeStampString + "/";
    public static String reportFilePath = reportLocation + "report-" + timeStampString + ".html";

    public static EmailActions emailActions = new EmailActions();

    public static void log4jConfiguration() {
        try {
            log4j = LogFactory.getLog(new Object().getClass().getName());
        } catch (Exception e) {
            log4j.error("log4jConfiguaration method - ERROR: ", e);
        }
    }

    public static String getTestCaseID() {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            for (StackTraceElement item : stackTraceElements) {
                if (item.getClassName().startsWith("com.modules"))
                    return item.getFileName().split("_")[0].toLowerCase();
            }
            return "noName";
        } catch (Exception e) {
            return "noName";
        }
    }

    public void getTestCaseExecutionCount(ArrayList<String> testCaseList) {
        for (int i = 0; i < testCaseList.size(); i++) {
            if (testCaseList.get(i).contains(": pass")) {
                TOTAL_PASSED++;
            } else if (testCaseList.get(i).contains(": skip")) {
                TOTAL_SKIPPED++;
            } else TOTAL_FAILED++;
        }
        TOTAL_TESTCASES = testCaseList.size();
    }

    public static void setDriver(RemoteWebDriver webDriver){
        driver.set(webDriver);
    }

    public static void initializeDriver(ExtentTest logTest){
        try {
            Utility.setDriver(DriverFactory.createInstance(logTest, BROWSER));
            WebDriverUtils.maximizeWindow();

            Utility.getDriver().manage().deleteAllCookies();
            Utility.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e){
            TOTAL_FAILED++;
            log4j.error("initializeDriver method - ERROR: ", e);
            TestReporter.logException(logTest, "initializeDriver method - ERROR: ", e);
        }
    }

    public static RemoteWebDriver getDriver() {
        return driver.get();
    }

    public static void quit(ExtentTest logTest) {
        try {
            Utility.getDriver().quit();
            TestReporter.logInfo(logTest, "Closed browser");
        } catch (Exception e) {
            log4j.error("Unable to close browser: ", e);
        }
    }

    public static void sleep(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (Exception e) {
            log4j.warn("Exception sleep method = ERROR", e);
        }
    }

    public static String getStackTrace(StackTraceElement[] stackTraceElements) {
        try {
            String stackTrade = "";
            for (StackTraceElement e : stackTraceElements) {
                stackTrade += e.toString() + "</br>";
                //Get stacktrade from com.common.modules level only
                if (e.toString().startsWith("com.common.modules")) {
                    break;
                }
            }
            return stackTrade;
        } catch (Exception e) {
            return "";
        }
    }

    public static Object[][] jsonArrayToObjectArray(JsonArray jsonArray) {

        Object[][] data = new Object[0][1];
        int index = 0;
        Gson gson = new Gson();

        if (jsonArray.size() > 0) {
            data = new Object[jsonArray.size()][1];
            for (Object obj : jsonArray) {
                Hashtable<String, String> hashTable = new Hashtable<String, String>();
                data[index][0] = gson.fromJson((JsonElement) obj, hashTable.getClass());
                index++;
            }
        }
        return data;
    }

    public static Object[][] getData(String testName, String dataFilePath) {

        Object[][] data = new Object[0][1];

        //Read json file data using Gson library
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dataFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonObject jsonObject = JsonParser.parseReader(br).getAsJsonObject();

        //Check for the test name in the json file
        boolean blnTCExist = jsonObject.has(testName);
        if (!blnTCExist) {
            log4j.error(testName + " is not present in the data.json file - " + dataFilePath);
            return data;
        }

        //Get test data for the specific test case
        JsonArray jsonArray = jsonObject.getAsJsonArray(testName);
        data = jsonArrayToObjectArray(jsonArray);
        return data;
    }

    public static String getDepartDate(int dateAmount) {
        LocalDate currentDate = LocalDate.now();
        LocalDate date = currentDate.plusDays(dateAmount);
        DateTimeFormatter dateFormatter = DateTimeFormatter
                .ofPattern("MM/dd/yyyy");
        String temp = date.format(dateFormatter);
        String[] temp1 = temp.split("/");
        int getMonth = Integer.parseInt(temp1[0]);
        int getDay = Integer.parseInt(temp1[1]);
        int getYear = Integer.parseInt(temp1[2]);
        String departDate = getMonth + "/" + getDay + "/" + getYear;
        return departDate;
    }
}
