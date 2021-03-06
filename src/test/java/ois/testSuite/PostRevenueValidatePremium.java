package ois.testSuite;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

import restAPIConstants.EndPointOIS;
import restAPIConstants.QueryParamOIS;
import utils.APIUtils;
import driver.ApiDriver;

public class PostRevenueValidatePremium extends ApiDriver{
	
	
	@Test(priority=0)
	  public void premObligationNotExist(){
		test=extent.createTest("TC01-PostRevenueValidatePremium", "verify Premium obligation does not exist");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			
			String sharedKey=QueryParamOIS.SharedSecret;

			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"EE\","
					+ "\"premiumOffer\":\"XXXX\","
					+ "\"premiumId\":\"05TK1\","
					+ "\"aamValueType\":\"E\","
					+ "\"labelStock\":\"XXXX\","
					+ "\"handlingCode\":6 }");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Invalid");
			APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "Premium obligation does not exist");
			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 104);
			
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
	
	
	@Test(priority=1)
	  public void premObligationNotExistnn(){
		test=extent.createTest("TC02-PostRevenueValidatePremium", "verify Valid Response");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			String sharedKey=QueryParamOIS.SharedSecret;
			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"TK\","
					+ "\"premiumOffer\":\"XXXX\","
					+ "\"premiumId\":\"TETK1\","
					+ "\"aamValueType\":\"E\","
					+ "\"labelStock\":\"XXXX\","
					+ "\"handlingCode\":6 }");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Valid");
		//	APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "Premium obligation does not exist");
			// APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 104);
			
				test.pass("Passed in report");
			}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
	  }
	
	@Test(priority=2)
	  public void aamValueTypeMisMatch(){
		test=extent.createTest("TC03-PostRevenueValidatePremium", "verify for AAM Value Type mismatch");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			String sharedKey=QueryParamOIS.SharedSecret;
			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"AS\","
					+ "\"premiumOffer\":\"XXXX\","
					+ "\"premiumId\":\"00010\","
					+ "\"aamValueType\":\"E\","
					+ "\"labelStock\":\"XXXX\","
					+ "\"handlingCode\":6 }");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Invalid");
			APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "AAM Value Type mismatch");
			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 105);
			
				test.pass("Passed in report");
			}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
	  }
	  

	
	@Test(priority=3)
	  public void labelStockAndPremType01(){
		test=extent.createTest("TC04-PostRevenueValidatePremium", "verify for labelStock = NONE and premiumType = Virtual ");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			String sharedKey=QueryParamOIS.SharedSecret;
			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"TK\","
					+ "\"premiumOffer\":\"XXXX\","
					+ "\"premiumId\":\"TETK1\","
					+ "\"aamValueType\":\"E\","
					+ "\"labelStock\":\"NONE\","
					+ "\"handlingCode\":6 }");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Valid");
			//APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "AAM Value Type mismatch");
			//APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 105);
			
				test.pass("Passed in report");
			}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
	  }
	
	

	@Test (priority=4)
	  public void labelStockAndPremType02(){
		test=extent.createTest("TC05-PostRevenueValidatePremium", "For labelStock = NONE, handlingCode is Not 6 or 7 and premiumType = Magazine Issue ");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			String sharedKey=QueryParamOIS.SharedSecret;
			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"GF\","
					+ "\"premiumOffer\":\"XXXX\","
					+ "\"premiumId\":\"05GF3\","
					+ "\"aamValueType\":\"E\","
					+ "\"labelStock\":\"NONE\","
					+ "\"handlingCode\":5 }");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Valid");
			//APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "AAM Value Type mismatch");
			//APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 105);
			
				test.pass("Passed in report");
			}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
	  }
	
	
	@Test(priority=5)
	  public void premiumlabelStockAndHandlingCodeMisMatch(){
		test=extent.createTest("TC06-PostRevenueValidatePremium", "Premium Label Stock - Handling Code mismatch-107 ");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			String sharedKey=QueryParamOIS.SharedSecret;
			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"AS\","
					+ "\"premiumOffer\":\"XXXX\","
					+ "\"premiumId\":\"00004\","
					+ "\"aamValueType\":\"E\","
					+ "\"labelStock\":\"NONE\","
					+ "\"handlingCode\":6 }");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Invalid");
			APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "Premium Label Stock - Handling Code mismatch");
			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 107);
			
				test.pass("Passed in report");
			}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
	  }
	
	
	@Test
	  public void premiumTypePhyOrMagIssue(){
		test=extent.createTest("TC07-PostRevenueValidatePremium", "For labelStock = NONE, handlingCode is Not 6 or 7 and premiumType = Magazine or Physical Issue ");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			String sharedKey=QueryParamOIS.SharedSecret;
			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"AS\","
					+ "\"premiumOffer\":\"XXXX\","
					+ "\"premiumId\":\"00004\","
					+ "\"aamValueType\":\"E\","
					+ "\"labelStock\":\"NONE\","
					+ "\"handlingCode\":5 }");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Valid");
			//APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "AAM Value Type mismatch");
			//APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 105);
			
				test.pass("Passed in report");
			}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
	  }
	
	
	@Test
	  public void premiumTypeNotEqualToVirtual(){
		test=extent.createTest("TC08-PostRevenueValidatePremium", "Premium Label Stock - Handling Code mismatch(107) when PremiumType != Virtual");
		test.pass(EndPointOIS.POST_VALIDATEREVENUEBUNDLES);
		try{		
			String sharedKey=QueryParamOIS.SharedSecret;
			RequestSpecification requestSpecification=ApiDriver.getRequestSpecification();				
			requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey+"\","
					+ "\"magcode\":\"TK\","
					+ "\"premiumOffer\":\"XXXX\","
					+ "\"premiumId\":\"0501\","
					+ "\"aamValueType\":\"E\","
					+ "\"labelStock\":\"NONE\","
					+ "\"handlingCode\":6 }");
			
			given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
				
			Response res=given().spec(requestSpecification).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Invalid");
			APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description","Premium Label Stock - Handling Code mismatch");
			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 107);
			
				test.pass("Passed in report");
			}
		catch(AssertionError e){		
			Assert.assertTrue(false, e.getMessage());
		}
	  }
	
	
	
	
	
	
	
}
