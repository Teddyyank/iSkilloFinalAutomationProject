package pages;

import org.apache.commons.lang3.RandomStringUtils;
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

    public void registerNewUser() {
        userNameField.sendKeys(generateRandomAlphabeticString(7, 7));
        emailField.sendKeys(generateRandomEmail(7, 8));
        passwordField.sendKeys("test123");
        confirmPassField.sendKeys("test123");
        singInButton.click();
    }

    public String generateRandomEmail(int minLengthInclusive, int maxLengthInclusive) {
        return generateRandomAlphabeticString(minLengthInclusive, maxLengthInclusive) + "@gmail.com";
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
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
