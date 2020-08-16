package Consumption;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.Auth;
import Utilities.Excel;
import Utilities.ExcelInit;
import Utilities.ExtentReport;
import Utilities.Verification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ConsumptionRedTest
{
	Response response;
	ConsumptionProducts RedProduct;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 10;
	String TCPath = System.getProperty("user.dir") + "/Inputs/" + "ConsumptionData.xlsx";
	
//====================================================================================================================	
	@BeforeMethod
	void StartTestCase()
	{	try {
			Excel.setPath(TCPath);
			RowNumber++;
			//--------------------------------Reading data from excel-------------------------------------------
			ExcelInit.TCName = Excel.read(RowNumber, ExcelInit.TCNameCol); //Reading test case name
			ExcelInit.MSISDN = Excel.read(RowNumber, ExcelInit.MSISDNCol); //Reading MSISDN
			ExcelInit.Password = Excel.read(RowNumber, ExcelInit.PasswordCol); //Reading Password
			//---------------------------------------Start test case--------------------------------------------
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(RowNumber+7)+"- "+ExcelInit.TCName);
			response = ConsumptionEndPoints.consumptionRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
			RedProduct= new ConsumptionProducts(response);
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===============================CheckInternetProduct=================================================
    @Test(priority = 18)
    public void internetProduct()
    {
    	try {     		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(RedProduct.RemainingInternet, 22080, "Remaining Internet = 22080", "Remaining Internet = " + Float.toString(RedProduct.RemainingInternet)); //Verify remainig MB 			
			Verification.VerifyFloat(RedProduct.UsedInternet, 200, "Used Internet = 200", "Used Internet = " + Float.toString(RedProduct.UsedInternet)); //Verify used MB 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===============================CheckVoiceProduct=================================================
    @Test(priority = 19)
    public void voiceProduct()
    {
    	try {        		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(RedProduct.RemainingMinutes, 4000, "Remaining Minutes =4000", "Remaining Minutes = " + Float.toString(RedProduct.RemainingMinutes)); //Verify remainig  min			
			Verification.VerifyFloat(RedProduct.UsedMinutes, 0, "Used Minutes = 0", "Used Minutes = " + Float.toString(RedProduct.UsedMinutes)); //Verify used  min			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    } 
//===============================CheckSMSProduct=================================================
    @Test(priority = 20)
    public void smsProduct()
    {
    	try {        		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(RedProduct.RemainingSMS, 1500, "Remaining Sms = 1500", "Remaining SMS = " + Float.toString(RedProduct.RemainingSMS)); //Verify remainig sms min			
			Verification.VerifyFloat(RedProduct.UsedSMS, 500, "Used SMS = 500", "Used SMS = " + Float.toString(RedProduct.UsedSMS)); //Verify used sms min			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//================================================================================================
  @AfterMethod
	void EndReportTestCase()
	{
	  try {
			ExtentReport.extent.endTest(ExtentReport.test); //close the test case in extent report
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
