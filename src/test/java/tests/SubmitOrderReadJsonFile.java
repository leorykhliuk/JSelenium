package tests;

import Data.DataReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

public class SubmitOrderReadJsonFile {

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

    @Test(dataProvider = "loginDataProvider", groups = {"Purchase"})
    public void reviewCart(Map<String, String> loginEntry) {
        catalog = login.loginFlow(loginEntry.get("email"), loginEntry.get("password"));
        catalog.addProductToCart(loginEntry.get("product"));
        header.clickCartButton();
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).endsWith("/cart"));
        Assert.assertTrue(cart.productExistsInCart(loginEntry.get("product")));
    };

    @DataProvider
    public Iterator<Object[]> loginDataProvider() throws IOException {
        // Read data from JSON
        SequencedCollection<Map<String, String>> data = new DataReader().getJsonData();

        // Convert to Iterator<Object[]> for TestNG
        return data.stream()
                .map(entry -> new Object[]{entry})
                .iterator();
    };


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
