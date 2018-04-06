package utils;

import java.io.File;



import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;





import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.LogStatus;

public class GenericUtils {
	
   public static ExtentReports extent;
   public static ExtentTest test;
   public static ExtentHtmlReporter htmlReporter;
   //public static ExtentTest logger;
	
   public static  void startExtentReport(){
	       
	   // refer :http://easybix.com/generate-extent-report-in-selenium-webdriver-using-extent-version-3/
	   	   
	   String curdttm=getCurrentDateTime01();
	       
	        htmlReporter=new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "//ExtentReports//API Report "+curdttm+".html"));
		    //  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Dummyfold/MyOwnReport.html");
		    // htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		    extent = new ExtentReports();
		    // htmlReporter.setAppendExisting(true);
		    htmlReporter.config().setChartVisibilityOnOpen(true);
		    extent.attachReporter(htmlReporter);
		    extent.setSystemInfo("OS", "Windows");
		    extent.setSystemInfo("Host Name", "Sushant");
		    extent.setSystemInfo("Environment", "QA");
		    extent.setSystemInfo("User Name", "CHECK THIS IN  EXTENTREPORT ");
		         
		    htmlReporter.config().setChartVisibilityOnOpen(true);
		    htmlReporter.config().setDocumentTitle("RestAPIReport");
		    htmlReporter.config().setReportName("        REST API AUTOMATION EXECUTION REPORT -TIME INC        ");
		    htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		    htmlReporter.config().setTheme(Theme.STANDARD);
		    
		      
    }
   
   // not used 
   public static String getCurrentDateTime(){
	   String strDate=null;
	   SimpleDateFormat sdf= new SimpleDateFormat("MM-DD-YYYY hh:mm:ss");
	   Calendar cal= Calendar.getInstance();
	  // cal.add(Calendar.MONTH, 1);
	   strDate=sdf.format(cal.getTime());
	   strDate=strDate.replace(':', '-');
	   return strDate;
	   
	      }
   
   
   public static String getCurrentDateTime01(){
	  Date date =new Date();
	  DateFormat sdf= new SimpleDateFormat("dd-MM-yyyy HH-mm");
	 // System.out.println(sdf.format(date));
	  String strDateTime=sdf.format(date);
	  return strDateTime;
	   
	      }
   
   //Parameters testCase Name and index
  public static JSONObject readJson(String testCaseName, int i, String apiTest){
   
	 
	 	  JSONObject  objTestData = null;
	 	  Object obj=null;
	 	  String tcFileName=null;
	    try {
	       JSONParser parser = new JSONParser();
	       
	       if (apiTest.contains("OIS")){
	    	   tcFileName="PostRevenueValidatePremium";
	            }
	       obj = parser.parse(new FileReader(System.getProperty("user.dir") + "//src//test//java//testDataOIS//td"+tcFileName+".json"));
           //  JSONObject jsonObject = (JSONObject)obj ;
            JSONArray ja=(JSONArray)obj;
            JSONObject  jsonObject=  (JSONObject) ja.get(i);
            // System.out.println("jsonObj : " +jsonObject);
         objTestData = (JSONObject)jsonObject.get(testCaseName);  //"TestCase01");  
       
           
            
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
		
	    return objTestData;
			   
   }
   
	
}
