package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

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
        driver.get("http://training.skillo-bg.com:4200/users/login");

        //Page Factory elements initialization
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    // public void logInUser

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        takeScreenshot(testResult);
        if (driver != null) {
            driver.quit();
        }
    }


    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        File uplPic = new File("src\\main\\resources\\upload\\24fa34c3b1bbc7fd47d4ac87d32aa08d.jpg");
        String postCaption = "Image description";
        return new Object[][]{{"Teddy123", "test123", uplPic, postCaption},};
    }

    private void takeScreenshot(ITestResult takeResult) {

        if (ITestResult.FAILURE == takeResult.getStatus()) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
             //   String testName = takeResult.getTestName();
                FileUtils.copyFile(screenshot, imgFile);
            } catch (IOException e) {
                System.out.println("Unable to create screenshot file: " + e.getMessage());
            }

        }
    }


}
