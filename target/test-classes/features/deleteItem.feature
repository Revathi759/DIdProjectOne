Feature: Delete Page Items Functionalities


  Scenario Outline: : Successfully upload a document and then delete it
    Given Enter "URL" and click I am on the login page
    When I enter <username> as username and <password> as password
    And I click the login button
    Then I should be logged in successfully on to the Dashboard page
    And I verify Dashboard is loaded
    And I upload the file <uploaded_files>
    And I navigate to "View All Documents" page
    #And I refresh the page
    And I sort the documents by "Uploaded" in descending order
    And I verify that <uploaded_files> is the first uploaded document
    When I select the second row for deletion
    Then the initial document count is recorded
    And I click the delete button and confirm the deletion
    Then the document count should decrease by 1
    Examples:
      | username                        | password    | uploaded_files |
      | "deidjuly7@mailinator.com" | "W3lcome!" |  "InstructionsToBakeCake2.txt" |
