package com.pcops.web.tests.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by bkondamu on 12/14/15.
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty","html:target/cucumber-html-reports", "json:target/cucumber.json"},
        glue = {"com.pcops.web.tests"},
        features = {"classpath:features/."},
        tags = {"~@WIP"},
        monochrome = true,
        strict = true)
public class CucumberRunnerTest {
}
