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

public class C021_User_can_not_book_11th_ticket_onwards extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng không thể dặt hơn 10 vé")
    public void TC01(Hashtable<String, String> data) {

        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway và đăng nhập");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        HighlevelMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Click vào tab MUA VÉ");
        homePage.clickBookTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Đặt 10 vé");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        String departDate = Utility.getDepartDate(10);
        bookTicketPage.bookTicket(logStep, departDate, data.get("DepartStation"), data.get("ArriveStation"), data.get("SeatType"), data.get("TicketAmount1"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #4: Xác minh rằng đặt vé thành công");
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getBookedTicketSuccessfullyTitle(), data.get("BookTicketSuccessTitle"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #5: Click vào tab MUA VÉ lần nữa");
        homePage.clickBookTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #6: Đặt thêm 1 vé");
        bookTicketPage.bookTicket(logStep, departDate, data.get("DepartStation"), data.get("ArriveStation"), data.get("SeatType"), data.get("TicketAmount2"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #4: Xác minh rằng đặt không thành công");
        Assertion.verifyActualAndExpected(logStep, bookTicketPage.getErrorMessage(data.get("TicketAmount1"), data.get("TicketsCanBePurchased")), data.get("ErrorMessage"));


    }
}
