package com.modules.login;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.login_register.LoginPage;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class C014_Login_fail_without_password extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng thấy thông báo lỗi khi đăng nhập mà không nhập mật khẩu")
    public void TC01(Hashtable<String, String> data) {
        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.open(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Mở popup Đăng nhập");
        homePage.clickLoginLabel(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Đăng nhập mà không nhập mật khẩu");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.login(logStep, data.get("Username"), data.get("Password"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #4: Kiểm tra nhắc nhở nhập mật khẩu được hiển thị");
        loginPage.checkPasswordAlert(logStep, data.get("Message1"), data.get("Message2"));
    }
}
