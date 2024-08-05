package test.ui;

import main.ui.actions.PrepareDriver;
import main.ui.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static main.ui.util.testData.*;

public abstract class BaseITCase {
    static WebDriver driver;
    LoginPage myLoginPage;

    @BeforeEach
    public void setup() {
        driver = PrepareDriver.driverInit("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL + "login");
        myLoginPage = new LoginPage(driver);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
