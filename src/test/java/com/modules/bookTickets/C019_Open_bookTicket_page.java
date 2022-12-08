package com.modules.bookTickets;

import com.common.TestBase;
import com.modules.actions.HighlevelMethods;
import com.pageObjects.HomePage;
import com.pageObjects.booktickets.BookTicketPage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class C019_Open_bookTicket_page extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng có thể di chuyển đến trang đặt vé sau khi đăng nhâp")
    public void TC01(Hashtable<String, String> data) {

        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway và đăng nhập");
        HighlevelMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Điều hướng đến trang Book tickets");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickBookTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Kiểm tra trang Book ticket hiển thị");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getLabelTitle(), data.get("Title"));

    }
}
