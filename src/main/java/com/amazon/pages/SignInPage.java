package com.amazon.pages;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;
import org.openqa.selenium.WebDriver;

public class SignInPage extends ParentPage {
	WebDriver driver = null;

	/*@FindBy(id = "ap_email")
	WebElement emailIdTextbox;

	@FindBy(xpath = "//input[@id='continue']")
			WebElement continueButton;

	@FindBy(id = "ap_password")
			WebElement passwordTextbox;

	@FindBy(id = "signInSubmit")
			WebElement signInButton;*/
	Element emailIdTextbox = new Element(LocatorType.ID, "ap_email", "Email Id Textbox");
	Element continueButton = new Element(LocatorType.XPATH, "//input[@id='continue']", "Continue Button");
	Element passwordTextbox = new Element(LocatorType.ID, "ap_password", "Password Textbox");
	Element signInButton = new Element(LocatorType.ID, "signInSubmit", "SignIn Button");
	
	public SignInPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
//		PageFactory.initElements(driver, this);
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
	
	public UserHomePage signIn(String emailId, String password){
            enterText(emailIdTextbox, emailId);
            click(continueButton);
            enterText(passwordTextbox, password);
            return clickSignInButton();
    }

	public UserHomePage clickSignInButton() {
		click(signInButton);
		return new UserHomePage(driver);
	}
	
	public boolean isUserLoggedOut() {
		return waitForTitleContains("Amazon Sign In");
	}
}