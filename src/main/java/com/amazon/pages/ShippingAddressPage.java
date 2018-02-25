package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class ShippingAddressPage extends ParentPage {
	WebDriver driver;
	Element selectAddressButton = new Element(LocatorType.XPATH, "//ul[@class='displayAddressUL']//b[text()='substituteVar']/following::a[contains(text(),'Deliver to this address')]", "SelectAddressButton");
	
	public ShippingAddressPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public ShippingOptionsPage selectAddress(String addressHeader) {
		// Update address dynamically in xpath
		updateElementWithDynamicXpath(selectAddressButton, "substituteVar", addressHeader);
		click(selectAddressButton);
		return new ShippingOptionsPage(driver);
	}
}
