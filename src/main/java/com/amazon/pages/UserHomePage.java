package com.amazon.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class UserHomePage extends ParentPage {
	WebDriver driver = null;
	Element loggedInUserName = new Element(LocatorType.XPATH, "//a[@id='nav-link-accountList']/span", "LoggedIn User Name");
	Element searchTextbox = new Element(LocatorType.ID, "twotabsearchtextbox", "SearchTextbox");
	Element cartItemCount = new Element(LocatorType.ID, "nav-cart-count", "CartItemCount");
	Element searchIcon = new Element(LocatorType.XPATH, "//input[@value='Go']", "SearchIcon");
	Element signOutLink = new Element(LocatorType.ID,"nav-item-signout-sa","SignOutLink");
	
	public UserHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		if (!driver.getCurrentUrl().contains("/gp/yourstore/home")){
			throw new IllegalArgumentException("Not on Amazon Userhome page... Current page is: " + driver.getCurrentUrl());
		}
	}

	public String getLoggedInUsername() {
		return getText(loggedInUserName);
	}

	public boolean isCartEmpty() {
		String count = getText(cartItemCount);
		if (Integer.parseInt(count) > 0) 
			return false;
		else 
			return true;
	}

	public void clearCart() {
		click(cartItemCount);
		new CartPage(driver).deleteAllItems();
	}
	
	public void enterSearchCriteria(String searchCriteria) {
		enterText(searchTextbox, searchCriteria);
	}
	
	public SearchResultPage clickSearchIcon() {
		click(searchIcon);
		return new SearchResultPage(driver);
	}
	
	public AmazonSignInPage logOut() {
		hoverOnElement(loggedInUserName);
		click(signOutLink);
		return new AmazonSignInPage(driver);
	}
}