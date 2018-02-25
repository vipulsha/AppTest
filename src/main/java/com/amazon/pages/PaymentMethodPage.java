package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class PaymentMethodPage extends ParentPage {
	WebDriver driver;
	Element continueButton = new Element(LocatorType.ID, "continue-top", "Continue Button");
	
	public PaymentMethodPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickContinueButton() {
		click(continueButton);
	}
}
