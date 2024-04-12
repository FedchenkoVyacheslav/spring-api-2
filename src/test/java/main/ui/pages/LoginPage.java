package main.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public BasePage clickOnLogIn() {
        logInButton.click();
        return this;
    }

    public BasePage typeEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public BasePage checkRememberMe() {
        rememberMeCheck.click();
        return this;
    }

    public BasePage typePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public BasePage loginWithCredential(String email, String password, boolean rememberMe) {
        this.typeEmail(email);
        this.typePassword(password);
        if (rememberMe) this.checkRememberMe();
        clickOnLogIn();
        return this;
    }
}
