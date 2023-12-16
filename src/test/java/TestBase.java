import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.File;
import java.time.Duration;

public class TestBase {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://training.skillo-bg.com:4200/users/login");

        //Page Factory elements initialization
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

    }

//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }


    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        File uplPic = new File("src\\main\\resources\\upload\\24fa34c3b1bbc7fd47d4ac87d32aa08d.jpg");
        String postCaption = "Image description";
        return new Object[][]{{"Teddy123", "test123", uplPic, postCaption},};
    }

}
