package org.pages.freeDivingFins;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.ParentPage;

public class RazorPolygonPage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    @FindBy (xpath = "//h1[text()='Razor Polygon']")
    private WebElement razorPolygonTitle;

    private final By wishlistButton =
            By.xpath("//button[contains(@class,'ProductFullDetail-wishlistRoot')]");

    public RazorPolygonPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "razor-polygon-420418";
    }
    public RazorPolygonPage checkIsRedirectToRazorPolygonPage(){
        webDriverWait10.until(driver -> driver.getCurrentUrl().equals(baseURL + getRelativeUrl()));
        checkUrl();
        Assert.assertTrue("Razor Polygon title is not displayed", isElementDisplayed(razorPolygonTitle));
        return this;
     }

    public boolean isInWishlist() {
        String aria = webDriver.findElement(wishlistButton)
                .getAttribute("aria-label");

        return aria != null && aria.contains("Select to remove");
    }

    public RazorPolygonPage addToWishlist() {

        if (!isInWishlist()) {
            clickOnElement(webDriver.findElement(wishlistButton));

            webDriverWait10.until(driver ->
                    driver.findElement(wishlistButton)
                            .getAttribute("aria-label")
                            .contains("Select to remove")
            );

            logger.info("Product added to wishlist");
        } else {
            logger.info("Product already in wishlist");
        }

        return this;
    }

    public RazorPolygonPage removeFromWishlist() {

        if (isInWishlist()) {
            clickOnElement(webDriver.findElement(wishlistButton));

            webDriverWait10.until(driver ->
                    driver.findElement(wishlistButton)
                            .getAttribute("aria-label")
                            .contains("Add to Favorites")
            );

            logger.info("Product removed from wishlist");
        } else {
            logger.info("Product already not in wishlist");
        }

        return this;
    }
}
