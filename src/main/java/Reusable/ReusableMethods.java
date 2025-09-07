package Reusable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ReusableMethods {

    public WebDriver driver;
    public WebDriverWait wait;
    private final By spinner;

    public ReusableMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        spinner = By.cssSelector("div.ngx-spinner-overlay");
    }

    public void waitForElementToAppears(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToAppearsByLocator(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForAllElementToAppears(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForSpecificUrl(String link) {
        wait.until(ExpectedConditions.urlContains(link));
    }

    public void waitSpinner() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(spinner));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }

}
