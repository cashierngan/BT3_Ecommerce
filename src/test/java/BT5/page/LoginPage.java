package BT5.page;

import ngan.xd.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    private DashboardPage dashboardPage;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        new WebUI(driver);
        dashboardPage = new DashboardPage(driver);
    }
    private By closePopup = By.xpath("//button[@class = 'absolute-top-right bg-white shadow-lg btn btn-circle btn-icon mr-n3 mt-n3 set-session' ]");

    private By buttonLogin1 = By.xpath("//a[@class = 'text-reset d-inline-block opacity-60 py-2' and normalize-space() = 'Login']");
    private By getRequiredEmail = By.xpath("//input[@id = 'email']");
    private By getMessageRequiredEmail = By.xpath("//span[@class='invalid-feedback']");
    private By inputEmail = By.xpath("//input[@id = 'email']");
    private By inputPassword = By.xpath("//input[@id = 'password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");

    public void openURL(String URL){
        WebUI.openURL(URL);
    }
    public void LoginValidNullData(String URL){
        openURL(URL);
        WebUI.clickElement(closePopup);
        WebUI.clickElement(buttonLogin1);
        WebUI.clickElement(buttonLogin);
        WebUI.sleep(1);
        Assert.assertTrue(driver.findElement(inputEmail).getAttribute("class").trim().contains("is-invalid"));
        Assert.assertTrue(driver.findElement(inputPassword).getAttribute("class").trim().contains("is-invalid"));
        Assert.assertTrue(driver.findElement(getMessageRequiredEmail).getText().equals("The email field is required when phone is not present."));
    }

    public void LoginValidFormatEmail(String URL, String emailFail, String email){
        openURL(URL);
        WebUI.clickElement(closePopup);
        WebUI.clickElement(buttonLogin1);
        WebUI.setText(inputEmail, emailFail);
        WebUI.clickElement(buttonLogin);
        System.out.println(driver.findElement(getRequiredEmail).getAttribute("validationMessage"));
        Assert.assertTrue(driver.findElement(getRequiredEmail).getAttribute("validationMessage").trim().equals("Please include an '@' in the email address. 'cashierngan002' is missing an '@'."), "FAIL EMAIL");
        WebUI.clearText(inputEmail);
        WebUI.setText(inputEmail, email);
        WebUI.clickElement(buttonLogin);
        System.out.println(driver.findElement(inputPassword).getAttribute("class"));
        Assert.assertTrue(driver.findElement(inputPassword).getAttribute("class").trim().contains("is-invalid"));
    }
    public void Login(String URL ,String email, String password){
        openURL(URL);
        WebUI.clickElement(closePopup);
        WebUI.clickElement(buttonLogin1);
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
        Assert.assertTrue(WebUI.checkElementExist(dashboardPage.DashboardMenuActive), "Login fail, không tìm thấy menu Dashboard");
    }
}
