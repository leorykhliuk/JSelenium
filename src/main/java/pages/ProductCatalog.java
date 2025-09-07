package pages;

import Reusable.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductCatalog {
    WebDriver driver;
    private final ReusableMethods reusable;
    private final By addToCart;

    public ProductCatalog(WebDriver driver){
        this.driver = driver;
        this.reusable = new ReusableMethods(driver);
        this.addToCart = By.xpath(".//button[contains(., 'Add To Cart')]");
    }

    public List<WebElement> getProductList() {
        List<WebElement> productCards = driver.findElements(By.cssSelector("div.card"));
        reusable.waitForAllElementToAppears(productCards);
        return productCards;
    }

    public WebElement getProductByName(String productName){
        return this.getProductList().stream().filter(product -> product.findElement(By.tagName("b"))
                .getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
//        System.out.println(product.getText());
        product.findElement(addToCart).click();
        reusable.waitSpinner();
    }
}
