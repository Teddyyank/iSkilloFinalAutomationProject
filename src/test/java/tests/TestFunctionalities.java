package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import tests.TestBase;

import java.io.File;

import static org.testng.Assert.*;

public class TestFunctionalities extends TestBase {

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void testLogin(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        assertTrue(loginPage.isUserLoggedIn(), "Url is different!");
        loginPage.login(username, password);
        assertEquals("Successful login!", loginPage.getToastMessage());

    }

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void createNewPost(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        assertTrue(loginPage.isUserLoggedIn());
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
        assertTrue(profilePage.isUrlLoaded(), "Url is not loaded");

    }


    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void testLogOut(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        assertTrue(loginPage.isUserLoggedIn());
        loginPage.login(username, password);

        Header header = new Header(getDriver());
        header.clickLogout();
        assertEquals("Successful logout!\n" +
                "Successful login!", loginPage.getToastMessage());

    }

    @Test
    public void testLikeAPostWithoutLogin() {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();
        assertTrue(homePage.isUrlLoaded());
        homePage.clickOnTheFirstPost();
        assertTrue(homePage.isVisibleCommentField(), "Comment field is not visible!");
        homePage.clickOnLikeButton();
        LoginPage loginPage = new LoginPage(super.getDriver());
        assertEquals("You must login\n" +
                "You must login", loginPage.getToastMessage());
    }


    @Test
    public void testLoginWithWrongUsername() {

        LoginPage loginPage = new LoginPage(super.getDriver());
        assertTrue(loginPage.isUserLoggedIn());
        loginPage.enterWrongCredentials();
        assertEquals("User not found", loginPage.getToastMessage());

    }


    @Test
    public void testViewProfileWithoutLogin() {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();
        assertTrue(homePage.isUrlLoaded());
        homePage.clickOnFirstUserName();
        assertEquals("You must be logged in in order to see this page!", homePage.getToastMessage());

    }


    @Test
    public void testRegisterNewUser() {

        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.clickRegister();

        RegisterPage registerPage = new RegisterPage(super.getDriver());
        registerPage.isURLRegister();
        registerPage.registerNewUser();
        assertEquals("Successful register!", registerPage.getToastMessageRegister());

    }

    //test failure - screenshot
    @Test
    public void testLoginWithoutEnteringCredentials() {

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickSignInButton();
        assertEquals("Successful login!!", loginPage.getToastMessage());



    }


}
