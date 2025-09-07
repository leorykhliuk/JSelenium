package tests;

import  org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import  org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LaunchPage {
    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
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
        WebElement match = products.stream().filter(product -> product.findElement(By.tagName("b"))
                .getText().equals(productName)).findFirst().orElse(null);
        match.findElement(By.xpath("//button[contains(., 'Add To pages.Cart')]")).click();

        waitSpinner(driver);
        System.out.println(match.getText());

        //Check counter
        Assert.assertTrue(driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart'] label")).getText().equals("1"));


        WebElement cartButton = driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']"));
        cartButton.click();

        System.out.println(driver.getCurrentUrl());

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/cart"));

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection > h3"));
        Boolean matchProduct = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(matchProduct);

        /*
         tried to catch success message
          WebElement alertMessage = driver.findElement(By.xpath("//div[@role='alertdialog']")); // and contains(.,'Product Added To pages.Cart')
          Assert.assertTrue(alertMessage.isDisplayed());
          System.out.println(alertMessage.getText());
         */

        WebElement checkoutButton = driver.findElement(By.xpath("//button[text()='Checkout']"));
        checkoutButton.click();


        //checkout
        Actions act = new Actions(driver);
        WebElement selectCountryField = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));

        act.sendKeys(selectCountryField, "Germany").build().perform();
        driver.findElement(By.cssSelector("span > i")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String summaryText = driver.findElement(By.cssSelector(".hero-primary")).getText();
//        Assert.assertEquals(summaryText, "Thankyou for the order.".toUpperCase());
        Assert.assertTrue(summaryText.equalsIgnoreCase("Thankyou for the order."));

//        driver.close();
    };

    public  static void waitSpinner(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ngx-spinner-overlay")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.ngx-spinner-overlay")));
//        Assert.assertTrue(driver.findElement(By.cssSelector("div.ngx-spinner-overlay")).isDisplayed());
    }
}
