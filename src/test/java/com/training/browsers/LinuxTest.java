package com.training.browsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LinuxTest {
	
	ChromeOptions chromeoptions = new ChromeOptions();
	
    FirefoxOptions firefoxoptions = new FirefoxOptions();
    DesiredCapabilities firefoxcapabilities=DesiredCapabilities.firefox();
    
	@BeforeClass
	public void beforeClass() {
		//Below code is disable notifications from chrome browser during the testing
		/*Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2); 
		chromeoptions.setExperimentalOption("prefs", prefs);
		chromeoptions.addArguments("--no-sandbox");
		chromeoptions.addArguments("headless");
		System.setProperty("webdriver.chrome.driver", "chromedriver");*/
		
		
		//FirefoxProfile firefoxprofile = new FirefoxProfile();
	    //firefoxprofile.setPreference("permissions.default.desktop-notification", 1);
		//firefoxcapabilities.setCapability(FirefoxDriver.PROFILE, firefoxprofile);
	    
		//firefoxoptions.setLogLevel(FirefoxDriverLogLevel.DEBUG);
	    //firefoxcapabilities.setCapability("marionatte", false);
	    
	    //firefoxoptions.merge(firefoxcapabilities);
		//firefoxoptions.addArguments("headless");
		System.setProperty("webdriver.gecko.driver", "geckodriver");
	}
	
	@Test (enabled = false)
	public void loginTest1() {
		WebDriver driver = new ChromeDriver(chromeoptions);
		facebookLoginTestChrome(driver);
		driver.close();
	}
	
	@Test (enabled = false)
	public void loginTest2() {
		WebDriver driver = new ChromeDriver(chromeoptions);
		facebookLoginTestChrome(driver);
		driver.close();
	}
	
	@Test (enabled = false)
	public void loginTest3() {
		WebDriver driver = new FirefoxDriver(firefoxoptions);	
		facebookLoginTestFirefox(driver);
		driver.close();
	}
	
	@Test (enabled = true)
	public void loginTest4() {
		System.out.println("step1");
		WebDriver driver = new FirefoxDriver(firefoxoptions);
		System.out.println("step2");
		facebookLoginTestFirefox(driver);
		System.out.println("step3");
		driver.close();
		System.out.println("step4");
	}
	
	public static void facebookLoginTestChrome(WebDriver driver) {
		driver.get("https://www.facebook.com");
		
		driver.findElement(By.id("email")).sendKeys("gamecheck280@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("system123");
		driver.findElement(By.name("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=\"submit\"]")));
		
		List <WebElement> list = new ArrayList<WebElement>();
		list = driver.findElements(By.tagName("span"));
		boolean userfound = false;
		for(WebElement e: list) {
			if (e.getText().contains("Gamecheck") && !e.getText().contains("Not you") && !e.getText().contains("Log in as")) {
				userfound = true;
				break;
			}
		}
		
		driver.findElement(By.id("userNavigationLabel")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span//form[@method='post']")));
		WebElement ele = driver.findElement(By.xpath("//span//form[@method='post']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='websubmit']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@role='button' and contains(text(), 'Create New Account')]")));
		
		AssertJUnit.assertTrue(userfound);
	}
	
	public static void facebookLoginTestFirefox(WebDriver driver) {
		driver.get("https://www.facebook.com");
		
		driver.findElement(By.id("email")).sendKeys("gamecheck280@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("system123");
		driver.findElement(By.name("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type=\"submit\"]")));
		
		List <WebElement> list = new ArrayList<WebElement>();
		list = driver.findElements(By.tagName("span"));
		boolean userfound = false;
		for(WebElement e: list) {
			if (e.getText().contains("Gamecheck") && !e.getText().contains("Not you") && !e.getText().contains("Log in as")) {
				userfound = true;
				break;
			}
		}
		
		driver.findElement(By.id("userNavigationLabel")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span//form[@method='post']")));
		WebElement ele = driver.findElement(By.xpath("//span//form[@method='post']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='websubmit']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@role='button' and contains(text(), 'Create New Account')]")));
		
		AssertJUnit.assertTrue(userfound);
	}

	@AfterClass
	public void afterClass() {
		
	}
	
	public static void sleep(int n) {
		try {
			Thread.sleep(n*1000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
