package main.ui.pages;

import main.ui.elements.Paginator;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MessagesPage extends BasePage {
    public MessagesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.card-columns>div.card")
    private List<WebElement> messages;

    public MessagesPage checkCountOfMessagesOnPage(int count) {
        Assert.assertEquals(count, messages.size());
        return this;
    }

    public MessagesPage changeMessagesCountPerPage(int count) {
        Paginator.changeCountOfElementsPerPage(driver, count);
        return this;
    }
}
