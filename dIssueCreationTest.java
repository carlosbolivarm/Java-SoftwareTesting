package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class dIssueCreationTest {
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
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 3, alwaysRun = true)
    public void testCreateNewIssue() {
        WebElement newIssueButton = driver.findElement(By.linkText("New issue")); // Select new issue button
        newIssueButton.click(); // Click new issue button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 4, alwaysRun = true)
    public void testIssueInfo() {
        WebElement issueTitle = driver.findElement(By.id("issue_title")); // Select issue title field
        issueTitle.sendKeys("issue-test1"); // Enter issue title
        delay(2000); // Delay following action by 2 seconds

        WebElement issueDescription = driver.findElement(By.id("issue_body")); // Select issue description field
        issueDescription.sendKeys("Test number 1"); // Enter issue description
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 5, alwaysRun = true)
    public void testConfirmIssueCreation() {
        WebElement commitNewIssue = driver.findElement(By.xpath("//button[contains(text(),'Submit new issue')]")); // Select commit new issue button
        commitNewIssue.click(); // Click new issue button
        delay(4000); // Delay following action by 4 seconds
    }

    @Test(priority = 6, alwaysRun = true)
    public void testIssueIsCreated() {
        WebElement issuesButton = driver.findElement(By.id("issues-tab")); // Select issues button
        issuesButton.click(); // Click issues button
        delay(3000); // Delay following action by 3 seconds

        // Assertion to confirm that issue has been created
        WebElement IssueCreation = driver.findElement(By.linkText("issue-test1"));
        if (IssueCreation.isDisplayed()) {
            System.out.println("Issue was created");
        } else {
            Assert.fail("Issue was not created");
        }

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

