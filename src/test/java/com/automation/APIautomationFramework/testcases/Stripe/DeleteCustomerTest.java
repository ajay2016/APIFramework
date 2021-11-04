package com.automation.APIautomationFramework.testcases.Stripe;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.automation.APIautomationFramework.APIs.Stripe.DeleteCustomerAPI;
import com.automation.APIautomationFramework.base.BaseTest;
import com.automation.APIautomationFramework.utilities.DataUtil;
import com.automation.APIautomationFramework.utilities.TestUtil;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

//extends Base
public class DeleteCustomerTest extends BaseTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void DeleteCustomer(Hashtable<String, String> data) {
		
		

		if (!DataUtil.isRunnable("DeleteCustomer") || data.get("Runmode").equals("N")) {

			 extentTest.get().log(Status.SKIP, "Test skipped since rumode is N");
			// ExtentListeners.testReport.get().info("Test skipped since rumode is N");
			// ExtentListeners.testReport.get().skip("Test skipped since rumode is N");
			throw new SkipException("Test skipped since rumode is N");

		}
		
		extentTest.get().log(Status.INFO, "Testing Delete Customer with Valid ID ");
		Response response = DeleteCustomerAPI.deleteCustomerAPIwithValidID(data);

		response.prettyPrint();
		
		//extentTest.get().log(Status.INFO, "Testing Create CustomerAPI with InvalidKey ");
		extentTest.get().log(Status.INFO, data.toString());
		//ExtentListeners.testReport.get().info(data.toString());

		/*
		 * String actual_id = response.jsonPath().get("id").toString();
		 * System.out.println("Deleted Customer id is:  "+actual_id);
		 * Assert.assertEquals(actual_id, data.get("id"));
		 */

		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);

		/*
		 * JSONObject jsonObject = new JSONObject(response.asString());
		 * System.out.println(jsonObject.has("id"));
		 * Assert.assertTrue(jsonObject.has("id"),"ID not present");
		 * 
		 * String actual_id = jsonObject.getString("id"); Assert.assertEquals(actual_id,
		 * data.get("id"));
		 */
		
		System.out.println("Presence check for Object Key : "+TestUtil.jsonHasKey(response.asString(), "object"));
		Boolean objectvalue = TestUtil.jsonHasKey(response.asString(), "object");
		extentTest.get().log(Status.INFO, "Presence check for Object Key : "+objectvalue);
		System.out.println("Presence check for Deleted Key : "+TestUtil.jsonHasKey(response.asString(), "deleted"));
		Boolean deletedvalue = TestUtil.jsonHasKey(response.asString(), "deleted");
		extentTest.get().log(Status.INFO, "Presence check for Deleted Key : "+deletedvalue);
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"), "ID not present");

		String actual_id = TestUtil.getJsonKeyValue(response.asString(), "id");
		Assert.assertEquals(actual_id, data.get("id"));
		
		//System.out.println("Object key value is : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
		//System.out.println("Deleted key value is : "+TestUtil.getJsonKeyValue(response.asString(), "deleted"));
		
		
		Assert.assertEquals(response.statusCode(), 200);
	}

}
