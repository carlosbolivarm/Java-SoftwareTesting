package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class bRepositoryCreationTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Path to Chrome Driver
        System.setProperty("webdriver.chrome.driver", "/Users/carlosbolivar/Downloads/chromedriver-mac-arm64 2/chromedriver");
        // Open Chrome Browser
        driver = new ChromeDriver();
        // Maximize browser window
        driver.manage().window().maximize();

        // Login Credentials
        login("softwaretestingfgcu", "softwaretestingspring2024");
    }

    // Private Login class
    private void login(String username, String password) {
        driver.get("https://github.com/login"); // Open GitHub website
        driver.findElement(By.id("login_field")).sendKeys(username); // Enter username into login field
        driver.findElement(By.id("password")).sendKeys(password); // Enter password into password field
        driver.findElement(By.name("commit")).click(); // Click Sign In button
    }

    @Test(priority = 1, alwaysRun = true)
    public void testRepositoryCreationPublic() {
        delay(5000); // Delay following action by 5 seconds

        WebElement newRepoButton = driver.findElement(By.linkText("New")); // Select new repository button
        newRepoButton.click(); // Click new repository button
        delay(3000); // Delay following action by 3 seconds

        WebElement nameOfRepo = driver.findElement(By.id(":r4:")); // Select name of repository field
        nameOfRepo.sendKeys("SoftwareTesting-repoPublic1"); // Enter name of repository
        delay(2000); // Delay following action by 2 seconds
    }

    @Test(priority = 2, alwaysRun = true)
    public void testAddDescriptionToRepo() {
        WebElement descriptionField = driver.findElement(By.id(":r5:")); // Select description of repository field
        descriptionField.sendKeys("Description test 1"); // Enter description of repository
        delay(3000); // Delay following action by 3 seconds

        WebElement createRepoButton = driver.findElement(By.xpath("//button[contains(., 'Create repository')]")); // Select create repository button
        createRepoButton.click(); // Click create repository button
        delay(5000); // Delay following action by 5 seconds
    }

    @Test(priority = 3, alwaysRun = true)
    public void testGoingBackHome() {
        WebElement optionsButton = driver.findElement(By.cssSelector("button[aria-label='Open global navigation menu']")); // Select options button
        optionsButton.click(); // Click options button
        delay(2000); // Delay following action by 2 seconds

        WebElement homeButton = driver.findElement(By.linkText("Home")); // Select home button
        homeButton.click(); // Click home button
        delay(3000); // Delay following action by 2 seconds
    }

    @Test(priority = 4, alwaysRun = true)
    public void testRepositoryCreationWithoutName(){
        WebElement newRepoButton = driver.findElement(By.linkText("New")); // Select new repository button
        newRepoButton.click(); // Click new repository button
        delay(3000); // Delay following action by 3 seconds

        WebElement createRepoButton = driver.findElement(By.xpath("//button[contains(., 'Create repository')]")); // Select create repository button
        createRepoButton.click(); // Click create repository button
        delay(3000); // Delay following action by 3 seconds

        // Assertion to confirm that error message is displayed
        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(), 'New repository name must not be blank')]"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message for repository name validation is not displayed.");

        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 5, alwaysRun = true)
    public void testRepositoryCreationPrivate() {
        WebElement nameOfRepo = driver.findElement(By.id(":r4:")); // Select name of repository field
        nameOfRepo.sendKeys("SoftwareTesting-repoPrivate1"); // Enter name of repository
        delay(2000); // Delay following action by 2 seconds

        WebElement privateRepo = driver.findElement(By.id(":r8:")); // Select make repository private button
        privateRepo.click(); // Click make repository private button
        delay(2000); // Delay following action by 2 seconds
    }

    @Test(priority = 6, alwaysRun = true)
    public void testAddREADMEToRepo() {
        WebElement addReadmeButton = driver.findElement(By.id(":ra:")); // Select add README button
        addReadmeButton.click(); // Click add README button
        delay(3000); // Delay following action by 3 seconds

        WebElement createRepoButton = driver.findElement(By.xpath("//button[contains(., 'Create repository')]")); // Select create repository button
        createRepoButton.click(); // Click create repository button
        delay(5000); // Delay following action by 5 seconds
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

