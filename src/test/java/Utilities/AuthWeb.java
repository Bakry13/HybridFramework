package Utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthWeb 
{	
	public static final String AuthURL = "https://web.vodafone.com.eg/auth/realms/vf-realm/protocol/openid-connect/token";
	public static String token = "0";
	public static String jsonString = "0";
	public static String StatusCode = "0";
	
	public static Response authRequest(String MSISDN, String Password)
	{
		RestAssured.baseURI = Routes.WebBaseURL+Routes.WebToken;
        Response response = given()
        			//Send body in Data Form type
	    			.formParam("username", MSISDN)
	    			.formParam("password", Password)
	    			.formParam("grant_type", "password")
	    			.formParam("client_id", "my-vodafone-app")
	    			.formParam("client_secret", "f8192a7b-254d-4131-a8db-898e74043472")
	    			.post(); //Make post action

        StatusCode = Integer.toString(response.getStatusCode());
        //System.out.println("Auth status code is "+ StatusCode);
        return response;
	}
	
	public static String getToken(String MSISDN, String Password)
	{
		Response response = authRequest(MSISDN, Password); //Get the auth response
		jsonString =response.getBody().asString();	//Convert Jasone response to string
		token = JsonPath.from(jsonString).get("access_token");	//Get token from its node
		
		return token;
    }
	
	public static void main( String[] args )
    {
		System.out.println(getToken("01008308223", "Test@1234"));
    }
}
