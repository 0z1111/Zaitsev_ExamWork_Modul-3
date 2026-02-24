package org.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.ActionWithElements;
import org.pages.MyProfilePage;

public class HeaderElement extends ActionWithElements {
    Logger logger = Logger.getLogger(getClass());

    @FindBy (xpath = "//a[@id='accountTriggerButton']")
    private WebElement myProfile;

    @FindBy (xpath = "//a[@href='/en/customer/account/wishlist']")
    private WebElement myWishlist;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement clickOnMyProfile(){
        clickOnElement(myProfile);
        logger.info("My profile was clicked");
        return this;
    }

    public MyProfilePage clickOnMyWishlist(){
        clickOnElement(myWishlist);
        logger.info("My wishlist was clicked");
        return new MyProfilePage(webDriver);
    }
}
