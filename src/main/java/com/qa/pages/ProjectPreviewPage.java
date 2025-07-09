package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.TaskPriority;
import com.qa.testdata.TaskStatus;

public class ProjectPreviewPage extends CommonPage {

	@FindBy(xpath="//div[@class='pull-middle ng-binding']")
	private WebElement Text_PageTitle;
	
	@FindBy(xpath="//back-to-dashboard[@id='previewComment']/child::button")
	private WebElement Button_BackToTOC;
	
	@FindBy(xpath="//button[normalize-space()='Previous']")
	private WebElement Button_Previous;
	
	@FindBy(xpath="//button[contains(text(),'Next')]")
	private WebElement Button_Next;
	
	@FindBy(id="openComment")
	private WebElement Icon_TasksAndComments;
	
	@FindBy(xpath="//div[@class='desktop-view sprite']")
	private WebElement Icon_DesktopView;
	
	@FindBy(xpath="//div[@class='tablet-view sprite']")
	private WebElement Icon_TabletView;
	
	@FindBy(xpath="//div[@class='small-tablet-view sprite']")
	private WebElement Icon_SmallTabletView;
	
	@FindBy(xpath="//div[@class='iphone5-view sprite']")
	private WebElement Icon_MobileView;
	
	@FindBy(id="testCont")
	private WebElement iframe_testMode;
	
	@FindBy(id="contentPreviewiframe")
	private WebElement iframe_MiniTOC;
	
	@FindBy(xpath="//div[@class='toccontent-container']")
	private WebElement List_TOCNodes;
	
	@FindBy(xpath="//a[contains(@class,'link-to-preview')]")
	private List<WebElement> List_SelectableNodes;
	
	@FindBy(xpath="//a[contains(@class,'link-to-preview active')]")
	private WebElement Active_Node;
	
	@FindBy(id="content_preview_iframe3")
	private WebElement iframe_Editor;
	
	@FindBy(xpath="//section[@class='container']/p")
	private WebElement Text_Editor;
	
	@FindBy(id="backBtn")
	private WebElement Button_TOC_Back;
	
	@FindBy(id="nextBtn")
	private WebElement Button_TOC_Next;

    @FindBy(id="editBtn")
    private WebElement Button_Edit;
	/*****************Task Comment Section Elements****************/
	
	@FindBy(xpath="//div[@class='comments-wrapper']//div[@id='fake-comment-box']")
	private WebElement TextBox_Tasks;
	
	@FindBy(xpath="//div[@class='comments-wrapper ']//div[@id='fake-comment-box']")
	private WebElement Text_Comments;
	
	@FindBy(xpath="//div[@class='actual-comment-box']//textarea[@id='comment']")
	private WebElement TextArea_TasksComments;
	
	@FindBy(id="ticket_attachment_task")
	private WebElement InputFile_TaskAttachment;
	
	@FindBy(id="ticket_attachment_comment")
	private WebElement InputFile_CommentAttachment;
	private String css_InputFile_CommentAttachment = "input[id=\"ticket_attachment_comment\"]";
	
	@FindBy(id="assigned_to")
	private WebElement SelectList_Users;
	
	@FindBy(id="status")
	private WebElement SelectList_TaskStatus;
	
	@FindBy(id="priority")
	private WebElement SelectList_TaskPriority;
	
	@FindBy(xpath="//div[@class='actual-comment-box']//button[@type='button'][normalize-space()='Cancel']")
	private WebElement Button_TaskComment_Cancel;
	
	@FindBy(xpath="//div[@class='actual-comment-box']//button[@type='submit'][normalize-space()='Submit']")
	private WebElement Button_TaskComment_Submit;
	
	@FindBy(xpath="//div[@id='please-wait' and @ng-show='pleaseWaitTask']")
	private WebElement Label_Task_PleaseWait;
	
	@FindBy(id="alert-message-task")
	private WebElement AlertMessage_Task;
	
	@FindBy(id="alert-message-comment")
	private WebElement AlertMessage_Comment;
	
	/******************************************************/
	
	
	public ProjectPreviewPage() {
		waitUntilLoading();
	}
	
	public String getPageTitle() throws Exception {
		return getText(Text_PageTitle);
	}
	
	public List<String> getTOCList() throws Exception {
		String list = getText(List_TOCNodes);
		String nodes[] = list.split("\\r?\\n");
		List<String> node_list = new ArrayList<String>();
		for(String node: nodes) {
			node_list.add(node);
		}
		return node_list;
	}
	
	public String getEditorText() throws Exception {
		switchToFrame(iframe_Editor);
		String text = getText(Text_Editor);
		switchToDefaultContent();
		return text;
	}
	
	public EditTOCPage clickOnBackToTOC() throws Exception {
		click(Button_BackToTOC);
		return new EditTOCPage();
	}
	
	public ProjectPreviewPage selectNode(String nodeName) throws Exception {
		click(getWebElementByExpectedText(List_SelectableNodes, nodeName));
		waitUntilLoading();
		return this;
	}
	
	public ProjectPreviewPage openTaskCommentSection() throws Exception {
		Thread.sleep(5000);
		click(wait.until(ExpectedConditions.elementToBeClickable(Icon_TasksAndComments)));
		return this;
	}
	
	public ProjectPreviewPage createTask(String description, String assignTo, TaskStatus status, TaskPriority priority, boolean attachment , String referenceAttachment) throws Exception {
		click(wait.until(ExpectedConditions.visibilityOf(TextBox_Tasks)));
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
	
	public ProjectPreviewPage createComment(String description) throws Exception {
		click(wait.until(ExpectedConditions.visibilityOf(Text_Comments)));
		sendKeys(wait.until(ExpectedConditions.elementToBeClickable(TextArea_TasksComments)), description);
		click(Button_TaskComment_Submit);
		waitUntilLoading();
		return this;
	}
	
	public ProjectPreviewPage createComment(String description, String referenceAttachment) throws Exception {
		click(wait.until(ExpectedConditions.visibilityOf(Text_Comments)));
		sendKeys(wait.until(ExpectedConditions.elementToBeClickable(TextArea_TasksComments)), description);
		updateOpacity(css_InputFile_CommentAttachment);
		sendKeys(InputFile_CommentAttachment, System.getProperty("user.dir")+"/src/main/resources/"+referenceAttachment);
		click(Button_TaskComment_Submit);
		waitUntilLoading();
		return this;
	}
	
	public String getTaskAlertMessage() throws Exception {
		return getText(AlertMessage_Task).replace("×", "").trim();
	}
	
	public String getCommentAlertMessage() throws Exception {
		return getText(AlertMessage_Comment);
	}
	
	public String getActiveNode() throws Exception {
		//switchToFrame(iframe_testMode);
		switchToFrame(iframe_MiniTOC);
		String activeNode = getText(Active_Node);
		switchToDefaultContent();
		return activeNode;
	}
	
	/**
	 * This method is to fix the issue in Jenkins where Feedback button is overlapping Comments Submit/Cancel Button
	 * This should be removed after the upcoming changes related to Feedback button is deployed
	 * @return
	 * @throws Exception 
	 */
	public ProjectPreviewPage zoomOut() throws Exception {
		jsDriver.executeScript("document.body.style.MozTransform=\"scale(0.9)\"");
		//sendKeys(body, Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		return this;
	}
	
	/**
	 * This method is to fix the issue in Jenkins where Feedback button is overlapping Comments Submit/Cancel Button
	 * This should be removed after the upcoming changes related to Feedback button is deployed
	 * @return
	 * @throws Exception 
	 */
	public ProjectPreviewPage zoomIn() throws Exception {
		jsDriver.executeScript("document.body.style.MozTransform=\"scale(1)\"");
		//sendKeys(body, Keys.chord(Keys.CONTROL, Keys.ADD));
		return this;
	}
	
//	private void waitForTaskCommentCreation() {
//		wait.until(ExpectedConditions.visibilityOf(Label_Task_PleaseWait));
//		wait.until(ExpectedConditions.invisibilityOf(Label_Task_PleaseWait));
//	}

    public boolean isEditButtonDisplayed() throws Exception {
        return isDisplayed(Button_Edit);
    }

    public CourseEditorPage clickOnEdit() throws Exception {
    	wait.until(ExpectedConditions.elementToBeClickable(Button_Edit));
    	click(Button_Edit);
    	wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToNewTab();
        return new CourseEditorPage();
    }

}
