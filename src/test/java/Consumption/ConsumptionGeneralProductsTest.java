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

public class ConsumptionGeneralProductsTest
{
	Response response;
	ConsumptionProducts GeneralProduct;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 1;
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
			GeneralProduct= new ConsumptionProducts(response);
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===================================CheckRoamingProducts========================================
    @Test(priority = 9)
    public void roamingProduct()
    {
    	try {	
    	    jsonString = Verification.Success(response); //Verify status code
		    Verification.VerifyFloat(GeneralProduct.RemainingRoamingOutgoing, 10, "Remaining Roaming Outgoing = 10", "Remaining Roaming Outgoing = " + Float.toString(GeneralProduct.RemainingRoamingOutgoing)); //Verify remainig mo min			
		    Verification.VerifyFloat(GeneralProduct.UsedRoamingOutgoing, 0, "Used Roaming Outgoing = 0", "Used Roaming Outgoing = " + Float.toString(GeneralProduct.RemainingRoamingOutgoing)); //Verify used mo min
	     	Verification.VerifyFloat(GeneralProduct.RemainingRoamingIncoming, 30, "Remaining Roaming Incoming = 60", "Remaining Roaming Incoming = " + Float.toString(GeneralProduct.RemainingRoamingIncoming)); //Verify remainig mt min			
	    	Verification.VerifyFloat(GeneralProduct.UsedRoamingIncoming, 30, "Used Roaming Incoming = 30", "Used Roaming Incoming = " + Float.toString(GeneralProduct.UsedRoamingIncoming)); //Verify used mt min			
		    Verification.VerifyFloat(GeneralProduct.RemainingRoamingInternet, 100, "Remaining Roaming Internet = 100", "Remaining Roaming Internet = " + Float.toString(GeneralProduct.RemainingRoamingInternet)); //Verify remainig data 			
		    Verification.VerifyFloat(GeneralProduct.UsedRoamingInternet, 400, "Used Roaming Internet = 400", "Used Roaming Internet = " + Float.toString(GeneralProduct.UsedRoamingInternet)); //Verify used data 			
		    Verification.VerifyFloat(GeneralProduct.RemainingRoamingSMS, 0, "Remaining Roaming SMS = 0", "Remaining Roaming SMS = " + Float.toString(GeneralProduct.RemainingRoamingSMS)); //Verify remainig sms 			
		    Verification.VerifyFloat(GeneralProduct.UsedRoamingSMS, 60, "Used Roaming SMS = 60", "Used Roaming SMS = " + Float.toString(GeneralProduct.UsedRoamingSMS)); //Verify used sms 
		
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================CheckFamilyProducts===================================           
    @Test(priority = 10)
    public void familyProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
			Verification.VerifyFloat(GeneralProduct.RemainingFamilyMinutes, 100, "Remaining Family Minutes = 100", "Remaining Family Minutes = " + Float.toString(GeneralProduct.RemainingFamilyMinutes)); //Verify remainig  min			
			Verification.VerifyFloat(GeneralProduct.UsedFamilyMinutes, 0, "Used Family Minutes= 0", "Used Family Minutes = " + Float.toString(GeneralProduct.UsedFamilyMinutes)); //Verify used  min
			Verification.VerifyFloat(GeneralProduct.RemainingFamilyInternet, 768, "Remaining Family Internet = 768", "Remaining Family Internet = " + Float.toString(GeneralProduct.RemainingFamilyInternet)); //Verify remainig data 			
			Verification.VerifyFloat(GeneralProduct.UsedFamilyInternet, 256, "Used Family Internet = 256", "Used Family Internet = " + Float.toString(GeneralProduct.UsedFamilyInternet)); //Verify used data 			
			Verification.VerifyFloat(GeneralProduct.RemainingFamilySMS, 12, "Remaining Family SMS = 12", "Remaining Family SMS = " + Float.toString(GeneralProduct.RemainingFamilySMS)); //Verify remainig sms 			
			Verification.VerifyFloat(GeneralProduct.UsedFamilySMS, 38, "Used Family SMS = 38", "Used Family SMS = " + Float.toString(GeneralProduct.UsedFamilySMS)); //Verify used sms min
    	
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }  
//===================================CheckDataProducts===================================           
    @Test(priority = 11)
    public void dataProduct()
    {
    	try {
    	    jsonString = Verification.Success(response); //Verify status code
    	    Verification.VerifyFloat(GeneralProduct.RemainingInternet, 1550, "Remaining Internet = 1550", "Remaining Internet = " + Float.toString(GeneralProduct.RemainingInternet)); //Verify remainig data 			
		    Verification.VerifyFloat(GeneralProduct.UsedInternet, 100, "Used Internet = 100", "Used Internet = " + Float.toString(GeneralProduct.UsedInternet)); //Verify used data 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================CheckSuperpassproducts==============================================           
    @Test(priority = 12)
    public void supperpassProduct()
    {
    	try {
    	    jsonString = Verification.Success(response); //Verify status code
    	    Verification.VerifyFloat(GeneralProduct.RemainingSuperPass, 1200, "Remaining SuperPass =1200", "Remaining SuperPass = " + Float.toString(GeneralProduct.RemainingSuperPass)); //Verify remainig data 			
		    Verification.VerifyFloat(GeneralProduct.UsedSuperPass, 300, "Used SuperPass = 300", "Used SuperPass = " + Float.toString(GeneralProduct.UsedSuperPass)); //Verify used data 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    } 
//===================================CheckAccountHaveNoRoamingProducts========================================
    @Test(priority = 13)
    public void noRoamingProduct()
    {
    	try {	
    	    jsonString = Verification.Success(response); //Verify status code
		    Verification.VerifyFloat(GeneralProduct.RemainingRoamingOutgoing, 123456, "Account Has No Roaming Bundle", "Remaining Roaming Outgoing = " + Float.toString(GeneralProduct.RemainingRoamingOutgoing)); //Verify remainig mo min			
		    Verification.VerifyFloat(GeneralProduct.UsedRoamingOutgoing, 123456, "Account Has No Roaming Bundle", "Used Roaming Outgoing = " + Float.toString(GeneralProduct.RemainingRoamingOutgoing)); //Verify used mo min
	     	Verification.VerifyFloat(GeneralProduct.RemainingRoamingIncoming, 123456, "Account Has No Roaming Bundle", "Remaining Roaming Incoming = " + Float.toString(GeneralProduct.RemainingRoamingIncoming)); //Verify remainig mt min			
	    	Verification.VerifyFloat(GeneralProduct.UsedRoamingIncoming, 123456, "Account Has No Roaming Bundle", "Used Roaming Incoming = " + Float.toString(GeneralProduct.UsedRoamingIncoming)); //Verify used mt min			
		    Verification.VerifyFloat(GeneralProduct.RemainingRoamingInternet, 123456, "Account Has No Roaming Bundle", "Remaining Roaming Internet = " + Float.toString(GeneralProduct.RemainingRoamingInternet)); //Verify remainig data 			
		    Verification.VerifyFloat(GeneralProduct.UsedRoamingInternet, 123456, "Account Has No Roaming Bundle", "Used Roaming Internet = " + Float.toString(GeneralProduct.UsedRoamingInternet)); //Verify used data 			
		    Verification.VerifyFloat(GeneralProduct.RemainingRoamingSMS, 123456, "Account Has No Roaming Bundle", "Remaining Roaming SMS = " + Float.toString(GeneralProduct.RemainingRoamingSMS)); //Verify remainig sms 			
		    Verification.VerifyFloat(GeneralProduct.UsedRoamingSMS, 123456, "Account Has No Roaming Bundle", "Used Roaming SMS = " + Float.toString(GeneralProduct.UsedRoamingSMS)); //Verify used sms 
		
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================CheckAccountHaveNoFamilyProducts===================================           
    @Test(priority = 14)
    public void noFamilyProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
			Verification.VerifyFloat(GeneralProduct.RemainingFamilyMinutes, 123456, "Account not in family member", "Remaining Family Minutes = " + Float.toString(GeneralProduct.RemainingFamilyMinutes)); //Verify remainig  min			
			Verification.VerifyFloat(GeneralProduct.UsedFamilyMinutes, 123456, "Account not in family member", "Used Family Minutes = " + Float.toString(GeneralProduct.UsedFamilyMinutes)); //Verify used  min
			Verification.VerifyFloat(GeneralProduct.RemainingFamilyInternet, 123456, "Account not in family member", "Remaining Family Internet = " + Float.toString(GeneralProduct.RemainingFamilyInternet)); //Verify remainig data 			
			Verification.VerifyFloat(GeneralProduct.UsedFamilyInternet, 123456, "Account not in family member", "Used Family Internet = " + Float.toString(GeneralProduct.UsedFamilyInternet)); //Verify used data 			
			Verification.VerifyFloat(GeneralProduct.RemainingFamilySMS, 123456, "Account not in family member", "Remaining Family SMS = " + Float.toString(GeneralProduct.RemainingFamilySMS)); //Verify remainig sms 			
			Verification.VerifyFloat(GeneralProduct.UsedFamilySMS, 123456, "Account not in family member", "Used Family SMS = " + Float.toString(GeneralProduct.UsedFamilySMS)); //Verify used sms min
    	
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }  
//===================================CheckHaveNoDataProducts===================================           
    @Test(priority = 15)
    public void NoDataProduct()
    {
    	try {
    	    jsonString = Verification.Success(response); //Verify status code
    	    Verification.VerifyFloat(GeneralProduct.RemainingInternet, 123456, "Account not subscribed in Mi bundle", "Remaining Internet = " + Float.toString(GeneralProduct.RemainingInternet)); //Verify remainig data 			
		    Verification.VerifyFloat(GeneralProduct.UsedInternet, 123456, "Account not subscribed in Mi bundle", "Used Internet = " + Float.toString(GeneralProduct.UsedInternet)); //Verify used data 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
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
