
package com.hooks;

import com.pages.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScenarioHooks {

    @After // This method will run after every Cucumber scenario
    public void tearDown(Scenario scenario) {
        // Optional: You might want to take a screenshot if the scenario fails

        if (scenario.isFailed()) {
            // Add code here to capture a screenshot and embed it into the report
            byte[] screenshot = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed_Screenshot");
            System.out.println("Failed scenario");
        }

        // Call the quitDriver() method from your WebDriverFactory
        WebDriverFactory.quitDriver();
    }
}
