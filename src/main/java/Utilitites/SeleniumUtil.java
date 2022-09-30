package Utilitites;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {
    private static WebDriver driver;

/**
 * Created by SreeLasya.Vallabhanenion.
 */ 

    public SeleniumUtil(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Wait for an element to be displayed in the DOM
     *
     * @param elementLocator - CSS of the element
     * @return - WebElement
     */

    public static WebElement waitForElement(String elementLocator) {
        WebElement webElement = null;
        int timeout = 20; //in seconds
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            System.out.println(elementLocator);
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementLocator)));
        } catch (WebDriverException e) {
            //do nothing, don't want to log this
        }

        
        return webElement;
    }


    public static WebElement waitForElementPrescence(String elementLocator) {
        WebElement webElement = null;
        int timeout = 10; //in seconds
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            System.out.println(elementLocator);
            webElement  = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementLocator)));

        } catch (WebDriverException e) {
            //do nothing, don't want to log this
        }

        //if element wasn't found -> fail test
        
        return webElement;
    }

    public static void postUrl(String elementLocatorUrl) {
        try{
            driver.get(elementLocatorUrl);
        } catch (WebDriverException e) {
            System.out.println("Cannot get the requested url" + elementLocatorUrl);
        }
    }

    public void click(String elementLocator) {

        WebElement webElement = waitForElement(elementLocator);
        try {
        webElement.click();
        } catch(Exception ex) {

        }
    }

    public static void enterText(String elementLocator, String text) {
        System.out.println(text);
        System.out.println(elementLocator);
        WebElement webElement = waitForElement(elementLocator);
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * Method to wait for page to update following an action that changes the pages state.
     * This is needed to avoid any race conditions between page updates and Selenium, which can sometimes occur.
     */

    public static void waitForPageLoad() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void refreshPage() {

        String currentURL = getCurrentURL();
        driver.navigate().to(currentURL);
    }

    public static String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public static String getElementText(String elementLocator) {

        WebElement webElement = waitForElement(elementLocator);
        return webElement.getText();
    }
}