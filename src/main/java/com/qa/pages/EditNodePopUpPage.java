/**
 * 
 */
package com.qa.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.AddNodePopupPage.resourceType;
import com.qa.pages.common.CommonPage;

/**
 * @author sharmaa11
 *
 */
public class EditNodePopUpPage extends CommonPage{
	
	@FindBy(xpath="//i[@id='spinner']")
	private WebElement Loader_spinner;
	
	@FindBy(xpath="modal-backdrop fade  in")
	private WebElement PopUp_BodyLayer;
	
	@FindBy(xpath="//label[text()='VTW ID']/parent::div/following-sibling::div/child::label[@class='switch-button-background']")
	private WebElement Button_Toogle_VTWID;
	
	@FindBy(xpath="//label[text()='UUID']/parent::div/following-sibling::div/child::label[@class='switch-button-background']")
	private WebElement Button_Toogle_UUID;
	
	@FindBy(id="vtw_id")
	private WebElement TextBox_VTWID;
	
	@FindBy(id="uuid")
	private WebElement TextBox_UUID;
	
	@FindBy(id="templateName")
	private WebElement TextField_TemplateName;
	
	/****************** FOLDER/MODULE ELEMENTS ***************/
	
	@FindBy(xpath="//input[@type='checkbox' and @ng-model='make_module']")
	private WebElement CheckBox_MakeAsModule;
	
	/******************************************************/
	
	/****************** ASSIGNMENT ELEMENTS ***************/
	
	@FindBy(xpath="//button[@class='btn chice-deletes assignment_delete']")
	private WebElement Button_DeleteAssignment;
	
	@FindBy(id="select_assignmentt")
	private WebElement Button_SelectAssignment;

	@FindBy(xpath="//input[contains(@name,'selected_assignment_id')]")
	private WebElement RadioButton_ListedAssignment;
	
	@FindBy(xpath="//div[@class='modal-footer']/child::button[normalize-space()='CONFIRM']")
	private WebElement Button_SearchList_Confirm;
	
	/******************************************************/
	
	/****************** ASSESSMENT ELEMENTS ***************/

	@FindBy(id="assessment_dropdown")
	private WebElement SelectList_Assessment;

	@FindBy(id="allAssessmentId")
	private WebElement RadioButton_AllAssessmentItem;

	@FindBy(id="selectdAssessmentId")
	private WebElement RadioButton_SelectedAssessmentItem;

	@FindBy(id="addAssessmentModalBtn")
	private WebElement Button_SelectAssessment;

	@FindBy(xpath="//input[contains(@name,'selected_assessment_id')]")
	private WebElement RadioButton_AllAssessmentItem_ListedAssessment;

	@FindBy(xpath="//input[contains(@name,'selected_assessment_item')]")
	private WebElement RadioButton_SelectedAssessmentItem_ListedAssessment;
	
	@FindBy(id="deleteAssessmentBtn")
	private WebElement Button_DeleteAssessment;
	
	@FindBy(xpath="/span[contains(@class,'assessment_name')]")
	private WebElement Text_Selected_Assessment;
	/******************************************************/
	
	/********************* FORUM ELEMENTS *****************/
	@FindBy(id="forum")
	private WebElement RadioButton_Forum;

	@FindBy(id="select_addForumModal")
	private WebElement Button_SelectForum;

	@FindBy(xpath="//input[contains(@name,'selected_forum_id')]")
	private WebElement RadioButton_ListedForum;
	
	@FindBy(id="forum_delete_btn")
	private WebElement Button_DeleteForum;
	/******************************************************/
	
	/******************** RESOURCE ELEMENTS ***************/

	@FindBy(id="linkName")
	private WebElement TextField_Resource_Name;

	@FindBy(id="ext_res_type_link")
	private WebElement SelectList_Resources;

	@FindBy(id="ext_res_url")
	private WebElement Resource_TextField_URL;

	@FindBy(id="addAssestModalBtn")
	private WebElement Resource_Button_SelectAsset;

	@FindBy(id="search_text_asset")
	private WebElement Resource_TextBox_SearchAsset;

	@FindBy(xpath="//button[contains(@class,'search_btn')]")
	private WebElement Resource_Button_Search;

	@FindBy(xpath="//div[@class='box-theme']")
	private WebElement Resource_ListedAsset;

	@FindBy(xpath="//div[@class='box-theme']/child::div[@class='images-select lor-design']")
	private WebElement Resource_ListedAsset_RadioButton;

	@FindBy(id="submit_image")
	private WebElement Resource_Button_AddSelectedAsset;

	@FindBy(xpath="//span[contains(@class,'asset_name')]")
	private WebElement Resource_SelectedAssetName;

	@FindBy(xpath="//span[contains(@class,'asset_type')]")
	private WebElement Resource_SelectedAssetType;

	@FindBy(id="deleteAsset")
	private WebElement Resource_DeleteSelectedAsset;
	/******************************************************/
	
	@FindBy(xpath="//button[@type='button'][normalize-space()='CANCEL']")
	private WebElement Button_Cancel;

	@FindBy(xpath="//button[normalize-space()='CONFIRM']")
	private WebElement Button_Confirm;
	
	@FindBy(xpath="//input[@ng-model='search_text']")
	private WebElement TextField_SearchItem;

	@FindBy(xpath="//button[@ng-click='search()']")
	private WebElement Button_Search;
	
	@FindBy(xpath="//div[contains(@class,'select_assignment')]/span[contains(@class,'assignment_name')]")
	private WebElement Text_Selected_AssignmentForum;
	
	public EditNodePopUpPage() {
		waitUntilLoading();
	}
	
	public EditNodePopUpPage updateFolderNode(String nodeName, boolean isModule) throws Exception {
		setNodeName(nodeName);
		if(isModule)
			click(CheckBox_MakeAsModule);
		return this;
	}

	public EditNodePopUpPage updateContentScreenNode(String NodeName) throws Exception {
		setNodeName(NodeName);
		return this;
	}
	
	public EditNodePopUpPage updateAssignmentNode(String nodeName, String assignmentName) throws Exception {
		setNodeName(nodeName);
		click(Button_DeleteAssignment);
		click(Button_SelectAssignment);
		waitForSpinner();
		sendKeys(TextField_SearchItem, assignmentName);
		click(Button_Search);
		waitUntilLoading();
		click(RadioButton_ListedAssignment);
		click(Button_SearchList_Confirm);
		return this;
	}
	
	public String getSelectedAssignmentForum() throws Exception {
		return getText(Text_Selected_AssignmentForum);
	}
	
	public EditNodePopUpPage updateAssessmentNode(String nodeName, String assessmentName) throws Exception {
		setNodeName(nodeName);
		click(Button_DeleteAssessment);
		click(Button_SelectAssessment);
		waitForSpinner();
		sendKeys(TextField_SearchItem, assessmentName);
		click(Button_Search);
		waitUntilLoading();
		click(RadioButton_AllAssessmentItem_ListedAssessment);
		click(Button_SearchList_Confirm);
		return this;
	}
	
	public String getSelectedAssessment() throws Exception {
		return getText(Text_Selected_Assessment);
	}
	
	public EditNodePopUpPage updateForumNode(String nodeName,String forumName) throws Exception {
		setNodeName(nodeName);
		click(Button_DeleteForum);
		click(Button_SelectForum);
		waitForSpinner();
		sendKeys(TextField_SearchItem, forumName);
		click(Button_Search);
		waitUntilLoading();
		click(RadioButton_ListedForum);
		click(Button_SearchList_Confirm);
		return this;
	}
	
	public EditNodePopUpPage updateResourceNode(String nodeName, resourceType type, String sssetNameOrURL) throws Exception {
		clear(TextField_Resource_Name);
		sendKeys(TextField_Resource_Name,nodeName);
		selectByValue(SelectList_Resources, type.toString());
		if(type.toString().equals(resourceType.FilefromAssetLibrary.toString()))
		{
			click(Resource_Button_SelectAsset);
			waitForSpinner();
			sendKeys(Resource_TextBox_SearchAsset, sssetNameOrURL.substring(0, 14));
			click(Resource_Button_Search);
			waitUntilLoading();
			click(Resource_ListedAsset_RadioButton);
			click(Resource_Button_AddSelectedAsset);
			waitUntilLoading();
		}
		else
		{
			clear(Resource_TextField_URL);
			sendKeys(Resource_TextField_URL, sssetNameOrURL);
		}
		return this;
	}
	
	public String getSelectedResourceURL() throws Exception {
		return getAttribute(Resource_TextField_URL, "value");
	}
	
	public void waitForSpinner() {
		try {
			wait.until(ExpectedConditions.visibilityOf(Loader_spinner));
			wait.until(ExpectedConditions.invisibilityOf(Loader_spinner));
		}catch(TimeoutException te) {
			//logs.error(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), te);
		}
	}
	
	public EditTOCPage clickOnCancel() throws Exception {
		scrollToElement(Button_Cancel);
		click(Button_Cancel);
		Thread.sleep(2000);
		return new EditTOCPage();
	}

	public EditTOCPage clickOnConfirm() throws Exception {
		scrollToElement(Button_Confirm);
		click(Button_Confirm);
		return new EditTOCPage();
	}
	
	private void setNodeName(String Name) throws Exception {
		clear(TextField_TemplateName);
		sendKeys(TextField_TemplateName, Name);
	}
	
	@FindBy(id="vtw_toggle")
	private WebElement Toogle_VTW;

	@FindBy(id="vtw_id")
	private WebElement TextBox_VTWId;

	@FindBy(id="uuid_toggle")
	private WebElement Toogle_UUID;

	@FindBy(xpath="//button[text()='GENERATE NEW ID']")
	private WebElement Button_GenerateUUID;
	
	@FindBy(className="confirm")
	private WebElement Button_Confirm_Edit;
	
	public String getVTWID() throws Exception{
		//explicitlyWait(TextBox_VTWId);
		//wait.until(null)
		return getAttribute(TextBox_VTWId, "value");
	}
	
	public String getUUID() throws Exception{
		//explicitlyWait(TextBox_UUID);
		return getAttribute(TextBox_UUID, "value");
	}
	
	public EditNodePopUpPage manuallySetVTWID(String ID) throws Exception{
		clear(TextBox_VTWId);
		sendKeys(TextBox_VTWId, ID);
		return this;
	}
	
	public EditNodePopUpPage manuallySetUUID(String ID) throws Exception{
		clear(TextBox_UUID);
		sendKeys(TextBox_UUID, ID);
		return this;
	}
	
	public EditNodePopUpPage generateUUID() throws Exception{
		click(Button_GenerateUUID);
		return this;
	}
	
	public EditNodePopUpPage toggleVTWSetting() throws Exception{
		click(Button_Toogle_VTWID);
		return this;
	}
	
	public EditNodePopUpPage toggleUUIDSetting() throws Exception{
		click(Toogle_UUID);
		return this;
	}
	
	public EditNodePopUpPage confirmPopup() throws Exception{
		click(Button_Confirm_Edit);
		return this;
	}
	
	
}
