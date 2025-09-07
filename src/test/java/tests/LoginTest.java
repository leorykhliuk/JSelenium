package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;
    private Main main;
    private Header header;
    private Cart cart;
    private CheckoutPage checkout;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        main = new Main(driver);
        main.openPage();
        LoginPage login = new LoginPage(driver);
        ProductCatalog catalog = login.loginFlow("tsc01@yopmail.com", "Test123!");
    }

    @Test
    public void loginIsDone(){
        Assert.assertTrue(main.getPageUrl().endsWith("/dashboard/dashLoginTest"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
