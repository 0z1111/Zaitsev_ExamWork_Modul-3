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

    @Override
    protected String getRelativeUrl() {
        return "customer/account";
    }

    public MyProfilePage checkIsRedirectedOnMyProfilePage() {
        // 1) ждём, что URL стал нужным
        webDriverWait10.until(driver -> driver.getCurrentUrl().equals(baseURL + getRelativeUrl()));
        checkUrl();

        // 2) ждём, что блок профиля и приветствие реально появились
        webDriverWait10.until(ExpectedConditions.visibilityOf(myProfileBlock));
        webDriverWait10.until(ExpectedConditions.visibilityOf(welcomeMessage));

        // 3) ждём, что текст не пустой (самое важное)
        webDriverWait10.until(driver -> !welcomeMessage.getText().trim().isEmpty());

        String actual = welcomeMessage.getText().trim();
        String expected = ("Hi " + TestData.FIRST_NAME).trim();

        Assert.assertEquals("Welcome message is wrong", expected, actual);
        Assert.assertTrue("My profile block is not visible", isElementDisplayed(myProfileBlock));

        return this;
    }

    public void logOut() {
        clickOnElement(logOutButton);
        logger.info("User is logged out");
    }
}
