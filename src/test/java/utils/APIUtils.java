package utils;

import static org.hamcrest.Matchers.*;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;

import com.relevantcodes.extentreports.LogStatus;





import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.fge.jsonschema.report.LogLevel;

public class APIUtils extends GenericUtils{
	
	
	public static void verifyResponseBodyfieldsIs(Response resp ,String jsonKey, String JsonValue)
	{
		try{
			resp.then().body((jsonKey), is(JsonValue));
			//logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
			//	GenericUtils.logger = extent.startTest("passTest");
			//GenericUtils.logger(LogStatus.PASS,"Pass");
			System.out.println("Passed step for Key :"+jsonKey +" is :"+JsonValue);
			test.log(Status.PASS, MarkupHelper.createLabel("Test step for key: "+jsonKey+" and value: "+JsonValue+ " PASSED", ExtentColor.GREEN));
			} 
		
		catch(AssertionError e){
			System.out.println("AssertionError*** :  " +e.getMessage());
			test.log(Status.FAIL, MarkupHelper.createLabel("Test step for key: "+jsonKey+" and value: "+JsonValue+ " FAILED", ExtentColor.RED));
			}
	}
	
	public static void verifyResponseBodyfieldsHasItem(Response resp ,String jsonKey, String JsonValue)
	{
		try{
			resp.then().body((jsonKey), hasItem(JsonValue));
			System.out.println("Passed step for Key :"+jsonKey +" hasItem :"+JsonValue);
			test.log(Status.PASS, MarkupHelper.createLabel("Test step for key: "+jsonKey+" and has item: "+JsonValue+ " PASSED", ExtentColor.GREEN));
			} 
		
		catch(AssertionError e){
			System.out.println("AssertionError*** :  " +e.getMessage());
			test.log(Status.FAIL, MarkupHelper.createLabel("Test step for key: "+jsonKey+" and value: "+JsonValue+ " FAILED", ExtentColor.RED));
			}
	}

	//test it
	public static void verifyResponseBodyfieldsHasItems(Response resp ,String jsonKey, int[] JsonValue)
	{
		try{
				for(int i=0; i<JsonValue.length; i++){
				resp.then().body((jsonKey), hasItem(JsonValue[i]));
				System.out.println("Passed step for Key :"+jsonKey +" hasItems :"+JsonValue[i]);
				}
			} 
		
		catch(AssertionError e){
			System.out.println("AssertionError*** :  " +e.getMessage());
					}
	}

		
	
	public static void verifyResponseBodyEqualTo(Response resp ,String jsonKey, int JsonValue)
	{
		try{
			resp.then().body((jsonKey), equalTo(JsonValue));
			System.out.println("Passed step for Key :"+jsonKey +" equalTo :"+JsonValue);
			test.log(Status.PASS, MarkupHelper.createLabel("Test step for key: "+jsonKey+" and value: "+jsonKey+ " PASSED", ExtentColor.GREEN));
			} 
		
		catch(AssertionError e){
			System.out.println("AssertionError*** :  " +e.getMessage());
			test.log(Status.FAIL, MarkupHelper.createLabel("Test step for key: "+jsonKey+" and value: "+jsonKey+ " FAILED", ExtentColor.RED));
			}
	}
	
	
	
	
	
	
	
}
