@Regression
Feature: De-Id Application Emails Functionality Verifications

  Scenario Outline: Successfully reset the password
    Given Enter "URL" and click I am on the login page
    When I Click on the Forgot Password Link
    And I Enter <username> email into the Email input field
    And I click on the Reset Password Button
    And I get Forgot Password Email Confirmation dialog window
    And I Enter Mailinator <Email URL> link and click
    And I Enter <username> into the input box and click
    And I Click on the Reset Your Password mail
    And I Click on the Click here link
    And I Enter <username> and <password> and <Confirm Password> values
    And I click on the Reset button
    And I receive Reset Password Confirmation Dialog box
    And I click on the Click here to login link
    And I navigate to the Login page
    And I enter <username> as username and <password> as password
    And I click the login button
    Then I should be logged in successfully on to the Dashboard page
    And I verify Dashboard is loaded
    Examples:
      | username                        | password    | Confirm Password | Email URL |
      |"deidtestmay209@mailinator.com"    | "W3lcome!1"   | "W3lcome!1"       | "https://www.mailinator.com/" |

  @HighPriority
  Scenario Outline:Successfully Sign Up for new account
    Given Enter "URL" and click I am on the login page
    When I click on the Sign up button
    And I navigating to the Create new account page
    And I enter <username> and <password> and <Confirm Password>
    And I click on Register button
    And I receive Registration Confirmation message
    And I Enter Mailinator <Email URL> link and click
    And I Enter <username> into the input box and click
    And I Click on the Confirm your email mail
    And I Click on the Click here link
    And I navigating to the Confirm email page
    And I click on the Sign In button
    And I navigate to the Login page
    And I enter <username> as username and <password> as password
    And I click the login button
    Then I should be logged in successfully on to the Dashboard page
    And I verify Dashboard is loaded
    Examples:
      | username                        | password    | Confirm Password | Email URL |
      |"deidjuly04@mailinator.com"    | "W3lcome!1"   | "W3lcome!1"       | "https://www.mailinator.com/" |
