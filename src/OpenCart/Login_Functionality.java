package OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login_Functionality {
    static WebDriver driver;
    static String str;
    @Test
    public void firstLogin() {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium dependency\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.opencart.com");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("input-email")).sendKeys("jonisherin@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("qwertyu2022");
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        driver.findElement(By.id("input-pin")).sendKeys("1590");
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        String welcome_message = driver.findElement(By.xpath("//p[text() = 'Welcome to OpenCart!']")).getText();
        String title = driver.getTitle();
        System.out.println("welcome_message = " + welcome_message);
        System.out.println("title = " + title);
        Assert.assertEquals("Welcome to OpenCart!", welcome_message);
        Assert.assertEquals("OpenCart - Your Account", title);

    }
    @Test
    public void secondLogin(){
        driver.switchTo().newWindow(WindowType.TAB).get("http://www.opencart.com");
        // //*[contains(text(),'Account')]//following-sibling::a
        // //div[@class = 'navbar-right hidden-xs']/child::a[2]
        // //div/a[contains(text(), 'Logout')]
    }
}
