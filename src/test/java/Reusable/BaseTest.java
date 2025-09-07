package Reusable;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LoginPage loginPage;
    private String baseURL;

    public WebDriver initializeDriver() throws IOException {

        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/Global.properties");
        props.load(fis);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                : props.getProperty("browser");
//        String browserName = props.getProperty("browser");
        baseURL = props.getProperty("baseUrl");

        if (browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.manage().window().maximize();
        System.out.println("BrowserNameIs: " + browserName);
        return driver;
    }

    @BeforeMethod
    public void launchApplication() throws IOException {
        driver = initializeDriver();
        loginPage = new LoginPage(driver);
        driver.get(baseURL);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
//        Path destination = Path.of("src/main/java/screenshots", testCaseName + ".png");
        Path destination = Path.of(System.getProperty("user.dir"), "reports", "screenshots",
                testCaseName + ".png");
        //create folder if it's missing
        Files.createDirectories(destination.getParent());
        //copy screenshot to destination
        Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
//        return destination.toUri().toString(); doesn't work as URL and absolute path doest fit the reporter
        return "screenshots/" + testCaseName + ".png";
    }
}
