package org.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.ActionWithElements;

public class HeaderElement extends ActionWithElements {
    Logger logger = Logger.getLogger(getClass());

    @FindBy (xpath = "//a[@id='accountTriggerButton']")
    private WebElement myProfile;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement clickOnMyProfile(){
        clickOnElement(myProfile);
        logger.info("My profile was clicked");
        return this;
    }
}
