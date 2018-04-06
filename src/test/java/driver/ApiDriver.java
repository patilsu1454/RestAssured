package driver;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;


import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import restAPIConstants.Constants;
import restAPIConstants.EndPointOIS;
import restAPIConstants.QueryParamOIS;
import utils.GenericUtils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ApiDriver extends GenericUtils{
	
	
	@BeforeSuite
	public void startUp(ITestContext contxt){
		
        EncoderConfig encdonfig	=EncoderConfig.encoderConfig().encodeContentTypeAs("ContentType.josn", ContentType.TEXT);
		given().config(RestAssured.config().encoderConfig(encdonfig));  
		
		if(contxt.getName().equalsIgnoreCase("PostValidateRevenueBundle"))
		{
		RestAssured.baseURI=Constants.OISAPI_BASEURI;
		
		}else if(contxt.getName().equalsIgnoreCase("PostValidateRevenueBundle01")){
			RestAssured.baseURI=Constants.OISAPI_BASEURI;	
		}else {
			System.out.println("Incorrect BaseURI has been set");
		}
		
		System.out.println("***Before Suite");
		startExtentReport();
		System.out.println("Report initiated:");
				
	}
	
	public static RequestSpecification getRequestSpecification(){
		
		return RestAssured.given().contentType("application/json : charset-utf-8");
	
	}
	
	@AfterMethod
    public void getResult(ITestResult result, ITestContext contxt)
    {
		System.out.println("The execution of " +contxt.getCurrentXmlTest().getXmlClasses() +" : Method : " +result.getName() + " is Completed ");
		System.out.println("********************************************************************");
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
	

	@BeforeTest
	public void logForTestGroupsBeforeExecution(ITestContext contxt){
		test=extent.createTest("Test Group name that STARTED execution now is :", contxt.getName());
		System.out.println("Test Group name that STARTED execution now is :" +contxt.getName());
		
		
	}
	@AfterTest
	public void logForTestGroups(ITestContext contxt){
		test=extent.createTest("Test Group name that COMPLETED execution now is :", contxt.getName());
		System.out.println("Test Group name that COMPLETED execution now is :" +contxt.getName());
		
		
	}
	
	@AfterSuite
	public void tearDown(){
		
		System.out.println("***After Suite method");
		extent.flush();
		
	
	}
	
}
