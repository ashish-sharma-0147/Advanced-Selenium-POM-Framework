package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static File file;
	private static FileInputStream iStream;
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	private static Row row;
	private static final String Path_To_Excel = (System.getProperty("user.dir")+ "/src/main/java/com/qa/testdata/");

	public static String getCellValue(String fileName, String sheetName, int rowNum, int colNum) {

		if(rowNum==0)
		{
			System.out.println("****RowNumber cannot be 0****\n");
		}else
		{
			try {
				workbook = setUpWorkbook(fileName);
				sheet = workbook.getSheet(sheetName);
				if (sheet.getLastRowNum()<1)
				{
					throw new Exception("No rows present in sheet *"+sheetName+"*");
				}else 
				{
					row = sheet.getRow(rowNum);
				}
				workbook.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return row.getCell(colNum).toString();
		
	}
	
	public static XSSFWorkbook setUpWorkbook(String fileName) {
		try {
		file =    new File(Path_To_Excel+fileName);
		iStream = new FileInputStream(file);
		workbook = new XSSFWorkbook(iStream);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return workbook;
		
	}
	
	public static void setRow(String fileName, String sheetName,int totalCells, List<String> values) {
		try {
			workbook = setUpWorkbook(fileName);
			sheet = workbook.getSheet(sheetName);
			int EntryRow = sheet.getLastRowNum()+1;
			XSSFRow row = sheet.createRow(EntryRow);
			for(int i=0 ; i < totalCells; i++) {
			(row.createCell(i)).setCellValue(values.get(i));
			FileOutputStream fos = new FileOutputStream(Path_To_Excel+fileName);
			workbook.write(fos);
			}
			workbook.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	public static void getDataForIteration(String SheetName) {
//		ArrayList<Object[]> object = new ArrayList<Object[]>();
//		
//		workbook = setUpWorkbook();
//		sheet = workbook.getSheet(SheetName);
//		
//		for(int i=1; i<=sheet.getLastRowNum(); i++) {
//			
//		}
//	}
//	
//	
//	public Object[][] testData(String excelPath, String sheetName) {
//
//        Object[][] data = null;
//
//        try {
//            ExcelUtil excelUtil = new ExcelUtil(excelPath, sheetName);
//
//            XSSFWorkbook workbook = setUpWorkbook();
//            XSSFSheet sheet = workbook.getSheet(sheetName);
//            int rowCount = workbook.getRowCount();
//            int colCount = excelUtil.getColCount();
//
//            data = new Object[rowCount - 1][colCount];
//
//            for (int i = 1; i < rowCount; i++) {
//
//                Row row = sheet.getRow(i);
//                for (int j = 0; j < colCount; j++) {
//
//                    Cell cell = row.getCell(j);
//                    switch (cell.getCellType()) {
//
//                        case STRING:
//                            data[i - 1][j] = excelUtil.getCellDataString(i, j);
//                            break;
//
//
//                        case NUMERIC:
//                            data[i - 1][j] = String.valueOf(excelUtil.getCellDataNumeric(i, j));
//                            break;
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            LOGGER.error("Error while loading data from Excel file: " + ex.getMessage());
//        }
//
//        return data;
//    }
//	
//	@DataProvider
//	public static void getCertificateCodeToVerify() {
//		
//	}
//	
//	
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//below are unused for now 
	
	public static int getRowNumForRowName(String sheetname,String rowName) {
		int rownum=0;
		sheet=workbook.getSheet(sheetname);
		for(int i=1;i<=getLastRowNum(sheetname);i++) {
			if(rowName.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
				rownum=i;
				break;
			}
		}

		return rownum;
	}

	/*
	 * Takes columnname and sheetname as parameter
	 * return column number based of columnheader
	 */

	public static int getColumnNumForColumnName(String sheetname, String columnname) {
		int colnum=0;
		sheet=workbook.getSheet(sheetname);
		for(int i=0;i<getLastColumnNum(sheetname, 0);i++) {
			if(columnname.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
				colnum=i;
				break;
			}
		}

		return colnum;

	}


	/*
	 * Takes sheetname as parameter
	 * return last row number of the sheet
	 */
	public static int getLastRowNum(String sheetname) {
		return workbook.getSheet(sheetname).getLastRowNum();
	}

	/*
	 * Takes sheetname, row number as parameter
	 * return last cell number of the row
	 */
	public static int getLastColumnNum(String sheetname, int rownum) {
		return workbook.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}


	/*
	 * Takes sheetname, row number, column number as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,int rownum,int colnum) {
		sheet=workbook.getSheet(sheetname);
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue().concat("").toString();


	}

	/*
	 * Takes sheetname, row number, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,int rownum,String columnname) {
		sheet=workbook.getSheet(sheetname);
		return sheet.getRow(rownum).getCell(getColumnNumForColumnName(sheetname, columnname)).getStringCellValue().concat("").toString();

	}

	/*
	 * Takes sheetname, row name, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,String rowname,String columnname) {
		sheet=workbook.getSheet(sheetname);
		int rownum=getRowNumForRowName(sheetname, rowname);
		int colnum=getColumnNumForColumnName(sheetname, columnname);
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue().concat("").toString();

	}



}
