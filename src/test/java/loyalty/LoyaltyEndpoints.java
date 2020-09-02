package loyalty;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.Auth;
import utilities.Routes;

import static io.restassured.RestAssured.given;

public class LoyaltyEndpoints {
	public static final String loyaltyURL = "https://mobile.vodafone.com.eg/services/dxl/loyaltymng/loyaltyProgramMember";
	static Response response = null;
	static String token;
	public static Response loyaltyRequest(String msisdn, String password)
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
					 .header("api-host","LoyaltyManagementHost")
					 .header("access-token",token)
					 .pathParam("MSISDN", msisdn).get("/{MSISDN}/loyaltyProgramProduct");
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
