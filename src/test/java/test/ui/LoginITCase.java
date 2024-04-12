package test.ui;

import main.ui.actions.PrepareDriver;
import main.ui.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static main.ui.util.testData.URL;

public class LoginITCase {
    static WebDriver driver;
    LoginPage myLoginPage;

    @BeforeEach
    public void setup() {
        driver = PrepareDriver.driverInit("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL + "login");
        myLoginPage = new LoginPage(driver);
    }

    @ParameterizedTest
    @CsvSource({"t1@gmail.com, 1111"})
    @DisplayName("Should login")
    public void loginUser(String email, String password) {
        myLoginPage
                .checkNavbarEmailText(email, false)
                .loginWithCredential(email, password, false)
                .checkUrlIsValid(URL)
                .pause(2500)
                .checkUrlIsValid(URL + "main")
                .checkNavbarEmailText(email, true)
                .checkCookie("JSESSIONID", true)
                .checkCookie("remember-me", false);
    }

    @ParameterizedTest
    @CsvSource({"t1@gmail.com, 1111"})
    @DisplayName("Should login user with Remember me")
    public void loginAndLogoutUserWithRememberMe(String email, String password) {
        myLoginPage
                .loginWithCredential(email, password, true)
                .checkUrlIsValid(URL)
                .pause(2500)
                .checkUrlIsValid(URL + "main")
                .checkNavbarEmailText(email, true)
                .checkCookie("JSESSIONID", true)
                .checkCookie("remember-me", true)
                .clickOnSignOut()
                .checkCookie("remember-me", false)
                .checkUrlIsValid(URL + "login?logout")
                .checkNavbarEmailText(email, false);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}