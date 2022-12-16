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
        Select ops;
        List<WebElement> opsList;
        WebElement selection;
        List<WebElement> selectedOps = new ArrayList<>();
        String qty;
        String text = "Test";

        setUp();

        selectOpsXpath_List = getXpath(getSelectOpsXpath());

        for (String optionsXpath : selectOpsXpath_List) {
            ops = new Select(driver.findElement(By.xpath(optionsXpath)));
            opsList = ops.getOptions();
            selection = opsList.get(randomNumberGenerator(opsList.size()));
            selectedOps.add(selection);
            selection.click();
            Thread.sleep(2000);
        }
        Thread.sleep(3000);
        WebElement textInput = driver.findElement(By.id("listing-page-personalization-textarea"));
        textInput.sendKeys(text);

        try {
            driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();
            Thread.sleep(3000);
            WebElement successMsg = driver.findElement(By.xpath("//h3[text() = 'Added to cart']"));
            Assert.assertEquals(successMsg.getText(), "Added to cart");
        } catch (Exception e){
            System.out.println("Item is not added to cart");
            driver.findElement(By.xpath("//a[contains(text(), 'View cart & check out')]"));
        }

        driver.findElement(By.xpath("//a[text() = 'Go to cart']")).click();
    }
}
