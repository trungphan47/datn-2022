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

public class C022_User_can_book_ticket_multiple_time extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng có thể đặt mua vé nhiều lần")
    public void TC01(Hashtable<String, String> data) {

        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway và đăng nhập");
        HighlevelMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Điều hướng đến trang Book tickets");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickBookTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Đặt vé");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        String departDate = Utility.getDepartDate(10);
        bookTicketPage.bookTicketMultipleTimes(logStep, departDate, data.get("DepartStation"), data.get("ArriveStation"), data.get("SeatType"), data.get("TicketAmount"),Integer.parseInt(data.get("Times")));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #4: Xác minh rằng đặt vé thành công");
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getBookedTicketSuccessfullyTitle(), data.get("BookTicketSuccessTitle"));

    }
}
