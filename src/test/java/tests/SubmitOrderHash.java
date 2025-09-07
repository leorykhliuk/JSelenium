package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

public class SubmitOrderHash {

    private WebDriver driver;
    private ProductCatalog catalog;
    private Header header;
    private Cart cart;
    private LoginPage login;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        Main main = new Main(driver);
        main.openPage();
        login = new LoginPage(driver);
        header = new Header(driver);
        cart = new Cart(driver);
    }

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void reviewCart(HashMap<String,String> input) {
        catalog = login.loginFlow(input.get("email"), input.get("password"));
        catalog.addProductToCart(input.get("product"));
        header.clickCartButton();
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).endsWith("/cart"));
        Assert.assertTrue(cart.productExistsInCart(input.get("product")));
    }

    @DataProvider
    public Object[][] getData(){
        HashMap <String, String> map1 = new HashMap<String, String>();
        map1.put("email", "tsc01@yopmail.com");
        map1.put("password", "Test123!");
        map1.put("product", "ZARA COAT 3");

        HashMap <String, String> map2 = new HashMap<String, String>();
        map2.put("email", "buthqa01@yopmail.com");
        map2.put("password", "Selen123@");
        map2.put("product", "IPHONE 13 PRO");
        return new Object[][]{
                {map1},
                {map2}
        };

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
