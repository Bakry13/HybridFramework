package Desktop;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Utilities.DesktopTestBase;
import Utilities.ExtentReport;
import Utilities.Verification;

public class CalculatorTest extends DesktopTestBase
{
	String appPath = "C:\\Windows\\System32\\calc.exe";
	@Test
	public void Add() throws IOException
	{
		try {
			DesktopInit(appPath);
			//ExtentReport.test = ExtentReport.extent.startTest("1- Check that 1+2=3");
			Thread.sleep(2000);
			service.start();
			driver.findElement(By.name("One")).click();
			driver.findElement(By.name("Plus")).click();
			driver.findElement(By.name("Two")).click();
			driver.findElement(By.name("Equals")).click();
			Thread.sleep(1000);
			String result = driver.findElement(By.id("CalculatorResults")).getText();
			System.out.println(result);
			//Verification.VerifyString(result, "Display is 3", "Result= Display is 3", "Result= "+result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
