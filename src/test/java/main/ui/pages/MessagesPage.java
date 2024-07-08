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
}
