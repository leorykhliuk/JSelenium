package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private WebDriver driver;
    private final By miniCart;
    private final By ordersBtn;

    public  Header(WebDriver driver){
        this.driver = driver;
        this.miniCart = By.cssSelector("button[routerlink='/dashboard/cart'] label");
        this.ordersBtn = By.cssSelector("button[routerlink='/dashboard/myorders']");
    }

    public String getMiniCartValue(){
        return driver.findElement(miniCart).getText();
    }

    public void clickCartButton(){
        driver.findElement(miniCart).click();
    }

    public void clickOrdersBtn(){
        this.driver.findElement(ordersBtn).click();
    }
}
