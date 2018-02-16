package com.pcops.web.driver.webdriver;

import com.pcops.web.driver.utilities.BrowserProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.SystemClock;

import java.util.Properties;

/**
 * Created by bkondamu on 9/26/15.
 */
public class WebDriverFactory {

    private static WebDriverFactory ourInstance;

    private WebDriverFactory() throws Exception {
        BrowserProperties browserProperties = new BrowserProperties();
        Properties properties = browserProperties.getBrowserProperties();
        browserProperties.setBrowserProperties(properties);
    }

    public synchronized static WebDriverFactory getInstance() throws Exception {
        if (ourInstance == null)
            ourInstance = new WebDriverFactory();
        return ourInstance;
    }


    public WebDriver getDriver() throws Exception {

        if (System.getProperty(BrowserProperties.BROWSER_NAME).equals("CHROME"))
            return SelectingBrowser.CHROME.getDriver();
        if (System.getProperty(BrowserProperties.BROWSER_NAME).equals("HTMLUNIT"))
            return SelectingBrowser.HTMLUNIT.getDriver();
        if (System.getProperty(BrowserProperties.BROWSER_NAME).equals("FIREFOX"))
            return SelectingBrowser.FIREFOX.getDriver();
        if (System.getProperty(BrowserProperties.BROWSER_NAME).equals("IE"))
            return SelectingBrowser.IE.getDriver();
        if (System.getProperty(BrowserProperties.BROWSER_NAME).equals("SAFARI"))
            return SelectingBrowser.SAFARI.getDriver();
        if(System.getProperty(BrowserProperties.BROWSER_NAME).equals("PHANTOMJS"))
            return SelectingBrowser.PHANTOMJS.getDriver();
        else return null;
    }

}
