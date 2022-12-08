package com.modules.register;

import com.common.TestBase;
import com.pageObjects.HomePage;
import com.pageObjects.login_register.RegisterPage;
import com.utility.DataFaker;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class C005_Register_Failed_without_confirm_password extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng thấy thông báo lỗi khi đăng ký mà không xác nhận lại mật khẩu")
    public void TC01(Hashtable<String, String> data) {

        String username = DataFaker.generateRandomEmail();

        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.open(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Mở tab Đăng ký");
        homePage.openRegisterTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Nhập thông tin và đăng ký tài khoản");
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, username, data.get("Password"), data.get("ConfirmPassword"), data.get("PID"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #4: Kiểm tra nhắc nhở xác nhận lại mật khẩu");
        registerPage.checkConfirmPasswordAlert(logStep, data.get("Message1"), data.get("Message2"));
    }
}
