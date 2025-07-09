package com.qa.driver;


import static com.qa.config.ConfigurationManager.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class OptionsManager {

    /** Get Chrome Options */
    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (configuration().isHeadless().equals(true)) {
        	options.addArguments("--window-size=1920,1080");
        	options.addArguments("--start-maximized");
        	options.addArguments("--kiosk");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless=new");
            options.addArguments("--disable-web-security");
            options.addArguments("--disable-extensions");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--remote-allow-origins=*");
        } else {
            options.addArguments("--disable-web-security");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--remote-allow-origins=*");
        }
        return options;
    }

    /** Get FireFox Options */
    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (configuration().isHeadless().equals(true)) {
        	options.addArguments("--headless");
        }
        options.setAcceptInsecureCerts(true);
        options.setCapability("moz:webdriverClick", false);
        
        return options;
    }

    /** Get Safari Options */
    public SafariOptions getSafariOption() {
        SafariOptions options = new SafariOptions();     
        options.setCapability("Platform", options.getPlatformName());
        options.setCapability("browser", options.getBrowserName());
        options.setCapability("browser_version", options.getBrowserVersion());
        return options;
    }

    /** Get Edge Options */
    public EdgeOptions getEdgeOption() {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("Platform", options.getPlatformName());
        options.setCapability("browser", options.getBrowserName());
        options.setCapability("browser_version", options.getBrowserVersion());
        return options;
    }
}
