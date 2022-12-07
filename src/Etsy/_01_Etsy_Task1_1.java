package Etsy;

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

public class _01_Etsy_Task1_1 {
    static WebDriver driver;

    @BeforeMethod
    public void initialize_driver() {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium dependency\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.etsy.com");
        driver.manage().window().maximize();
    }

    @Test
    public static void clickRandomMeuItem() {
        List<WebElement> menuWebElementList = driver.findElements(By.xpath("//*[@data-ui='top-nav-category-list']//li"));
        List<String> menuStringList = getListOfWebElements(menuWebElementList); // index start from 0 till list.size()
        int randomIndex = randomNumberGenerator(menuWebElementList.size());
        String randomElement = menuStringList.get(randomIndex);
        String randomWebElement = menuWebElementList.get(randomIndex).getText();
        System.out.println("randomIndex = " + randomIndex);
        System.out.println("randomElement = " + randomElement);
        System.out.println("randomWebElement = " + randomWebElement);

        int itemIndex = getIndexOfSelectedItem(menuWebElementList, randomElement);
        selectRandomProduct(menuWebElementList, randomIndex);

        Assert.assertEquals(randomElement, randomWebElement);
        Assert.assertEquals(menuWebElementList.size(), menuStringList.size());
        Assert.assertEquals(randomIndex, itemIndex);

    }

    @Test
    public static void clickOnRandomJewelryProduct() {
        WebElement jewelryBtn = driver.findElement(By.id("catnav-primary-link-10855"));
        jewelryBtn.click();
    }

    public static List<String> getListOfWebElements(List<WebElement> webElementList) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < webElementList.size(); i++) {
            stringList.add(webElementList.get(i).getText());
        }
        return stringList;
    }

    public static int randomNumberGenerator(int n) {
        return new Random().nextInt(n);  //5
    }

    public static int getIndexOfSelectedItem(List<WebElement> list, String element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().equalsIgnoreCase(element)) return i;
        }
        return -1;
    }

    public static void selectRandomProduct(List<WebElement> itemList, int randomIndex) {
        itemList.get(randomIndex).click();
    }
}
