package tests;

import Reusable.BaseTest;
import Reusable.BaseTestAI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginHandlingTest extends BaseTestAI {

    @Test
    public void wrongCredentialsError(){
        loginPage.enterEmail("random@email.com");
        loginPage.enterPassword("pass");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.invalidCredentialsAlertDisplayed());
        Assert.assertTrue(loginPage.getInvalidCredText().equalsIgnoreCase("Incorrect email or password."));
    }

    @Test
    public void emptyPassword() {
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isMissingPasswordErrorDisplayed());
        Assert.assertTrue(loginPage.getMissingPasswordText().equalsIgnoreCase("*Password is required"));
    }

    @Test
    public void emptyEmail() {
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isMissingEmailErrorDisplayed());
        Assert.assertTrue(loginPage.getMissingEmailText().equalsIgnoreCase("*Email is required"));
        Assert.assertEquals(loginPage.getMissingEmailText(), "*Email is required1");
    }
}
