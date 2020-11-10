package utilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.GoogleHome;
import pages.GoogleWiki;

public class WebTestBase
{
	public static WebDriver driver;
	static String driverPath = System.getProperty("user.dir")+"/drivers/";
	@BeforeTest
	@Parameters("Browser")
	public static void WebInit(@Optional("Chrome") String Browser)
	{
		try {
			if(Browser.equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", driverPath+"ChromeDriver.exe");
				driver = new ChromeDriver();
			}
			else if(Browser.equalsIgnoreCase("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
//				File pathBinary = new File("C:\\Users\\V19MOsman\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
//				FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
//				DesiredCapabilities desired = DesiredCapabilities.firefox();
//				FirefoxOptions options = new FirefoxOptions();
//				desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
//				driver = new FirefoxDriver(options);
				driver = new FirefoxDriver();
			}
			else if(Browser.equalsIgnoreCase("IE"))
			{
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability("EnableNativeEvents", true);    
				ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
				ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
				ieCapabilities.setCapability("disable-popup-blocking", true);
				ieCapabilities.setCapability("enablePersistentHover", true);
				ieCapabilities.setCapability("ignoreZoomSetting", true);
				ieCapabilities.setCapability("RequireWindowFocus", true);
				
				System.setProperty("webdriver.ie.driver", driverPath+"IEdriverServer.exe");
				driver = new InternetExplorerDriver(ieCapabilities);
			}
			else if(Browser.equalsIgnoreCase("Headless"))
			{
				driver = new HtmlUnitDriver();
			}
			driver.manage().deleteAllCookies();
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //wait until loading the initial webpage
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		WebDriverWait wait=new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
	}
	@AfterTest
	void closedriver()
	{
		driver.quit();
	}
	@Test
	public void testMethod()
	{
		driver.get("https://www.google.com.eg/");
		driver.findElement(By.name("q")).sendKeys("Wikipedia");
		driver.findElement(By.name("btnK")).click();
		System.out.println(driver.getCurrentUrl());
	}
}
