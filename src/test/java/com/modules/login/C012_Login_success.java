package com.modules.login;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.login_register.LoginPage;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class C012_Login_success extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng đăng nhập thành công với các thông tin hợp lệ")
    public void TC01(Hashtable<String, String> data) {
        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.open(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Di chuyển đến trang Đăng nhập");
        homePage.clickLoginLabel(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Đăng nhập");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.login(logStep, data.get("Username"), data.get("Password"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Xác minh người dùng đã đăng nhập thành công");
        homePage.checkSuccessMsg(logStep, "Welcome to Safe Railway");
    }
}
