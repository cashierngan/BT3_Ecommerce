package BT4_POM.testcases;

import BT4_POM.page.AddProductPage;
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
        addProductPage.addProduct(DataEcommerce.EMAIL, DataEcommerce.PASSWORD, DataEcommerce.PRODUCT_NAME, DataEcommerce.CATEGORY);
    }

    @Test
    public void testAddProductWithRequiredData(){
        addProductPage.addProductWithRequiredData(DataEcommerce.EMAIL, DataEcommerce.PASSWORD, DataEcommerce.PRODUCT_NAME,DataEcommerce.UNIT);
    }
}
