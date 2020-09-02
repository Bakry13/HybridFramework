package loyalty;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import loyalty.LoyaltyErrorHandling;
import utilities.Auth;
import utilities.Excel;
import utilities.ExcelInit;
import utilities.ExtentReport;
import utilities.Verification;

public class LoyaltyErrorTest {
	Response response;
	public int StatusCode = 0;
	public String jsonString = "0";
	public String Code = "0";
	public String reason = "0";
	public String message = "0";
	public static int rowNumber = 1;
	String tcPath = System.getProperty("user.dir") + "/inputs/" + "LoyaltyErrorHandling.xlsx";
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
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(rowNumber+3)+"- "+ExcelInit.tcName);
			System.out.println(ExcelInit.tcName);
		} catch (IOException e) {e.printStackTrace();}
	}	
//==================================401 Unauthorized - loyaltyRequestWithoutmsisdn===============================================
	 @Test(priority = 5)
	 public void RequestWithoutmsisdn() throws IOException
	 {	try {
		    response = LoyaltyErrorHandling.loyaltyWithoutmsisdn(ExcelInit.msisdn, ExcelInit.password);
		    Verification.unauthorized(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}
	 }
//==================================401 Unauthorized - loyaltyRequestWithoutToken==============================================================
	@Test(priority = 6)
	public void RequestWithoutToken() throws IOException
	{	try {
		    response = LoyaltyErrorHandling.loyaltyWithoutToken(ExcelInit.msisdn, ExcelInit.password);
		    jsonString = Verification.unauthorized(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode);
		  e.printStackTrace();}
	}
//=====================================404 Not Found - loyaltyRequestWrongURL==============================================================
	@Test(priority = 7)
	public void RequestWrongURL() throws IOException
	{	try {
		    response = LoyaltyErrorHandling.loyaltyWrongURL(ExcelInit.msisdn, ExcelInit.password); 
		    jsonString = Verification.notFoundURL(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}
	}
//===================================405 Method Not Allowed - loyaltyPostRequest==============================================================
	@Test(priority = 8)
	public void PostRequest() throws IOException
	{	try {
		    response = LoyaltyErrorHandling.loyaltyPostRequest(ExcelInit.msisdn, ExcelInit.password); 
		    jsonString = Verification.methodNotAllowed(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}	
	}
//===================================415 Unsupported Media type - Request without Content-Type==============================================================
	@Test(priority = 9)
	public void RequestWithoutContentType() throws IOException
	{	try {
		    response = LoyaltyErrorHandling.loyaltyWithoutContentType(ExcelInit.msisdn, ExcelInit.password);
		    jsonString = Verification.unsupportedMediaType(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}
	}
//===================================================================================================================				
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
