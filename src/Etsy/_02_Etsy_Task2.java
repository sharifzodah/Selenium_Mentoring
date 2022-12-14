package Etsy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*
TC2.1
1. Navigate to https://www.etsy.com/
2. Click on Random Menu Item Link
3. Click on Random product
*/
public class _02_Etsy_Task2 {
    static WebDriver driver;

    @BeforeMethod
    public void initialize_driver() {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium dependency\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.etsy.com");
        driver.manage().window().maximize();
    }

    @Test
    public void clickOnRandomItem(){
        String linkStr = clickOnRandomMenuLink();
        if (!linkStr.equalsIgnoreCase("Holiday Shop") &&
                !linkStr.equalsIgnoreCase( "Gifts & Gift Cards")){
            List<WebElement> productList = driver.findElements(
                    By.xpath("//h1[@id = 'search-results-top']/following::a/div[2]/h3"));
            selectRandomProduct(productList);
            driver.navigate().back();
        }
    }

    public String clickOnRandomMenuLink() {
        String xpath = "//ul[@data-ui='top-nav-category-list']//span";
        List<String> menuXpath = getMenuXpath(xpath);
        String randomXpath = selectRandomXpath(menuXpath);
        WebElement randomMenuLink = driver.findElement(By.xpath(randomXpath));
        String randomMenuLinkStr = randomMenuLink.getText();
        randomMenuLink.click();

        return randomMenuLinkStr;
    }
    public static List<String> getMenuXpath(String xpath){
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        List<String> xpathList = new ArrayList<>();

        for (WebElement element : elements) {
            String element_xpath = "//*[contains(text(), \'" + element.getText() + "\')]";
            xpathList.add(element_xpath);
        }
        return xpathList;
    }

    public static String selectRandomXpath(List<String>xpathList){
        int randomIndex = randomNumberGenerator(xpathList.size());
        return xpathList.get(randomIndex);
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
        for (WebElement webElement : webElements) {
            stringList.add(webElement.getText());
        }
        return stringList;
    }
}
