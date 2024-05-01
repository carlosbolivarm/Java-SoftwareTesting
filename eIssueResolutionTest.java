package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class eIssueResolutionTest {
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
    public void testSelectExistingRepo() {
        delay(5000); // Delay following action by 5 seconds

        WebElement selectRepo = driver.findElement(By.linkText("softwaretestingfgcu/SoftwareTesting-repoPublic1")); // Select existing repository
        selectRepo.click(); // Click existing repository
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 2, alwaysRun = true)
    public void testIssuesTab() {
        WebElement issuesButton = driver.findElement(By.id("issues-tab")); // Select issues button
        issuesButton.click(); // Click issues button
        delay(3000); // Delay 3 seconds button
    }

    @Test(priority = 3, alwaysRun = true)
    public void testSelectExistingIssue() {
        WebElement selectIssue = driver.findElement(By.linkText("issue-test1")); // Select existing issue
        selectIssue.click(); // Click existing issue
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 4, alwaysRun = true)
    public void testAddCommentToResolvedIssue() {
        WebElement commentIssue = driver.findElement(By.id("new_comment_field")); // Select comment on issue field
        commentIssue.sendKeys("Issue 1 was resolved"); // Comment on issue
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 5, alwaysRun = true)
    public void testCommitResolvedIssue() {
        WebElement commitButton = driver.findElement(By.name("comment_and_close")); // Select commit button
        commitButton.click(); // Click commit button
        delay(4000); // Delay following action by 4 seconds
    }

    @Test(priority = 6, alwaysRun = true)
    public void testIssueIsResolved() {
        WebElement issuesButton = driver.findElement(By.id("issues-tab")); // Select issues button
        issuesButton.click(); // Click issues button
        delay(3000); // Delay following action by 3 seconds

        // Assertion to confirm that issue has been resolved
        WebElement selectIssue = null;
        try {
            selectIssue = driver.findElement(By.linkText("issue-test1"));
            selectIssue.click();
        } catch (Exception e) {
            // If the element is not clickable, catch the exception and the test passes
            System.out.println("Issue is resolved.");
            Assert.assertTrue(true, "The Issue is resolved");
            return;
        }

        // If the element is clickable, fail the test
        Assert.fail("The issue is not resolved.");

        delay(4000); // Delay following action by 4 seconds
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

