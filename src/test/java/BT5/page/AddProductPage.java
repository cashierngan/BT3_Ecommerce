package BT5.page;

import ngan.xd.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AddProductPage {
    WebDriver driver;
    JavascriptExecutor js;
    public AddProductPage(WebDriver driver){
        this.driver = driver;
        new WebUI(driver);
    }
    private By productMenu = By.xpath("//span[normalize-space() = 'Products']");
    private By addNewProduct = By.xpath("//span[normalize-space() = 'Add New Product']");
    private By addNewProductPage = By.xpath("//h5[normalize-space() = 'Add New Product']");
    private By buttonSubmitAddProduct = By.xpath("//button[@class = 'btn btn-primary action-btn']");
    private By getRequiredProductName = By.xpath("//input[@placeholder = 'Product Name' and @required]");
    private By category = By.xpath("//div[contains(text(),'Computer & Accessories')]");
    private By inputCoctailCategory = By.xpath("//input[@aria-controls= 'bs-select-1']");
    private By getRequiredUnit = By.xpath("//input[@name = 'unit' and @required]");

    private By blockProductInf = By.xpath("(//div[@class='card-header'])[1]");
    private By labelProductName = By.xpath("(//div[@class='form-group row']//label[@class='col-md-3 col-from-label'])[1]");
    private By inputProductName = By.xpath("//input[@name = 'name']");
    private By labelCategory = By.xpath("//div[@id = 'category']//label[@class = 'col-md-3 col-from-label']");





    public void selectMenuAddProduct(){
        WebUI.clickElement(productMenu);
        WebUI.clickElement(addNewProduct);
    }
    public void addProductWithRequiredData(String productName, String unit){

        Actions action = new Actions(driver);
        js = (JavascriptExecutor) driver;

        selectMenuAddProduct();

        action.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
        Assert.assertTrue(driver.findElement(buttonSubmitAddProduct).isEnabled(),"CAN NOT CLICK SAVE BUTTON");
        WebUI.clickElement(buttonSubmitAddProduct);
        WebUI.sleep(2);
        js.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(addNewProductPage));
        WebUI.sleep(2);
        System.out.println(driver.findElement(inputProductName).getAttribute("validationMessage"));
        Assert.assertTrue(driver.findElement(getRequiredProductName).isDisplayed());
        Assert.assertTrue(driver.findElement(inputProductName).getAttribute("validationMessage").trim().equals("Please fill out this field."));
        Assert.assertTrue(driver.findElement(labelProductName).getText().trim().contains("Product Name"));
        Assert.assertTrue(driver.findElement(inputProductName).getAttribute("placeholder").equals("Product Name"));

        WebUI.setText(inputProductName, productName);
        action.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
        Assert.assertTrue(driver.findElement(buttonSubmitAddProduct).isEnabled(),"CAN NOT CLICK SAVE BUTTON");
        WebUI.clickElement(buttonSubmitAddProduct);
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(addNewProductPage));
        Assert.assertTrue(driver.findElement(getRequiredUnit).isDisplayed());
        Assert.assertTrue(driver.findElement(getRequiredUnit).getAttribute("validationMessage").trim().equals("Please fill out this field."), "Fail message validate Unit");
        WebUI.setText(getRequiredUnit, unit);
        action.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
        Assert.assertTrue(driver.findElement(buttonSubmitAddProduct).isEnabled(),"CAN NOT CLICK SAVE BUTTON");
        WebUI.clickElement(buttonSubmitAddProduct);

    }

    public void productInformation(String productName){
        // Product Name
        Assert.assertTrue(driver.findElement(blockProductInf).getText().trim().equals("Product Information"));
        Assert.assertTrue(driver.findElement(labelProductName).getText().trim().contains("Product Name"));
        Assert.assertTrue(driver.findElement(inputProductName).getAttribute("placeholder").equals("Product Name"));
        WebUI.setText(inputProductName, productName);
        Assert.assertTrue(driver.findElement(labelProductName).getText().trim().contains("Category"));





    }
    public void addProduct(String productName, String categories){
        selectMenuAddProduct();
        WebUI.setText(inputProductName, productName);
        WebUI.clickElement(category);
        WebUI.setText(inputCoctailCategory,categories);
    }



}
