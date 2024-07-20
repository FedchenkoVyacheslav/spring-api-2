package main.ui.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SubscriptionsPage extends BasePage {
    public SubscriptionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "subscribe-item")
    private List<WebElement> subscriptions;
    @FindBy(className = "title-subscribe-title")
    private WebElement authorTitle;

    public SubscriptionsPage checkSubscriptionInList(String email, boolean exists) {
        boolean subscriptionExists = false;
        for (WebElement subscription : subscriptions) {
            if (subscription.findElement(By.className("link-subscribe-list")).getText().equals(email))
                subscriptionExists = true;
        }
        if (exists) {
            Assert.assertTrue(subscriptionExists);
        } else {
            Assert.assertFalse(subscriptionExists);
        }
        return this;
    }

    public SubscriptionsPage subscribeToUser(String email) {
        for (WebElement subscription : subscriptions) {
            if (subscription.findElement(By.className("link-subscribe-list")).getText().equals(email)) {
                Assert.assertEquals("Subscribe", subscription.findElement(By.className("followers-btn")).getText());
                subscription.findElement(By.className("followers-btn")).click();
            }
        }
        return this;
    }

    public SubscriptionsPage unsubscribeFromUser(String email) {
        for (WebElement subscription : subscriptions) {
            if (subscription.findElement(By.className("link-subscribe-list")).getText().equals(email)) {
                Assert.assertEquals("Unsubscribe", subscription.findElement(By.className("followers-btn")).getText());
                subscription.findElement(By.className("followers-btn")).click();
            }
        }
        return this;
    }

    public SubscriptionsPage checkSubscriptionsPageTitle(String authorName) {
        Assert.assertEquals(authorName + "'s", authorTitle.getText());
        return this;
    }
}
