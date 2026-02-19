package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends ActionWithElements {
    protected String baseURL = "https://www.mares.com/en/";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
    Logger logger = Logger.getLogger(getClass());

    abstract protected String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("URL is not as expected",
                baseURL + getRelativeUrl(), webDriver.getCurrentUrl());
    }
}
