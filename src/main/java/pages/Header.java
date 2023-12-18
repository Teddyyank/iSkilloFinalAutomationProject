package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Header {
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

    public void clickNewPost() { newPostButton.click();}

    public static void clickLogout() {
        logoutButton.click();
    }

}
