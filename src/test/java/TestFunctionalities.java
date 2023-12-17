import com.beust.ah.A;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;

public class TestFunctionalities extends TestBase {

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void testLogin(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        //assert that url navigates login page
        Assert.assertTrue(loginPage.isUserLoggedIn());
        //enter registered username and password
        loginPage.login(username, password);
        //verify that the toast "Successful login!" appears
        Assert.assertEquals("Successful login!", loginPage.getToastMessage());

    }

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void createNewPost(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isUserLoggedIn());
        loginPage.login(username, password);
        Assert.assertEquals("Successful login!", loginPage.getToastMessage());


        Header header = new Header(super.getDriver());
        //click on "New post" button
        header.clickNewPost();

        PostPage postPage = new PostPage(super.getDriver());
        //assert that url navigates to create post page
        Assert.assertTrue(postPage.isUrlLoaded());
        //upload an image
        postPage.uploadImage(uplPic);
        //assert that image name is the same with uploaded name
        Assert.assertEquals(postPage.getImageName(), uplPic.getName());
        //add text on caption field
        postPage.addCaption(postCaption);
        //click on "Submit" button
        postPage.clickSubmitButton();
        //verify that the image is uploaded

    }
//to verify the uploaded image

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void testLogOut(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isUserLoggedIn());
        loginPage.login(username, password);

        Header header = new Header(getDriver());
        //click on "Log out" button
        Header.clickLogout();
        //verify that the toast "Logged out" appears
        Assert.assertEquals("Successful logout!\n" +
                "Successful login!", loginPage.getToastMessage());

    }

    @Test
    public void testLikeAPostWithoutLogin() {

        HomePage homePage = new HomePage(super.getDriver());
        //open Home page
        homePage.navigateTo();
        //verify that the user is on Home page
        Assert.assertTrue(homePage.isUrlLoaded());
        //click on the first image
        homePage.clickOnTheFirstPost();
        //verify that the comment field is visible
        Assert.assertTrue(homePage.isVisibleCommentField());
        //click on like button
        homePage.clickOnLikeButton();
        //verify that the toast "You must login" appears
        LoginPage loginPage = new LoginPage(super.getDriver());
        Assert.assertEquals("You must login\n" +
                "You must login", loginPage.getToastMessage());
    }


    @Test
    public void testLoginWithWrongUsername() {

        //navigate to Login Page
        LoginPage loginPage = new LoginPage(super.getDriver());
        Assert.assertTrue(loginPage.isUserLoggedIn());
        //enter wrong credentials and click SingIn button
        loginPage.enterWrongCredentials();
        //verify that the toast message that appears is correct
        Assert.assertEquals("User not found", loginPage.getToastMessage());

    }


    @Test
    public void testViewProfileWithoutLogin() {

        HomePage homePage = new HomePage(super.getDriver());
        //open Home page
        homePage.navigateTo();
        //assert that Home page url is loaded
        Assert.assertTrue(homePage.isUrlLoaded());
        //click on the first username

        //assert that the toast "You must log in" is visible


    } //not ready


    @Test
    public void testRegisterNewUser() {

        //navigate to login page
        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.clickRegister();

        RegisterPage registerPage = new RegisterPage(super.getDriver());
        //verify that the user is on the register page
        registerPage.isURLRegister();
        //fill all the fields and click "Sing In" button
        registerPage.registerNewUser();

        //verify that the toast "Successful register!" appears
        Assert.assertEquals("Successful register!", registerPage.getToastMessageRegister());

    }



}
