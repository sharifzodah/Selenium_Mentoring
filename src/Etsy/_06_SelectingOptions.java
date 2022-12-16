package Etsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static utils.BaseClass.*;
import static utils.locators.*;
import static utils.reusableMethods.*;

public class _06_SelectingOptions {

    /**
     * @throws InterruptedException
     */

    List<String> selectOpsXpath_List;
    Select ops;
    List<WebElement> opsList;
    List<WebElement> selectedOps = new ArrayList<>();
    WebElement selection;
    String qty;
    String text = "Test";

    @Test
    public void selectOptions() throws InterruptedException {
        setUp();
        clickOnRandomWebElement(getMenuXpath());
        try {
            clickOnRandomWebElement(getItemListXpath());
        } catch (Exception e) {
            System.out.println("Current category has no listed products");
        }
        if (driver != null) {
            switchToNewTab(driver);

            optionSelection();

            try {
                Thread.sleep(4000);
                WebElement qtySelection = driver.findElement(By.xpath("//select[@id = 'listing-page-quantity-select']"));
                ops = new Select(qtySelection);
                int index = randomNumberGenerator(ops.getOptions().size());
                ops.selectByIndex(index);
                qty = ops.getFirstSelectedOption().getText();
            } catch (Exception e) {
                System.out.println("Just add to cart");
                driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();
                Thread.sleep(3000);
                try {
                    driver.findElement(By.xpath("//a[text() = 'Go to cart']")).click();
                } catch (Exception e1) {
                    try {
                        Thread.sleep(3000);
                        driver.findElement(By.xpath("//a[contains(text(), 'View cart & check out')]"));
                    } catch (Exception e2) {
                        System.out.println("Already in your cart");
                        tearDown();
                    }
                }
            }
        }
        tearDown();
    }

    public void optionSelection() throws InterruptedException {
        selectOpsXpath_List = getXpath(getSelectOpsXpath());
        for (String optionsXpath : selectOpsXpath_List) {
            ops = new Select(driver.findElement(By.xpath(optionsXpath)));
            opsList = ops.getOptions();
            selection = opsList.get(randomNumberGenerator(opsList.size()));
            selectedOps.add(selection);
            selection.click();
            Thread.sleep(2000);
        }
        try {
            Thread.sleep(3000);
            WebElement textInput = driver.findElement(By.id("listing-page-personalization-textarea"));
            textInput.sendKeys(text);
        } catch (Exception e) {
            System.out.println("There is no input elements");
            driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();
        }
    }
}
