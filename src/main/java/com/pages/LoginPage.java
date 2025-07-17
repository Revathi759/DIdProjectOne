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
        WebElement loginBtn = driver.findElement(loginButton);
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
}
