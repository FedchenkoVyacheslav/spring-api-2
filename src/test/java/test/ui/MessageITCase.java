package test.ui;

import main.ui.actions.PrepareDriver;
import main.ui.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static main.ui.util.testData.URL;

public class MessageITCase {
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
    @DisplayName("Should add new message")
    public void addNewMessage() {
    }

    @ParameterizedTest
    @DisplayName("Should add new message without image")
    public void addNewMessageWithoutImage() {
    }

    @ParameterizedTest
    @DisplayName("Should check validation errors when adding a message")
    public void checkValidationErrorsOnAddingMessage() {
    }

    @ParameterizedTest
    @DisplayName("Should check search of message")
    public void checkSearchOfMessage() {
    }

    @ParameterizedTest
    @DisplayName("Should check likes of message")
    public void checkMessageLikes() {
    }

    @ParameterizedTest
    @DisplayName("Should check the message change")
    public void checkMessageChange() {
    }

    @ParameterizedTest
    @DisplayName("Should check the message deletion")
    public void checkMessageDeletion() {
    }
    @ParameterizedTest
    @DisplayName("Should check changing the number of displayed messages on Main page")
    public void checkChangingNumberOfMessagesPerPageOnMainPage() {}

    @ParameterizedTest
    @DisplayName("Should check changing the number of displayed messages on My messages page")
    public void checkChangingNumberOfMessagesPerPageOnMessagesPage() {
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
