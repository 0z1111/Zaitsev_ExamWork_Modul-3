package org.pages;

import org.apache.log4j.Logger;
import org.elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

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
    private WebElement successfulRegMessage;

    @FindBy(xpath = "//div[@class='dialog-header-Luy']")
    private WebElement signInPopup;

    @FindBy(xpath = "//span[text()='Continue']")
    private WebElement continueButtonInSignInPopup;

    @FindBy(xpath = "//h2[@class='dialog-headerTitle-RxS']")
    private WebElement createAccountPopup;

    @FindBy(xpath = "//input[@name='datepicker_date_of_birth']")
    private WebElement dateOfBirthInput;

    @FindBy(xpath = "//label[@for=(//input[@name='datepicker_date_of_birth']/@id)]")
    private WebElement dateOfBirthLabel;

    @FindBy(xpath = "//button[.//span[normalize-space()='Done']]")
    private WebElement doneButtonInWelcomePopup;

    @FindBy (xpath = "//a[text()='Allow all cookies']")
    private WebElement allowAllCookiesButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy (xpath = "//button[@type='submit']")
    private WebElement signInButtonInSignInPopup;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }


    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage openHomePage() {
        webDriver.get(baseURL);
        logger.info("Home page was opened");
        acceptCookiesIfPresent();
        return this;
    }

    public MyProfilePage login(String email, String password) {
        openHomePage();
        getHeaderElement().clickOnMyProfile();
        checkIsSignInPopupDisplayed();
        submitEmailInSignInPopup(email);
        submitPasswordInSignInPopup(password);

        return new MyProfilePage(webDriver);
    }

    public void acceptCookiesIfPresent() {
        if (isElementDisplayed(allowAllCookiesButton)){
            clickOnElement(allowAllCookiesButton);
            logger.info("Allow all cookies button was clicked");
        } else {
            logger.info("Allow all cookies button is not displayed");
        }
    }

    public HomePage submitEmailInSignInPopup(String email) {
        clearAndEnterTextIntoElement(emailInput, email);
        logger.info(email + " was inputted in Sign In popup");
        clickOnElement(continueButtonInSignInPopup);
        logger.info("Continue button was clicked in Sign In popup");
        return this;
    }

    public HomePage submitPasswordInSignInPopup(String password) {
        clearAndEnterTextIntoElement(passwordInput, password);
        logger.info(password + " was inputted in Sign In popup");
        clickOnElement(signInButtonInSignInPopup);
        logger.info("SignIn button was clicked in Sign In popup");
        return this;
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
        clearAndEnterTextIntoElement(firstNameInput, firstName);
        logger.info(firstName + " was inputted in the " + getElementName(firstNameInput));
        clearAndEnterTextIntoElement(lastNameInput, lastName);
        logger.info(lastName + " was inputted in the " + getElementName(lastNameInput));
        pickDate(birthDate);
        logger.info("Birth date was picked in the " + getElementName(dateOfBirthInput));
        clearAndEnterTextIntoElement(passwordInput, password);
        clearAndEnterTextIntoElement(confirmPasswordInput, confirmPassword);
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
    public void pickDate(LocalDate date) {
        WebDriverWait wait = webDriverWait10;

        // 1) открыть календарь кликом по label (она перекрывает input)
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthInput));
        wait.until(ExpectedConditions.elementToBeClickable(dateOfBirthLabel)).click();

        // 2) выбрать год
        WebElement yearSelectEl = wait.until(ExpectedConditions.visibilityOf(yearOfBirthDropdown));
        new Select(yearSelectEl).selectByVisibleText(String.valueOf(date.getYear()));

        // 3) выбрать месяц
        WebElement monthSelectEl = wait.until(ExpectedConditions.visibilityOf(monthOfBirthDropdown));
        String monthText = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        new Select(monthSelectEl).selectByVisibleText(monthText);

        // 4) выбрать день (react-datepicker)
        String day2 = String.format("%02d", date.getDayOfMonth()); // 01..31

        By dayCell = By.xpath(
                "//div[contains(@class,'react-datepicker__day') " +
                        "and contains(@class,'react-datepicker__day--0" + day2 + "') " +
                        "and not(contains(@class,'--outside-month')) " +
                        "and not(contains(@class,'--disabled'))]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(dayCell)).click();

        // 5) дождаться, что значение появилось в input
        wait.until(driver -> !dateOfBirthInput.getAttribute("value").trim().isEmpty());
    }
}
