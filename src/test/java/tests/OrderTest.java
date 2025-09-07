package tests;

import TestData.GeneralNamings;
import TestData.ProductNames;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;
import java.util.Objects;

public class OrderTest {

    private WebDriver driver;
    private ProductCatalog catalog;
    private Header header;
    private Cart cart;
    private CheckoutPage checkout;
    private OrderHistory orderHistory;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        Main main = new Main(driver);
        main.openPage();
        LoginPage login = new LoginPage(driver);
        catalog = login.loginFlow("tsc01@yopmail.com", "Test123!");
        header = new Header(driver);
        cart = new Cart(driver);
        checkout = new CheckoutPage(driver);
        orderHistory = new OrderHistory(driver);
    }

    @Test
    public void addProductToCart(){
        catalog.addProductToCart(ProductNames.ZARA.get());
        Assert.assertEquals(header.getMiniCartValue(), "1");
    }

    @Test
    public void reviewCart(){
        catalog.addProductToCart(ProductNames.ZARA.get());
        header.clickCartButton();
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).endsWith("/cart"));
        Assert.assertTrue(cart.productExistsInCart(ProductNames.ZARA.get()));
    }

    @Test
    public void passCheckout(){
        catalog.addProductToCart(ProductNames.ZARA.get());
        header.clickCartButton();
        cart.clickCheckoutBtn();
        checkout.selectCountry("GERMANY");
        checkout.clickPlaceOrder();
        Assert.assertTrue(new ConfirmationPage(driver).getSummaryText()
                .equalsIgnoreCase(GeneralNamings.CONFIRMATION_TEXT.get()));
    }

    @Test//(dependsOnMethods = "passCheckout")
    public void orderHistoryHasOrderCards(){
        header.clickOrdersBtn();
        Assert.assertTrue(this.orderHistory.isTheOrdersInTheHistory());
    }

    @Test
    public void productNameAppearsInHistory(){
        header.clickOrdersBtn();
        Assert.assertTrue(orderHistory.getProductNames().contains(ProductNames.ZARA.get()));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
