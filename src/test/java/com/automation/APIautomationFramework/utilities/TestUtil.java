package com.automation.APIautomationFramework.utilities;

import org.json.JSONObject;

import com.automation.APIautomationFramework.base.BaseTest;
import com.aventstack.extentreports.Status;

public class TestUtil extends BaseTest {

	// to get the key
	public static boolean jsonHasKey(String json, String key) {

		JSONObject jsonObject = new JSONObject(json);
		extentTest.get().log(Status.INFO, "Validating the presence of key " + key);
		return jsonObject.has(key);

	}

	// to get the value
	public static String getJsonKeyValue(String json, String key) {

		JSONObject jsonObject = new JSONObject(json);
		extentTest.get().log(Status.INFO, "Validating the presence of key value " + key);
		return jsonObject.getString(key);

	}

}
