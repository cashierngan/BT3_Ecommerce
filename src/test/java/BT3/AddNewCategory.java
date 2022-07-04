package BT3;

import Common.BaseTest;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.charset.StandardCharsets;

public class AddNewCategory extends BaseTest {
    @Test(priority = 1, description = "Login to HRM")
    public void Login() throws InterruptedException {

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
        Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space() = 'Login to your account.']")).getText().trim().equals("Login to your account."), "Fail Title Login Form");
        softAssert.assertTrue(driver.findElement(By.xpath("//input[@id = 'email']")).getAttribute("placeholder").equals("Email"), "Fail Email Placeholder");
        softAssert.assertTrue(driver.findElement(By.xpath("//input[@id = 'password']")).getAttribute("placeholder").equals("Password"), "Fail Password Placeholder");
        softAssert.assertTrue(driver.findElement(By.xpath("//button[@class= 'btn btn-primary btn-block fw-600']")).isEnabled(), "Login button is NOT enable");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id = 'email']")).getAttribute("type").equals("email"), "Fail type of email");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id = 'password']")).getAttribute("type").equals("password"), "Fail type of password");
        String email = "//input[@id = 'email']";
        String password = "//input[@id = 'password']";
        String submitLogin = "//button[normalize-space()='Login']";

        // Login fail without email/ password
        driver.findElement(By.xpath(submitLogin)).click();
        String messageEmail = "//strong[text() = 'The email field is required when phone is not present.']";
        WebElement checkMessageEmail = driver.findElement(By.xpath(messageEmail));
        Assert.assertEquals(checkMessageEmail.getText().trim(), "The email field is required when phone is not present.", "Message valid Email");

        Thread.sleep(500);
        driver.findElement(By.xpath(email)).sendKeys("cashier");
        driver.findElement(By.xpath(password)).sendKeys("12345678");
        driver.findElement(By.xpath(submitLogin)).click();
        Assert.assertTrue(driver.findElement(By.id("email")).getAttribute("validationMessage").contains("Please include an '@' in the email address."));
        System.out.println(driver.findElement(By.id("email")).getAttribute("validationMessage"));
        Thread.sleep(500);

        Thread.sleep(500);
        driver.findElement(By.xpath(email)).sendKeys("cashierngan002@gmail.com");
        driver.findElement(By.xpath(password)).sendKeys("12345678");
        driver.findElement(By.xpath(submitLogin)).click();
        Thread.sleep(500);


        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space() = 'Invalid login credentials']")).getText().trim().equals("Invalid login credentials"), "Verify password fail");
        Thread.sleep(500);
        driver.findElement(By.xpath(email)).sendKeys("cashierngan002@gmail.com");
        driver.findElement(By.xpath(password)).sendKeys("123456");
        driver.findElement(By.xpath(submitLogin)).click();
        Thread.sleep(500);
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Add Category")
    public void addCategory () throws InterruptedException {
        String searchMenu = "//input[@class = 'form-control bg-soft-secondary border-0 form-control-sm text-white']";
        WebElement enterSearchMenu = driver.findElement(By.xpath(searchMenu));
        enterSearchMenu.sendKeys("Category");
        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.xpath("//i[@class='las la-ellipsis-h aiz-side-nav-icon']")).isDisplayed(), "Validate Category Menu");
        String menuCategory = "//a[@class = 'aiz-side-nav-link'][normalize-space() = 'Category']";
        WebElement clickMenuCategory = driver.findElement(By.xpath(menuCategory));
        clickMenuCategory.click();
        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Add New category']")).getText().trim().equals("Add New category"), "Validate Add Category button");
        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Add New category']")).isEnabled(), "Validate Add Category button Enabled");
        String addNewCategory = "//a[@class = 'btn btn-primary']";
        WebElement clickAddNewCategory = driver.findElement(By.xpath(addNewCategory));
        clickAddNewCategory.click();
        Thread.sleep(1000);

        System.out.println("*** Verify Add Category Form***");

        // Click Add New Category
        Assert.assertTrue(driver.findElement(By.xpath("//h5[text() = 'Category Information']")).getText().trim().equals("Category Information"));
        driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();
        Thread.sleep(500);

        // Input Name
        Assert.assertTrue(driver.findElement(By.id("name")).getAttribute("placeholder").trim().equals("Name"), "Fail validate placeholder Name");
        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space() = 'Name']")).getText().trim().equals("Name"), "Fail validate label Name");
        WebElement requiredTextbox = driver.findElement(By.xpath("//input[@id='name' and @required]"));
        Assert.assertTrue(requiredTextbox.isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("name")).getAttribute("type").equals("text"), "Fail type input");
        String categoryName = "name";
        WebElement enterCategoryName = driver.findElement(By.id(categoryName));
        enterCategoryName.sendKeys("Ngannn_beer");
        Thread.sleep(500);

        // Select Parent Category
        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Parent Category']")).getText().trim().equals("Parent Category"), "Fail label Parent Category");
        Assert.assertTrue(driver.findElement(By.xpath("//div[text() = 'No Parent']")).getText().trim().equals("No Parent"), "Fail placeholder No Parent");
        String categoryParent = "//div[normalize-space() = 'No Parent' and @class = 'filter-option-inner-inner']";
        WebElement selectCategoryParent = driver.findElement(By.xpath(categoryParent));
        selectCategoryParent.click();
        Thread.sleep(500);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '---- Brand PC']")).getText().trim().contains("Brand PC"), "Not existed Parent Brand PC");
        String inputCategoryParent = "//div[@class = 'dropdown-menu show']//input[@class = 'form-control']";
        WebElement enterCategoryParent = driver.findElement(By.xpath(inputCategoryParent));
        enterCategoryParent.sendKeys("Brand PC", Keys.ENTER);
        Thread.sleep(500);

        // Input ordering Number
        String orderingNumber = "//input[@id = 'order_level']";
        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space() = 'Ordering Number']")).getText().trim().equals("Ordering Number"), "Fail label Ordering number");
        Assert.assertTrue(driver.findElement(By.xpath(orderingNumber)).getAttribute("type").equals("number"), "Fail type of Ordering Number");
        Assert.assertTrue(driver.findElement(By.xpath(orderingNumber)).getAttribute("placeholder").equals("Order Level"));
        WebElement enterOrderingNumber = driver.findElement(By.xpath(orderingNumber));
        enterOrderingNumber.sendKeys("e");
        Thread.sleep(200);
        driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();
        Thread.sleep(500);
        System.out.println(enterOrderingNumber.getAttribute("validationMessage"));
        Assert.assertTrue(enterOrderingNumber.getAttribute("validationMessage").contains("Please enter a number."));
        Thread.sleep(500);
        enterOrderingNumber.clear();
        enterOrderingNumber.sendKeys("3");
        Thread.sleep(500);

        // Select type category
        String typeCategory = "//div[normalize-space() = 'Physical' and @class = 'filter-option-inner-inner']";
        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Type']")).getText().trim().equals("Type"), "Fail label type");
        Assert.assertTrue(driver.findElement(By.xpath(typeCategory)).getText().trim().equals("Physical"), "Fail placeholder type category");
        WebElement clickTypeCategory = driver.findElement(By.xpath(typeCategory));
        clickTypeCategory.click();
        Thread.sleep(500);
        String typePhisical = "//span[normalize-space() = 'Digital']";
        Assert.assertTrue(driver.findElement(By.xpath(typePhisical)).getText().trim().equals("Digital"), "Fail select Physical at Type");
        WebElement selectTypePhisical = driver.findElement(By.xpath(typePhisical));
        selectTypePhisical.click();
        Thread.sleep(500);

        // Upload image Banner (200x200)
        System.out.println(driver.findElement(By.xpath("//label[text()='Banner ']")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Banner ']")).getText().trim().equals("Banner (200x200)"), "Fail label Banner (200x200)");
        Assert.assertTrue(driver.findElement(By.xpath("(//div[@class = 'input-group'])[1]")).getAttribute("data-type").equals("image"), "Fail data type Banner");
        String bannerCategory = "(//div[@class='form-control file-amount' and normalize-space()='Choose File'])[1]";
        WebElement clickBannerCategory = driver.findElement(By.xpath(bannerCategory));
        clickBannerCategory.click();
        Thread.sleep(500);
        String uploadNewBanner = "//a[@class = 'nav-link font-weight-medium text-dark']";
        WebElement clickUploadNewBanner = driver.findElement(By.xpath(uploadNewBanner));
        clickUploadNewBanner.click();
        Thread.sleep(500);
        String inputFileUpload = "//input[@class='uppy-Dashboard-input']";
        driver.findElement(By.xpath(inputFileUpload)).sendKeys(System.getProperty("user.dir") + "/src/main/resources/download.jpg");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Icon ']")).getText().trim().equals("Icon (32x32)"), "Fail label Icon (32x32)");
        Assert.assertTrue(driver.findElement(By.xpath("(//div[@class = 'input-group'])[2]")).getAttribute("data-type").equals("image"), "Fail data type Icon");
        String selectFile = "//a[@class = 'nav-link font-weight-medium text-dark']";
        WebElement clickSelectFile = driver.findElement(By.xpath(selectFile));
        clickSelectFile.click();
        Thread.sleep(500);

        // Upload image Icon (32x32)
        String imgBanner = "//img[@src = '//ecommerce.anhtester.com/public/uploads/all/GgQwb4fVJDvkA0Ii9Q7OZErEQQ7mdhO4fdObTHT6.jpg']";
        WebElement clickImgBanner = driver.findElement(By.xpath(imgBanner));
        clickImgBanner.click();
        Thread.sleep(500);
        String addFile = "//button[@class = 'btn btn-sm btn-primary' and text() = 'Add Files']";
        WebElement clickAddfile = driver.findElement(By.xpath(addFile));
        clickAddfile.click();
        Thread.sleep(1000);
        String iconCategory = "//div[@class = 'form-control file-amount' and text() = 'Choose File']";
        driver.findElement(By.xpath(iconCategory)).click();
        Thread.sleep(500);
        String imgIcon = "//img[@src = '//ecommerce.anhtester.com/public/uploads/all/DCO0Ut2C2qK3jCz8cSskhf5vhJ8kH0yY1PsWC1pB.jpg']";
        driver.findElement(By.xpath(imgIcon)).click();
        Thread.sleep(500);
        String addFileIcon = "//button[@class = 'btn btn-sm btn-primary' and text() = 'Add Files']";
        WebElement clickAddfileIcon = driver.findElement(By.xpath(addFileIcon));
        clickAddfileIcon.click();
        Thread.sleep(500);

        // Input Meta title
        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Meta Title']")).getText().trim().equals("Meta Title"), "Fail Label Meta Title");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name = 'meta_title']")).getAttribute("placeholder").equals("Meta Title"), "Fail Meta title placeholder");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name = 'meta_title']")).getAttribute("type").equals("text"), "Fail Meta tile type");
        driver.findElement(By.xpath("//input[@name = 'meta_title']")).sendKeys("@@ NNN @@");
        Thread.sleep(500);

        // Input Meta description
        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Meta description']")).getText().equals("Meta description"), "Fail Meta description");
        driver.findElement(By.xpath("//textarea[@name = 'meta_description']")).sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
        Thread.sleep(500);

        // Select Filtering Attributes
        Assert.assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Filtering Attributes']")).getText().trim().equals("Filtering Attributes"), "Fail label Filtering Attributes");
        Assert.assertTrue(driver.findElement(By.xpath("//div[text() = 'Nothing selected']")).getText().equals("Nothing selected"), "Fail name select field Filtering Attributes");
        driver.findElement(By.xpath("//div[text() = 'Nothing selected']")).click();
        Thread.sleep(500);
        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Size']")).getText().trim().equals("Size"), "Do not exist Size");
        driver.findElement(By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']")).sendKeys("Size", Keys.ENTER);
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']")).clear();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']")).sendKeys("Fabric", Keys.ENTER);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Fabric']")).getText().trim().equals("Fabric"), "Do not exist Fabric");
        Thread.sleep(500);

        // Submit
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).isEnabled());
        driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();
        Thread.sleep(2000);


    }

    @Test(priority = 3, description = "Verify new category on the grid")
    public void searchCategory() throws InterruptedException {

        System.out.println("***Verify a new Category on the grid***");
        driver.findElement(By.xpath("//input[@class = 'form-control bg-soft-secondary border-0 form-control-sm text-white']")).sendKeys("Category");
        Thread.sleep(500);

        driver.findElement(By.xpath("//a[@class = 'aiz-side-nav-link'][normalize-space() = 'Category']")).click();
        Thread.sleep(500);

        Assert.assertTrue(driver.findElement(By.xpath("//h1[normalize-space() = 'All categories']")).getText().trim().equals("All categories"), "Fail All Categories Page");
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='search']")).getAttribute("placeholder").equals("Type name & Enter"), "Fail search textbox");
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Ngannn_beer", Keys.ENTER);
        Thread.sleep(2000);

        System.out.println("Count quantity category by Name is Ngannn_beer: " + driver.findElements(By.xpath("//td[normalize-space()='Ngannn_beer']")).size());

        Assert.assertTrue(driver.findElement(By.xpath("(//td[normalize-space()='Ngannn_beer'])[1]")).getText().trim().equals("Ngannn_beer"));

    }
}
