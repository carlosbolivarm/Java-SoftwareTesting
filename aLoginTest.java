package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class aLoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Path to chrome driver
        System.setProperty("webdriver.chrome.driver", "/Users/carlosbolivar/Downloads/chromedriver-mac-arm64 2/chromedriver");
        // Open chrome browser
        driver = new ChromeDriver();
        // Open GitHub website
        driver.get("https://github.com/login");
        // Maximize browser window
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testLoginWithValidCredentials() {
        delay(2000); // delay following action by 2 seconds

        WebElement userName = driver.findElement(By.id("login_field")); // Select login field
        userName.sendKeys("softwaretestingfgcu"); // Enter username
        delay(2000); // delay following action by 2 seconds

        WebElement passwd = driver.findElement(By.id("password")); // Select password field
        passwd.sendKeys("softwaretestingspring2024"); // Enter password
        delay(2000); // delay following action by 2 seconds

        WebElement commitLogin = driver.findElement(By.name("commit")); // Select commit button
        commitLogin.click(); // Click commit button
        delay(5000); // delay following action by 2 seconds
    }

    @Test(priority = 2)
    public void testLogout() {
        WebElement accountButton = driver.findElement(By.xpath("//span[@class='Button-label']//img")); // Select account button
        accountButton.click(); // Click account button
        delay(4000); // Delay following action by 4 seconds

        WebElement signOutButton = driver.findElement(By.linkText("Sign out")); // Select sign out button
        signOutButton.click(); // Click sign out button
        delay(2000); // Delay following action by 2 seconds

        WebElement commitButton = driver.findElement(By.name("commit")); // Select commit button
        commitButton.click(); // Click commit button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 3)
    public void testLoginWithNoCredentials() {
        WebElement signInButton = driver.findElement(By.linkText("Sign in")); // Select sign in button
        signInButton.click(); // Click sign in button
        delay(2000); // Delay following action by 2 seconds

        WebElement signInCommitButton = driver.findElement(By.name("commit")); // Select sign in commit button
        signInCommitButton.click(); // Click sign in commit button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 4, alwaysRun = true)
    public void testLoginWithInvalidCredentials() {
        WebElement enterUserName = driver.findElement(By.id("login_field")); // Select login field
        enterUserName.sendKeys("softwaretestingfgcu"); // Enter username at login field

        WebElement enterPassword = driver.findElement(By.id("password")); // Select password field
        enterPassword.sendKeys("softwaretestingspring2023"); // Enter password field at password field
        delay(1000); // Delay following action by 1 second

        WebElement signInCommitButton = driver.findElement(By.cssSelector("input[type='submit'][name='commit'][value='Sign in']")); // Select login commit button
        signInCommitButton.click(); // Click login commit button
        delay(3000); // Delay following action by 3 seconds
    }

    @Test(priority = 5, alwaysRun = true)
    public void testErrorMessage() {
        // Detect Error Message after wrong login credentials
        WebElement errorMessage = driver.findElement(By.cssSelector("button.flash-close.js-flash-close[aria-label='Dismiss this message']"));
        if (errorMessage.isDisplayed()) {
            System.out.println("Error message was displayed");
        } else {
            Assert.fail("Error message for signing in with invalid credentials is not displayed.");
        }
        delay(4000); // Delay following action by 4 seconds
    }

    @Test(priority = 6, alwaysRun = true)
    public void forgotPassword (){
        WebElement forgotPasswdButton = driver.findElement(By.id("forgot-password")); // Select forgot password link
        forgotPasswdButton.click(); // Click forgot password link
        delay(2000); // Delay following action by 2 seconds

        WebElement recoveryEmail = driver.findElement(By.id("email_field")); // Select email field
        recoveryEmail.sendKeys("softwaretestingspring@gmail.com"); // Enter email at email field
        delay(2000); // Delay following action by 2 seconds

        WebElement verifyButton = driver.findElement(By.cssSelector("button.sc-nkuzb1-0.sc-d5trka-0.eZxMRy.button[data-theme='home.verifyButton']")); // Select verification button
        verifyButton.click(); // Click verification button
        delay(2000); // Delay following action by 2 seconds

        WebElement sendEmailButton = driver.findElement(By.cssSelector("input[name='commit'][value='Send password reset email']")); // Select send email button
        sendEmailButton.click(); // Click send email button
        delay(3000); // Delay following action by 2 seconds
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
