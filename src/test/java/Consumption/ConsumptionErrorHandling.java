package Consumption;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;

public class ConsumptionErrorHandling {
 
	public static String token;
	static Response response = null;
//==================================Unauthorized 401==================================================	
	public static Response consumptionRequestWithoutMsisdn(String MSISDN, String Password)
	{    
		try {
			token= Auth.getToken(MSISDN, Password); 
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption;
			 response = given()
						//Send header parameters
						 .header("Content-Type","application/json")
						 .header("Accept","application/json")
						 .header("api-host","usageConsumptionHost")
						 .header("access-token",token)
						 .queryParam("@type","aggregated") 
			             .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Unauthorized 401==================================================
	public static Response consumptionRequestWithoutToken(String MSISDN, String Password)
	{    
	     try {
			token= Auth.getToken(MSISDN, Password); 
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption;
			 response = given()
						//Send header parameters
						 .header("Content-Type","application/json")
						 .header("Accept","application/json")
						 .header("msisdn",MSISDN)
						 .header("api-host","usageConsumptionHost")
						 .queryParam("@type","aggregated") 
			             .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Not Found 404==================================================	
	public static Response consumptionRequestWrongURL(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption+"m";
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","usageConsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Method Not Allowed 405==================================================
	public static Response consumptionpostRequest(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","usageConsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id",MSISDN).post();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Bad Request 400==================================================
	public static Response consumptionWrongQparam(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","usageConsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id",MSISDN+1).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Internal Server Error 500=========================================	
	public static Response consumptionMissingQParamtRequest(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","usageConsumptionHost")
					 .header("access-token",token)
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Unsupported Media type 415=========================================
	public static Response consumptionWithoutContentType(String MSISDN, String Password)
	{
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption;
			 response = given()
					//Send header parameters
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","usageConsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Forbidden 403=========================================
	public static Response consumptionWithDifferentMSISDN(String MSISDN, String Password)
	{
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","usageConsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id","1032322955").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//===================================================================================================
	public static void main( String[] args )
    {
		Response output=consumptionMissingQParamtRequest("1030693069", "Test@1234");
		System.out.println("Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}

	

