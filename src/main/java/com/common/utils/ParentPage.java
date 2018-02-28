package com.common.utils;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ParentPage {

	protected WebDriver driver;
	public static final int WAIT_FOR_ELEMENT_SECS = Integer.parseInt(DataReader.getConfigData("waitForElementTimeInSec"));
	protected WebDriverWait wait = null;

	public ParentPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECS);
	}

	protected void click(Element element) {
		waitForElement(element, ExpectedCondition.CLICKABLE).click();
//		System.out.println("Clicked on "+element.getElementName());
		ATUReports.add("Click on "+element.getElementName(), LogAs.PASSED, null);
	}

	protected void enterText(Element element, String text) {
		waitForElement(element).sendKeys(text);
//		System.out.println("Entered '"+text+"' in "+element.getElementName());
		ATUReports.add("Enter in "+element.getElementName(), text,LogAs.PASSED,null);
	}

	protected WebElement waitForElement(Element element) {
		By by = element.getBy();
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	protected WebElement waitForElement(Element element, ExpectedCondition condition) {
		By by = element.getBy();
		WebElement webElement = null;
		switch (condition) {
		case VISIBLE:
			webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			break;
		case CLICKABLE:
			webElement = wait.until(ExpectedConditions.elementToBeClickable(by));	
			break;
		case SELECTED:
			wait.until(ExpectedConditions.elementSelectionStateToBe(by, true));
			webElement = driver.findElement(by);
			break;
		case DESELECTED:
			wait.until(ExpectedConditions.elementSelectionStateToBe(by, false));
			webElement = driver.findElement(by);
			break;
		case PRESENCE:
			webElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}
		return webElement;
	}
	
	public List<WebElement> waitForElements(Element element) {
		By by = element.getBy();
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	protected void selectDropdown(Element dropdownElement,SelectBy selectBy, String textOrIndexOrValue) {
		Select select = new Select(waitForElement(dropdownElement));
		switch (selectBy) {
		case VISIBLE_TEXT:
			select.selectByVisibleText(textOrIndexOrValue);
//			System.out.println("Selected '"+textOrIndexOrValue+"' from dropdown "+dropdownElement.getElementName());
			ATUReports.add("Selected '"+textOrIndexOrValue+"' from dropdown "+dropdownElement.getElementName(),LogAs.PASSED,null);
			break;
		case VALUE:
			select.selectByValue(textOrIndexOrValue);
//			System.out.println("Selected element with value '"+textOrIndexOrValue+"' from dropdown "+dropdownElement.getElementName());
			ATUReports.add("Selected element with value '"+textOrIndexOrValue+"' from dropdown "+dropdownElement.getElementName(),LogAs.PASSED,null);
			break;
		case INDEX:
			select.selectByIndex(Integer.parseInt(textOrIndexOrValue.trim()));
//			System.out.println("Selected element with index '"+textOrIndexOrValue+"' from dropdown "+dropdownElement.getElementName());
			ATUReports.add("Selected element with index '"+textOrIndexOrValue+"' from dropdown "+dropdownElement.getElementName(),LogAs.PASSED,null);
			break;
		}
	}

	protected boolean isElementDisplayed(Element element) {
		try {
			waitForElement(element,ExpectedCondition.VISIBLE);
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	protected String getText(Element element) {
		return waitForElement(element).getText();
	}
	
	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public void navigateTo(String url) {
		driver.navigate().to(url);
		System.out.println("Navigated to: "+url);
		ATUReports.add("Navigated to: "+url,LogAs.PASSED,null);
	}
	
	protected String getDynamicXpath(String rawXpath,String strReplace,String strWith) {
		return rawXpath.replace(strReplace, strWith);
	}
	
	public void updateElementWithDynamicXpath(Element element,String strReplace,String strWith) {
		element.setLocatorValue(element.getLocatorValue().replace(strReplace, strWith));
		element.setBy(By.xpath(element.getLocatorValue()));
	}
	
	public Element getDynamicWebElemet(Element rawWebElement,String strReplace,String strWith) {
		return new Element(LocatorType.XPATH, rawWebElement.getLocatorValue().replace(strReplace, strWith), rawWebElement.getElementName()); 
	}
	
	public WebElement hoverOnElement(Element element) {
		WebElement wElement = waitForElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(wElement).perform();
		return wElement;
	}
	
	public boolean waitForTitleContains(String text) {
		return wait.until(ExpectedConditions.titleContains(text));
	}
	
	public boolean waitForTitleEquals(String text) {
		return wait.until(ExpectedConditions.titleIs(text));
	}
}