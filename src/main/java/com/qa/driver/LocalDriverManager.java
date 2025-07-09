package com.qa.driver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalDriverManager implements IDriver {
	protected OptionsManager optionsManager = new OptionsManager();
	private WebDriver driverInstance;
	@Override
	public WebDriver createInstance(String browser) {
		if (driverInstance != null) {
            return driverInstance;
        }
		
		try {
			if(browser.equalsIgnoreCase("chrome")) 
			{
				WebDriverManager.chromedriver().setup();
				driverInstance= new ChromeDriver(optionsManager.getChromeOptions());	
			}else if(browser.equalsIgnoreCase("firefox")) 
			{
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_PROFILE,"/dev/null");
				WebDriverManager.firefoxdriver().setup();
				Map<String, String> environment = new HashMap<>();
				environment.put("DISPLAY", ":90");
				GeckoDriverService service = new GeckoDriverService.Builder()
				        .usingAnyFreePort()
				        .withEnvironment(environment)
				        .build();
				driverInstance= new FirefoxDriver(service,optionsManager.getFirefoxOptions());	
			}else if(browser.equalsIgnoreCase("edge")) 
			{
				WebDriverManager.edgedriver().setup();
				driverInstance= new EdgeDriver();
			}else if(browser.equalsIgnoreCase("safari")) 
			{
				WebDriverManager.safaridriver().setup();
				driverInstance= new SafariDriver();
			}else
			{
				throw new Exception("Unexpected Value: "+browser);
			}
		}catch (Exception e) 
		{
			e.printStackTrace();
			logs.info(">>>>>Unable to instansiate WebDriver :" +e.getMessage());
		}
		return driverInstance;
		
	}

}
