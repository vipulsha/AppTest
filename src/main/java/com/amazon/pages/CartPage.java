package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class CartPage extends ParentPage {
	WebDriver driver = null;
	Element deleteLinks = new Element(LocatorType.XPATH, "//input[@value='Delete']", "Delete Link");
	Element cartItemCount = new Element(LocatorType.ID, "nav-cart-count", "Cart Item Count");
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void deleteAllItems() {
		List<WebElement> list = waitForElements(deleteLinks);
		for (WebElement webElement : list) {
			webElement.click();
		}

		if (Integer.parseInt(getText(cartItemCount)) > 0) {
			deleteAllItems();
		}
		System.out.println("Cart cleared");
	}
}