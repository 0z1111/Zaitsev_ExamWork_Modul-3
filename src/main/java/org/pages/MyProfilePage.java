package org.pages;

import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MyProfilePage extends ParentPage {

    @FindBy(xpath = "//button[.//span[text()='Delete my account']]")
    private WebElement deleteMyAccountButton;

    @FindBy(xpath = "//button[@priority='normal' and @role='button']")
    private WebElement deleteMyAccountButtonInPopup;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//h1[@class='myAccountIndexPage-greeting-OEh']")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//div[@class='myAccount-root-I1S']")
    private WebElement myProfileBlock;

    @FindBy(xpath = "//button[text()='Log out']")
    private WebElement logOutButton;

    @FindBy(xpath = "//a[@href='/en/customer/account/edit']")
    private WebElement accountInformationMenuItem;

    @FindBy(xpath = "//div[@class='accountInformationPage-root-UZN']")
    private WebElement accountInformationBlock;

    @FindBy(xpath = "//div[@class='accountInformationPage-changeSectionContainer-B98']")
    private WebElement changePasswordBlock;

    @FindBy(xpath = "//span[text() = 'Change Password']")
    private WebElement changePasswordButton;

    @FindBy(xpath = "//input[@type='password' and @autocomplete='current-password']")
    private WebElement currentPasswordInput;

    @FindBy(xpath = "//input[@type='password' and @autocomplete='new-password']")
    private WebElement newPasswordInput;

    @FindBy(xpath = "//input[@type='password' and @name='confirmPassword']")
    private WebElement confirmNewPasswordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveNewPasswordButton;

    @FindBy(xpath = "//h1[text() = 'Wishlist']")
    private WebElement myWishListTitle;

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

    public MyProfilePage checkIsRedirectToMyWishList() {
        Assert.assertTrue("My WishList title is not displayed", isElementDisplayed(myWishListTitle));
        logger.info("Redirect to My WishList page was successful");
        return this;
    }

    public void checkIsProductAddedToWishList(String productName) {
        By productInWishList = By.xpath("//a[normalize-space()='" + productName + "']");

        Assert.assertTrue(
                "Product '" + productName + "' is not displayed in WishList",
                isElementDisplayed(webDriver.findElement(productInWishList))
        );

        logger.info("Product '" + productName + "' is displayed in WishList");
    }

    private final By deleteFromWishListButton =
            By.xpath("//button[contains(@aria-label,'Remove from Wishlist')]");

    public void deleteAllProductsFromWishList() {

        int safety = 50;

        while (safety-- > 0) {
            List<WebElement> buttons = webDriver.findElements(deleteFromWishListButton);
            if (buttons.isEmpty()) break;

            WebElement btn = buttons.get(0);

            clickOnElement(btn);

            webDriverWait10.until(ExpectedConditions.stalenessOf(btn));
        }

        if (safety <= 0) {
            throw new AssertionError("deleteAllProductsFromWishList: possible infinite loop (too many items or delete doesn't work)");
        }

        logger.info("All products were deleted from WishList");
    }

    public HomePage clickOnDeleteAccountButton() {
        clickOnElement(deleteMyAccountButton);
        webDriverWait10.until(ExpectedConditions.visibilityOf(deleteMyAccountButtonInPopup)).click();
        logger.info("Delete my account button was clicked in popup");
        return new HomePage(webDriver);
    }
}
