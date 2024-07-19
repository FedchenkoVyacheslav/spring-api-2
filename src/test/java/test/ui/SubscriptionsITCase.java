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
    @DisplayName("Should subscribe to user and unsubscribe from user on Messages page")
    public void subscribeAndUnsubscribeOnMessagesPage(String name, String surname, String email, String password) {
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
                .checkFollowerExistsInList(ADMIN_EMAIL)
                .switchToMainPage()
                .switchToMessagesPageFromMessageLink()
                .unsubscribeFromUser()
                .checkFollowersCount(0)
                .switchToFollowersPage()
                .checkThatFollowersListIsEmpty();
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should subscribe to user and unsubscribe from user on Subscriptions page")
    public void subscribeAndUnsubscribeOnSubscriptionsPage(String name, String surname, String email, String password) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, false)
                .switchToMainPage()
                .expandSendMessageForm()
                .sendMessage(TITLE, TEXT)
                .changeCountOfMessagesPerPage(12)
                .switchToLastPage()
                .switchToMessagesPageFromMessageLink(USER_EMAIL)
                .subscribeToUser()
                .clickOnSignOut()
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .switchToMessagesPageFromMessageLink(email)
                .switchToSubscriptionsPage()
                .subscribeToUser(USER_EMAIL)
                .switchToMessagesPage()
                .switchToSubscriptionsPage()
                .checkSubscriptionInList(USER_EMAIL, true)
                .unsubscribeFromUser(USER_EMAIL)
                .checkSubscriptionInList(USER_EMAIL, false);
    }


    @AfterEach
    public void quit() {
        driver.quit();
    }
}
