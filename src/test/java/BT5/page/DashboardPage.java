package BT5.page;

import ngan.xd.utils.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    WebDriver driver;
    public DashboardPage(WebDriver driver){
        this.driver = driver;
        new WebUI(driver);
    }
     public By DashboardMenuActive = By.xpath("//li[@class='aiz-side-nav-item mm-active']");
}
