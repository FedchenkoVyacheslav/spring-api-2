package test.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

import static main.ui.util.testData.*;

public class UserEditITCase extends BaseITCase {
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
                .checkUserRole(email, "Admin")
                .removeLastCreatedInstance("user");
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#editUserValidationTestData")
    @DisplayName("Should checking for validation errors when changing user email")
    public void checkValidationErrorsWhenChangingUserEmail(String email, String password, String newEmail, String validationError) {
        myLoginPage
                .loginWithCredential(email, password, false)
                .switchToAdminPage()
                .switchToUserEditPage(email)
                .checkEmailIsFilled(email)
                .typeEmail(newEmail)
                .clickOnSaveButton()
                .checkErrorInForm("admin", validationError);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should check changing of user email")
    public void checkChangeOfUserEmail(String name, String surname, String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("email", NEW_EMAIL);

        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToAdminPage()
                .switchToLastPage()
                .switchToUserEditPage(email)
                .typeEmail(NEW_EMAIL)
                .clickOnSaveButton()
                .goBackToAdminPage()
                .checkUserInList(NEW_EMAIL, true)
                .verifyParamsOfLastCreatedInstanceInDB("user", params)
                .removeLastCreatedInstance("user");
    }
}
