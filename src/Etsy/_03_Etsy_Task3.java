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

public class _03_Etsy_Task3 {
    static WebDriver driver;

    @BeforeMethod
    public void initialize_driver() {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium dependency\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.etsy.com");
        driver.manage().window().maximize();
    }

//*[contains(text(), '14k Solid Gold Letter')]

    String menuXpath = "//ul[@data-ui='top-nav-category-list']//span";
    String itemListXpath = "//h1[@id = 'search-results-top']/following::a/div[2]/h3";

    @Test
    public void clickOnRandomItem(){
        clickOnRandomWebElement(menuXpath);
        try{
            clickOnRandomWebElement(itemListXpath);
        }catch (Exception e){
            System.out.println("Current category has no listed products!");
        }
    }

    public void clickOnRandomWebElement(String xpath) {
        List<String> menuXpath = getXpath(xpath);
        String randomXpath = selectRandomXpath(menuXpath);
        WebElement randomMenuLink = driver.findElement(By.xpath(randomXpath));
        randomMenuLink.click();
    }
    public static List<String> getXpath(String xpath){
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
    public static int randomNumberGenerator(int n) {
        return new Random().nextInt(n);
    }
}
