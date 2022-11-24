package Saucedemo.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task1 {
    static WebDriver driver;

    @BeforeMethod
    public void initialize_driver(){
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium dependency\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }


    @Test()
    public void validate_URL_and_Title() {
        String title = driver.getTitle();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue("swag labs".equalsIgnoreCase(title));
        driver.close();
    }

    @Test
    public void validate_List_of_Items() {
        List<WebElement> webElementList = driver.findElements(By.className("inventory_item_name"));
        List<String> stringList = new ArrayList<>();
        for (WebElement element : webElementList) {
            stringList.add(element.getText());
        }
        Assert.assertEquals(webElementList.size(), stringList.size());
        Random rnd = new Random(stringList.size());


    }
}
