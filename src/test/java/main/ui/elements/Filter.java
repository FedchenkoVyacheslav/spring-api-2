package main.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.junit.Assert.*;

public class Filter {
    public static void search(WebDriver driver, String searchQuery) {
        WebElement filter = driver.findElement(By.id("search-input"));
        WebElement confirmBtn = driver.findElement(By.id("search-button"));
        WebElement searchLabel = driver.findElement(By.id("error-el"));
        filter.sendKeys(Keys.CONTROL + "a");
        filter.sendKeys(Keys.DELETE);
        assertEquals("Enter your search query...", searchLabel.getText());
        filter.sendKeys(searchQuery);
        if(searchQuery.isEmpty()) {
            assertEquals("Enter your search query...", searchLabel.getText());
        } else {
            assertEquals("", searchLabel.getText());
        }
        confirmBtn.click();
    }

    public static void clickOnSearchButton(WebDriver driver) {
        driver.findElement(By.id("search-button")).click();
    }
}
