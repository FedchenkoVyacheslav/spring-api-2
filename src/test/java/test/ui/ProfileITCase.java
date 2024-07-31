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
import java.util.HashMap;
import java.util.Map;

import static main.ui.util.testData.*;

public class ProfileITCase {
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
    @MethodSource("main.ui.util.testData#editProfileTestData")
    @DisplayName("Should update user profile")
    public void changeUserProfileData(String name, String surname, String email, String password,
                                      String newName, String newSurname, String location, String age, String path) {
        Map<String, String> paramsAfterReg = new HashMap<>();
        paramsAfterReg.put("name", name);
        paramsAfterReg.put("surname", surname);
        paramsAfterReg.put("location", null);
        paramsAfterReg.put("age", null);
        paramsAfterReg.put("photo_url", null);

        Map<String, String> paramsAfterUpdate = new HashMap<>();
        paramsAfterUpdate.put("name", newName);
        paramsAfterUpdate.put("surname", newSurname);
        paramsAfterUpdate.put("location", location);
        paramsAfterUpdate.put("age", age);
        paramsAfterUpdate.put("photo_url", path.split("/")[1]);

        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, true)
                .checkUrlIsValid(URL)
                .verifyParamsOfLastCreatedInstanceInDB("user", paramsAfterReg)
                .switchToProfilePage()
                .checkProfileData(name, surname, "", "", "profile-empty")
                .saveProfileData(newName, newSurname, location, age, path)
                .checkProfileData(newName, newSurname, location, age, "admin")
                .verifyParamsOfLastCreatedInstanceInDB("user", paramsAfterUpdate)
                .removeLastCreatedInstance("user");
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#editProfileValidationTestData")
    @DisplayName("Should check validation errors on profile update")
    public void checkValidationErrorsOnProfileUpdate(String newName, String newSurname, String location, String age, String validationError) {
        myLoginPage
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, true)
                .checkUrlIsValid(URL)
                .switchToProfilePage()
                .saveProfileData(newName, newSurname, location, age)
                .checkErrorInForm("profile", validationError);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
