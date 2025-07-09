package com.qa.pages;

import com.qa.pages.common.CommonPage;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExportPage extends CommonPage{
	
	@FindBy(xpath="//div[@class='pull-middle ng-binding']")
	private WebElement Text_PageHeading;
	
	@FindBy(xpath="//a[normalize-space()='Export List']")
	private WebElement Button_Exports;
	
	@FindBy(xpath="//a[normalize-space()='All Active Exports']")
	private WebElement Button_ActiveExports;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//center[normalize-space()='No Active Exports']")
	private WebElement Text_NoActiveExports;
	
	@FindBy(xpath="//div[@id='allactiveexports']/descendant::table[@class='pattern-list-table']/descendant::th")
	private List<WebElement> ActiveExportList_TableHeaders;

	@FindBy(xpath="//div[@id='allactiveexports']/descendant::table[@class='pattern-list-table']/descendant::tbody/tr")
	private List<WebElement> ActiveExportList_TableRows;
	private String xpath_ActiveExportList_TableRows = "//div[@id='allactiveexports']/descendant::table[@class='pattern-list-table']/descendant::tbody/tr";
	
	@FindBy(xpath="//div[@id='exportlist']/descendant::table[@class='pattern-list-table']/descendant::th")
	private List<WebElement> ExportList_TableHeaders;

	@FindBy(xpath="//div[@id='exportlist']/descendant::table[@class='pattern-list-table']/descendant::tbody/tr")
	private List<WebElement> ExportList_TableRows;
	private String xpath_ExportList_TableRows = "//div[@id='exportlist']/descendant::table[@class='pattern-list-table']/descendant::tbody/tr";
	
	@FindBy(xpath="//tbody/tr[1]/td[3]")
	private WebElement Text_Status;
	
	
	public ExportPage() throws InterruptedException{
		waitForPageToCompletelyLoad();
	}
	
	public ExportPage openExportsTab() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(Button_Exports));
		click(Button_Exports);
		//waitUntilLoading();
		return this;
	}
	
	public ExportPage openActiveExportsTab() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(Button_ActiveExports));
		click(Button_ActiveExports);
		Thread.sleep(2000);
		return this;
	}
	
	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}
	
	public List<LinkedHashMap<String, String>> getActiveExportList() throws Exception {
		return getTableDataAsList(ActiveExportList_TableHeaders,ActiveExportList_TableRows,xpath_ActiveExportList_TableRows);
	}
	
	public String getProjectNameFromActiveExportList() throws Exception {
		return getActiveExportList().get(0).get("Project Name");
	}
	
	public Boolean isCorrectModuleDisplayedInActiveExportList() throws Exception {
		String moduleNames = getActiveExportList().get(0).get("Module Name");
		return (moduleNames.contains("Chapter 8 Conceptual and Philosophic Foundations of Professional Nursing") && moduleNames.contains("Course Introduction")) ? true: false;
	}
	
	public LinkedHashMap<String, String> getExportList() throws Exception {
		return getTableDataAsList(ExportList_TableHeaders,ExportList_TableRows,xpath_ExportList_TableRows).get(0);
	}
	
	public Boolean isCorrectModuleDisplayedInExportList() throws Exception {
		String moduleNames = getExportList().get("Module Name");
		return (moduleNames.contains("Chapter 8 Conceptual and Philosophic Foundations of Professional Nursing") && moduleNames.contains("Course Introduction")) ? true: false;
	}
	
	
	public String getExportAlertMessage() throws Exception{
		return getText(Text_NoActiveExports);
	}

	public String getStatus() throws Exception{
		return getExportList().get("Status\n"+ "All");
	}
	
	public ExportPage refreshPageUntilStatusIsCompleted() throws Exception {
		int i=0;
		do{
			hardRefresh();
			Thread.sleep(2000);
			if(getStatus().equals("Completed"))
				break;
			i++;
		}while(getStatus().equals("Completed") || i<10);
		return this;
	}
	
	
}
