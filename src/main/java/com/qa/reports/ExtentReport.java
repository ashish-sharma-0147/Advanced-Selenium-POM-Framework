package com.qa.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	private volatile static ExtentReports extent;
	public static String fileName = getReportName();
	
	private ExtentReport() {
		
	}
	
	public static ExtentReports getInstance() {
		if (extent == null) {
			synchronized (ExtentReport.class) {
				if (extent == null) {
					String directory = System.getProperty("user.dir") + "/TestReports/";
					new File(directory).mkdir();
					String path = directory + fileName;
					ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
					htmlReporter.config().setEncoding("utf-8");
					htmlReporter.config().setDocumentTitle("Test Automation Report");
					htmlReporter.config().setReportName("FROST UI Automation Report");
					htmlReporter.config().setTheme(Theme.DARK);
					extent = new ExtentReports();
					extent.attachReporter(htmlReporter);
				}
			}
		}
			

        return extent;
    }

	public static String getReportName()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		Date date = new Date();
		String currentDate = formatter.format(date);
		String fileName = "ExecutionReport_"+currentDate + ".html";
		return fileName;
		
	}
}
