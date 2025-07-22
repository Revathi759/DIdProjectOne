@Smoke
Feature: De-Id Application Login Functionalities

  @HighPriority
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

  @LowPriority
  Scenario Outline: Unsuccessful login with incorrect password credentials
    Given Enter "URL" and click I am on the login page
    When I enter <username> as username and <password> as password
    And I click the login button
    Then I should see an error <message>
    Examples:
      | username                | password   | message   |
      | "deidjuly8@mailinator.com" | "W3lcome" | "Error: Invalid login attempt." |

  @LowPriority
  Scenario Outline: Unsuccessful login with incorrect username credentials
    Given Enter "URL" and click I am on the login page
    When I enter <username> as username and <password> as password
    And I click the login button
    Then I should see an error <message>
    Examples:
      | username                | password   | message   |
      | "deidjuly@mailinator.com"  | "W3lcome!1" | "Error: Invalid login attempt." |

  @LowPriority
  Scenario Outline: Unsuccessful login with Empty Password value
    Given Enter "URL" and click I am on the login page
    When I enter <username> as username and <password> as password
    And I click the login button
    Then I should see an error <message>
    Examples:
      | username                | password   | message   |
      | "deidjuly8@mailinator.com" | ""  | "The Password field is required." |

  @LowPriority
  Scenario Outline: Unsuccessful login with Empty username value
    Given Enter "URL" and click I am on the login page
    When I enter <username> as username and <password> as password
    And I click the login button
    Then I should see an error <message>
    Examples:
      | username                | password   | message   |
      | ""                         | "W3lcome!1" | "The Email field is required." |