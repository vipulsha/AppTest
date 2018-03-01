package com.amazon.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.amazon.pages.AmazonSignInPage;
import com.amazon.pages.CartDetailsPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.PaymentMethodPage;
import com.amazon.pages.ProductDetailsPage;
import com.amazon.pages.SearchResultPage;
import com.amazon.pages.ShippingAddressPage;
import com.amazon.pages.ShippingOptionsPage;
import com.amazon.pages.UserHomePage;
import com.common.utils.Assertion;
import com.common.utils.ParentTest;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class TC_002_AmazonLoginLogoutTest extends ParentTest {

	@Test
	public void loginLogoutTest() {
		try {
			HomePage homePage = new HomePage(driver);
			AmazonSignInPage signInPage = homePage.clickOnSignInOption();
			signInPage.enterEmailId(getData("emailId"));
			signInPage.clickContinueButton();
			signInPage.enterPassword(getData("password"));
			UserHomePage userHomePage = signInPage.clickSignInButton();

			// Verify user is logged in
			String actUsername = userHomePage.getLoggedInUsername();
			String expUsername = getData("userName");
			Assertion.assertTrue(actUsername.contains(expUsername), "Verify User got login");

			// Logout
			signInPage = userHomePage.logOut();
			Assertion.assertTrue(signInPage.isUserLoggedOut(), "Verify User got logged out");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ATUReports.add("Exception: "+e.getMessage(),LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
		}
	}
}
