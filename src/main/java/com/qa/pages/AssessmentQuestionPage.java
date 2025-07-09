package com.qa.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.QuestionType;
import com.qa.util.TestUtil;

public class AssessmentQuestionPage extends CommonPage{

	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title']")
	private WebElement PageTitle;

	@FindBy(xpath="//div[@class='page-title']/child::div[contains(@class,'pull-middle')]")
	private WebElement ProjectTitle;

	@FindBy(xpath="//button[normalize-space()='Back to Assessment']")
	private WebElement Button_BackToAssessment;

	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;

	@FindBy(xpath="//button[normalize-space()='Create New Question']")
	private WebElement Button_CreteNewQuestion;

	@FindBy(xpath="//button[normalize-space()='Copy Assessment']")
	private WebElement Button_CopyAssessment;

	@FindBy(xpath="//button[normalize-space()='Advanced Search']")
	private WebElement Button_AdvancedSearch;

	@FindBy(xpath="//button[normalize-space()='Upload Question Json']")
	private WebElement Button_UploadQuestionJSON;
	
	@FindBy(xpath="(//*[@type = 'file'])")
	private WebElement Input_File;

	@FindBy(xpath="//div[@role='alert']")
	private WebElement Alert_Msg;

	@FindBy(xpath="//div[@class='group inputfrom']/child::select")
	private WebElement PopUp_SelectList_AssessmentType;

	@FindBy(xpath="//button[normalize-space()='Confirm']/preceding-sibling::button[normalize-space()='CANCEL']")
	private WebElement PopUp_Button_Cancel;

	@FindBy(xpath="//button[normalize-space()='Confirm']")
	private WebElement PopUp_Button_Confirm;

	@FindBy(xpath="//td[@class='tooltips ng-binding']")
	private List<WebElement> List_QuestionTypes; 
	
	@FindBy(xpath="//td[@class='ng-binding'][1]")
	private List<WebElement> List_UploadedQuestionTypes;

	@FindBy(xpath="//span[@class='file-size ng-binding']")
	private WebElement upload_progress;
	
	@FindBy(xpath="//span[@class='caret maximize']")
	private WebElement Button_UploadBar_Minimize;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement PopUp_Button_OK;

    @FindBy(xpath="//button[normalize-space()='Edit'][1]")
    private WebElement Button_Edit;
   
    @FindBy(xpath="//button[normalize-space()='Go Back']")
    private WebElement Button_GoBack;
    
    @FindBy(xpath="//tbody/tr[@class='ng-scope']/td[5]/i[@class='fa fa-exclamation-triangle']")
	private WebElement Text_AlertStatus;
	
	public AssessmentQuestionPage() {
		//waitUntilLoading();
	}

	public AssessmentQuestionPage(String KillDefaultConstructor) {
	}

	public AssessmentPage clickOnBackToAssessment() throws Exception {
		click(Button_BackToAssessment);
		return new AssessmentPage();
	}

	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}

	public AssessmentQuestionsDetailsPage createQuestion(QuestionType Type) throws Exception {
		clickOnCreateNewQuestion();
		selectAssessmentType(Type);
		return clickOnConfirmPopUp();
	}

	private void clickOnCreateNewQuestion() throws Exception {
		click(Button_CreteNewQuestion);
		Thread.sleep(2000);
	}

	private AssessmentQuestionPage selectAssessmentType(QuestionType Type) throws Exception {
		selectByText(PopUp_SelectList_AssessmentType, Type.toString());
		return this;
	}

	public AssessmentQuestionsDetailsPage clickOnConfirmPopUp() throws Exception {
		click(PopUp_Button_Confirm);
		return new AssessmentQuestionsDetailsPage();
	}

	public AssessmentQuestionPage clickOnCancelPopup() throws Exception {
		click(PopUp_Button_Cancel);
		return this;
	}

	public List<String> getAllListedQuestions() throws Exception {
		return getText(List_QuestionTypes);
	}

	public String getQuestionByName(String Name) throws Exception {
		return getText(getWebElementByExpectedText(List_QuestionTypes, Name));
	}
	
	public AssessmentQuestionPage uploadQuestionsJSON(String JSONToUpload) throws Exception {
		enableHiddenInputTags();
		sendKeys(Input_File, System.getProperty("user.dir")+TestUtil.updateFilePathSeparatorBasedOnOS("/src/main/java/com/qa/testdata/")+JSONToUpload);
		try {
			click(PopUp_Button_OK);
		}catch(Exception e) {
			//logs.error(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), e);
			wait.until(ExpectedConditions.textToBePresentInElement(upload_progress, "100%"));
		}
		waitUntilLoading();
		return this;
	}	
	
	public HashMap<String, Integer> getUploadedQuestionsTypeData() throws Exception {
		HashMap<String, Integer> questionTypeWithCount = new HashMap<String, Integer>();
		//List<String> questionTypeList = getText(List_UploadedQuestionTypes);
		for(String word: getText(List_UploadedQuestionTypes)) {
		  Integer count = questionTypeWithCount.get(word.toUpperCase());          
		  questionTypeWithCount.put(word.toUpperCase(), (count==null) ? 1 : count+1);
		}
		return questionTypeWithCount;
	}

    public AssessmentQuestionsDetailsPage clickOnEdit() throws Exception {
        click(Button_Edit);
        return new AssessmentQuestionsDetailsPage();
    }
    
    public AssessmentPage clickOnGoBack() throws Exception {
    	click(Button_GoBack);
    	return new AssessmentPage();
    }
    
    public String getAlertStatus() throws Exception {
		return getAttribute(Text_AlertStatus, "style");
	}
    
}
