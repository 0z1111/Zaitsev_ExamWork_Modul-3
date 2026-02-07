package org.baseTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
private WebDriver webDriver;
private Logger logger = Logger.getLogger(getClass());

@Before
public void setup(){
    WebDriverManager.chromedriver().setup();
    webDriver = new ChromeDriver();
    webDriver.manage().window().maximize();
    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    logger.info("Browser was opened");
}

@After
    public void tearDown(){
    webDriver.quit();
    logger.info("Browser was closed");
}
@Test
    public void AccountCreation(){
    webDriver.get("https://www.mares.com/en/");
    logger.info("Site was opened");
}
}
