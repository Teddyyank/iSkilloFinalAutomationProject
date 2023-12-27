package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HomePage {
    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='nav-link-login']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@class='fas fa-sign-out-alt fa-lg']")
    private WebElement logoutButton;

    @FindBy(className = "post-feed-img")
    private WebElement selectFirstPost;

    @FindBy(xpath = "//*[@class='like far fa-heart fa-2x']")
    private WebElement selectLikeButton;

    @FindBy(xpath = "//*[@placeholder='Comment here']")
    private WebElement commentField;

    @FindBy(id = "toast-container")
    private WebElement toast;

    @FindBy(xpath = "(//a[@class='post-user'])[1]")
    private WebElement selectFirstUsername;

//    @FindBy(css = "a.post-user[_ngcontent-qlu-c7]")
//    private List<WebElement> listUsers;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(HomePage.HOME_URL));
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void clickOnTheFirstPost() {
        selectFirstPost.click();
    }

    public void clickOnLikeButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(selectFirstPost));
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

    public String getToastMessage() {
        String toastMessage = toast.getText();
        return toastMessage;
    }

    public void clickOnUserName() {
        selectFirstUsername.click();
    }


//    public void clickOnFirstPostUserLink() {
//        if (!listUsers.isEmpty()) {
//            WebElement firstUser = listUsers.get(0);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.elementToBeClickable(firstUser));
//            firstUser.click();
//            wait.until(ExpectedConditions.elementToBeSelected(firstUser));
//        }
//    }


}

