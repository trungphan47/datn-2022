package com.modules.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.login_register.LoginPage;
import com.pageObjects.login_register.RegisterPage;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class C001_Open_register_tab extends TestBase {
    @Test(description = "Người dùng có thể mở được tab Đăng ký")
    public void TC01() {
        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.open(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Mở tab Đăng ký");
        homePage.openRegisterTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Kiểm tra form Đăng ký được hiển thị");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.checkRegisterPageDisplayed(logStep);
    }
}
