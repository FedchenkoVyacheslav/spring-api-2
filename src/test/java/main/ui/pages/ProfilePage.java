package main.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "edit-name")
    private WebElement nameInput;
    @FindBy(id = "edit-surname")
    private WebElement surnameInput;
    @FindBy(id = "edit-location")
    private WebElement locationInput;
    @FindBy(id = "edit-age")
    private WebElement ageInput;
    @FindBy(id = "edit-photo")
    private WebElement photoInput;
    @FindBy(id = "edit-btn")
    private WebElement saveButton;
    @FindBy(className = "profile-img")
    private WebElement profilePhoto;
    @FindBy(className = "custom-file-label")
    private WebElement photoLabel;

    public ProfilePage typeName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    public ProfilePage typeSurname(String surname) {
        surnameInput.clear();
        surnameInput.sendKeys(surname);
        return this;
    }

    public ProfilePage typeLocation(String location) {
        locationInput.clear();
        locationInput.sendKeys(location);
        return this;
    }

    public ProfilePage typeAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
        return this;
    }

    public ProfilePage uploadPhoto(String path) {
        File file = new File(path);
        photoInput.sendKeys(file.getAbsolutePath());
        return this;
    }

    public ProfilePage clickOnSaveButton() {
        saveButton.click();
        return this;
    }

    public ProfilePage checkNameFilled(String name) {
        assertEquals(nameInput.getAttribute("value"), name);
        return this;
    }

    public ProfilePage checkSurnameFilled(String surname) {
        assertEquals(surnameInput.getAttribute("value"), surname);
        return this;
    }

    public ProfilePage checkLocationFilled(String location) {
        assertEquals(locationInput.getAttribute("value"), location);
        return this;
    }

    public ProfilePage checkAgeFilled(String age) {
        assertEquals(ageInput.getAttribute("value"), age);
        return this;
    }

    public ProfilePage checkPhotoName(String fileName) {
        File path = new File(profilePhoto.getAttribute("src"));
        assertTrue(path.getName().contains(fileName));
        return this;
    }

    public ProfilePage checkPhotoLabel(String text) {
        assertEquals(photoLabel.getText(), String.format("%s...", text));
        return this;
    }

    public ProfilePage saveProfileData(String name, String surname, String location, String age, String path) {
        this.typeName(name);
        this.typeSurname(surname);
        this.typeLocation(location);
        this.typeAge(age);
        this.uploadPhoto(path);
        this.checkPhotoLabel(Paths.get(path).getFileName().toString());
        clickOnSaveButton();
        this.checkPhotoLabel("Choose file");
        return this;
    }

    public ProfilePage saveProfileData(String name, String surname, String location, String age) {
        this.typeName(name);
        this.typeSurname(surname);
        this.typeLocation(location);
        this.typeAge(age);
        clickOnSaveButton();
        this.checkPhotoLabel("Choose file");
        return this;
    }

    public ProfilePage checkProfileData(String name, String surname, String location, String age, String fileName) {
        this.checkNameFilled(name);
        this.checkSurnameFilled(surname);
        this.checkLocationFilled(location);
        this.checkAgeFilled(age);
        this.checkPhotoName(fileName);
        this.checkPhotoLabel("Choose file");
        return this;
    }
}
