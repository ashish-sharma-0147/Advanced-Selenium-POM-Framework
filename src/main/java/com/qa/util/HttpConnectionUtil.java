package com.qa.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpConnectionUtil {

	private static Logger logs = Logger.getLogger("logs");

	public static List<String> getBrokenLinks(List<String> Links){
		logs.info(" <<<<< Broken Link Verification : Started >>>>>");
		Set<String> uniqueLinks = new HashSet<String>(Links);
		List<String> brokenLinks = new ArrayList<String>();
		uniqueLinks.parallelStream().forEach(link -> brokenLinks.add(isLinkBroken(link)));
		brokenLinks.removeAll(Collections.singleton(null));
		logs.info(" <<<<< Broken Link Verification : Ended >>>>>");
		return brokenLinks;
	}

	/**
	 * Check if Given Link is Broken
	 * @param Link
	 * @return Link , if broken
	 */
	private static String isLinkBroken(String Link) {
		try {
			if (Link != null)
			{
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet(Link);
				HttpResponse response = client.execute(request);
				if (response.getStatusLine().getStatusCode() != 200)
				{
					logs.info(" <<<<< '"+Link+"'  is broken, Status Code = "+response.getStatusLine().getStatusCode()+" >>>>>");
					return Link;
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
