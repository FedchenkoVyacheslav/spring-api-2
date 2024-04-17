package main.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "auth-email")
    private WebElement emailInput;
    @FindBy(id = "auth-password")
    private WebElement passwordInput;
    @FindBy(id = "auth-name")
    private WebElement nameInput;
    @FindBy(id = "auth-surname")
    private WebElement surnameInput;
    @FindBy(id = "auth-button")
    private WebElement signUpButton;

    public RegistrationPage clickOnSignUp() {
        signUpButton.click();
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public RegistrationPage typePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public RegistrationPage typeName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    public RegistrationPage typeSurname(String surname) {
        surnameInput.sendKeys(surname);
        return this;
    }

    public LoginPage register(String name, String surname, String email, String password) {
        this.typeName(name);
        this.typeSurname(surname);
        this.typeEmail(email);
        this.typePassword(password);
        clickOnSignUp();
        return new LoginPage(driver);
    }
}
