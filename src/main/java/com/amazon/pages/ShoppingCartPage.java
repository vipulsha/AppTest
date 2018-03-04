package com.amazon.pages;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends ParentPage {
	WebDriver driver = null;
	Element deleteLinks = new Element(LocatorType.XPATH, "//input[@value='Delete']", "Delete Link");
	Element cartItemCount = new Element(LocatorType.ID, "nav-cart-count", "Cart Item Count");
    Element totalPriceOnCart = new Element(LocatorType.XPATH, ".//*[@id='gutterCartViewForm']/div[3]/div[2]/div/div[1]/p/span/span[2]", "Cart Item Count");
    Element productAuthor = new Element(LocatorType.CSS, ".a-size-base.sc-product-creator", "Book Author");
    Element productBinding = new Element(LocatorType.CSS, ".a-size-small.a-color-secondary.sc-product-binding", "Book Binding");
    Element productTitle = new Element(LocatorType.CSS, ".a-size-medium.sc-product-title.a-text-bold", "Book Title");

	public ShoppingCartPage(WebDriver driver) {
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

    public Double getTotalPriceOnCart() {
        return Double.parseDouble(getText(totalPriceOnCart).trim().replace("$", ""));
    }

    public String getAuthor(){
	    return (getText(productAuthor));
    }

    public String getBookBinding(){
        return (getText(productBinding));
    }

    public String getBookTitle(){
        return (getText(productTitle));
    }


}