package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class AmazonSignInPage extends ParentPage {
	WebDriver driver = null;
	Element emailIdTextbox = new Element(LocatorType.ID, "ap_email", "Email Id Textbox");
	Element continueButton = new Element(LocatorType.XPATH, "//input[@id='continue']", "Continue Button");
	Element passwordTextbox = new Element(LocatorType.ID, "ap_password", "Password Textbox");
	Element signInButton = new Element(LocatorType.ID, "signInSubmit", "SignIn Button");
	
	public AmazonSignInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void enterEmailId(String emailId) {
		enterText(emailIdTextbox, emailId);
	}
	
	public void enterPassword(String password) {
		enterText(passwordTextbox, password);
	}
	
	public void clickContinueButton() {
		click(continueButton);
	}
	
	public UserHomePage clickSignInButton() {
		click(signInButton);
		return new UserHomePage(driver);
	}
	
	public boolean isUserLoggedOut() {
		return waitForTitleContains("Amazon Sign In");
	}
}