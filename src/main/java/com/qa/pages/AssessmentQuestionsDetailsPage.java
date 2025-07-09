package com.qa.pages;

import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.TaskPriority;
import com.qa.testdata.TaskStatus;
import com.qa.util.TestUtil;

public class AssessmentQuestionsDetailsPage extends CommonPage {

	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title ng-binding']")
	private WebElement PageTitle;

	@FindBy(xpath="//div[@class='page-title']/child::div[contains(@class,'pull-middle')]")
	private WebElement ProjectTitle;

	@FindBy(xpath="//button[normalize-space()='Go Back']")
	private WebElement Button_GoBack;

	@FindBy(xpath="//button[normalize-space()='Save']")
	private WebElement Button_Save;

	@FindBy(xpath="//button[@class='confirm']")
	private WebElement PopUp_Button_SaveItorOK;

	@FindBy(xpath="//button[@class='cancel']")
	private WebElement PopUp_Button_DontSave;

	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/p")
	private WebElement PopUp_AlertMessage;

	@FindBy(id="question_title")
	private WebElement TextField_QuestionTitle;

	@FindBy(xpath="//body/p")
	private WebElement iFrame_TextArea;

	@FindBy(xpath="//iframe[@title='Rich Text Editor, question_stem']")
	private WebElement iframe_QuestionStem;

	@FindBy(xpath="//iframe[@title='Rich Text Editor, case_study']")
	private WebElement iframe_CaseStudy;

	@FindBy(xpath="//iframe[@title='Rich Text Editor, instruction_text']")
	private WebElement iframe_InstructionText;

	@FindBy(xpath="//iframe[@title='Rich Text Editor, choices_1']")
	private WebElement iframe_Choice1;

	@FindBy(xpath="//iframe[@title='Rich Text Editor, choices_2']")
	private WebElement iframe_Choice2;

	@FindBy(xpath="//iframe[@title='Rich Text Editor, choices_3']")
	private WebElement iframe_Choice3;

	@FindBy(xpath="//input[@id='assess_1_radio']")
	private WebElement radioButton_Choice1;

    @FindBy(xpath="//button[@id='openComment']")
    private WebElement Button_OpenTaskComment;
    
    @FindBy(xpath="//label[@for='vtw_toggle']")
	private WebElement Toogle_VTW;

	@FindBy(id="vtw_id")
	private WebElement TextBox_VTWId;

	@FindBy(id="uuid_toggle")
	private WebElement Toogle_UUID;

	@FindBy(id="uuid")
	private WebElement TextBox_UUID;

	@FindBy(xpath="//button[text()='GENERATE NEW ID']")
	private WebElement Button_GenerateUUID;
	
	@FindBy(className="confirm")
	private WebElement Button_Confirm_Edit;
	
	@FindBy(xpath="//button[normalize-space()='Update']")
	private WebElement Button_Update;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/child::p")
	private WebElement PopUp_Text_Message;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement Button_Ok;
	
	@FindBy(xpath="//div[@class='cke_contents cke_reset']/child::iframe[@class='cke_wysiwyg_frame cke_reset']")
	private WebElement Iframe_QuestionStem;
	
	@FindBy(xpath="//body[@class='cke_editable cke_editable_themed cke_contents_ltr']/p")
	private WebElement TextArea_QuestionStem;
	
	@FindBy(xpath="//div[@class='pull-right']//button[@id='next_assessment']")
	private WebElement Button_Next;

    /*****************Task Comment Section Elements****************/
	@FindBy(xpath="//span[normalize-space()='Question Stem Comments/Tasks']")
    private WebElement Label_QuestionStemCommentsTasks;

    @FindBy(xpath="//span[normalize-space()='Sample Answer Comments/Tasks']")
    private WebElement Label_SampleAnswerCommentsTasks;

    @FindBy(xpath="//span[normalize-space()='Feedback Comments/Tasks']")
    private WebElement Label_FeedbackCommentsTasks;

	@FindBy(xpath="//div[@class='comments-wrapper']//div[contains(@id,'fake-comment-box')]")
	private WebElement Fake_TextBox_Tasks;
	
	@FindBy(xpath="//div[@class='comments-wrapper ']//div[contains(@id,'fake-comment-box')]")
	private WebElement Fake_Text_Comments;
	
	@FindBy(xpath="//div[@id='actual-comment-box-task']//textarea[@id='comment']")
	private WebElement Actual_TextArea_Task;

    @FindBy(xpath="//div[@id='actual-comment-box-comment']//textarea[@id='comment']")
	private WebElement Actual_TextArea_Comment;
	
	@FindBy(id="ticket_attachment_task")
	private WebElement InputFile_TaskAttachment;
	
	@FindBy(id="ticket_attachment_comment")
	private WebElement InputFile_CommentAttachment;
	
	@FindBy(id="status")
	private WebElement SelectList_TaskStatus;
	
	@FindBy(id="priority")
	private WebElement SelectList_TaskPriority;
	
	@FindBy(xpath="//div[@class='actual-comment-box']//button[@type='button'][normalize-space()='Cancel']")
	private WebElement Button_Task_Cancel;
	
	@FindBy(xpath="//div[@class='actual-comment-box']//button[@type='submit'][normalize-space()='Submit']")
	private WebElement Button_Task_Submit;

    @FindBy(xpath="//div[@id='actual-comment-box-comment']//button[@type='submit'][normalize-space()='Submit']")
    private WebElement Button_Comment_Submit;	

	@FindBy(xpath="//div[@id='please-wait' and @ng-show='pleaseWaitTask']")
	private WebElement Label_Task_PleaseWait;
	
	@FindBy(id="alert-message-task")
	private WebElement AlertMessage_Task;
	
	@FindBy(id="alert-message-comment")
	private WebElement AlertMessage_Comment;

    @FindBy(xpath="//button[@ng-click='closeComment()']")
    private WebElement Button_Close;    
	/******************************************************/

    /****************** All Comment/Task Comment Section Elements  **************************/

    @FindBy(xpath="//span[normalize-space()='All Comments/Tasks']")
    private WebElement Label_AllCommentsTasks;

    @FindBy(xpath="//div[@id='task_0']//div[@class='col-md-12 col-sm-12']")
    private WebElement Task_FeedbackPanel;

    @FindBy(xpath="//div[@id='task_0']//div[@class='col-md-12 col-sm-12 comment-panel active']")
    private WebElement Task_FeedbackPanelActive;

    @FindBy(xpath="//span[normalize-space()='Feedback: Task']")
    private WebElement Task_FeedbackTask;

    @FindBy(xpath="//div[@id='task_1']//div[@class='col-md-12 col-sm-12 comment-panel active']")
    private WebElement Task_SampleAnswerPanel;

    @FindBy(xpath="(//span[normalize-space()='Sample Answer: Task'])")
    private WebElement Task_SampleAnswerTask;

    @FindBy(xpath="//div[@id='task_2']//div[@class='col-md-12 col-sm-12 comment-panel active']")
    private WebElement Task_QuestionStemPanel;

    @FindBy(xpath="//span[normalize-space()='Question Stem: Task']")
    private WebElement Task_QuestionStemTask;

    @FindBy(id="comment_0")
    private WebElement Comment_FeedbackCommentPanel;

    @FindBy(xpath="//span[normalize-space()='Feedback: Comment']")
    private WebElement Comment_FeedbackComment;

    @FindBy(id="comment_1")
    private WebElement Comment_SampleAnswerCommentPanel;

    @FindBy(xpath="//span[normalize-space()='Sample Answer: Comment']")
    private WebElement Comment_SampleAnswerComment;

    @FindBy(id="comment_2")
    private WebElement Comment_QuestionStemCommentPanel;

    @FindBy(xpath="//span[normalize-space()='Question Stem: Comment']")
    private WebElement Comment_QuestionStemComment;

    @FindBy(xpath="//div[@class='col-md-12 col-sm-12 comment-panel active']")
    private WebElement IndividualTaskPanel;

    /****************** Question Type Icons  **************************/

    @FindBy(xpath="//button[@ng-click=\"openComment('Question Stem')\"]")
    private WebElement Icon_QuestionStem;

    @FindBy(xpath="//button[@ng-click=\"openComment('Sample Answer')\"]")
    private WebElement Icon_SampleAnswer;

    @FindBy(xpath="//div[@id='with-response']//div[2]//button[1]")
    private WebElement Icon_Feedback;
     
    /*****************Task Comment Section Elements****************/
	
	@FindBy(xpath="//div[@ng-show='fakeCommentBoxTask']")
	private WebElement TextBox_Tasks;

	@FindBy(xpath="//div[@id='actual-comment-box-task']//textarea[@id='comment']")
	private WebElement TextArea_TasksComments;

	@FindBy(id="assigned_to")
	private WebElement SelectList_Users;
	
	@FindBy(xpath="//div[@class='actual-comment-box']//button[@type='button'][normalize-space()='Cancel']")
	private WebElement Button_TaskComment_Cancel;
	
	@FindBy(xpath="//div[@class='actual-comment-box']//button[@type='submit'][normalize-space()='Submit']")
	private WebElement Button_TaskComment_Submit;
	
    @FindBy(className="comment-panel")
    private WebElement Button_CommentPanel;
   
    @FindBy(xpath="//i[@class='fa fa-edit']")
    private WebElement Button_EditTask;
    
    @FindBy(xpath="//button[normalize-space()='Yes, edit it!']")
    private WebElement Button_ConfirmTaskEdit;
    
    @FindBy(xpath="//button[@id='openComment']//span[@class='fa fa-comments']")
    private WebElement Button_OpenTaskPanel;
    
    @FindBy(xpath="//span[normalize-space()='All Comments/Tasks']")
    private WebElement TaskCommentPanel_Title;

	/******************************************************/
    
	public AssessmentQuestionsDetailsPage() {
		waitUntilLoading();
	}

	public void clickOnSave() throws Exception {
		click(Button_Save);
		waitUntilLoading();
	}

	public AssessmentQuestionsDetailsPage createMCQQuestion(String QuestionTitle) throws Exception {
		setQuestionTitleAndStem(QuestionTitle);
		setMCQChoices();
		clickOnSave();
		return this;
	}

	private void setQuestionTitleAndStem(String Title) throws Exception {
		sendKeys(TextField_QuestionTitle, Title );
		switchToFrame(iframe_QuestionStem);
		sendKeysByJsExecutor(iFrame_TextArea, TestUtil.faker.lorem().word());
		switchToDefaultContent();
	}

	private void setMCQChoices() throws Exception {
		click(radioButton_Choice1);
		switchToFrame(iframe_Choice1);
		scrollDown(100);
		sendKeysByJsExecutor(iFrame_TextArea, TestUtil.faker.color().name());
		switchToDefaultContent();
		switchToFrame(iframe_Choice2);
		scrollDown(300);
		sendKeysByJsExecutor(iFrame_TextArea, TestUtil.faker.color().name());
		switchToDefaultContent();
		switchToFrame(iframe_Choice3);
		scrollDown(400);
		sendKeysByJsExecutor(iFrame_TextArea, TestUtil.faker.color().name());
		switchToDefaultContent();
	}


	public String getPopUpText() throws Exception {
		return getText(PopUp_AlertMessage);
	}

	public AssessmentQuestionPage closeSuccessPopUp() throws Exception {
		click(PopUp_Button_SaveItorOK);
		return new AssessmentQuestionPage("KillDefaultConstructor");
	}

    public boolean verifyIcons() throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOf(Icon_QuestionStem));
            wait.until(ExpectedConditions.visibilityOf(Icon_SampleAnswer));
            wait.until(ExpectedConditions.visibilityOf(Icon_Feedback));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickQuestionStemIcon() throws Exception {
        click(wait.until(ExpectedConditions.elementToBeClickable(Icon_QuestionStem)));
    }

    public void clickSampleAnswerIcon() throws Exception {
        click(wait.until(ExpectedConditions.elementToBeClickable(Icon_SampleAnswer)));
    }

    public void clickFeedbackIcon() throws Exception {
        click(wait.until(ExpectedConditions.elementToBeClickable(Icon_Feedback)));
    }

    public boolean hasClass(WebElement element, String className) {
        String classes = element.getAttribute("active");
        return classes != null && Arrays.asList(classes.split(" ")).contains(className);
    }
    
    public boolean verifyTaskCommentPanel() throws Exception {

        if(hasClass(Task_FeedbackPanel, "active")) {
            click(wait.until(ExpectedConditions.elementToBeClickable(Task_FeedbackPanel)));
            wait.until(ExpectedConditions.visibilityOf(Task_FeedbackTask));
        }
        String feedbackTask = getText(Task_FeedbackTask);
        boolean feedbackTaskStatus = feedbackTask.equals("Feedback: Task");

        if(hasClass(Task_SampleAnswerPanel, "active")) {
            click(wait.until(ExpectedConditions.elementToBeClickable(Task_SampleAnswerPanel)));
            wait.until(ExpectedConditions.visibilityOf(Task_SampleAnswerTask));
        }
        String sampleAnswerTask = getText(Task_SampleAnswerTask);
        boolean sampleAnswerTaskStatus = sampleAnswerTask.equals("Sample Answer: Task");

        if(hasClass(Task_QuestionStemPanel, "active")) {
            click(wait.until(ExpectedConditions.elementToBeClickable(Task_QuestionStemPanel)));
            wait.until(ExpectedConditions.visibilityOf(Task_QuestionStemTask));
        }
        String questionStemTask = getText(Task_QuestionStemTask);
        boolean questionStemTaskStatus = questionStemTask.equals("Question Stem: Task");

        if(!Comment_FeedbackCommentPanel.isDisplayed()) {
            click(Comment_FeedbackCommentPanel);
            wait.until(ExpectedConditions.visibilityOf(Comment_FeedbackComment));
        }
        String feedbackComment = getText(Comment_FeedbackComment);
        boolean feedbackCommentStatus = feedbackComment.equals("Feedback: Comment");

        if(!Comment_SampleAnswerCommentPanel.isDisplayed()) {
            click(Comment_SampleAnswerCommentPanel);
            wait.until(ExpectedConditions.visibilityOf(Comment_SampleAnswerComment));
        }
        String sampleAnswerComment = getText(Comment_SampleAnswerComment);
        boolean sampleAnswerCommentStatus = sampleAnswerComment.equals("Sample Answer: Comment");

        if(!Comment_QuestionStemCommentPanel.isDisplayed()) {
            click(Comment_QuestionStemCommentPanel);
            wait.until(ExpectedConditions.visibilityOf(Comment_QuestionStemComment));
        }
        String questionStemComment = getText(Comment_QuestionStemComment);
        boolean questionStemCommentStatus = questionStemComment.equals("Question Stem: Comment");

        return feedbackTaskStatus && sampleAnswerTaskStatus && questionStemTaskStatus && feedbackCommentStatus && sampleAnswerCommentStatus && questionStemCommentStatus;
    }

    public void clickOpenTaskComment() throws Exception {
        click(Button_OpenTaskComment);
        wait.until(ExpectedConditions.visibilityOf(Fake_TextBox_Tasks));
    }

    public void createTaskAndComment(String taskDescription, String commentDescription, TaskStatus status, TaskPriority priority, int type) throws Exception {
        if (type == 1) {
            wait.until(ExpectedConditions.visibilityOf(Label_QuestionStemCommentsTasks));
        } else if(type == 2) {
            wait.until(ExpectedConditions.visibilityOf(Label_SampleAnswerCommentsTasks));
        } else {
            wait.until(ExpectedConditions.visibilityOf(Label_FeedbackCommentsTasks));
        }
        click(wait.until(ExpectedConditions.elementToBeClickable(Fake_TextBox_Tasks)));
		sendKeys(wait.until(ExpectedConditions.elementToBeClickable(Actual_TextArea_Task)), taskDescription);
		selectByText(SelectList_TaskStatus, status.toString());
		selectByText(SelectList_TaskPriority, priority.toString());
		Thread.sleep(1000);//need to replace this with Framework level wait for click in Driver Actions
		click(Button_Task_Submit);
        wait.until(ExpectedConditions.visibilityOf(AlertMessage_Task));
        wait.until(ExpectedConditions.invisibilityOf(AlertMessage_Task));
		//waitForTaskCommentCreation();
        click(wait.until(ExpectedConditions.elementToBeClickable(Fake_Text_Comments)));
        sendKeys(wait.until(ExpectedConditions.elementToBeClickable(Actual_TextArea_Comment)), commentDescription);
        click(Button_Comment_Submit);
        wait.until(ExpectedConditions.visibilityOf(AlertMessage_Comment));
        wait.until(ExpectedConditions.invisibilityOf(AlertMessage_Comment));
	}
    // String taskDescription, String commentDescription, TaskStatus status, TaskPriority priority, int type
    public boolean verifyAllTasksAndComments() throws Exception {
        click(Button_Close);
        clickOpenTaskComment();
        String feedbackTask = getText(Task_FeedbackTask);
        boolean feedbackTaskStatus = feedbackTask.equals("Feedback: Task");
        
        click(Task_SampleAnswerPanel);
        wait.until(ExpectedConditions.visibilityOf(Task_SampleAnswerTask));
        String sampleAnswerTask = getText(Task_SampleAnswerTask);
        boolean sampleAnswerTaskStatus = sampleAnswerTask.equals("Sample Answer: Task");

        click(Task_QuestionStemPanel);
        wait.until(ExpectedConditions.visibilityOf(Task_QuestionStemTask));
        String questionStemTask = getText(Task_QuestionStemTask);
        boolean questionStemTaskStatus = questionStemTask.equals("Question Stem: Task");

        String feedbackComment = getText(Comment_FeedbackComment);
        boolean feedbackCommentStatus = feedbackComment.equals("Feedback: Comment");

        click(Comment_SampleAnswerCommentPanel);
        wait.until(ExpectedConditions.visibilityOf(Comment_SampleAnswerComment));
        String sampleAnswerComment = getText(Comment_SampleAnswerComment);
        boolean sampleAnswerCommentStatus = sampleAnswerComment.equals("Sample Answer: Comment");


        click(Comment_QuestionStemCommentPanel);
        wait.until(ExpectedConditions.visibilityOf(Comment_QuestionStemComment));
        String questionStemComment = getText(Comment_QuestionStemComment);
        boolean questionStemCommentStatus = questionStemComment.equals("Question Stem: Comment");

        clickQuestionStemIcon();
        wait.until(ExpectedConditions.visibilityOf(Label_QuestionStemCommentsTasks));
        wait.until(ExpectedConditions.visibilityOf(Task_QuestionStemTask));
        String individualQuestionStemTask = getText(Task_QuestionStemTask);
        boolean individualQuestionStemTaskStatus = individualQuestionStemTask.equals("Question Stem: Task");
        String individualQuestionStemComment = getText(Comment_QuestionStemComment);
        boolean individualQuestionStemCommentStatus = individualQuestionStemComment.equals("Question Stem: Comment");

        clickSampleAnswerIcon();
        wait.until(ExpectedConditions.visibilityOf(Label_SampleAnswerCommentsTasks));
        wait.until(ExpectedConditions.visibilityOf(Task_SampleAnswerTask));
        String individualSampleAnswerTask = getText(Task_SampleAnswerTask);
        boolean individualSampleAnswerTaskStatus = individualSampleAnswerTask.equals("Sample Answer: Task");
        String individualSampleAnswerComment = getText(Comment_SampleAnswerComment);
        boolean individualSampleAnswerCommentStatus = individualSampleAnswerComment.equals("Sample Answer: Comment");

        clickFeedbackIcon();
        wait.until(ExpectedConditions.visibilityOf(Label_FeedbackCommentsTasks));
        wait.until(ExpectedConditions.visibilityOf(Task_FeedbackTask));
        String individualFeedbackTask = getText(Task_FeedbackTask);
        boolean individualFeedbackTaskStatus = individualFeedbackTask.equals("Feedback: Task");
        wait.until(ExpectedConditions.visibilityOf(Comment_FeedbackComment));
        String individualFeedbackComment = getText(Comment_FeedbackComment);
        boolean individualFeedbackCommentStatus = individualFeedbackComment.equals("Feedback: Comment");


        return feedbackTaskStatus && sampleAnswerTaskStatus && questionStemTaskStatus && feedbackCommentStatus && sampleAnswerCommentStatus && questionStemCommentStatus && individualQuestionStemCommentStatus && individualSampleAnswerCommentStatus && individualFeedbackCommentStatus && individualQuestionStemTaskStatus && individualSampleAnswerTaskStatus && individualFeedbackTaskStatus;
    }

    public AssessmentQuestionPage backToAssessmentPage() throws Exception {
        click(Button_GoBack);
        if(isDisplayed(PopUp_Button_DontSave)) {
        	//wait.until(ExpectedConditions.visibilityOf(PopUp_Button_DontSave));
        	wait.until(ExpectedConditions.elementToBeClickable(PopUp_Button_DontSave));
        	Thread.sleep(2000);
        	click(PopUp_Button_DontSave);
        }
        return new AssessmentQuestionPage();
    }

    public boolean checkProjectUsageTaskLink() throws Exception {
        wait.until(ExpectedConditions.visibilityOf(Label_AllCommentsTasks));
        click(Task_FeedbackPanelActive);
        wait.until(ExpectedConditions.visibilityOf(Task_FeedbackTask));
        String feedbackTask = getText(Task_FeedbackTask);
        boolean feedbackTaskStatus = feedbackTask.equals("Feedback: Task");
        return feedbackTaskStatus;
    }

    public boolean checkProjectUsageCommentLink() throws Exception {
        wait.until(ExpectedConditions.visibilityOf(Label_AllCommentsTasks));
        wait.until(ExpectedConditions.visibilityOf(Comment_FeedbackComment));
        String feedbackTask = getText(Comment_FeedbackComment);
        boolean feedbackTaskStatus = feedbackTask.equals("Feedback: Comment");
        return feedbackTaskStatus;
    }
    
    public String getVTWID() throws Exception{
		scrollToElement(TextBox_VTWId);
		wait.until(ExpectedConditions.attributeToBeNotEmpty(TextBox_VTWId, "value"));
		return getAttribute(TextBox_VTWId, "value");
	}
	
	public String getUUID() throws Exception{
		scrollToElement(TextBox_UUID);
		wait.until(ExpectedConditions.attributeToBeNotEmpty(TextBox_UUID, "value"));
		return getAttribute(TextBox_UUID, "value");
	}
	
	public AssessmentQuestionsDetailsPage manuallySetVTWID(String ID) throws Exception{
		clear(wait.until(ExpectedConditions.visibilityOf(TextBox_VTWId)));
		sendKeys(TextBox_VTWId, ID);
		return this;
	}
	
	public AssessmentQuestionsDetailsPage manuallySetUUID(String ID) throws Exception{
		clear(TextBox_UUID);
		sendKeys(TextBox_UUID, ID);
		return this;
	}
	
	public AssessmentQuestionsDetailsPage generateUUID() throws Exception{
		click(Button_GenerateUUID);
		waitUntilLoading();
		return this;
	}
	
	public AssessmentQuestionsDetailsPage toggleVTWSetting() throws Exception{
		click(Toogle_VTW);
		return this;
	}
	
	public AssessmentQuestionsDetailsPage toggleUUIDSetting() throws Exception{
		click(Toogle_UUID);
		return this;
	}
	
	public AssessmentQuestionsDetailsPage confirmPopup() throws Exception{
		click(Button_Confirm_Edit);
		return this;
	}
	
	public AssessmentQuestionsDetailsPage clickOnUpdate() throws Exception{
		scrollToElement(Button_Update);
		click(Button_Update);
		if (isDisplayed(PopUp_Button_SaveItorOK)) {
			wait.until(ExpectedConditions.elementToBeClickable(PopUp_Button_SaveItorOK));
			click(PopUp_Button_SaveItorOK);
		}
		waitUntilLoading();
		return this;
	}
	
	public String getAlertMessage() throws Exception {
		return getText(PopUp_Text_Message);
	}
	
	public AssessmentQuestionsDetailsPage clickOk() throws Exception{
		click(Button_Ok);
		Thread.sleep(2000);
		return this;
	}
	
	public AssessmentQuestionsDetailsPage clickNextButton() throws Exception{
		scrollToElement(Button_Next);
		click(Button_Next);
		waitUntilLoading();
		//waitForQuestionStemToPopulate();
		Thread.sleep(4000);
		return this;
	}
	
	public AssessmentQuestionsDetailsPage waitForQuestionStemToPopulate() throws Exception {
		switchToFrame(Iframe_QuestionStem);
		wait.until(ExpectedConditions.attributeToBeNotEmpty(TextArea_QuestionStem, "value"));
		return this;
	}
	
	public boolean isIDEditDisplayed() throws Exception{
		scrollDown(300);
		Thread.sleep(2000);
		return isDisplayed(Toogle_VTW) && isDisplayed(Toogle_UUID);
	}
	
	public AssessmentQuestionsDetailsPage openTaskPanel() throws Exception {
    	click(Button_OpenTaskPanel);
    	wait.until(ExpectedConditions.visibilityOf(TaskCommentPanel_Title));
    	//Thread.sleep(2000);
    	return this;
    }
	
	public AssessmentQuestionsDetailsPage clickOnTaskBox() throws Exception{
		click(TextBox_Tasks);
		return this;
	}
    
    public AssessmentQuestionsDetailsPage createTask(String description, String assignTo, TaskStatus status, TaskPriority priority, boolean attachment , String referenceAttachment) throws Exception {
    	Thread.sleep(4000);
    	click(wait.until(ExpectedConditions.elementToBeClickable(TextBox_Tasks)));
		sendKeys(wait.until(ExpectedConditions.elementToBeClickable(TextArea_TasksComments)), description);
		if(attachment) {
			updateOpacityforInputTags();
			sendKeys(InputFile_TaskAttachment, System.getProperty("user.dir")+"/src/main/resources/"+referenceAttachment);
		}
		selectByText(SelectList_Users, assignTo);
		selectByText(SelectList_TaskStatus, status.toString());
		selectByText(SelectList_TaskPriority, priority.toString());
		Thread.sleep(1000);//need to replace this with Framework level wait for click in Driver Actions
		click(Button_TaskComment_Submit);
		//waitForTaskCommentCreation();
		waitUntilLoading();
		return this;
	}
    
    public AssessmentQuestionsDetailsPage closeRecentTask() throws Exception {
    	click(Button_CommentPanel);
		Thread.sleep(2000);
    	click(Button_EditTask);
    	Thread.sleep(1000);
    	click(Button_ConfirmTaskEdit);
    	selectByText(SelectList_TaskStatus, "Fixed");
		Thread.sleep(1000);//need to replace this with Framework level wait for click in Driver Actions
		click(Button_TaskComment_Submit);
    	return this;
    }
    
    public AssessmentQuestionsDetailsPage openRecentTask() throws Exception {
    	click(Button_CommentPanel);
		Thread.sleep(2000);
    	click(Button_EditTask);
    	Thread.sleep(1000);
    	click(Button_ConfirmTaskEdit);
    	selectByText(SelectList_TaskStatus, TaskStatus.OPEN.toString());
		Thread.sleep(1000);//need to replace this with Framework level wait for click in Driver Actions
		click(Button_TaskComment_Submit);
    	return this;
    }
	

}
