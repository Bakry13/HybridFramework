package loyalty;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Auth;
import utilities.Routes;

import static io.restassured.RestAssured.given;

public class LoyaltyErrorHandling {
	public static String token;
	static Response response = null;
//==================================Unauthorized 401==================================================		
	public static Response loyaltyWithoutmsisdn(String msisdn, String password)
	{    
		try {
			token= Auth.getToken(msisdn, password); 
			 RestAssured.baseURI = Routes.baseURL+Routes.loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("api-host","loyaltyManagementHost")
					 .header("access-token",token)	
			 		 .pathParam("msisdn", msisdn).get("/{msisdn}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return response;
	}
//==================================Unauthorized 401==================================================	
	public static Response loyaltyWithoutToken(String msisdn, String password)
	{    
	     try {
			token= Auth.getToken(msisdn, password); 
			 RestAssured.baseURI = Routes.baseURL+Routes.loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("api-host","loyaltyManagementHost")
					 .header("msisdn",msisdn)		
			 		 .pathParam("msisdn", msisdn).get("/{msisdn}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return response;
	}
//==================================Not Found 404==================================================	
	public static Response loyaltyWrongURL(String msisdn, String password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","loyaltyManagementHost")
					 .header("access-token",token)			 
			 		 .pathParam("msisdn", msisdn).get("/{msisdn}/loyaltyProgramProductm");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return response;
	}
//==================================Method Not Allowed 405==================================================		
	public static Response loyaltyPostRequest(String msisdn, String password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","loyaltyManagementHost")
					 .header("access-token",token)		 
			 	     .pathParam("msisdn", msisdn).post("/{msisdn}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		 return response;
	}
//==================================Unsupported Media type 415=========================================
	public static Response loyaltyWithoutContentType(String msisdn, String password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(msisdn, password); 
			 //Then send request
			 RestAssured.baseURI = Routes.baseURL+Routes.loyalty;
			 response = given()
					 //Send header parameters
					 .header("Accept","application/json")
					 .header("msisdn",msisdn)
					 .header("api-host","loyaltyManagementHost")
					 .header("access-token",token)
					 .pathParam("msisdn", msisdn).get("/{msisdn}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//===================================================================================================
	public static void main( String[] args )
    {
		Response output=loyaltyWithoutContentType("1030693069", "Test@1234");
		System.out.println("loyalty Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}
