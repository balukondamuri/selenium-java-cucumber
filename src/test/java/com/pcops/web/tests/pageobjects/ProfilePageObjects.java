package com.pcops.web.tests.pageobjects;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.pcops.web.driver.utilities.BrowserProperties;
import com.pcops.web.driver.utilities.NavigationKeywords;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by bkondamu on 12/14/15.
 */
public class ProfilePageObjects {


    WebDriver driver=null;
    NavigationKeywords navigationKeywords;

    public ProfilePageObjects(WebDriver driver) {
        this.driver = driver;
        navigationKeywords = new NavigationKeywords(this.driver);
    }

    private String expecteId;
    private final static String definitions = "//a[contains(@href,'#/definitions')]";
    private final static String ELEMENT_TABLE = "//table[@class='table table-striped']/tbody/tr[1]/td[6]/div/ul";
    private final static String ELEMENT_HEADER = "//table[@class='table table-striped']/thead/tr";

    private void waitForDriver(By location){
        WebDriverWait wait = new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.elementToBeClickable(location));

    }

    public void clickOnSelectDropDownButton() throws Exception {
        waitForDriver(By.xpath(definitions));
        navigationKeywords.click(By.xpath(definitions));
    }

    public List viewTheDropdownActionItems() throws Exception {
        List<String> actualActionItems = new ArrayList<>();
        String actionItemsText = null;
        try {
            WebElement elements = navigationKeywords.getElement(By.xpath(ELEMENT_TABLE));
            List<WebElement> listOfElements = elements.findElements(By.tagName("li"));
            int sizeOfItems = listOfElements.size();
            for (int i = 1; i <= sizeOfItems; ++i) {
                driver.findElement(By.xpath("//*[@id='simple-btn-keyboard-nav']")).click();
                actionItemsText = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[6]/div/ul/li[" + i + "]/a")).getText();
                if (!actionItemsText.equals(null)) {
                    actionItemsText = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[6]/div/ul/li[" + i + "]/a")).getText();
                }
                actualActionItems.add(actionItemsText);
            }

        } catch (NoSuchElementException e) {
            throw new Exception("Unable to find the element at to view the actions");
        }

        return actualActionItems;
    }

    public void clickOnViewDefinition() throws Exception {
        try {
            String currentWindowHandleId = driver.getWindowHandle();
            String newWindUrl = null;
            WebElement elements = navigationKeywords.getElement(By.xpath(ELEMENT_TABLE));
            List<WebElement> listOfElements = elements.findElements(By.tagName("li"));
            int sizeOfItems = listOfElements.size();
            for (int i = 1; i < sizeOfItems; ++i) {
                String actionItemsText = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[6]/div/ul/li[" + i + "]/a")).getText();
                newWindUrl = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[6]/div/ul/li[" + i + "]/a")).getAttribute("href");
                System.out.println("new window url ---> " + newWindUrl);
                if (actionItemsText.equals("View Definition")) {
                    driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[6]/div/ul/li[" + i + "]")).click();
                    break;
                }
            }
            if (!System.getProperty(BrowserProperties.BROWSER_NAME).equals("PHANTOMJS")) {
                navigationKeywords.waitForSecondWindowOpen(2);
                System.out.println("current window " + currentWindowHandleId);
                Set<String> handles = driver.getWindowHandles();
                handles.remove(currentWindowHandleId);
                expecteId = handles.iterator().next();
            } else {
                expecteId = newWindUrl;
            }

        } catch (NoSuchElementException e) {
            throw new Exception("Unable to find the action elements");
        }

    }

    public Response verifyInNewWindow() {
        Response response = null;
        if (!System.getProperty(BrowserProperties.BROWSER_NAME).equals("PHANTOMJS")) {
            driver.switchTo().window(expecteId);
            response = RestAssured.when().get(driver.getCurrentUrl());
        } else {
            System.out.println("Rest URL --> " + expecteId);
            response = RestAssured.when().get(expecteId);
        }

        return response;

    }

    public List verifyingTheGridHeaders() throws Exception {
        List<String> actualContent = new ArrayList<>();
        try {
            WebElement headerElement = navigationKeywords.getElement(By.xpath(ELEMENT_HEADER));
            List<WebElement> listOfHeaderElements = headerElement.findElements(By.tagName("th"));
            int sizeOfElements = listOfHeaderElements.size();
            String actualText = null;
            for (int i = 1; i <= sizeOfElements; i++) {
                actualText = driver.findElement(By.xpath("//table[@class='table table-striped']/thead/tr/th[" + i + "]")).getText();
                actualContent.add(actualText);
            }

        } catch (NoSuchElementException e) {
            throw new Exception("Unable to find the grid elements");
        }
        return actualContent;
    }

    public void verifyTheSortOptions() {

    }

    public void selectTheDepartmentAndClickMapDefinition() throws Exception {

        try {
            navigationKeywords.click(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[6]/div/button"));
            navigationKeywords.click(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[6]/div/ul/li[3]"));
        } catch (NoSuchElementException e) {
        }
    }

    public String getPreselectedValue() {
        return driver.findElement(By.xpath("//div[@class='host']/div/ul/li")).getText();
    }
}
