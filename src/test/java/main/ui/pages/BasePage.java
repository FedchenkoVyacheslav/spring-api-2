package main.ui.pages;

import main.ui.elements.ValidationForm;
import main.ui.util.JDBCService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static main.ui.elements.ValidationForm.checkInvalidInputs;
import static org.junit.Assert.*;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCService.mysqlDataSource());

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[text()='Sign out']")
    private WebElement signOutButton;
    @FindBy(className = "nav-email")
    private WebElement navbarEmail;
    @FindBy(className = "greeting-title")
    private WebElement greetingTitle;
    @FindBy(linkText = "Admin dashboard")
    private WebElement navBarAdminPage;
    @FindBy(linkText = "Profile")
    private WebElement navBarProfilePage;
    @FindBy(linkText = "Spring-boot App")
    private WebElement navBarMainPage;
    @FindBy(linkText = "My messages")
    private WebElement navBarMessagesPage;
    @FindBy(className = "nav-item")
    private List<WebElement> navBarLinks;

    public LoginPage clickOnSignOut() {
        signOutButton.click();
        return new LoginPage(driver);
    }

    public LoginPage checkNavbarEmailText(String email, boolean loggedIn) {
        if (loggedIn) {
            assertEquals(email, navbarEmail.getText());
        } else {
            assertEquals("Not logged in", navbarEmail.getText());
        }
        return new LoginPage(driver);
    }

    public BasePage checkUrlIsValid(String url) {
        assertEquals(url, driver.getCurrentUrl());
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
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(Keys.DELETE);
        return this;
    }

    public BasePage checkErrorInForm(String formName, String message) {
        checkInvalidMessage(formName, message);
        checkInvalidInputs(driver, formName, true);
        clearInvalidInput(formName);
        checkInvalidInputs(driver, formName, false);
        return this;
    }

    public BasePage checkGreetings(String name, String surname) {
        String title = greetingTitle.getText();
        String[] titleWords = title.substring(0, title.length() - 1).split(" ");
        assertEquals(name, titleWords[1]);
        assertEquals(surname, titleWords[2]);
        return this;
    }

    public BasePage verifyParamsOfLastCreatedInstanceInDB(String instanceName, Map<String, String> params) {
        Integer id = null;
        if (instanceName.equals("user")) {
            id = jdbcTemplate.queryForObject("select id from user order by id desc limit 1", Integer.class);
        } else if (instanceName.equals("message")) {
            id = jdbcTemplate.queryForObject("select id from message order by id desc limit 1", Integer.class);
        }

        for (Map.Entry<String, String> param : params.entrySet()) {
            if ((param.getKey().equals("photo_url") || param.getKey().equals("filename")) && param.getValue() != null) {
                assertTrue(jdbcTemplate.queryForObject(String.format("select %s from %s where id=%d", param.getKey(), instanceName, id), String.class).contains(param.getValue()));
            } else {
                assertEquals(param.getValue(), jdbcTemplate.queryForObject(String.format("select %s from %s where id=%d", param.getKey(), instanceName, id), String.class));
            }
        }
        return this;
    }

    public AdminPage switchToAdminPage() {
        navBarAdminPage.click();
        return new AdminPage(driver);
    }

    public ProfilePage switchToProfilePage() {
        navBarProfilePage.click();
        return new ProfilePage(driver);
    }

    public MainPage switchToMainPage() {
        navBarMainPage.click();
        return new MainPage(driver);
    }

    public MessagesPage switchToMessagesPage() {
        navBarMessagesPage.click();
        return new MessagesPage(driver);
    }

    public BasePage checkNavBarContainsLink(String link, boolean containLink) {
        List<String> linksText = new ArrayList<>();
        for (WebElement navBarLink : navBarLinks) {
            linksText.add(navBarLink.getText());
        }
        if (containLink) {
            assertTrue(linksText.contains(link));
        } else {
            assertFalse(linksText.contains(link));
        }
        return this;
    }

    public BasePage switchToPreviousPage() {
        driver.navigate().back();
        return this;
    }
}
