package ois.testSuite;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import restAPIConstants.EndPointOIS;
import restAPIConstants.QueryParamOIS;
import driver.ApiDriver;

public class ReponseValidation extends ApiDriver{
	
	//EndPointOISOIS EndPointOIS=new EndPointOIS();
	//QueryParamOIS queryParam=new QueryParamOIS();

	@Test
	public void validateQueryParam(){
		test=extent.createTest("Get the obligations");
		try{
		RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();
		requestSpecification.queryParam("username", QueryParamOIS.username);
		requestSpecification.queryParam("magcode", "SI").log().all();
		given().spec(requestSpecification).get(EndPointOIS.GET_OBLIGATIONS).then().statusCode(200).log().all();
		
		//Inline validation
		//getting response --Hard assert , with dots between key-value pairs---> Stops the execution as and when it finds mismatch
		Response resp=given().spec(requestSpecification).get(EndPointOIS.GET_OBLIGATIONS);
		resp.then().body("Obligations.ObligationID", hasItem(47)).body("Obligations.Mag_Code", hasItem("SI"));   //after dot and dot hard assertion else it's a soft assertion
		
		//Soft assert did not halt execution upon failure, it logs exception object message and continues with execution
		System.out.println("Soft assert did not halt execution upon failure");
		resp.then().body("Obligations.ObligationID", hasItem(47),"Obligations.Mag_Code", hasItem("SI"),"Obligations.Status", hasItem("REVISED"));
		}
		
		catch(AssertionError e){
		System.out.println("AssertionError*** :  " +e.getMessage());
		Assert.assertTrue(false,e.getMessage());
		//softAssert.assertTrue(false);
		}
		
	}
	
	
	@Test
	public void validateQueryParamforRevenue(){
		
		//http://tmpcmamva11.corp.ad.timeinc.com:9001/Revenue/GetRevenueBundles?SharedSecret=kzBVuwq9Y%5EV-8W%23c&MagCode=SI 
				
		test=extent.createTest("TC#01 : Get revnue bundles API validation");
		try{
		RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();
		requestSpecification.queryParam("SharedSecret", QueryParamOIS.SharedSecret);
		requestSpecification.queryParam("MagCode", "SI").log().all();
		given().spec(requestSpecification).get(EndPointOIS.GET_REVENUEBUNDLES).then().statusCode(200).log().all();
		
		//getting response --Hard assert , with dots between key-value pairs---> Stops the execution as and when it finds mismatch
		Response resp=given().spec(requestSpecification).get(EndPointOIS.GET_REVENUEBUNDLES);
		//resp.then().body("Obligations.ObligationID", hasItem(47)).body("Obligations.Mag_Code", hasItem("SI"));   //after dot and dot hard assertion else it's a soft assertion
		
		//Soft assert did not halt execution upon failure, it logs exception object message and continues with execution
		System.out.println("Soft assert did not halt execution upon failure");
		resp.then().body("Bundles.description", hasItem("0-6 Months Print Only"));
		}
		
		catch(AssertionError e){
		System.out.println("AssertionError*** :  " +e.getMessage());
		Assert.assertTrue(false,e.getMessage());
		//softAssert.assertTrue(false);
		}
		
	}
	
}
