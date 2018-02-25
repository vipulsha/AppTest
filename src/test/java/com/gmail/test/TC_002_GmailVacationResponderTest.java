package com.gmail.test;

import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.common.utils.Assertion;
import com.common.utils.ParentTest;
import com.gmail.pages.GmailSignInPage;
import com.gmail.pages.HomePage;
import com.gmail.pages.SettingsPage;

public class TC_002_GmailVacationResponderTest extends ParentTest {

	@Test
	public void amazonTest() {
		try {
			GmailSignInPage signInPage = new GmailSignInPage(driver);
			signInPage.enterEmailId(getData("emailId"));
			signInPage.clickNextButton();
			signInPage.enterPassword(getData("password"));
			HomePage homePage = signInPage.clickSignInButton();
			
			// Verify user got login
			Assertion.assertTrue(homePage.isUserLoggedIn(getData("emailId")), "Verify User got login");

			homePage.clickInboxLink();
			homePage.openEmail(1);
			homePage.clickComposeButton();

			homePage.enterTo(getData("to"));
			String subject = getData("subject")+getRandomNumberString();
			homePage.enterSubject(subject);
			homePage.enterMessageBody(getData("messageBody"));
			homePage.clickSendButton();
			Assertion.assertTrue(homePage.isMesasgeSentMessageDisplayed(), "Verify mail sent successfully");

			// Verify new email displayed in Inbox
			homePage.clickInboxLink();
			Assertion.assertTrue(homePage.isEmailPresentWithSubject(subject), "Verify email is present in list");
			
			homePage.clickSettingsIcon();
			SettingsPage settingsPage = homePage.clickOnSettingsOption(getData("option"));
			settingsPage.selectVacationResponder(getData("vacationResponder"));
			settingsPage.clickOnFirstDayTextbox();
			
			// Select tomorrow's date
			Date currDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currDate);
			calendar.add(Calendar.DATE, 1);
			Date nextDate = calendar.getTime();

			int month = (currDate.getMonth())+1;
			int month2 = (nextDate.getMonth())+1;
			int nextDay = nextDate.getDate();

			// Check if next date falls in same month
			if (month2 != month) {
				settingsPage.clickOnNextMonthIcon();
			}
			settingsPage.selectDateFromCurrentMonth(nextDay+"");
			settingsPage.enterSubject(getData("subject2"));
			settingsPage.enterMessageBody(getData("message"));
			homePage = settingsPage.clickOnSaveChangesButton();

			// Logout
			signInPage = homePage.logOut();
			Assertion.assertTrue(signInPage.isUserLoggedOut(), "Verify User got logged out");			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ATUReports.add("Exception: "+e.getMessage(),LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
		}
	}
}