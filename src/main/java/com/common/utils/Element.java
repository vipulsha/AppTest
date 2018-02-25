package com.common.utils;

import org.openqa.selenium.By;

public class Element {
	LocatorType locatorType;
	String locatorValue;
	String elementName;
	By by;
	
	public Element(LocatorType locatorType,String locatorValue,String elementName) {
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;
		this.elementName = elementName;
		
		switch (locatorType) {
		case ID:
			by = By.id(locatorValue);
			break;
		case NAME:
			by = By.name(locatorValue);
			break;
		case CLASS_NAME:
			by = By.className(locatorValue);
			break;
		case LINK_TEXT:
			by = By.linkText(locatorValue);
			break;
		case PARTIAL_LINK_TEXT:
			by = By.partialLinkText(locatorValue);
			break;
		case TAG_NAME:
			by = By.tagName(locatorValue);
			break;
		case CSS:
			by = By.cssSelector(locatorValue);
			break;
		case XPATH:
			by = By.xpath(locatorValue);
		}
	}
	
	public LocatorType getLocatorType() {
		return locatorType;
	}
	
	public void setLocatorType(LocatorType locatorType) {
		this.locatorType = locatorType;
	}
	
	public String getLocatorValue() {
		return locatorValue;
	}
	
	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}
	
	public String getElementName() {
		return elementName;
	}
	
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	public By getBy() {
		return by;
	}
	
	public void setBy(By by) {
		this.by = by;
	}
}