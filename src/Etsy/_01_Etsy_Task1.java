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
    public static void listOfMenuItems() throws InterruptedException {
        List<WebElement> menuWebElementList = driver.findElements(
                By.xpath("//ul[@data-ui = 'top-nav-category-list']//li"));
        List<String> menuStringList = getListOfWebElements(menuWebElementList);

        Assert.assertEquals(menuStringList.size(), menuWebElementList.size());

        selectRandomProduct(menuWebElementList);
        driver.navigate().back();

        Thread.sleep(2000);
        menuWebElementList = driver.findElements(
                By.xpath("//ul[@data-ui = 'top-nav-category-list']//li"));

        int randomIndex = randomNumberGenerator(menuWebElementList.size());
        String randomString = menuStringList.get(randomIndex);
        String randomWebElement = menuWebElementList.get(randomIndex).getText();

        Assert.assertEquals(randomString, randomWebElement);

        driver.quit();
    }
    @Test(priority = 1)
    public static void navigateToJewelry() throws InterruptedException{
        WebElement jewelry_btn = driver.findElement(By.xpath("//span[@id = 'catnav-primary-link-10855']"));
        jewelry_btn.click();

        List<WebElement> productList = driver.findElements(
                By.xpath("//h1[@id = 'search-results-top']/following::a/div[2]/h3"));
        List<String> stringList = getListOfWebElements(productList);
        selectRandomProduct(productList);

        Assert.assertEquals(stringList.size(), productList.size());

        int randomIndex = randomNumberGenerator(productList.size());
        String randomString = stringList.get(randomIndex);
        String randomWebElement = productList.get(randomIndex).getText();

        Assert.assertEquals(randomString, randomWebElement);
        driver.quit();
    }

    @Test(priority = 2)
    public static void getMenuXpath(){
        List<WebElement> elements = driver.findElements(
                By.xpath("//ul[@data-ui='top-nav-category-list']//span"));
        List<String> xpathList = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            String element_to_string = elements.get(i).toString();
            String element_text = elements.get(i).getText();
            String xpath = element_to_string
                    .substring(element_to_string.indexOf("//"), element_to_string.lastIndexOf("]"))
                    + "[contains(text(), \'" + element_text + "\')]";;
            xpathList.add(xpath);
            System.out.println("xpathList.get(i) = " + xpathList.get(i));
        }
    }

    public static void selectRandomProduct(List<WebElement> itemList) {
        int randomIndex = randomNumberGenerator(itemList.size());
        itemList.get(randomIndex).click();
    }

    public static int randomNumberGenerator(int n) {
        return new Random().nextInt(n);
    }

    public static List<String> getListOfWebElements(List<WebElement> webElements){
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < webElements.size(); i++) {
            stringList.add(webElements.get(i).getText());
        }
        return stringList;
    }
}
