package tests;

import Reusable.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

public class LoginWithReport extends BaseTest {

    @Test
    public void wrongCredentialsError(){
        ExtentReports extent = ExtentReportManager.getReportObject();
        ExtentTest test = extent.createTest("Test Name");
        loginPage.enterEmail("random@email.com");
        loginPage.enterPassword("pass");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.invalidCredentialsAlertDisplayed());
        Assert.assertTrue(loginPage.getInvalidCredText().equalsIgnoreCase("Incorrect email or password."));
        driver.close();
        test.fail("something wrong");
        extent.flush();
    }

    @Test
    public void emptyPassword() throws InterruptedException {
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isMissingPasswordErrorDisplayed());
        Assert.assertTrue(loginPage.getMissingPasswordText().equalsIgnoreCase("*Password is required"));
    }

    @Test
    public void emptyEmail() throws InterruptedException {
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isMissingEmailErrorDisplayed());
        Assert.assertTrue(loginPage.getMissingEmailText().equalsIgnoreCase("*Email is required"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
