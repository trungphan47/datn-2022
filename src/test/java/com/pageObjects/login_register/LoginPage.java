package com.pageObjects.login_register;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Utility {

    @FindBy(id = "username")
    private WebElement username_input;

    @FindBy(id = "password")
    private WebElement password_input;

    @FindBy(xpath = "//input[@title='Login']")
    private WebElement login_button;

    @FindBy(xpath = "//p[@class='message error LoginForm']")
    private WebElement loginErrorMsg_label;

    @FindBy(xpath = "//li[@class='username']//label[2]")
    private WebElement errorUserNameBlanks_label;

    @FindBy(xpath = "//label[contains(text(),'You must specify a password.')]")
    private WebElement errorPasswordBlanks_label;

    @FindBy(xpath = "//center//a")
    private WebElement webHosting_label;

    @FindBy(xpath = "//p[@class='message error LoginForm']")
    private WebElement errorMessage;

    @FindBy(xpath = "//li[@class='username']//label[@class='validation-error']")
    private WebElement username_alert;

    @FindBy(xpath = "//li[@class='password']//label[@class='validation-error']")
    private WebElement password_alert;

    //methods
    public void checkLoginPageDisplayed(ExtentTest logTest) {
        WebDriverUtils.waitForPageLoaded();
        Assertion.checkControlExist(logTest, username_input, "Ô nhập tên người dùng");
        Assertion.checkControlExist(logTest, password_input, "Ô nhập mật khẩu");
        Assertion.checkControlExist(logTest, login_button, "Nút đăng nhập");
    }

    public void checkUsernameAlert(ExtentTest logTest, String msg1,String msg2) {
        log4j.info("checkUsernameAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Starts");

        WebDriverUtils.waitForControl(errorMessage);
        String actual1 = errorMessage.getText();
        Assertion.verifyActualAndExpected(logTest, actual1, msg1);

        WebDriverUtils.waitForControl(username_alert);
        String actual2 = username_alert.getText();
        Assertion.verifyActualAndExpected(logTest, actual2, msg2);

        log4j.info("checkUsernameAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Ends");
    }

    public void checkPasswordAlert(ExtentTest logTest, String msg1,String msg2) {
        log4j.info("checkPasswordAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo mật khẩu - Starts");

        WebDriverUtils.waitForControl(errorMessage);
        String actual1 = errorMessage.getText();
        Assertion.verifyActualAndExpected(logTest, actual1, msg1);

        WebDriverUtils.waitForControl(password_alert);
        String actual2 = password_alert.getText();
        Assertion.verifyActualAndExpected(logTest, actual2, msg2);

        log4j.info("checkPasswordAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo mật khẩu - Ends");
    }

    public void login(ExtentTest logTest, String userName, String password) {
        log4j.info("Login method - Starts");
        TestReporter.logInfo(logTest, "Đăng nhập - Starts");

        TestReporter.logInfo(logTest, "Nhập tên tài khoản: " + userName);
        WebDriverUtils.waitForControl(username_input);
        username_input.sendKeys(userName);

        TestReporter.logInfo(logTest, "Nhập mật khẩu: " + password);
        WebDriverUtils.waitForControl(password_input);
        password_input.sendKeys(password);

        TestReporter.logInfo(logTest, "Nhấn nút đăng nhập");
        WebDriverUtils.scrollIntoView(webHosting_label);
        WebDriverUtils.waitForControl(login_button);
        login_button.click();

        log4j.info("Login method- Ends");
        TestReporter.logInfo(logTest, "Đăng nhập - Ends");
    }

    public void loginNotRemember(ExtentTest logTest, String userName, String password) {
        log4j.info("Login method - Starts");
        TestReporter.logInfo(logTest, "Đăng nhập - Starts");

        TestReporter.logInfo(logTest, "Nhập tên tài khoản: " + userName);
        WebDriverUtils.waitForControl(username_input);
        username_input.sendKeys(userName);

        TestReporter.logInfo(logTest, "Nhập mật khẩu: " + password);
        WebDriverUtils.waitForControl(password_input);
        password_input.sendKeys(password);

        TestReporter.logInfo(logTest, "Nhấn nút đăng nhập");
        WebDriverUtils.waitForControl(login_button);
        login_button.click();

        log4j.info("Login method- Ends");
        TestReporter.logInfo(logTest, "Đăng nhập - Ends");
    }


}
