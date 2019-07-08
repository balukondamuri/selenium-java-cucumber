package com.web.tests.stepdefs;

import com.web.driver.webdriver.DynamicBrowser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by bkondamu on 7/18/16.
 */
public class CommonSteps {

    private static final Logger logger = Logger.getLogger(CommonSteps.class.getName());
    static WebDriver driver;


    private static final String userName_XPATH = "//input[@id='username']";
    private static final String password_XPATH = "//input[@id='password']";
    private static final String signinButton_XPATH = "//input[@id='login-button']";

    public CommonSteps(DynamicBrowser driver) {
        logger.info("Launching browser driver");
        this.driver = driver;
    }


    public static WebDriver getDriver() {
        return driver;
    }

    @Given("^Profile login page$")
    public void profileLoginPage() throws Throwable {
        logger.info("Lunching profile home page");
        driver.get("https://pcopsprofilesb.premierinc.com/pcops/profile-ui/");

    }

    @When("^user logged in to profile home page$")
    public void launchProfileHomePage(List<Map<String, String>> userDetails) {
        logger.info("login to profiles");
        String userName = userDetails.get(0).get("user-name");
        String password = userDetails.get(0).get("password");
        driver.findElement(By.xpath(userName_XPATH)).sendKeys(userName);
        driver.findElement(By.xpath(password_XPATH)).sendKeys(password);
        driver.findElement(By.xpath(signinButton_XPATH)).click();
        logger.info("Logged in to Profiles");


    }
}
