package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.ProjectType;
import com.qa.util.TestUtil;

public class BuildingBlocksPage extends CommonPage {
	
	@FindBy(xpath="//div[@class='pull-middle']")
	private WebElement PageTitle;
	
	@FindBy(xpath="//ul[@class='nav nav-tabs']//li[@ng-show='showPattern']")
	private WebElement Link_Patterns;

	@FindBy(xpath="//ul[@class='nav nav-tabs']//li[@ng-show='showTemplate']")
	private WebElement Link_Templates;
	
	@FindBy(xpath="//ul[@class='nav nav-tabs']//li[@ng-show='showWidget']")
	private WebElement Link_Widgets;
	
	@FindBy(name="search_text")
	private WebElement TextBox_SearchPattern;
	
	@FindBy(xpath="//h4[text()='Template Management']/parent::div//input[@name='search_text_form']")
	private WebElement TextBox_SearchTemplate;
	
	@FindBy(xpath="//h4[text()='Widget Management']/parent::div//input[@name='search_text_form']")
	private WebElement TextBox_SearchWidget;
	
	@FindBy(xpath="//button[normalize-space()='Create New Pattern']")
	private WebElement Button_CreateNewPattern;
	
	@FindBy(xpath="//tr[@total-items='totalPatterns']//button[normalize-space()='Preview']")
	private WebElement Button_PreviewPattern;
	
	@FindBy(xpath="//tr[@total-items='totalPatterns']//button[normalize-space()='Edit']")
	private WebElement Button_EditPattern;
	
	@FindBy(xpath="//tr[@total-items='totalPatterns']//button[normalize-space()='Delete']")
	private WebElement Button_DeletePattern;
	
	@FindBy(xpath="//button[@class='confirm']")
	private WebElement Button_Delete_Confirm;
	
	@FindBy(xpath="//button[@class='cancel']")
	private WebElement Button_Delete_Cancel;
	
	@FindBy(name="pattern_title")
	private WebElement PatternPopUp_TextBox_Name;
	
	@FindBy(id="project_type")
	private WebElement PatternPopUp_SelectList_ProjectType;
	
	@FindBy(xpath="//textarea[@id='pattern_content']")
	private WebElement PatternPopUp_TextArea_Content;
	
	@FindBy(name="pattern_class")
	private WebElement PatternPopUp_TextBox_Class;
	
	@FindBy(xpath="//button[normalize-space()='Add']")
	private WebElement PatternPopUp_Button_AddClass;
	
	@FindBy(xpath="//button[normalize-space()='Remove']")
	private WebElement PatternPopUp_Button_RemoveClass;
	
	@FindBy(xpath="//div[@class='modal-footer']//button[normalize-space()='Cancel']")
	private WebElement PatternPopUp_Button_Cancel;
	
	@FindBy(xpath="//div[@class='modal-footer']//button[normalize-space()='Confirm' and @ng-click='add()']")
	private WebElement PatternPopUp_Button_Confirm;
	
	@FindBy(xpath="//tr[@total-items='totalPatterns']/td[@class='ng-binding']")
	private List<WebElement> List_Patterns; 
	
	@FindBy(xpath="//button[normalize-space()='Create New Template']")
	private WebElement Button_CreateNewTemplate;
	
	@FindBy(xpath="//button[normalize-space()='Content']")
	private WebElement Button_Content;
	
	@FindBy(xpath="//tr[@total-items='totalTemplates']/td/button[normalize-space()='Preview']")
	private WebElement Button_PreviewTemplate;
	
	@FindBy(xpath="//tr[@total-items='totalTemplates']//button[@type='button'][normalize-space()='Edit']")
	private WebElement Button_EditTemplate;
	
	@FindBy(xpath="//tr[@total-items='totalTemplates']//button[@type='button'][normalize-space()='Delete']")
	private WebElement Button_DeleteTemplate;
	
	@FindBy(id="templateName")
	private WebElement TemplatePopUp_TextBox_Name;
	
	@FindBy(xpath="//button[@class='btn btn-default'][normalize-space()='CANCEL']")
	private WebElement TemplatePopUp_Button_Cancel;
	
	@FindBy(xpath="//button[@class='btn btn-primary'][normalize-space()='CONFIRM']")
	private WebElement TemplatePopUp_Button_Confirm;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/child::p")
	private WebElement PopUp_Text_Message;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/button[@class='confirm']")
	private WebElement PopUp_Button_OK;
	
	@FindBy(xpath="//tr[@total-items='totalTemplates']/td[1]")
	private List<WebElement> List_Templates; 
	
	@FindBy(xpath="//button[normalize-space()='Create New Widget']")
	private WebElement Button_CreateNewWidget;
	
	@FindBy(xpath="//tr[@total-items='total']/td[@class='ng-binding']")
	private List<WebElement> List_Widgets; 
	
	public BuildingBlocksPage() {
		waitUntilLoading();
	}
	
	public String getPageTitle() throws Exception {
		return getText(PageTitle);
	}
	
	public BuildingBlocksPage clickOnPatterns() throws Exception {
		click(Link_Patterns);
		return this;
	}
	
	public BuildingBlocksPage clickOnTemplates() throws Exception {
		click(Link_Templates);
		return this;
	}
	
	public BuildingBlocksPage clickOnWidgets() throws Exception {
		click(Link_Widgets);
		return this;
	}
	
	public BuildingBlocksPage searchPattern(String PatternName) throws Exception {
		clear(TextBox_SearchPattern);
		sendKeys(TextBox_SearchPattern, PatternName);
		sendKeys(TextBox_SearchPattern,Keys.RETURN);
		waitUntilLoading();
		return this;
	}
	
	public BuildingBlocksPage createPattern(String Name, String Content, ProjectType type) throws Exception {
		click(Button_CreateNewPattern);
		waitUntilLoading();
		sendKeys(PatternPopUp_TextBox_Name, Name);
		selectByText(PatternPopUp_SelectList_ProjectType, type.toString());
		sendKeys(PatternPopUp_TextArea_Content, "<h2>"+Content+"</h2>");
		sendKeys(PatternPopUp_TextBox_Class, TestUtil.faker.lorem().word());
		click(PatternPopUp_Button_Confirm);
		return new BuildingBlocksPage();
	}
	
	public List<String> getListedPatterns() throws Exception{
		return getText(List_Patterns);
	}
	
	public String deletePattern(String patternName) throws Exception {
		searchPattern(patternName);
		click(Button_DeletePattern);
		Thread.sleep(2000);
		confirmPopUp();
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//button[@class='confirm']"), "Yes, delete it!"));
		return getText(PopUp_Text_Message);
	}
	
	public void confirmPopUp() throws Exception {
		click(Button_Delete_Confirm);
	}
	
	public BuildingBlocksPage searchTemplate(String TemplateName) throws Exception {
		sendKeys(TextBox_SearchTemplate, TemplateName);
		sendKeys(TextBox_SearchTemplate,Keys.RETURN);
		waitUntilLoading();
		return this;
	}
	
	public String createTemplate(String Name) throws Exception {
		click(Button_CreateNewTemplate);
		sendKeys(TemplatePopUp_TextBox_Name, Name);
		click(TemplatePopUp_Button_Confirm);
		waitUntilLoading();
		String Message =  getText(PopUp_Text_Message);
		click(PopUp_Button_OK);
		return Message;
	}
	
	public List<String> getListedTemplates() throws Exception{
		return getText(List_Templates);
	}
	
	public CourseEditorPage clickOnContent() throws Exception {
		click(Button_Content);
		return new CourseEditorPage();
	}
	
	public String deleteTemplate(String TemplateName) throws Exception {
		searchTemplate(TemplateName);
		click(Button_DeleteTemplate);
		Thread.sleep(2000);
		confirmPopUp();
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//button[@class='confirm']"), "Yes, delete it!"));
		return getText(PopUp_Text_Message);
	}
	
	public BuildingBlocksPage searchWidget(String WidgetName) throws Exception {
		sendKeys(TextBox_SearchWidget, WidgetName);
		sendKeys(TextBox_SearchWidget,Keys.RETURN);
		waitUntilLoading();
		return this;
	}
	
	public List<String> getListedWidget() throws Exception{
		return getText(List_Widgets);
	}
	
}
