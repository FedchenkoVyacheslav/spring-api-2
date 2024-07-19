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

public class SubscriptionsITCase {
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
    @DisplayName("Should subscribe to user")
    public void subscribeToUser(String name, String surname, String email, String password) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, false)
                .switchToMainPage()
                .expandSendMessageForm()
                .sendMessage(TITLE, TEXT)
                .clickOnSignOut()
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .switchToMessagesPageFromMessageLink()
                .subscribeToUser()
                .checkFollowersCount(1)
                .switchToFollowersPage()
                .checkFollowersPageTitle(name)
                .checkFollowersCount(1)
                .checkFollowerExistsInList(ADMIN_EMAIL);
    }


    @AfterEach
    public void quit() {
        driver.quit();
    }
}
