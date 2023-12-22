package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class PostPage {

    private final WebDriver driver;

    public static String PostPage_URL = "http://training.skillo-bg.com:4200/posts/create";

    @FindBy(css = ".file[type=\"file\"]")
    private WebElement uploadImageField;

    @FindBy(css = "input.input-lg")
    private WebElement uploadedPath;

    @FindBy(name = "caption")
    private WebElement captionField;

    @FindBy(xpath = "//*[@class='post-status-label custom-control-label active']")
    private WebElement switchButton;

    @FindBy(xpath = "//*[@id='create-post']")
    private WebElement submitButton;

    @FindBy(id = "toast-container")
    private WebElement toast;

    public PostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlContains(PostPage.PostPage_URL));
    }

    public void uploadImage(File pathPic) {
        uploadImageField.sendKeys(pathPic.getAbsolutePath());
    }

    public String getImageName() {
        return uploadedPath.getAttribute("placeholder");
    }

    public void addCaption() {
        captionField.sendKeys("content added!");
    }

    public void clickOnSwitchButton() {
        switchButton.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public String getToastMessage() {
        String toastMessage = toast.getText();
        return toastMessage;
    }


}
