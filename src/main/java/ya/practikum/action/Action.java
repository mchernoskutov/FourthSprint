package ya.practikum.action;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ya.practikum.constants.Constants.STANDART_WAIT_TIME;


public class Action {

    public static void scrollToElement(By element, WebDriver driver, long waitTime) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void setText (String text, By element, WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public static String getElementValueText (By element, WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getAttribute("value");
    }

    public static String getElementText (By element, WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    public static boolean isElementHidden (By element, WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }

    public static void clickElement (By element, WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public static void sendEnter (By element, WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).sendKeys(Keys.ENTER);
    }

    public static boolean isElementVisible (By element, WebDriver driver, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }


    public static void scrollToElement(By element, WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(STANDART_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        WebElement scroller = driver.findElement(element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", scroller);
    }

    public static void setText (String text, By element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(STANDART_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);

    }

    public static String getElementValueText (By element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(STANDART_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getAttribute("value");
    }

    public static String getElementText (By element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(STANDART_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    public static boolean isElementHidden (By element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(STANDART_WAIT_TIME));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }

    public static void clickElement (By element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(STANDART_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public static void sendEnter (By element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(STANDART_WAIT_TIME));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).sendKeys(Keys.ENTER);
    }

    public static boolean isElementVisible (By element, WebDriver driver) {

         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(STANDART_WAIT_TIME));
         return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();

    }


    public static void justWait() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
