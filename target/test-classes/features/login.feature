Feature: De-Id Application Login Functionalities

  Scenario Outline: Successfully login to the application
    Given Enter "URL" and click I am on the login page
    When I enter <username> as username and <password> as password
    And I click the login button
    Then I should be logged in successfully on to the Dashboard page
    And I verify Dashboard is loaded
    And I click on log out button
    And I verify successfully logged out and on Login page
    Examples:
      | username                        | password    |
      | "deidjuly8@mailinator.com" | "W3lcome!1" |

  Scenario Outline: Unsuccessful login with incorrect credentials
    Given Enter "URL" and click I am on the login page
    When I enter <username> as username and <password> as password
    And I click the login button
    Then I should see an error <message>
    Examples:
      | username                | password   | message   |
      | "deidjuly8@mailinator.com" | "W3lcome" | "Error: Invalid login attempt." |
      | "deidjuly@mailinator.com"  | "W3lcome!1" | "Error: Invalid login attempt." |
      | "deidjuly8@mailinator.com" | ""  | "The Password field is required." |
      | ""                         | "W3lcome!1" | "The Email field is required." |