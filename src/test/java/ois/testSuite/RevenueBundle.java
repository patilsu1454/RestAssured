package ois.testSuite;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import restAPIConstants.EndPointOIS;
import restAPIConstants.QueryParamOIS;
import driver.ApiDriver;

//import static org.hamcrest.Matchers.*;﻿
public class RevenueBundle extends ApiDriver {
	
	//EndPointOIS eodPoint= new EndPointOIS();
	//QueryParamOIS qparam= new QueryParamOIS();
		
	@Test (enabled=true)
	
	public void obl_getObligations(){	
		test =extent.createTest("Without using RestAssured BaseURI and basePath: obl_getObligations for SI magazine that has items 47,48,49");
		try{
			given().baseUri("http://tmpcmamva11.corp.ad.timeinc.com:9001/Obligations/").
			given().param("username",QueryParamOIS.username ).param("magCode", "SI").
			when().get("/GetObligations").
			then().body("Obligations.ObligationID", hasItems(47,48,49)).statusCode(200);  // status code OK
			System.out.println(" ** Passed hasItems for bundleId 47,48,49  **");
				
		}catch(AssertionError e){
			System.out.println("AssertionError*** :  " +e.getMessage());
			Assert.assertTrue(false,e.getMessage());
			//softAssert.assertTrue(false);
		}
	}
	
  @Test (enabled=true)
	
	public void obl_getObligations01(){
	test =extent.createTest("Using RestAssured basepath and baseURI");
	try{
		RestAssured.basePath="/Obligations";
		given().param("username", "iyeru").param("magCode", "SI").when().get("/GetObligations").
		then().body("Obligations.ObligationID", hasItems(147,148,149));
		System.out.println(" ** Passed hasItems for bundleId 147,148,149 **");
		
	}catch(AssertionError e){
		System.out.println("AssertionError*** :  " +e.getMessage());
		Assert.assertTrue(false,e.getMessage());
	}
	
   }
  

  @Test 	
	public void gettingRevenueBundles()
	{
	test=extent.createTest("Get the revnue bundles");
	
		try{
			
			RestAssured.basePath="/Revenue";
			given().param("SharedSecret", "kzBVuwq9Y^V-8W#c").param("magCode", "SI").get("/GetRevenueBundles").
			then().statusCode(200).log().all();
			
			}
		catch(AssertionError e){
			System.out.println("AssertionError*** :  " +e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}
  @Test 	
	public void gettingRevenueBundles02()
	{
	test=extent.createTest("Get the revnue bundles and verify bundle ID along with description");
	
		try{
			
			RestAssured.basePath="/Revenue";
			given().param("SharedSecret", "kzBVuwq9Y^V-8W#c").param("magCode", "SI").get("/GetRevenueBundles").
			then().body("Bundles.bundleId", hasItem(81));
			System.out.println("bundleId is valid");
			
			}
		catch(AssertionError e){
			System.out.println("AssertionError*** :  " +e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}
  
    
  
  
  
}
  
  


