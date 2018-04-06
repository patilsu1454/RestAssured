package ois.testSuite;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.ITestContext;

import restAPIConstants.EndPointOIS;
import restAPIConstants.QueryParamOIS;
import utils.APIUtils;
import driver.ApiDriver;

public class PostValidateRevenueBundle extends ApiDriver{
	
	
  @Test(priority=8)
  public void validResponse(){
	test=extent.createTest("TC01-PostRevenueValidateRevenueBundle", "verify valid response");
	test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
	try{		
		
		String sharedKey=QueryParamOIS.SharedSecret;

		RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
		requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
				+ "\"magcode\":\"EE\","
				+ "\"printFlag\":\"Y\","
				+ "\"digitalFlag\":\"N\","
				+ "\"term\":10,"
				+ "\"bundleId\":18,"
				+ "\"bulkIndicator\":\"S\"}");
		
		given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES).then().statusCode(200).log().all();
			
		Response res=given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		
		APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Valid");
	/*	SoftAssert sa=new SoftAssert();
		sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);
		sa.assertEquals(res.then().body(("validity_flag"), is("Vaalid")) ,true);
		sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);*/

				
		test.pass("Passed in report");
				
			}
	
						catch(AssertionError e){		
						Assert.assertTrue(false, e.getMessage());
					}
	
			
  }
  
  
  @Test (priority=9)
   public void nullInterm(){
 		test=extent.createTest("TC02-PostRevenueValidateRevenueBundle", "Verify Revenue Bundle does not contain appropriate term-103");
 		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
 		try{		
 			
 			String sharedKey=QueryParamOIS.SharedSecret;

 			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
 			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
 					+ "\"magcode\":\"EE\","
 					+ "\"printFlag\":\"Y\","
 					+ "\"digitalFlag\":\"N\","
 					+ "\"term\":null,"
 					+ "\"bundleId\":18,"
 					+ "\"bulkIndicator\":\"S\"}");
 			
 			given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES).then().statusCode(200).log().all();
 				
 			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
 			
 			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 103);
 			APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "Revenue Bundle does not contain appropriate term");
 	
 		/*	SoftAssert sa=new SoftAssert();
 			sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);
 			sa.assertEquals(res.then().body(("validity_flag"), is("Vaalid")) ,true);
 			sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);*/

 					
 			test.pass("Passed in report");
 					
 		}
 		catch(AssertionError e){		
 			Assert.assertTrue(false, e.getMessage());
 		}
 						
   }
  
 @Test(priority=10)
  public void invalidterm(){
		test=extent.createTest("TC03-PostRevenueValidateRevenueBundle", "Verify Revenue Bundle does not contain appropriate term-103");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			
			String sharedKey=QueryParamOIS.SharedSecret;

			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"EE\","
					+ "\"printFlag\":\"Y\","
					+ "\"digitalFlag\":\"N\","
					+ "\"term\":13,"
					+ "\"bundleId\":18,"
					+ "\"bulkIndicator\":\"S\"}");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
			
			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 103);
			APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "Revenue Bundle does not contain appropriate term");
	
		/*	SoftAssert sa=new SoftAssert();
			sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);
			sa.assertEquals(res.then().body(("validity_flag"), is("Vaalid")) ,true);
			sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);*/

					
			test.pass("Passed in report");
					
		}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
						
  }
 
 
 @Test (priority=11)
 public void invalidBulkInd(){
		test=extent.createTest("TC04-PostRevenueValidateRevenueBundle", "verify Bulk Indicators S or B or W only included in the request-400");
		
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			
			String sharedKey=QueryParamOIS.SharedSecret;

			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"EE\","
					+ "\"printFlag\":\"Y\","
					+ "\"digitalFlag\":\"N\","
					+ "\"term\":13,"
					+ "\"bundleId\":18,"
					+ "\"bulkIndicator\":\"p\"}");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
			
			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 400);
	
		/*	SoftAssert sa=new SoftAssert();
			sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);
			sa.assertEquals(res.then().body(("validity_flag"), is("Vaalid")) ,true);
			sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);*/

					
			test.pass("Passed in report");
					
		}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
		
				
 }
  
 
 @Test(priority=11)
 public void revBundleNotwithBulkInd(){
		test=extent.createTest("TC05-PostRevenueValidateRevenueBundle", "verify Revenue Bundle does not contain appropriate Bulk Indicator-104");
		
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			
			String sharedKey=QueryParamOIS.SharedSecret;

			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"AS\","
					+ "\"printFlag\":\"Y\","
					+ "\"digitalFlag\":\"N\","
					+ "\"term\":2,"
					+ "\"bundleId\":3050,"
					+ "\"bulkIndicator\":\"B\"}");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
			
			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 104);
			APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "Revenue Bundle does not contain appropriate Bulk Indicator");
	
		/*	SoftAssert sa=new SoftAssert();
			sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);
			sa.assertEquals(res.then().body(("validity_flag"), is("Vaalid")) ,true);
			sa.assertEquals(res.then().body(("validity_flag"), is("Valid")) ,true);*/

					
			test.pass("Passed in report");
					
		}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
		
				
 }
	


}
