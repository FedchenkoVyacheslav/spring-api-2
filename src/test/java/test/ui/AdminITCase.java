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

import static main.ui.util.testData.*;

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
                .checkNavBarContainsLink("Admin dashboard", false)
                .clickOnSignOut()
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .checkNavBarContainsLink("Admin dashboard", true)
                .switchToAdminPage()
                .checkCountOfUsersOnPage(10)
                .findUser(email)
                .checkUserInList(email, true)
                .checkCountOfUsersOnPage(1)
                .findUser(INVALID_EMAIL)
                .checkMessageForEmptyList()
                .findUser("")
                .checkCountOfUsersOnPage(10);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#admin")
    @DisplayName("Should check changing the number of displayed users on the page")
    public void checkChangingNumberOfUsersPerPage(String email, String password) {
        myLoginPage
                .loginWithCredential(email, password, false)
                .switchToAdminPage()
                .checkCountOfUsersOnPage(10)
                .changeUsersCountPerPage(20)
                .checkCountOfUsersOnPage(20)
                .changeUsersCountPerPage(10)
                .checkCountOfUsersOnPage(10);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should check that last created user on last page of list")
    public void checkPageOfLastCreatedUser(String name, String surname, String email, String password) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, false)
                .clickOnSignOut()
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToAdminPage()
                .checkUserInList(email, false)
                .switchToLastPage()
                .checkUserInList(email, true);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
