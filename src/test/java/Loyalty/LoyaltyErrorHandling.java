package Loyalty;

import Utilities.Auth;
import Utilities.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class LoyaltyErrorHandling {
	public static String token;
	static Response response = null;
//==================================Unauthorized 401==================================================		
	public static Response loyaltyWithoutMsisdn(String MSISDN, String Password)
	{    
		try {
			token= Auth.getToken(MSISDN, Password); 
			 RestAssured.baseURI = Routes.BaseURL+Routes.Loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("api-host","LoyaltyManagementHost")
					 .header("access-token",token)	
			 		 .pathParam("MSISDN", MSISDN).get("/{MSISDN}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return response;
	}
//==================================Unauthorized 401==================================================	
	public static Response loyaltyWithoutToken(String MSISDN, String Password)
	{    
	     try {
			token= Auth.getToken(MSISDN, Password); 
			 RestAssured.baseURI = Routes.BaseURL+Routes.Loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("api-host","LoyaltyManagementHost")
					 .header("msisdn",MSISDN)		
			 		 .pathParam("MSISDN", MSISDN).get("/{MSISDN}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return response;
	}
//==================================Not Found 404==================================================	
	public static Response loyaltyWrongURL(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","LoyaltyManagementHost")
					 .header("access-token",token)			 
			 		 .pathParam("MSISDN", MSISDN).get("/{MSISDN}/loyaltyProgramProductm");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return response;
	}
//==================================Method Not Allowed 405==================================================		
	public static Response loyaltyPostRequest(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","LoyaltyManagementHost")
					 .header("access-token",token)		 
			 	     .pathParam("MSISDN", MSISDN).post("/{MSISDN}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return response;
	}
//==================================Unsupported Media type 415=========================================
	public static Response loyaltyWithoutContentType(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Loyalty;
			 response = given()
					 //Send header parameters
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","LoyaltyManagementHost")
					 .header("access-token",token)
					 .pathParam("MSISDN", MSISDN).get("/{MSISDN}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//===================================================================================================
	public static void main( String[] args )
    {
		Response output=loyaltyWithoutContentType("1030693069", "Test@1234");
		System.out.println("Loyalty Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}
