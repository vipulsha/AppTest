package com.common.utils;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class Assertion {

	public static void assertTrue(boolean condition,String description) {
		if (condition) {
			markPass(description);
		} else {
			markFail(description);
		}
	}
	
	public static void assertNotNull(Object object,String description) {
		if (object != null) {
			markPass(description);
		} else {
			markFail(description);
		}
	}
	
	public static void assertEquals(Object s1,Object s2,String description) {
		if (s1 instanceof String && s2 instanceof String) {
			if (s1.equals(s2)) {
				ATUReports.add(description, s1+"", s2+"", LogAs.PASSED, null);
				System.out.println(description+" : "+"PASS");
			} else {
				ATUReports.add(description, s1+"", s2+"", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				System.out.println(description+" : "+"Fail "+"Expected: "+s1+" Actual: "+s2);
			}	
		} else {
			if (s1 == s2) {
				ATUReports.add(description, s1+"", s2+"", LogAs.PASSED, null);
				System.out.println(description+" : "+"PASS");
			} else {
				ATUReports.add(description, s1+"", s2+"", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				System.out.println(description+" : "+"Fail "+"Expected: "+s1+" Actual: "+s2);
			}
		}
	}
	
	public static void markPass(String description) {
		ATUReports.add(description,LogAs.PASSED,null);
		System.out.println(description+" : "+"PASS");
	}
	
	public static void markFail(String description) {
		System.out.println(description+" : "+"PASS");
		ATUReports.add(description,LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
	}
}
