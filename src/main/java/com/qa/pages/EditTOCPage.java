package com.qa.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.NodeType;
import com.qa.testdata.ProjectExportTypes;
import com.qa.testdata.WorkFlowStatus;
import com.qa.util.TestUtil;

public class EditTOCPage extends CommonPage {

	@FindBy(xpath="//section[contains(@class,'wrapper')]/child::div[contains(@class,'page-title')]")
	private WebElement Text_ProjectName;

	@FindBy(xpath="//div[@class='from-container']//div[@class='page-title']")
	private WebElement Text_PageTitle;

	@FindBy(xpath="//button[@aria-label='add']")
	private WebElement Button_AddNode;
	
	@FindBy(xpath="//button[normalize-space()='Import']")
	private WebElement Button_Import;
	
	@FindBy(xpath="//a[normalize-space()='Import TOC']")
	private WebElement ImportPopup_Link_ImporToc;
	
	@FindBy(xpath="//a[normalize-space()='Import Module']")
	private WebElement ImportPopup_Link_ImportModule;
	
	@FindBy(id="associate_slo")
	private WebElement ImportPopUp_Checkbox_IncludeSLO;
	
	@FindBy(xpath="//input[@name='tocFile' and @type='file']")
	private WebElement ImportPopUp_Input_SelectFile;
		
	@FindBy(xpath="//button[normalize-space()='Close']")
	private WebElement ImportPopUp_Button_Close;
	
	@FindBy(xpath="//button[contains(text(),'Import')]")
	private WebElement ImportPopUp_Button_Import;
	
	@FindBy(xpath="//button[@title='Project Locking']")
	private WebElement Button_LockProject;
	
	@FindBy(xpath="//button[normalize-space()='Search']")
	private WebElement Button_Search;
	
	@FindBy(xpath="//button[normalize-space()='Mapped EDUPUB Courses']")
	private WebElement Button_MappedEdupubCourses;
	
	@FindBy(xpath="//button[normalize-space()='Publish']")
	private WebElement Button_Publish;
	
	@FindBy(xpath="//button[normalize-space()='Republish']")
	private WebElement Button_RePublish;
	
	@FindBy(xpath="//button[normalize-space()='Export Toc Summary']")
	private WebElement Button_ExportTocSummary;
	
	@FindBy(xpath="//button[normalize-space()='Export Course']")
	private WebElement Button_Export_Course;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement Button_OK;
	
	@FindBy (xpath = "//div[@id='chooseStatus ']")
	private List<WebElement> List_Button_Workflow;
	String xpath_Button_Workflow = "//div[@id='chooseStatus ']";
	
	@FindBy (xpath = "//div[normalize-space()='Ready For Export']")
	private WebElement Button_ReadyForExport;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu ']/li")
	private List<WebElement> List_Workflow_Status;
	
	@FindBy(xpath="//button[normalize-space()='Project Preview']")
	private WebElement Button_ProjectPreview;

	@FindBy(xpath="//span[@ng-show='isData']")
	private WebElement Toogle_makeTocPublic;
	
	@FindBy(xpath="//span[contains(@class,'globalchk')]//span[contains(@class,'cr')]")
	private WebElement CheckBox_MakePublicInLOR;
	
	@FindBy(xpath="//button[@class='btn btn-secondary']")
	private WebElement PublishPopUp_Button_Cancel;
	
	@FindBy(xpath="//div[@class='modal-footer']//button[normalize-space()='Publish']")
	private WebElement PublishPopUp_Button_Publish;
	
	@FindBy(xpath="//div[@class='context-wrap d-inline-flex']")
	private List<WebElement> List_Node_Containers;
	
	@FindBy(xpath="//span[contains(@class,'cont-text')]")
	private List<WebElement> List_Nodes;

	@FindBy(xpath="//span[contains(@class,'nodeType')]|//label[contains(@class,'lesson-topic')]")
	private List<WebElement> List_Nodes_Type;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Edit Project']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_EditProject;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Duplicate Project']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_DuplicateProject;

	@FindBy(xpath="//button[@class='confirm']")
	private WebElement PopUp_Confirm;
	
	@FindBy(xpath="//button[@class='cancel']")
	private WebElement PopUp_Cancel;
	
	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Assessment']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_Assessment;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Assignments']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_Assignments;

	@FindBy(xpath="//div[@class='tocmenu-name ebook' and text()='Ebook']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_Ebook;

	@FindBy(xpath="//div[@class='tocmenu-name ebook' and text()='Group Activity']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_GroupActivity;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Forums']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_Forums;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Manage Patterns']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_ManagePatterns;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Manage Resources']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_ManageResources;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Manage Templates']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_ManageTemplates;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Manage Assets']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_ManageAssets;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Manage Users']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_ManageUsers;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Comment']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_Comment;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Project Usage Dashboard']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_ProjectUsageDashboard;

	@FindBy(xpath="//div[@class='tocmenu-name' and text()='Utilities']/parent::div/parent::a/div")
	private WebElement RightPanel_Link_Utilities;
	
	@FindBy(xpath="//span[contains(@tooltip-html-unsafe,'Assign Module')]")
	private WebElement Icon_AssignModule;

	@FindBy(xpath="//span[@class='glyphicon glyphicon-file']/parent::button[@aria-label='content']")
	private WebElement Icon_Content;
	
	@FindBy(id="delete-node ")
	private WebElement Icon_DeleteNode;
	
	@FindBy(id="collapse")
	private WebElement Button_Expand;
	
	@FindBy(id="expand")
	private WebElement Button_Collapse;
	
	@FindBy(id="global_expand_collapse")
	private WebElement TOC_Expand_Spinner;
	
	@FindBy(xpath="//div[contains(@class,'toccontent-container')]")
	private WebElement TOC_Container;
	
	private String EditNodeButton_Xpath="//span[contains(@class,'nodeType') and contains(text(),'xxxxxxxxxx')]/parent::span/descendant::button[@id='edit-node']";
	
	@FindBy(xpath="//div[contains(@class,'workflow-dropdown')]/preceding-sibling::span[contains(@class,'cont-text')]/descendant::button[@id='edit-node']")
	private WebElement Button_Module_EditNode;
	
	@FindBy(xpath="//div[contains(@class,'workflow-dropdown')]/preceding-sibling::span[contains(@class,'cont-text')]/descendant::span[contains(@class,'plus-sign')]/parent::button")
	private WebElement Button_Module_AddNode;
	
	@FindBy(xpath="//label[text()='LESSON']/parent::span/following-sibling::span[contains(@class,'toc-plus')]/button/span[contains(@class,'plus-sign')]")
	private WebElement Button_Lesson_AddNode;
	
	@FindBy(xpath="//label[text()=' TOPIC']/parent::span/following-sibling::span[contains(@class,'toc-plus')]/button/span[contains(@class,'plus-sign')]")
	private WebElement Button_Topic_AddNode;
	
	@FindBy(xpath="//label[text()='LESSON']/parent::span/following-sibling::button[@id='edit-node']")
	private WebElement Button_Lesson_EditNode;
	
	@FindBy(xpath="//label[normalize-space()='TOPIC']/parent::span/following-sibling::button[@id='edit-node']")
	private WebElement Button_Topic_EditNode;
	
	@FindBy(xpath="(//span[@class='fa fa-exclamation-triangle ng-scope'])[1]")
	private WebElement Icon_Alert;
	
	@FindBy(xpath="//span[@class='glyphicon glyphicon-link ']")
	private WebElement Button_EditAssessmentNode;
	
	@FindBy(className="tooltip-inner")
	private WebElement Text_ContentScreen_Alert_ToolTipText;
	
	@FindBy(xpath="//div[@class='toc-panel-left tc-cont ng-scope not-allow']")
	private WebElement TOC_Overlay;
	
	@FindBy(xpath="//span[contains(@class,'sub-toc-icon ng-scope')][normalize-space()='assignment_turned_in']/parent::span/following-sibling::span[@class='cont-text ng-binding']//span[@class='fa fa-exclamation-triangle ng-scope']")
	private WebElement Icon_AssessmentNode_Alert;
	
	@FindBy(xpath="//span[contains(@class,'sub-toc-icon ng-scope')][normalize-space()='assignment_turned_in']/parent::span/following-sibling::span[@class='cont-text ng-binding']//div[contains(@class,'tooltip-inner')]")
	private WebElement Text_AssessmentNode_Alert_ToolTipText;
	
	@FindBy(xpath="//span[normalize-space()='ASSESSMENT']//following-sibling::button[@id='delete-node ']")
	private WebElement Icon_AssessmentNode_Delete;
	
	@FindBy(xpath="//label[normalize-space()='TOPIC']/parent::span/following-sibling::button[@id='delete-node ']")
	private WebElement Icon_TopicNode_Delete;
	
	/*************** EXPORT RELATED ************/
	
	@FindBy(xpath="//button[normalize-space()='Select All']")
	private WebElement Button_SelectAll;
	
	@FindBy(xpath="//button[normalize-space()='Yes, export it!']")
	private WebElement Button_YesExport;
	
	@FindBy(id="course_export_options")
	private WebElement SelectList_ExportOptions;
	
	//qti export checkboxes
	
	@FindBy(id="test")
	private WebElement Radio_Test;
	
	@FindBy(id="final")
	private WebElement Radio_Final;
	
	@FindBy(id="EntireMapping")
	private WebElement CheckBox_EntireMapping;
	
	
	/*************** ASSIGN USER POPUP OBJECTS ************/
	
	@FindBy(id="selectRole")
	private WebElement SelectList_SelectRole;
	
	@FindBy(id="selectUser")
	private WebElement SelectList_SelectUser;
	
	@FindBy(xpath="//button[normalize-space()='Add another user']")
	private WebElement Button_AddAnotherUser;
	
	@FindBy(xpath="//button[contains(@class,'btn btn-default')][normalize-space()='CANCEL']")
	private WebElement Button_Cancel;
	
	@FindBy(xpath="//tr[@ng-repeat='user in listUser']/td[1]")
	private List<WebElement> List_Selected_User;
	
	@FindBy(xpath="//tr[@ng-repeat='user in listUser']/td[2]")
	private List<WebElement> List_Selected_Role;
	
	@FindBy(xpath="//button[@class='btn btn-icon']")
	private WebElement Button_DeleteUser;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/child::p")
	private WebElement PopUp_Text_Message;
	/******************************************************/
	
	@FindBy(id="course_export_options")
	private WebElement SelectList_ExportType;
	
	public EditTOCPage() {
		waitUntilLoading();
	}

	public String getProjectName() throws Exception {
		return getText(Text_ProjectName);
	}

	public String getPageTitle() throws Exception {
		return getText(Text_PageTitle).substring(0,17).trim();
	}

	public AddNodePopupPage clickOnAddNode() throws Exception {
		click(Button_AddNode);
		return new AddNodePopupPage();
	}
	
	public AddNodePopupPage addLessonNode() throws Exception {
		click(Button_Module_AddNode);
		return new AddNodePopupPage();
	}
	
	public AddNodePopupPage addTopicNode() throws Exception {
		click(Button_Lesson_AddNode);
		return new AddNodePopupPage();
	}
	
	public AddNodePopupPage addNodeUnderTopic() throws Exception {
		click(Button_Topic_AddNode);
		return new AddNodePopupPage();
	}
	
	public EditTOCPage waitForTOCOverlay() {
		wait.until(ExpectedConditions.visibilityOf(TOC_Overlay));
		wait.until(ExpectedConditions.invisibilityOf(TOC_Overlay));
		return this;
	}
	
	public EditTOCPage waitForExpansion() {
	    try {
	        if (isDisplayed(TOC_Expand_Spinner)) {
	            wait.until(ExpectedConditions.visibilityOf(TOC_Expand_Spinner));
	            wait.until(ExpectedConditions.invisibilityOf(TOC_Expand_Spinner));
	        }
	    } catch (Exception e) {
	        // Log the exception if needed, but do not break the execution
	        System.out.println("Expand loader is not displayed or encountered an issue: " + e.getMessage());
	    }
	    return this;
	}
	
	public EditTOCPage expandTOCnodes() throws Exception {
		click(Button_Expand);
		if(isDisplayed(PopUp_Text_Message)) {
			if(getText(PopUp_Text_Message).contains("Please wait till the page is completely loaded!"))
			{
				confirmPopUp();
				click(Button_Expand);
			}
		}
		waitForExpansion();
		//waitForTOCOverlay();
		waitUntilLoading();
		return this;
	}

	public EditTOCPage collapseTOCnodes() throws Exception {
		click(Button_Collapse);
		//waitUntilLoading();
		return this;
	}
	
	public List<String> getAllNodes() throws Exception {
		List<String> NodeNamesWithType = getText(List_Nodes);
		List<NodeType> NodeTypes = Arrays.asList(NodeType.values());
		List<String> NodeName = new ArrayList<String>();
		/** Removing Node Type from Node Name*/
		for(String Name:NodeNamesWithType) {
			String text = Name;
			int flag = 0;
			for(NodeType Type: NodeTypes) {
				if(text.contains(Type.toString())) {
					text = (text.contains("Workflow")) ? (text.replace("Workflow", "")).trim() : text;
					text = (text.replaceAll(Type.toString(), "")).trim();
					flag++;
				}
			}
			if(!NodeName.contains(Name) && flag==0)
				NodeName.add(Name.trim());
			else
				NodeName.add(text);
		}
		return NodeName;
	}
	
	public HashMap<String,Integer> getAllNodeTypeCount() throws Exception {
		HashMap<String, Integer> nodeTypesWithCount = new HashMap<String, Integer>();
		List<String> nodeTypeList = getText(List_Nodes_Type);
		Collections.replaceAll(nodeTypeList, "", "MODULES");
		for(String word: nodeTypeList) {
		  Integer count = nodeTypesWithCount.get(word);          
		  nodeTypesWithCount.put(word, (count==null) ? 1 : count+1);
		}
		return nodeTypesWithCount;
	}
	
	public AssessmentQuestionPage clickOnEditAssessmentNode() throws Exception {
		click(Button_EditAssessmentNode);
		return new AssessmentQuestionPage();
	}

	public AssessmentPage clickOnAssessment() throws Exception {
		click(RightPanel_Link_Assessment);
		return new AssessmentPage();
	}
	
	public AssignmentsPage clickOnAssignment() throws Exception {
		click(RightPanel_Link_Assignments);
		return new AssignmentsPage();
	}

	public ForumsPage clickOnForum() throws Exception {
		scrollToElement(RightPanel_Link_Forums);
		click(RightPanel_Link_Forums);
		return new ForumsPage();
	}
	
	public AssetPage clickOnManageAssets() throws Exception {
		scrollDown(1000);
		scrollToElement(RightPanel_Link_ManageAssets);
		click(RightPanel_Link_ManageAssets);
		return new AssetPage();
	}
	
	public GroupActivityPage clickOnGroupActivity() throws Exception {
		scrollDown(400);
		scrollToElement(RightPanel_Link_GroupActivity);
		click(RightPanel_Link_GroupActivity);
		return new GroupActivityPage();
	}
	
	public PatternManagementPage clickOnManagePattern() throws Exception {
		scrollDown(700);
		scrollToElement(RightPanel_Link_ManagePatterns);
		click(RightPanel_Link_ManagePatterns);
		return new PatternManagementPage();
	}
	
	public TemplateManagementPage clickOnManageTemplate() throws Exception {
		scrollDown(700);
		scrollToElement(RightPanel_Link_ManageTemplates);
		click(RightPanel_Link_ManageTemplates);
		return new TemplateManagementPage();
	}
	
	public ProjectUsageDashboardPage clickOnProjectUsageDashboard() throws Exception {
		scrollToElement(RightPanel_Link_ProjectUsageDashboard);
		click(RightPanel_Link_ProjectUsageDashboard);
		return new ProjectUsageDashboardPage();
	}
	
	public EditTOCPage duplicateProject() throws Exception {
		click(RightPanel_Link_DuplicateProject);
		Thread.sleep(2000);
		click(PopUp_Confirm);
		waitUntilLoading();
		click(PopUp_Confirm);
		return this;
	}
	
	public ProjectUserManagementPage clickOnManageUsers() throws Exception {
		scrollToElement(RightPanel_Link_ManageUsers);
		click(RightPanel_Link_ManageUsers);
		return new ProjectUserManagementPage();
	}
	
	public EbookManagementPage clickOnEbook() throws Exception {
		scrollDown(700);
		scrollToElement(RightPanel_Link_Ebook);
		click(RightPanel_Link_Ebook);
		return new EbookManagementPage();
	}
	
	public EditTOCPage clickOnAssignModule() throws Exception {
		click(Icon_AssignModule);
		waitUntilLoading();
		return this;
	}
	
	public EditTOCPage assignModule(String userName,String role) throws Exception {
		selectByText(SelectList_SelectRole, role);
		selectByValue(SelectList_SelectUser, userName);
		click(Button_AddAnotherUser);
		waitUntilLoading();
		return this;
	}
	
	public List<HashMap<String,String>> getSelectedUsersWithRole() throws Exception {
		List<HashMap<String,String>> mapList = new ArrayList<HashMap<String,String>>();
		for(int i =0; i< List_Selected_User.size(); i++) {
			HashMap<String,String> map = new HashMap<String,String>();
				map.put(getText(List_Selected_User.get(i)), getText(List_Selected_Role.get(i)));
				mapList.add(map);
		}
		return mapList;
	}
	
	/**
	 * This method currently supports if there's only 1 user assigned, further modification can be made
	 * to handle unAssignment when multiple users are assigned to a module
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public EditTOCPage unAssignModule(String userName) throws Exception {
		if(getSelectedUsersWithRole().get(0).keySet().contains(userName)) {
			click(Button_DeleteUser);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.textToBePresentInElement(PopUp_Text_Message, "You will not be able to recover this User!"));
			confirmPopUp();
			return this;
		}else {
			throw new Exception("User not found!! Unable to unAssign!!");
		}
		
	}
	
	public EditTOCPage closeAssignUserPopUp() throws Exception {
		click(Button_Cancel);
		Thread.sleep(1000);
		return this;
	}
	
	public String getAlertMessage() throws Exception {
		return getText(PopUp_Text_Message);
	}
	
	public EditTOCPage confirmPopUp() throws Exception {
		try{
			fluentWait.until(ExpectedConditions.elementToBeClickable(PopUp_Confirm));
			click(PopUp_Confirm);
		}catch (Exception ne) {}
		return this;
	}
	
	public CourseEditorPage openContentScreen(/*String contentScreenName*/) throws Exception {
		click(Icon_Content);
		//waitUntilLoading();
		return new CourseEditorPage();
	}
	
	public EditTOCPage deleteNode() throws Exception {
		click(Icon_DeleteNode);
		Thread.sleep(2000);
		confirmPopUp();
		waitUntilLoading();
		return this;
	}
	
	public EditTOCPage publishProject(/**boolean makeTocPublic*/) throws Exception {
		click(Button_Publish);
		Thread.sleep(3000);
		click(wait.until(ExpectedConditions.elementToBeClickable(PublishPopUp_Button_Publish)));
//		if(makeTocPublic == true) {
//			click(Toogle_makeSTocPublic);
//			click(CheckBox_MakePublicInLOR);
//		}
		waitUntilLoading();
		return this;
	}
	
	public EditTOCPage clickOK() throws Exception{
		click(Button_OK);
		return this;
	}
	
	public EditTOCPage waitForProjectPublishToComplete() throws Exception {
		int i=0;
		while(isDisplayed(Button_Export_Course) || i<10){
			hardRefresh();
			if(isDisplayed(Button_Export_Course))
					break;
			i++;
		}
		return this;
	}
	
	public EditTOCPage exportTOCSummary(/**String exportType*/) throws Exception {
		click(Button_ExportTocSummary);
		//selectByValue(SelectList_ExportType, exportType);
		//click(PopUp_Confirm);
		waitUntilLoading();
		return this;
	}
	
	/**
	 * This method can be updated in future to change workflow status based on module/node name 
	 */
	public EditTOCPage updateWorkFlowStatusForAllModules(WorkFlowStatus status) throws Exception {
		for (WebElement workflow : List_Button_Workflow) {
			click(workflow);
			click(getWebElementBySubString(List_Workflow_Status,status.toString()));
			waitUntilLoading();
		}
		//This is to bypass the issue where page redirects to DashBoard! This needs to be checked with Developer!
		Thread.sleep(3000);
		hardRefresh();
		hardRefresh();
		return this;
	}
	
	public EditTOCPage exportCourse(ProjectExportTypes exportType) throws Exception{
		click(Button_Export_Course);
		waitUntilLoading();
		selectByValue(SelectList_ExportOptions, exportType.toString());
		wait.until(ExpectedConditions.elementToBeClickable(Button_SelectAll));
		click(Button_SelectAll);
		click(Button_YesExport);
		waitUntilLoading();
		click(Button_OK);
		return this;
	}
	
	public ProjectPreviewPage clickOnProjectPreview() throws Exception {
		click(Button_ProjectPreview);
		Thread.sleep(3000);
		return new ProjectPreviewPage();
	}
	
	public EditTOCPage importTOCPackage(String packageToImport, boolean includeSLO) throws Exception {
		click(Button_Import);
		//wait.until(ExpectedConditions.visibilityOf(ImportPopUp_Input_SelectFile));
		Thread.sleep(5000);
		updateOpacityforInputTags();
		enableHiddenInputTags();
		sendKeys(ImportPopUp_Input_SelectFile, System.getProperty("user.dir")+TestUtil.updateFilePathSeparatorBasedOnOS("/src/main/java/com/qa/testdata/")+packageToImport);
		if(includeSLO) click(ImportPopUp_Checkbox_IncludeSLO);
		click(ImportPopUp_Button_Import);
		waitUntilLoading();
		Thread.sleep(5000);
		return this;
	}
	
	public EditNodePopUpPage editNode(NodeType nodeType) throws Exception {
		click(getEditButtonElement(nodeType));
		return new EditNodePopUpPage();
	}
	
	public EditNodePopUpPage editModule() throws Exception {
		click(Button_Module_EditNode);
		return new EditNodePopUpPage();
	}
	
	public EditNodePopUpPage editLesson() throws Exception {
		click(Button_Lesson_EditNode);
		return new EditNodePopUpPage();
	}
	
	public EditNodePopUpPage editTopic() throws Exception {
		click(Button_Topic_EditNode);
		return new EditNodePopUpPage();
	}
	
	private WebElement getEditButtonElement(NodeType nodeType) {
		return TOC_Container.findElement(By.xpath(EditNodeButton_Xpath.replace("xxxxxxxxxx", nodeType.toString())));
	}
	
	public String getAlertStatus() throws Exception {
		mouseHover(Icon_Alert);
		Thread.sleep(2000);
		return getText(wait.until(ExpectedConditions.visibilityOf(Text_ContentScreen_Alert_ToolTipText)));
	}

	public String getAssessmentNodeAlertStatus() throws Exception {
		mouseHover(Icon_AssessmentNode_Alert);
		return getText(wait.until(ExpectedConditions.visibilityOf(Text_AssessmentNode_Alert_ToolTipText)));
	}
	
	public String getAssessmentNodeAlertColor() throws Exception {
		return getAttribute(Icon_AssessmentNode_Alert, "style");
	}
	
	public EditTOCPage deleteAssessmentNode() throws Exception	{
		click(Icon_AssessmentNode_Delete);
		Thread.sleep(2000);
		confirmPopUp();
		waitUntilLoading();
		return this;
	}
	
	public EditTOCPage deleteTopicNode() throws Exception	{
		click(Icon_TopicNode_Delete);
		Thread.sleep(2000);
		confirmPopUp();
		waitUntilLoading();
		return this;
	}
}
