package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ActionWithElements {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    Logger logger = Logger.getLogger(getClass());

    @FindBy (xpath = "//input[@name='datepicker_date_of_birth']")
    private WebElement dateOfBirthInput;

    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    private WebElement yearOfBirthDropdown;

    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    private WebElement monthOfBirthDropdown;


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

    public void pickDate(LocalDate date) {

        dateOfBirthInput.click();

        WebDriverWait wait = webDriverWait10;

        WebElement yearSelectEl = wait.until(ExpectedConditions.visibilityOf(yearOfBirthDropdown));
        Select yearSelect = new Select(yearSelectEl);
        yearSelect.selectByVisibleText(String.valueOf(date.getYear()));

        WebElement monthSelectEl = wait.until(ExpectedConditions.visibilityOf(monthOfBirthDropdown));
        Select monthSelect = new Select(monthSelectEl);
        String monthText = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        monthSelect.selectByVisibleText(monthText);

        String day = String.valueOf(date.getDayOfMonth());
        By dayCell = By.xpath("//td[not(contains(@class,'old')) and not(contains(@class,'new')) and normalize-space()='" + day + "']");

        wait.until(ExpectedConditions.elementToBeClickable(dayCell)).click();
    }

}
