package main.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Paginator {
    public static void switchToPage(WebDriver driver, int page) {
        List<WebElement> links = driver.findElements(By.xpath("//ul[1]//a[@class='page-link']"));
        links.stream().filter(e -> e.getText().equals(String.valueOf(page))).findFirst().stream().toList().getLast().click();
    }

    public static int getPagesCount(WebDriver driver) {
        List<WebElement> links = driver.findElements(By.xpath("//ul[1]//a[@class='page-link']"));
        return Integer.parseInt(links.getLast().getText());
    }

    public static void changeCountOfElementsPerPage(WebDriver driver, int countOfElements) {
        List<WebElement> links = driver.findElements(By.xpath("//ul[2]//a[@class='page-link']"));
        links.stream().filter(e -> e.getText().equals(String.valueOf(countOfElements))).findFirst().stream().toList().getLast().click();
    }
}
