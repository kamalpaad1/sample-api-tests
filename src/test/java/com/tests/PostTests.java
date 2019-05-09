package com.tests;

import org.json.JSONObject;
import org.json.JSONException;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.commons.BaseTest;
import com.utils.TestUtils;

/**
* Unit test for simple App.
*/
public class PostTests extends BaseTest{
	
//	//base URL to the RESTful web service
	String baseURI = getBaseUrl();
	RequestSpecification httpRequest = RestAssured.given();
	
	@Test(priority=1, description="Post a test data")
	public void postTestData() throws JSONException
	{   
		//provide headers
		util.provideHeaders(httpRequest);
		
		//response ID
		int responseID = 101;
		
		//setup the payload
		int userID = 1;
		String title = "foo";
		String body = "bar";
		
		JSONObject obj = util.postPayload(userID, title, body);
		httpRequest.body(obj.toString());
		
		Response response = httpRequest.request(Method.POST, baseURI+"/posts");
		test.log(Status.INFO,"Post url: - "+baseURI+"/posts");

		JSONObject responseBody = new JSONObject(response.getBody().asString());
		test.log(Status.INFO,"Response for POST req: - "+responseBody);
		
		//Verify the status code
		int respCode = response.getStatusCode();
		Assert.assertEquals(respCode, 201, "Expected return of status code 201");
		test.log(Status.PASS,"Response Code for POST req: - "+respCode);
		
		//Verify response with jsonschema 
		response.then().assertThat().body(matchesJsonSchemaInClasspath("json-schemas/validate_schema.json"));
		test.log(Status.PASS,"Schema validation of POST response successful");
		
		//Verify record Create match
		Assert.assertTrue(responseBody.getInt("id") == responseID);
		test.log(Status.PASS,"Response ID matches with required ID: "+responseBody.getInt("id"));
		
		Assert.assertTrue(responseBody.getString("title").equals(title));
		test.log(Status.PASS,"Response Title matches with required ID: "+responseBody.getString("title"));
		
		Assert.assertTrue(responseBody.getString("body").equals(body));
		test.log(Status.PASS,"Response Body matches with required ID: "+responseBody.getString("body"));
		
		Assert.assertTrue(responseBody.getInt("UserId") == userID);
		test.log(Status.PASS,"Response User ID matches with required User ID: "+responseBody.getInt("UserId"));	
		
	}
}