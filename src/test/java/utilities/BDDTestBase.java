package utilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public class BDDTestBase extends AbstractTestNGCucumberTests
{
	public static WebDriver driver;
	static String driverPath = System.getProperty("user.dir")+"/drivers/";
	
	@BeforeTest
	@Parameters("Browser")
	public static void webInit(@Optional("Chrome") String Browser)
	{
		try {
			if(Browser.equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
				driver = new ChromeDriver();
			}
			else if(Browser.equalsIgnoreCase("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver", driverPath+"geckodriver.exe");
				File pathBinary = new File("C:\\Users\\V19MOsman\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
				FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
				DesiredCapabilities desired = DesiredCapabilities.firefox();
				FirefoxOptions options = new FirefoxOptions();
				desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
				driver = new FirefoxDriver(options);
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
				
				System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer.exe");
				driver = new InternetExplorerDriver(ieCapabilities);
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //wait until loading the initial webpage
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			//driver.get("https://www.google.com.eg/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	@AfterTest
	void closeDriver()
	{
		driver.quit();
	}*/
}
