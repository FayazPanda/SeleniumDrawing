package com.qa.selenium;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.qa.selenium.pages.demosite.DrawSite;
import jdk.incubator.jpackage.internal.IOUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class DrawSiteTest {

    private static RemoteWebDriver driver;
    private static Logger LOGGER = Logger.getGlobal();
    private static WebElement targElement = null;
    private IOUtils FileUtils;

    @BeforeClass
    public static void initialise(){
        LOGGER.setLevel(Level.ALL);
        System.setProperty("webdriver.chrome.driver", "src/test/resources/selenium/webdrivers/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));

        // timeouts
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
    }

    @Before
    public void setup() {}

    @Test
    public void drawInitials() throws IOException {
        DrawSite webpage = PageFactory.initElements(driver , DrawSite.class);
        Actions actions = new Actions(driver);

        actions.moveByOffset(500,500)
                .clickAndHold()
                .moveByOffset(0,100)
                .release()
                .moveByOffset(0 ,-50)
                .clickAndHold()
                .moveByOffset(15,0)
                .release()
                .moveByOffset(-15,-50)
                .clickAndHold()
                .moveByOffset(50,0)
                .release()
                .moveByOffset(115,0)
                .clickAndHold()
                .moveByOffset(-100,50)
                .moveByOffset(100,0)
                .moveByOffset(-100,50)
                .release();

        TakesScreenshot scrnshot = ((TakesScreenshot)webpage);
        File scrnFile = scrnshot.getScreenshotAs(OutputType.FILE);

        File DestFile=new File("C:\\Users\\DELL PRECISION\\Desktop\\test.png");

        FileUtils.copyFile(scrnFile, DestFile);
    }


    @AfterClass
    public static void tearDown() {
        LOGGER.warning("Closing webdriver instance!");

        driver.close();

        LOGGER.info("!!! Webdriver closed successfully !!!");
    }
}