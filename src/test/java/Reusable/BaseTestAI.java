package Reusable;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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

public class BaseTestAI {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public LoginPage loginPage;
    private String baseURL;

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public WebDriver initializeDriver() throws IOException {

        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/Global.properties");
        props.load(fis);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                : props.getProperty("browser");
        baseURL = props.getProperty("baseUrl");

        // ... your existing driver setup
        WebDriver drv;

        if (browserName.equalsIgnoreCase("chrome")){
            drv = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")){
            drv = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")){
            drv = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        drv.manage().window().maximize();
        setDriver(drv);  // store in ThreadLocal
        return drv;
    }

    @BeforeMethod
    public void launchApplication() throws IOException {
        WebDriver drv = initializeDriver();
        loginPage = new LoginPage(drv);
        drv.get(baseURL);
    }

    @AfterMethod
    public void tearDown(){
        getDriver().quit(); // close correct thread driver
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
