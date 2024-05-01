package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class hSomeoneElseRepoTest {
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
   public void testSearchTab() {
        delay(5000); // Delay following action by 5 seconds

       WebElement searchButton = driver.findElement(By.cssSelector("button.AppHeader-button")); // Select search tab
       searchButton.sendKeys("carlosbolivarm/");
       delay(2000); // Delay following action by 2 seconds
   }

   @Test(priority = 2, alwaysRun = true)
   public void testEnterSomeoneElseProfile() {
       WebElement randomProfile = driver.findElement(By.id("query-builder-test")); // Search tab field
       randomProfile.sendKeys("carlosbolivarm/"); // Enter someone else's profile
       delay(2000); // Delay following action by 2 seconds
       randomProfile.sendKeys(Keys.ENTER); // Hit ENTER
       delay(6000); // Delay following action by 6 seconds
   }

   @Test(priority = 3, alwaysRun = true)
   public void testSelectRepo() {
       WebElement repoName = driver.findElement(By.linkText("carlosbolivarm/c-")); // Select a repository
       repoName.click(); // Click on a repository
       delay(5000); // Delay following action by 5 seconds
   }

   @Test(priority = 4, alwaysRun = true)
   public void testSelectFile() {
        WebElement fileButton = driver.findElement(By.linkText("tools.cpp")); // Select a file
        fileButton.click(); // Click on a file
        delay(4000); // Delay following action by 4 seconds
   }

   @Test(priority = 5, alwaysRun = true)
   public void testSelectDifferentFile() {
        WebElement differentFile = driver.findElement(By.id("tools_function.h-item")); // Select a different file
        differentFile.click(); // Click on a different file
        delay(5000); // Delay following action by 5 seconds
   }

   @Test(priority = 6, alwaysRun = true)
   public void testGoToUserProfile() {
        WebElement userProfile = driver.findElement(By.cssSelector("a.AppHeader-context-item")); // Select user's profile
        userProfile.click(); // Click on user's profile
        delay(6000); // Delay following action by 6 seconds
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
