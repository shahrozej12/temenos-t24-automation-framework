package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

public class functions {
    private WebDriver driver;
    private WebDriverWait wait;


    // Method to pause execution for a specified time in milliseconds
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

        public void login() {

            // Initialize Chrome options (you can add options if necessary)
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\Shahroze.Janjua\\Downloads\\chrome-win64_stable\\chrome-win64\\chrome.exe");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            // Initialize WebDriverWait
            wait = new WebDriverWait(driver, Duration.ofSeconds(45));
            driver.get("http://10.111.201.244:9081/T24UAT/servlet/BrowserServlet");
        }

    public void iFillEnterLoginCredentialsForT24(String username, String password) {
        // Locate the username and password fields and fill them
        driver.findElement(By.xpath(Xpaths.T24_Username)).sendKeys(username);
        pause(1000);
        driver.findElement(By.xpath(Xpaths.T24_Password)).sendKeys(password);
    }

    public void perform() {
        login();
        iFillEnterLoginCredentialsForT24("SHAHROZE01", "shahroze01");
        iLoginInT24();
        enterAndPressTickButton("CUSTOMER,");
        enterCIFNoAndViewIt();
    }

    public void iLoginInT24() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpaths.T24_SignIn)));
        loginButton.click();
        pause(2000);
    }

    public void enterAndPressTickButton(String arg0) {
        driver.switchTo().frame(0);
        driver.findElement(By.xpath(Xpaths.T24_Text)).sendKeys(arg0);
        pause(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpaths.T24_TickButton))).click();
        // Switch back to the main page
        driver.switchTo().defaultContent();


    }

    public void close() {
        if (driver != null) {
            driver.quit();
            pause(2000);
        }
    }


    public void enterCIFNoAndViewIt() {
        // Get the current window handle
        String mainWindowHandle = driver.getWindowHandle();

        // Wait for the new window or tab to open
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // Wait for 2 windows

        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Switch to the new window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Switched to new window.");

                    while (ExcelHelper.hasNextRow()) {
                        Map<String, String> data = ExcelHelper.getNextRow();
                        if (data == null || data.isEmpty()) {
                            throw new RuntimeException("No data found in the Excel file or currentRowData is null.");
                        }

                    // Enter the CIF No into the customer field
                    WebElement customerIdField = driver.findElement(By.xpath(Xpaths.T24_Customer_Text));
                    String cifNumber = data.get("CIF Number");
                    if (cifNumber != null && cifNumber.endsWith(".0")) {
                        cifNumber = cifNumber.substring(0, cifNumber.length() - 2);  // Remove ".0"
                    }
                    customerIdField.sendKeys(cifNumber);
                    pause(2000);
                    WebElement viewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpaths.T24_Edit_Customer)));
                    viewButton.click();
                    pause(1000);
                        updateFieldIfNeeded(Xpaths.MNEMONIC, "Mnemonic", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Short_Name, "Short Name", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.GB_Name, "GB Name", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Sector, "Sector", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Language, "Language", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.GB_Street, "Street", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Post_Code, "Post Code", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Account_Officer, "Account Officer", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Industry, "Industry", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Target, "Target", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Nationality, "Nationality", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Customer_Status, "Customer Status", data);
                        pause(2000);
                        updateFieldIfNeeded(Xpaths.Residence, "Residence", data);
                        pause(2000);
                    WebElement commitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Xpaths.T24_Commit_Customer)));
                    commitButton.click();
                    pause(2000);


                }
            }
        }

    }
    private void updateFieldIfNeeded(String xpath, String excelColumnName, Map<String, String> data) {
        String excelValue = data.get(excelColumnName);

        // Check if the Excel value is not null or empty
        if (excelValue != null && !excelValue.isEmpty()) {
            // Get the current value from the browser field
            WebElement field = driver.findElement(By.xpath(xpath));
            String browserValue = field.getAttribute("value");

            // Remove decimal portion if any (not just ".0")
            // Check if the excelValue contains a decimal or ends with ".0"
            if (excelValue.contains(".")) {
                if (excelValue.endsWith(".0")) {
                    // Remove ".0" from the end
                    excelValue = excelValue.substring(0, excelValue.length() - 2);
                } else {
                    // Remove the decimal point entirely
                    excelValue = excelValue.replace(".", "");
                }
            }

            // Compare and update the browser value if necessary
            if (browserValue == null || !browserValue.equals(excelValue)) {
                field.clear();  // Clear the existing value
                field.sendKeys(excelValue);  // Set the new value from Excel
                System.out.println("Field updated: " + excelColumnName + " with value: " + excelValue);
            } else {
                System.out.println("Field already matches for: " + excelColumnName);
            }
        } else {
            System.out.println("No value in Excel for: " + excelColumnName);
        }
    }
}