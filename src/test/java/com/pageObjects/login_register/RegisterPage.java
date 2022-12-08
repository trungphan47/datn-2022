package com.pageObjects.login_register;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Utility {

    @FindBy(xpath = "//h1[normalize-space()='Create account']")
    private WebElement title;

    @FindBy(id = "email")
    private WebElement username_input;

    @FindBy(id = "password")
    private WebElement password_input;

    @FindBy(id = "confirmPassword")
    private WebElement confirm_password_input;

    @FindBy(id = "pid")
    private WebElement pid_input;

    @FindBy(xpath = "//input[@title='Register']")
    private WebElement register_button;

    @FindBy(xpath = "//li[@class='email']//label[@class='validation-error']")
    private WebElement username_error_msg;

    @FindBy(xpath = "//li[@class='password']//label[@class='validation-error']")
    private WebElement password_error_msg;

    @FindBy(xpath = "//li[@class='confirm-password']//label[@class='validation-error']")
    private WebElement confirm_error_msg;

    @FindBy(xpath = "//li[@class='pid-number']//label[@class='validation-error']")
    private WebElement pid_error_msg;

    @FindBy(xpath = "//div[@id='content']//p")
    private WebElement registerSuccess_label;

    @FindBy(xpath = "//p[@class='message error']")
    private WebElement error_msg;

    @FindBy(xpath = "//a[normalize-space()='Web hosting by Somee.com']")
    private WebElement hosting;
    //methods

    public void checkRegisterPageDisplayed(ExtentTest logTest) {
        WebDriverUtils.waitForPageLoaded();
        Assertion.checkControlExist(logTest, title, "Tiêu đề");
        Assertion.checkControlExist(logTest, username_input, "Ô nhập tên người dùng");
        Assertion.checkControlExist(logTest, password_input, "Ô nhập mật khẩu");
        Assertion.checkControlExist(logTest, confirm_password_input, "Ô xác nhận lại mật khẩu");
        Assertion.checkControlExist(logTest, pid_input, "Ô nhập passport");
        Assertion.checkControlExist(logTest, register_button, "Nút đăng ký");
    }

    public void checkUsernameAlert(ExtentTest logTest, String msg1, String msg2) {
        log4j.info("checkUsernameAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Starts");

        WebDriverUtils.waitForControl(username_error_msg);
        String actual1 = username_error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual1, msg1);

        WebDriverUtils.waitForControl(error_msg);
        String actual2 = error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual2, msg2);

        log4j.info("checkUsernameAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Ends");
    }

    public void checkPasswordAlert(ExtentTest logTest, String msg1, String msg2) {
        log4j.info("checkPasswordAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo mật khẩu - Starts");

        WebDriverUtils.waitForControl(password_error_msg);
        String actual1 = password_error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual1, msg1);

        WebDriverUtils.waitForControl(error_msg);
        String actual2 = error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual2, msg2);

        log4j.info("checkPasswordAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo mật khẩu - Ends");
    }


    public void checkConfirmPasswordAlert(ExtentTest logTest, String msg, String msg2) {
        log4j.info("checkConfirmPasswordAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo xác nhận mật khẩu - Starts");

        WebDriverUtils.waitForControl(confirm_error_msg);
        String actual = confirm_error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual, msg);

        WebDriverUtils.waitForControl(error_msg);
        String actual2 = error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual2, msg2);

        log4j.info("checkConfirmPasswordAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo xác nhận mật khẩu - Ends");
    }

    public void checkPIDAlert(ExtentTest logTest, String msg) {
        log4j.info("checkPIDAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo nhập email - Starts");

        WebDriverUtils.waitForControl(pid_error_msg);
        String actual = pid_error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual, msg);

        log4j.info("checkPIDAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo nhập email - Ends");
    }

    public void register(ExtentTest logTest, String userName, String password, String confirmPassword, String pid) {
        log4j.info("register method - Starts");
        TestReporter.logInfo(logTest, "Đăng ký - Starts");

        TestReporter.logInfo(logTest, "Nhập tên tài khoản: " + userName);
        WebDriverUtils.waitForControl(username_input);
        username_input.sendKeys(userName);

        TestReporter.logInfo(logTest, "Nhập mật khẩu: " + password);
        WebDriverUtils.waitForControl(password_input);
        password_input.sendKeys(password);

        TestReporter.logInfo(logTest, "Nhập xác nhận mật khẩu: " + confirmPassword);
        WebDriverUtils.waitForControl(confirm_password_input);
        confirm_password_input.sendKeys(confirmPassword);

        TestReporter.logInfo(logTest, "Nhập passport: " + pid);
        WebDriverUtils.waitForControl(pid_input);
        pid_input.sendKeys(pid);

        TestReporter.logInfo(logTest, "Bấm nút Đăng ký");
        WebDriverUtils.scrollIntoView(hosting);
        WebDriverUtils.waitForControl(register_button);
        register_button.click();

        log4j.info("register method- Ends");
        TestReporter.logInfo(logTest, "Đăng ký - Ends");
    }

    public String getRegisterSuccessLabel() {
        WebDriverUtils.waitForControl(registerSuccess_label);
        return registerSuccess_label.getText();
    }

    public String getErrorMessageLabel() {
        WebDriverUtils.waitForControl(error_msg);
        return error_msg.getText();
    }

    public void checkWarningMsgForEmail(ExtentTest logTest, String msg1, String msg2) {
        log4j.info("checkUsernameAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Starts");

        WebDriverUtils.waitForControl(username_error_msg);
        String actual1 = username_error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual1, msg1);

        Assertion.verifyActualAndExpected(logTest, getErrorMessageLabel(), msg2);

        log4j.info("checkUsernameAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Ends");
    }

    public void checkWarningMsgForPassword(ExtentTest logTest, String msg1, String msg2) {
        log4j.info("checkUsernameAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Starts");

        WebDriverUtils.waitForControl(password_error_msg);
        String actual1 = password_error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual1, msg1);

        Assertion.verifyActualAndExpected(logTest, getErrorMessageLabel(), msg2);

        log4j.info("checkUsernameAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Ends");
    }

    public void checkWarningMsgForPID(ExtentTest logTest, String msg1, String msg2) {
        log4j.info("checkUsernameAlert method - Starts");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Starts");

        WebDriverUtils.waitForControl(pid_error_msg);
        String actual1 = pid_error_msg.getText();
        Assertion.verifyActualAndExpected(logTest, actual1, msg1);

        Assertion.verifyActualAndExpected(logTest, getErrorMessageLabel(), msg2);

        log4j.info("checkUsernameAlert method- Ends");
        TestReporter.logInfo(logTest, "Kiểm tra cảnh báo tên người dùng - Ends");
    }

}
