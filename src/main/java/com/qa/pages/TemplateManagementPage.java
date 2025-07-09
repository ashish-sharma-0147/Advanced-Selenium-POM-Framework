package com.qa.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;

public class TemplateManagementPage extends CommonPage{
	
	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title']")
	private WebElement PageTitle;
	
	@FindBy(xpath="//div[@class='pull-middle ng-binding']")
	private WebElement ProjectTitle;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//button[normalize-space()='Create New Template']")
	private WebElement Button_CreateNewTemplate;
	
	@FindBy(name="search_text")
	private WebElement TextBox_Search;
	
	@FindBy(xpath="//label[normalize-space()='Local']")
	private WebElement Button_Toogle_Local;
	
	@FindBy(xpath="//label[normalize-space()='Global']")
	private WebElement Button_Toogle_Global;
	
	@FindBy(xpath="//button[normalize-space()='Content']")
	private WebElement Button_Content;
	
	@FindBy(xpath="//button[normalize-space()='Preview']")
	private WebElement Button_Preview;

	@FindBy(xpath="//button[normalize-space()='Edit']")
	private WebElement Button_Edit;
	
	@FindBy(xpath="//button[normalize-space()='Delete']")
	private WebElement Button_Delete;
	
	@FindBy(xpath="//tr[@total-items='totalTemplates']/td[@class='ng-binding'][1]")
	private List<WebElement> List_Templates;
	
	/** Table Elements are pending */
	
	@FindBy(id="templateName")
	private WebElement PopUp_TextBox_Name;
	
	@FindBy(xpath="//button[@class='btn btn-default'][normalize-space()='CANCEL']")
	private WebElement PopUp_Button_Cancel;
	
	@FindBy(xpath="//button[@class='btn btn-primary'][normalize-space()='CONFIRM']")
	private WebElement PopUp_Button_Confirm;
	
	@FindBy(xpath="//span[@class='glyphicon glyphicon-remove']")
	private WebElement SearchIcon_Remove;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/child::p")
	private WebElement PopUp_Text_Message;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement PopUp_Button_Ok;
	
	public TemplateManagementPage() {
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
	
	public TemplateManagementPage searchTemplate(String TemplateName) throws Exception {
		sendKeys(TextBox_Search, TemplateName);
		sendKeys(TextBox_Search,Keys.RETURN);
		waitUntilLoading();
		return this;
	}
	
	public List<String> getListedTemplates() throws Exception{
		return getText(List_Templates);
	}
	
	public TemplateManagementPage createTemplate(String Name) throws Exception {
		click(Button_CreateNewTemplate);
		sendKeys(PopUp_TextBox_Name, Name);
		return this;
	}
	
	public TemplateManagementPage editTemplate(String Name) throws Exception {
		click(Button_Edit);
		clear(PopUp_TextBox_Name);
		sendKeys(PopUp_TextBox_Name, Name);
		click(PopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public String getAlertMessage() throws Exception {
		return getText(PopUp_Text_Message);
	}
	
	public TemplateManagementPage closeAlertPopUp() throws Exception {
		click(PopUp_Button_Ok);
		return this;
	}
	
	public CourseEditorPage clickOnConfirm() throws Exception {
		click(PopUp_Button_Confirm);
		waitUntilLoading();
		return new CourseEditorPage();
	}
	
	public TemplateManagementPage clickOnCancel() throws Exception {
		click(PopUp_Button_Cancel);
		return this;
	}
	
	public TemplateManagementPage resetSearch() throws Exception {
		click(SearchIcon_Remove);
		waitUntilLoading();
		return this;
	}
}
