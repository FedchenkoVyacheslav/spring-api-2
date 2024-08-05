package test.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static main.ui.util.testData.*;

public class SubscriptionsITCase extends BaseITCase {
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
                .switchToMessagesPageFromMessageLink(email)
                .subscribeToUser()
                .checkFollowersCount(1)
                .switchToFollowersPage()
                .checkFollowersPageTitle(name)
                .checkFollowersCount(1)
                .checkFollowerExistsInList(ADMIN_EMAIL)
                .switchToMainPage()
                .switchToMessagesPageFromMessageLink(email)
                .unsubscribeFromUser()
                .checkFollowersCount(0)
                .switchToFollowersPage()
                .checkThatFollowersListIsEmpty()
                .removeLastCreatedInstance("user");
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
                .switchToLastPage()
                .switchToMessagesPageFromMessageLink(USER_EMAIL)
                .subscribeToUser()
                .clickOnSignOut()
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .switchToMessagesPageFromMessageLink(email)
                .switchToSubscriptionsPage()
                .checkSubscriptionsPageTitle(name)
                .subscribeToUser(USER_EMAIL)
                .switchToMessagesPage()
                .switchToSubscriptionsPage()
                .checkSubscriptionInList(USER_EMAIL, true)
                .unsubscribeFromUser(USER_EMAIL)
                .checkSubscriptionInList(USER_EMAIL, false)
                .removeLastCreatedInstance("user");
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should subscribe to user and unsubscribe from user on Followers page")
    public void subscribeAndUnsubscribeOnFollowersPage(String name, String surname, String email, String password) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, false)
                .switchToMainPage()
                .switchToLastPage()
                .switchToMessagesPageFromMessageLink(USER_EMAIL)
                .subscribeToUser()
                .clickOnSignOut()
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .switchToLastPage()
                .switchToMessagesPageFromMessageLink(USER_EMAIL)
                .switchToFollowersPage()
                .subscribeToUser(email)
                .switchToMessagesPage()
                .switchToSubscriptionsPage()
                .checkSubscriptionInList(email, true)
                .switchToMainPage()
                .switchToLastPage()
                .switchToMessagesPageFromMessageLink(USER_EMAIL)
                .switchToFollowersPage()
                .unsubscribeFromUser(email)
                .switchToMessagesPage()
                .switchToSubscriptionsPage()
                .checkSubscriptionInList(email, false)
                .removeLastCreatedInstance("user");
    }
}
