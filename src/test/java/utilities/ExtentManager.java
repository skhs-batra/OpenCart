package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbases.BaseClass;

public class ExtentManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	 // Called before any test method runs in the suite
    public void onStart(ITestContext testcontext) {
    	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	repName="Test-report- "+timeStamp+".html";
    	sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);
    	sparkReporter.config().setDocumentTitle("opencart Automation Report");
    	sparkReporter.config().setReportName("opencart Functional Testing");
    	sparkReporter.config().setTheme(Theme.DARK);
    	
    	extent=new ExtentReports();
    	extent.attachReporter(sparkReporter);
    	extent.setSystemInfo("Application", "opencart");
    	extent.setSystemInfo("Module", "Admin");
    	extent.setSystemInfo("Sub Module", "Customers");
    	extent.setSystemInfo("User Name", System.getProperty("user.name"));
    	extent.setSystemInfo("Environment", "QA");
    	
    	//String os=testcontext.getCurrentXmlTest().getParameter("os");
    	//extent.setSystemInfo("Operating System", os);
    	
    	String browser=testcontext.getCurrentXmlTest().getParameter("browser");
    	extent.setSystemInfo("Browser", browser);
    	
    	List<String> includedGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
    	if(!includedGroups.isEmpty()) {
    		extent.setSystemInfo("Groups", includedGroups.toString());
    	}
    	}

	    // Called when a test case passes
	    public void onTestSuccess(ITestResult result) {
	    	test=extent.createTest(result.getTestClass().getName());
	    	test.assignCategory(result.getMethod().getGroups());
	    	test.log(Status.PASS,result.getName()+" got Successfully executed");
	    }

	    // Called when a test case fails
	    public void onTestFailure(ITestResult result) {
	    	test=extent.createTest(result.getTestClass().getName());
	    	test.assignCategory(result.getMethod().getGroups());
	    	
	    	test.log(Status.FAIL,result.getName()+" got failed");
	    	test.log(Status.INFO,result.getThrowable().getMessage());
	    	
	    	try {
	    		String imgPath=new BaseClass().captureScreen(result.getName());
	    		test.addScreenCaptureFromPath(imgPath);
	    		
	    }
	    	catch(IOException e) {
	    		e.printStackTrace();
	    		
	    	}
	    }

	    // Called when a test case is skipped
	    public void onTestSkipped(ITestResult result) {
	    	
	     test=extent.createTest(result.getTestClass().getName());
	     test.assignCategory(result.getMethod().getGroups());
	     test.log(Status.SKIP, result.getName()+" got skipped");
	     test.log(Status.INFO, result.getThrowable().getMessage());
	    	
	    }
	    
	    // Called after all test methods in the suite have run
	    public void onFinish(ITestContext context) {
	    	
	    	extent.flush();
	    	String pathOfExtentReport=System.getProperty("user.dir")+"/reports/"+repName;
	    	File extentReport=new File(pathOfExtentReport);
	    	try {
	    		Desktop.getDesktop().browse(extentReport.toURI());
	    	}
	    	catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    
	    	
	    }
	    
	    	
	    	
	    

	   

	    
	    

	   
	


}
