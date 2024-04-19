package main.ui.util;

import com.github.javafaker.Faker;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class testData {
    public static Faker faker = new Faker();
    public static final String URL = "http://localhost:8082/";
    public static final String NAME = faker.name().firstName();
    public static final String SURNAME = faker.name().lastName();
    public static final String PASSWORD = faker.internet().password();
    public static final String EMAIL = String.format("%s.%s@%s", NAME, SURNAME, faker.internet().domainName()).toLowerCase();
    public static final String NEW_EMAIL = String.format("%s2.%s2@%s", NAME, SURNAME, faker.internet().domainName()).toLowerCase();
    public static final String ADMIN_EMAIL = "t1@gmail.com";
    public static final String ADMIN_PASSWORD = "1111";
    public static final String INVALID_EMAIL = "11111";

    public static Stream<Arguments> loginValidationTestData() {
        return Stream.of(
                Arguments.of("", "1", "Email cannot be empty"),
                Arguments.of("1", "1", "Email is not correct"),
                Arguments.of("test@mail.com", "", "Password cannot be empty")
        );
    }

    public static Stream<Arguments> validRegisterData() {
        return Stream.of(
                Arguments.of(NAME, SURNAME, EMAIL, PASSWORD)
        );
    }

    public static Stream<Arguments> registerValidationTestData() {
        return Stream.of(
                Arguments.of("", SURNAME, EMAIL, PASSWORD, "Name cannot be empty"),
                Arguments.of(NAME, "", EMAIL, PASSWORD, "Surname cannot be empty"),
                Arguments.of(NAME, SURNAME, "", PASSWORD, "Email cannot be empty"),
                Arguments.of(NAME, SURNAME, "1", PASSWORD, "Email is not correct"),
                Arguments.of(NAME, SURNAME, EMAIL, "", "Password cannot be empty"),
                Arguments.of(NAME, SURNAME, ADMIN_EMAIL, PASSWORD, "User exists!")
        );
    }

    public static Stream<Arguments> editUserValidationTestData() {
        return Stream.of(
                Arguments.of(ADMIN_EMAIL, ADMIN_PASSWORD, INVALID_EMAIL, "Email is not correct"),
                Arguments.of(ADMIN_EMAIL, ADMIN_PASSWORD, "", "This field is required")
        );
    }
}
