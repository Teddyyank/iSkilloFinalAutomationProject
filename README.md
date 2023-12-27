Java Maven Test Automation Project

This project is focused on automating tests for the web application "http://training.skillo-bg.com:4200/posts/all" using TestNG and Selenium WebDriver.
The goal is to ensure the reliability and functionality of the web application by automating key test scenarios.
The project follows the Page Object Model design pattern and Page Factory.

Tests that project contains are:
1. Test for Login that verifies the user can log in to the website with valid credentials
2. Test for creating new post, after Login the user can create new post
3. Test Logout, after Login the user can Log out successfully
4. Test for opening a post and click on like button without login, verifies the toast "You must login"
5. Test for login with wrong credentials, verifies the  toast "User not found"
6. Test view user profile without login, verifies the toast "You must be logged in order to see this page!"
7. Test register new user, after entering valid username, email, password and confirm password, verifies the toast "Successful register!"
8. Test click on "Sing In" button without entering username and password, verifies the toast "Successful login!" - this test will failure
   and a screenshot will be kept, the screenshot can be found in the project path: src/main/resources/screenshots

The project contains testng.xml file where test suites, test classes, and test methods are configured.
All the test can be executed with command "mvn clean test" written in the Terminal. 
The project is uploaded in git repo, url: "https://github.com/Teddyyank/iSkilloFinalAutomationProject" 
