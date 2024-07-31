package main.ui.pages;

import main.ui.elements.Filter;
import main.ui.elements.Paginator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static main.ui.util.testData.TEXT;
import static main.ui.util.testData.TITLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "search-input")
    private WebElement searchInput;
    @FindBy(id = "search-button")
    private WebElement searchButton;
    @FindBy(id = "error-el2")
    private WebElement errorMessage;
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
    @FindBy(css = "div.card:nth-child(1)>img")
    private WebElement lastMessageImage;
    @FindBy(css = "div.card:nth-child(1) h5.card-title")
    private WebElement lastMessageTitle;
    @FindBy(css = "div.card:nth-child(1) span.card-text")
    private WebElement lastMessageText;
    @FindBy(css = "div.card:nth-child(1) div.card-author>span")
    private WebElement lastMessageAuthorName;
    @FindBy(css = "div.card:nth-child(1) div.card-author>a")
    private WebElement lastMessageAuthorEmail;
    @FindBy(css = "div.card:nth-child(1) div.card-author>p")
    private WebElement lastMessageDate;
    @FindBy(css = "div.card:nth-child(1) div.card-author>a.message-like")
    private WebElement lastMessageLikes;
    @FindBy(css = "div.card")
    private List<WebElement> messages;

    public MainPage expandSendMessageForm() {
        assertEquals("Add new message", messageButton.getText());
        messageButton.click();
        assertEquals("Hide message form", messageButton.getText());
        return this;
    }

    public MainPage typeTitle(String title) {
        messageTitleInput.clear();
        messageTitleInput.sendKeys(title);
        return this;
    }

    public MainPage typeText(String text) {
        messageTextInput.clear();
        messageTextInput.sendKeys(text);
        return this;
    }

    public MainPage uploadPhoto(String path) {
        File file = new File(path);
        messageImageInput.sendKeys(file.getAbsolutePath());
        return this;
    }

    public MainPage clickOnAddNewMessageButton() {
        addMessageButton.click();
        return this;
    }

    public MainPage checkPhotoLabel(String text) {
        assertEquals(String.format("%s...", text), photoInputLabel.getText());
        return this;
    }

    public MainPage getMessageImage(String fileName) {
        File path = new File(lastMessageImage.getAttribute("src"));
        assertTrue(path.getName().contains(fileName.split("/")[1]));
        return this;
    }

    public MainPage getMessageTitle(String title) {
        assertEquals(title, lastMessageTitle.getText());
        return this;
    }

    public MainPage getMessageText(String text) {
        assertEquals(text, lastMessageText.getText());
        return this;
    }

    public MainPage getMessageAuthorName(String authorName) {
        assertEquals(authorName, lastMessageAuthorName.getText());
        return this;
    }

    public MainPage getMessageAuthorEmail(String authorEmail) {
        assertEquals(authorEmail, lastMessageAuthorEmail.getText());
        return this;
    }

    public MainPage getMessageDate(String date) {
        assertEquals(date, lastMessageDate.getText());
        return this;
    }

    public MainPage getMessageLikes(int likes) {
        assertEquals(String.valueOf(likes), lastMessageLikes.getText());
        return this;
    }

    public MainPage sendMessage(String title, String text, String path) {
        this.typeTitle(title);
        this.typeText(text);
        this.uploadPhoto(path);
        this.checkPhotoLabel(Paths.get(path).getFileName().toString());
        clickOnAddNewMessageButton();
        return this;
    }

    public MainPage sendMessage(String title, String text) {
        this.typeTitle(title);
        this.typeText(text);
        clickOnAddNewMessageButton();
        return this;
    }

    public MainPage sentRandomMessages(int messagesCount) {
        for (int i = 0; i < messagesCount; i++) {
            this.expandSendMessageForm();
            this.sendMessage(TITLE, TEXT);
        }
        return this;
    }

    public String getDateOfLastCreatedMessage() {
        ZonedDateTime date = jdbcTemplate.queryForObject("select created_at from message order by id desc limit 1", ZonedDateTime.class);
        ZonedDateTime withLocalZone = date.withZoneSameInstant(ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").format(withLocalZone.toLocalDateTime());
    }

    public MainPage checkLastCreatedMessage(String path, String title, String text, String author, String email, int likes) {
        this.checkMessage(path, title, text, author, email, likes);
        this.getMessageDate(getDateOfLastCreatedMessage());
        return this;
    }

    public MainPage checkMessage(String path, String title, String text, String author, String email, int likes) {
        this.getMessageImage(path);
        this.getMessageTitle(title);
        this.getMessageText(text);
        this.getMessageAuthorName(author);
        this.getMessageAuthorEmail(email);
        this.getMessageLikes(likes);
        return this;
    }

    public MainPage checkCountOfMessagesOnPage(int count) {
        Assert.assertEquals(count, messages.size());
        return this;
    }

    public MainPage changeMessagesCountPerPage(int count) {
        Paginator.changeCountOfElementsPerPage(driver, count);
        return this;
    }

    public MainPage typeSearchQuery(String text) {
        Filter.search(driver, text);
        return this;
    }

    public MainPage clickOnSearchButton() {
        Filter.clickOnSearchButton(driver);
        return this;
    }

    public MainPage findTheMessage(String text) {
        this.typeSearchQuery(text);
        this.clickOnSearchButton();
        return this;
    }

    public MainPage checkErrorMessage(String text) {
        assertEquals(text, errorMessage.getText());
        return this;
    }

    public MainPage likeMessage() {
        lastMessageLikes.click();
        return this;
    }

    public MainPage dislikeMessage() {
        lastMessageLikes.click();
        return this;
    }

    public MainPage clickOnLastMessageAuthorEmail() {
        lastMessageAuthorEmail.click();
        return this;
    }

    public MessagesPage switchToMessagesPageFromMessageLink(String email) {
        driver.findElements(By.linkText(email)).getFirst().click();
        return new MessagesPage(driver);
    }

    public MainPage switchToLastPage() {
        int lastPage = Paginator.getPagesCount(driver);
        Paginator.switchToPage(driver, lastPage);
        return this;
    }
}
