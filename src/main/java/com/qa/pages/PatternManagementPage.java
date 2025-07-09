package com.qa.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;
import com.qa.util.TestUtil;

public class PatternManagementPage extends CommonPage {
	
	@FindBy(xpath="//h4[contains(@class,'title')]")
	private WebElement PageTitle;
	
	@FindBy(xpath="//div[@class='pull-middle ng-binding']")
	private WebElement ProjectTitle;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//button[normalize-space()='Create New Pattern']")
	private WebElement Button_CreateNewPattern;

	@FindBy(name="search_text")
	private WebElement TextBox_Search;
	
	@FindBy(xpath="//button[normalize-space()='Local']")
	private WebElement Button_Toogle_Local;
	
	@FindBy(xpath="//button[normalize-space()='Global']")
	private WebElement Button_Toogle_Global;
	
	@FindBy(xpath="//tr[@total-items='totalPatterns']/td[@class='ng-binding']")
	private List<WebElement> List_Patterns; 
	
	@FindBy(xpath="//button[normalize-space()='Edit']")
	private WebElement Button_Edit;
	
	/** Table Elements are pending */
	
	@FindBy(name="pattern_title")
	private WebElement PopUp_TextField_Name;
	
	@FindBy(name="pattern_content")
	private WebElement PopUp_TextArea_Content;
	
	@FindBy(name="pattern_class")
	private WebElement PopUp_TextField_Class;
	
	@FindBy(xpath="//button[normalize-space()='Add']")
	private WebElement PopUp_Button_AddClass;
	
	@FindBy(xpath="//button[normalize-space()='Remove']")
	private WebElement PopUp_Button_RemoveClass;
	
	@FindBy(xpath="//button[@class='btn btn-default'][normalize-space()='CANCEL']")
	private WebElement PopUp_Button_Cancel;
	
	@FindBy(xpath="//button[@class='btn btn-primary'][normalize-space()='CONFIRM']")
	private WebElement PopUp_Button_Confirm;
	
	@FindBy(xpath="//span[@class='glyphicon glyphicon-remove']")
	private WebElement SearchIcon_Remove;
	
	public PatternManagementPage() {
		waitUntilLoading();
	}
	
	public String getPageTitle() throws Exception {
		return getText(PageTitle);
	}

	public String getProjectTitle() throws Exception {
		return getText(ProjectTitle);
	}
	
	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}
	
	public PatternManagementPage searchPattern(String PatternName) throws Exception {
		sendKeys(TextBox_Search, PatternName);
		sendKeys(TextBox_Search,Keys.RETURN);
		waitUntilLoading();
		return this;
	}
	
	public List<String> getListedPatterns() throws Exception{
		return getText(List_Patterns);
	}
	
	public PatternManagementPage createNewPattern(String Name, String Content) throws Exception {
		click(Button_CreateNewPattern);
		waitUntilLoading();
		sendKeys(PopUp_TextField_Name, Name);
		sendKeys(PopUp_TextArea_Content, "<h2>"+Content+"</h2>");
		sendKeys(PopUp_TextField_Class, TestUtil.faker.lorem().word());
		return this;
	}
	
	public PatternManagementPage updatePattern(String Name, String Content) throws Exception {
		click(Button_Edit);
		waitUntilLoading();
		clear(PopUp_TextField_Name);
		clear(PopUp_TextArea_Content);
		clear(PopUp_TextField_Class);
		sendKeys(PopUp_TextField_Name, Name);
		sendKeys(PopUp_TextArea_Content, "<h2>"+Content+"</h2>");
		sendKeys(PopUp_TextField_Class, TestUtil.faker.lorem().word());
		return this;
	}
	
	public PatternManagementPage clickOnConfirm() throws Exception {
		click(PopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public PatternManagementPage clickOnCancel() throws Exception {
		click(PopUp_Button_Cancel);
		return this;
	}
	
	public PatternManagementPage resetSearch() throws Exception {
		click(SearchIcon_Remove);
		waitUntilLoading();
		return this;
	}
	
}
