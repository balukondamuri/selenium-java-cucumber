package com.web.driver.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by bkondamu on 9/26/15.
 */
public class BrowserProperties {
    public static final String BROWSER_NAME = "browser.name";
    public static final String BROWSER_VERSION = "browser.version";
    public static final String HUB_URL = "hub.url";
    public static final String IE_EXE_DRIVER = "webdriver.ie.driver";
    public static final String CHROME_EXE_DRIVER = "webdriver.chrome.driver";
    public static final String remoteExecution = "remote.execution";
    public static final String PHANTOMJS_MAC_DRIVER = "phantomjs.binary.path";

    public BrowserProperties() throws Exception {

    }

    public Properties getBrowserProperties() throws Exception {

        Properties properties = new Properties();
        if (System.getProperty("browser") != null) {
            properties.load(new FileReader(new File(System.getProperty("browser"))));
        } else {
            InputStream inputStream = null;
            try {
                inputStream = new BrowserProperties().getClass().getClassLoader().getResourceAsStream("browser.properties");
                if (inputStream != null) {
                    properties.load(inputStream);
                }
            } catch (Exception e) {
                throw new Exception("Error occurred while reading the input stream (or unable to read the properties files) ", e);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        return properties;
    }

    public void setBrowserProperties(Properties properties) {
        if (properties.getProperty(HUB_URL) != null)
            System.setProperty(HUB_URL, properties.getProperty(HUB_URL));
        if (properties.getProperty(BROWSER_NAME) != null)
            System.setProperty(BROWSER_NAME, properties.getProperty(BROWSER_NAME));
        if (properties.getProperty(BROWSER_VERSION) != null)
            System.setProperty(BROWSER_VERSION, properties.getProperty(BROWSER_VERSION));
        if (properties.getProperty(IE_EXE_DRIVER) != null)
            System.setProperty(IE_EXE_DRIVER, getPath(properties.getProperty(IE_EXE_DRIVER)));
        if (properties.getProperty(CHROME_EXE_DRIVER) != null)
            System.setProperty(CHROME_EXE_DRIVER, getPath(properties.getProperty(CHROME_EXE_DRIVER)));
        if (properties.getProperty(remoteExecution) != null)
            System.setProperty(remoteExecution, properties.getProperty(remoteExecution));
        if (properties.getProperty(PHANTOMJS_MAC_DRIVER) != null)
            System.setProperty(PHANTOMJS_MAC_DRIVER, getPath(properties.getProperty(PHANTOMJS_MAC_DRIVER)));
    }

    private String getPath(String path) {
        File file = new File(path);
        String pathValue = file.getAbsolutePath();
        return pathValue;
    }
}
