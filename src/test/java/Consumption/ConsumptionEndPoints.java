package Consumption;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;

public class ConsumptionEndPoints {
	static String token;
	static Response response = null;
	public static Response consumptionRequest(String MSISDN, String Password)
	{
		//Get token at first
		try {
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
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
//==========================Test consumption==============================================
	public static void main( String[] args )
    {
		Response output=consumptionRequest("1030693069", "Test@1234");
		System.out.println("Loyalty Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }

}
