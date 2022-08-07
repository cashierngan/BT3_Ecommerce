package BT4_POM.testcases;

import BT4_POM.page.LoginPage;
import Common.DataEcommerce;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends Common.BaseTest {
    public LoginPage loginPage;

    @BeforeMethod
    public void LoginTest(){
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginNullRequired(){
        loginPage.LoginValidNullData();
    }
    @Test
    public void testLoginValidEmail(){
        loginPage.LoginValidFormatEmail(DataEcommerce.EMAILINVALID, DataEcommerce.EMAIL);
    }

    @Test
    public void testLogin(){
        loginPage.Login("cashierngan002@gmail.com", "123456");
    }
}
