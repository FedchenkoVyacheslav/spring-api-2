package main.ui.pages;

import main.ui.elements.ValidationForm;
import main.ui.util.JDBCService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.jdbc.core.JdbcTemplate;

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
    @FindBy(xpath = "//li[@class='nav-email']/div")
    private WebElement navbarEmail;
    @FindBy(xpath = "//h1[@class='greeting-title']")
    private WebElement greetingTitle;

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
        assertEquals(titleWords[1], name);
        assertEquals(titleWords[2], surname);
        return this;
    }

    public BasePage verifyParamsOfLastCreatedUserInDB(Map<String, String> params) {
        Integer id = jdbcTemplate.queryForObject("select id from user order by id desc limit 1", Integer.class);
        for (Map.Entry<String, String> param : params.entrySet()) {
            assertEquals(param.getValue(), jdbcTemplate.queryForObject(String.format("select %s from user where id=%d", param.getKey(), id), String.class));
        }
        return this;
    }
}
