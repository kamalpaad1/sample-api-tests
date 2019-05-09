package com.utils;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TestUtils {
		
	public RequestSpecification provideHeaders(RequestSpecification request) {
		request.header("Content-Type", "application/json");
		request.header("charset", "UTF-8");
		
		return request;
	}
	
	public JSONObject putPayload(int userID, int ID, String title, String body) throws JSONException {
		JSONObject bodyData = new JSONObject();
		bodyData.put("title", title);
		bodyData.put("body", body);
		bodyData.put("UserId", userID);
		bodyData.put("id", ID);
		
		return bodyData;
	}
	
	public JSONObject postPayload(int userID, String title, String body) throws JSONException {
		JSONObject bodyData = new JSONObject();
		bodyData.put("title", title);
		bodyData.put("body", body);
		bodyData.put("UserId", userID);
		
		return bodyData;
	}

}
