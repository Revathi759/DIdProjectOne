package com.stepdefinitions;

import com.pages.WebDriverFactory;
import com.pages.DashboardPage;
import com.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageSteps {
    public WebDriver driver;
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public WebDriverFactory webDriverManager; // To manage the driver life cycle

    @Before
    public void setup() {
        //driver = WebDriverFactory.getDriver(); // Get the shared driver instance
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @After
    public void teardown() {
        if (driver!= null) {
            WebDriverFactory.quitDriver(); // Quits the driver after each scenario
        }
    }
    @Given("Enter {string} and click I am on the login page")
    public void enterAndClickIAmOnTheLoginPage(String url) {
        loginPage.navigateToUrl(url);
    }


    @When("I enter {string} as username and {string} as password")
    public void i_enter_as_username_and_as_password(String username, String password) {
        // Write code here that turns the phrase above into concrete actions
        loginPage.enterCredentials(username, password);
        //throw new io.cucumber.java.PendingException();
    }

    @When("I click the login button")
    public void i_click_the_login_button() throws InterruptedException{
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickLoginButton();
        //throw new io.cucumber.java.PendingException();
    }

    @Then("I should be logged in successfully on to the Dashboard page")
    public void iShouldBeLoggedInSuccessfullyOnToTheDashboardPage() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertTrue(loginPage.isDashboardLoaded(), "Navigation to Dashboard page failed.");
    }

    @And("I verify Dashboard is loaded")
    public void iVerifyDashboardIsLoaded() {
        boolean isLoaded = loginPage.isDashboardLoaded();
        Assert.assertTrue(isLoaded,"The Dashboard Page was not loaded successfully");
    }


    @And("I click on log out button")
    public void iClickOnLogOutButton() {
        loginPage.iClickOnLogOutButton();
    }

    @And("I verify successfully logged out and on Login page")
    public void iVerifySuccessfullyLoggedOutAndOnLoginPage() {
        boolean isLoginPage=loginPage.iVerifyNavigatedToLogoutPage();
        Assert.assertTrue(isLoginPage,"User unable to navigate to the Login page, after logout is clicked");
    }

    @Then("I should see an error {string}")
    public void iShouldSeeAnErrorMessage(String expectedErrorMessage) {
        //System.out.println("reached");
        String actualErrorMessage=loginPage.getErrorMessageText(expectedErrorMessage);
        System.out.println("error message: "+ actualErrorMessage);
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }
}
