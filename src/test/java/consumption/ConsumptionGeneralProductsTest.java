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

public class ConsumptionGeneralProductsTest
{
	Response response;
	ConsumptionProducts generalProduct;
	public int statusCode = 0;
	public String jsonString = "0";
	public static int rowNumber = 1;
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
			generalProduct= new ConsumptionProducts(response);
			System.out.println(ExcelInit.tcName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===================================CheckRoamingProducts========================================
    @Test(priority = 9)
    public void roamingProduct()
    {
    	try {	
    	    jsonString = Verification.success(response); //verify status code
		    Verification.verifyFloat(generalProduct.remainingRoamingOutgoing, 10, "remaining Roaming Outgoing = 10", "remaining Roaming Outgoing = " + Float.toString(generalProduct.remainingRoamingOutgoing)); //verify remainig mo min			
		    Verification.verifyFloat(generalProduct.usedRoamingOutgoing, 0, "used Roaming Outgoing = 0", "used Roaming Outgoing = " + Float.toString(generalProduct.remainingRoamingOutgoing)); //verify used mo min
	     	Verification.verifyFloat(generalProduct.remainingRoamingIncoming, 30, "remaining Roaming Incoming = 60", "remaining Roaming Incoming = " + Float.toString(generalProduct.remainingRoamingIncoming)); //verify remainig mt min			
	    	Verification.verifyFloat(generalProduct.usedRoamingIncoming, 30, "used Roaming Incoming = 30", "used Roaming Incoming = " + Float.toString(generalProduct.usedRoamingIncoming)); //verify used mt min			
		    Verification.verifyFloat(generalProduct.remainingRoamingInternet, 100, "remaining Roaming Internet = 100", "remaining Roaming Internet = " + Float.toString(generalProduct.remainingRoamingInternet)); //verify remainig data 			
		    Verification.verifyFloat(generalProduct.usedRoamingInternet, 400, "used Roaming Internet = 400", "used Roaming Internet = " + Float.toString(generalProduct.usedRoamingInternet)); //verify used data 			
		    Verification.verifyFloat(generalProduct.remainingRoamingSMS, 0, "remaining Roaming SMS = 0", "remaining Roaming SMS = " + Float.toString(generalProduct.remainingRoamingSMS)); //verify remainig sms 			
		    Verification.verifyFloat(generalProduct.usedRoamingSMS, 60, "used Roaming SMS = 60", "used Roaming SMS = " + Float.toString(generalProduct.usedRoamingSMS)); //verify used sms 
		
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    }
//===================================CheckFamilyProducts===================================           
    @Test(priority = 10)
    public void familyProduct()
    {
    	try {
    		jsonString = Verification.success(response); //verify status code
			Verification.verifyFloat(generalProduct.remainingFamilyMinutes, 100, "remaining Family Minutes = 100", "remaining Family Minutes = " + Float.toString(generalProduct.remainingFamilyMinutes)); //verify remainig  min			
			Verification.verifyFloat(generalProduct.usedFamilyMinutes, 0, "used Family Minutes= 0", "used Family Minutes = " + Float.toString(generalProduct.usedFamilyMinutes)); //verify used  min
			Verification.verifyFloat(generalProduct.remainingFamilyInternet, 768, "remaining Family Internet = 768", "remaining Family Internet = " + Float.toString(generalProduct.remainingFamilyInternet)); //verify remainig data 			
			Verification.verifyFloat(generalProduct.usedFamilyInternet, 256, "used Family Internet = 256", "used Family Internet = " + Float.toString(generalProduct.usedFamilyInternet)); //verify used data 			
			Verification.verifyFloat(generalProduct.remainingFamilySMS, 12, "remaining Family SMS = 12", "remaining Family SMS = " + Float.toString(generalProduct.remainingFamilySMS)); //verify remainig sms 			
			Verification.verifyFloat(generalProduct.usedFamilySMS, 38, "used Family SMS = 38", "used Family SMS = " + Float.toString(generalProduct.usedFamilySMS)); //verify used sms min
    	
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    }  
//===================================CheckDataProducts===================================           
    @Test(priority = 11)
    public void dataProduct()
    {
    	try {
    	    jsonString = Verification.success(response); //verify status code
    	    Verification.verifyFloat(generalProduct.remainingInternet, 1550, "remaining Internet = 1550", "remaining Internet = " + Float.toString(generalProduct.remainingInternet)); //verify remainig data 			
		    Verification.verifyFloat(generalProduct.usedInternet, 100, "used Internet = 100", "used Internet = " + Float.toString(generalProduct.usedInternet)); //verify used data 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    }
//===================================CheckSuperpassproducts==============================================           
    @Test(priority = 12)
    public void supperpassProduct()
    {
    	try {
    	    jsonString = Verification.success(response); //verify status code
    	    Verification.verifyFloat(generalProduct.remainingSuperPass, 1200, "remaining SuperPass =1200", "remaining SuperPass = " + Float.toString(generalProduct.remainingSuperPass)); //verify remainig data 			
		    Verification.verifyFloat(generalProduct.usedSuperPass, 300, "used SuperPass = 300", "used SuperPass = " + Float.toString(generalProduct.usedSuperPass)); //verify used data 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    } 
//===================================CheckAccountHaveNoRoamingProducts========================================
    @Test(priority = 13)
    public void noRoamingProduct()
    {
    	try {	
    	    jsonString = Verification.success(response); //verify status code
		    Verification.verifyFloat(generalProduct.remainingRoamingOutgoing, 123456, "Account Has No Roaming Bundle", "remaining Roaming Outgoing = " + Float.toString(generalProduct.remainingRoamingOutgoing)); //verify remainig mo min			
		    Verification.verifyFloat(generalProduct.usedRoamingOutgoing, 123456, "Account Has No Roaming Bundle", "used Roaming Outgoing = " + Float.toString(generalProduct.remainingRoamingOutgoing)); //verify used mo min
	     	Verification.verifyFloat(generalProduct.remainingRoamingIncoming, 123456, "Account Has No Roaming Bundle", "remaining Roaming Incoming = " + Float.toString(generalProduct.remainingRoamingIncoming)); //verify remainig mt min			
	    	Verification.verifyFloat(generalProduct.usedRoamingIncoming, 123456, "Account Has No Roaming Bundle", "used Roaming Incoming = " + Float.toString(generalProduct.usedRoamingIncoming)); //verify used mt min			
		    Verification.verifyFloat(generalProduct.remainingRoamingInternet, 123456, "Account Has No Roaming Bundle", "remaining Roaming Internet = " + Float.toString(generalProduct.remainingRoamingInternet)); //verify remainig data 			
		    Verification.verifyFloat(generalProduct.usedRoamingInternet, 123456, "Account Has No Roaming Bundle", "used Roaming Internet = " + Float.toString(generalProduct.usedRoamingInternet)); //verify used data 			
		    Verification.verifyFloat(generalProduct.remainingRoamingSMS, 123456, "Account Has No Roaming Bundle", "remaining Roaming SMS = " + Float.toString(generalProduct.remainingRoamingSMS)); //verify remainig sms 			
		    Verification.verifyFloat(generalProduct.usedRoamingSMS, 123456, "Account Has No Roaming Bundle", "used Roaming SMS = " + Float.toString(generalProduct.usedRoamingSMS)); //verify used sms 
		
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    }
//===================================CheckAccountHaveNoFamilyProducts===================================           
    @Test(priority = 14)
    public void noFamilyProduct()
    {
    	try {
    		jsonString = Verification.success(response); //verify status code
			Verification.verifyFloat(generalProduct.remainingFamilyMinutes, 123456, "Account not in family member", "remaining Family Minutes = " + Float.toString(generalProduct.remainingFamilyMinutes)); //verify remainig  min			
			Verification.verifyFloat(generalProduct.usedFamilyMinutes, 123456, "Account not in family member", "used Family Minutes = " + Float.toString(generalProduct.usedFamilyMinutes)); //verify used  min
			Verification.verifyFloat(generalProduct.remainingFamilyInternet, 123456, "Account not in family member", "remaining Family Internet = " + Float.toString(generalProduct.remainingFamilyInternet)); //verify remainig data 			
			Verification.verifyFloat(generalProduct.usedFamilyInternet, 123456, "Account not in family member", "used Family Internet = " + Float.toString(generalProduct.usedFamilyInternet)); //verify used data 			
			Verification.verifyFloat(generalProduct.remainingFamilySMS, 123456, "Account not in family member", "remaining Family SMS = " + Float.toString(generalProduct.remainingFamilySMS)); //verify remainig sms 			
			Verification.verifyFloat(generalProduct.usedFamilySMS, 123456, "Account not in family member", "used Family SMS = " + Float.toString(generalProduct.usedFamilySMS)); //verify used sms min
    	
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    }  
//===================================CheckHaveNoDataProducts===================================           
    @Test(priority = 15)
    public void NoDataProduct()
    {
    	try {
    	    jsonString = Verification.success(response); //verify status code
    	    Verification.verifyFloat(generalProduct.remainingInternet, 123456, "Account not subscribed in Mi bundle", "remaining Internet = " + Float.toString(generalProduct.remainingInternet)); //verify remainig data 			
		    Verification.verifyFloat(generalProduct.usedInternet, 123456, "Account not subscribed in Mi bundle", "used Internet = " + Float.toString(generalProduct.usedInternet)); //verify used data 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    }        
//===============================================================================================
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
