package org.pages;

import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyProfilePage extends ParentPage{
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy (xpath = "//h1[@class='myAccountIndexPage-greeting-OEh']")
    private WebElement welcomeMessage;

    @FindBy (xpath = "//div[@class='myAccount-root-I1S']")
    private WebElement myProfileBlock;

    @FindBy (xpath = "//button[text()='Log out']")
    private WebElement logOutButton;

    @FindBy (xpath = "//a[@href='/en/customer/account/edit']")
    private WebElement accountInformationMenuItem;

    @FindBy (xpath = "//div[@class='accountInformationPage-root-UZN']")
    private WebElement accountInformationBlock;

    @FindBy (xpath = "//div[@class='accountInformationPage-changeSectionContainer-B98']")
    private WebElement changePasswordBlock;

    @FindBy (xpath = "//span[text() = 'Change Password']")
    private WebElement changePasswordButton;

    @FindBy (xpath = "//input[@type='password' and @autocomplete='current-password']")
    private WebElement currentPasswordInput;

    @FindBy (xpath = "//input[@type='password' and @autocomplete='new-password']")
    private WebElement newPasswordInput;

    @FindBy (xpath = "//input[@type='password' and @name='confirmPassword']")
    private WebElement confirmNewPasswordInput;

    @FindBy (xpath = "//button[@type='submit']")
    private WebElement saveNewPasswordButton;

    @Override
    protected String getRelativeUrl() {
        return "customer/account";
    }

    public MyProfilePage checkIsRedirectedOnMyProfilePage() {
        webDriverWait10.until(driver -> driver.getCurrentUrl().equals(baseURL + getRelativeUrl()));
        checkUrl();

        webDriverWait10.until(ExpectedConditions.visibilityOf(myProfileBlock));
        webDriverWait10.until(ExpectedConditions.visibilityOf(welcomeMessage));

        webDriverWait10.until(driver -> !welcomeMessage.getText().trim().isEmpty());

        String actual = welcomeMessage.getText().trim();
        String expected = ("Hi " + TestData.FIRST_NAME).trim();

        Assert.assertEquals("Welcome message is wrong", expected, actual);
        Assert.assertTrue("My profile block is not visible", isElementDisplayed(myProfileBlock));

        return this;
    }

    public MyProfilePage clickOnAccountInformationMenuItem() {
        clickOnElement(accountInformationMenuItem);
        logger.info("Account information menu item was clicked");
        Assert.assertTrue("Account information block is not visible", isElementDisplayed(accountInformationBlock));
        return this;
    }

    public MyProfilePage clickOnChangePasswordButton() {
        clickOnElement(changePasswordButton);
        logger.info("Change password button was clicked");
        webDriverWait10.until(ExpectedConditions.visibilityOf(changePasswordBlock));
        Assert.assertTrue("Current password input is not visible", isElementDisplayed(currentPasswordInput));
        Assert.assertTrue("New password input is not visible", isElementDisplayed(newPasswordInput));
        Assert.assertTrue("Confirm new password input is not visible", isElementDisplayed(confirmNewPasswordInput));
        logger.info("All inputs for changing password are visible");
        return this;
    }

    public MyProfilePage enterCurrentPassword(String currentPassword) {
        clearAndEnterTextIntoElement(currentPasswordInput, currentPassword);
        logger.info("Current password was entered");
        return this;
    }

    public MyProfilePage enterNewPassword(String newPassword) {
        clearAndEnterTextIntoElement(newPasswordInput, newPassword);
        logger.info("New password was entered");
        return this;
    }

    public MyProfilePage enterConfirmNewPassword(String confirmNewPassword) {
        clearAndEnterTextIntoElement(confirmNewPasswordInput, confirmNewPassword);
        logger.info("Confirm new password was entered");
        return this;
    }

    public void clickSaveNewPasswordButton() {
        clickOnElement(saveNewPasswordButton);
        logger.info("Save new password button was clicked");
    }

    public void logOut() {
        clickOnElement(logOutButton);
        webDriverWait15.until(ExpectedConditions.urlToBe(baseURL));
        logger.info("User is logged out");
    }
}
