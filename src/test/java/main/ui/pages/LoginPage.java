package main.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static main.ui.elements.ValidationForm.checkInvalidInputs;
import static org.junit.Assert.*;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "auth-email")
    private WebElement emailInput;
    @FindBy(id = "auth-password")
    private WebElement passwordInput;
    @FindBy(id = "auth-button")
    private WebElement logInButton;
    @FindBy(id = "auth-remember")
    private WebElement rememberMeCheck;
    @FindBy(id = "auth-error")
    private WebElement authErrorMessage;
    @FindBy(className = "auth-link")
    private WebElement registerLink;

    public LoginPage clickOnLogIn() {
        logInButton.click();
        return this;
    }

    public RegistrationPage switchToRegisterPage() {
        registerLink.click();
        return new RegistrationPage(driver);
    }

    public LoginPage typeEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage checkRememberMe() {
        rememberMeCheck.click();
        return this;
    }

    public LoginPage typePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage loginWithCredential(String email, String password, boolean rememberMe) {
        this.typeEmail(email);
        this.typePassword(password);
        if (rememberMe) this.checkRememberMe();
        clickOnLogIn();
        return this;
    }

    public LoginPage checkErrorForNotExistedUser() {
        assertEquals("That combination of email and password is not recognized", authErrorMessage.getText());
        checkInvalidInputs(driver, "auth", true);
        return this;
    }
}
