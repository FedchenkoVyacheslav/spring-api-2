package test.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

import static main.ui.util.testData.URL;

public class RegisterITCase extends BaseITCase {
    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should register new user")
    public void registerNewUser(String name, String surname, String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        params.put("email", email);

        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, true)
                .checkUrlIsValid(URL)
                .checkGreetings(name, surname)
                .checkNavbarEmailText(email, true)
                .checkCookie("JSESSIONID", true)
                .checkCookie("remember-me", true)
                .verifyParamsOfLastCreatedInstanceInDB("user", params)
                .removeLastCreatedInstance("user");
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#registerValidationTestData")
    @DisplayName("Should check validation errors on registration")
    public void checkValidationErrorsOnRegistration(String name, String surname, String email, String password, String validationError) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .checkErrorInForm("register", validationError);
    }
}
