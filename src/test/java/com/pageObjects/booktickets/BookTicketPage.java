package com.pageObjects.booktickets;

import com.aventstack.extentreports.ExtentTest;
import com.pageObjects.HomePage;
import com.utility.Assertion;
import com.utility.TestReporter;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookTicketPage extends Utility {
    //Locators
    @FindBy(xpath = "//select[@name='Date']")
    private WebElement dropdown_DepartDate;

    @FindBy(xpath = "//select[@name='DepartStation']")
    private WebElement dropdown_DepartStation;

    @FindBy(xpath = "//select[@name='ArriveStation']")
    private WebElement dropdown_ArriveStation;

    @FindBy(xpath = "//select[@name='SeatType']")
    private WebElement dropdown_SeatType;

    @FindBy(xpath = "//select[@name='TicketAmount']")
    private WebElement dropdown_TicketAmount;

    @FindBy(xpath = "//input[@value='Book ticket']")
    private WebElement button_BookTicket;

    @FindBy(xpath = "//h1[text()='Book ticket']")
    private WebElement titlePage;

    @FindBy(xpath = "//h1[contains(text(),'Ticket Booked Successfully!')]")
    private WebElement bookTicketSuccessTitle;

    @FindBy(xpath = "//center//a")
    private WebElement webHosting_label;

    String errorMessage = "//label[contains(text(),'You have booked %s ticket(s). You can only book %s m')]";

    //Elements
    public Select SelectDepartDate() {
        log4j.info("SelectDepartDate - Start");

        WebDriverUtils.waitForControl(dropdown_DepartDate);
        Select departDate = new Select(dropdown_DepartDate);

        log4j.info("SelectDepartDate - End");
        return departDate;
    }

    public Select SelectDepartStation() {
        log4j.info("SelectDepartStation - Start");
        WebDriverUtils.waitForControl(dropdown_DepartStation);
        Select departDate = new Select(dropdown_DepartStation);

        log4j.info("SelectDepartStation - End");
        return departDate;
    }

    public Select SelectArriveStation() {
        log4j.info("SelectArriveStation - Start");
        WebDriverUtils.waitForControl(dropdown_ArriveStation);
        Select departDate = new Select(dropdown_ArriveStation);

        log4j.info("SelectArriveStation - End");
        return departDate;
    }

    public Select SelectSeatType() {
        log4j.info("SelectSeatType - Start");
        WebDriverUtils.waitForControl(dropdown_SeatType);
        Select departDate = new Select(dropdown_SeatType);

        log4j.info("SelectSeatType - End");
        return departDate;
    }

    public Select SelectTicketAmount() {
        log4j.info("SelectTicketAmount - Start");
        WebDriverUtils.waitForControl(dropdown_TicketAmount);
        Select departDate = new Select(dropdown_TicketAmount);

        log4j.info("SelectTicketAmount - End");
        return departDate;
    }

    public String getLabelTitle() {
        WebDriverUtils.waitForControl(titlePage);
        return titlePage.getText();
    }

    public String getErrorMessage(String ticketAmount1, String ticketAmount2) {
        return Utility.getDriver().findElement(By.xpath(String.format(errorMessage, ticketAmount1, ticketAmount2))).getText();
    }

//    public WebElement getDgdBookTicketSuccessfully(String no) {
//        return Constant.WEBDRIVER.findElement(By.xpath(String.format(dgdBookTicketSuccessfully, no)));
//    }

    //Methods
    public void bookTicket(ExtentTest logTest, String departDate, String departStation, String arriveStation, String seatType, String ticketAmount) {

        try {
            log4j.info("BookTicket method - Starts");
            TestReporter.logInfo(logTest, "Chọn ngày khởi hành " + departDate);
            SelectDepartDate().selectByVisibleText(departDate);

            TestReporter.logInfo(logTest, "Chọn ga khởi hành " + departStation);
            SelectDepartStation().selectByVisibleText(departStation);
            Thread.sleep(1000);

            TestReporter.logInfo(logTest, "Chọn gà dừng " + arriveStation);
            SelectArriveStation().selectByVisibleText(arriveStation);

            TestReporter.logInfo(logTest, "Chọn loại ghế ngồi " + seatType);
            SelectSeatType().selectByVisibleText(seatType);

            TestReporter.logInfo(logTest, "Chọn số vé cần mua " + ticketAmount);
            SelectTicketAmount().selectByVisibleText(ticketAmount);

            TestReporter.logInfo(logTest, "Bấm nút Book ticket để đặt vé");
            WebDriverUtils.scrollIntoView(webHosting_label);
            button_BookTicket.submit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getBookedTicketSuccessfullyTitle() {
        return bookTicketSuccessTitle.getText();
    }

    public void bookTicketMultipleTimes(ExtentTest logTest, String departDate, String departStation, String arriveStation,
                                        String seatType, String ticketAmount, int times) {
        try {
            for (int i = 0; i < times; i++) {
                HomePage homePage = PageFactory.initElements(Utility.getDriver(), HomePage.class);
                homePage.clickBookTicketTab(logTest);

                log4j.info("BookTicket method - Starts");
                TestReporter.logInfo(logTest, "Chọn ngày khởi hành " + departDate);
                SelectDepartDate().selectByVisibleText(departDate);

                TestReporter.logInfo(logTest, "Chọn ga khởi hành " + departStation);
                SelectDepartStation().selectByVisibleText(departStation);
                Thread.sleep(1000);

                TestReporter.logInfo(logTest, "Chọn ga dừng " + arriveStation);
                SelectArriveStation().selectByVisibleText(arriveStation);

                TestReporter.logInfo(logTest, "Chọn loại ghế ngồi " + seatType);
                SelectSeatType().selectByVisibleText(seatType);

                TestReporter.logInfo(logTest, "Chọn số vé cần mua " + ticketAmount);
                SelectTicketAmount().selectByVisibleText(ticketAmount);

                TestReporter.logInfo(logTest, "Bấm nút Book ticket để đặt vé");
                WebDriverUtils.scrollIntoView(webHosting_label);
                button_BookTicket.submit();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

