package com.qa.base;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import com.qa.driver.DriverManager;
import com.qa.util.DriverActions;

import io.github.sukgu.support.ElementFieldDecorator;

public class PageBase extends DriverActions {
	/*
	 * 1. All the page classes must extend this class to initialize the pageFactory
	 * elements. 
	 * 2. The other way around is that the page classes extending the common
	 * page and the common page extending this class to initialize the page
	 * elements.
	 * 3. Currently in the framework point 2 is implemented.
	 */
	
	protected PageBase() {
		ElementFieldDecorator decorator = new ElementFieldDecorator(new DefaultElementLocatorFactory(DriverManager.getInstance().getDriver()));
		PageFactory.initElements(decorator, this);
	}
}
