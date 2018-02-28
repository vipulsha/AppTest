package com.amazon.test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import com.amazon.pages.HomePage;
import com.amazon.pages.SignUpPage;
import com.amazon.pages.UserHomePage;
import com.common.utils.Assertion;
import com.common.utils.ParentTest;
import org.testng.annotations.Test;

public class SignUpTest extends ParentTest{

    @Test
    public void testSignUp(){
        try{
            HomePage homePage = new HomePage(driver);
            SignUpPage signUpPage = homePage.gotoSignUpPage();
            UserHomePage userHomePage = signUpPage.signUp(getData("name"), "Demo"+getRandomNumberString()
                            +"@gmail.com", getData("pass"),getData("repass"));
            Assertion.assertTrue(userHomePage.getLoggedInUsername().contains("Hello, DemoUser"), "Error in sign up.");

        } catch (Exception e) {
        System.out.println(e.getMessage());
        ATUReports.add("Exception: "+e.getMessage(), LogAs.FAILED,new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    }

    }
}
