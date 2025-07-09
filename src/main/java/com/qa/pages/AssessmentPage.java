package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;

public class AssessmentPage extends CommonPage{

	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title']")
	private WebElement PageTitle;

	@FindBy(xpath="//div[@class='page-title']/child::div[contains(@class,'pull-middle')]")
	private WebElement ProjectTitle;

	@FindBy(name="search_text")
	private WebElement TextField_AssessmentName;

	@FindBy(xpath="//button[normalize-space()='Advanced search']")
	private WebElement Button_AdvancedSearch;

	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;

	@FindBy(xpath="//button[normalize-space()='Create New Assessment']")
	private WebElement Button_CreateNewAssessment;

	@FindBy(xpath="//div[@role='alert']")
	private WebElement Alert_Msg;

	@FindBy(xpath="//a[@class='ng-binding']")
	private List<WebElement> List_AssessmentName;

	@FindBy(xpath="//div[contains(@class,'popup-boxes')]/descendant::div[contains(@class,'modal-header')]")
	private WebElement PopUp_Title;

	@FindBy(id="details")
	private WebElement PopUp_Link_Details;

	@FindBy(id="metadata")
	private WebElement Link_MetaData;

	@FindBy(xpath="//div[@class='group inputfrom']/input[@placeholder='Name']")
	private WebElement PopUp_TextField_AssessmentName;

	@FindBy(id="vtw_toggle")
	private WebElement PopUp_Toogle_VTW;

	@FindBy(xpath="vtw_id")
	private WebElement PopUp_TextBox_VTWId;

	@FindBy(id="uuid_toggle")
	private WebElement PopUp_Toogle_UUID;

	@FindBy(id="uuid")
	private WebElement PopUp_TextBox_UUID;

	@FindBy(xpath="//button[text()='GENERATE NEW ID']")
	private WebElement PopUp_Button_GenerateUUID;

	@FindBy(xpath="//a[@role='button' and @aria-label='cancel']")
	private WebElement PopUp_Button_Cancel;

	@FindBy(xpath="//button[normalize-space()='CONFIRM']")
	private WebElement PopUp_Button_Confirm;
	
	public AssessmentPage() {
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

	public AssessmentPage clickCreateNewAssessment() throws Exception {
		click(Button_CreateNewAssessment);
		waitUntilLoading();
		return this;
	}

	public AssessmentPage setAssessmentName(String Name) throws Exception {
		sendKeys(PopUp_TextField_AssessmentName, Name);
		return this;
	}

	public AssessmentPage clickConfirm() throws Exception {
		Thread.sleep(2000);
		click(PopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}

	public AssessmentQuestionPage openAssessment(String Name) throws Exception {
		searchAssessment(Name);
		click(getWebElementByExpectedText(List_AssessmentName, Name));
		Thread.sleep(4000);
		return new AssessmentQuestionPage();
	}
	
	public AssessmentPage searchAssessment(String assessmentName) throws Exception {
		sendKeys(TextField_AssessmentName, assessmentName);
		waitUntilLoading();
		return this;
	}

}
