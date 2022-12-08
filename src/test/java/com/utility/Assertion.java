package com.utility;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;

import static com.utility.Utility.log4j;

public class Assertion {

    public static void verifyActualAndExpected(ExtentTest logTest, String actual, String expected) {
        try {
            log4j.info("Actual: " + actual);
            log4j.info("Expected: " + expected);
            if (actual.trim().equalsIgnoreCase(expected.trim()))
                TestReporter.logPass(logTest, "Thực tế: " + actual + "</br>Mong đợi: " + expected + "</br>");
            else
                TestReporter.logFail(logTest, "Thực tế: " + actual + "</br>Mong đợi: " + expected + "</br>");
        } catch (Exception e) {
            log4j.error("verifyActualAndExpected method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyActualAndExpected method - ERROR: ", e);
        }
    }

    public static void verifyActualAndExpected(ExtentTest logTest, boolean actual, boolean expected) {
        try {
            if (actual == expected)
                TestReporter.logPass(logTest, "Thực tế: " + actual + "</br>Mong đợi: " + expected + "</br>");
            else
                TestReporter.logFail(logTest, "Thực tế: " + actual + "</br>Mong đợi: " + expected + "</br>");
        } catch (Exception e) {
            log4j.error("verifyActualAndExpected method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyActualAndExpected method - ERROR: ", e);
        }
    }

    public static void verifyActualAndExpected(ExtentTest logTest, double actual, double expected) {
        try {
            if (actual == expected)
                TestReporter.logPass(logTest, "Thực tế: " + actual + "</br>Mong đợi: " + expected + "</br>");
            else
                TestReporter.logFail(logTest, "Thực tế: " + actual + "</br>Mong đợi: " + expected + "</br>");
        } catch (Exception e) {
            log4j.error("verifyActualAndExpected method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyActualAndExpected method - ERROR: ", e);
        }
    }

    public static void verifyActualAndExpected(ExtentTest logTest, int actual, int expected) {
        try {
            if (actual == expected)
                TestReporter.logPass(logTest, "Thực tế: " + actual + "</br>Mong đợi: " + expected + "</br>");
            else
                TestReporter.logFail(logTest, "Thực tế: " + actual + "</br>Mong đợi: " + expected + "</br>");
        } catch (Exception e) {
            log4j.error("verifyActualAndExpected method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyActualAndExpected method - ERROR: ", e);
        }
    }

    public static void verifyStringDoesNotContainSubString(ExtentTest logTest, String string, String subString) {
        try {
            if (!string.trim().contains(subString.trim()))
                TestReporter.logPass(logTest, "Chuỗi: " + string + " not contains </br>Chuỗi con: " + subString);
            else
                TestReporter.logFail(logTest, "Chuỗi: " + string + " contains </br>Chuỗi con: " + subString);
        } catch (Exception e) {
            log4j.error("verifyActualAndExpected method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyActualAndExpected method - ERROR: ", e);
        }
    }

    public static void verifyStringContainSubString(ExtentTest logTest, String string, String subString) {
        try {
            if (string.trim().contains(subString.trim()))
                TestReporter.logPass(logTest, "Chuỗi: " + string + " contains </br>Chuỗi con: " + subString);
            else
                TestReporter.logFail(logTest, "Chuỗi: " + string + " not contains </br>Chuỗi con: " + subString);
        } catch (Exception e) {
            log4j.error("verifyStringContainSubString method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyStringContainSubString method - ERROR: ", e);
        }
    }

    public static void checkControlExist(ExtentTest logTest, WebElement element, String name) {
        try {
            WebDriverUtils.waitForControl(element);
            if (WebDriverUtils.doesControlExist(element))
                TestReporter.logPass(logTest, name + " có tồn tại");
            else
                TestReporter.logFail(logTest, name + " không tồn tại");
        } catch (Exception e) {
            log4j.error("checkControlExist method - ERROR: ", e);
            TestReporter.logException(logTest, "checkControlExist method - ERROR: ", e);
        }
    }

    public static void checkControlNotExist(ExtentTest logTest, WebElement element, String name) {
        try {
            WebDriverUtils.waitForControl(element);
            if (!WebDriverUtils.doesControlExist(element))
                TestReporter.logPass(logTest, name + " không tồn tại");
            else
                TestReporter.logFail(logTest, name + " có tồn tại");
        } catch (Exception e) {
            log4j.error("checkControlExist method - ERROR: ", e);
            TestReporter.logException(logTest, "checkControlExist method - ERROR: ", e);
        }
    }

    public static void checkControlValue(ExtentTest logTest, WebElement element, String value) {
        try {
            WebDriverUtils.waitForControl(element);

            String actualValue = element.getAttribute("value");
            if (actualValue == null) actualValue = element.getText();

            verifyActualAndExpected(logTest, actualValue, value);
        } catch (Exception e) {
            log4j.error("checkControlValue method - ERROR: ", e);
            TestReporter.logException(logTest, "checkControlValue method - ERROR: ", e);
        }
    }

    public static void verifyPageURL(ExtentTest logTest, String value) {
        try {
            WebDriverUtils.waitForPageLoaded();
            String currentURL = Utility.getDriver().getCurrentUrl();
            verifyActualAndExpected(logTest, currentURL, value);
        } catch (Exception e) {
            log4j.error("verifyPageURL method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyPageURL method - ERROR: ", e);

        }
    }

    public static void verifyPageURLContains(ExtentTest logTest, String value) {
        try {
            WebDriverUtils.waitForPageLoaded();
            String currentURL = Utility.getDriver().getCurrentUrl();
            verifyStringContainSubString(logTest, currentURL, value);
        } catch (Exception e) {
            log4j.error("verifyPageURLContains method - ERROR: ", e);
            TestReporter.logException(logTest, "verifyPageURLContains method - ERROR: ", e);

        }
    }
}
