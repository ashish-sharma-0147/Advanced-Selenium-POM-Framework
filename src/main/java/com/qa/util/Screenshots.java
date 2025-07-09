package com.qa.util;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.driver.DriverManager;

public class Screenshots {

	private static org.slf4j.Logger logs = getLogger(lookup().lookupClass());
	
	public static String takeScreenshot(String methodName)
	{
		String fileName = getScreenshotName(methodName);
		String directory = System.getProperty("user.dir") + "/screenshots/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		try {
			File screenshot = ((TakesScreenshot)DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			logs.info("======================================================================");
			logs.info("Screenshot stored at: " + path);
			logs.info("======================================================================");	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return path;
	}
	
	public static String takeBase64Screenshot() {
		String Base64StringofScreenshot="";
		try {
			;
			File screenshot = ((TakesScreenshot)DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
			Base64StringofScreenshot = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileContent);
			logs.info("======================================================================");
			logs.info("Screenshot taken");
			logs.info("======================================================================");	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Base64StringofScreenshot;
	}
	
	
	private static String getScreenshotName(String methodName)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		Date date = new Date();
		String currentDate = formatter.format(date);
		
		String fileName = methodName+"_"+currentDate + ".png";
		return fileName;
	}
	
	
}
