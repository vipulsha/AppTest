package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class SearchResultPage extends ParentPage {
	WebDriver driver = null;
	Element items = new Element(LocatorType.XPATH, "//div[@class='a-row a-spacing-small']/div/a[contains(@title,'substituteVar')]", "Items");
	
	public SearchResultPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public List<WebElement> getItemsInSearchResult(String keyword) {
		// Prepare dynamix xpath
		String dynamicXpath = getDynamicXpath(items.getLocatorValue(), "substituteVar", keyword);
		items.setLocatorValue(dynamicXpath);
		items.setBy(By.xpath(dynamicXpath));
		return waitForElements(items);
	}
}
