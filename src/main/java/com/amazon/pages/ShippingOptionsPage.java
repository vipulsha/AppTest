package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class ShippingOptionsPage extends ParentPage {
	WebDriver driver;
	Element selectShippingOption = new Element(LocatorType.XPATH, "//input[@name='order_0_ShippingSpeed']/following::div[contains(text(),'substituteVar')]", "SelectShippingOption");
	Element continueButton = new Element(LocatorType.XPATH, "//input[@value='Continue']", "Continue Button");
	Element productName = new Element(LocatorType.XPATH, "//ul[@class='order-level-item-summary-list']//strong", "ProductName");
	Element price = new Element(LocatorType.XPATH, "//ul[@class='order-level-item-summary-list']//span[@class='a-color-price']", "Price");
	Element quantity = new Element(LocatorType.XPATH, "//ul[@class='order-level-item-summary-list']//span[@class='a-color-secondary']", "Quantity");
	
	public ShippingOptionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void selectShippingOption(String shippingOption) {
		updateElementWithDynamicXpath(selectShippingOption, "substituteVar", shippingOption);
		click(selectShippingOption);
	}
	
	public PaymentMethodPage clickContinueButton() {
		click(continueButton);
		return new PaymentMethodPage(driver);
	}
	
	public String getProductName() {
		return getText(productName);
	}
	
	public String getProductPrice() {
		return getText(price).trim().replace("$", "");
	}
	
	public String getQuantity() {
		return getText(quantity).trim().split(":")[1].trim();
	}
}