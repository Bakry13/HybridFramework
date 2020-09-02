package consumption;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.Auth;
import utilities.Routes;

import static io.restassured.RestAssured.given;

public class ConsumptionEndPoints {
	static String token;
	static Response response = null;
	public static Response consumptionRequest(String msisdn, String password)
	{
		//Get token at first
		try {
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
			         .queryParam("relatedParty.id",msisdn).get();
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
