package com.gmail.pages;

import org.openqa.selenium.WebDriver;
import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class GmailSignInPage extends ParentPage {
	WebDriver driver = null;
	Element emailIdTextbox = new Element(LocatorType.ID, "identifierId", "Email Id Textbox");
	Element nextButton = new Element(LocatorType.XPATH, "//div[@id='identifierNext']//span[text()='Next']", "Next Button");
	Element passwordTextbox = new Element(LocatorType.NAME, "password", "Password Textbox");
	Element signInButton = new Element(LocatorType.XPATH, "//div[@id='passwordNext']//span[text()='Next']", "SignIn Button");
	
	public GmailSignInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void enterEmailId(String emailId) {
		enterText(emailIdTextbox, emailId);
	}
	
	public void enterPassword(String password) {
		enterText(passwordTextbox, password);
	}
	
	public void clickNextButton() {
		click(nextButton);
	}
	
	public HomePage clickSignInButton() {
		click(signInButton);
		return new HomePage(driver);
	}

	public boolean isUserLoggedOut() {
		return waitForTitleEquals("Gmail");
	}
}