package proj_package.Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import proj_package.Report.ExtentReport;

public class ListenersClass implements ITestListener {

	
	ExtentReport reportObj=new ExtentReport();
	ExtentReports extent=reportObj.reportConfig();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		test.fail(result.getThrowable());
	}
	
	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
		
	}
	
}
