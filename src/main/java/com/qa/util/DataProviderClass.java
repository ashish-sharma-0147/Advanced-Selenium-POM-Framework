package com.qa.util;

import java.util.Arrays;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.qa.testdata.ProjectType;

public class DataProviderClass {
	
	@DataProvider (name = "ProjectTypes")
	private Iterator<Object[]> getAllProjectTypes() {
		return Arrays.asList(
			      new Object[]{ProjectType.EDUPUB.toString()},
			      new Object[]{ProjectType.ObjectiveOrganized.toString()},
			      new Object[]{ProjectType.BookOrganized.toString()},
			      new Object[]{ProjectType.AdaptiveLearning.toString()}
			  ).iterator();
	}
}
