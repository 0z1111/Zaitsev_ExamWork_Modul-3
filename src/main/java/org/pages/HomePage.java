package org.pages;

import org.apache.log4j.Logger;
import org.elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.time.LocalDate;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    private WebElement yearOfBirthDropdown;

    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    private WebElement monthOfBirthDropdown;

    @FindBy(xpath = "//input[@autocomplete='given-name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@autocomplete='family-name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@name='password-confirmation']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//label[@class='checkboxform-root-FNN']")
    private WebElement termsCondotionsCheckBox;

    @FindBy(xpath = "//span[text()='Create Account']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//div[@class='dialog-header-Luy']")
    private WebElement welcomeToMARESPopup;

    @FindBy(xpath = "//div[@class='dialog-accountCreatedMessageContent-yee']")
    private Label successfulRegMessage;

    @FindBy(xpath = "//div[@class='dialog-header-Luy']")
    private WebElement signInPopup;

    @FindBy(xpath = "//span[text()='Continue']")
    private WebElement continueButtonInSignInPopup;

    @FindBy(xpath = "//h2[@class='dialog-headerTitle-RxS']")
    private WebElement createAccountPopup;

    @FindBy(xpath = "//input[@name='datepicker_date_of_birth']")
    private WebElement dateOfBirthInput;

    @FindBy(xpath = "//span[text()='Done']")
    private WebElement doneButtonInWelcomePopup;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage openHomePage() {
        try {
            webDriver.get(baseURL);
            logger.info("Home page was opened");
        } catch (Exception e) {
            logger.error("Can not open Home page");
        }
        return this;
    }

    public void submitEmailInSignInPopup(String email) {
        clearAndEnterTextIntoElement(signInPopup, email);
        logger.info(email + " was inputted in Sign In popup");
        clickOnElement(continueButtonInSignInPopup);
        logger.info("Continue button was clicked in Sign In popup");
    }

    public boolean checkIsSignInPopupDisplayed() {
        Assert.assertTrue("Sign In popup is not displayed", isElementDisplayed(signInPopup));
        logger.info("Sign In popup is displayed");
        return true;
    }

    public boolean checkIsCreateAccountPopupDisplayed() {
        Assert.assertTrue("Create Account popup is not displayed", isElementDisplayed(createAccountPopup));
        logger.info("Create Account popup is displayed");
        return true;
    }

    public void submitRegistrationForm(String firstName, String lastName, LocalDate birthDate
            , String password, String confirmPassword) {
        clearAndEnterTextIntoElement(firstNameInput, "firstName");
        logger.info(firstName + " was inputted in the " + getElementName(firstNameInput));
        clearAndEnterTextIntoElement(lastNameInput, "lastName");
        logger.info(lastName + " was inputted in the " + getElementName(lastNameInput));
        pickDate(birthDate);
        logger.info("Birth date was picked in the " + getElementName(dateOfBirthInput));
        clearAndEnterTextIntoElement(passwordInput, "password");
        clearAndEnterTextIntoElement(confirmPasswordInput, "confirmPassword");
        logger.info("Password and Confirm Password were inputted in the " + getElementName(passwordInput)
                + " and " + getElementName(confirmPasswordInput));
        checkCheckBox(termsCondotionsCheckBox);
        logger.info("Terms and Conditions checkbox was checked");
        clickOnElement(createAccountButton);
        logger.info(getElementName(createAccountButton) + " was clicked");
    }

    public void checkIsRegistrationSuccessfulAndClickDone() {
        webDriverWait10.until(ExpectedConditions.visibilityOf(welcomeToMARESPopup));
        Assert.assertEquals("Registration success message is incorrect"
                , "You’ve successfully joined MARES.", successfulRegMessage.getText());
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(doneButtonInWelcomePopup));
        clickOnElement(doneButtonInWelcomePopup);
    }
}
