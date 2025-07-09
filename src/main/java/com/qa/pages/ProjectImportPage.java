package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;

public class ProjectImportPage extends CommonPage {

	@FindBy(xpath="//section[contains(@class,'wrapper')]/child::div[contains(@class,'page-title')]")
	private WebElement Text_ProjectName;
	
	@FindBy(xpath="//div[@class='from-container']//div[@class='page-title']")
	private WebElement Text_PageTitle;
	
	@FindBy(id="customProcess")
	private WebElement Button_CreateTOC;
	
	@FindBy(xpath="//div[@class='drop-box-container']/child::button")
	private WebElement Button_UploadCoursePackage;
	
	public ProjectImportPage() {
		waitForPageToCompletelyLoad();
	}
	
	public String getProjectName() throws Exception {
		return getText(Text_ProjectName);
	}
	
	public String getPageTitle() throws Exception {
		return getText(Text_PageTitle);
	}
	
	public EditTOCPage clickOnCreateTOC() throws Exception {
		click(Button_CreateTOC);
		return new EditTOCPage();
	}
}
