/**
 * 
 */
package com.qa.listeners;

import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlSuite;

/**
 * 
 */
public class AlterSuiteListener implements IAlterSuiteListener {

	@Override
	@Parameters("sdsd")
	public void alter(List<XmlSuite> suites){
		IAlterSuiteListener.super.alter(suites);

		XmlSuite testNGSuite = suites.get(0);
		
		if (System.getProperty("parallel.execution.mode") != null) {
            switch (System.getProperty("parallel.execution.mode")) {
            case "CLASSES":
            	testNGSuite.setParallel(XmlSuite.ParallelMode.CLASSES);
                break;
            case "METHODS":
            	testNGSuite.setParallel(XmlSuite.ParallelMode.METHODS);
                break;
            case "INSTANCES":
            	testNGSuite.setParallel(XmlSuite.ParallelMode.INSTANCES);
                break;
            case "TESTS":
            	testNGSuite.setParallel(XmlSuite.ParallelMode.TESTS);
            	break;
            case "NONE":
            	testNGSuite.setParallel(XmlSuite.ParallelMode.NONE);
                break;
            default:
            	testNGSuite.setParallel(XmlSuite.ParallelMode.NONE);
            }
		}

		testNGSuite.setThreadCount(Integer.parseInt(System.getProperty("parallel.execution.thread.count")));

	}

}
