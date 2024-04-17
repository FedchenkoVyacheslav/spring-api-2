package test.ui;

import main.ui.actions.PrepareDriver;
import main.ui.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static main.ui.util.testData.URL;

public class AdminITCase {
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
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should check filter for users list")
    public void checkFilterForUsers(String name, String surname, String email, String password) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, false)
                .pause(2500)
                .checkNavBarContainsLink("Admin dashboard", false)
                .clickOnSignOut()
                .loginWithCredential("t1@gmail.com", "1111", false)
                .pause(2500)
                .checkNavBarContainsLink("Admin dashboard", true)
                .switchToAdminPage()
                .checkCountOfUsersOnPage(10)
                .findUser(email)
                .checkUserInList(email, true)
                .checkCountOfUsersOnPage(1)
                .findUser("11111")
                .checkMessageForEmptyList()
                .findUser("")
                .checkCountOfUsersOnPage(10);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
