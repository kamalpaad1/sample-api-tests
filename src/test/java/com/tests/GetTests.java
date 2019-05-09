package com.tests;

//import java.lang.reflect.*;
//import java.lang.reflect.Method;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
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
public class GetTests extends BaseTest {
	
	RequestSpecification httpRequest = RestAssured.given();
//	//base URL to the RESTful web service
	//String baseURI = "http://jsonplaceholder.typicode.com";
	String baseURI = getBaseUrl();
	
	@Test(priority=1, description="Get all the posts", enabled=true)
	public void GetAllPosts() throws JSONException
	{   
	
		//provide headers
		util.provideHeaders(httpRequest);
		
		System.out.println("ENVIRONMENT USED: "+System.getProperty("environment"));

		// send a request to the server with the method type and the method URL and store the data in response variable
		Response response = httpRequest.request(Method.GET, baseURI+"/posts");
		test.log(Status.INFO,"Queried GET url: - "+baseURI+"/posts");

		JSONArray responseBody = new JSONArray(response.getBody().asString());
		test.log(Status.INFO,"Response for Get req: - "+responseBody);
		
		//Verify the status code
		int respCode = response.getStatusCode();
		test.log(Status.INFO,"Response Code for GET req: - "+respCode);
		Assert.assertEquals(respCode, 200, "Expected return of status code 200");
		
		//Verify response with jsonschema 
		response.then().assertThat().body(matchesJsonSchemaInClasspath("json-schemas/getAllPosts_schema.json"));
		test.log(Status.PASS,"Schema validation of GET all posts response successful");

		Assert.assertTrue(responseBody.length() <= 100);
		test.log(Status.PASS,"response body has "+responseBody.length()+" records");
		
	}
  
	@Test(priority=2, description="Get individual id based posts")
	public void GetSinglePost() throws JSONException
	{   
		//provide headers
		util.provideHeaders(httpRequest);
		
		int postID = 1;


		// send a request to the server with the method type and the method URL and store the data in response variable
		Response response = httpRequest.request(Method.GET, baseURI+"/posts/"+postID);
		test.log(Status.INFO,"Queried GET url: - "+baseURI+"/posts/"+postID);

		JSONObject responseBody = new JSONObject(response.getBody().asString());
		test.log(Status.INFO,"Response for Get req: - "+responseBody);
		
		Assert.assertTrue(responseBody.getInt("id") == postID);
		
		//Verify the status code
		int respCode = response.getStatusCode();
		test.log(Status.INFO,"Response Code for GET req: - "+respCode);
		Assert.assertEquals(respCode, 200, "Expected return of status code 200");

		//Verify response with jsonschema 
		response.then().assertThat().body(matchesJsonSchemaInClasspath("json-schemas/getSinglePost_schema.json"));
		test.log(Status.PASS,"Schema validation of GET single post response successful");
	}
	
	@Test(priority=3, description="check invalid Id request")
	public void checkInvalidIDPost() throws JSONException
	{   
		
		int postID = 1234;

		//provide headers
		util.provideHeaders(httpRequest);

		// send a request to the server with the method type and the method URL and store the data in response variable
		Response response = httpRequest.request(Method.GET, baseURI+"/posts/"+postID);
		test.log(Status.INFO,"Queried GET url: - "+baseURI+"/posts/"+postID);

		JSONObject responseBody = new JSONObject(response.getBody().asString());
		test.log(Status.INFO,"Response for Get req: - "+responseBody);
		
		//Verify the status code
		int respCode = response.getStatusCode();
		test.log(Status.INFO,"Response Code for GET req: - "+respCode);
		Assert.assertEquals(respCode, 404, "Expected return of status code 404");
		test.log(Status.PASS,"Response Code for GET Invalid posts req: - "+respCode);

	}
  
}
