package com.qa.driver;


import static com.qa.config.ConfigurationManager.configuration;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriverManager implements IDriver{
	DesiredCapabilities capabilities = new DesiredCapabilities();
	protected OptionsManager optionsManager = new OptionsManager();
	protected ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();

	@Override
	public WebDriver createInstance(String browser) {
		try {
			switch(browser.toUpperCase())
			{
			case "CHROME":
				capabilities.merge(optionsManager.getChromeOptions());
				webDriver.set(new RemoteWebDriver(new URL(configuration().GridURL()),capabilities));
				webDriver.get().setFileDetector(new LocalFileDetector());
				break;
			case "FIREFOX":
				capabilities.merge(optionsManager.getFirefoxOptions());
				webDriver.set(new RemoteWebDriver(new URL(configuration().GridURL()),capabilities));
				webDriver.get().setFileDetector(new LocalFileDetector());
				//webDriver.set((RemoteWebDriver) WebDriverManager.getInstance(DriverManagerType.FIREFOX).remoteAddress(configuration().GridURL()).capabilities(capabilities).create());
				break;
			case "EDGE":
				capabilities.merge(optionsManager.getEdgeOption());
				webDriver.set(new RemoteWebDriver(new URL(configuration().GridURL()),capabilities));
				webDriver.get().setFileDetector(new LocalFileDetector());
			case "SAFARI":
				capabilities.merge(optionsManager.getSafariOption());
				webDriver.set(new RemoteWebDriver(new URL(configuration().GridURL()),capabilities));
				webDriver.get().setFileDetector(new LocalFileDetector());
			default:
				throw new IllegalStateException("Unexpected value: " + browser);

			}
		}catch(Exception e) {
			//System.out.println("Exception Occured : \n" + e.getMessage());
			logs.info(">>>>>Exception Occured : \n" + e.getMessage());
		}
		return webDriver.get();
	}

	/**
	 * This will download specified driver binaries in Drivers folder
	 */
	/**public static void getDriverBinariesForLocal() {
		//System.setProperty("wdm.targetPath", System.getProperty("user.dir") + "/Standalone-Server");
		try {
		WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		if(!WebDriverManager.edgedriver().getBrowserPath().isEmpty()) {
			WebDriverManager.getInstance(DriverManagerType.EDGE).setup();
		}
		if (Platform.getCurrent().is(Platform.MAC)) {
			WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();
		}
		} catch(Exception e)
		{
			System.out.println(e.getCause());
		}
		//WebDriverManager.seleniumServerStandalone().version(getCONFIG().getProperty("seleniumVersion")).setup();
	}
	
    public static void setupGridNodeForLocal() {
            if (configuration().RunMode().equalsIgnoreCase("remote")) {
            	try { 
                GridLauncherV3.main(new String[] { "-role", "hub", "-port", "4444", "-newSessionWaitTimeout", "-1",
                        "-capabilityMatcher", "org.openqa.grid.internal.utils.DefaultCapabilityMatcher", "-registry",
                        "org.openqa.grid.internal.DefaultGridRegistry", "-throwOnCapabilityNotPresent", "true",
                        "-cleanUpCycle", "5000", "-browserTimeout", "0", "-timeout", "900000" });
                Thread.sleep(5000);
                GridLauncherV3.main(new String[] { "-role", "node", "-hub", "http://localhost:4444/grid/register",
                        "-browser", "browserName=chrome,maxInstances=10,seleniumProtocol=WebDriver", "-maxSession",
                        "10", "-proxy", "org.openqa.grid.selenium.proxy.DefaultRemoteProxy", "-port", "-1",
                        "-registerCycle", "5000", "-nodeStatusCheckTimeout", "5000", "-nodePolling", "5000",
                        "-unregisterIfStillDownAfter", "60000", "-downPollingLimit", "2" });
                Thread.sleep(5000);
                GridLauncherV3.main(new String[] { "-role", "node", "-hub", "http://localhost:4444/grid/register",
                        "-browser", "browserName=firefox,maxInstances=6,seleniumProtocol=WebDriver", "-maxSession",
                        "10", "-proxy", "org.openqa.grid.selenium.proxy.DefaultRemoteProxy", "-port", "-1",
                        "-registerCycle", "5000", "-nodeStatusCheckTimeout", "5000", "-nodePolling", "5000",
                        "-unregisterIfStillDownAfter", "60000", "-downPollingLimit", "2" });
                Thread.sleep(5000);
            	}catch (InterruptedException e) {
            		System.out.println(e.getMessage());
            	}
            }
        }*/
}
