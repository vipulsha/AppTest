package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class ReviewOrderPage extends ParentPage {
	WebDriver driver;
	Element continueButton = new Element(LocatorType.ID, "continue-top", "Continue Button");
	
	public ReviewOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	
}
