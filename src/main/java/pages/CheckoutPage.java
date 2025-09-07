package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CheckoutPage {
    public final WebDriver driver;
    private final By selectCountryField = By.cssSelector("input[placeholder='Select Country']");;
    private final By countryFirstOption = By.cssSelector("span > i");
    private final By placeOrderbtn = By.cssSelector(".action__submit");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    private void clickFirstOption(){
        driver.findElement(countryFirstOption).click();
    }

    public void clickPlaceOrder(){
        driver.findElement(placeOrderbtn).click();
    }

    public void selectCountry(String countryName){
        Actions act = new Actions(driver);
        act.sendKeys(driver.findElement(selectCountryField), countryName).build().perform();
        clickFirstOption();
    }

}
