package ois.testSuite;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class DummyPrakash {

	
	@Test
    public void test()
    {
          //String sharedKey=QueryParamOIS.SharedSecret;
          RestAssured.baseURI="https://qa-lucie.timeinc.com/webservices/";
          RestAssured.given().accept("Content-Type:application/json\r\n").param("userid","darth_vader_inactive@test.com").param("magcode", "ZPP");
          RequestSpecification requestSpecification=getRequestSpecification().log().all(); 

          requestSpecification.given().contentType(ContentType.JSON).with().body("{ \"password\" : \"Welcome123\"}").

          /*
          + "\"printFlag\":\"Y\","
          + "\"digitalFlag\":\"N\","
          + "\"term\":10,"
          + "\"bundleId\":18,"
          + "\"bulkIndicator\":\"S\"}");*/

          given().spec(requestSpecification).post("customers").then().log().all();
    }


    public static RequestSpecification getRequestSpecification(){

          //return RestAssured.given().contentType("application/json\r\n");  //odata=verbose

          return RestAssured.given().contentType("application/json:charset-utf-8").accept	("application/json");

    }
    
    
    

}
