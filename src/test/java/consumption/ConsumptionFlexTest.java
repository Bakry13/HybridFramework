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

public class ConsumptionFlexTest
{
	Response response;
	ConsumptionProducts flexProduct;
	public int statusCode = 0;
	public String jsonString = "0";
	public static int rowNumber = 8;
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
			flexProduct= new ConsumptionProducts(response);
			System.out.println(ExcelInit.tcName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===============================CheckflexProduct=================================================
    @Test(priority = 16)
    public void flexProduct()
    {
    	try {        		
	    	jsonString = Verification.success(response); //verify status code
	    	Verification.verifyFloat(flexProduct.remainingFlexes, 550, "remaining Flexes = 550", "remaining Flexes = " + Float.toString(flexProduct.remainingFlexes)); //verify remainig flexs 			
			Verification.verifyFloat(flexProduct.usedFlexes, 50, "used flexes = 50", "used flexes = " + Float.toString(flexProduct.usedFlexes)); //verify used flexs 			

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.statusCode); e.printStackTrace();}
    }
 //===============================CheckexpiredflexProduct=================================================
    @Test(priority = 17)
    public void flexsExpired()
    {
    	try {        		
	    	jsonString = Verification.success(response); //verify status code
	    	Verification.verifyFloat(flexProduct.remainingFlexes, 123456, "Flex Bundle expired", "remaining Flexes = " + Float.toString(flexProduct.remainingFlexes)); //verify remainig flexs 			
			Verification.verifyFloat(flexProduct.usedFlexes, 123456, "Flex Bundle expired ", "used flexes = " + Float.toString(flexProduct.usedFlexes)); //verify used flexs 			

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
