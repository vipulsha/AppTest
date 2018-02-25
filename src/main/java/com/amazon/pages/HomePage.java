package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class HomePage extends ParentPage {
	WebDriver driver = null;
	Element signInOption = new Element(LocatorType.XPATH, "//span[text()='Account & Lists']", "SignIn option");
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public AmazonSignInPage clickOnSignInOption() {
		click(signInOption);
		return new AmazonSignInPage(driver);
	}
}