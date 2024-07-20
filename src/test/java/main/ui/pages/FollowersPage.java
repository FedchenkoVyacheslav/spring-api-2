package main.ui.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FollowersPage extends BasePage {
    public FollowersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "title-subscribe-title")
    private WebElement authorTitle;

    @FindBy(className = "empty-subscribe-list")
    private WebElement followersMessage;

    @FindBy(className = "subscribe-item")
    private List<WebElement> followers;

    public FollowersPage checkFollowersPageTitle(String authorName) {
        Assert.assertEquals(authorName + "'s", authorTitle.getText());
        return this;
    }

    public FollowersPage checkFollowersCount(int count) {
        Assert.assertEquals(count, followers.size());
        return this;
    }

    public FollowersPage checkFollowerExistsInList(String email) {
        boolean followerExists = false;
        for (WebElement follower : followers) {
            if (follower.findElement(By.className("link-subscribe-list")).getText().equals(email)) followerExists = true;
        }
        Assert.assertTrue(followerExists);
        return this;
    }

    public FollowersPage checkThatFollowersListIsEmpty() {
        Assert.assertEquals("No followers", followersMessage.getText());
        return this;
    }

    public FollowersPage subscribeToUser(String email) {
        for (WebElement follower : followers) {
            if (follower.findElement(By.className("link-subscribe-list")).getText().equals(email)) {
                Assert.assertEquals("Subscribe", follower.findElement(By.className("followers-btn")).getText());
                follower.findElement(By.className("followers-btn")).click();
            }
        }
        return this;
    }

    public FollowersPage unsubscribeFromUser(String email) {
        for (WebElement follower : followers) {
            if (follower.findElement(By.className("link-subscribe-list")).getText().equals(email)) {
                Assert.assertEquals("Unsubscribe", follower.findElement(By.className("followers-btn")).getText());
                follower.findElement(By.className("followers-btn")).click();
            }
        }
        return this;
    }
}
