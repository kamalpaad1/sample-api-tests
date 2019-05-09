package com.tests;

import org.json.JSONObject;
import org.json.JSONException;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commons.BaseTest;
import com.utils.TestUtils;

public class PutTests extends BaseTest {

//	//base URL to the RESTful web service
	String baseURI = getBaseUrl();
	RequestSpecification httpRequest = RestAssured.given();
	
	@Test(priority=1, description="Put test data")
	public void putTestData() throws JSONException
	{   
		
		int postID = 1;
		
		//provide headers
		util.provideHeaders(httpRequest);
		
		//setup PUT payload
		int userID = 1;
		int ID = 1;
		String title = "abc";
		String body = "xyz";
		
		JSONObject obj = util.putPayload(userID, ID, title, body);
		httpRequest.body(obj.toString());
		
		Response response = httpRequest.request(Method.PUT, baseURI+"/posts/"+postID);
		test.log(Status.INFO,"Put url: - "+baseURI+"/posts/"+postID);

		JSONObject responseBody = new JSONObject(response.getBody().asString());
		test.log(Status.INFO,"Response for PUT req: - "+responseBody);
		
		//Verify the status code
		int respCode = response.getStatusCode();
		
		test.log(Status.INFO,"Response Code for PUT req: - "+respCode);
		Assert.assertEquals(respCode, 200, "Expected return of status code 201");
		
		//Verify response with jsonschema 
		response.then().assertThat().body(matchesJsonSchemaInClasspath("json-schemas/validate_schema.json"));
		test.log(Status.PASS,"Schema validation of PUT response successful");
		
		//Verify record update match
		Assert.assertTrue(responseBody.getInt("id") == ID);
		test.log(Status.PASS,"Response ID matches with required ID: "+responseBody.getInt("id"));
		
		Assert.assertTrue(responseBody.getString("title").equals(title));
		test.log(Status.PASS,"Response Title matches with required ID: "+responseBody.getString("title"));
		
		Assert.assertTrue(responseBody.getString("body").equals(body));
		test.log(Status.PASS,"Response Body matches with required ID: "+responseBody.getString("body"));
		
		Assert.assertTrue(responseBody.getInt("UserId") == userID);
		test.log(Status.PASS,"Response User ID matches with required User ID: "+responseBody.getInt("UserId"));	
		
	
	}
}