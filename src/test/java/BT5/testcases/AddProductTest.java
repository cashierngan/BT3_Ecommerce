package BT5.testcases;

import BT5.page.AddProductPage;
import BT5.page.LoginPage;
import Common.DataEcommerce;
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
        new LoginPage(driver).Login(DataEcommerce.URL,DataEcommerce.EMAIL, DataEcommerce.PASSWORD);
        addProductPage.addProduct(DataEcommerce.PRODUCT_NAME, DataEcommerce.CATEGORY);
    }

    @Test
    public void testAddProductWithRequiredData(){
        new LoginPage(driver).Login(DataEcommerce.URL,DataEcommerce.EMAIL, DataEcommerce.PASSWORD);
        addProductPage.addProductWithRequiredData(DataEcommerce.PRODUCT_NAME,DataEcommerce.UNIT);
    }
}
