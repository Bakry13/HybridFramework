package utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthWeb 
{	
	public static final String authURL = "https://web.vodafone.com.eg/auth/realms/vf-realm/protocol/openid-connect/token";
	public static String token = "0";
	public static String jsonString = "0";
	public static String statusCode = "0";
	
	public static Response authRequest(String msisdn, String password)
	{
		RestAssured.baseURI = Routes.webBaseURL+Routes.webToken;
        Response response = given()
        			//Send body in Data Form type
	    			.formParam("username", msisdn)
	    			.formParam("password", password)
	    			.formParam("grant_type", "password")
	    			.formParam("client_id", "my-vodafone-app")
	    			.formParam("client_secret", "f8192a7b-254d-4131-a8db-898e74043472")
	    			.post(); //Make post action

        statusCode = Integer.toString(response.getStatusCode());
        //System.out.println("Auth status code is "+ statusCode);
        return response;
	}
	
	public static String getToken(String msisdn, String password)
	{
		Response response = authRequest(msisdn, password); //Get the auth response
		jsonString =response.getBody().asString();	//Convert Jasone response to string
		token = JsonPath.from(jsonString).get("access_token");	//Get token from its node
		
		return token;
    }
	
	public static void main( String[] args )
    {
		System.out.println(getToken("01008308223", "Test@1234"));
    }
}
