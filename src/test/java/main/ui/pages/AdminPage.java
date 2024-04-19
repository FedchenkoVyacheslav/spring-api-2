package main.ui.pages;

import main.ui.elements.Filter;
import main.ui.elements.Paginator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdminPage extends BasePage {
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tbody/tr/td[1]")
    private List<WebElement> users;
    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> userRoles;
    @FindBy(id = "error-el2")
    private WebElement messageUserList;

    public AdminPage findUser(String query) {
        Filter.search(driver, query);
        return this;
    }

    public AdminPage checkUserInList(String email, boolean containUser) {
        List<String> usersText = new ArrayList<>();
        for (WebElement user : users) {
            usersText.add(user.getText());
        }
        if (containUser) {
            assertTrue(usersText.contains(email));
        } else {
            assertFalse(usersText.contains(email));
        }
        return this;
    }

    public AdminPage checkUserRole(String email, String ...roles) {
        WebElement rolesText = driver.findElement(By.xpath(String.format("//tbody/tr/td[text()='%s']/following-sibling::td", email)));
        for (String role : roles) {
            assertTrue(rolesText.getText().contains(role.toUpperCase()));
        }
        return this;
    }

    public AdminPage checkMessageForEmptyList() {
        assertNotNull(messageUserList);
        assertEquals(messageUserList.getText(), "No results found!");
        return this;
    }

    public AdminPage checkCountOfUsersOnPage(int count) {
        assertEquals(users.size(), count);
        return this;
    }

    public AdminPage changeUsersCountPerPage(int count) {
        Paginator.changeCountOfElementsPerPage(driver, count);
        return this;
    }

    public AdminPage switchToLastPage() {
        int lastPage = Paginator.getPagesCount(driver);
        Paginator.switchToPage(driver, lastPage);
        return this;
    }

    public UserEditPage switchToUserEditPage(String email) {
        driver.findElement(By.xpath(String.format("//tbody/tr/td[text()='%s']/following-sibling::td/a", email))).click();
        return new UserEditPage(driver);
    }
}
