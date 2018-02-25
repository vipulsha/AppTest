package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;
import com.common.utils.SelectBy;

public class ProductDetailsPage extends ParentPage {
	WebDriver driver;
	Element capacity = new Element(LocatorType.XPATH, "//div[@id='variation_size_name']//span[@class='a-size-base' and text()='substituteVar']", "Capacity");
	Element addToCartButton = new Element(LocatorType.ID, "add-to-cart-button", "Add To Cart Button");
	Element availabilityStatus = new Element(LocatorType.XPATH, "//div[@id='availability']/span[contains(text(),'In Stock')]", "Availability Status");
	Element productName = new Element(LocatorType.ID, "productTitle", "ProductTitle");
	Element price = new Element(LocatorType.ID, "priceblock_ourprice", "Price"); 
	Element selectQuantity = new Element(LocatorType.ID, "quantity", "Select Quantity dropdown");
	Element productAddedToCartMessage = new Element(LocatorType.XPATH, "//div[@id='attachAddBaseItemSuccessAlert']//h4[@class='a-alert-heading']", "Product Added To Cart Message"); 
	Element noOfItemsAddedToCart = new Element(LocatorType.ID, "attach-accessory-cart-total-string", "NoOfItemsInCart");
	Element totalPriceOnCart = new Element(LocatorType.ID, "attach-accessory-cart-subtotal", "TotalPriceOnCart");
	Element proceedToCheckoutButton = new Element(LocatorType.ID, "attach-accessory-proceed-to-checkout-text", "ProceedToCheckoutButton");
	Element noThanksButton = new Element(LocatorType.ID, "siNoCoverage-announce", "NoThanksButton");
	
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void selectCapacity(String inTB) {
		updateElementWithDynamicXpath(capacity, "substituteVar", inTB);
		click(capacity);
		try {Thread.sleep(3000);} catch (Exception e) {}
	}
	
	public boolean isProductAvailable() {
		return isElementDisplayed(availabilityStatus);
	}
	
	public String getProductName() {
		return getText(productName);
	}
	
	public String getPrice() {
		return getText(price);
	}
	
	public void selectQuantity(String value) {
		selectDropdown(selectQuantity, SelectBy.VALUE, value);
	}
	
	public void clickAddToCartButton() {
		click(addToCartButton);
	}
	
	public boolean isProductAddedToCart() {
		if (getText(productAddedToCartMessage).equals("Added to Cart"))
			return true;
		else
			return false;
	}
	
	public String getNoOfItemsAddedToCart() {
		String s = getText(noOfItemsAddedToCart).trim();
		s = s.split("(")[1];
		return s.split(" ")[0];
	}
	
	public Double getTotalPriceOnCart() {
		return Double.parseDouble(getText(totalPriceOnCart));
	}
	
	public ShippingAddressPage clickProceedToCheckout() {
		click(proceedToCheckoutButton);
		return new ShippingAddressPage(driver);
	}
	
	public CartDetailsPage closeDataRecoveryPlanPopUp() {
		try {
			click(noThanksButton);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CartDetailsPage(driver);
	}
}