package com.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class Get_Users_Test {

	// Step 1 : pass the baseURI
	String baseURI = "https://gorest.co.in/";
	
	// Step 2 : Pass the basePath
	String basePath = "/public-api/users";
	
	// Step 3 : Pass the token
	String token = " _0B7lvRyO-EGyJRHzkWvdC87dMP0nQVhqBAS";
	
	// Step 4 :
	@Test(priority =1)
	public void getAllusersTest(){
		// Step 5 :
		Response response = RestClient.getCalls("JSON", baseURI, basePath, token, null, true);
		
		//Step 6 :
		System.out.println("Status Code captured after sending request is :"+ response.getStatusCode());
		System.out.println("Content type captured after sending request is :"+ response.getContentType());
		System.out.println(response.prettyPrint());
	}
	
	// Step 7 :
	@Test(priority=2)
	public void getUserWithQueryParam(){
		
		// Step 8 :
		Map<String, String> params = new HashMap<String, String>();
		params.put("first_name", "George");
		params.put("gender", "male");
		
		// Step 9 :
		Response response = RestClient.getCalls("JSON", baseURI, basePath, token, params, true);
		System.out.println("Status Code captured after sending request is :"+ response.getStatusCode());
		System.out.println("Content type captured after sending request is :"+ response.getContentType());
		System.out.println(response.prettyPrint());
		
	}
	
	
}
