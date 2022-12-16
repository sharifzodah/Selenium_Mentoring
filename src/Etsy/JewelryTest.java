package Etsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Set;

import static utils.BaseClass.*;
import static utils.reusableMethods.*;

public class JewelryTest {
    String itemListXpath = "//h1[@id = 'search-results-top']/following::a/div[2]/h3";

    @Test
    public void clickOnRandomJewelry() {
        setUp();
        WebElement clothing = driver.findElement(By.xpath("//*[contains(text(), 'Jewelry & Accessories')]"));
        clothing.click();
        try {
            clickOnRandomWebElement(itemListXpath);
        } catch (Exception e) {
            System.out.println("Current category has no listed products!");
        }
        switchToNewTab(driver);
        driver.findElement(By.xpath("//button[contains(text(), 'Reviews for this shop')]")).click();
        tearDown();
    }
}
