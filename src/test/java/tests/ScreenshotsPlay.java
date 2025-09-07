package tests;

import Reusable.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotsPlay extends BaseTest {

    public static void takeElementScreenshot(WebElement element, String fileName) throws IOException {
        File screenshot = element.getScreenshotAs(OutputType.FILE);
        Path destination = Path.of("src/main/java/screenshots", fileName + ".png");
        Files.copy(screenshot.toPath(), destination);
    }

    @Test
    public void getScreen() throws IOException {
        takeElementScreenshot(driver.findElement(By.id("login")), "loginBattton");
    }
    //    public void getScreenshot(){
//        TakesScreenshot tsc = (TakesScreenshot) driver;
//        File source = tsc.getScreenshotAs(OutputType.FILE);
//        Path destination = Paths.get("src/main/java/screenshots");
//
//    }
    
}
