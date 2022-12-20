package Etsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static utils.BaseClass.*;
import static utils.locators.*;
import static utils.reusableMethods.*;

public class VinylSticker {

    @Test
    public static void addVinylSticker() throws InterruptedException {
        List<String> selectOpsXpath_List;
        List<WebElement> selectedOps = new ArrayList<>();
        String text = "Test";

        setUp();

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

        boolean isCurrentURL = driver.getCurrentUrl().equalsIgnoreCase("https://www.etsy.com/cart");
        if (isCurrentURL) {
            try {
                Thread.sleep(2000);
                driver.findElement(By.xpath(getViewCart())).click();
            } catch (Exception e1) {
                Thread.sleep(2000);
                driver.findElement(By.xpath(getGoToCart())).click();
            }
        }
        tearDown();
    }

    @Test
    public void selectOpsRandomly() throws InterruptedException {
        List<String> selectOpsXpath_List;
        String text = "Test";

        setUp();

        clickOnRandomWebElement(getMenuXpath());
        try {
            clickOnRandomWebElement(getItemListXpath());
        } catch (Exception e) {
            System.out.println("Current category has no listed products");
        }
        System.out.println(driver);
        System.out.println(driver.getCurrentUrl());
        if (driver != null) {
            switchToNewTab(driver);

            try {
                selectOpsXpath_List = getXpath(getSelectOpsXpath());
                for (String optionsXpath : selectOpsXpath_List) {
                    Thread.sleep(3000);
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
            } catch (Exception ignored){
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
                Assert.assertTrue(checkout.getText().contains("Proceed to checkout"));
            } catch (Exception ignored) {
            }
        }
        tearDown();
    }
}
