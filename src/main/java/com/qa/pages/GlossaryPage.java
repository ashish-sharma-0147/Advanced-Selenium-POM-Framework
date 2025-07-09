package com.qa.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;

/**
 * 
 * @author sharmaa11
 *
 */
public class GlossaryPage extends CommonPage{

	@FindBy(xpath="//div[@class='pull-middle ng-binding']")
	private WebElement ProjectTitle;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//button[normalize-space()='Create New Glossary']")
	private WebElement Button_CreateNewGlossary;
	
	@FindBy(xpath="//button[@ng-click='downloadCSV()']")
	private WebElement Button_ExportGlossary;
	
	@FindBy(xpath="//input[@placeholder='Search For Term']")
	private WebElement TextBox_GlossarySearch;
	
	@FindBy(xpath="//button[normalize-space()='close']")
	private WebElement Button_GlossarySearch_Clear;
	
	@FindBy(xpath="//button[normalize-space()='Download Sample Csv']")
	private WebElement Button_DownloadSampleCSV;
	
	@FindBy(xpath="//button[normalize-space()='Import Glossary Csv']")
	private WebElement Button_ImportGlossaryCSV;
	
	@FindBy(xpath="//ul[@class='pagination']/li/a")
	private List<WebElement> Filter_ByAlphabet;
	
	@FindBy(xpath="//button[normalize-space()='Edit']")
	private WebElement Button_Edit;
	
	@FindBy(xpath="//button[normalize-space()='View']")
	private WebElement Button_View;
	
	@FindBy(xpath="//div[@class='group inputfrom']/label")
	private List<WebElement> viewPopUp_List_GlossaryKeys;
	
	@FindBy(xpath="//div[@class='group inputfrom']/div")
	private List<WebElement> viewPopUp_List_GlossaryValues;
	
	@FindBy(xpath="//button[normalize-space()='Delete']")
	private WebElement Button_Delete;
	
	@FindBy(xpath="//dir-pagination-controls[contains(@class,'dir_pagination')]")
	private WebElement PaginationBar;
	
	@FindBy(xpath="(//dir-pagination-controls[contains(@class,'dir_pagination')]/descendant::span)|(//dir-pagination-controls[contains(@class,'dir_pagination')]/descendant::a)")
	private List<WebElement> Pagination_Numbers;
	
	@FindBy(name="term")
	private WebElement TextBox_AddEditGlossary_Term;
	
	@FindBy(name="description")
	private WebElement TextArea_AddEditGlossary_Description;
	
	@FindBy(name="definition_record")
	private WebElement TextBox_AddEditGlossary_DefinitionRecord;
	
	@FindBy(name="definition_text_record")
	private WebElement TextBox_AddEditGlossary_DefinitionTextRecord;
	
	@FindBy(name="source")
	private WebElement TextBox_AddEditGlossary_Source;
	
	@FindBy(name="linkedterms")
	private WebElement EditGlossary_LinkedTermContainer;
	
	@FindBy(xpath="//div[@name='linkedterms']/descendant::div")
	private List<WebElement> EditGlossary_List_LinkedTerms;
	
	@FindBy(xpath="//div[@name='linkedterms']/descendant::button")
	private WebElement Button_EditGlossary_RemoveLinkedTerm;
	private String Xpath_EditGlossary_RemoveLinkedTermByMatchingText = "//div[@name='linkedterms']/descendant::div[contains(text(),'xxxxxxxxxx')]/following-sibling::button";
	
	@FindBy(xpath="//button[@class='btn btn-default']")
	private WebElement Button_AddEditViewGlossary_Cancel;
	
	@FindBy(xpath="//button[@class='btn btn-primary'][normalize-space()='CONFIRM']")
	private WebElement Button_AddEditGlossary_Confirm;
	
	@FindBy(xpath="//b[contains(text(),'Term')]/parent::label/following-sibling::div")
	private WebElement Label_ViewGlossary_Term;
	
	@FindBy(xpath="//b[contains(text(),'Description')]/parent::label/following-sibling::div")
	private WebElement Label_ViewGlossary_Description;
	
	@FindBy(xpath="//b[contains(text(),'Definition Record Id')]/parent::label/following-sibling::div")
	private WebElement Label_ViewGlossary_DefinitionRecordId;
	
	@FindBy(xpath="//b[contains(text(),'Definition Text Record')]/parent::label/following-sibling::div")
	private WebElement Label_ViewGlossary_DefinitionTextRecord;
	
	@FindBy(xpath="//b[contains(text(),'Source')]/parent::label/following-sibling::div")
	private WebElement Label_ViewGlossary_Source;
	
	@FindBy(xpath="//button[normalize-space()='Yes, delete it!']")
	private WebElement Button_Delete_Yes;
	
	@FindBy(xpath="//button[normalize-space()='No, cancel!']")
	private WebElement Button_Delete_No;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/p")
	private WebElement Delete_AlertMessage;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement Button_Delete_Ok;
	
	@FindBy(xpath="//table[@ng-if='totalGlossary']/thead/descendant::th")
	private List<WebElement> ListedGlossary_Headers;
	
	@FindBy(xpath="//table[@ng-if='totalGlossary']/tbody/descendant::tr[@total-items='totalGlossary']")
	private List<WebElement> ListedGlossary_Rows;
	private String xpath_GlossaryList = "//table[@ng-if='totalGlossary']/tbody/descendant::tr[@total-items='totalGlossary']";
	
	public GlossaryPage() {
		waitUntilLoading();
	}
	
	public String getProjectTitle() throws Exception {
		return getText(ProjectTitle);
	}
	
	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}
	
	public GlossaryPage createNewGlossary(String term, String description, String definitionRecordId, String definitionTextRecord, String source) throws Exception {
		click(Button_CreateNewGlossary);
		Thread.sleep(2000);
		sendKeys(TextBox_AddEditGlossary_Term, term);
		sendKeys(TextArea_AddEditGlossary_Description, description);
		sendKeys(TextBox_AddEditGlossary_DefinitionRecord, definitionRecordId);
		sendKeys(TextBox_AddEditGlossary_DefinitionTextRecord, definitionTextRecord);
		sendKeys(TextBox_AddEditGlossary_Source, source);
		return this;
	}
	
	public GlossaryPage clickOnConfirm() throws Exception {
		click(Button_AddEditGlossary_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public GlossaryPage clickOnCancel() throws Exception {
		click(Button_AddEditViewGlossary_Cancel);
		return this;
	}
	
	public GlossaryPage searchGlossary(String glossaryTerm) throws Exception {
		sendKeys(TextBox_GlossarySearch, glossaryTerm);
		sendKeys(TextBox_GlossarySearch, Keys.RETURN);
		waitUntilLoading();
		return this;
	}
	
	public GlossaryPage clearGlossarySearch() throws Exception {
		click(Button_GlossarySearch_Clear);
		waitUntilLoading();
		return this;
	}
	
	public GlossaryPage editGlossary(String term, String description, String definitionTextRecord, String source) throws Exception {
		click(Button_Edit);
		waitUntilLoading();
		clear(TextBox_AddEditGlossary_Term);
		sendKeys(TextBox_AddEditGlossary_Term, term);
		clear(TextArea_AddEditGlossary_Description);
		sendKeys(TextArea_AddEditGlossary_Description, description);
		clear(TextBox_AddEditGlossary_DefinitionTextRecord);
		sendKeys(TextBox_AddEditGlossary_DefinitionTextRecord, definitionTextRecord);
		clear(TextBox_AddEditGlossary_Source);
		sendKeys(TextBox_AddEditGlossary_Source, source);
		return this;
	}
	
	public GlossaryPage removeLinkedTerm(String linkedTerm) throws Exception {
		click(Button_Edit);
		waitUntilLoading();
		if(getWebElementByExpectedText(EditGlossary_List_LinkedTerms, linkedTerm.toLowerCase()) != null) 
			click(EditGlossary_LinkedTermContainer.findElement(By.xpath(Xpath_EditGlossary_RemoveLinkedTermByMatchingText.replace("xxxxxxxxxx", linkedTerm.toLowerCase()))));
		return this;
	}
	
	public HashMap<Object,Object> viewPopUp_getGlossaryDetails() throws Exception{
		click(Button_View);
		waitUntilLoading();
		return createElementTextKeyValueHashMap(viewPopUp_List_GlossaryKeys,viewPopUp_List_GlossaryValues);
	}
	
	public String deleteGlossary(String glossaryTerm) throws Exception {
		searchGlossary(glossaryTerm);
		return deleteGlossary() ;
	}
	
	public String deleteGlossary() throws Exception {
		click(Button_Delete);
		Thread.sleep(2000);
		click(Button_Delete_Yes);
		waitUntilLoading();
		return getText(Delete_AlertMessage);
	}
	
	public GlossaryPage confirmPopUp() throws Exception {
		click(Button_Delete_Ok);
		return this;
	}
	
	public GlossaryPage cancelPopUp() throws Exception {
		click(Button_AddEditViewGlossary_Cancel);
		Thread.sleep(2000);
		return this;
	}
	
	public List<LinkedHashMap <String,String>> getListedGlossaries() throws Exception{
		return getTableDataAsList(ListedGlossary_Headers, ListedGlossary_Rows, xpath_GlossaryList);
	}
	
}
