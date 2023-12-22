package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/login";

    public WebDriver driver;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInput;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernamePopulated;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@formcontrolname='rememberMe']")
    private WebElement rememberMeCheckbox;

    @FindBy(id = "sign-in-button")
    private WebElement singInButton;

    @FindBy(id = "toast-container")
    private WebElement toast;

    @FindBy(xpath = "//a[contains(text(), 'Register')]")
    private WebElement registerButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }



    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        rememberMeCheckbox.click();
        singInButton.click();
    }

    public void waitUsernameField(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementValue(usernamePopulated, "Teddy123"));
    }

    public void enterWrongCredentials() {
        usernameInput.sendKeys("Teddy12345");
        passwordInput.sendKeys("test123");
        singInButton.click();
    }

    public boolean isUserLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(LoginPage.PAGE_URL));
    }

    public void clickRegister() {
        registerButton.click();
    }

    public String getToastMessage() {
        return toast.getText();
    }

    public void clickSignInButton() {
        singInButton.click();
    }


}
