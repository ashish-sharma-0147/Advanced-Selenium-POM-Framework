package com.qa.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private static final ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
	private volatile static ExtentManager instance = new ExtentManager();

	private ExtentManager() {

	}

	public static ExtentManager getInstance() {
		if (instance == null) {
			synchronized (ExtentManager.class) {
				if (instance == null) {
					instance = new ExtentManager();
				}
			}
		}
		return instance;
	}

	public ExtentTest getExtent() {
		return extent.get();

	}

	public void setExtent(ExtentTest extentTest) {
		ExtentManager.extent.set(extentTest);
	}
	
	public void removeExtentObject() {
		extent.remove();
	}

}
