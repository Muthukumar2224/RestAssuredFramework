package api.utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;   //ui
	public ExtentReports extent;    // common info
	public ExtentTest test;
	
	String repName;
	public void onStart(ITestContext testContext) {
	    // not implemented
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timestamp
		repName = "Test-Report-"+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); //location of the report
		sparkReporter.config().setDocumentTitle("Rest Assured Automation Project");  //Title of the report
		sparkReporter.config().setReportName("Pet Store Users API"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Tester Name","Smk");
	  }
	 public void onTestSuccess(ITestResult result) {
		    // not implemented
		test = extent.createTest(result.getName());	
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());		
		test.log(Status.PASS, "Test Passed: "+result.getName());
		  }
	
	 public void onTestFailure(ITestResult result) {
		    // not implemented
		 test = extent.createTest(result.getName());		
			test.createNode(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL, "Test Failed: "+result.getName());
			test.log(Status.FAIL, "Failed Reason: "+result.getThrowable().getMessage());
		  }
	 public void onTestSkipped(ITestResult result) {
		    // not implemented
		 test = extent.createTest(result.getName());
		 test.createNode(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, "Test Skipped: "+result.getName());
		 test.log(Status.SKIP, "Skipped Reason: "+result.getThrowable().getMessage());			
		  }
	 
	 public void onFinish(ITestContext testContext) {
		    // not implemented
		 extent.flush();
		  }
	 
}
