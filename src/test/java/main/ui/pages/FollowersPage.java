package main.ui.pages;

import org.junit.Assert;
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

    @FindBy(className = "link-subscribe-list")
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
            if (follower.getText().equals(email)) followerExists = true;
            else followerExists = false;
        }
        Assert.assertTrue(followerExists);
        return this;
    }
}
