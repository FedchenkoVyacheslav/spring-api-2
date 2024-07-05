package main.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//button[@aria-controls='messageForm']")
    private WebElement messageButton;
    @FindBy(id = "message-title")
    private WebElement messageTitleInput;
    @FindBy(id = "message-text")
    private WebElement messageTextInput;
    @FindBy(id = "formFile")
    private WebElement messageImageInput;
    @FindBy(id = "message-btn")
    private WebElement addMessageButton;
    @FindBy(className = "custom-file-label")
    private WebElement photoInputLabel;

    public MainPage expandSendMessageForm () {
        assertEquals(messageButton.getText(), "Add new message");
        messageButton.click();
        assertEquals(messageButton.getText(), "Hide message form");
        return this;
    }

    public MainPage typeTitle(String title) {
        messageTitleInput.sendKeys(title);
        return this;
    }

    public MainPage typeText(String text) {
        messageTextInput.sendKeys(text);
        return this;
    }

    public MainPage uploadPhoto(String path) {
        File file = new File(path);
        messageImageInput.sendKeys(file.getAbsolutePath());
        return this;
    }

    public MainPage clickOnAddNewMessageButton () {
        addMessageButton.click();
        return this;
    }

    public MainPage checkPhotoLabel(String text) {
        assertEquals(photoInputLabel.getText(), String.format("%s...", text));
        return this;
    }

    public MainPage sendMessage(String title, String text, String path) {
        this.expandSendMessageForm();
        this.typeTitle(title);
        this.typeText(text);
        this.uploadPhoto(path);
        this.checkPhotoLabel(Paths.get(path).getFileName().toString());
        clickOnAddNewMessageButton();
        return this;
    }
}
