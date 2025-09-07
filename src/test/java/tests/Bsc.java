package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Bsc {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://t29594-s48733.stg1.mozu.com/");
    }
}
