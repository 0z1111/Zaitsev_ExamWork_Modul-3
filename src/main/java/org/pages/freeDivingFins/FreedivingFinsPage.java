package org.pages.freeDivingFins;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.ParentPage;

public class FreedivingFinsPage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());
    public FreedivingFinsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "shop-freediving/freediving-fins";
    }
    public FreedivingFinsPage checkIsRedirectToFreedivingFinsPage(){
        webDriverWait10.until(driver -> driver.getCurrentUrl().equals(baseURL + getRelativeUrl()));
        checkUrl();
        logger.info("Freediving Fins page is opened");
        return this;
    }

    public FreedivingFinsPage openProductByName(String productName) {

        By productBy = By.xpath("//a[@aria-label='" + productName + "']");

        WebElement productLink = webDriverWait10.until(
                ExpectedConditions.presenceOfElementLocated(productBy)
        );

        ((JavascriptExecutor) webDriver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                productLink
        );

        webDriverWait10.until(ExpectedConditions.elementToBeClickable(productLink));
        clickOnElement(productLink);

        return this;
    }
}
