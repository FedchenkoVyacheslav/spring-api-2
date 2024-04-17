package test.ui;

import main.ui.actions.PrepareDriver;
import main.ui.pages.RegistrationPage;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static main.ui.util.testData.URL;

public class RegisterITCase {
    static WebDriver driver;
    RegistrationPage myRegistrationPage;

    @BeforeEach
    public void setup() {
        driver = PrepareDriver.driverInit("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL + "registration");
        myRegistrationPage = new RegistrationPage(driver);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should register new user")
    public void registerNewUser(String name, String surname, String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("surname", surname);
        params.put("email", email);

        myRegistrationPage
                .register(name, surname, email, password)
                .loginWithCredential(email, password, true)
                .checkUrlIsValid(URL)
                .checkGreetings(name, surname)
                .pause(2500)
                .checkUrlIsValid(URL + "main")
                .checkNavbarEmailText(email, true)
                .checkCookie("JSESSIONID", true)
                .checkCookie("remember-me", true)
                .verifyParamsOfLastCreatedUserInDB(params);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#registerValidationTestData")
    @DisplayName("Should check validation errors on registration")
    public void checkValidationErrorsOnRegistration(String name, String surname, String email, String password, String validationError) {
        myRegistrationPage
                .register(name, surname, email, password)
                .checkErrorInForm("register", validationError);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
