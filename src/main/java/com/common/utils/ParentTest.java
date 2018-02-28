package com.common.utils;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

		DesiredCapabilities capabilities;

		// Get browser details from config
		browser = DataReader.getConfigData("browser");

		if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		} else if (browser.equalsIgnoreCase("FF")) {
			driver = new FirefoxDriver();
			capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);


        } else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			capabilities = DesiredCapabilities.chrome();
		}

		ATUReports.setWebDriver(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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