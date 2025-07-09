/**
 * 
 */
package com.qa.retryAnalyzer;


import java.util.logging.Logger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author sharmaa11
 *
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private static int MAX_RETRY = 1;
	private 	   int count = 0;
	private Logger logs = Logger.getLogger("logs");
	
	public boolean retry(ITestResult result) {
		if (!result.isSuccess ()) {
			if (this.count < MAX_RETRY) {
				logs.info("<<<<< Re-running test case: '" + result.getMethod().toString().substring(0, result.getMethod().toString().indexOf("[")) + "' for " + (this.count + 1)
	                    + " times out of " + MAX_RETRY + " >>>>>");
				this.count++;
	            return true;
			}
		}
		return false;
	}

}
