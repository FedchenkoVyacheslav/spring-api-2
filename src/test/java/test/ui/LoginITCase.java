package test.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static main.ui.util.testData.*;

public class LoginITCase extends BaseITCase{
    @ParameterizedTest
    @MethodSource("main.ui.util.testData#admin")
    @DisplayName("Should login user")
    public void loginUser(String email, String password) {
        myLoginPage
                .checkNavbarEmailText(email, false)
                .loginWithCredential(email, password, false)
                .checkUrlIsValid(URL)
                .checkNavbarEmailText(email, true)
                .checkCookie("JSESSIONID", true)
                .checkCookie("remember-me", false);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#admin")
    @DisplayName("Should login user with Remember me")
    public void loginAndLogoutUserWithRememberMe(String email, String password) {
        myLoginPage
                .loginWithCredential(email, password, true)
                .checkUrlIsValid(URL)
                .checkNavbarEmailText(email, true)
                .checkCookie("JSESSIONID", true)
                .checkCookie("remember-me", true)
                .clickOnSignOut()
                .checkCookie("remember-me", false)
                .checkUrlIsValid(URL + "login?logout")
                .checkNavbarEmailText(email, false);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#loginValidationTestData")
    @DisplayName("Should check validation errors on login")
    public void checkValidationErrorsOnLogin(String email, String password, String validationError) {
        myLoginPage
                .loginWithCredential(email, password, true)
                .checkErrorInForm("auth", validationError);
    }

    @ParameterizedTest
    @CsvSource({"test@test.com, 1"})
    @DisplayName("Should check validation errors if user not exists")
    public void checkValidationErrorIfUserNotExists(String email, String password) {
        myLoginPage
                .clickOnLogIn()
                .loginWithCredential(email, password, true)
                .checkErrorForNotExistedUser();
    }
}