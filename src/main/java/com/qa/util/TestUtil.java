package com.qa.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;
import com.qa.driver.DriverManager;

public class TestUtil {

	public static long PAGE_LOAD_TIMEOUT = 50;
	public static long IMPLICIT_WAIT = 0;
	public static Faker faker = new Faker();

	static DriverActions driverActions;

	private static final String underscore = "_";
	private static final String atTheRate = "@";
	private static final String dotCom = ".com";
	public static final String White_Space = " ";
	public static final String blank_string = "";
	private static final String Table_rowBeforeXpath = "//table[@class='pattern-list-table']/tbody/tr[@class='ng-scope'][";
	private static final String Table_afterXpath  = "]";	
	private static final String Table_colBeforeXpath = "//td[";

	/**
	 *  Get Total Count from Table Entries Text 
	 *  */
	public static int getTotalCount(WebElement element) {
		String TableEntriesText = element.getText();
		TableEntriesText = TableEntriesText.replaceAll("[^-?0-9]+", " ");
		int TotalTableEntries = Integer.parseInt(Arrays.asList(TableEntriesText.trim().split(" ")).get(2));
		return TotalTableEntries;
	}
	
	/**
	 *  Get Current Page Max Table Entries Text 
	 *  */
	public static int getCurrentMaxCount(WebElement element) {
		String TableEntriesText = element.getText();
		TableEntriesText = TableEntriesText.replaceAll("[^-?0-9]+", " ");
		int TotalTableEntries = Integer.parseInt(Arrays.asList(TableEntriesText.trim().split(" ")).get(1));
		return TotalTableEntries;
	}

	public static String getRandomSpecialChar(int length) {
		final String charSet = "~`!@#$%^&*()_-+={[}]?/>.<\\,;:|";
		final int N = charSet.length();
		Random rd = new Random();
		int iLength = length;
		StringBuilder sb = new StringBuilder(iLength);
		for (int i = 0; i < iLength; i++) {
			sb.append(charSet.charAt(rd.nextInt(N)));
		}
		return sb.toString();
	}

	/** Validating Date Format for "MMM dd, yyyy" using regular expression */
	public static boolean validateDateFormat(String date) {
		String[] part = date.trim().replace(",", "").split(" ");
		String mon = part[0];
		String dd = part[1];
		String year = part[2];
		System.out.println("DD: " + dd + " Mon: " + mon + " Year: " + year);
		if (mon.matches(
				"^(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|June?|July?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?)$")
				&& dd.matches("0[1-9]|[12][0-9]|3[01]") && year.matches("(19|20)\\d\\d")) {
			System.out.println("Date Format matched.");
			return true;
		} else {
			System.out.println("Date Format didn't matched.");
			return false;
		}
	}

	public static String getRandomEmail(String Domain) {
		return faker.lorem().word().trim() + faker.number().randomDigit() + underscore
				+ faker.animal().name().trim().replace(White_Space, blank_string) + underscore
				+ faker.number().randomDigit() + atTheRate + Domain + dotCom;
	}

	public static String getRandomAlphanumericPassword() {
		return faker.lorem().characters(4, 8).replace(White_Space, blank_string)
				+ faker.lorem().characters(2, 7).replace(White_Space, blank_string).toUpperCase()
				+ getRandomSpecialChar(1) + faker.number().randomDigit();
	}

	public static String getRandomString(int min, int max) {
		return TestUtil.faker.lorem().characters(min, max).replace(TestUtil.White_Space, TestUtil.blank_string);
	}
	/**
	 * Get Random Date in MM/dd/yyyy format
	 * @return
	 */
	public static String getRandomDate(String DateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
		return sdf.format(faker.date().birthday());
	}


	public static String getCellValueByCellElement(List<WebElement> RowList, int ColumnToIterate, String CellValueToCompare, int TargetColumn) throws Exception {
		int rowSize = RowList.size();
		String values = null;
		for(int i =1; i <= rowSize ; i++) {
			String columnXpath = Table_rowBeforeXpath + i + Table_afterXpath + Table_colBeforeXpath + ColumnToIterate + Table_afterXpath;
			WebElement CellElement = DriverManager.getInstance().getDriver().findElement(By.xpath(columnXpath));
			driverActions.click(CellElement);
			if(CellElement.getText().equals(CellValueToCompare))
			{
				String FinalXpath = Table_rowBeforeXpath + i + Table_afterXpath + Table_colBeforeXpath + TargetColumn + Table_afterXpath;
				WebElement ActionElement = DriverManager.getInstance().getDriver().findElement(By.xpath(FinalXpath));
				values = driverActions.getText(ActionElement);
				break;
			}		
		}
		return values;
	}

	public static void selectButtonByCellValue(List<WebElement> RowList,int ColumnToIterate, String CellValueToCompare, int TargetButtonColumnNumber, String TargetButtonPartialXpath ) throws Exception {
		int rowSize = RowList.size();
		for(int i =1; i <= rowSize ; i++) {
			String columnXpath = Table_rowBeforeXpath + i + Table_afterXpath + Table_colBeforeXpath + ColumnToIterate + Table_afterXpath;
			WebElement CellElement = DriverManager.getInstance().getDriver().findElement(By.xpath(columnXpath));
			driverActions.click(CellElement);
			if(CellElement.getText().equals(CellValueToCompare))
			{
				String FinalXpath = Table_rowBeforeXpath + i + Table_afterXpath + Table_colBeforeXpath + TargetButtonColumnNumber + Table_afterXpath + TargetButtonPartialXpath;
				WebElement ActionElement = DriverManager.getInstance().getDriver().findElement(By.xpath(FinalXpath));
				driverActions.click(ActionElement);
				break;
			}		
		}

	}

	public static  List<String> getAllCellValuesByCellElement(List<WebElement> RowList, int ColumnToIterate) throws Exception {
		int rowSize = RowList.size();
		List<String> values = new ArrayList<String>();
		for(int i =1; i <= rowSize ; i++) {
			//String columnXpath = Table_rowBeforeXpath + i + Table_afterXpath + Table_colBeforeXpath + ColumnToIterate + Table_afterXpath;
			//WebElement CellElement = DriverManager.getDriver().findElement(By.xpath(columnXpath));

			String ActionXpath = Table_rowBeforeXpath + i + Table_afterXpath + Table_colBeforeXpath + ColumnToIterate + Table_afterXpath;
			WebElement ActionElement = DriverManager.getInstance().getDriver().findElement(By.xpath(ActionXpath));
			values.add(driverActions.getText(ActionElement));

		}
		return values;
	}

	/** This is not tested. Should be used or removed in future
	 * @throws Exception */
	public static String verifyValuePresentInList(List<WebElement> element, String value) throws Exception {
		int flag = 0;
		Iterator<WebElement> itr = element.iterator();
		while (itr.hasNext()) {

			WebElement e = itr.next();
			if (e.getText().equalsIgnoreCase(value)) {
				driverActions.click(e);
				flag = flag + 1;
				return value+" is present in the list ";
			}
		}
		if (flag == 0) {

			return ("Element with value '" + value + "' NotFound in list");
		}else {
			return null;
		}

	}

	/** Get total file count from given directory */
	public static int getFileCountInDirectory(String dirPath) {
		File dir = new File(dirPath);
		return dir.listFiles().length;
	}
	
	public static String removeDuplicateWords(String input)
    {
        // Regex to matching repeated words.
        String regex
            = "\\b(\\w+)(?:\\W+\\1\\b)+";
        Pattern p
            = Pattern.compile(
                regex,
                Pattern.CASE_INSENSITIVE);
 
        // Pattern class contains matcher() method
        // to find matching between given sentence
        // and regular expression.
        Matcher m = p.matcher(input);
 
        // Check for subsequences of input
        // that match the compiled pattern
        while (m.find()) {
            input
                = input.replaceAll(
                    m.group(),
                    m.group(1));
        }
        return input;
    }
	
	public static String updateFilePathSeparatorBasedOnOS(String filePath) {
		return filePath.replace("/", File.separator);
	}
	
	public static String encodeToBase64(String textTOEncode) {
		return new String(Base64.getEncoder().encode(textTOEncode.getBytes()));
	}
	
	public static String decodeBase64(String encodedText) {
		return new String(Base64.getDecoder().decode(encodedText.getBytes()));
	}
	
	public static ArrayList<String> getArrayListWithRandomString(int size) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			list.add(faker.lorem().word()+faker.cat().name()+faker.animal().name());
		}
		return list;
	}
	
}