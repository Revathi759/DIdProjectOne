package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        //features = "src/test/resources/features/deleteItem.feature", // Path to your feature files
        features = "src/test/resources/features/login.feature", // Path to your feature files
        glue = {"com.stepdefinitions","com.hooks"}, // Path to your step definitions
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-html-report.html", "json:target/cucumber-reports/cucumber.json"}
        //tags = "@DeleteFunctionality" // Optional: run specific tags, remove or change as needed
)
public class TestRunner extends AbstractTestNGCucumberTests {

    // This method is required to run tests in parallel using TestNG
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
