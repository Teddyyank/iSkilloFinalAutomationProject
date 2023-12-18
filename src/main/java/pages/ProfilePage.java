package pages;

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

    @FindBy(xpath = "xpath=//div[@id='toast-container']/div/div")
    private WebElement toast;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlContains(ProfilePage.PROFILE_URL));
    }

    public String getToastMessage() {
        String toastMessage = toast.getText();
        return toastMessage;
    }

}
