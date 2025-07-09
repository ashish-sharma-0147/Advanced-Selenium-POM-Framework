package com.qa.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
private volatile static DriverManager instance = new DriverManager();

	private DriverManager() {
		
	}
	
	public static DriverManager getInstance() {
		if (instance == null) {
			synchronized (DriverManager.class) {
				if (instance == null) {
					instance = new DriverManager();
				}
			}
		}
		return instance;
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void setWebDriver(WebDriver driver) {
		DriverManager.driver.set(driver);
	}
	
	public void quit() {
		if (DriverManager.driver.get() != null) {
        	DriverManager.driver.get().quit();
        	driver.remove();
		}
    }

}
