package main.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='auth-email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='auth-password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@id='auth-name']")
    private WebElement nameInput;
    @FindBy(xpath = "//input[@id='auth-surname']")
    private WebElement surnameInput;
    @FindBy(xpath = "//button[@id='auth-button']")
    private WebElement signUpButton;
    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border' and @role='presentation']")
    private WebElement reCaptcha;
    @FindBy(xpath = "//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")
    private WebElement reCaptchaIframe;

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

    public RegistrationPage checkReCaptcha() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(reCaptchaIframe));
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(reCaptcha)).click();
        pause(2000);
        driver.switchTo().defaultContent();
        return this;
    }

    public LoginPage register(String email, String password, String name, String surname, boolean reCaptcha) {
        this.typeEmail(email);
        this.typePassword(password);
        this.typeName(name);
        this.typeSurname(surname);
        this.typePassword(password);
        if (reCaptcha) this.checkReCaptcha();
        clickOnSignUp();
        return new LoginPage(driver);
    }
}
