package utilities;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport
{
	public static int tcNumb = 1;
	public static int testId = 0;
	public static ExtentReports extent;
	public static ExtentTest test;
//===========================================================================================================	
	@BeforeTest
	@Parameters("ReportName")
	public void startReport(String ReportName) 
	{
		extent = new ExtentReports(System.getProperty("user.dir") + "/reports/" + ReportName + ".html");
	}
//-----------------------------------------------------------------------------------------------------------
	@AfterTest
	public void endReport()
	{
		extent.flush();
		extent.close();
	}
//===========================================================================================================	
	public static void startEndTC(String keywordAction, String tcName) throws IOException
	{
		String tcNumber = "1";
		String testCaseName = "0";
		if (keywordAction.equals("StartOfTC") || keywordAction.equals("EndOfTest"))
		{
			if (tcNumb != 1) // if it is not the first test case we will end the previous one
			{
				extent.endTest(test); //close the test case in extent report
			}
			if ((keywordAction.equals("StartOfTC")))// if it is not the last test case we will start the next one
			{
				tcNumber = Integer.toString(tcNumb++); //Counting test case number
				testCaseName = tcNumber + "-  " + tcName; //store test case name with its number
				test = extent.startTest(testCaseName); //Test Case name in extent report
			}
		}
	}
}
