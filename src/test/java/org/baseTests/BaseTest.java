package org.baseTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.PageProvider;

import java.time.Duration;

public class BaseTest {
private WebDriver webDriver;
private Logger logger = Logger.getLogger(getClass());
protected PageProvider pageProvider;

@Before
public void setup(){
    WebDriverManager.chromedriver().setup();
    webDriver = new ChromeDriver();
    webDriver.manage().window().maximize();
    webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    logger.info("Browser was opened");

    pageProvider = new PageProvider(webDriver);
}

@After
    public void tearDown(){
    webDriver.quit();
    logger.info("Browser was closed");
}
}
