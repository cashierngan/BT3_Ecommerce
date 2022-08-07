package ngan.xd.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebUI {
    private static int TIMEOUT = 10;
    private static int STEP_TIME = 1;

    private static WebDriver driver;
    public WebUI(WebDriver driver){
        WebUI.driver = driver;
    }
    public static void sleep (long second){
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clickElement ( By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        driver.findElement(by).click();
    }

    public static void enterText ( By by, String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        driver.findElement(by).sendKeys(text);
    }

    public static void clearText ( By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(STEP_TIME);
        driver.findElement(by).clear();
    }

    public static void waitElementVisible ( By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void openURL(String URL){
        driver.get(URL);
    }
}
