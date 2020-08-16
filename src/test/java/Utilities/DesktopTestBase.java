package Utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterTest;

public class DesktopTestBase 
{
	public static WiniumDriver driver;
	public static WiniumDriverService service;
	static String DriverPath = System.getProperty("user.dir")+"/drivers/";

	public static void DesktopInit(String appPath) throws IOException
	{
		DesktopOptions option = new DesktopOptions();
		option.setApplicationPath(appPath);
		service = new WiniumDriverService.Builder()
				.usingDriverExecutable(new File(System.getProperty("user.dir")+"\\drivers\\Winium.Desktop.Driver.exe"))
				.usingPort(9999)
				.withVerbose(true)
				.withSilent(false)
				.buildDesktopService();
				//service.start();
		driver = new WiniumDriver(service,option);
		//driver = new WiniumDriver(new URL("http://localhost:9999"),option);
	}

	//@AfterTest
	public void closeService()
	{
		service.stop();
	}
}
