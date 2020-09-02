package app;

import java.io.IOException;

import org.testng.annotations.Test;

import pages.General;
import pages.Login;
import utilities.Excel;
import utilities.ExcelInit;
import utilities.ExtentReport;
import utilities.Screenshot;
import utilities.TestBase;

public class LoginExecuter extends TestBase
{
	public static int RowNumber = 1;
	static String TCPath = System.getProperty("user.dir") + "/inputs/" + "Login.xlsx";
	//===================================Let's start login tests===================================================
	@Test
	public static void MISubscriptionTestActions() throws IOException, InterruptedException
	{	
		//AppInit(); 
		General.PageFactoryObject(); //App Initializations
		Excel.setPath(TCPath);
		//----------------------Here we will start to loop on test cases steps in the excel sheet---------------------
		while (!(ExcelInit.keyword.equals("EndOfTest"))) //loop on steps  keyword != "EndOfTest"
		{
			try {
				ExcelInit.keyword = Excel.read(RowNumber, ExcelInit.keywordCol); //Read keyword action for each step
				System.out.println(ExcelInit.keyword);		
				ExcelInit.msisdn = Excel.read(RowNumber, ExcelInit.msisdnCol); //Read needed data for every step
				System.out.println(ExcelInit.msisdn);
				ExcelInit.password = Excel.read(RowNumber, ExcelInit.passwordCol); //Read needed data for every step
				System.out.println(ExcelInit.password);
				ExcelInit.tcName = Excel.read(RowNumber, ExcelInit.tcNameCol); //Read needed TC name for every step
				System.out.println(ExcelInit.tcName);
				//----------------------------Test cases start and end in report-----------------------------------
				ExtentReport.startEndTC(ExcelInit.keyword, ExcelInit.tcName);
				//-----------------------------------------Find the keyword----------------------------------------
				if (ExcelInit.keyword.equals("EnterMSISDN"))
				{
					Login.msisdn.sendKeys(ExcelInit.msisdn); //Enter msisdn
				}
				else if (ExcelInit.keyword.equals("PressProceed"))
				{
					Login.proceedBtn.click(); //Press Proceed Button
				}
				else if (ExcelInit.keyword.equals("EnterPassword"))
				{
					Login.password.sendKeys(ExcelInit.password); //Enter password
				}
				if (ExcelInit.keyword.equals("TakeScreenshot"))
				{
					Screenshot.saveScreenshot(driver, "Home"); //Saving screenshot in the directory and in the extent report
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			RowNumber++;
		}
	}
}