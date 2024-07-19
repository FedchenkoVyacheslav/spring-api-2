package main.ui.pages;

import main.ui.elements.Paginator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MessagesPage extends BasePage {
    public MessagesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.card-columns>div.card")
    private List<WebElement> messages;
    @FindBy(css = "div.card:nth-child(1) a.edit-message")
    private WebElement editMessageButton;
    @FindBy(css = "div.card:nth-child(1) a.delete-message")
    private WebElement deleteMessageButton;
    @FindBy(css = "div.card:nth-child(1) a.btn")
    private WebElement confirmDeleteMessageButton;
    @FindBy(css = "div.card:nth-child(1) button.btn")
    private WebElement cancelDeleteMessageButton;
    @FindBy(className = "subscribe-btn")
    private WebElement subscribeButton;
    @FindBy(css = "div:nth-child(1) > a.subscribe-link")
    private WebElement subscriptionsLink;
    @FindBy(css = "div:nth-child(2) > a.subscribe-link")
    private WebElement followersLink;

    public MessagesPage checkCountOfMessagesOnPage(int count) {
        Assert.assertEquals(count, messages.size());
        return this;
    }

    public MessagesPage changeMessagesCountPerPage(int count) {
        Paginator.changeCountOfElementsPerPage(driver, count);
        return this;
    }

    public MessageEditPage switchToEditMessagePage() {
        editMessageButton.click();
        return new MessageEditPage(driver);
    }

    public MessagesPage deleteLastCreatedMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(deleteMessageButton)).click();
        return this;
    }

    public MessagesPage cancelDeletion() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(cancelDeleteMessageButton)).click();
        return this;
    }

    public MessagesPage confirmDeletion() {
        confirmDeleteMessageButton.click();
        return this;
    }

    public MessagesPage subscribeToUser() {
        Assert.assertEquals("Subscribe", subscribeButton.getText());
        subscribeButton.click();
        Assert.assertEquals("Unsubscribe", subscribeButton.getText());
        return this;
    }

    public MessagesPage unsubscribeFromUser() {
        Assert.assertEquals("Unsubscribe", subscribeButton.getText());
        subscribeButton.click();
        Assert.assertEquals("Subscribe", subscribeButton.getText());
        return this;
    }

    public MessagesPage checkFollowersCount(int count) {
        Assert.assertEquals(count, Integer.parseInt(followersLink.getText()));
        return this;
    }

    public MessagesPage clickOnFollowersLink() {
        followersLink.click();
        return this;
    }

    public MessagesPage clickOnSubscriptionsLink() {
        subscriptionsLink.click();
        return this;
    }

    public FollowersPage switchToFollowersPage() {
        this.clickOnFollowersLink();
        return new FollowersPage(driver);
    }

    public SubscriptionsPage switchToSubscriptionsPage() {
        this.clickOnSubscriptionsLink();
        return new SubscriptionsPage(driver);
    }
}
