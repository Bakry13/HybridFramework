package consumption;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.Auth;
import utilities.Routes;

import static io.restassured.RestAssured.given;

public class ConsumptionErrorHandling {
 
	public static String token;
	static Response response = null;
//==================================Unauthorized 401==================================================	
	public static Response consumptionRequestWithoutmsisdn(String msisdn, String password)
	{    
		try {
			token= Auth.getToken(msisdn, password); 
			 RestAssured.baseURI = Routes.baseURL+Routes.consumption;
			 response = given()
						//Send header parameters
						 .header("Content-Type","application/json")
						 .header("Accept","application/json")
						 .header("api-host","usageconsumptionHost")
						 .header("access-token",token)
						 .queryParam("@type","aggregated") 
			             .queryParam("relatedParty.id",msisdn).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Unauthorized 401==================================================
	public static Response consumptionRequestWithoutToken(String msisdn, String password)
	{    
	     try {
			token= Auth.getToken(msisdn, password); 
			 RestAssured.baseURI = Routes.baseURL+Routes.consumption;
			 response = given()
						//Send header parameters
						 .header("Content-Type","application/json")
						 .header("Accept","application/json")
						 .header("msisdn",msisdn)
						 .header("api-host","usageconsumptionHost")
						 .queryParam("@type","aggregated") 
			             .queryParam("relatedParty.id",msisdn).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Not Found 404==================================================	
	public static Response consumptionRequestWrongURL(String msisdn, String password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.consumption+"m";
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","usageconsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id",msisdn).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Method Not Allowed 405==================================================
	public static Response consumptionpostRequest(String msisdn, String password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.consumption;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","usageconsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id",msisdn).post();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Bad Request 400==================================================
	public static Response consumptionWrongQparam(String msisdn, String password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.consumption;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","usageconsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id",msisdn+1).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Internal Server Error 500=========================================	
	public static Response consumptionMissingQParamtRequest(String msisdn, String password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.consumption;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","usageconsumptionHost")
					 .header("access-token",token)
			         .queryParam("relatedParty.id",msisdn).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Unsupported Media type 415=========================================
	public static Response consumptionWithoutContentType(String msisdn, String password)
	{
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.consumption;
			 response = given()
					//Send header parameters
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","usageconsumptionHost")
					 .header("access-token",token)
					 .queryParam("@type","aggregated") 
			         .queryParam("relatedParty.id",msisdn).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Forbidden 403=========================================
	public static Response consumptionWithDifferentmsisdn(String msisdn, String password)
	{
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.consumption;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","usageconsumptionHost")
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

	

