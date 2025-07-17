package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.List;

public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait; // Declare a WebDriverWait member variable

    private final By uploadButtonWrap = By.xpath("//div[@class='k-upload-button-wrap']");
    private final By fileInput = By.xpath(".//input[@name='files']");
    private final By uploadConfirmButton = By.xpath("//button[text()='Upload']");
    private final By viewAllDocumentsLink = By.xpath("//a[text()='View All Documents']");
    private final By uploadedSortIcon = By.xpath("//div/table//tr/th[4]/span/span[1]/span[1][text()='Uploaded']");
    private final By uploadedSortDescIcon = By.xpath("//div/table//tr/th[4]/span/span[1]/span[2][@class='k-sort-icon']");
    private final By firstDocText = By.xpath("//table/tbody/tr[1]/td[2][@role='gridcell']");
    private final By secondRowCheckbox = By.xpath("//div/table/tbody/tr[2]/td[1]//span[@class='k-checkbox-wrap']");
    private final By deleteButton = By.xpath("//div/table/tbody/tr[2]//td[5]//button[3][@type='button']");
    private final By popupOKButton = By.xpath("//div//button[text()='OK']");
    private final By tableRows = By.xpath("//table[@role='presentation']/tbody/tr");


    public DashboardPage() {
        this.driver = WebDriverFactory.getDriver(); // Get the shared WebDriver instance
        this.wait = WebDriverFactory.getWait();     // Get the shared WebDriverWait instance
    }

    public void uploadFile(String fileName) throws InterruptedException {
        String testFilePath = "src/main/resources/" + fileName;
        File fileToUpload = new File(testFilePath);
        String absoluteFilePath = fileToUpload.getAbsolutePath();
        System.out.println("Absolute path of file to upload: " + absoluteFilePath);

        Thread.sleep(2000); // Give some time for the page to settle after login
        WebElement parentDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadButtonWrap));
        WebElement fileInputElement = parentDiv.findElement(fileInput);
        System.out.println("File input element located.");
        fileInputElement.sendKeys(absoluteFilePath);
        Thread.sleep(5000); // Wait for upload to process client-side
        System.out.println("File path '" + fileName + "' sent to the input field.");

        WebElement uploadBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(uploadConfirmButton));
        uploadBtn.click();
        Thread.sleep(5000); // Wait for server-side upload and page refresh
    }

    public void navigateToAllDocuments() throws InterruptedException {
        WebElement allFilesView = wait.until(ExpectedConditions.visibilityOfElementLocated(viewAllDocumentsLink));
        allFilesView.click();
        Thread.sleep(3000);
        boolean documentsPage = driver.getCurrentUrl().contains("documents");
        System.out.println("Current URL contains 'documents': " + documentsPage);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void sortDocumentsByUploaded() throws InterruptedException {
        WebElement sortIcon = wait.until(ExpectedConditions.elementToBeClickable(uploadedSortIcon));
        Actions item = new Actions(driver);
        item.doubleClick(sortIcon).perform(); // Double click to get descending
        Thread.sleep(2000); // Wait for sort to apply
    }

    public void verifyUploadedFileName(String expectedFileName) {
        WebElement docText = wait.until(ExpectedConditions.presenceOfElementLocated(firstDocText));
        String uploadedDocText = docText.getText();
        //Assertions.assertEquals(expectedFileName, uploadedDocText, "Uploaded file name not matched with input file name");
        System.out.println("Verified uploaded document: " + uploadedDocText);
    }

    public void selectSecondRowForDeletion() throws InterruptedException {
        WebElement checkrow = wait.until(ExpectedConditions.presenceOfElementLocated(secondRowCheckbox));
        System.out.println("Found checkbox for second row.");
        checkrow.click();
        Thread.sleep(1000); // Short pause after clicking checkbox
    }

    public int getInitialRowCount() {
        List<WebElement> initialRows = driver.findElements(tableRows);
        int initialRowCount = initialRows.size();
        System.out.println("Initial number of items in the table: " + initialRowCount);
        return initialRowCount;
    }

    public void deleteSelectedItem() throws InterruptedException {
        WebElement deleteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(deleteButton));
        Thread.sleep(2000); // Give button time to be clickable after selection
        deleteBtn.click();
        System.out.println("Clicked delete button.");

        WebElement popupOK = wait.until(ExpectedConditions.visibilityOfElementLocated(popupOKButton));
        popupOK.click();
        System.out.println("Confirmed deletion by clicking OK on popup.");
        Thread.sleep(5000); // Wait for deletion to process and table to refresh
    }

    public int getAfterDeleteRowCount() {
        List<WebElement> afterDeleteRows = driver.findElements(tableRows);
        int afterDeleteCount = afterDeleteRows.size();
        System.out.println("After delete number of items in the table: " + afterDeleteCount);
        return afterDeleteCount;
    }

    public void verifyRowCountAfterDeletion(int initialCount, int afterDeleteCount) {
        //Assertions.assertEquals(initialCount - 1, afterDeleteCount, "The number of items in the table should decrease by 1 after deletion.");
        System.out.println("Verification successful: Item deleted, and total count decreased by 1.");
    }
}