package com.api.gorest.restclient;

import java.util.Map;


import org.testng.annotations.Test;

import com.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	// Step 1
	/**
	 * This class will be having all the http methods that will call the API's
	 * and having generic methods
	 * @return 
	 */

	// Step 2
	// Create method for GET call
	// need to pass--> String contentType, String baseURI, String basePath, String token,
	// Map<String, String> paramsMap, boolean log

	
	@Test
	public static Response getCalls(String contentType, String baseURI, String basePath, String token,
			Map<String, String> paramsMap, boolean log) {

		// Step 4 : Calling setBaseURI method
		setBaseURI(baseURI);

		// Step 6 : Calling createrequest method
		RequestSpecification req = createRequest(contentType, token, paramsMap, log);
		
		// Step 10 : calling getResponse method
		return getResponse("GET", req, basePath);
	}

	// Step 3
	private static void setBaseURI(String baseURI) {

		RestAssured.baseURI = baseURI;

	}

	// Step 5 : Create a request
	private static RequestSpecification createRequest(String contentType, String token, Map<String, String> paramsMap, boolean log) {

		RequestSpecification req;
		// Log
		if (log) {
			// For log if value is passed as true, then it will generate logs
			req = RestAssured.given().log().all();

		} else {
			// Not generating any logs
			req = RestAssured.given();

		}

		// Passing Token
		if (token != null) {
			req.header("Authorization", "Bearer" + token);
		}

		// Passing Params
		if (!(paramsMap == null)) {
			req.queryParams(paramsMap);
		}

		// JSON, XML, TEXT
		// Passing Content Types
		if (contentType.equalsIgnoreCase("JSON")) {
			req.contentType(ContentType.JSON);

		} else if (contentType.equalsIgnoreCase("XML")) {

			req.contentType(ContentType.XML);

		} else if (contentType.equalsIgnoreCase("TEXT")) {

			req.contentType(ContentType.TEXT);

		}
		
		return req;
	}
	
	// Step 7 : Hitting an API --> need to pass 
	
	private static Response getResponse(String httpMethod, RequestSpecification req, String basePath){
		
		// Step 9 :
		return executeAPI(httpMethod, req, basePath);
		
	}
	
	//Step 8 : Executing API
	
	private static Response executeAPI(String httpMethod, RequestSpecification req, String basePath){
		// This executeAPI method will return some response
		Response response = null;
		switch (httpMethod) {
		case "GET":
			response = req.get(basePath);
			break;

		case "POST":
			response = req.post(basePath);
			break;
		case "DELETE":
			response = req.delete(basePath);
			break;
		default:
			System.out.println("Please pass the valid HTTP Method like (GET,POST, PUT, DELETE)");
			break;
		}
	
	return response;
	}
	
	// Step 11
	// For POST calls--> create method for POST call
	// need to pass--> String contentType, String baseURI, String basePath, String token,
	// Map<String, String> paramsMap, boolean log
	public static Response postcalls(String contentType, String baseURI, String basePath, String token,
			                      Map<String, String> paramsMap, boolean log, Object obj){
		
	// Step 12
		setBaseURI(baseURI);
	// Step 13
		RequestSpecification req = createRequest(contentType, token, paramsMap, log);
	
	// Step 14 : payload from testUtil class
		
		String jsonPayload = TestUtil.getSerilizedJSON(obj);
		req.body(jsonPayload);
		
	// Step 15 : calling getResponse method
		return getResponse("POST", req, basePath);
		
	}
}
