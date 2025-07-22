package com.stepdefinitions;

import com.pages.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EmailsPageSteps {
    public WebDriver driver;
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public DeletePage deletePage;
    public EmailsPage emailspage;
    public WebDriverFactory webDriverFactory; // To manage the driver life cycle
    //public int initialRowCount;

    @Before
    public void setup() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
         emailspage =new EmailsPage();
    }
//    @Given("Enter {string} and click I am on the login page")
//    public void enterAndClickIAmOnTheLoginPage(String url) {
//        loginPage.navigateToUrl(url);
//    }

    @When("I Click on the Forgot Password Link")
    public void iClickOnTheForgotPasswordLink() throws InterruptedException {
        emailspage.clickForgotPassword();
    }

    @And("I Enter {string} email into the Email input field")
    public void iEnterUsernameEmailIntoTheEmailInputField(String username) {
        emailspage.enterUserEmailIntoEmailField(username);
    }

    @And("I click on the Reset Password Button")
    public void iClickOnTheResetPasswordButton() {
        emailspage.clickResetPasswordLink();
    }

    @And("I get Forgot Password Email Confirmation dialog window")
    public void iGetForgotPasswordEmailConfirmationDialogWindow() {
        String ForgotConfDialogMessage=emailspage.forgotPasswordEmailConfirmation();
        System.out.println(ForgotConfDialogMessage);
    }

    @And("I Enter Mailinator {string} link and click")
    public void iEnterMailinatorEmailURLLinkAndClick(String emailUrl) {
        emailspage.gotoEmailURLLink(emailUrl);
    }

    @And("I Enter {string} into the input box and click")
    public void iEnterUsernameIntoTheInputBoxAndClick(String username) {
        emailspage.EnterUserNameAndClick(username);
    }

    @And("I Click on the Reset Your Password mail")
    public void iClickOnTheResetYourPasswordMail() throws InterruptedException {
        emailspage.ClickOnResetYourPasswordMail();
    }

    @And("I Click on the Click here link")
    public void iClickOnTheClickHereLink() throws InterruptedException {
        emailspage.ClickOnClickHereLink();
    }

    @And("I Enter {string} and {string} and {string} values")
    public void iEnterUsernameAndPasswordAndConfirmPasswordValues(String username,String password,String confirmPassword) {
        emailspage.enterUsernamePasswordConfirmPassword(username,password,confirmPassword);
    }

    @And("I click on the Reset button")
    public void iClickOnTheResetButton() {
        emailspage.clickOntheResetButton();
    }

    @And("I receive Reset Password Confirmation Dialog box")
    public void iReceiveResetPasswordConfirmationDialogBox() {
        String resetPassConfDialog=emailspage.receiveResetPasswordConfirmationDialogBox();
        System.out.println("ReceiveResetPasswordConfirmationDialogBox: " +resetPassConfDialog);
    }

    @And("I click on the Click here to login link")
    public void iClickOnTheClickHereToLoginLink() {
        emailspage.clickOnClickHereToLoginLink();
    }

    @And("I navigate to the Login page")
    public void iNavigateToTheLoginPage() {
        String loginUrl=emailspage.navigateTotheLoginPage();
        Assert.assertEquals(loginUrl,"https://devstage.de-idapp.com/Account/Login","User Unable to navigate to the  Login Page");
    }


    @When("I click on the Sign up button")
    public void iClickOnTheSignUpButton() throws InterruptedException {
        emailspage.clickOnSignUpLink();
    }

    @And("I navigating to the Create new account page")
    public void iNavigatingToTheCreateNewAccountPage() {
        String newAccountPage=emailspage.iAmOnCreateNewAccountPage();
        Assert.assertEquals(newAccountPage,"Create a new account.","User not able to navigate to the Create new Account Page");
    }
   // @And("I enter <username> <password> and <Confirm Password>")
   // public void iEnterUsernamePasswordAndConfirmPassword(String username,String password,String confirmPassword) {
    //    emailspage.enterUserPasswordConfirmPassword(username,password,confirmPassword);
   // }

    @And("I enter {string} and {string} and {string}")
    public void i_enter_and(String username, String password, String confirmPassword) {
        emailspage.enterUserPasswordConfirmPassword(username,password,confirmPassword);
    }

    @And("I click on Register button")
    public void iClickOnRegisterButton() throws InterruptedException {
        emailspage.clickonRegisterButton();
    }

    @And("I receive Registration Confirmation message")
    public void iReceiveRegistrationConfirmationMessage() {
        String registrationConf=emailspage.registrationConfirmation();
        Assert.assertEquals(registrationConf,"Register Confirmation","User not able to navigate to the Registarion Confirmation Page");
    }

    @And("I Click on the Confirm your email mail")
    public void iClickOnTheConfirmYourEmailMail() {
        emailspage.clickConfirmYourEmail();
    }

    @And("I navigating to the Confirm email page")
    public void iNavigatingToTheConfirmEmailPage() {
        String confirmEmail=emailspage.confirmEmailMessage();
        Assert.assertEquals(confirmEmail,"Confirm Email","User not able to navigate to the confirm email page");

    }

    @And("I click on the Sign In button")
    public void iClickOnTheSignInButton() {
        emailspage.clickSignInBButton();
    }


}
