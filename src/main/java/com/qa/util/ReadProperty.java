package com.qa.util;

import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {
	
	private Properties properties;

	   public ReadProperty(String propertyFileName) {
	        InputStream is = getClass().getClassLoader()
	            .getResourceAsStream(propertyFileName);
	        this.properties = new Properties();
	        try {
				this.properties.load(is);
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
	    }

	    public String getProperty(String propertyName) {
	        return this.properties.getProperty(propertyName);
	    }
	    

}