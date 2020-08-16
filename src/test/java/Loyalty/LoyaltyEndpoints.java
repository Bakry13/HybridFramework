package Loyalty;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;

public class LoyaltyEndpoints {
	public static final String LoyaltyURL = "https://mobile.vodafone.com.eg/services/dxl/loyaltymng/loyaltyProgramMember";
	static Response response = null;
	static String token;
	public static Response loyaltyRequest(String MSISDN, String Password)
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
					 .pathParam("MSISDN", MSISDN).get("/{MSISDN}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==========================Test loyalty==============================================
	public static void main( String[] args )
    {
		Response output=loyaltyRequest("1030693069", "Test@1234");
		System.out.println("Loyalty Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}
