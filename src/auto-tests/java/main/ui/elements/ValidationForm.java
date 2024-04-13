package main.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;


public class ValidationForm {
    public static void checkInvalidInputs(WebDriver driver, String formName, boolean isActive) {
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        if(isActive) {
            assertTrue(inputs.stream().anyMatch(i -> i.getAttribute("class").contains("is-invalid")));
        } else {
            assertFalse(inputs.stream().anyMatch(i -> i.getAttribute("class").contains("is-invalid")));
        }
    }
    public static String getInvalidMessage(WebDriver driver, String formName) {
        WebElement message = driver.findElement(By.xpath("//form[@name='" + formName + "']//div[@class='invalid-feedback']"));
        return message.getAttribute("innerHTML");
    }
}
