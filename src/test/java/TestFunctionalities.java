import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Header;
import pages.HomePage;
import pages.LoginPage;
import pages.PostPage;

import java.io.File;

public class TestFunctionalities extends TestBase {

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void testLogin(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isUserLoggedIn(), "Successful login!");
    }

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void createNewPost(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isUserLoggedIn(), "Successful login!");

        Header header = new Header(super.getDriver());
        header.clickNewPost();

        PostPage postPage = new PostPage(super.getDriver());
        Assert.assertTrue(postPage.isUrlLoaded());

        postPage.uploadImage(uplPic);
        Assert.assertTrue(postPage.isUrlLoaded(), "Image is not visible!");

        Assert.assertEquals(postPage.getImageName(), uplPic.getName());

        postPage.addCaption(postCaption);
        postPage.clickSubmitButton();

    }

    @Test(dataProvider = "loginData", dataProviderClass = TestBase.class)
    public void testLogOut(String username, String password, File uplPic, String postCaption) {

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isUserLoggedIn(), "Successful login!");

        Header header = new Header(getDriver());
        Header.clickLogout();
        Assert.assertTrue(header.isUserLoggedOut(), "Successful logout!");


    }

    @Test
    public void testLikeAPostWithoutLogin() {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.isUrlLoaded(), "Home");

        homePage.clickOnTheFirstPost();

        Assert.assertTrue(homePage.isVisibleCommentField(), "Comment here");

        homePage.clickOnLikeButton();

        homePage.isToastVisible();

        Assert.assertTrue(homePage.isToastVisible(), "You must login");
    }



    @Test
    public void testLoginWithWrongUsername() {

        //navigate to Login Page
        LoginPage loginPage = new LoginPage(super.getDriver());

        //enter wrong email/username and click SingIn button
        loginPage.testWrongEmail();

        //assert toast - "User not found"

    } //assert the toast


    @Test
    public void testViewProfileWithoutLogin() {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();

        Assert.assertTrue(homePage.isUrlLoaded(), "Login");

        //click on the first profile name

        //assert that the toast "You must log in" is visible


    } //not ready


    @Test
    public void testRegisterNewUser() {



    }  //started





}
