package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import pages.General;

public class Screenshot extends TestBase 
{
	public static String capture(AndroidDriver driver, String screenshotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+"/Screenshots/"+screenshotName + "_" + General.language[General.languageID] + ".png"; //Write screenshot name with the running language
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);

		return dest;
	}
	
	public static void saveScreenshot(AndroidDriver driver, String screenshotName) throws IOException
	{
		String screenshotPath = Screenshot.capture(driver, screenshotName); //renaming the screenshot image
		String ScreenshotPath = "../screenshots/"+screenshotName + "_" + General.language[General.languageID] + ".png"; //Saving images to be relative to extent report
		ExtentReport.test.log(LogStatus.PASS, "Screenshot Below: " + ExtentReport.test.addScreenCapture(ScreenshotPath)); //saving screeshot in extent report
	}
}
