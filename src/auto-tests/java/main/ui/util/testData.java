package main.ui.util;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class testData {
    public static final String URL = "http://localhost:8082/";

    public static Stream<Arguments> loginValidationTestData() {
        return Stream.of(
                Arguments.of("", "1", "Email cannot be empty"),
                Arguments.of("1", "1", "Email is not correct"),
                Arguments.of("test@mail.com", "", "Password cannot be empty")
        );
    }
}
