package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class EmailsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //private final By uploadButtonWrap = By.xpath("//div[@class='k-upload-button-wrap']");
    private final By forgotPasswordLink=By.xpath("//div/a[text()='Forgot password?']");
    private final By userEmail=By.xpath("//div/input[@name='Input.Email']");
    private final By resetPasswordButt=By.xpath("//div/button[text()='Reset Password']");
    private final By forgotEmailConfDialog=By.xpath("//div/h2[text()='Forgot Password Email Confirmation']");
    private final By mailbox=By.id("search");
    private final By resetYourMail=By.xpath("//tr[1]/td[contains(text(), 'Reset your password')]");
    private final By clickHereLink=By.xpath("/html/body/a[contains(text(),'clicking here')]");
    private final By usersEmail=By.name("Input.Email");
    private final By userPassword=By.name("Input.Password");
    private final By passwordConf=By.name("Input.ConfirmPassword");
    private final By resetButton=By.xpath("//div/button[text()='Reset']");
    private final By resetPassDialogConf=By.xpath("//div/h2[text()='Reset password confirmation']");
    private final By clickHereLogin=By.xpath("//div/a[text()='click here to log in']");
    private final By signUpLinkButton=By.xpath("//a[text()='Sign Up']");
    private final By createNewUser=By.xpath("//h2[text()='Create a new account.']");
    private final By newRegistration=By.xpath("//button[text()='Register']");
    private final By registrationConfirm=By.xpath("//h2[text()='Register Confirmation']");
    private final By confirmYourEmail=By.xpath("//tr[1]/td[contains(text(), 'Confirm your email')]");
    private final By confirmEmail=By.xpath("//h2[text()='Confirm Email']");
    private final By signinButton=By.xpath("//a[text()='Sign In']");


    public EmailsPage() {
        this.driver = WebDriverFactory.getDriver(); // Get the shared WebDriver instance
        this.wait = WebDriverFactory.getWait();
    }


    public void clickForgotPassword() throws InterruptedException {
        WebElement forgotLink =wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordLink));
        forgotLink.click();
        Thread.sleep(3000);
    }

    public void enterUserEmailIntoEmailField(String username) {
        WebElement userEmails=wait.until(ExpectedConditions.visibilityOfElementLocated(userEmail));
        userEmails.sendKeys(username);
    }

    public void clickResetPasswordLink() {
        WebElement resetPassButton=wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordButt));
        resetPassButton.click();
    }

    public String forgotPasswordEmailConfirmation() {
        WebElement forgotConfirmDialog=wait.until(ExpectedConditions.visibilityOfElementLocated(forgotEmailConfDialog));
        return forgotConfirmDialog.getText();

    }

    public void gotoEmailURLLink(String emailUrl) {
        driver.get(emailUrl);
    }

    public void EnterUserNameAndClick(String username) {
        WebElement mailInbox=wait.until(ExpectedConditions.visibilityOfElementLocated(mailbox));
        mailInbox.sendKeys(username);
        WebElement go=driver.findElement(By.xpath("//div/button[text()='GO']"));
        go.click();
    }

    public void ClickOnResetYourPasswordMail() throws InterruptedException {
        WebElement resetYourMailToSelect=wait.until(ExpectedConditions.visibilityOfElementLocated(resetYourMail));
        Thread.sleep(3000);
        resetYourMailToSelect.click();

    }

    public void ClickOnClickHereLink() throws InterruptedException {
        String originalTabHandle = driver.getWindowHandle();
        System.out.println("Original Tab Handle: " + originalTabHandle);
        WebElement iframeElement = driver.findElement(By.id("html_msg_body"));
        driver.switchTo().frame(iframeElement);
        Thread.sleep(1000);
        WebElement clickHere=wait.until(ExpectedConditions.elementToBeClickable(clickHereLink));
        clickHere.click();
        Thread.sleep(2000);
        driver.switchTo().defaultContent();

    }

    public void enterUsernamePasswordConfirmPassword(String username, String password, String confirmPassword) {
        // Step 5: Get all window handles and switch to the new tab
        Set<String> allWindowHandles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(allWindowHandles);
        String newTabHandle = tabs.get(tabs.size() - 1); // New tab is usually the last one
        driver.switchTo().window(newTabHandle);
        System.out.println("Switched to New Tab. Handle: " + driver.getWindowHandle());
        WebElement userPassword1=driver.findElement(userPassword);
        userPassword1.sendKeys(password);
        WebElement passwordConf1=driver.findElement(passwordConf);
        passwordConf1.sendKeys(confirmPassword);
        WebElement email1=wait.until(ExpectedConditions.visibilityOfElementLocated(usersEmail));
        email1.sendKeys(username);
    }

    public void clickOntheResetButton() {
        WebElement resetButton1= driver.findElement(resetButton);
        resetButton1.click();
    }

    public String receiveResetPasswordConfirmationDialogBox() {
        WebElement resetPassDialogConf1=wait.until(ExpectedConditions.visibilityOfElementLocated(resetPassDialogConf));
        return resetPassDialogConf1.getText();
    }

    public void clickOnClickHereToLoginLink() {
        WebElement clickHereToLogin1=wait.until(ExpectedConditions.visibilityOfElementLocated(clickHereLogin));
        clickHereToLogin1.click();
    }

    public String navigateTotheLoginPage() {
        return driver.getCurrentUrl();

    }
    public void clickOnSignUpLink() throws InterruptedException {
        WebElement clickSignup=wait.until(ExpectedConditions.elementToBeClickable(signUpLinkButton));
        Thread.sleep(5000);
        clickSignup.click();
    }
    public String iAmOnCreateNewAccountPage(){
        WebElement createNewAccount=wait.until(ExpectedConditions.visibilityOfElementLocated(createNewUser));
        return createNewAccount.getText();
    }
    public void enterUserPasswordConfirmPassword(String username,String password,String confirmPassword){
        WebElement email1=wait.until(ExpectedConditions.visibilityOfElementLocated(usersEmail));
        email1.sendKeys(username);
        WebElement userPassword1=driver.findElement(userPassword);
        userPassword1.sendKeys(password);
        WebElement passwordConf1=driver.findElement(passwordConf);
        passwordConf1.sendKeys(confirmPassword);

    }
    public void clickonRegisterButton() throws InterruptedException {
        WebElement registrerButton=wait.until(ExpectedConditions.elementToBeClickable(newRegistration));
        Thread.sleep(3000);
        registrerButton.click();
    }
    public String registrationConfirmation(){
        WebElement registrationConfirm1=wait.until(ExpectedConditions.visibilityOfElementLocated(registrationConfirm));
        return registrationConfirm1.getText();
    }
    public void clickConfirmYourEmail(){
        WebElement confirmYourEmail1=wait.until(ExpectedConditions.elementToBeClickable(confirmYourEmail));
        confirmYourEmail1.click();
    }
    public String confirmEmailMessage(){
        Set<String> allWindowHandles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(allWindowHandles);
        String newTabHandle = tabs.get(tabs.size() - 1); // New tab is usually the last one
        driver.switchTo().window(newTabHandle);
        System.out.println("Switched to New Tab. Handle: " + driver.getWindowHandle());
        WebElement confirmEmail1=wait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmail));
        return confirmEmail1.getText();
    }
    public void clickSignInBButton(){
        WebElement signInButton=wait.until(ExpectedConditions.elementToBeClickable(signinButton));
        signInButton.click();
    }
}
