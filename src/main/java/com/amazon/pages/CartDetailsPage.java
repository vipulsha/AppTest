package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class CartDetailsPage extends ParentPage {

	WebDriver driver;
	
	Element productAddedToCartMessage = new Element(LocatorType.XPATH, "//div[@id='huc-v2-order-row-confirm-text']/h1[contains(text(),'Added to Cart')]", "Product Added To Cart Message"); 
	Element noOfItemsAddedToCart = new Element(LocatorType.XPATH, "//div[@id='hlb-subcart']/div/span/span", "NoOfItemsInCart");
	Element totalPriceOnCart = new Element(LocatorType.XPATH, "//div[@id='hlb-subcart']//span[contains(@class,'a-color-price hlb-price')]", "TotalPriceOnCart");
	Element proceedToCheckoutButton = new Element(LocatorType.ID, "hlb-ptc-btn-native", "ProceedToCheckoutButton");
	
	public CartDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean isProductAddedToCart() {
		if (isElementDisplayed(productAddedToCartMessage))
			return true;
		else
			return false;
	}
	
	public String getNoOfItemsAddedToCart() {
		String s = getText(noOfItemsAddedToCart).trim();
		s = s.split("\\(")[1];
		return s.split(" ")[0];
	}
	
	public Double getTotalPriceOnCart() {
		return Double.parseDouble(getText(totalPriceOnCart).trim().replace("$", ""));
	}
	
	public ShippingAddressPage clickProceedToCheckout() {
		click(proceedToCheckoutButton);
		return new ShippingAddressPage(driver);
	}

}
