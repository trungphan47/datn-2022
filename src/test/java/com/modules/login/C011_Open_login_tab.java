package com.modules.login;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.login_register.LoginPage;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class C011_Open_login_tab extends TestBase {
    @Test(description = "Người dùng có thể mở được tab Đăng nhập")
    public void TC01() {
        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.open(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Mở popup Đăng nhập");
        homePage.clickLoginLabel(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Kiểm tra trang Đăng nhập được hiển thị");
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.checkLoginPageDisplayed(logStep);
    }
}
