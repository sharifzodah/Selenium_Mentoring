package Saucedemo.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Task1_1 {
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

    @Test
    public void validate_Title_and_URL(){
        String currentUrl = driver.getCurrentUrl();
        String title = driver.getTitle();

        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue("swag labs".equalsIgnoreCase(title));
    }

    @Test
    public void validate_Cart_Total(){
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Smith");
        driver.findElement(By.id("postal-code")).sendKeys("07650");
        driver.findElement(By.id("continue")).click();
        double item1_price = Double.parseDouble(driver.findElement(By.xpath("//*[@id= 'item_4_title_link']/following::div[2]/child::div"))
                .getText().substring(1));
        double item2_price = Double.parseDouble(driver.findElement(By.xpath("//*[@id= 'item_3_title_link']/following::div[2]/child::div"))
                .getText().substring(1));
        double item3_price = Double.parseDouble(driver.findElement(By.xpath("//*[@id= 'item_1_title_link']/following::div[2]/child::div"))
                .getText().substring(1));
        double subtotal = Double.parseDouble(driver.findElement(By.className("summary_subtotal_label"))
                .getText().replaceAll("[^\\d.]", ""));
        double tax_amount = Double.parseDouble(driver.findElement(By.className("summary_tax_label"))
                .getText().replaceAll("[^\\d.]", ""));
        double total_amount = Double.parseDouble(driver.findElement(By.className("summary_total_label"))
                .getText().replaceAll("[^\\d.]", ""));

        Assert.assertEquals(item1_price, 29.99);
        Assert.assertEquals(item2_price, 15.99);
        Assert.assertEquals(item3_price, 15.99);
        Assert.assertEquals(tax_amount, 4.96);
        Assert.assertEquals(subtotal, (item1_price+item2_price+item3_price), 0.0001);
        Assert.assertEquals(total_amount, (subtotal + tax_amount), 0.0001);

        driver.findElement(By.id("finish")).click();

        String complete_msg = driver.findElement(By.xpath("//*[contains(text(), 'Complete!')]")).getText();
        Assert.assertTrue(complete_msg.contains("Complete".toUpperCase()));

    }
}
