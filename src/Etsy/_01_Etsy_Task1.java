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

public class _01_Etsy_Task1 {
    static WebDriver driver;

    @BeforeMethod
    public void initialize_driver() {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium dependency\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.etsy.com");
        driver.manage().window().maximize();
    }
    // //span[contains(text(), 'Jewelry & Accessories')]
    // //span[@id = 'catnav-primary-link-10855']
    // #catnav-primary-link-10855

    @Test
    public static void clickRandomMenuItem() {
        List<WebElement> menuWebElementList = driver.findElements(
                By.xpath("//ul[@data-ui = 'top-nav-category-list']//li"));
        List<String> menuStringList = getListOfWebElements(menuWebElementList);
        System.out.println("menuStringList = " + menuStringList);

        int randomIndex = randomNumberGenerator(menuWebElementList.size());
        String randomString = menuStringList.get(randomIndex);
        String randomWebElement = menuWebElementList.get(randomIndex).getText();
        System.out.println("randomIndex = " + randomIndex);
        System.out.println("randomString = " + randomString);
        System.out.println("randomWebElement = " + randomWebElement);

        int itemIndex = getIndexOfSelectedItem(menuWebElementList, randomString);
        selectRandomProduct(menuWebElementList, randomIndex);

        Assert.assertEquals(randomString, randomWebElement);
        Assert.assertEquals(menuStringList.size(), menuWebElementList.size());
        Assert.assertEquals(randomIndex, itemIndex);

        driver.navigate().back();

//        driver.quit();
    }

    @Test(priority = 1)
    public static void clickOnRandomJewelryProduct() {
        WebElement jewelry_btn = driver.findElement(By.xpath("//span[@id = 'catnav-primary-link-10855']"));
        jewelry_btn.click();

        List<WebElement> productList = driver.findElements(
                By.xpath("//h1[@id = 'search-results-top']/following::a/div[2]/h3"));
        List<String> stringList = getListOfWebElements(productList);
        System.out.println("stringList = " + stringList);

        int randomIndex = randomNumberGenerator(productList.size());
        String randomString = stringList.get(randomIndex);
        String randomWebElement = productList.get(randomIndex).getText();

        System.out.println("randomIndex = " + randomIndex);
        System.out.println("randomString = " + randomString);
        System.out.println("randomWebElement = " + randomWebElement);

        int itemIndex = getIndexOfSelectedItem(productList, randomString);
        selectRandomProduct(productList, randomIndex);

        Assert.assertEquals(randomString, randomWebElement);
        Assert.assertEquals(stringList.size(), productList.size());
        Assert.assertEquals(randomIndex, itemIndex);

//        driver.quit();
    }

    public static void selectRandomProduct(List<WebElement> itemList, int randomIndex) {
        itemList.get(randomIndex).click();
    }

    public static void selectSpecificMenuItem(List<WebElement> menuList, String menuItem) {
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getText().equalsIgnoreCase(menuItem)) menuList.get(i).click();
        }
    }

    public static int randomNumberGenerator(int n) {
        return new Random().nextInt(n);
    }

    public static List<String> getListOfWebElements(List<WebElement> webElements) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < webElements.size(); i++) {
            stringList.add(webElements.get(i).getText());
        }
        return stringList;
    }

    public static int getIndexOfSelectedItem(List<WebElement> list, String element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().equalsIgnoreCase(element)) return i;
        }
        return -1;
    }
}
