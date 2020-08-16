package Consumption;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.Auth;
import Utilities.Excel;
import Utilities.ExcelInit;
import Utilities.ExtentReport;
import Utilities.Verification;
import io.restassured.response.Response;

public class ConsumptionMassTest 
{
	Response response;
	ConsumptionProducts RedProduct;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 13;
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
//===============================CheckVoiceProduct=================================================
    @Test(priority = 21)
    public void harakatVoiceProduct()
    {
    	try {        		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(RedProduct.RemainingMinutes, 499, "Remaining Minutes = 499", "Remaining Minutes = " + Float.toString(RedProduct.RemainingMinutes)); //Verify remainig  min			
			Verification.VerifyFloat(RedProduct.UsedMinutes, 1, "Used Minutes = 1", "Used Minutes = " + Float.toString(RedProduct.UsedMinutes)); //Verify used  min			

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
