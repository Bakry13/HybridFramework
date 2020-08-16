package Utilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WebTestBase
{
	public static WebDriver Driver;
	static String DriverPath = System.getProperty("user.dir")+"/drivers/";
	@BeforeTest
	@Parameters("Browser")
	public static void WebInit(@Optional("Chrome") String Browser)
	{
		try {
			if(Browser.equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", DriverPath+"chromedriver.exe");
				Driver = new ChromeDriver();
			}
			else if(Browser.equalsIgnoreCase("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver", DriverPath+"geckodriver.exe");
				File pathBinary = new File("C:\\Users\\V19MOsman\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
				FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
				DesiredCapabilities desired = DesiredCapabilities.firefox();
				FirefoxOptions options = new FirefoxOptions();
				desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
				Driver = new FirefoxDriver(options);
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
				
				System.setProperty("webdriver.ie.driver", DriverPath+"IEDriverServer.exe");
				Driver = new InternetExplorerDriver(ieCapabilities);
			}
			Driver.manage().deleteAllCookies();
			Driver.manage().window().maximize();
			Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //wait until loading the initial webpage
			Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		WebDriverWait wait=new WebDriverWait(Driver, 20);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
	}
	@AfterTest
	void closeDriver()
	{
		Driver.quit();
	}
	//@Test
	public void testMethod()
	{
		Driver.get("https://www.google.com.eg/");
	}
}
