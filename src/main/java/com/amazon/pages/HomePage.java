package com.amazon.pages;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {
	WebDriver driver = null;
	Element signInOption = new Element(LocatorType.XPATH, "//span[text()='Account & Lists']", "SignIn option");
    Element helloUserLink = new Element(LocatorType.ID, "nav-link-accountList", "Hello User Link");
    Element startHereLink = new Element(LocatorType.XPATH, ".//*[@id='nav-flyout-ya-newCust']/a", "Start Here Link");


	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public SignInPage clickOnSignInOption() {
		click(signInOption);
		return new SignInPage(driver);
	}

	public SignUpPage gotoSignUpPage(){
        hoverOnElement(helloUserLink);
        waitForElement(startHereLink);
        click(startHereLink);
        return new SignUpPage(driver);
    }
}