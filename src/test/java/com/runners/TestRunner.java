package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        //features = "src/test/resources/features/deleteItem.feature", // Path to your feature files
        features = {
                "src/test/resources/features/high_priority/login.feature",
                "src/test/resources/features/high_priority/deleteItem.feature",
                "src/test/resources/features/high_priority/emailsVerification.feature"
        }, // Path to your feature files
        glue = {"com.stepdefinitions","com.hooks"}, // Path to your step definitions
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-html-report.html", "json:target/cucumber-reports/cucumber.json"},
        monochrome = true,
        // Possible additional configurations for parallelization
        tags = "@Regression and @HighPriority" // Example to run only scenarios tagged with @smoke
        //tags = "@HighPriority"
        //tags= "@LowPriority" // Optional: run specific tags, remove or change as needed
)
public class TestRunner extends AbstractTestNGCucumberTests {

    // This method is required to run tests in parallel using TestNG
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
