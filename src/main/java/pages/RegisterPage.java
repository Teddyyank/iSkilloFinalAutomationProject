package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    public static final String REGISTER_URL = "http://training.skillo-bg.com:4200/users/register";

    public WebDriver driver;

    @FindBy(xpath = "//*[@formcontrolname='username']")
    private WebElement userNameField;

    @FindBy(xpath = "//*[@formcontrolname='email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@formcontrolname='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@formcontrolname='confirmPassword']")
    private WebElement confirmPassField;

    @FindBy(id = "sign-in-button")
    private WebElement singInButton;

    @FindBy(id = "toast-container")
    private WebElement toast;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void registerNewUser(String userName, String email, String password, String confirmPassword) {
        userNameField.sendKeys(userName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPassField.sendKeys(confirmPassword);
        singInButton.click();
    }




    public void isURLRegister() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedConditions.urlToBe(REGISTER_URL)));
    }

    public String getToastMessageRegister() {
        String toastMessage = toast.getText();
        return toastMessage;
    }

}
