package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    private final WebDriver driver;
    private final By summaryText = By.cssSelector(".hero-primary");

    public ConfirmationPage(WebDriver driver){
        this.driver = driver;
    }

    public String getSummaryText(){
        return driver.findElement(summaryText).getText();
    }
}
