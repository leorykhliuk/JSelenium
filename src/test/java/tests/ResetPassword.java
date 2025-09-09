package tests;

import Reusable.BaseTest;
import Reusable.BaseTestAI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetPassword extends BaseTestAI {

    @Test
    public void resetPasswordPageReached() {
        loginPage.clickForgetPassword();
        Assert.assertFalse(getDriver().findElement(loginPage.saveNewPasswordBtn).isDisplayed(),
                "Save New Password button is not displayed on the page");
    }
}
