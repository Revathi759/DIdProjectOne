package com.stepdefinitions;

import com.pages.DeletePage;
import com.pages.WebDriverFactory;
import com.pages.DashboardPage;
import com.pages.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DeletePageSteps{
    public WebDriver driver;
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public DeletePage deletePage;
    public WebDriverFactory webDriverFactory; // To manage the driver life cycle
    public int initialRowCount;

    @Before
    public void setup() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }


    @And("I upload the file {string}")
    public void iUploadTheFile(String fileName) throws InterruptedException {
        dashboardPage.uploadFile(fileName);
    }

    @And("I navigate to {string} page")
    public void iNavigateToPage(String pageName) throws InterruptedException {
        if ("View All Documents".equals(pageName)) {
            dashboardPage.navigateToAllDocuments();
        } else {
            System.out.println("Navigation to '" + pageName + "' is not implemented.");
        }
    }

    /*@And("I refresh the page")
    public void iRefreshThePage() {
        dashboardPage.refreshPage();
    }*/

    @And("I sort the documents by {string} in descending order")
    public void iSortTheDocumentsByInDescendingOrder(String column) throws InterruptedException {
        if ("Uploaded".equals(column)) {
            dashboardPage.sortDocumentsByUploaded();
        } else {
            System.out.println("Sorting by '" + column + "' is not implemented or not applicable.");
        }
    }

    @And("I verify that {string} is the first uploaded document")
    public void iVerifyThatIsTheFirstUploadedDocument(String expectedFileName) {
        dashboardPage.verifyUploadedFileName(expectedFileName);
    }

    @When("I select the second row for deletion")
    public void iSelectTheSecondRowForDeletion() throws InterruptedException {
        dashboardPage.selectSecondRowForDeletion();
    }

    @Then("the initial document count is recorded")
    public void theInitialDocumentCountIsRecorded() {
        initialRowCount = dashboardPage.getInitialRowCount();
    }

    @And("I click the delete button and confirm the deletion")
    public void iClickTheDeleteButtonAndConfirmTheDeletion() throws InterruptedException {
        dashboardPage.deleteSelectedItem();
    }

    @Then("the document count should decrease by {int}")
    public void theDocumentCountShouldDecreaseBy(int expectedDecrease) {
        int afterDeleteCount = dashboardPage.getAfterDeleteRowCount();
        Assert.assertEquals(initialRowCount - expectedDecrease, afterDeleteCount, "Document count did not decrease as expected.");
    }


}
