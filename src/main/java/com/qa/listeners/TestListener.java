package com.qa.listeners;




import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.qa.reports.ExtentManager;
import com.qa.reports.ExtentReport;
import com.qa.util.Screenshots;

public class TestListener implements ITestListener {

	protected static ExtentReports extent = ExtentReport.getInstance();
	//private ExtentTest extentTest = ExtentManager.getInstance().
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public static org.slf4j.Logger logs = getLogger(lookup().lookupClass());

	@Override
	public void onStart(ITestContext context) {
		logs.info("Automation Test : " + context.getName() + " starting............. ");
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		//ExtentTest extest = extent.createTest(result.getTestClass().getName().substring(17) + " :: " + result.getMethod().getMethodName());
		logs.info("Starting Test : " + result.getMethod().getMethodName() + " :............. ");
		ExtentManager.getInstance().setExtent(test.get());
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		logs.info(result.getMethod().getMethodName()+ " Passed! \n");
		String logText = "<b> Thread ID :" + Thread.currentThread().getId() + "_" +"Test Method " + result.getMethod().getMethodName() + " Executed Successfully</b>";
//		try {
//			String path = Screenshots.takeBase64Screenshot();
//			ExtentManager.getInstance().getExtent().pass(MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
//		}catch(Exception e) {
//			ExtentManager.getInstance().getExtent().warning("Unable to attach screenshot \n"+e.getCause());
//		}
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		ExtentManager.getInstance().getExtent().log(Status.PASS,m);
		ExtentManager.getInstance().removeExtentObject();
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		logs.info(result.getMethod().getMethodName()+ " failed! \n");
		String exceptionMessage = (result.getThrowable().getMessage());
		try {
			String path = Screenshots.takeBase64Screenshot();
			ExtentManager.getInstance().getExtent().fail("<details><summary><b><font color=red>" +
					"Exception occured, click to see details:"+ "</font></b></summary> " +
					exceptionMessage.replaceAll(",", "<br>") + "</details> \n",MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
			//ExtentManager.getInstance().getExtent().fail("<b><font color=red>" + "Screenshot of failed test" + "</font></b>",
			//		MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}catch(Exception e) {
			ExtentManager.getInstance().getExtent().warning("Test Failed, unable to attach screenshot \n"+e.getCause());
		}

		String logText = "<b> Thread ID :" + Thread.currentThread().getId() + "_" +"Test Method " + result.getMethod().getMethodName() + "Failed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentManager.getInstance().getExtent().log(Status.FAIL,m);
		ExtentManager.getInstance().removeExtentObject();
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		logs.info(result.getMethod().getMethodName()+ " Skipped! \n");
		String exceptionMessage = (result.getThrowable().getMessage());
		try {
			String path = Screenshots.takeBase64Screenshot();
			ExtentManager.getInstance().getExtent().skip("<details><summary><b><font color=red>" +
					"Test Skipped. Test will rerun if Retry Analyzer is set. \n Click to see the details about the failure:"+ "</font></b></summary> " +
					exceptionMessage.replaceAll(",", "<br>") + "</details> \n",MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
		}catch(Exception e) {
			ExtentManager.getInstance().getExtent().warning("Unable to attach screenshot \n"+e.getCause());
		}
		String logText = "<b>Test Case " + result.getMethod().getMethodName() + "  Skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		ExtentManager.getInstance().getExtent().log(Status.SKIP,m);
		ExtentManager.getInstance().removeExtentObject();
	}


	@Override
	public synchronized void onFinish(ITestContext context) {
		logs.info("Automation Test : " + context.getName() + " ending............... ");
		if(extent!=null) 
		{
			extent.flush();
		}
	}

	public synchronized void stepDetails(String details) throws Exception {
		long threadId = Thread.currentThread().getId();
		try {
			logs.info("Thread Id - {} : {} ",threadId, details);
			//logs.info("Exporting {} as {}", variableName, variableValue);
			if(!Thread.currentThread().getStackTrace()[2].getMethodName().equals("explicitlyWait")) {
				ExtentManager.getInstance().getExtent().log(Status.INFO," Thread Id - "+ threadId +" : " + details);
			}
		} catch (Throwable t) {
			throw new Exception("" + t.getCause());
		}
	}
	
	public void setExtentSystemInfo(String key, String value) {
        extent.setSystemInfo(key, value);
    }


}
