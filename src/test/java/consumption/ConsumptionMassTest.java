package consumption;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;
import utilities.Auth;
import utilities.Excel;
import utilities.ExcelInit;
import utilities.ExtentReport;
import utilities.Verification;

public class ConsumptionMassTest 
{
	Response response;
	ConsumptionProducts massProduct;
	public int statusCode = 0;
	public String jsonString = "0";
	public static int rowNumber = 13;
	String tcPath = System.getProperty("user.dir") + "/inputs/" + "ConsumptionData.xlsx";
	
//====================================================================================================================	
	@BeforeMethod
	void StartTestCase()
	{	try {
			Excel.setPath(tcPath);
			rowNumber++;
			//--------------------------------Reading data from excel-------------------------------------------
			ExcelInit.tcName = Excel.read(rowNumber, ExcelInit.tcNameCol); //Reading test case name
			ExcelInit.msisdn = Excel.read(rowNumber, ExcelInit.msisdnCol); //Reading msisdn
			ExcelInit.password = Excel.read(rowNumber, ExcelInit.passwordCol); //Reading password
			//---------------------------------------Start test case--------------------------------------------
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(rowNumber+7)+"- "+ExcelInit.tcName);
			response = ConsumptionEndPoints.consumptionRequest(ExcelInit.msisdn, ExcelInit.password); //Auth using a number has points
			massProduct= new ConsumptionProducts(response);
			System.out.println(ExcelInit.tcName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===============================CheckVoiceProduct=================================================
    @Test(priority = 21)
    public void harakatVoiceProduct()
    {
    	try {        		
	    	Verification.verifyFloat(massProduct.remainingMinutes, 499, "Remaining Minutes = 499", "Remaining Minutes = " + Float.toString(massProduct.remainingMinutes)); //verify remainig  min			
			Verification.verifyFloat(massProduct.usedMinutes, 1, "used Minutes = 1", "used Minutes = " + Float.toString(massProduct.usedMinutes)); //verify used  min			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
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
