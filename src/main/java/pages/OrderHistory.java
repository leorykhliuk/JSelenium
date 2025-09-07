package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class OrderHistory {
    private WebDriver driver;
    public By orderCard;
    public By productNames;

    public OrderHistory(WebDriver driver){
        this.driver = driver;
        this.orderCard = By.cssSelector("tr[class='ng-star-inserted']");
        this.productNames = By.cssSelector("tbody > tr > td:nth-child(3)");
    }

    public boolean isTheOrdersInTheHistory(){
        int numberOfOrders = driver.findElements(orderCard).size();
        return numberOfOrders >= 1;
    }

    public String getProductNames(){
        return driver.findElements(productNames).getFirst().getText();
    }
}
