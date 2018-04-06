package utils;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restAPIConstants.EndPointOIS;
import restAPIConstants.QueryParamOIS;

public class OISutils {
	
	public static Response requestPostRevenueValidatePremium(RequestSpecification reqSpec1, String sharedKey1, String magcode, String premOffer, String premId, String aamValueType,String lblStock, String handlCode)
	{	Response resp = null;
		try{
			reqSpec1.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+sharedKey1+"\","
				+ "\"magCode\":\""+magcode+"\","
				+ "\"premiumOffer\":\""+premOffer+"\","
				+ "\"premiumId\":\""+premId+"\","
				+ "\"aamValueType\":\""+aamValueType+"\","
				+ "\"labelStock\":\""+lblStock+"\","
				+ "\"handlingCode\":\""+handlCode+"\"}").log().all();
			given().spec(reqSpec1).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
		
		//given().spec(reqSpec1).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200);
		resp=given().spec(reqSpec1).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
	    return resp;
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			return resp;
		}
		
		
	}
	
	
	public static Response	requestPostRevenueValidatePremium1(RequestSpecification reqSpec1, String[] strBody)
	{	Response resp = null;
		try{
			reqSpec1.given().contentType(ContentType.JSON).with().body("{ \"Shared_Secret\" : \""+strBody[0]+"\","
				+ "\"magCode\":\""+strBody[1]+"\","
				+ "\"premiumOffer\":\""+strBody[2]+"\","
				+ "\"premiumId\":\""+strBody[3]+"\","
				+ "\"aamValueType\":\""+strBody[4]+"\","
				+ "\"labelStock\":\""+strBody[5]+"\","
				+ "\"handlingCode\":\""+strBody[6]+"\"}").log().all();
			given().spec(reqSpec1).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200).log().all();
		
		//given().spec(reqSpec1).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200);
		resp=given().spec(reqSpec1).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM);
	    return resp;
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			return resp;
		}
		
		
	}
	
	
		public static Response	requestPostRevenueValidatePremium22(RequestSpecification reqSpec1, String strBody,String endPt)
		{	Response resp = null;
			try{
				reqSpec1.given().contentType(ContentType.JSON).with().body(strBody).log().all();
				given().spec(reqSpec1).post(endPt).then().statusCode(200).log().all();
			
			//given().spec(reqSpec1).post(EndPointOIS.POST_REVENUEVALIDATEPREMIUM).then().statusCode(200);
			resp=given().spec(reqSpec1).post(endPt);
		    return resp;
			}
			catch (Exception e){
				System.out.println(e.getMessage());
				return resp;
			}
			
			
		}
	
		public static Response	requestGetRevenueValidatePremium22(RequestSpecification reqSpec1, HashMap<String, String> map , String methodName)
		
		{   
			 Response resp = null;
				  try{			
						/* HashMap<String, String> parameters = new HashMap<String, String>();
						 for(Map.Entry<String, String> entry:map.entrySet()){    
						     parameters.put(entry.getKey(), entry.getValue()); 
						      }*/
						 
						 resp=reqSpec1.given().params(map).get(methodName);
			         }
				  catch (Exception e){
					System.out.println(e.getMessage());
					  }
			  return resp;
			
		}
	
	

}
