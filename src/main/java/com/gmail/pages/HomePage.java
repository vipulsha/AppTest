package com.gmail.pages;

import org.openqa.selenium.WebDriver;

import com.common.utils.Element;
import com.common.utils.LocatorType;
import com.common.utils.ParentPage;

public class HomePage extends ParentPage {
	WebDriver driver = null;
	Element composeButton = new Element(LocatorType.XPATH, "//div[text()='COMPOSE']", "Compose Button");
	Element settingsIcon = new Element(LocatorType.XPATH, "//div[@class='T-I J-J5-Ji ash T-I-ax7 L3']", "Settings Icon");
	Element settingOptions = new Element(LocatorType.XPATH, "//div[@role='menuitem']/div[text()='replaceString']", "Setting Options"); // Make it dynamic
	Element inboxLink = new Element(LocatorType.PARTIAL_LINK_TEXT, "Inbox", "Inbox Link");
	Element email = new Element(LocatorType.XPATH, "(//div[@class='Cp']/div/table//span[@class='bog'])[index]", "Email");
	Element toTextbox = new Element(LocatorType.NAME, "to", "To textbox");
	Element subjectTextbox = new Element(LocatorType.NAME, "subjectbox", "Subject textbox");
	Element messageBody = new Element(LocatorType.XPATH, "//div[@aria-label='Message Body']", "Message Body");
	Element sendButton = new Element(LocatorType.XPATH, "//div[@role='button' and text()='Send']", "Send button");
	Element messageSentMessage = new Element(LocatorType.XPATH, "//div[@class='vh' and contains(text(),'Your message has been sent')]", "MessageSentMessage");
	Element emailSubjectInList = new Element(LocatorType.XPATH, "//div[@class='Cp']/div/table//span[@class='bog']/b[text()='emailSubject']", "EmailSubjectInList");
	Element userAccountIcon = new Element(LocatorType.XPATH, "//span[@class='gb_ab gbii']", "User Account Icon");
	Element signOutLink = new Element(LocatorType.LINK_TEXT, "Sign out", "SignOutLink");
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isUserLoggedIn(String emailId) {
		return waitForTitleContains(emailId);
	}
	
	public void openEmail(int n) {
		Element emailN = getDynamicWebElemet(email, "index", n+"");
		waitForElement(emailN).click();
	}
	
	public void clickInboxLink() {
		click(inboxLink);
	}
	
	public void clickComposeButton() {
		click(composeButton);
	}
	
	public void clickSendButton() {
		click(sendButton);
	}
	
	public void enterTo(String to) {
		enterText(toTextbox, to);
	}
	
	public void enterSubject(String subject) {
		enterText(subjectTextbox, subject);
	}
	
	public void enterMessageBody(String messageBody) {
		enterText(this.messageBody, messageBody);
	}
	
	public boolean isMesasgeSentMessageDisplayed() {
		return isElementDisplayed(messageSentMessage);
	}
	
	public boolean isEmailPresentWithSubject(String subject) {
		return isElementDisplayed(getDynamicWebElemet(emailSubjectInList, "emailSubject", subject.trim()));
	}
	
	public void clickSettingsIcon() {
		click(settingsIcon);
	}
	
	public SettingsPage clickOnSettingsOption(String option) {
		click(getDynamicWebElemet(settingOptions, "replaceString", option));
		return new SettingsPage(driver);
	}
	
	public GmailSignInPage logOut() {
		click(userAccountIcon);
		click(signOutLink);
		return new GmailSignInPage(driver);
	}
}