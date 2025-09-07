package tests;

import Reusable.BaseTestAI;
import Reusable.Retry;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class LoginTestBaseAI extends BaseTestAI {

    private Main main;

    @Test(retryAnalyzer = Retry.class)
    public void loginIsDone(){
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://rahulshettyacademy.com/client/#/auth/login");
    }

}
