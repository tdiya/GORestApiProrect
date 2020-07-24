package com.api.gorest.tests;

import org.testng.annotations.Test;

import com.api.gorest.pojo.User;
import com.api.gorest.restclient.RestClient;

import io.restassured.response.Response;

public class Create_Users_Test_POST_Call {

	// Step 1 : pass the baseURI
	String baseURI = "https://gorest.co.in/";

	// Step 2 : Pass the basePath
	String basePath = "/public-api/users";

	// Step 3 : Pass the token
	String token = " _0B7lvRyO-EGyJRHzkWvdC87dMP0nQVhqBAS";
	
	@Test
	public void Create_User_Post_Call_Test(){
		
		User user = new User("API", "09last", "female", "01-03-1998", "uyduy@gmail.com", "9345000321", "http://www.ugy.com", "1rtuiosd", "active");
		Response response = RestClient.postcalls("JSON", baseURI, basePath, token,null, true, user);
		System.out.println("Status code is ========>>>>>" + response.getStatusCode());
		System.out.println("content type is ========>>>>"+ response.getContentType());
		
		System.out.println(response.prettyPrint());
	}
}
