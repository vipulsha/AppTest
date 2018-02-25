package com.common.utils;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;

public class ParentTest {
	String browser = "";
	String testClassName = "";
	Properties testData;
	protected WebDriver driver = null;
	
	{
		System.setProperty("atu.reporter.config", System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\atu.properties");
	}
	
	@BeforeSuite
	public void beforeSuite() {
		// All pre-requisite steps
	}

	
	@AfterSuite
	public void afterSuite() throws Exception {
		// Code for sending report to team
	}

	@BeforeClass
	public void setUp() {
		System.out.println("In before class");
		testClassName = this.getClass().getSimpleName();
		testData = DataReader.getTestDataPropertiesObject(testClassName);

		// Get browser details from config
		browser = DataReader.getConfigData("browser");

		if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("FF")) {
			driver = new FirefoxDriver();
			
		} else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		ATUReports.setWebDriver(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(testData.getProperty(("baseURL")));
		ATUReports.add("Navigated to", testData.getProperty("baseURL"),LogAs.PASSED,null);
	}

	@AfterClass
	public void tearDown() {
		System.out.println("In after class");
		driver.quit();
	}

	public String getData(String key) {
		return testData.get(key).toString().trim();
	}
	
    public String getRandomNumberString() {
		return (100000 + new Random().nextInt(900000))+"";
    }
}