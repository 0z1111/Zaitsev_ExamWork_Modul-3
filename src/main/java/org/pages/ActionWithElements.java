package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;

import java.time.Duration;

public class ActionWithElements {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    Logger logger = Logger.getLogger(getClass());

    public ActionWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider
                .configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider
                .configProperties.TIME_FOR_DEFAULT_WAIT()));
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception e) {
            logger.error("Can not click on element " + webElement);
        }
    }

    protected void clearAndEnterTextIntoElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
        } catch (Exception e) {
            logger.error("Can not input text into element " + webElement);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Exception e) {
            logger.error("Element is not displayed " + webElement);
            return false;
        }
    }

    protected void checkCheckBox(WebElement webElement) {
            if (!webElement.isSelected()) {
                webElement.click();
                logger.info("CheckBox " + getElementName(webElement) + " was checked");
            } else {
                logger.info("CheckBox " + getElementName(webElement) + " is already checked");
            }
    }

    protected void selectValueInDropdown(WebElement webElement, String value) {
        try {
            Select seclect = new Select(webElement);
            seclect.selectByValue(value);
            logger.info("Value '" + value + "' was selected in DropDown " + getElementName(webElement));
        }catch (Exception e) {
            logger.error("Can not select value '" + value + "' in DropDown " + getElementName(webElement));
        }
    }

    protected String getElementName(WebElement webElement){
        try {
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }
    }
}
