package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait; // Declare a WebDriverWait member variable

    private final By emailField = By.name("Input.Email");
    private final By passwordField = By.name("Input.Password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By logoutButton=By.id("logout");
    private By errorMessageGeneric=By.xpath("//div[text()='Error: Invalid login attempt.']");
    private By errorPasswordFieldRequired=By.xpath("//div[text()='The Password field is required.']");
    private By errorEmailFieldRequired=By.xpath("//div[text()='The Email field is required.']");
    // Constructor to receive the WebDriver instance
    public LoginPage() {
        this.driver = WebDriverFactory.getDriver(); // Get the shared WebDriver instance
        this.wait = WebDriverFactory.getWait();     // Get the shared WebDriverWait instance
    }
    public void navigateToUrl(String url){
        driver.get("https://devstage.de-idapp.com/");
    }

    public void enterCredentials(String username, String password) {
        //WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        WebElement usernameElement = driver.findElement(emailField);
        System.out.println("Entering username: " + username);
        usernameElement.sendKeys(username);

        WebElement passwordElement = driver.findElement(passwordField);
        System.out.println("Entering password: " + password);
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() throws InterruptedException {
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        System.out.println("Clicking login button.");
        loginBtn.click();
        Thread.sleep(5000);
    }

    public boolean isDashboardLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Dashboard']")));
            System.out.println("Login successful: Dashboard element found.");
            return true;
        } catch (Exception e) {
            System.err.println("Login failed or dashboard element not found: " + e.getMessage());
            return false;
        }
    }


    public void iClickOnLogOutButton() {
        WebElement logoutBtn=driver.findElement(logoutButton);
        logoutBtn.click();
    }

    public boolean iVerifyNavigatedToLogoutPage(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h2[text()='Sign in to your account']")));
            return true;
        } catch (Exception e) {
            System.out.println("Logout failed or User not in Login page:"+ e.getMessage());
            return false;
        }
    }

    public String getErrorMessageText(String expectedMessage){
        WebElement errorMessageElement = null;

        // 1. Common Error Message Element:
        // Try to find a general error message element that might display various messages.
        try {
            // Wait for the element to be visible
            errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageGeneric));
            // If found, return its text. We'll assert the content in the step definition.
            return errorMessageElement.getText();
        } catch (Exception e) {
            // Log, but don't throw, as we need to check other locations
            System.out.println("Generic error message element not found: " + e.getMessage());
        }

        // 2. Specific Field-Level Error Messages:
        // Check for error messages directly associated with input fields.
        try {
            errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorEmailFieldRequired));
            return errorMessageElement.getText();
        } catch (Exception e) {
            System.out.println("Email field specific error message element not found: " + e.getMessage());
        }

        try {
            errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorPasswordFieldRequired));
            return errorMessageElement.getText();
        } catch (Exception e) {
            System.out.println("Password field specific error message element not found: " + e.getMessage());
        }

        // 3. Fallback: Check if the message exists anywhere in the page source
        // This is a less ideal, but sometimes necessary, fallback.
        if (driver.getPageSource().contains(expectedMessage)) {
            System.out.println("Expected error message '" + expectedMessage + "' found in page source as a last resort.");
            return expectedMessage; // Return the expected message as it was found somewhere
        }

        // If none of the above found the element or its text, throw an AssertionError.
        // This will cause the test to fail.
        throw new AssertionError("Error message containing '" + expectedMessage + "' was not found on the page.");
    }
}
