package com.qa.driver;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.openqa.selenium.WebDriver;

public interface IDriver {

	org.slf4j.Logger logs = getLogger(lookup().lookupClass());
    WebDriver createInstance(String browser);
}
