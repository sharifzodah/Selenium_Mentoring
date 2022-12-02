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
    public void clickOnRandomMenuLink() throws InterruptedException {
        List<String> menuXpath = getMenuXpath();
        String randomXpath = selectRandomXpath(menuXpath);
        System.out.println("randomXpath = " + randomXpath);
        WebElement randomMenuLink = driver.findElement(By.xpath(randomXpath));
        randomMenuLink.click();

        Thread.sleep(5000);
        System.out.println(driver.getCurrentUrl());

        while (!randomMenuLink.getText().equalsIgnoreCase("Holiday Shop") &&
                !randomMenuLink.getText().equalsIgnoreCase( "Gifts & Gift Cards")){
            List<WebElement> productList = driver.findElements(
                    By.xpath("//h1[@id = 'search-results-top']/following::a/div[2]/h3"));
            selectRandomProduct(productList);
        }

    }



    public static List<String> getMenuXpath(){
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
        for (int i = 0; i < webElements.size(); i++) {
            stringList.add(webElements.get(i).getText());
        }
        return stringList;
    }
}
