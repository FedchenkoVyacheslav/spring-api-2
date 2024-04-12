package test.ui;

import main.ui.actions.PrepareDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static main.ui.util.testData.URL;

public class LoginITCase {
    static WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = PrepareDriver.driverInit("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    public void init() {}

    @AfterEach
    public void quit() {
        driver.quit();
    }
}