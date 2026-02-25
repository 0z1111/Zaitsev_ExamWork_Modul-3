package org.pages;

import org.elements.HeaderElement;
import org.openqa.selenium.WebDriver;
import org.pages.freeDivingFins.FreedivingFinsPage;
import org.pages.freeDivingFins.RazorPolygonPage;

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
    public FreedivingFinsPage getFreedivingFinsPage(){
        return new FreedivingFinsPage(webDriver);
    }
    public RazorPolygonPage getRazorPolygonPage(){
        return new RazorPolygonPage(webDriver);
    }
}
