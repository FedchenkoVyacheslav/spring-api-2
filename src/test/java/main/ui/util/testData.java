package main.ui.util;

import com.github.javafaker.Faker;
import main.ui.pages.BasePage;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class testData {
    public static Faker faker = new Faker();
    public static final String URL = "http://localhost:8082/";
    public static final String NAME = faker.name().firstName();
    public static final String SURNAME = faker.name().lastName();
    public static final String LOCATION = faker.address().city();
    public static final String AGE = String.valueOf((int) (Math.random() * (100 - 18)) + 18);
    public static final String PASSWORD = faker.internet().password();
    public static final String EMAIL = String.format("%s.%s@%s", NAME, SURNAME, faker.internet().domainName()).toLowerCase();
    public static final String NEW_EMAIL = String.format("%s2.%s2@%s", NAME, SURNAME, faker.internet().domainName()).toLowerCase();
    public static final String ADMIN_EMAIL = "t1@gmail.com";
    public static final String ADMIN_PASSWORD = "1111";
    public static final String INVALID_EMAIL = "11111";
    public static final String ADMIN_NAME = "Tom Anderson";
    public static final String PATH = "uploads/admin.jpg";
    public static final String NEW_NAME = new Faker().name().firstName();
    public static final String NEW_SURNAME = new Faker().name().lastName();
    public static final String TITLE = faker.howIMetYourMother().catchPhrase();
    public static final String TEXT = faker.howIMetYourMother().quote();
    public static final String NEW_TITLE = new Faker().howIMetYourMother().catchPhrase();
    public static final String NEW_TEXT = new Faker().howIMetYourMother().quote();

    public static Stream<Arguments> loginValidationTestData() {
        return Stream.of(
                Arguments.of("", "1", "Email cannot be empty"),
                Arguments.of("1", "1", "Email is not correct"),
                Arguments.of("test@mail.com", "", "Password cannot be empty")
        );
    }

    public static Stream<Arguments> admin() {
        return Stream.of(
                Arguments.of(ADMIN_EMAIL, ADMIN_PASSWORD)
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

    public static Stream<Arguments> editProfileTestData() {
        return Stream.of(
                Arguments.of(NAME, SURNAME, EMAIL, PASSWORD, NEW_NAME, NEW_SURNAME, LOCATION, AGE, PATH)
        );
    }

    public static Stream<Arguments> editProfileValidationTestData() {
        return Stream.of(
                Arguments.of("", NEW_SURNAME, LOCATION, AGE, "This field is required"),
                Arguments.of("A", NEW_SURNAME, LOCATION, AGE, "Too short name"),
                Arguments.of(NEW_NAME, "", LOCATION, AGE, "This field is required"),
                Arguments.of(NEW_NAME, "A", LOCATION, AGE, "Too short surname"),
                Arguments.of(NEW_NAME, NEW_SURNAME, LOCATION, "9", "Invalid age"),
                Arguments.of(NEW_NAME, NEW_SURNAME, LOCATION, "101", "Invalid age")
        );
    }

    public static Stream<Arguments> validMessageData() {
        return Stream.of(
                Arguments.of(TITLE, TEXT, PATH)
        );
    }

    public static Stream<Arguments> searchMessageData() {
        return Stream.of(
                Arguments.of("eagle", "img/Blog34", "Curious Facts About the Bald Eagle.", "Bald Eagle is a large bird of prey with a wingspan of more than 2 meters. It is one of the national symbols of the USA.", "Joi Dream", "joi@gmail.com", 12),
                Arguments.of("viper", "img/Blog17", "The most poisonous snakes in the world.", "The viper-like deadly snake is a very venomous snake living in Australia, New Guinea and the surrounding area. She is considered one of the deadliest snakes in the world.", "Officer K", "key@gmail.com", 16)
        );
    }

    public static Stream<Arguments> sendMessageValidationTestData() {
        return Stream.of(
                Arguments.of("", TEXT, "Please fill the title of message"),
                Arguments.of("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Duis nec volutpat nulla. Fusce at leo lectus. Nunc interdum, sem ut posuere " +
                        "congue, felis ligula convallis leo, quis interdum massa nibh porttitor nisi. " +
                        "Etiam id fermentum velit. Aliquam urna erat, bibendum at efficitur at, varius vitae mi. ", TEXT, "Message title is too long"),
                Arguments.of(TITLE, "", "Please fill the message text"),
                Arguments.of(TITLE, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Duis nec volutpat nulla. Fusce at leo lectus. Nunc interdum, sem ut posuere congue, " +
                        "felis ligula convallis leo, quis interdum massa nibh porttitor nisi. Etiam id fermentum velit. " +
                        "Aliquam urna erat, bibendum at efficitur at, varius vitae mi. Vestibulum ante ipsum primis in " +
                        "faucibus orci luctus et ultrices posuere cubilia curae; Phasellus semper dui in viverra hendrerit. " +
                        "Ut maximus dictum sem eu porta. Sed purus nunc, porta a ante at, pharetra pellentesque nunc. " +
                        "Integer neque libero, congue a faucibus non, dictum eu enim. Nullam volutpat ligula et sapien faucibus, " +
                        "vel tincidunt felis suscipit. Pellentesque in finibus nunc, eu aliquet neque. Duis laoreet nisi posuere " +
                        "nibh venenatis sollicitudin ac sit amet ex. Praesent tempus metus non ligula accumsan viverra. " +
                        "Mauris malesuada rutrum dolor nec vehicula. Nam sit amet erat et libero tempor aliquam. Sed vitae dui " +
                        "sed odio condimentum auctor convallis at metus. Donec felis lorem, semper ac quam et, ornare finibus lorem. " +
                        "Mauris non ante vel ex lobortis semper sed eu metus. Praesent eget neque tincidunt, bibendum quam vel, porttitor ipsum. " +
                        "Sed vulputate at libero non congue. Curabitur quis porttitor risus. Donec venenatis nibh in nulla faucibus viverra. " +
                        "Integer diam erat, iaculis at consequat vitae, rhoncus vitae turpis.Cras vehicula lobortis leo, eget mattis ex rutrum eu. " +
                        "Mauris porta eros at justo posuere, non auctor nisl porta. Sed enim magna, cursus a egestas a, commodo at lorem. " +
                        "Aliquam ipsum felis, mattis eget libero vitae, mollis gravida justo. Maecenas velit ante, condimentum ac elementum ut, " +
                        "vestibulum eu sapien. Donec congue suscipit odio, sit amet varius lectus dictum eget. Donec ac neque sit amet eros pulvinar " +
                        "suscipit in commodo lorem. Maecenas ut porta ligula. Nam porttitor tellus et odio finibus vulputate. Morbi consequat est in " +
                        "tempus consequat. Aliquam id mauris tristique, euismod nisi vel, posuere nibh. Vivamus in venenatis arcu. Curabitur vestibulum " +
                        "mauris in erat semper rutrum. Proin eleifend velit nec ante interdum, vel faucibus urna imperdiet. Vivamus dignissim porttitor " +
                        "facilisis. Nunc dignissim porttitor risus, ut viverra mi cursus quis. Donec convallis velit vel sem varius, euismod facilisis " +
                        "tortor convallis. Integer in eros accumsan, blandit magna id, bibendum nibh. Nam porta, orci nec vehicula facilisis, nisl elit " +
                        "pharetra magna, at tempor nisl felis in quam.", "Message text is too long")
        );
    }

    public static Stream<Arguments> searchMessageValidationTestData() {
        return Stream.of(
                Arguments.of("1234567890", "No results found!")
        );
    }
}
