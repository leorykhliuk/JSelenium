package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import  org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddAllProducts {
    public static void main(String[] args) throws InterruptedException {
//        String productName = "ZARA COAT 3";

        WebDriver driver = new ChromeDriver();
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions act = new Actions(driver);

        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.manage().window().maximize();
        //locators
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        WebElement passwordInput = driver.findElement(By.id("userPassword"));
        WebElement buttonLogin = driver.findElement(By.id("login"));
        List<WebElement> allProducts = driver.findElements(By.cssSelector("div.card"));

        emailInput.sendKeys("tsc01@yopmail.com");
        passwordInput.sendKeys("Test123!");
        buttonLogin.click();

        Thread.sleep(2000);
        String url = driver.getCurrentUrl();
        System.out.println(url);
        Assert.assertTrue(url.endsWith("/dashboard/dash"));

        List<WebElement> products = driver.findElements(By.cssSelector("div.card"));
        products.forEach((product) -> {
            WebElement addToBagBtn = product.findElement(By.xpath(".//button[contains(.,'Add To pages.Cart')]"));
            addToBagBtn.click();
            waitSpinner(driver);
        });

        String miniCartProductNumber = driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart'] > label")).getText();
        int numberOfProducts = products.size();
        //Check counter
        Assert.assertEquals(Integer.parseInt(miniCartProductNumber), numberOfProducts);


        WebElement cartButton = driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']"));
        cartButton.click();

        WebElement checkoutButton = driver.findElement(By.xpath("//button[text()='Checkout']"));
        w.until(ExpectedConditions.visibilityOf(checkoutButton));

        List<String> expectedNames = List.of("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO", "Cream");
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection > h3"));
        ArrayList<String> collectedNames = new ArrayList<>();
        for(WebElement product : cartProducts) {
            collectedNames.add(product.getText().trim());
        }
        Assert.assertTrue(collectedNames.containsAll(expectedNames));

        System.out.println("TEXT IS HERE!!!" + checkoutButton.getText());
        act.moveToElement(checkoutButton).perform();
        checkoutButton.click();


        //checkout
        WebElement selectCountryField = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));

        act.sendKeys(selectCountryField, "Germany").build().perform();
        driver.findElement(By.cssSelector("span > i")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String summaryText = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(summaryText.equalsIgnoreCase("Thankyou for the order."));

        driver.close();
    };

    public  static void waitSpinner(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ngx-spinner-overlay")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.ngx-spinner-overlay")));
//        Assert.assertTrue(driver.findElement(By.cssSelector("div.ngx-spinner-overlay")).isDisplayed());
    }
}
