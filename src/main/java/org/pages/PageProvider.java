package org.pages;

import org.elements.HeaderElement;
import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }
    public HeaderElement getHeaderElement(){
        return new HeaderElement(webDriver);
    }
    public MyProfilePage getMyProfilePage(){
        return new MyProfilePage(webDriver);
    }
}
