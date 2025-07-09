package com.qa.driver;

import static com.qa.config.ConfigurationManager.configuration;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;


public class AWSDeviceFarmDriverManager implements IDriver {

	@Override
	public WebDriver createInstance(String browser) {
		AwsSessionCredentials awsSessionCred = AwsSessionCredentials.create(configuration().DeviceFarm_AccessKey(),
				configuration().DeviceFarm_SecretKey(), configuration().DeviceFarm_SessionToken());
		DeviceFarmClient client = DeviceFarmClient.builder()
				.credentialsProvider(StaticCredentialsProvider.create(awsSessionCred)).region(Region.US_WEST_2).build();
		CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder().expiresInSeconds(300)
				.projectArn(configuration().DeviceFarm_ProjectARN()).build();

		DesiredCapabilities capabilities;
		RemoteWebDriver driver = null;
		URL testGridUrl = null;
		
		try {
			CreateTestGridUrlResponse response = client.createTestGridUrl(request);
			testGridUrl = new URL(response.url());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				capabilities = new DesiredCapabilities();
				capabilities.setCapability("browserName", "chrome");
				capabilities.setCapability("browserVersion", "latest");
				capabilities.setCapability("platform", System.getProperty("os.name"));
				driver = new RemoteWebDriver(testGridUrl, capabilities);
			}else if(browser.equalsIgnoreCase("firefox")) {
				capabilities = new DesiredCapabilities();
				capabilities.setCapability("browserName", "firefox");
				capabilities.setCapability("browserVersion", "latest");
				capabilities.setCapability("platform", System.getProperty("os.name"));
				driver = new RemoteWebDriver(testGridUrl, capabilities);
			} else if(browser.equalsIgnoreCase("internet explorer")) {
				capabilities = new DesiredCapabilities();
				capabilities.setCapability("browserName", "internet explorer");
				capabilities.setCapability("browserVersion", "latest");
				capabilities.setCapability("platform", System.getProperty("os.name"));
				driver = new RemoteWebDriver(testGridUrl, capabilities);
			}else {
				System.out.println("Device Farm only Supports IE, Chrome and FireFox Browsers");
				throw new IllegalStateException("Unexpected value: " + browser);				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
}
