package Utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Auth 
{	
	public static final String AuthURL = "https://mobile.vodafone.com.eg/services/security/oauth/oauth/token";
	public static String token = "0";
	public static String jsonString = "0";
	public static String StatusCode = "0";
	
	public static Response authRequest(String MSISDN, String Password)
	{
		RestAssured.baseURI = Routes.BaseURL+Routes.Token;
        Response response = given()
        			//Send body in Data Form type
	    			.formParam("username", MSISDN)
	    			.formParam("password", Password)
	    			.formParam("grant_type", "password")
	    			.formParam("client_id", "my-trusted-client")
	    			.formParam("client_secret", "secret")
	    			.post(); //Make post action
        
        StatusCode = Integer.toString(response.getStatusCode());
        System.out.println("Auth status code is "+ StatusCode);
        return response;
	}
	
	public static String getToken(String MSISDN, String Password)
	{
		try {
			Response response = authRequest(MSISDN, Password); //Get the auth response
			jsonString =response.getBody().asString();	//Convert Jasone response to string
			token = JsonPath.from(jsonString).get("access_token");	//Get token from its node
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return token;
    }
	
	public static void main( String[] args )
    {
		System.out.println(getToken("1030693069", "Test@1234"));
    }
}
