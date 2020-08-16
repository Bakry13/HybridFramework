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

public class ConsumptionFlexTest
{
	Response response;
	ConsumptionProducts Flexproduct;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 8;
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
			Flexproduct= new ConsumptionProducts(response);
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===============================CheckFlexProduct=================================================
    @Test(priority = 16)
    public void flexProduct()
    {
    	try {        		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(Flexproduct.RemainingFlexes, 550, "Remaining Flexes = 550", "Remaining Flexes = " + Float.toString(Flexproduct.RemainingFlexes)); //Verify remainig flexs 			
			Verification.VerifyFloat(Flexproduct.UsedFlexes, 50, "Used flexes = 50", "Used flexes = " + Float.toString(Flexproduct.UsedFlexes)); //Verify used flexs 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
 //===============================CheckexpiredFlexProduct=================================================
    @Test(priority = 17)
    public void flexsExpired()
    {
    	try {        		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(Flexproduct.RemainingFlexes, 123456, "Flex Bundle expired", "Remaining Flexes = " + Float.toString(Flexproduct.RemainingFlexes)); //Verify remainig flexs 			
			Verification.VerifyFloat(Flexproduct.UsedFlexes, 123456, "Flex Bundle expired ", "Used flexes = " + Float.toString(Flexproduct.UsedFlexes)); //Verify used flexs 			

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
