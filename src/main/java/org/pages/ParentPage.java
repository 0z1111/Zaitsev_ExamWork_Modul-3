package org.pages;

import org.openqa.selenium.WebDriver;

public class ParentPage extends ActionWithElements {
    protected String baseURL = "https://www.mares.com/en/";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
