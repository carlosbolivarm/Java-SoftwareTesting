package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class fAddREADMETest {
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
    public void testAddREADMEToRepo() {
        WebElement addReadmeButton = driver.findElement(By.linkText("Add a README")); // Select add README button
        addReadmeButton.click(); // Click add README button
        delay(3000); // Delay following action by 3 seconds

        WebElement commitChangesButton = driver.findElement(By.cssSelector("button.types__StyledButton-sc-ws60qy-0.bBAzZg")); // Select commit changes button
        commitChangesButton.click(); // Click commit changes button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 4, alwaysRun = true)
    public void testREADMEDescription() {
        WebElement readmeDescription = driver.findElement(By.cssSelector("textarea[placeholder='Add an optional extended description..']")); // Select README description field
        readmeDescription.sendKeys("README test 1"); // Enter README description
        delay(2000); // Delay following action by 2 seconds
    }

    @Test(priority = 5, alwaysRun = true)
    public void testConfirmREADMECreation() {
        WebElement confirmButton = driver.findElement(By.cssSelector("button.types__StyledButton-sc-ws60qy-0.gFhByl")); // Select confirm README creation button
        confirmButton.click(); // Click confirm README creation button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 6, alwaysRun = true)
    public void testREADMEIsCreated() {
        // Assertion to confirm README has been created
        WebElement IssueCreation = driver.findElement(By.linkText("README.md"));
        if (IssueCreation.isDisplayed()) {
            System.out.println("README was created");
        } else {
            Assert.fail("README was not created");
        }
        delay(4000);
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

