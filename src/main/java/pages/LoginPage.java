package pages;

import Reusable.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class LoginPage extends ReusableMethods {
    public By emailInput;
    private final By passwordInput;
    private final By buttonLogin;
    public final By invalidEmailWarning;
    private final By invalidCredentialsError;
    private final By missingPassword;
    private final By forgotPasswordBtn;
    public final By saveNewPasswordBtn;

    public LoginPage(WebDriver driver){
        super(driver);
        this.emailInput = By.id("userEmail");
        this.passwordInput = By.id("userPassword");
        this.buttonLogin = By.id("login");
        invalidEmailWarning = By.cssSelector("div[class='form-group'] > div.invalid-feedback > div");
        invalidCredentialsError = By.cssSelector("div[role='alert']");
        this.missingPassword = By.cssSelector("div.form-group.mb-4 > div.invalid-feedback > div");
        forgotPasswordBtn = By.cssSelector("div >  a.forgot-password-link");
        saveNewPasswordBtn = By.xpath("//button[@type='submit'][text()='Save New Password']");
    };

    public void enterEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    };

    public void enterPassword(String password){
        driver.findElement(passwordInput).sendKeys(password);
    };

    public void clickLogin(){
        driver.findElement(buttonLogin).click();
    }

    public ProductCatalog loginFlow(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        try {
            Thread.sleep(Duration.ofSeconds(4));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ProductCatalog(driver);
    }

    public Boolean isMissingEmailErrorDisplayed(){
        return driver.findElement(invalidEmailWarning).isDisplayed();
    }

    public String getMissingEmailText(){
        WebElement emailError = driver.findElement(invalidEmailWarning);
        waitForElementToAppears(emailError);
        return emailError.getText();
    }

    public Boolean invalidCredentialsAlertDisplayed(){
        waitForElementToAppears(driver.findElement(invalidCredentialsError));
        return driver.findElement(invalidCredentialsError).isDisplayed();
    }

    public String getInvalidCredText(){
        return driver.findElement(invalidCredentialsError).getText();
    }

    public String getMissingPasswordText() {
        return driver.findElement(missingPassword).getText();
    }

    public Boolean isMissingPasswordErrorDisplayed(){
        waitForElementToAppearsByLocator(missingPassword);
        return driver.findElement(missingPassword).isDisplayed();
    }

    public void clickForgetPassword() {
        driver.findElement(forgotPasswordBtn).click();
    }
}
