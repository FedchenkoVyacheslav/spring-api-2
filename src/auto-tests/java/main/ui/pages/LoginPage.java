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

    @FindBy(xpath = "//input[@id='auth-email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='auth-password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@id='auth-button']")
    private WebElement logInButton;
    @FindBy(xpath = "//input[@id='auth-remember']")
    private WebElement rememberMeCheck;

    @FindBy(xpath = "//div[@id='auth-error']")
    private WebElement authErrorMessage;

    @FindBy(xpath = "//a[@class='auth-link']")
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

    public LoginPage checkErrorInLoginForm(String message) {
        checkInvalidMessage("auth", message);
        checkInvalidInputs(driver, "auth", true);
        clearInvalidInput("auth");
        checkInvalidInputs(driver, "auth", false);
        return this;
    }

    public LoginPage checkErrorForNotExistedUser() {
        assertEquals(authErrorMessage.getText(), "That combination of email and password is not recognized");
        checkInvalidInputs(driver, "auth", true);
        return this;
    }
}
