package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static utils.BaseClass.driver;
import static utils.locators.getItemListXpath;
import static utils.locators.getMenuXpath;

public class reusableMethods {

    public static List<String> getXpath(String xpath) {
        List<WebElement> webElementList = driver.findElements(By.xpath(xpath));
        List<String> xpathList = new ArrayList<>();
        String elementXpath;

        if (xpath.equalsIgnoreCase(getMenuXpath()) || xpath.equalsIgnoreCase(getItemListXpath())) {
            for (WebElement element : webElementList) {
                elementXpath = "//*[contains(text(), \'" + element.getText() + "\')]";
                xpathList.add(elementXpath);
            }
        } else {
            for (int i = 0; i < webElementList.size(); i++) {
                elementXpath = "//select[@id = \'variation-selector-" + i + "\']";
                xpathList.add(elementXpath);
            }
        }
        return xpathList;
    }

    public static String selectRandomXpath(List<String> xpathList) {
        return xpathList.get(randomNumberGenerator(xpathList.size()));
    }

    public static int randomNumberGenerator(int n) {
        return new Random().nextInt(n);
    }

    public static void clickOnRandomWebElement(String xpath) {
        List<String> xpathOfWebelements = getXpath(xpath);
        String randomXpath = selectRandomXpath(xpathOfWebelements);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement randomItem = driver.findElement(By.xpath(randomXpath));
        System.out.println("randomItem = " + randomItem.getText());
        randomItem.click();
    }

    public static void switchToNewTab(WebDriver webDriver) {
        Set<String> allWindows = webDriver.getWindowHandles();
        for (String window : allWindows) {
            webDriver.switchTo().window(window);
        }
    }
}
