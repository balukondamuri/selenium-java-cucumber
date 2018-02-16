package com.pcops.web.driver.webdriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by bkondamu on 9/26/15.
 */
public class DynamicBrowser extends EventFiringWebDriver{

    public DynamicBrowser() throws Exception {
         super(WebDriverFactory.getInstance().getDriver());

    }

}
