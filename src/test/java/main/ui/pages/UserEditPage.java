package main.ui.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserEditPage extends BasePage {
    public UserEditPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "edit-btn")
    private WebElement saveButton;
    @FindBy(id = "edit-email")
    private WebElement changeEmailInput;
    @FindBy(id = "check-role")
    private List<WebElement> roleCheckboxes;

    public UserEditPage clickOnSaveButton() {
        saveButton.click();
        return this;
    }

    public UserEditPage checkEmailIsFilled(String email) {
        assertTrue(changeEmailInput.getAttribute("value").equals(email));
        return this;
    }

    public UserEditPage typeEmail(String email) {
        changeEmailInput.clear();
        changeEmailInput.sendKeys(email);
        return this;
    }

    public UserEditPage selectUserRoles(String... roles) {
        for (String role : roles) {
            for (WebElement roleCheckbox : roleCheckboxes) {
                if (roleCheckbox.getAttribute("name").equalsIgnoreCase(role) && !roleCheckbox.isSelected()) {
                    roleCheckbox.click();
                }
            }
        }
        return this;
    }

    public UserEditPage deselectUserRoles(String... roles) {
        for (String role : roles) {
            for (WebElement roleCheckbox : roleCheckboxes) {
                if (roleCheckbox.getAttribute("name").equalsIgnoreCase(role) && roleCheckbox.isSelected()) {
                    roleCheckbox.click();
                }
            }
        }
        return this;
    }

    public UserEditPage checkActiveUserRoles(String... roles) {
        for (String role : roles) {
            for (WebElement roleCheckbox : roleCheckboxes) {
                if (roleCheckbox.getAttribute("name").equalsIgnoreCase(role)) {
                    assertTrue(roleCheckbox.isSelected());
                }
            }
        }
        return this;
    }

    public UserEditPage checkSaveButtonIsDisabled(boolean disabled) {
        if (disabled) {
            assertFalse(saveButton.isEnabled());
        } else {
            assertTrue(saveButton.isEnabled());
        }
        return this;
    }

    public AdminPage goBackToAdminPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.history.go(-2)");
        return new AdminPage(driver);
    }
}
