package main.ui.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;
public class PrepareDriver {
    public static WebDriver driverInit(String browser) {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);

        WebDriver driver;
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.setCapability("goog:loggingPrefs", logs);
                driver = new ChromeDriver(optionsChrome);
                return driver;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:/firefoxdriver/geckodriver.exe");
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.setCapability("goog:loggingPrefs", logs);
                driver = new FirefoxDriver(optionsFirefox);
                return driver;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
    }
}
