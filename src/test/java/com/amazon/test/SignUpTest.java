package com.amazon.test;

import com.amazon.pages.HomePage;
import com.amazon.pages.SignUpPage;
import com.amazon.pages.UserHomePage;
import com.common.utils.Assertion;
import com.common.utils.ParentTest;
import org.testng.annotations.Test;

public class SignUpTest extends ParentTest{

    @Test
    public void testSignUp(){

        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = homePage.gotoSignUpPage();
        UserHomePage userHomePage = signUpPage.signUp("DemoUser", "Demo"+getRandomNumberString()+"@gmail.com",
                "Test#1234","Test#1234");
        Assertion.assertTrue(userHomePage.getLoggedInUsername().contains("Hello, DemoUser"), "Error in sign up.");

    }
}
