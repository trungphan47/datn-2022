package com.utility;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import static com.common.GlobalVariables.WAIT_TIME_60;

public class WebDriverUtils extends Utility{
    public static void navigateToTestSite(ExtentTest logTest, String url){
        try {
            TestReporter.logInfo(logTest, "Điều hướng đến: " + url);
            WebDriverUtils.switchToWindowHandle();
            Utility.getDriver().navigate().to(url);
            WebDriverUtils.waitForPageLoaded();
        } catch (Exception e){
            Utility.log4j.error("navigateToTestSite method - ERROR: ", e);
            TestReporter.logException(logTest, "navigateToTestSite method - ERROR: ", e);
        }
    }

    public static void waitForPageLoaded() {
        Wait<WebDriver> wait = new WebDriverWait(Utility.getDriver(), WAIT_TIME_60);
        try {
            //Wait for HTML load
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver){
                    Utility.sleep(1);
                    boolean readyState = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    boolean activeJQuery = ((JavascriptExecutor) driver).executeScript("if (typeof jQuery != 'undefined') {return jQuery.active == 0; } else { return true; }").equals(true);
                    return readyState && activeJQuery;
                }
            });
            
        } catch (Exception e){
            Utility.log4j.error("waitForPageloaded - ERROR: ", e);
        }
    }

    public static void switchToWindowHandle(){
        try {
            String popupWindowHandle = getWindowHandle(Utility.getDriver());
            Utility.getDriver().switchTo().window(popupWindowHandle);
            maximizeWindow();
        } catch (Exception e){
            Utility.log4j.error("switchToWindowHandle method - ERROR: ", e);
        }
    }
    
    public static void switchToFrame(){
        try {
            WebElement element = Utility.getDriver().findElement(By.tagName("iframe"));
            Utility.getDriver().switchTo().frame(element);
        } catch (Exception e){
            Utility.log4j.error("switchToFrame method - ERROR: ", e);
        }
    }

    public static String getWindowHandle(WebDriver driver){
    //get all the window handles after the popup window appears
        Set<String > afterPopup = driver.getWindowHandles();
        Iterator<String> iterator = afterPopup.iterator();
        while (iterator.hasNext()){
            Utility.subWindowHandler = iterator.next();
        }

        return Utility.subWindowHandler;
    }
    
    public static void maximizeWindow(){
        try{
            Utility.getDriver().manage().window().maximize();            
        } catch (Exception e){
            Utility.log4j.error("maximizeWindow method - ERROR: ", e);
        }
    }

    public static void waitForControl(WebElement element){
        try {
            new WebDriverWait(Utility.getDriver(), WAIT_TIME_60).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e){

        }
    }

    public static void waitForControl(WebElement element, int waitTime){
        try {
            new WebDriverWait(Utility.getDriver(), waitTime).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e){

        }
    }

    public static void waitForControlBeClickable(WebElement element){
        try {
            new WebDriverWait(Utility.getDriver(), WAIT_TIME_60).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e){

        }
    }

    public static void  scrollIntoView(WebElement element){
        try {
            Actions actions = new Actions(Utility.getDriver());
            actions.moveToElement(element);
            actions.perform();
        } catch (Exception e){
        }
    }

    public static boolean doesControlExist(WebElement element){
        try {
            return element.isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public static void closeTab(ExtentTest logMethod){
        Utility.getDriver().quit();
        initializeDriver(logMethod);
    }

}
