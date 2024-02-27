Java Maven Test Automation Project

This project is focused on automating tests for the web application "http://training.skillo-bg.com:4200/posts/all" using TestNG and Selenium WebDriver.
The goal is to ensure the reliability and functionality of the web application by automating key test scenarios.
The project follows the Page Object Model design pattern and Page Factory.

Tests that the project contains are:
1. Test for Login that verifies the user can log in to the website with valid credentials
2. Test for creating new posts, after successful login the user can create a new post
3. Test for logout functionality 
4. Test for opening a post and click on the like button without login, verifying the toast "You must login"
5. Test for login with wrong credentials, verify the  toast "User not found"
6. Test view user profile without login, verify the toast "You must be logged in order to see this page!"
7. Test register new user, after entering a valid username, email, password, and confirm password, verify the toast "Successful register!"
8. Test click on the "Sing In" button without entering username and password, verifies the toast "Successful login!" - this test will fail
   and a screenshot will be created, the screenshot can be found in the project path: src/main/resources/screenshots

The project contains a testng.xml file where test suites, test classes, and test methods are configured.
All the tests can be executed with the command "mvn clean test" written in the Terminal. 
The project is uploaded in a git repo with the URL: "https://github.com/Teddyyank/iSkilloFinalAutomationProject" 
