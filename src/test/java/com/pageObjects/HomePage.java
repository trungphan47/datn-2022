package com.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.common.Constant;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Utility {

    @FindBy(xpath = "//a[@href='/Account/Login.cshtml']//span")
    private WebElement login_tab;

    @FindBy(xpath = "//h1[@align='center']")
    private WebElement success_msg;

    @FindBy(xpath = "//p[@class='message error LoginForm']")
    private WebElement errorUsernameOrPasswordInvalid_label;

    @FindBy(xpath = "//div[@class='menu']//a[.='HOME']")
    private WebElement home_tab;

    @FindBy(xpath = "//a[@href='/Page/BookTicketPage.cshtml']//span")
    private WebElement bookTicket_tab;

    @FindBy(xpath = "//a[@href='/Account/Register.cshtml']//span")
    private WebElement register_tab;

    @FindBy(xpath = "//a[@href='/Page/ManageTicket.cshtml']//span")
    private WebElement myTicket_tab;

    //methods
    public void open(ExtentTest logTest) {
        try {
            log4j.info("Open home page - Starts");
            TestReporter.logInfo(logTest, "Mở trình duyệt và điều hướng đến trang chủ");

            Utility.getDriver().get(Constant.RAILWAY_URL);

            log4j.info("Open home page - Ends");
        } catch (Exception e) {
            log4j.info("Open home page - ERROR");
            TestReporter.logException(logTest, "Mở trình duyệt và điều hướng đến trang chủ - ERROR", e);
        }
    }

    public void clickLoginLabel(ExtentTest logStep) {
        try {
            log4j.info("Click login label - Starts");
            TestReporter.logInfo(logStep, "Click vào nhãn Đăng nhập...");

            WebDriverUtils.waitForControlBeClickable(login_tab);
            login_tab.click();

            log4j.info("Click login label - Ends");
        } catch (Exception e) {
            log4j.error("ClickLoginLabel - ERROR: ", e);
            TestReporter.logException(logStep, "Click vào nhãn Đăng nhập - ERROR", e);
        }
    }


    public void openRegisterTab(ExtentTest logTest) {

        log4j.info("openRegisterTab method - Starts");
        TestReporter.logInfo(logTest, "Mở tab đăng ký - Starts");

        WebDriverUtils.waitForControl(register_tab);
        register_tab.click();

        log4j.info("openRegisterTab method- Ends");
        TestReporter.logInfo(logTest, "Mở tab đăng ký - Ends");
    }

    public void clickBookTicketTab(ExtentTest logStep) {
        try {
            log4j.info("clickBookTicketTab method - Starts");
            TestReporter.logInfo(logStep, "Click vào tab MUA VÉ ...");

            WebDriverUtils.waitForControlBeClickable(bookTicket_tab);
            bookTicket_tab.click();

            log4j.info("clickBookTicketTab method - Ends");
        } catch (Exception e) {
            log4j.error("clickBookTicketTab method - ERROR: ", e);
            TestReporter.logException(logStep, "Click vào tab MUA VÉ - ERROR", e);
        }
    }

    public void openMyTicketTab(ExtentTest logTest) {

        log4j.info("openMyTicketTab method - Starts");
        TestReporter.logInfo(logTest, "Mở tab vé của tôi - Starts");

        WebDriverUtils.waitForControl(myTicket_tab);
        myTicket_tab.click();

        log4j.info("openMyTicketTab method- Ends");
        TestReporter.logInfo(logTest, "Mở tab vé của tôi - Ends");
    }

    public String getSuccessMsg(ExtentTest logStep) {
        try {
            log4j.info("getSuccessMsg - Starts");
            WebDriverUtils.waitForControl(success_msg);
            String msg = success_msg.getText();
            log4j.info("getSuccessMsg - Ends");
            return msg;
        } catch (Exception e) {
            log4j.error("getSuccessMsg - ERROR: ", e);
            TestReporter.logException(logStep, "getSuccessMsg - ERROR", e);
            return "";
        }
    }

    public String getWarningMsg(ExtentTest logStep) {
        try {
            log4j.info("getWarningMsg - Starts");

            WebDriverUtils.waitForPageLoaded();
            WebDriverUtils.waitForControl(errorUsernameOrPasswordInvalid_label);
            String msg = errorUsernameOrPasswordInvalid_label.getText();

            log4j.info("getWarningMsg - Ends");
            return msg;
        } catch (Exception e) {
            log4j.error("getWarningMsg - ERROR: ", e);
            TestReporter.logException(logStep, "getWarningMsg - ERROR", e);
            return "";
        }
    }

    public void checkSuccessMsg(ExtentTest logStep, String ExpectedMsg) {
        try {
            log4j.info("checkSuccessMsg - Starts");
            TestReporter.logInfo(logStep, "Kiểm tra thông báo thành công - Starts");

            WebDriverUtils.waitForControl(success_msg);
            String actualMsg = getSuccessMsg(logStep);
            Assertion.verifyActualAndExpected(logStep, actualMsg, ExpectedMsg);

            log4j.info("checkSuccessMsg - Ends");
            TestReporter.logInfo(logStep, "Kiểm tra thông báo thành công - Ends");
        } catch (Exception e) {
            log4j.error("checkSuccessMsg - ERROR: ", e);
            TestReporter.logException(logStep, "Kiểm tra thông báo thành công - ERROR", e);
        }
    }

    public void checkWarningMsg(ExtentTest logStep, String ExpectedMsg) {
        try {
            log4j.info("checkWarningMsg - Starts");
            TestReporter.logInfo(logStep, "Kiểm tra cảnh báo - Starts");

            WebDriverUtils.waitForControl(errorUsernameOrPasswordInvalid_label);
            String actualMsg = getWarningMsg(logStep);
            Assertion.verifyActualAndExpected(logStep, actualMsg, ExpectedMsg);

            log4j.info("checkWarningMsg - Ends");
            TestReporter.logInfo(logStep, "Kiểm tra cảnh báo - Ends");
        } catch (Exception e) {
            log4j.error("checkWarningMsg - ERROR: ", e);
            TestReporter.logException(logStep, "Kiểm tra cảnh báo - ERROR", e);
        }
    }

    public void checkUsernameLabel(ExtentTest logStep, String name) {
        try {
            log4j.info("checkUsernameLabel method - Starts");
            TestReporter.logInfo(logStep, "Kiểm tra tên người dùng - Starts");

            WebElement welcomeLabel = Utility.getDriver().findElement(By.xpath("//div[@class='logout']//p[contains(. , '" + name + "')]"));
            Assertion.checkControlExist(logStep, welcomeLabel, "Nhãn tên người dùng");
            Assertion.checkControlValue(logStep, welcomeLabel, name);

            log4j.info("checkUsernameLabel method - Ends");
            TestReporter.logInfo(logStep, "Kiểm tra tên người dùng - Ends");

        } catch (Exception e) {
            log4j.error("checkUsernameLabel method - ERROR: ", e);
            TestReporter.logException(logStep, "Kiểm tra tên người dùng - ERROR", e);
        }
    }


    public void checkAlertMessage(ExtentTest logStep, String message) {
        try {
            log4j.info("checkAlertMessage method - Starts");
            TestReporter.logInfo(logStep, "Kiểm tra thông báo ...");

            Assertion.verifyActualAndExpected(logStep, Utility.getDriver().switchTo().alert().getText(), message);

            log4j.info("checkAlertMessage method - Ends");
        } catch (NoAlertPresentException e) {
            log4j.error("checkAlertPresent - ERROR: ", e);
            TestReporter.logException(logStep, "Kiểm tra alert - ERROR", e);
        }
    }
}
