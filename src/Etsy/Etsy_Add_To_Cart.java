package Etsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static utils.BaseClass.*;
import static utils.locators.*;
import static utils.reusableMethods.*;

public class Etsy_Add_To_Cart {

    @Test
    public static void addSpecificItem() throws InterruptedException {
        List<String> selectOpsXpath_List;
        String text = "Test";

        setUp(url1);

        selectOpsXpath_List = getXpath(getSelectOpsXpath());

        for (String optionsXpath : selectOpsXpath_List) {
            Thread.sleep(2000);
            selectOption(optionsXpath);
        }
        Thread.sleep(2000);
        WebElement textInput = driver.findElement(By.id(getTextInput()));
        textInput.sendKeys(text);

        Thread.sleep(2000);
        selectOption(getQtySelXpath());

        Thread.sleep(2000);
        driver.findElement(By.xpath(getAddToCart())).click();

        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath(getViewCart())).click();
        } catch (Exception ignored) {
        }
        try {
            Thread.sleep(2000);
            driver.findElement(By.xpath(getGoToCart())).click();
        } catch (Exception ignored) {
        }

        Thread.sleep(2000);
        WebElement checkout = driver.findElement(By.xpath(getCheckOut()));
        System.out.println("checkout = " + checkout.getText());
        Assert.assertTrue(checkout.getText().contains("Proceed to checkout"));

        Thread.sleep(5000);
        tearDown();
    }

    @Test(priority = 1)
    public void selectOpsRandomly() throws InterruptedException {
        List<String> selectOpsXpath_List;
        String text = "Test";

        setUp(url);

        clickOnRandomWebElement(getMenuXpath());
        try {
            clickOnRandomWebElement(getItemListXpath());
        } catch (Exception e) {
            System.out.println("Current category has no listed products");
            tearDown();
            System.out.println(driver);
            driver = null;
        }

        if (driver != null) {
            switchToNewTab(driver);

            try {
                selectOpsXpath_List = getXpath(getSelectOpsXpath());
                for (String optionsXpath : selectOpsXpath_List) {
                    Thread.sleep(4000);
                    selectOption(optionsXpath);
                }
            } catch (Exception e1) {
                System.out.println("No Options available");
            }
            try {
                Thread.sleep(2000);
                WebElement textInput = driver.findElement(By.id(getTextInput()));
                textInput.sendKeys(text);
            } catch (Exception e2) {
                System.out.println("No text required");
            }
            try {
                Thread.sleep(2000);
                selectOption(getQtySelXpath());
            } catch (Exception e3) {
                System.out.println("No quantity needed");
            }
            try {
                Thread.sleep(2000);
                driver.findElement(By.xpath(getAddToCart())).click();
            } catch (Exception ignored) {
            }
            try {
                Thread.sleep(2000);
                driver.findElement(By.xpath(getViewCart())).click();
            } catch (Exception ignored) {
            }
            try {
                Thread.sleep(2000);
                driver.findElement(By.xpath(getGoToCart())).click();
            } catch (Exception ignored) {
            }
            try {
                WebElement checkout = driver.findElement(By.xpath(getCheckOut()));
                checkout.click();
            } catch (Exception e4) {
                System.out.println("Other payment method required before checkout");
            }
        }
        Thread.sleep(3000);
        tearDown();
    }
}
