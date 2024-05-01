package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class gREADMEDeletionTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set path to chrome driver
        System.setProperty("webdriver.chrome.driver", "/Users/carlosbolivar/Downloads/chromedriver-mac-arm64 2/chromedriver");
        // Open chrome browser
        driver = new ChromeDriver();
        // Maximize browser window
        driver.manage().window().maximize();

        // Login credentials
        login("softwaretestingfgcu", "softwaretestingspring2024");
    }

    // Private login class
    private void login(String username, String password) {
        driver.get("https://github.com/login"); // Open GitHub website
        driver.findElement(By.id("login_field")).sendKeys(username); // Enter username into login field
        driver.findElement(By.id("password")).sendKeys(password); // Enter password into password field
        driver.findElement(By.name("commit")).click(); // Click Sign In button
    }

    @Test(priority = 1, alwaysRun = true)
    public void testAccountProfile() {
        delay(5000); // Delay following action by 5 seconds

        WebElement accountButton = driver.findElement(By.xpath("//span[@class='Button-label']//img")); // Select account button
        accountButton.click(); // Click account button
        delay(3000); // Delay following action by 3 seconds

        WebElement profileButton = driver.findElement(By.linkText("Your profile")); // Select profile button
        profileButton.click(); // Click profile button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 2, alwaysRun = true)
    public void testSelectProfileRepo() {
        WebElement chooseRepo = driver.findElement(By.cssSelector("span.repo")); // Select profile repository
        chooseRepo.click(); // Click profile repository
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 3, alwaysRun = true)
    public void testSelectREADME() {
        WebElement readmeButton = driver.findElement(By.linkText("README.md")); // Select README
        readmeButton.click(); // Click on README
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 4, alwaysRun = true)
    public void testREADMEOptions() {
        WebElement fileOptionsButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'More file actions')]")); // Select file options button
        fileOptionsButton.click(); // Click file options button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 5, alwaysRun = true)
    public void testREADMEDeletion() {
        WebElement deleteFileButton = driver.findElement(By.xpath("//a[contains(@aria-label, 'Delete this file')]")); // Select delete file button
        deleteFileButton.click(); // Click delete file button
        delay(3000); // Delay following action by 3 seconds

        WebElement deleteButton = driver.findElement(By.cssSelector("button.types__StyledButton-sc-ws60qy-0.gFhByl")); // Select delete button
        deleteButton.click(); // Click delete button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 6, alwaysRun = true)
    public void testAddCommentToDeletion() {
        WebElement addComment = driver.findElement(By.cssSelector("textarea.Textarea__StyledTextarea-sc-1lf8it-0.gBIQdK")); // Select add comment field
        addComment.sendKeys("Delete README test 1"); // Enter comment
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 7, alwaysRun = true)
    public void testConfirmDeletion() {
        WebElement confirmationButton = driver.findElement(By.xpath("//button[@type='button' and @data-hotkey='Mod+Enter']")); // Select confirmation button
        confirmationButton.click(); // Click confirmation button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 8, alwaysRun = true)
    public void testREADMEIsDeleted() {
        // Assertion to confirm that README has been deleted
        WebElement selectREADME = null;
        try {
            selectREADME = driver.findElement(By.linkText("README.md"));
            selectREADME.click();
        } catch (Exception e) {
            // If the element is not clickable, catch the exception and the test passes
            System.out.println("README was deleted.");
            Assert.assertTrue(true, "The README was deleted");
            return;
        }

        // If the element is clickable, fail the test
        Assert.fail("README is not deleted.");

        delay(2000); // Delay following action by 2 seconds
    }

    // Delay class
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    // Close browser class
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
