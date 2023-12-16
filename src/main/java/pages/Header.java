package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {

    public static final String LOGOUT_URL = "http://training.skillo-bg.com:4200/users/login";

    private final WebDriver driver;

    @FindBy(id = "nav-link-profile")
    private static WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    private WebElement newPostButton;

    @FindBy(xpath = "//*[@class='fas fa-sign-out-alt fa-lg']")
    private static WebElement logoutButton;


    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void isLogoutButtonLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
    }

    public void clickNewPost() { newPostButton.click();}

    public static void clickLogout() {
        logoutButton.click();
    }

    public static void clickOnProfile() {
        profileLink.click();
    }

    public boolean isUserLoggedOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlToBe(LOGOUT_URL));
    }


}
