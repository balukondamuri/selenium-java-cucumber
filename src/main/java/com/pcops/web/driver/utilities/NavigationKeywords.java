package com.pcops.web.driver.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by bkondamu on 9/26/15.
 */

/**
 * This class is for customised or user defined functions/methods for selenium webdriver
 **/
public class NavigationKeywords {

    WebDriver driver;
    Wait<WebDriver> wait;

    /**
     * Constructor for the UserMethod Class to load single session webdriver
     **/
    public NavigationKeywords(WebDriver driver) {
        this.driver = driver;
        setTimeout(30);
    }

    /**
     * Setting timeout for complete execution
     **/
    public void setTimeout(long seconds) {
        wait = new FluentWait<>(driver).withTimeout(seconds, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * This method is for click on the element
     **/
    public void click(By xpath) throws Exception {
        try {
            driver.findElement(xpath).click();
        } catch (NoSuchElementException e) {
            throw new Exception("Unable to find the xpath for click");
        }

    }

    /**
     * Method will wait until open the respective number of windows
     **/

    public void waitForSecondWindowOpen(int i) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 40);
            wait.until((ExpectedCondition<Boolean>) driver1 -> driver1.getWindowHandles().size() == i);
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new Exception("Unable to find the respective windows");
        }
    }

    public WebElement getElement(By xpath) {
        WebElement element = driver.findElement(xpath);
        return element;
    }
}
