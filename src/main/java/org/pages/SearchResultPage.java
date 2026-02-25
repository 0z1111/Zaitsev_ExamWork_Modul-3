package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends ActionWithElements{
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//h1[@class='menuShelf-title-DiI']")
    private WebElement searchResultTitle;

    @FindBy(xpath = "//div[contains(@class,'productCard-root')]")
    private List<WebElement> productCards;

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SearchResultPage verifySearchResult(String searchQuery) {

        Assert.assertEquals(
                "Search result title is not as expected",
                searchQuery.toLowerCase(),
                searchResultTitle.getText().toLowerCase()
        );
        logger.info("Search result title verified successfully for query: " + searchQuery);
        Assert.assertTrue(
                "No product cards were found for search query: " + searchQuery,
                productCards.size() > 0
        );
        logger.info("Search results verified successfully for query: " + searchQuery);

        return this;
    }
}
