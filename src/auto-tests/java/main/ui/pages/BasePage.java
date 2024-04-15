package main.ui.pages;

import main.ui.elements.ValidationForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public abstract class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[text()='Sign out']")
    private WebElement signOutButton;

    @FindBy(xpath = "//li[@class='nav-email']/div")
    private WebElement navbarEmail;

    public static String getUserEmail(String name, String surname) {
        return String.format("%s.%s@gmail.com", name, surname).toLowerCase();
    }

    public BasePage clickOnSignOut() {
        signOutButton.click();
        return this;
    }

    public LoginPage checkNavbarEmailText(String email, boolean loggedIn) {
        if (loggedIn) {
            assertEquals(navbarEmail.getText(), email);
        } else {
            assertEquals(navbarEmail.getText(), "Not logged in");
        }
        return new LoginPage(driver);
    }

    public BasePage checkUrlIsValid(String url) {
        assertEquals(driver.getCurrentUrl(), url);
        return this;
    }

    public BasePage pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public BasePage checkCookie(String cookie, boolean exists) {
        if (exists) {
            assertNotNull(driver.manage().getCookieNamed(cookie));
        } else {
            assertNull(driver.manage().getCookieNamed(cookie));
        }
        return this;
    }

    public BasePage checkInvalidMessage(String formClass, String innerText) {
        String message = ValidationForm.getInvalidMessage(driver, formClass);
        assertEquals(innerText, message);
        return this;
    }

    public BasePage clearInvalidInput(String formName) {
        WebElement input = driver.findElement(By.xpath("//form[@name='" + formName + "']//div[@class='invalid-feedback']/../input"));
        input.sendKeys(" ");
        input.clear();
        return this;
    }

}
