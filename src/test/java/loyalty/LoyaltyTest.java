package loyalty;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Auth;
import utilities.Excel;
import utilities.ExcelInit;
import utilities.ExtentReport;
import utilities.Verification;

public class LoyaltyTest 
{
	Response response;
	public int statusCode = 0;
	public String jsonString = "0";
	public float points = 111;
	public String code = "0";
	public String reason = "0";
	public String message = "0";
	public static int rowNumber = 1;
	String tcPath = System.getProperty("user.dir") + "/inputs/" + "LoyaltyData.xlsx";
//====================================================================================================================	
	@BeforeMethod
	void StartTestCase()
	{	try {
			Excel.setPath(tcPath);
			rowNumber++;
			//--------------------------------Reading data from excel-------------------------------------------
			ExcelInit.tcName = Excel.read(rowNumber, ExcelInit.tcNameCol); //Reading test case name
			ExcelInit.msisdn = Excel.read(rowNumber, ExcelInit.msisdnCol); //Reading MSISDN
			ExcelInit.password = Excel.read(rowNumber, ExcelInit.passwordCol); //Reading Password
			//---------------------------------------Start test case--------------------------------------------
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(rowNumber-1)+"- "+ExcelInit.tcName);
			response = LoyaltyEndpoints.loyaltyRequest(ExcelInit.msisdn, ExcelInit.password); //Auth using a number has points
			System.out.println(ExcelInit.tcName);
		} catch (IOException e) {e.printStackTrace();}
	}
//==============================================Red number that has points============================================	
	@Test(priority = 1)
	public void RedPointsHasValue() throws IOException
	{	try {
			jsonString = Verification.success(response); //Verify status code
			points = JsonPath.from(jsonString).get("loyaltyAccount[0].loyaltyBalance.quantity.balance"); //Verify points
			Verification.verifyFloat(points, 20, "Points = 20", "Points = " + Float.toString(points)); //Verify status code			
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
	}
//=========================================Red number that does has 0 points============================================	
	@Test(priority = 2)
	public void RedPointsAreZero() throws IOException
	{	try {
			jsonString = Verification.success(response); //Verify status code
			points = JsonPath.from(jsonString).get("loyaltyAccount[0].loyaltyBalance.quantity.balance"); //Verify points
			Verification.verifyFloat(points, 0, "Points = 0", "Points = " + Float.toString(points)); //Verify status code		
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
	}
//=========================================Red number that is not eligible for loyalty============================================	
	@Test(priority = 3)
	public void RedNotEligibleForLoyalty() throws IOException
	{	try {
			jsonString = Verification.notFound(response); //Verify Not found 1001
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
	}
//==================================================NonRedNumber===================================================	
	@Test(priority = 4)
	public void NonRedNumber() throws IOException
	{	try {
			jsonString = Verification.notFound(response); //Verify Not found 1001
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
	}

//===================================================================================================================				
	@AfterMethod
	void EndReportTestCase()
	{
		ExtentReport.extent.endTest(ExtentReport.test); //close the test case in extent report
	}
	
}
