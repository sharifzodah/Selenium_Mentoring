package Etsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

import static utils.BaseClass.*;
import static utils.locators.*;
import static utils.reusableMethods.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class _05_Task_RandomSelection {

    @Test
    public void clickOnRandomXpath() {
        setUp(url);
        clickOnRandomWebElement(getMenuXpath());
        try {
            clickOnRandomWebElement(getItemListXpath());
        } catch (Exception e) {
            System.out.println("Current category has no listed products");
        }
        tearDown();
    }
}
