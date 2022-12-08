package com.modules.myTickets;

import com.common.TestBase;
import com.modules.actions.HighlevelMethods;
import com.pageObjects.HomePage;
import com.pageObjects.booktickets.BookTicketPage;
import com.pageObjects.myTicket.MyTicketPage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class C030_user_can_filter_tickets_by_choosing_all_filter_items_with_incorrect_Depart_date_format extends TestBase {

    @Test(dataProvider = "getDataForTest", description = "Người dùng có thể lọc vé đã mua")
    public void TC01(Hashtable<String, String> data) {

        logStep = TestReporter.logStepInfo(logMethod, "Bước #1: Điều hướng đến trang web Railway và đăng nhập");
        HighlevelMethods.login(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #2: Điều hướng đến trang Book tickets");
        HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
        homePage.clickBookTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #3: Đặt vé");
        BookTicketPage bookTicketPage = PageFactory.initElements(Utility.getDriver(), BookTicketPage.class);
        String departDate = Utility.getDepartDate(10);
        for (int i = 1; i <= 6; i++) {
            bookTicketPage.bookTicket(logStep, departDate, data.get("DepartStation" + i), data.get("ArriveStation" + i), data.get("SeatType"), data.get("TicketAmount"));
            homePage.clickBookTicketTab(logStep);
        }
        logStep = TestReporter.logStepInfo(logMethod, "Bước #4: Điều hướng đến trang My Ticket");
        homePage.openMyTicketTab(logStep);

        logStep = TestReporter.logStepInfo(logMethod, "Bước #5: Lọc theo các tiêu chí");
        MyTicketPage myTicketPage = PageFactory.initElements(Utility.getDriver(), MyTicketPage.class);
        myTicketPage.selectDropDownDepartStation(data.get("DepartStationFilter"));
        myTicketPage.enterDepartDate(departDate);
        myTicketPage.selectDropDownStatus("New");
        myTicketPage.selectDropDownArriveStation(data.get("ArriveStationFilter"));

        logStep = TestReporter.logStepInfo(logMethod, "Bước #6: Nhấn nút FilterApply");
        myTicketPage.clickApplyFilterButton();

        logStep = TestReporter.logStepInfo(logMethod, "Bước #7: Xác minh rằng những vé đúng điều kiện được hiển thị");
        Assertion.verifyActualAndExpected(logStep, myTicketPage.getDepartStation(), data.get("DepartStationFilter"));
        Assertion.verifyActualAndExpected(logStep, myTicketPage.getArriveStation(), data.get("ArriveStationFilter"));
        Assertion.verifyActualAndExpected(logStep, myTicketPage.getDepartDate(), departDate);
        Assertion.verifyActualAndExpected(logStep, myTicketPage.getSeatType(), data.get("SeatType"));
        Assertion.verifyActualAndExpected(logStep, myTicketPage.getAmount(), data.get("TicketAmount"));

    }
}
