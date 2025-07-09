package com.qa.util;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.qa.reports.ExtentManager;

public class CustomHardAssert extends Assertion{

	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
//		try {
//			String path = Screenshots.takeBase64Screenshot();
//			ExtentManager.getInstance().getExtent().fail("<details><summary><b><font color=red>" +
//					"Exception occured, click to see details:"+ "</font></b></summary> " +
//					ex.getMessage().replaceAll(",", "<br>") + "</details> \n",MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
//		}catch(Exception e) {
//			ExtentManager.getInstance().getExtent().warning("Test Failed, unable to attach screenshot \n"+e.getCause());
//		}
	}

	@Override
	public void onAssertSuccess(IAssert<?> a) {
		try {
			String path = Screenshots.takeBase64Screenshot();
			ExtentManager.getInstance().getExtent().pass(MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
		}catch(Exception e) {
			ExtentManager.getInstance().getExtent().warning("Unable to attach screenshot \n"+e.getCause());
		}
	}
}