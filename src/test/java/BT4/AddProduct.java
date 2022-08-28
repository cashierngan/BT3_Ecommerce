package BT4;

import Common.BaseTest;
import Common.DataEcommerce;
import Common.LoginPage;
import ngan.xd.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;

public class AddProduct extends BaseTest {

    JavascriptExecutor js;

    @Test(priority = 1, description = "Login to HRM")
    public void Login() throws InterruptedException {

        LoginPage.login(driver, "cashierngan002@gmail.com","123456");

    }

    @Test(priority = 2, description = "Add New Product")
    public void addProduct() throws InterruptedException, AWTException {
        Actions action = new Actions(driver);
        Robot robot = new Robot();


        js = (JavascriptExecutor) driver;

        System.out.println("***Verify and Action class Add New Product form***");

        String productMenu = "//span[normalize-space() = 'Products']";
        Thread.sleep(500);
        Assert.assertTrue(driver.findElement(By.xpath(productMenu)).isDisplayed(), "Product menu is NOT displayed");
        WebUI.clickElement(By.xpath(productMenu));
//        driver.findElement(By.xpath(productMenu)).click();
        String addNewProductMenu = "//span[normalize-space() = 'Add New Product']";
        Assert.assertTrue(driver.findElement(By.xpath(addNewProductMenu)).isDisplayed(), "Add new product submenu is NOT displayed");
//        driver.findElement(By.xpath(addNewProductMenu)).click();
        WebUI.clickElement( By.xpath(addNewProductMenu));

        String addNewProductPage = "//h5[normalize-space() = 'Add New Product']";
        Assert.assertTrue(driver.findElement(By.xpath(addNewProductPage)).getText().trim().equals("Add New Product"), "Fail Add Product page");

        action.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(500);
        String submitAddProduct = "//button[@class = 'btn btn-primary action-btn']";
        Assert.assertTrue(driver.findElement(By.xpath(submitAddProduct)).isEnabled(), "Can NOT click _Save and unpublish_ button");
//        driver.findElement(By.xpath(submitAddProduct)).click();
        WebUI.clickElement(By.xpath(submitAddProduct));
        WebElement element = driver.findElement(By.xpath("//h5[normalize-space()='Add New Product']"));
        js.executeScript("arguments[0].scrollIntoView(false);", element);
        Thread.sleep(500);
        String productName = "//input[@placeholder = 'Product Name']";
        String requiredProductName = "//input[@placeholder = 'Product Name' and @required]";
        System.out.println(driver.findElement(By.xpath(productName)).getAttribute("validationMessage"));
        Assert.assertTrue(driver.findElement(By.xpath(requiredProductName)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(productName)).getAttribute("validationMessage").trim().equals("Please fill out this field."));
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Product Name ']")).getText().trim().contains("Product Name"));
        Assert.assertTrue(driver.findElement(By.xpath(productName)).getAttribute("placeholder").equals("Product Name"));

        Thread.sleep(500);
//        driver.findElement(By.xpath("//input[@name = 'name']")).sendKeys(DataEcommerce.PRODUCT_NAME );
        WebUI.setText( By.xpath("//input[@name = 'name']"), DataEcommerce.PRODUCT_NAME);
        Thread.sleep(500);
        action.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
        driver.findElement(By.xpath(submitAddProduct)).click();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        String unit = "//input[@name = 'unit' and @required]";
        Assert.assertTrue(driver.findElement(By.xpath(unit)).isDisplayed());
        System.out.println(driver.findElement(By.xpath(unit)).getText());
        Assert.assertTrue(driver.findElement(By.xpath(unit)).getAttribute("validationMessage").trim().equals("Please fill out this field."), "Fail message validate Unit");
        Assert.assertTrue(driver.findElement(By.xpath(unit)).getAttribute("placeholder").equals("Unit (e.g. KG, Pc etc)"));
        Assert.assertTrue(driver.findElement(By.xpath("//label[text() = 'Unit']")).getText().trim().equals("Unit"));
        driver.findElement(By.xpath(unit)).sendKeys("Chai");

        Thread.sleep(500);
        Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'Category')]")).getText().trim().contains("Category"));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(text(),'Computer & Accessories')]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@aria-controls= 'bs-select-1']")).sendKeys("cocktail", Keys.ENTER);
        Thread.sleep(500);

        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Brand']")).getText().trim().equals("Brand"));
        driver.findElement(By.xpath("//button[@title='Select Brand']")).click();
        driver.findElement(By.xpath("//input[@aria-controls= 'bs-select-2']")).sendKeys("LL", Keys.ENTER);
        Thread.sleep(500);
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='tagify__input']")).getAttribute("aria-placeholder").equals("Type and hit enter to add a tag"));
        Assert.assertTrue(driver.findElement(By.xpath("(//small[@class = 'text-muted'])[1]")).getText().trim().equals("This is used for search. Input those words by which cutomer can find this product."));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@role='textbox']")).sendKeys("NganNgan", Keys.ENTER);
        Thread.sleep(1000);

        // Gallery Images
        driver.findElement(By.xpath("(//div[normalize-space() = 'Choose File'])[1]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[@class = 'nav-link font-weight-medium text-dark']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@class = 'uppy-Dashboard-input']")).sendKeys(System.getProperty("user.dir") + "/src/main/resources/d7706b2888a2102d25ed8ec2a9fc0a91.png");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[normalize-space()='Select File']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//div[@title='d7706b2888a2102d25ed8ec2a9fc0a91.png'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        Thread.sleep(2000);

        // Thumbnail Image
        driver.findElement(By.xpath("(//div[normalize-space() = 'Choose File'])[1]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[@class = 'nav-link font-weight-medium text-dark']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@class = 'uppy-Dashboard-input']")).sendKeys(System.getProperty("user.dir") + "/src/main/resources/d7706b2888a2102d25ed8ec2a9fc0a91.png");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[normalize-space()='Select File']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//div[@title='d7706b2888a2102d25ed8ec2a9fc0a91.png'])[2]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        Thread.sleep(500);

        WebElement scrollProductVideo = driver.findElement(By.xpath("//h5[normalize-space() = 'Product Videos']"));

        js.executeScript("arguments[0].scrollIntoView(true);", scrollProductVideo);
        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space() = 'Video Provider']")).getText().trim().equals("Video Provider"));
        driver.findElement(By.xpath("//div[contains(text(),'Youtube')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Dailymotion')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name = 'video_link']")).sendKeys("https://www.youtube.com/watch?v=56L-gzlPzK8");
        Thread.sleep(4000);

        driver.findElement(By.xpath("//input[@name = 'colors_active']//following-sibling::span")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("(//div[contains(text(),'Nothing selected')])[1]")).click();
        Thread.sleep(500);
//        driver.findElement(By.xpath("//input[@aria-controls = 'bs-select-4']")).sendKeys("aqua");
        WebUI.setText( By.xpath("//input[@aria-controls = 'bs-select-4']"), "aqua");
        action.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN).keyUp(Keys.CONTROL).keyUp(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(500);
//        driver.findElement(By.xpath("//input[@aria-controls = 'bs-select-4']")).clear();
//        Thread.sleep(500);
        WebUI.clearText( By.xpath("//input[@aria-controls = 'bs-select-4']"));
//        driver.findElement(By.xpath("//input[@aria-controls = 'bs-select-4']")).sendKeys("crim",Keys.ENTER);
//        Thread.sleep(500);
        WebUI.setText( By.xpath("//input[@aria-controls = 'bs-select-4']"), "crim");
        action.keyDown(Keys.ENTER).keyUp(Keys.ENTER);
//        driver.findElement(By.xpath("//div[contains(text(),'2 items selected')]")).click();
        Thread.sleep(500);
        WebUI.clickElement( By.xpath("//div[contains(text(),'2 items selected')]"));
//        driver.findElement(By.xpath("//div[contains(text(),'Nothing selected')]")).click();
//        Thread.sleep(500);
        WebUI.clickElement( By.xpath("//div[contains(text(),'Nothing selected')]"));
        WebUI.clickElement(By.xpath("//span[normalize-space() = 'Fabric']"));
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h5[normalize-space()='Product price + stock']")));
        WebUI.clearText( By.xpath("//input[@name = 'unit_price']"));
        WebUI.setText( By.xpath("//input[@name = 'unit_price']"), "3000");
        WebUI.clickElement( By.xpath("//input[@placeholder='Select Date']"));
        WebUI.clickElement( By.xpath("//div[@class='drp-calendar left']//select[@class='hourselect']"));
        WebUI.clickElement(By.xpath("//div[@class = 'drp-calendar left']//select[@class = 'hourselect']//option[@value = 7]"));
        WebUI.clickElement( By.xpath("//button[@class = 'applyBtn btn btn-sm btn-primary']"));
        WebUI.clearText( By.xpath("//input[@placeholder='Discount']"));
        WebUI.setText( By.xpath("//input[@placeholder='Discount']"), "10");
//        WebUI.clearText( By.xpath("//input[@placeholder='Quantity']"));
//        WebUI.enterText( By.xpath("//input[@placeholder='Quantity']"), "99");
//        WebUI.enterText( By.xpath("//input[@placeholder='SKU']"),DataEcommerce.PRODUCT_SKU);
        WebUI.setText( By.xpath("//input[@placeholder='External link']"), "https://anhtester.com/lesson/selenium-java-bai-14-cach-dung-javascript-executor-de-hanh-dong");
        WebUI.setText( By.xpath("//div[@role='textbox']"), DataEcommerce.PRODUCT_DESCRIPTION);
        WebUI.clickElement( By.xpath("(//div[normalize-space()='Choose File'])[3]"));
        WebUI.clickElement( By.xpath("//a[normalize-space()='Upload New']"));
        driver.findElement(By.xpath("//input[@class='uppy-Dashboard-input']")).sendKeys(System.getProperty("user.dir") + "/src/main/resources/sample.pdf");
        WebUI.clickElement( By.xpath("//a[normalize-space()='Select File']"));
        WebUI.clickElement( By.xpath("//div[@class='card card-file aiz-uploader-select']"));
        WebUI.clickElement( By.xpath("//button[normalize-space()='Add Files']"));

        WebUI.clickElement( By.xpath("//button[@class = 'btn btn-success action-btn']"));
        Thread.sleep(5000);




    }

}
