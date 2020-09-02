package utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
/**
 * Unit test for simple App.
 * @param <AndroidDriver>
 */
public class TestBase 
{
	public static AndroidDriver driver;
	public static File appPath = new File(System.getProperty("user.dir")+"/APK/"+"AnaVodafone.apk");
	@BeforeTest
	public static void appInit()
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "emulator-5554"); //10.73.231.92:5555, DEFNW18C05005421
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("automationName", "UiAutomator2"); 
		//capabilities.setCapability("unicodeKeyboard", true); //to disable keypad
		//capabilities.setCapability("resetKeyboard", true); //to disable keypad
	//	capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2"); //To detect elements after closing pop up dialog
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("noReset", true);
	//	capabilities.setCapability("app", appPath.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.emeint.android.myservices");
		capabilities.setCapability("appActivity", "vodafone.vis.engezly.ui.screens.splash.SplashRevampActivity");
		try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //wait before every action max 15sec
	}
}