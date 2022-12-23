package Etsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static utils.BaseClass.*;
import static utils.locators.*;
import static utils.reusableMethods.*;

public class Vinyl2 {

    @Test
    public static void addVinylSticker() throws InterruptedException {

        List<String> selectOpsXpathList;
        Select ops;
        List<WebElement> opsList;
        WebElement selection;
        List<WebElement> selectionList = new ArrayList<>();
        setUp();

        selectOpsXpathList = getXpath(getSelectOpsXpath());

        for (String optionsXpath : selectOpsXpathList) {
            ops = new Select(driver.findElement(By.xpath(optionsXpath)));
            opsList = ops.getOptions();
            selection = opsList.get(randomNumberGenerator(opsList.size()));
            selection.click();
            Thread.sleep(3000);
        }

    }
}
