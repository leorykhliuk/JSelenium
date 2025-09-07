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
import java.util.Objects;

public class SubmitOrderDataProvider {

    private WebDriver driver;
    private ProductCatalog catalog;
    private Header header;
    private Cart cart;
    private LoginPage login;
//    private CheckoutPage checkout;
//    private OrderHistory orderHistory;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        Main main = new Main(driver);
        main.openPage();
        login = new LoginPage(driver);
        header = new Header(driver);
        cart = new Cart(driver);
//        checkout = new CheckoutPage(driver);
//        orderHistory = new OrderHistory(driver);
    }

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void reviewCart(String email, String password, String productName) {
        catalog = login.loginFlow(email, password);
        catalog.addProductToCart(productName);
        header.clickCartButton();
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).endsWith("/cart"));
        Assert.assertTrue(cart.productExistsInCart(productName));
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"tsc01@yopmail.com", "Test123!", "ZARA COAT 3"},
                {"buthqa01@yopmail.com", "Selen123@", "IPHONE 13 PRO"}
        };

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
