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

public class UserEditITCase {
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
    @DisplayName("Should check changing of user roles")
    public void checkChangeOfUserRoles(String name, String surname, String email, String password) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToAdminPage()
                .switchToLastPage()
                .checkUserRole(email, "User")
                .switchToUserEditPage(email)
                .checkSaveButtonIsDisabled(false)
                .checkActiveUserRoles("User")
                .deselectUserRoles("User")
                .checkSaveButtonIsDisabled(true)
                .selectUserRoles("User", "Admin")
                .checkActiveUserRoles("User", "Admin")
                .clickOnSaveButton()
                .goBackToAdminPage()
                .checkUserRole(email, "User", "Admin")
                .switchToUserEditPage(email)
                .deselectUserRoles("User")
                .checkActiveUserRoles("Admin")
                .clickOnSaveButton()
                .goBackToAdminPage()
                .checkUserRole(email,  "Admin");
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
