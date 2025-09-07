package tests;

import Reusable.BaseTest;
import TestData.GeneralNamings;
import TestData.ProductNames;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

public class DifferentFlowWithBefore extends BaseTest {
    private ProductCatalog catalog;
    private Header header;
    private Cart cart;
    private CheckoutPage checkout;

    @BeforeMethod
    public void setup() throws InterruptedException, IOException {
        catalog = loginPage.loginFlow("tsc01@yopmail.com", "Test123!");
        header = new Header(driver);
        cart = new Cart(driver);
        checkout = new CheckoutPage(driver);
    }

    @Test
    public void loginIsDone(){
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/dashboard/dash"));
    }

    @Test
    public void addProductToCart(){
        catalog.addProductToCart(ProductNames.ZARA.get());
        Assert.assertTrue(header.getMiniCartValue().equals("1"));
    }

    @Test
    public void reviewCart(){
        catalog.addProductToCart(ProductNames.ZARA.get());
        header.clickCartButton();
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/cart"));
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

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
