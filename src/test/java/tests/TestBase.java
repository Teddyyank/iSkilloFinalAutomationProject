package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static pages.HomePage.HOME_URL;

public class TestBase {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    private static final String SCREENSHOT_DIR = "src\\main\\resources\\screenshots\\";

    String fileName = "simpleFile.jpeg";

    File imgFile = new File(SCREENSHOT_DIR.concat(fileName));


    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUpMethod() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(HOME_URL);

    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        takeScreenshot(testResult);
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        String username = "Teddy123";
        File uplPic = new File("src\\main\\resources\\upload\\24fa34c3b1bbc7fd47d4ac87d32aa08d.jpg");
        String postCaption = "Image description";
        return new Object[][]{{username, "test123", uplPic, postCaption},};
    }

    @DataProvider(name = "register")
    public Object[][] registerData() {
        String userName = generateRandomAlphabeticString(7, 8);
        String email = generateRandomEmail(7, 7);
        String password = "test123";
        String confirmPassword = "test123";
        return new Object[][]{{userName, email, password, confirmPassword},};
    }


    @DataProvider(name = "wrongCredentials")
    public Object[][] wrongCredentials() {
        return new Object[][]{{"Teddy12345", "test123"},};
    }

    public String generateRandomEmail(int minLengthInclusive, int maxLengthInclusive) {
        return generateRandomAlphabeticString(minLengthInclusive, maxLengthInclusive) + "@gmail.com";
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
    }


    private void takeScreenshot(ITestResult takeResult) {

        if (ITestResult.FAILURE == takeResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, imgFile);
            } catch (IOException e) {
                System.out.println("Unable to create screenshot file: " + e.getMessage());
            }

        }
    }


}
