package com.pageObjects.myTicket;

import com.aventstack.extentreports.ExtentTest;
import com.utility.Assertion;
import com.utility.Utility;
import com.utility.WebDriverUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MyTicketPage extends Utility {


    @FindBy(xpath = "//tbody/tr[count(//th[text()='Operation']/preceding-sibling::th)-8]//input[@value='Cancel']")
    private WebElement button_Cancel;

    @FindBy(xpath = "//h1")
    private WebElement label_title;

    @FindBy(xpath = "//input[@value='Apply Filter']")
    private WebElement button_ApplyFilter;

    @FindBy(xpath = "//div[@class='error message']")
    private WebElement label_NoResultFoundErrorMessage;

    @FindBy(xpath = "//select[@name='FilterStatus']")
    private WebElement dropDown_Status;

    @FindBy(name = "FilterDpDate")
    private WebElement input_DepartDate;

    @FindBy(xpath = "//div[@class='Filter']//div[@class='error message']")
    private WebElement label_MalformedDateErrorMessage;

    @FindBy(xpath = "//select[@name='FilterArStation']")
    private WebElement dropDown_ArriveStation;

    @FindBy(xpath = "//select[@name='FilterDpStation']")
    private WebElement dropDown_DepartStation;

    @FindBy(xpath = "//tr[@class='TableSmallHeader']//..//td[count(//tr[@class='TableSmallHeader']//th[text()='Depart Station']/preceding-sibling::th)+1]")
    private WebElement departStation;

    @FindBy(xpath = "//tr[@class='TableSmallHeader']//..//td[count(//tr[@class='TableSmallHeader']//th[text()='Arrive Station']/preceding-sibling::th)+1]")
    private WebElement arriveStation;

    @FindBy(xpath = "//tr[@class='TableSmallHeader']//..//td[count(//tr[@class='TableSmallHeader']//th[text()='Depart Date']/preceding-sibling::th)+1]")
    private WebElement departDate;

    @FindBy(xpath = "//tr[@class='TableSmallHeader']//..//td[count(//tr[@class='TableSmallHeader']//th[text()='Seat Type']/preceding-sibling::th)+1]")
    private WebElement seatType;

    @FindBy(xpath = "//tr[@class='TableSmallHeader']//..//td[count(//tr[@class='TableSmallHeader']//th[text()='Amount']/preceding-sibling::th)+1]")
    private WebElement amount;

    //Methods
    public String getLabelTitle() {
        return label_title.getText();
    }

    public void cancelTicket() {

        log4j.info("cancelTicket() - Start");
        WebDriverUtils.scrollIntoView(button_Cancel);
        button_Cancel.click();
        log4j.info("cancelTicket() - End");
    }

    public void verifyCancelTicketSuccessfully(ExtentTest logTest) {
        Assertion.checkControlNotExist(logTest, button_Cancel, "Cancel button");
    }

    public void selectDropDownStatus(String option) {

        log4j.info("selectDropDownStatus - Start");
        WebDriverUtils.waitForControl(dropDown_Status);
        Select select = new Select(dropDown_Status);
        select.selectByVisibleText(option);
        log4j.info("selectDropDownStatus - End");
    }

    public void selectDropDownArriveStation(String option) {

        log4j.info("selectDropDownArriveStation - Start");
        WebDriverUtils.waitForControl(dropDown_ArriveStation);
        Select select = new Select(dropDown_ArriveStation);
        select.selectByVisibleText(option);
        log4j.info("selectDropDownArriveStation - End");
    }

    public void selectDropDownDepartStation(String option) {
        log4j.info("selectDropDownDepartStation - Start");
        WebDriverUtils.waitForControl(dropDown_DepartStation);
        Select select = new Select(dropDown_DepartStation);
        select.selectByVisibleText(option);
        log4j.info("selectDropDownDepartStation - End");
    }

    public void enterDepartDate(String departDate) {
        log4j.info("enterDepartDate - Start");
        input_DepartDate.sendKeys(departDate);
        log4j.info("enterDepartDate - End");
    }

    public void clickApplyFilterButton() {
        log4j.info("clickApplyFilterButton - Start");
        WebDriverUtils.waitForControl(button_ApplyFilter);
        button_ApplyFilter.click();
        log4j.info("clickApplyFilterButton - End");
    }

    public void verifyApplyFilterButtonIsDisPlay(ExtentTest logTest) {
        Assertion.checkControlExist(logTest, button_ApplyFilter, "ApplyFilter button");
    }

    public void verifyNoResultFoundErrorMessageIsDisPlay(ExtentTest logTest) {
        WebDriverUtils.waitForControl(label_NoResultFoundErrorMessage);
        Assertion.checkControlExist(logTest, label_NoResultFoundErrorMessage, "ErrorMsg");
    }

    public void verifyNoResultFoundErrorMessageIsNotDisPlay(ExtentTest logTest) {
        Assertion.checkControlNotExist(logTest, label_NoResultFoundErrorMessage, "ErrorMsg");
    }

    public String getDepartStation() {
        WebDriverUtils.waitForControl(departStation);
        return departStation.getText();
    }

    public String getArriveStation() {
        WebDriverUtils.waitForControl(arriveStation);
        return arriveStation.getText();
    }

    public String getDepartDate() {
        WebDriverUtils.waitForControl(departDate);
        return departDate.getText();
    }

    public String getSeatType() {
        WebDriverUtils.waitForControl(seatType);
        return seatType.getText();
    }

    public String getAmount() {
        WebDriverUtils.waitForControl(amount);
        return amount.getText();
    }

    public void acceptAlert() {
        Alert alert = Utility.getDriver().switchTo().alert();
        alert.accept();
    }

}
