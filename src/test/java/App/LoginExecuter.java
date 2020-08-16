package App;

import java.io.IOException;

import org.testng.annotations.Test;

import Pages.General;
import Pages.Login;
import Utilities.Excel;
import Utilities.ExcelInit;
import Utilities.ExtentReport;
import Utilities.TestBase;

public class LoginExecuter extends TestBase
{
	public static int RowNumber = 1;
	static String TCPath = System.getProperty("user.dir") + "/Inputs/" + "Login.xlsx";
	//===================================Let's start login tests===================================================
	@Test
	public static void MISubscriptionTestActions() throws IOException, InterruptedException
	{	
		//AppInit(); 
		General.PageFactoryObject(); //App Initializations
		Excel.setPath(TCPath);
		//----------------------Here we will start to loop on test cases steps in the excel sheet---------------------
		while (!(ExcelInit.Keyword.equals("EndOfTest"))) //loop on steps  Keyword != "EndOfTest"
		{
			try {
				ExcelInit.Keyword = Excel.read(RowNumber, ExcelInit.KeywordCol); //Read Keyword action for each step
				System.out.println(ExcelInit.Keyword);		
				ExcelInit.MSISDN = Excel.read(RowNumber, ExcelInit.MSISDNCol); //Read needed data for every step
				System.out.println(ExcelInit.MSISDN);
				ExcelInit.Password = Excel.read(RowNumber, ExcelInit.PasswordCol); //Read needed data for every step
				System.out.println(ExcelInit.Password);
				ExcelInit.TCName = Excel.read(RowNumber, ExcelInit.TCNameCol); //Read needed TC name for every step
				System.out.println(ExcelInit.TCName);
				//----------------------------Test cases start and end in report-----------------------------------
				ExtentReport.StartEndTC(ExcelInit.Keyword, ExcelInit.TCName);
				//-----------------------------------------Find the keyword----------------------------------------
				if (ExcelInit.Keyword.equals("EnterMSISDN"))
				{
					Login.MSISDN.sendKeys(ExcelInit.MSISDN); //Enter MSISDN
				}
				else if (ExcelInit.Keyword.equals("PressProceed"))
				{
					Login.ProceedBtn.click(); //Press Proceed Button
				}
				else if (ExcelInit.Keyword.equals("EnterPassword"))
				{
					Login.Password.sendKeys(ExcelInit.Password); //Enter password
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			RowNumber++;
		}
	}
}