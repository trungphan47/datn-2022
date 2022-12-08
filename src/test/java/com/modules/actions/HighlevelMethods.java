package com.modules.actions;

import com.aventstack.extentreports.ExtentTest;
import com.pageObjects.HomePage;
import com.pageObjects.login_register.LoginPage;
import com.pageObjects.login_register.RegisterPage;
import com.utility.DataFaker;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.support.PageFactory;

public class HighlevelMethods {

    public static void login(ExtentTest logStep) {
        String userName = DataFaker.generateRandomEmail();
        String password = DataFaker.generateCharRandom(10);
        String confirmPassword = password;
        String PID = DataFaker.generateCharRandom(12);

        TestReporter.logInfo(logStep, "Điều hướng đến trang web Railwway");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.open(logStep);

        TestReporter.logInfo(logStep, "Điều hướng đến trang đăng kí và đăng kí tài khoản");
        homePage.openRegisterTab(logStep);
        RegisterPage registerPage = PageFactory.initElements(Utility.getDriver(), RegisterPage.class);
        registerPage.register(logStep, userName, password, confirmPassword, PID);

        TestReporter.logInfo(logStep, "Điều hướng đến trang đăng nhập và đăng nhập");
        homePage.clickLoginLabel(logStep);
        LoginPage loginPage = PageFactory.initElements(Utility.getDriver(), LoginPage.class);
        loginPage.login(logStep, userName, password);
    }

}
