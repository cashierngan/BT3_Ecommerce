package BT4_POM.testcases;

import BT4_POM.page.AddProductPage;
import BT4_POM.page.LoginPage;
import Common.DataEcommerce;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddProductTest extends Common.BaseTest {
    public AddProductPage addProductPage;

    @BeforeMethod
    public void AddProductPage(){
        addProductPage = new AddProductPage(driver);
    }

    @Test
    public void testAddProduct(){
        new LoginPage(driver).Login(DataEcommerce.EMAIL, DataEcommerce.PASSWORD);
        addProductPage.addProduct(DataEcommerce.EMAIL, DataEcommerce.PASSWORD, DataEcommerce.PRODUCT_NAME, DataEcommerce.CATEGORY);
    }

    @Test
    public void testAddProductWithRequiredData(){
        new LoginPage(driver).Login(DataEcommerce.EMAIL, DataEcommerce.PASSWORD);
        addProductPage.addProductWithRequiredData(DataEcommerce.EMAIL, DataEcommerce.PASSWORD, DataEcommerce.PRODUCT_NAME,DataEcommerce.UNIT);
    }
}
