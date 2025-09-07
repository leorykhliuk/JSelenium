package pages;

import org.openqa.selenium.WebDriver;

public class Main {

    public WebDriver driver;

    public Main(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(){
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }
}
