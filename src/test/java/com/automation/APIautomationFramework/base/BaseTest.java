package com.automation.APIautomationFramework.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automation.APIautomationFramework.utilities.ExcelReader;

import extentreports.TestListeners;
import io.restassured.RestAssured;

public class BaseTest extends TestListeners {
	
	//public ExtentTest test;
	public static Properties prop = new Properties();;
	public FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\testdata\\testdata.xlsx");

	
	@BeforeSuite
	public void setUp() {
		
	//	prop= new Properties();
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		RestAssured.baseURI = prop.getProperty("baseURI");
		RestAssured.basePath = prop.getProperty("basePath");

	}
	
	
	@AfterSuite
	public void tearDown() {

	}
	
	/*
	 * //To get log public static ExtentTest getLog() {
	 * 
	 * ExtentTest test =ExtentListeners.testReport.get().info("Test Started");
	 * 
	 * return test;
	 * 
	 * }
	 */

}
