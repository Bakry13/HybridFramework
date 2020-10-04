package utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.testng.AbstractTestNGCucumberTests;
/**
 * Unit test for simple App.
 * @param <AndroidDriver>
 */
public class TestBase 
{
	public static AndroidDriver driver;
	static GlobalParams params = new GlobalParams();
	public static File appPath = new File(System.getProperty("user.dir")+"/APK/"+"AnaVodafone.apk");
	@BeforeTest
	public static void appInit()
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", params.getDeviceName()); //10.73.231.92:5555, DEFNW18C05005421
		capabilities.setCapability("platformName", params.getPlatformName());
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
	
	/*public class TestBase extends AbstractTestNGCucumberTests {
    public static AppiumDriver driver;
    static GlobalParams params = new GlobalParams();
    public static File appPath = new File(System.getProperty("user.dir") + "/sources/" + "AnaVodafone.apk");


    @BeforeTest
    @Parameters("Language")
    public static void appInit(String appLanguage) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        params.initializeGlobalParams(appLanguage);

        capabilities.setCapability("deviceName", params.getDeviceName()); //10.73.231.92:5555, DEFNW18C05005421
        capabilities.setCapability("platformName", params.getPlatformName());


        switch (GlobalParams.currentLanguage) {
            case ENGLISH:
                capabilities.setCapability("language", "en");
                capabilities.setCapability("locale", "US");
                break;
            case ARABIC:
                capabilities.setCapability("language", "ar");
                capabilities.setCapability("locale", "EG");
        }
        switch (params.getPlatformName()) {
            case ANDROID:
                //capabilities.setCapability("unicodeKeyboard", true); //to disable keypad
                //capabilities.setCapability("resetKeyboard", true); //to disable keypad
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                capabilities.setCapability("systemPort", params.getSystemPort());
                capabilities.setCapability("fullReset", false);
                capabilities.setCapability("noReset", false);
              //  capabilities.setCapability("app", getSourceAppPath(GlobalParams.Platform.ANDROID));
                capabilities.setCapability("appPackage", "com.emeint.android.myservices");
                capabilities.setCapability("appActivity", "vodafone.vis.engezly.ui.screens.splash.SplashRevampActivity");
                break;
            case IOS:
                capabilities.setCapability(MobileCapabilityType.UDID, params.getUDID());
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability("bundleId", "com.emeint.engezly");
                capabilities.setCapability("wdaLocalPort", params.getWdaLocalPort());
                capabilities.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
                capabilities.setCapability("app", getSourceAppPath(GlobalParams.Platform.IOS));
//                capabilities.setCapability("xcodeOrgId", "LQVUT2443V");
//                capabilities.setCapability("xcodeSigningId", "iPhone Developer");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.7");
                capabilities.setCapability("useNewWDA", "true");
                capabilities.setCapability("agentPath",   System.getProperty("user.dir") + File.separator + "resources" + File.separator + "sources"
                        + File.separator + "mobile"+ File.separator + "apps" + File.separator+ "WebDriver/WebDriverAgent.xcodeproj");
                capabilities.setCapability("bootstrapPath", System.getProperty("user.dir") + File.separator + "resources" + File.separator + "sources"
                        + File.separator + "mobile"+ File.separator + "apps" + File.separator + "WebDriver");
                capabilities.setCapability("autoAcceptAlerts", true);
                break;
        }

        try {
            URL server = new URL("http://0.0.0.0:4723/wd/hub");

            switch (params.getPlatformName()) {
                case ANDROID:
                    driver = new AndroidDriver(server, capabilities);
                    break;
                case IOS:
                    driver = new IOSDriver(server, capabilities);
                    break;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); //wait before every action max 15sec
    }

    @AfterTest
    public void clearDriver() {
        driver.quit();
    }

    private static String getSourceAppPath(GlobalParams.Platform platform) {
        String path = "";
        switch (platform) {
            case ANDROID:
                path = System.getProperty("user.dir") + File.separator + "resources" + File.separator + "sources"
                        + File.separator + "mobile" + File.separator + "apps" + File.separator + "AVA.apk";
                break;
            case IOS:
                path = System.getProperty("user.dir") + File.separator + "resources" + File.separator + "sources"
                        + File.separator + "mobile" + File.separator + "apps" + File.separator + "Ana Vodafone.app";
        }

        return path;
    }
}
	 */
}