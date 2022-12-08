package com.modules.myTickets;

import com.common.TestBase;
import com.modules.actions.HighlevelMethods;
import com.pageObjects.HomePage;
import com.pageObjects.myTicket.MyTicketPage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class C023_Open_My_Ticket_tab extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng đến được trang vé của tôi")
    public void TC01(Hashtable<String, String> data) {
        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway và đăng nhập");
        HighlevelMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Điều hướng đến trang My Ticket");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.openMyTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Kiểm tra trang My ticket hiển thị");
        MyTicketPage myTicketPage = PageFactory.initElements(Utility.getDriver(), MyTicketPage.class);
        Assertion.verifyActualAndExpected(logStep, myTicketPage.getLabelTitle(), data.get("Title"));
    }
}
