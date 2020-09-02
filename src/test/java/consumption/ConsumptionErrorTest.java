package consumption;

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

public class ConsumptionErrorTest {
  
	Response response;
	public int statusCode = 0;
	public String jsonString = "0";
	public String code = "0";
	public String reason = "0";
	public String message = "0";
	public static int rowNumber = 1;
	String tcPath = System.getProperty("user.dir") + "/inputs/" + "ConsumptionErrorHandling.xlsx";
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
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(rowNumber-1)+"- "+ExcelInit.tcName);
			System.out.println(ExcelInit.tcName);
		} catch (IOException e) {e.printStackTrace();}
	}	
//==================================Unauthorized - ConsumptionRequestWithoutmsisdn===============================================
	@Test(priority = 1)
	 public void RequestWithoutmsisdn() throws IOException
	 {	try {
		    response = ConsumptionErrorHandling.consumptionRequestWithoutmsisdn(ExcelInit.msisdn, ExcelInit.password);
		    jsonString = Verification.unauthorized(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}
	 }
//==================================Unauthorized - ConsumptionRequestWithoutToken==============================================================
	@Test(priority = 2)
	public void RequestWithoutToken() throws IOException
	{	try {
		    response = ConsumptionErrorHandling.consumptionRequestWithoutToken(ExcelInit.msisdn, ExcelInit.password);
		    jsonString = Verification.unauthorized(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode);
		  e.printStackTrace();}
	}
//=====================================Not Found - ConsumptionRequestWrongURL==============================================================
	@Test(priority = 3)
	public void RequestWrongURL() throws IOException
	{	try {
		    response = ConsumptionErrorHandling.consumptionRequestWrongURL(ExcelInit.msisdn, ExcelInit.password); 
		    jsonString = Verification.notFoundURL(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}
	}
//===================================405 Method Not Allowed - ConsumptionPostRequest==============================================================
	@Test(priority = 4)
	public void PostRequest() throws IOException
	{	try {
		    response = ConsumptionErrorHandling.consumptionpostRequest(ExcelInit.msisdn, ExcelInit.password); 
		    jsonString = Verification.methodNotAllowed(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}	
	}
//=====================================400 Bad request - ConsumptionRequestWrongURL==============================================================
	@Test(priority = 5)
	public void badRequest() throws IOException
	{	try {
		    response = ConsumptionErrorHandling.consumptionWrongQparam(ExcelInit.msisdn, ExcelInit.password); 
		    jsonString = Verification.badRequest(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}
	}
//===================================500 Internal Server Error - Missing query param Request==============================================================
	@Test(priority = 6)
	public void missningQRequest() throws IOException
	{	try {
		    response = ConsumptionErrorHandling.consumptionMissingQParamtRequest(ExcelInit.msisdn, ExcelInit.password); 
		    jsonString = Verification.badRequest(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}
	}
//===================================415 Unsupported Media type - Request without Content-Type==============================================================
	@Test(priority = 7)
	public void RequestWithoutContentType() throws IOException
	{	try {
		    response = ConsumptionErrorHandling.consumptionWithoutContentType(ExcelInit.msisdn, ExcelInit.password);
		    jsonString = Verification.unsupportedMediaType(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); 
		  e.printStackTrace();}
	}
//===================================403 Forbidden - Request without different msisdn==============================================================
	@Test(priority = 8)
	public void RequestWithDifferentmsisdn() throws IOException
	{	try {
		    response = ConsumptionErrorHandling.consumptionWithDifferentmsisdn(ExcelInit.msisdn, ExcelInit.password);
		    jsonString = Verification.forbidden(response);
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


 
