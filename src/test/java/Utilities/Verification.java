package Utilities;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import com.relevantcodes.extentreports.LogStatus;

import Pages.General;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Verification 
{
	public static boolean FailureFlag = false;
	public static int StatusCode = 0;
	public static String jsonString = "0";
	public static String Code = "0";
	public static String reason = "0";
	public static String message = "0";
	//--------------------------------Verify if a certain element exists----------------------------------------
	public static boolean VerifyElement(WebElement Element, String SuccessText, String FailureText) throws IOException
	{
		try
		{
			assert Element.isDisplayed(); //find if the element is displayed
			ExtentReport.test.log(LogStatus.PASS, SuccessText); //Record expected result
			FailureFlag = false;
		} catch (Exception e) 
		{
			ExtentReport.test.log(LogStatus.FAIL, FailureText); //Write failure statement
			FailureFlag = true;
			e.printStackTrace();
		}
		return FailureFlag;
	}
	//--------------------------------Verify if a certain Text exists----------------------------------------
	public static boolean VerifyText(String ElementText, String SuccessText, String FailureText) throws IOException
	{
		try
		{
			assert General.ScrollAndFind(ElementText).isDisplayed(); //find if the element is displayed
			
			ExtentReport.test.log(LogStatus.PASS, SuccessText); //Record expected result
			FailureFlag = false;
		} catch (Exception e) 
		{
			ExtentReport.test.log(LogStatus.FAIL, FailureText); //Write failure statement
			FailureFlag = true;
			e.printStackTrace();
		}
		return FailureFlag;
	}
	//-------------------------------Verify the correctness of text of a certain element------------------------------
	public static boolean VerifyElementText(WebElement Element, String SuccessText, String ElementText, String FailureText) throws IOException
	{		
		if(Element.getText().equals(ElementText))
		{  
			ExtentReport.test.log(LogStatus.PASS, SuccessText); //Record expected result
			FailureFlag = false;
		}
		else
		{  
			ExtentReport.test.log(LogStatus.FAIL, FailureText); //Write failure statement
			FailureFlag = true;  
		}
		return FailureFlag;
	}
	//-------------------------------Verify the correctness of String value------------------------------
	public static boolean VerifyString(String Value, String ExpectedValue, String SuccessText, String FailureText) throws IOException
	{		
		if(Value.equals(ExpectedValue))
		{ 
			ExtentReport.test.log(LogStatus.PASS, SuccessText); //Record expected result
			FailureFlag = false;
		}
		else
		{  
			ExtentReport.test.log(LogStatus.FAIL, FailureText); //Write failure statement
			FailureFlag = true;  
		}
		return FailureFlag;
	}
	//-------------------------------Verify the correctness of integer value------------------------------
	public static boolean VerifyInt(int Value, int ExpectedValue, String SuccessText, String FailureText) throws IOException
	{		
		if(Value == ExpectedValue)
		{  
			ExtentReport.test.log(LogStatus.PASS, SuccessText); //Record expected result
			FailureFlag = false;
		}
		else
		{  
			ExtentReport.test.log(LogStatus.FAIL, FailureText); //Write failure statement
			FailureFlag = true;  
		}
		return FailureFlag;
	}
	//-------------------------------Verify the correctness of float value------------------------------
	public static boolean VerifyFloat(float Value, float ExpectedValue, String SuccessText, String FailureText) throws IOException
	{		
		if(Value == ExpectedValue)
		{  
			ExtentReport.test.log(LogStatus.PASS, SuccessText); //Record expected result
			FailureFlag = false;
		}
		else
		{  
			ExtentReport.test.log(LogStatus.FAIL, FailureText); //Write failure statement
			FailureFlag = true;  
		}
		return FailureFlag;
	}
	//-------------------------------Verify the correctness of boolean value------------------------------
	public static boolean VerifyBoolean(boolean Value, boolean ExpectedValue, String SuccessText, String FailureText) throws IOException
	{		
		if(Value == ExpectedValue)
		{  
			ExtentReport.test.log(LogStatus.PASS, SuccessText); //Record expected result
			FailureFlag = false;
		}
		else
		{  
			ExtentReport.test.log(LogStatus.FAIL, FailureText); //Write failure statement
			FailureFlag = true;  
		}
		return FailureFlag;
	}
//===========================================API Verification==========================================
	//-------------------------------Verify Success Code 200------------------------------
	public static String Success(Response response) throws IOException
	{		
		StatusCode = response.getStatusCode();
		jsonString = response.asString();
		VerifyInt(StatusCode, 200, "Status code = 200", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		return jsonString;
	}
	//-------------------------------Verify Not found 404 - 1001---loyalty---------------------------
	public static String NotFound(Response response) throws IOException
	{	
		StatusCode = response.getStatusCode();
		jsonString = response.asString();
		VerifyInt(StatusCode, 404, "Status code = 404", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		Code = JsonPath.from(jsonString).get("code");
		VerifyString(Code, "1001", "Code = 1001", "Code = " + Code); //Verify body code
		reason = JsonPath.from(jsonString).get("reason");
		VerifyString(reason, "No Data Found", "reasone is: No Data Found", "reason is: " + reason); //Verify body code
		return jsonString;
	}
	//-------------------------------Verify Not found 404 - 1006------------------------------
	public static String NotFoundURL(Response response) throws IOException
	{	
		StatusCode = response.getStatusCode();
		jsonString = response.asString();
		VerifyInt(StatusCode, 404, "Status code = 404", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		Code = JsonPath.from(jsonString).get("code");
		VerifyString(Code, "1006", "Code = 1006", "Code = " + Code); //Verify body code
		reason = JsonPath.from(jsonString).get("reason");
		VerifyString(reason, "Not Found", "reasone is: Not Found", "reason is: " + reason); //Verify body code
		return jsonString;
	}
	//-------------------------------Verify Unauthorized 401------------------------------
	public static String Unauthorized(Response response) throws IOException
	{	
		StatusCode = response.getStatusCode();
	    jsonString = response.asString();
		Verification.VerifyInt(StatusCode, 401, "Status code = 401", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		return jsonString;
	}
	//-------------------------------Verify Method Not Allowed 405------------------------------
	public static String MethodNotAllowed(Response response) throws IOException
	{	
		StatusCode = response.getStatusCode();
        jsonString = response.asString();
		VerifyInt(StatusCode, 405, "Status code = 405", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		Code = JsonPath.from(jsonString).get("code");
		VerifyString(Code, "2003", "Code = 2003", "Code = " + Code); //Verify body code
		reason = JsonPath.from(jsonString).get("reason");
		VerifyString(reason, "Method Not Accepted", "reason is: Method Not Accepted", "reason is: " + reason);
		return jsonString;
	}
	//-------------------------------Unsupported Media Type 415------------------------------
	public static String UnsupportedMediaType(Response response) throws IOException
	{	
		StatusCode = response.getStatusCode();
        jsonString = response.asString();
		VerifyInt(StatusCode, 415, "Status code = 415", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		Code = JsonPath.from(jsonString).get("code");
		VerifyString(Code, "2004", "Code = 2004", "Code = " + Code); //Verify body code
		reason = JsonPath.from(jsonString).get("reason");
		VerifyString(reason, "Media Type not supported", "reasone is: Media Type not supported", "reason is: " + reason);
		message = JsonPath.from(jsonString).get("message");
		VerifyString(message, "Content-Type: 'null' Not supported", "message is: Content-Type: 'null' Not supported", "message is: " + message);
		return jsonString;
	}
	//-------------------------------Bad Request 400------------------------------
	public static String BadRequest(Response response) throws IOException
	{	
		StatusCode = response.getStatusCode();
        jsonString = response.asString();
		VerifyInt(StatusCode, 400, "Status code = 400", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		Code = JsonPath.from(jsonString).get("code");
		VerifyString(Code, "1004", "Code = 1004", "Code = " + Code); //Verify body code
		reason = JsonPath.from(jsonString).get("reason");
		VerifyString(reason, "Invalid Input", "reasone is: Invalid Input", "reason is: " + reason); //Verify body code
		return jsonString;
	}
	//---------------------------------Forbidden 403--------------------------------
	public static String Forbidden(Response response) throws IOException
	{	
		StatusCode = response.getStatusCode();
        jsonString = response.asString();
		VerifyInt(StatusCode, 403, "Status code = 403", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		Code = JsonPath.from(jsonString).get("code");
		VerifyString(Code, "2001", "Code = 2001", "Code = " + Code); //Verify body code
		reason = JsonPath.from(jsonString).get("reason");
		VerifyString(reason, "Action Not Permitted", "reasone is: Action Not Permitted", "reason is: " + reason); //Verify body code
		message = JsonPath.from(jsonString).get("message");
		VerifyString(message, "Access is denied", "message is: Access is denied", "message is: " + message);
		return jsonString;
	}
	//-------------------------------Internal Server Error 500------------------------------
	public static String InternalServerError(Response response) throws IOException
	{	
		StatusCode = response.getStatusCode();
        jsonString = response.asString();
		VerifyInt(StatusCode, 500, "Status code = 500", "Status code = " + Integer.toString(StatusCode)); //Verify status code
		Code = JsonPath.from(jsonString).get("code");
		VerifyString(Code, "3999", "Code = 3999", "Code = " + Code); //Verify body code
		reason = JsonPath.from(jsonString).get("reason");
		VerifyString(reason, "Generic System Error", "reasone is: Generic System Error", "reason is: " + reason);
		return jsonString;
	}
}
