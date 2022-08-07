package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginPage {
    public static void login(WebDriver driver, String email1, String password1) throws InterruptedException {
        driver.get("https://ecommerce.anhtester.com/users/login");

        SoftAssert softAssert = new SoftAssert();

        String popup = "//button[@class = 'absolute-top-right bg-white shadow-lg btn btn-circle btn-icon mr-n3 mt-n3 set-session' ]";
        WebElement closepopup = driver.findElement(By.xpath(popup));
        closepopup.click();

        String login = "//a[@class = 'text-reset d-inline-block opacity-60 py-2' and normalize-space() = 'Login']";
        WebElement buttonLogin = driver.findElement(By.xpath(login));
        buttonLogin.click();
        Thread.sleep(500);


        // Validate Login form
        System.out.println("*** Verify The Content Of Login Form **");
        String email = "//input[@id = 'email']";
        String password = "//input[@id = 'password']";
        String submitLogin = "//button[normalize-space()='Login']";


        Thread.sleep(500);
        driver.findElement(By.xpath(email)).sendKeys("cashierngan002@gmail.com");
        driver.findElement(By.xpath(password)).sendKeys("12345678");
        driver.findElement(By.xpath(submitLogin)).click();
        Thread.sleep(500);


        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space() = 'Invalid login credentials']")).getText().trim().equals("Invalid login credentials"), "Verify password fail");
        Thread.sleep(500);
        driver.findElement(By.xpath(email)).sendKeys(email1);
        driver.findElement(By.xpath(password)).sendKeys(password1);
        driver.findElement(By.xpath(submitLogin)).click();
        Thread.sleep(500);
        softAssert.assertAll();
    }
}
