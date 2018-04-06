package ois.testSuite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TestJsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONParser parser= new JSONParser();
		try {	
	Object obj=null;
	try {
		obj = parser.parse(new FileReader(System.getProperty("user.dir")+"//src//test//java//testDataOIS//test.json"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JSONObject jobj=(JSONObject)obj;
	JSONArray ja=(JSONArray)jobj.get("petHetro");
	JSONObject req=(JSONObject)ja.get(0);
	System.out.println(req.get("city").toString());
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		
	}

}
