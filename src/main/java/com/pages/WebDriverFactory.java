package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager; // Import WebDriverManager
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverFactory {

    public static WebDriver driver; // Static to hold a single instance

    public static WebDriverWait wait; // Static to hold a single instance of WebDriverWait
    // Private constructor to prevent instantiation
    private WebDriverFactory() {
        // You can add logic here to read browser type from a config file or system property
        String browser = System.getProperty("browser", "chrome"); // Default to chrome

        if (driver == null) { // Only initialize if no driver exists
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup(); // Setup ChromeDriver
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup(); // Setup FirefoxDriver
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup(); // Setup EdgeDriver
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            // Initialize WebDriverWait here after the driver is initialized
            // 20 seconds is the maximum time to wait for a condition
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }

    // Public method to get the driver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            new WebDriverFactory();// Initialize if not already initialized
        }
        return driver;
    }
    // Public method to get the WebDriverWait instance
    public static WebDriverWait getWait() {
        if (wait == null) { // Ensure wait is initialized even if getDriver() wasn't explicitly called first
            new WebDriverFactory(); // This will initialize both driver and wait
        }
        return wait;
    }

    // Method to close the browser
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Set to null after quitting to allow re-initialization if needed
        }
    }
}