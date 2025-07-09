package com.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.qa.driver.DriverManager;

public class Highlighter {


	public static void highLightElement(WebElement element)	{
		JavascriptExecutor js=(JavascriptExecutor)DriverManager.getInstance().getDriver(); 
		js.executeScript("arguments[0].setAttribute('style', 'background: #e1b382; border: 2px solid #0049B7;');", element);

		try 
		{
			Thread.sleep(2);
		} 
		catch (InterruptedException e) {

			System.out.println(e.getMessage());
		} 

		//js.executeScript("arguments[0].setAttribute('style','border: solid 1px white');", element);
		js.executeScript("arguments[0].setAttribute('style','border-top: 1px solid #cecece;"
				+ "border-left: 1px solid #cecece;"
				+ "border-right: 1px solid #cecece"
				+ "border-bottom: 2px solid #666"
				+ "padding: 4px 10px"
				+ "border-radius: 0');", element);

	}

}
