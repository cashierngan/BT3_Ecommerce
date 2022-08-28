package BT5.testcases;

import BT5.page.LoginPage;
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
        loginPage.LoginValidNullData(DataEcommerce.URL);
    }
    @Test
    public void testLoginValidEmail(){
        loginPage.LoginValidFormatEmail(DataEcommerce.URL,DataEcommerce.EMAILINVALID, DataEcommerce.EMAIL);
    }

    @Test
    public void testLogin(){
        loginPage.Login(DataEcommerce.URL,DataEcommerce.EMAIL, DataEcommerce.PASSWORD);
    }
}
