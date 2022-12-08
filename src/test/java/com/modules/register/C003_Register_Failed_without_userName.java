package com.modules.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.login_register.RegisterPage;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class C003_Register_Failed_without_userName extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng thấy thông báo lỗi khi đăng ký mà không nhập tên tài khoản")
    public void TC01(Hashtable<String, String> data) {

        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.open(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Mở popup Đăng nhập/Đăng ký");
        homePage.clickLoginLabel(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Mở tab Đăng ký");
        homePage.openRegisterTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #4: Nhập thông tin và đăng ký tài khoản");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, data.get("UserName"), data.get("Password"), data.get("ConfirmPassword"), data.get("PID"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #5: Kiểm tra nhắc nhở nhập tên người dùng được hiển thị");
        registerPage.checkUsernameAlert(logStep, data.get("Message1"), data.get("Message2"));
    }
}
