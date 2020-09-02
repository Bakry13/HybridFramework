package consumption;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Auth;
import utilities.Excel;
import utilities.ExcelInit;
import utilities.ExtentReport;
import utilities.Verification;

public class ConsumptionRedTest
{
	Response response;
	ConsumptionProducts redProduct;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int rowNumber = 10;
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
			redProduct= new ConsumptionProducts(response);
			System.out.println(ExcelInit.tcName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===============================CheckInternetProduct=================================================
    @Test(priority = 18)
    public void internetProduct()
    {
    	try {     		
	    	jsonString = Verification.success(response); //verify status code
	    	Verification.verifyFloat(redProduct.remainingInternet, 22080, "remaining Internet = 22080", "remaining Internet = " + Float.toString(redProduct.remainingInternet)); //verify remainig MB 			
			Verification.verifyFloat(redProduct.usedInternet, 200, "used Internet = 200", "used Internet = " + Float.toString(redProduct.usedInternet)); //verify used MB 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    }
//===============================CheckVoiceProduct=================================================
    @Test(priority = 19)
    public void voiceProduct()
    {
    	try {        		
	    	jsonString = Verification.success(response); //verify status code
	    	Verification.verifyFloat(redProduct.remainingMinutes, 4000, "remaining Minutes =4000", "remaining Minutes = " + Float.toString(redProduct.remainingMinutes)); //verify remainig  min			
			Verification.verifyFloat(redProduct.usedMinutes, 0, "used Minutes = 0", "used Minutes = " + Float.toString(redProduct.usedMinutes)); //verify used  min			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    } 
//===============================CheckSMSProduct=================================================
    @Test(priority = 20)
    public void smsProduct()
    {
    	try {        		
	    	jsonString = Verification.success(response); //verify status code
	    	Verification.verifyFloat(redProduct.remainingSMS, 1500, "remaining Sms = 1500", "remaining SMS = " + Float.toString(redProduct.remainingSMS)); //verify remainig sms min			
			Verification.verifyFloat(redProduct.usedSMS, 500, "used SMS = 500", "used SMS = " + Float.toString(redProduct.usedSMS)); //verify used sms min			

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
