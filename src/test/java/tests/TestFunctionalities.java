package tests;

import org.testng.annotations.Test;
import pages.*;
import java.io.File;


import static org.testng.Assert.*;

public class TestFunctionalities extends TestBase {

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void testLogin(String username, String password, File ignoreUplPic,String ignorePostCaption) {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        assertTrue(loginPage.isUrlLoaded());
        loginPage.login(username, password);
        loginPage.waitUsernameField();
        assertEquals("Successful login!", loginPage.getToastMessage());

    }

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void createNewPost(String username, String password, File uplPic, String postCaption) {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        assertTrue(loginPage.isUrlLoaded());
        loginPage.login(username, password);
        assertEquals("Successful login!", loginPage.getToastMessage());

        Header header = new Header(super.getDriver());
        header.clickNewPost();

        PostPage postPage = new PostPage(super.getDriver());
        assertTrue(postPage.isUrlLoaded(), "Url is not loaded!");
        postPage.uploadImage(uplPic);
        assertEquals(postPage.getImageName(), uplPic.getName());
        postPage.addCaption(postCaption);
        postPage.clickOnSwitchButton();
        postPage.clickSubmitButton();
        ProfilePage profilePage = new ProfilePage(super.getDriver());
        assertTrue(profilePage.isUrlLoaded(), "url is not navigated to profile page");
    }


    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void testLogOut(String username, String password, File ignoreUplPic, String ignorePostCaption) {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(super.getDriver());
        assertTrue(loginPage.isUrlLoaded());
        loginPage.login(username, password);
        homePage.clickLogoutButton();
        homePage.getToastMessage();
        assertEquals("Successful logout!\n" +
                "Successful login!", loginPage.getToastMessage());

    }

    @Test
    public void testLikeAPostWithoutLogin() {

        HomePage homePage = new HomePage(super.getDriver());
        assertTrue(homePage.isUrlLoaded());
        homePage.clickOnTheFirstPost();
        assertTrue(homePage.isVisibleCommentField(), "Comment field is not visible!");
        homePage.clickOnLikeButton();
        LoginPage loginPage = new LoginPage(super.getDriver());
        assertEquals("You must login\n" +
                "You must login", loginPage.getToastMessage());
    }


    @Test (dataProvider = "wrongCredentials", dataProviderClass = TestBase.class)
    public void testLoginWithWrongUsername(String username, String password) {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(super.getDriver());
        assertTrue(loginPage.isUrlLoaded());
        loginPage.enterWrongCredentials(username, password);
        assertEquals("User not found", loginPage.getToastMessage());

    }


    @Test
    public void testViewProfileWithoutLogin() {

        HomePage homePage = new HomePage(super.getDriver());
        assertTrue(homePage.isUrlLoaded());
        homePage.clickOnUserName();
        assertEquals("You must be logged in in order to see this page!", homePage.getToastMessage());

    }


    @Test (dataProvider = "register", dataProviderClass = TestBase.class)
    public void testRegisterNewUser(String userName, String email, String password, String confirmPassword) {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.clickRegister();

        RegisterPage registerPage = new RegisterPage(super.getDriver());
        registerPage.isURLRegister();
        registerPage.registerNewUser(userName, email, password, confirmPassword);
        assertEquals("Successful register!", registerPage.getToastMessageRegister());

    }

    //test failure - demonstrate taking a screenshot
    @Test
    public void testLoginWithoutEnteringCredentials() {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.clickLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickSignInButton();
        assertEquals("Successful login!", loginPage.getToastMessage());

    }


}
