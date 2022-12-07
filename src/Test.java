import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
//        System.setProperty("webdriver.chrome.driver", "D:\\Selenium dependency\\drivers\\chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//
////        Navigating to the website
//        driver.get("http://www.practiceselenium.com/");
//        driver.switchTo().newWindow(WindowType.TAB);

        Random rnd = new Random();
        int a = rnd.nextInt(2);
        System.out.println("rnd = " + a);
        String b = "abcd";
        boolean abcd = b.contentEquals("abcd");
        System.out.println("abcd = " + abcd);

    }
}
