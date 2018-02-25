package com.gmail.pages;

import org.openqa.selenium.WebDriver;
import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class SettingsPage extends ParentPage {
	WebDriver driver = null;
	Element composeButton = new Element(LocatorType.XPATH, "//div[text()='COMPOSE']", "Compose Button");
	Element vacationResponderRadioButton = new Element(LocatorType.XPATH, "//span[@class='rS']/label[text()='onOrOff']/parent::span/parent::td/preceding-sibling::td/input", "VacationResponder option");
	Element firstDayTextbox = new Element(LocatorType.XPATH, "//input[@class='sE']", "FirstDay textbox");
	Element dateElemet = new Element(LocatorType.XPATH, "//div[@class='J-jxwiSc J-JB-KA']//td[@class='J-JB-KA-JB' and text()='dateToReplace']", "DateFromCalender");
	Element nextMonthIcon = new Element(LocatorType.XPATH, "//button[text()='»']", "NextMonthIcon");
	Element subjectTextbox = new Element(LocatorType.XPATH, "//input[@aria-label='Subject']", "Subject textbox");
	Element messageBody = new Element(LocatorType.XPATH, "//div[@aria-label='Vacation responder']", "Message Body");
	Element saveChangesButton = new Element(LocatorType.XPATH, "//button[text()='Save Changes']", "Save changes button");
	
	public SettingsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void selectVacationResponder(String onOrOff) {
		if (onOrOff.trim().equalsIgnoreCase("ON")) {
			click(getDynamicWebElemet(vacationResponderRadioButton, "onOrOff", "Vacation responder on"));
		} else {
			click(getDynamicWebElemet(vacationResponderRadioButton, "onOrOff", "Vacation responder off"));
		}
	}
	
	public void selectDateFromCurrentMonth(String date) {
		click(getDynamicWebElemet(dateElemet, "dateToReplace", date));
	}
	
	public void clickOnNextMonthIcon() {
		click(nextMonthIcon);
	}
	
	public void enterSubject(String subject) {
		enterText(subjectTextbox, subject);
	}
	
	public void enterMessageBody(String messageBody) {
		enterText(this.messageBody, messageBody);
	}
	
	public HomePage clickOnSaveChangesButton() {
		click(saveChangesButton);
		return new HomePage(driver);
	}
	
	public void clickOnFirstDayTextbox() {
		click(firstDayTextbox);
	}
}