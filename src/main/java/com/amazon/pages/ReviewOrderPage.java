package com.amazon.pages;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;
import org.openqa.selenium.WebDriver;

public class ReviewOrderPage extends ParentPage {
	WebDriver driver;

	/*@FindBy(id = "continue-top")
    WebElement continueButton;*/

	Element continueButton = new Element(LocatorType.ID, "continue-top", "Continue Button");
	
	public ReviewOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	
}
