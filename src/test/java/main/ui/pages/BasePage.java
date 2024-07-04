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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static main.ui.elements.ValidationForm.checkInvalidInputs;
import static org.junit.Assert.*;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCService.mysqlDataSource());
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    public static int getCurrentYear() {
        return Integer.parseInt(sdf.format(new Date()));
    }

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
    @FindBy(xpath = "//li[@class='nav-item']/a[text()='Admin dashboard']")
    private WebElement navBarAdminPage;
    @FindBy(xpath = "//li[@class='nav-item']/a[text()='Profile']")
    private WebElement navBarProfilePage;
    @FindBy(xpath = "//li[@class='nav-item']/a")
    private List<WebElement> navBarLinks;

    public LoginPage clickOnSignOut() {
        signOutButton.click();
        return new LoginPage(driver);
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
        input.sendKeys(Keys.CONTROL,"a");
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
        assertEquals(titleWords[1], name);
        assertEquals(titleWords[2], surname);
        return this;
    }

    public BasePage verifyParamsOfLastCreatedUserInDB(Map<String, String> params) {
        Integer id = jdbcTemplate.queryForObject("select id from user order by id desc limit 1", Integer.class);
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (param.getKey().equals("photo_url") && param.getValue() != null) {
                assertTrue(jdbcTemplate.queryForObject(String.format("select %s from user where id=%d", param.getKey(), id), String.class).contains(param.getValue()));
            } else {
                assertEquals(param.getValue(), jdbcTemplate.queryForObject(String.format("select %s from user where id=%d", param.getKey(), id), String.class));
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
}
