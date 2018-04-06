package ois.testSuite;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import restAPIConstants.EndPointOIS;
import restAPIConstants.QueryParamOIS;
import utils.APIUtils;
import utils.GenericUtils;
import utils.OISutils;
import driver.ApiDriver;

public class OISDummy extends ApiDriver{
	
	@Test(priority=5)
	  public void premObligationNotExistDummy(){
		test=extent.createTest("TC01-PostRevenueValidatePremium", "verify Premium obligation does not exist");
		test.pass(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
		try{		
			
			JSONObject objJson=GenericUtils.readJson("TestCase00",0,this.getClass().getSimpleName());   //this.getClass().getSimpleName()
		    String strBody=	objJson.toString();
					
			RequestSpecification reqSpec=ApiDriver.getRequestSpecification();
			String endPoint=EndPointOIS.POST_REVENUEVALIDATEPREMIUM;
			Response res=OISutils.requestPostRevenueValidatePremium22(reqSpec,strBody,endPoint);
					
			//Response res=given().spec(reqSpec).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
			
			APIUtils.verifyResponseBodyfieldsIs(res, "validity_flag", "Invalid");
			APIUtils.verifyResponseBodyfieldsIs(res, "Status.Description", "Premium obligation does not exist");
			APIUtils.verifyResponseBodyEqualTo(res, "Status.ErrorCode", 104);
					
			test.pass("Passed in report");
					
			}
		
							catch(AssertionError e){		
							Assert.assertTrue(false, e.getMessage());
						}		
	  }
	
	
	

}
