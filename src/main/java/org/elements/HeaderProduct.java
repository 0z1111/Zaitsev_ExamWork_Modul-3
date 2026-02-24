package org.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.ActionWithElements;

public class HeaderProduct extends ActionWithElements {
    Logger logger = Logger.getLogger(getClass());

    @FindBy (xpath = "//span[text()='Products']")
    WebElement productsMenu;

    public HeaderProduct(WebDriver webDriver) {
        super(webDriver);
    }

    public void openSubMenu(String sectionName, String subMenuName) {
        Actions actions = new Actions(webDriver);

        logger.info("Hover on Products");
        actions.moveToElement(productsMenu).perform();

        // Ждём, что левый список секций появился (любой li стал видимым)
        By leftMenuAnyItem = By.xpath("//ul[contains(@class,'submenu-categories')]//li[contains(@class,'submenu-step-link')]");
        webDriverWait10.until(ExpectedConditions.visibilityOfElementLocated(leftMenuAnyItem));

        // Берём ВИДИМЫЙ элемент секции: чаще всего внутри li есть <a> или <button>.
        By sectionVisibleTarget = By.xpath(
                "(//ul[contains(@class,'submenu-categories')]//li[contains(@class,'submenu-step-link') and contains(normalize-space(.),'" + sectionName + "')]//*[self::a or self::button or self::span][normalize-space()!=''])[1]"
        );

        WebElement sectionEl = webDriverWait10.until(ExpectedConditions.visibilityOfElementLocated(sectionVisibleTarget));

        // hover на видимый target
        actions.moveToElement(sectionEl).perform();

        // Ждём появления правого сабменю и кликаем
        By subMenuLink = By.xpath("//a[contains(normalize-space(.),'" + subMenuName + "')]");
        WebElement subMenuEl = webDriverWait10.until(ExpectedConditions.elementToBeClickable(subMenuLink));

        clickOnElement(subMenuEl);
    }


}
