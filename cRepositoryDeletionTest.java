package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class cRepositoryDeletionTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set path to Chrome Driver
        System.setProperty("webdriver.chrome.driver", "/Users/carlosbolivar/Downloads/chromedriver-mac-arm64 2/chromedriver");
        // Open Chrome browser
        driver = new ChromeDriver();
        // Maximize browser window
        driver.manage().window().maximize();

        // Login credentials
        login("softwaretestingfgcu", "softwaretestingspring2024");
    }

    // Private Login class
    private void login(String username, String password) {
        driver.get("https://github.com/login"); // Open GitHub website
        driver.findElement(By.id("login_field")).sendKeys(username); // Enter username into login field
        driver.findElement(By.id("password")).sendKeys(password); // Enter password into password field
        driver.findElement(By.name("commit")).click(); // Click Sign In button
    }

    @Test(priority = 1)
    public void testSelectExistingPrivateRepo() {
        delay(5000); // Delay following action by 5 seconds

        WebElement selectRepo = driver.findElement(By.linkText("softwaretestingfgcu/SoftwareTesting-repoPrivate1")); // Select existing repository
        selectRepo.click(); // Click existing repository
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 2, alwaysRun = true)
    public void testSettingsOfRepo() {
        WebElement settingsButton = driver.findElement(By.id("settings-tab")); // Select settings button
        settingsButton.click(); // Click settings button
        delay(4000); // Delay following action by 4 seconds
    }

    @Test(priority = 3, alwaysRun = true)
    public void testDeletePrivateRepo() {
        WebElement deleteRepoButton = driver.findElement(By.xpath("//span[text()='Delete this repository']")); // Select delete repository button
        deleteRepoButton.click(); // Click delete repository button
        delay(3000); // Delay following action by 3 seconds

        WebElement confirmDeleteRepo = driver.findElement(By.xpath("//span[text()='I want to delete this repository']")); // Select confirm deletion button
        confirmDeleteRepo.click(); // Click confirm deletion button
        delay(3000); // Delay following action by 3 seconds

        WebElement agreementButton = driver.findElement(By.xpath("//span[text()='I have read and understand these effects']")); // Select agreement button
        agreementButton.click(); // Click agreement button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 4, alwaysRun = true)
    public void testEnterWrongRepoName() {
        WebElement enterVerification = driver.findElement(By.id("verification_field")); // Select verification field
        enterVerification.sendKeys("softwaretestingfgcu/SoftwareTesting-repos"); // Enter wrong name of repository in verification field
        delay(2000); // Delay following action by 2 seconds

        WebElement commitDeletion = driver.findElement(By.id("repo-delete-proceed-button")); // Select commit deletion button
        commitDeletion.click(); // Click commit deletion button
        delay(4000); // Delay following action by 4 seconds
    }

    @Test(priority = 5, alwaysRun = true)
    public void testEnterValidRepoName() {
        WebElement enterVerification = driver.findElement(By.id("verification_field")); // Select verification field
        enterVerification.clear(); // Clear verification field
        delay(1000); // Delay following action by 1 second
        enterVerification.sendKeys("softwaretestingfgcu/SoftwareTesting-repoPrivate1"); // Enter correct repository name in verification field
        delay(2000); // Delay following action by 2 seconds

        WebElement commitDeletion = driver.findElement(By.id("repo-delete-proceed-button")); // Select commit deletion button
        commitDeletion.click(); // Click commit deletion button
        delay(4000); // Delay following action by 4 seconds
    }

    @Test(priority = 6, alwaysRun = true)
    public void testGoingBackHome() {
        WebElement optionsButton = driver.findElement(By.cssSelector("button[aria-label='Open global navigation menu']")); // Select option button
        optionsButton.click(); // Click options button
        delay(2000); // Delay following action by 2 seconds

        WebElement homeButton = driver.findElement(By.linkText("Home")); // Select home button
        homeButton.click(); // Click home button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 7, alwaysRun = true)
    public void testRepoIsDeleted() {
        // Assertion to make sure that repository has been deleted
        WebElement selectRepo = null;
        try {
            selectRepo = driver.findElement(By.linkText("softwaretestingfgcu/SoftwareTesting-repoPrivate1"));
            selectRepo.click();
        } catch (Exception e) {
            // If the element is not clickable, catch the exception and the test passes
            System.out.println("Repository does not exist.");
            Assert.assertTrue(true, "The Repository does not exist");
            return;
        }

        // If the element is clickable, fail the test
        Assert.fail("The Repository does exist.");

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

