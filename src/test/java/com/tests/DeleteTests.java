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

/**
* Unit test for simple App.
*/
public class DeleteTests extends BaseTest {
	
    //base URL to the RESTful web service
	String baseURI = getBaseUrl();
	RequestSpecification httpRequest = RestAssured.given();
	
	@Test(priority=1, description="delete a test data")
	public void deleteTestData() throws JSONException
	{   
		int postID = 1;
		
		//provide headers
		util.provideHeaders(httpRequest);
		
		Response response = httpRequest.request(Method.DELETE, baseURI+"/posts/"+postID);
		test.log(Status.INFO,"Delete url: - "+baseURI+"/posts/"+postID);

		JSONObject responseBody = new JSONObject(response.getBody().asString());
		test.log(Status.INFO,"Response for Delete req: - "+responseBody);
		
		//Verify the status code
		int respCode = response.getStatusCode();
		Assert.assertEquals(respCode, 200, "Expected return of status code 200");
		test.log(Status.PASS,"Response Code for Delete req: - "+respCode);
		
		Assert.assertTrue(responseBody.toString().equals("{}"));
		test.log(Status.PASS,"Response body is empty: - "+responseBody);
		
	}
}