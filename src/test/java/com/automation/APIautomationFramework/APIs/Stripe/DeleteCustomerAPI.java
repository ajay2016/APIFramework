package com.automation.APIautomationFramework.APIs.Stripe;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.automation.APIautomationFramework.base.BaseTest;

import io.restassured.response.Response;

public class DeleteCustomerAPI extends BaseTest {
	
	
public static Response deleteCustomerAPIwithValidID(Hashtable<String, String> data) {
		
		Response response = given().auth().basic(prop.getProperty("ValidSecretKey"), "")
		
		.delete(prop.getProperty("customerAPIEndPoint")+"/"+data.get("id"));
		
		return response;
		
	}

}
