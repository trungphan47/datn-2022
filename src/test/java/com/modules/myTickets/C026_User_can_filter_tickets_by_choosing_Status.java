package com.modules.myTickets;

import com.common.TestBase;
import com.modules.actions.HighlevelMethods;
import com.pageObjects.HomePage;
import com.pageObjects.booktickets.BookTicketPage;
import com.pageObjects.myTicket.MyTicketPage;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class C026_User_can_filter_tickets_by_choosing_Status extends TestBase {
    @Test(dataProvider = "getDataForTest", description = "Người dùng có thể lọc vé đã mua theo trang thái")
    public void TC01(Hashtable<String, String> data) {

        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway và đăng nhập");
        HighlevelMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Điều hướng đến trang Book tickets");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickBookTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Đặt vé");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        String departDate = Utility.getDepartDate(10);
        bookTicketPage.bookTicketMultipleTimes(logStep, departDate, data.get("DepartStation"), data.get("ArriveStation"), data.get("SeatType"), data.get("TicketAmount"), Integer.parseInt(data.get("Times")));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #4: Điều hướng đến trang My Ticket");
        homePage.openMyTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #5: Lọc theo trạng thái");
        MyTicketPage myTicketPage = PageFactory.initElements(Utility.getDriver(), MyTicketPage.class);
        myTicketPage.selectDropDownStatus(data.get("Status"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #6: Nhấn nút ApplyFilter");
        myTicketPage.clickApplyFilterButton();

        logStep = TestReporter.logStepInfo(logMethod, "Bước #7: Xác minh rằng những vé đúng điều kiện được hiển thị");
        if (data.get("Status").equals("New")) {
            myTicketPage.verifyNoResultFoundErrorMessageIsNotDisPlay(logStep);
        } else {
            myTicketPage.verifyNoResultFoundErrorMessageIsDisPlay(logStep);
        }

    }
}
