package com.web.tests.stepdefs;

import com.web.tests.pageobjects.WelcomePage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by bkondamu on 9/13/16.
 */
public class StepsDefs {


    WebDriver driver;
    private static final Logger logger = Logger.getLogger(StepsDefs.class.getName());
    WelcomePage profilePageObjects;

    public StepsDefs() {
        driver = CommonSteps.getDriver();
    }

    @Given("^Definition Page$")
    public void definitionPage() throws Throwable {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(@href,'#/definitions')]")).click();

    }

    @When("^the User clicks on Select$")
    public void theUserClicksOnSelect() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the following actions appear$")
    public void theFollowingActionsAppear() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}
