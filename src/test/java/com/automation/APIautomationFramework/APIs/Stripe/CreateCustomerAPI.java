package com.automation.APIautomationFramework.APIs.Stripe;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.automation.APIautomationFramework.base.BaseTest;

import io.restassured.response.Response;

public class CreateCustomerAPI extends BaseTest {
	
	public static Response createCustomerAPIwithValidKey(Hashtable<String, String> data) {
		
		Response response = given().auth().basic(prop.getProperty("ValidSecretKey"), "")
		.formParam("name", data.get("name"))
		.formParam("email", data.get("email")).formParam("description", data.get("description"))
		.post(prop.getProperty("customerAPIEndPoint"));
		
		return response;
		
	}
	
public static Response createCustomerAPIwithInvalidKey(Hashtable<String, String> data) {
	
	Response response = given().auth().basic(prop.getProperty("InvalidSecretKey"), "")
			.formParam("name", data.get("name"))
			.formParam("email", data.get("email")).formParam("description", data.get("description"))
			.post(prop.getProperty("customerAPIEndPoint"));
	
	 return response;
		
	}

}
