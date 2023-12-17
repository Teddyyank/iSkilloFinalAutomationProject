package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";
    private WebDriver driver;

    @FindBy(xpath = "(//div[@class='post-feed-img'])[1]")
    private WebElement likeFirstPost;

    @FindBy(xpath = "//*[@class='like far fa-heart fa-2x']")
    private WebElement selectLikeButton;

    @FindBy(xpath = "//*[@placeholder='Comment here']")
    private WebElement commentField;

    @FindBy(id = "toast-container")
    private WebElement toast;

    @FindBy(id = "//*[@id='nav-link-profile']")
    private WebElement profileButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(HomePage.HOME_URL));
    }

    public void navigateTo() {
        this.driver.get(HOME_URL);
    }

    public void clickOnTheFirstPost() {
        likeFirstPost.click();
    }

    public void clickOnLikeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(likeFirstPost));
        selectLikeButton.click();
    }

    public boolean isVisibleCommentField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(commentField));
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }




    //test to view a profile without login

    @FindBy(css = "a.post-user")
    private WebElement userSelector;



//    public boolean isProfileButtonVisible() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        return wait.until((ExpectedConditions.
//    }


}

