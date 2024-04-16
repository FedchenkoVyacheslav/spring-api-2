package test.ui;

import main.ui.actions.PrepareDriver;
import main.ui.pages.LoginPage;
import main.ui.pages.RegistrationPage;
import main.ui.util.JDBCService;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Duration;

import static main.ui.util.testData.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterITCase {
    static WebDriver driver;
    LoginPage myLoginPage;
    RegistrationPage myRegistrationPage;
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCService.mysqlDataSource());

    @BeforeEach
    public void setup() {
        driver = PrepareDriver.driverInit("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL + "login");
        myLoginPage = new LoginPage(driver);
        myRegistrationPage = new RegistrationPage(driver);
    }

    @ParameterizedTest
    @Ignore
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should register new user")
    public void registerNewUser(String name, String surname, String email, String password) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, true)
                .checkUrlIsValid(URL)
                .checkGreetings(name, surname)
                .pause(2500)
                .checkUrlIsValid(URL + "main")
                .checkNavbarEmailText(email, true)
                .checkCookie("JSESSIONID", true)
                .checkCookie("remember-me", true);

        Integer id = jdbcTemplate.queryForObject("select id from user order by id desc limit 1", Integer.class);
        assertEquals(email, jdbcTemplate.queryForObject(String.format("select email from user where id=%d", id), String.class));
        assertEquals(name, jdbcTemplate.queryForObject(String.format("select name from user where id=%d", id), String.class));
        assertEquals(surname, jdbcTemplate.queryForObject(String.format("select surname from user where id=%d", id), String.class));
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#registerValidationTestData")
    @DisplayName("Should check validation errors on registration")
    public void checkValidationErrorsOnRegistration(String name, String surname, String email, String password, String validationError) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .checkErrorInForm("register", validationError);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
