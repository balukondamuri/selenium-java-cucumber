package com.pcops.web.driver.webdriver;

import com.pcops.web.driver.utilities.BrowserProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by bkondamu on 9/26/15.
 */
public enum SelectingBrowser {

    CHROME {
        public WebDriver getDriver() {
            RemoteWebDriver remoteWebDriver;
            WebDriver driver;

            if (Boolean.valueOf(getRemoteExecution())) {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                remoteWebDriver = (RemoteWebDriver) new Augmenter().augment(new RemoteWebDriver(getHubUrl(), capabilities));
                remoteWebDriver.setFileDetector(new LocalFileDetector());
                remoteWebDriver.setLogLevel(Level.ALL);
                return remoteWebDriver;
            } else {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--allow-running-insecure-content");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                return driver;
            }
        }

    },

    FIREFOX {
        public WebDriver getDriver() {

            RemoteWebDriver remoteWebDriver;
            WebDriver driver;

            if (Boolean.valueOf(getRemoteExecution())) {
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                remoteWebDriver = (RemoteWebDriver) new Augmenter().augment(new RemoteWebDriver(getHubUrl(), capabilities));
                remoteWebDriver.setFileDetector(new LocalFileDetector());
                remoteWebDriver.setLogLevel(Level.ALL);
                return remoteWebDriver;
            } else {
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                driver = new FirefoxDriver(firefoxProfile);
                driver.manage().window().maximize();
                return driver;
            }
        }
    },

    IE {
        public WebDriver getDriver() {

            RemoteWebDriver remoteWebDriver;
            WebDriver driver;

            if (Boolean.valueOf(getRemoteExecution())) {
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                setIECapabilities(capabilities);
                remoteWebDriver = (RemoteWebDriver) new Augmenter().augment(new RemoteWebDriver(getHubUrl(), capabilities));
                remoteWebDriver.setFileDetector(new LocalFileDetector());
                return remoteWebDriver;

            } else {
                driver = new InternetExplorerDriver();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(180, TimeUnit.SECONDS).setScriptTimeout(15, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                bypassIECertificateIfAny(driver);
                return driver;
            }

        }
    },

    HTMLUNIT {
        public WebDriver getDriver() {

            RemoteWebDriver remoteWebDriver;

            if (Boolean.valueOf(getRemoteExecution())) {
                DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
                remoteWebDriver = (RemoteWebDriver) new Augmenter().augment(new RemoteWebDriver(getHubUrl(), capabilities));
                remoteWebDriver.setFileDetector(new LocalFileDetector());
                return remoteWebDriver;
            } else
                return new HtmlUnitDriver(true);
        }
    },

    SAFARI {
        public WebDriver getDriver() {

            RemoteWebDriver remoteWebDriver;
            WebDriver driver;

            if (Boolean.valueOf(getRemoteExecution())) {
                DesiredCapabilities capabilities = DesiredCapabilities.safari();
                remoteWebDriver = (RemoteWebDriver) new Augmenter().augment(new RemoteWebDriver(getHubUrl(), capabilities));
                remoteWebDriver.setFileDetector(new LocalFileDetector());
                return remoteWebDriver;
            } else

                driver = new SafariDriver();
            driver.manage().window().maximize();
            return driver;
        }
    },
    PHANTOMJS {
        public WebDriver getDriver() {
            RemoteWebDriver remoteWebDriver;
            WebDriver driver;

            if (Boolean.valueOf(getRemoteExecution())) {
                DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
                remoteWebDriver = (RemoteWebDriver) new Augmenter().augment(new RemoteWebDriver(getHubUrl(), capabilities));
                remoteWebDriver.setFileDetector(new LocalFileDetector());
                return remoteWebDriver;
            } else

               driver = new PhantomJSDriver();
           driver.manage().window().maximize();
            return driver;
        }
    };


    public abstract WebDriver getDriver();

    protected URL getHubUrl() {

        URL HUB_URL = null;

        try {
            HUB_URL = new URL(System.getProperty(BrowserProperties.HUB_URL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return HUB_URL;

    }

    protected String getRemoteExecution() {

        return System.getProperty(BrowserProperties.remoteExecution);

    }

    protected void bypassIECertificateIfAny(WebDriver driver) {
        if (System.getProperty(BrowserProperties.BROWSER_NAME) != null && System.getProperty(BrowserProperties.BROWSER_NAME).equalsIgnoreCase("IEXPLORE")) {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS).pageLoadTimeout(180, TimeUnit.SECONDS).setScriptTimeout(15, TimeUnit.SECONDS);
            List<WebElement> list = driver.findElements(By.id("overridelink"));

            while (list != null && list.size() > 0) {
                list.get(0).click();
                list = driver.findElements(By.id("overridelink"));
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS).pageLoadTimeout(180, TimeUnit.SECONDS).setScriptTimeout(15, TimeUnit.SECONDS);
        }
    }

    protected void setIECapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
        capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, true);
        capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
        capabilities.setCapability(InternetExplorerDriver.SILENT, true);
        capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        //capabillities.setCapability("max-duration", 600);
        //capabilities.setCapability("platform", Platform.WINDOWS);
        //capabillities.setCapability("selenium-version", "2.44.0");
        //capabillities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.google.com");
    }

}
