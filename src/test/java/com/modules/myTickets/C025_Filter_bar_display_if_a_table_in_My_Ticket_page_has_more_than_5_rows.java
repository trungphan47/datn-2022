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

public class C025_Filter_bar_display_if_a_table_in_My_Ticket_page_has_more_than_5_rows extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Chức năng lọc sẽ xuất hiện nếu số vé của bạn trên 6 hàng trong phần quản lý vé")
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

        logStep = TestReporter.logStepInfo(logMethod, "Bước #5: Xác minh rằng bộ lọc sẽ xuất hiện");
        MyTicketPage myTicketPage = PageFactory.initElements(Utility.getDriver(), MyTicketPage.class);
        myTicketPage.verifyApplyFilterButtonIsDisPlay(logStep);
    }
}
