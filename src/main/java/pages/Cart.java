package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart {
    private final WebDriver driver;
    private final By cartProductName;
    public final By checkoutBtn;

    public Cart(WebDriver driver){
        this.driver = driver;
        this.cartProductName = By.cssSelector(".cartSection > h3");
        this.checkoutBtn = By.xpath("//button[text()='Checkout']");
    }

    public Boolean productExistsInCart(String productName){
        return driver.findElements(cartProductName).stream()
                .anyMatch((item) -> item.getText().equalsIgnoreCase(productName));
    }

    public void clickCheckoutBtn(){
        driver.findElement(checkoutBtn).click();
    }
}
