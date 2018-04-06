package ois.testSuite;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashMap;

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
import static org.awaitility.Awaitility.*;

public class OISOblGetObligations extends ApiDriver{
	
	private String endPoint=EndPointOIS.GET_OBLIGATIONS;

@Test (enabled=false)
	
	public void obl_getObligations01(){	
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

			public void obl_getObligations02(){	
				test=extent.createTest("TC00-Get obligations", "obl_getObligations for SI magazine that has items 47,48,49");
				test.pass(endPoint);
				
				HashMap<String, String> hm=new HashMap<String, String>();
				hm.put("username", QueryParamOIS.username);
				hm.put("magCode", "SI");
			
				RequestSpecification reqSpec=ApiDriver.getRequestSpecification();
				Response res=OISutils.requestGetRevenueValidatePremium22(reqSpec,hm,endPoint);
				System.out.println("Response log is a  as below ************");
				res.then().log().all();
				System.out.println("Response log is a  as above ************");
				int a[]={47,48,49};
				APIUtils.verifyResponseBodyfieldsHasItems(res, "Obligations.ObligationID", a);
				
				//res.then().body("Obligations.ObligationID", hasItems(47,48,49)).statusCode(200);  // status code OK
				System.out.println(" ** Passed hasItems for bundleId 47,48,49  **");
						
				
			}


}
