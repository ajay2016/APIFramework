package com.automation.APIautomationFramework.rough;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.APIautomationFramework.pojo.Orders;
import com.automation.APIautomationFramework.pojo.PurchaseUnits;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestPaypal {

	static String Client_ID = "AfZPxDyNA8PeMrwSK3-NKp7rlXJSi_8QVY1YWHaL0lZVb7jmVoBKd0nT6EagmICkN6gumg_OYc6DVMqZ";
	static String Secret_Key = "ELAsJeknsDwbNLC1NTU10YZSxUCxL2UI5pZoGc93BFsq8Xt5S_nYtXO6j0-x5h7x4TD_MoVAhwfR52Bx";
	static String access_token;
	static String orderId;
	

	@Test(priority = 1)
	public void getAccessToken() {
		RestAssured.baseURI = "https://api.sandbox.paypal.com/";

		Response response = given().param("grant_type", "client_credentials").auth().preemptive()// This means that the
																									// authentication
																									// details are sent
																									// in the request
																									// header
				.basic(Client_ID, Secret_Key).post("/v1/oauth2/token");

		response.prettyPrint();

		access_token = response.jsonPath().get("access_token").toString();
		System.out.println(access_token);
	}

	@Test(priority = 2, dependsOnMethods = "getAccessToken")
	public void createOrder() {
		RestAssured.baseURI = "https://api.sandbox.paypal.com/";
		
		//purchase units as array lists
		ArrayList<PurchaseUnits> list = new ArrayList<PurchaseUnits>();
		list.add(new PurchaseUnits("USD", "200.00"));
		
		//we have to send intent and purchase units
		Orders order = new Orders("CAPTURE",list);

		/*
		 * String order = "{\r\n" + "  \"intent\": \"CAPTURE\",\r\n" +
		 * "  \"purchase_units\": [\r\n" + "    {\r\n" + "      \"amount\": {\r\n" +
		 * "        \"currency_code\": \"USD\",\r\n" +
		 * "        \"value\": \"150.00\"\r\n" + "      }\r\n" + "    }\r\n" + "  ]\r\n"
		 * + "}";
		 */
		Response resposne = given().contentType(ContentType.JSON).auth().oauth2(access_token).body(order)
				.post("/v2/checkout/orders");

		resposne.prettyPrint();
		String status = resposne.jsonPath().get("status").toString();
		Assert.assertEquals(status, "CREATED");
		orderId = resposne.jsonPath().get("id").toString();

	}

	@Test(priority = 3, dependsOnMethods = { "getAccessToken", "createOrder" })
	public void getOrderDetails() {

		System.out.println("----Getting the ORDER-------");

		RestAssured.baseURI = "https://api.sandbox.paypal.com/";
		Response resposne = given().contentType(ContentType.JSON).auth().oauth2(access_token)

				.get("/v2/checkout/orders" + "/" + orderId);

		resposne.prettyPrint();

		Assert.assertEquals(resposne.getStatusCode(), 200);

	}

}
