package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    public static final String PROFILE_URL = "http://training.skillo-bg.com:4200/users/5098";
    private final WebDriver driver;

    @FindBy(css = "h2[_ngcontent-deh-c12]:first-child")
    private WebElement username;

    @FindBy(id = "nav-link-profile")
    private WebDriver profile;

    @FindBy(xpath = "//*[@class='post-img']")
    private WebElement postImage;

    @FindBy(xpath = "//*[@class='fas fa-user-edit ng-star-inserted']")
    private WebElement modifyProfileButton;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    public String getUsername() {
//        return username.getText();
//    }

    public void clickOnProfileButton() {
        profile.close();
    }


    public void clickOnThePost() {
        postImage.click();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlContains(ProfilePage.PROFILE_URL));
    }

}
