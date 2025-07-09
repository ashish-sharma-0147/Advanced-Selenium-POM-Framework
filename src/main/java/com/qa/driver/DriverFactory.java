package com.qa.driver;

import static com.qa.config.ConfigurationManager.configuration;

import org.openqa.selenium.WebDriver;

public class DriverFactory implements IDriver {
	public WebDriver createInstance(String browser) {
		WebDriver webdriver ;
		String Mode = configuration().RunMode().toUpperCase(); 	
		
		switch(Mode)
		{
		case "LOCAL":
			webdriver = new LocalDriverManager().createInstance(browser);
			break;
		case "REMOTE":
			//RemoteDriverManager.getDriverBinariesForLocal();
			//RemoteDriverManager.setupGridNodeForLocal();
			webdriver = new RemoteDriverManager().createInstance(browser);			
			break;
		case "AWS":
			webdriver = new AWSDeviceFarmDriverManager().createInstance(browser);
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + Mode);
		}
		
		return webdriver;
		
	}
}
