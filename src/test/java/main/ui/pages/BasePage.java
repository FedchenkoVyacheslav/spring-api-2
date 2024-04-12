package main.ui.pages;

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

    public BasePage clickOnSignOut() {
        signOutButton.click();
        return this;
    }

    public LoginPage checkNavbarEmailText(String email, boolean loggedIn) {
        if(loggedIn) {
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
}
