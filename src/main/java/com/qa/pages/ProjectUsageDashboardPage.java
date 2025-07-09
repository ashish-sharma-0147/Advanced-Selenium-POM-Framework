package com.qa.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.TaskPriority;
import com.qa.testdata.TaskStatus;

/**
 * 
 * @author sharmaa11
 *
 */
public class ProjectUsageDashboardPage extends CommonPage {

	@FindBy(xpath="//div[@class='pull-middle ng-binding']")
	private WebElement ProjectTitle;

	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;

	@FindBy(id="export_btn")
	private WebElement Button_ExportCourse;

	@FindBy(xpath="//button[normalize-space()='Project Preview']")
	private WebElement Button_ProjectPreview;

	@FindBy(xpath="//div[@class='db-blk ng-scope']/label")
	private List<WebElement> List_Item_Headers;

	@FindBy(xpath="//div[@class='db-blk ng-scope']/div[1]")
	private List<WebElement> List_Item_Counts;

	@FindBy(xpath="//div[contains(@class,'panel-heading')]")
	private List<WebElement> Widget_Headings;

	@FindBy(id="status_type")
	private WebElement SelectList_WorkflowStatus_Type;

	@FindBy(xpath="//div[text()='Users']/parent::div/div[2]/table/thead/tr/th")
	private List<WebElement> List_UsersWidget_TableHeaders;

	@FindBy(xpath="//div[text()='Users']/parent::div/div[2]/table/tbody/tr")
	private List<WebElement> List_UserWidget_TableRows;
	private String xpath_UserListing = "//div[text()='Users']/parent::div/div[2]/table/tbody/tr";

	@FindBy(xpath="//div[text()='Assets']/parent::div/div[2]/table/thead/tr/th")
	private List<WebElement> List_AssetsWidget_TableHeaders;

	@FindBy(xpath="//div[text()='Assets']/parent::div/div[2]/table/tbody/tr")
	private List<WebElement> List_AssetsWidget_TableRows;
	private String xpath_AssetsListing = "//div[text()='Assets']/parent::div/div[2]/table/tbody/tr";
	
	@FindBy(xpath="//div[text()='Assets']/parent::div/div[2]/table/tbody/tr/td[1]")
	private List<WebElement> List_AssetsWidget_AssetName;
	
	@FindBy(xpath="//div[text()='Assets']/parent::div/div[2]/table/tbody/tr/td[2]")
	private List<WebElement> List_AssetsWidget_AssetCount;

	@FindBy(xpath="//div[text()='Assessment']/parent::div/div[2]/table/thead/tr/th")
	private List<WebElement> List_AssessnemtWidget_TableHeaders;

	@FindBy(xpath="//div[text()='Assessment']/parent::div/div[2]/table/tbody/tr")
	private List<WebElement> List_AssessmentWidget_TableRows;
	private String xpath_AssessmentListing = "//div[text()='Assessment']/parent::div/div[2]/table/tbody/tr";

	@FindBy(xpath="//div[text()='Widgets']/parent::div/div[2]/table/thead/tr/th")
	private List<WebElement> List_WidgetsWidget_TableHeaders;

	@FindBy(xpath="//div[text()='Widgets']/parent::div/div[2]/table/tbody/tr")
	private List<WebElement> List_WidgetsWidget_TableRows;
	
	@FindBy(xpath="//div[text()='Widgets']/parent::div/div[2]/table/tbody/tr/td[1]")
	private List<WebElement> List_WidgetsWidget_WidgetName;
	
	@FindBy(xpath="//div[text()='Widgets']/parent::div/div[2]/table/tbody/tr/td[2]")
	private List<WebElement> List_WidgetsWidget_WidgetCount;

	@FindBy(xpath="//div[text()='Patterns']/parent::div/div[2]/table/thead/tr/th")
	private List<WebElement> List_PatternsWidget_TableHeaders;

	@FindBy(xpath="//div[text()='Patterns']/parent::div/div[2]/table/tbody/tr")
	private List<WebElement> List_PatternsWidget_TableRows;

	@FindBy(xpath="//div[text()='Workflow Status']/parent::div/parent::div/following-sibling::div/table/thead/tr/th")
	private List<WebElement> List_WorkFlowStatus_TableHeaders;

	@FindBy(xpath="//div[text()='Workflow Status']/parent::div/parent::div/following-sibling::div/table/tbody/tr/td")
	private List<WebElement> List_WorkFlowStatus_Count;

	@FindBy(xpath="//div[contains(text(),'Tasks')]/following-sibling::div/table/thead/tr/th")
	private List<WebElement> List_Tasks_TableHeaders;

	@FindBy(xpath="//div[contains(text(),'Tasks')]/following-sibling::div/table/tbody/tr")
	private List<WebElement> List_Tasks_TableRows;
	private String xpath_TaskListing = "//div[contains(text(),'Tasks')]/following-sibling::div/table/tbody/tr";

	@FindBy(xpath="//div[contains(text(),'Comments')]/following-sibling::div/table/thead/tr/th")
	private List<WebElement> List_Comments_TableHeaders;

	@FindBy(xpath="//div[contains(text(),'Comments')]/following-sibling::div/table/tbody/tr")
	private List<WebElement> List_Comments_TableRows;
	private String xpath_CommentsListing = "//div[contains(text(),'Comments')]/following-sibling::div/table/tbody/tr";

	@FindBy(xpath="//button[@ng-click='taskCsvExport()']")
	private WebElement Button_TaskCsvExport;

	@FindBy(id="statusFilter")
	private WebElement Filter_TaskStatus;

	@FindBy(xpath="//ul[@id='statusFilterOptions']/li/span[2]")
	private List<WebElement> List_TaskStatus_Options;

	@FindBy(id="severityFilter")
	private WebElement Filter_TaskSeverity;

	@FindBy(xpath="//ul[@id='severityFilterOptions']/li/span[2]")
	private List<WebElement> List_TaskSeverity_Options;

	@FindBy(id="assignToFilter")
	private WebElement Filter_TaskAssignedTo;

    @FindBy(xpath="(//a[@title='4 is an even number'][normalize-space()='4 is an ev...'])[1]")
    private WebElement First_Task_link;

    @FindBy(xpath="(//a[@title='4 is an even number'][normalize-space()='4 is an ev...'])[4]")
    private WebElement First_Comment_link;

	@FindBy(xpath="//div[@id='assignToMultiSelect']/ul/li//span[2]")
	private List<WebElement> List_TaskAssignedTo_Options;

	@FindBy(id="userCommentFilter")
	private WebElement Filter_TaskCreator;

	@FindBy(xpath="//div[@id='userCommentMultiSelect']/ul/li//span[2]")
	private List<WebElement> List_TaskCreator_Options;

	@FindBy(id="taskDateFilter")
	private WebElement Filter_TaskDate;

	@FindBy(xpath="//a[@ng-click='goToMoreIssues()']")
	private WebElement Button_Task_More;

	@FindBy(id="assigned_to")
	private WebElement PopUp_SelectList_AssignedTo;

	@FindBy(id="response")
	private WebElement PopUp_TextArea_Description;

	@FindBy(xpath="//button[@type='button'][normalize-space()='Cancel']")
	private WebElement PopUp_Button_CancelTaskResolve;

	@FindBy(xpath="//button[normalize-space()='Submit']")
	private WebElement PopUp_button_SubmitTaskResolve;

	@FindBy(xpath="//button[@ng-click='commentCsvExport()']")
	private WebElement Button_CommentCsvExport;

	@FindBy(id="userCommentFilterComment")
	private WebElement Filter_CommentsCreator;
	
	@FindBy(xpath="//div[@id='userCommentMultiSelectComment']/ul/li//span[2]")
	private List<WebElement> List_CommentsCreator_Options; 

	@FindBy(id="commentDateFilter")
	private WebElement Filter_CommentDate;

	@FindBy(xpath="//a[@ng-click='goToMoreComment()']")
	private WebElement Button_Comments_More;

	@FindBy(xpath="///button[@class='cancel']")
	private WebElement PopUp_Button_Cancel;

	@FindBy(xpath="//button[@class='confirm']")
	private WebElement PopUp_Button_Confirm;

	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/p")
	private WebElement PopUp_Success_Message;

	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement PopUp_Success_Ok;

	public ProjectUsageDashboardPage() {
		waitUntilLoading();
	}

	public String getProjectTitle() throws Exception {
		return getText(ProjectTitle);
	}

	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}

	public ProjectUsageDashboardPage clickOnExportCourse() throws Exception {
		click(Button_ExportCourse);
		return this;
	}

	public HashMap<String,Integer> getItemCount() throws Exception {
		return getCounts(List_Item_Headers, List_Item_Counts);
	}

	public List<LinkedHashMap<String, String>> getUserList() throws Exception {
		return getTableDataAsList(List_UsersWidget_TableHeaders,List_UserWidget_TableRows,xpath_UserListing);
	}

	public List<LinkedHashMap<String, String>> getAssetList() throws Exception {
		return getTableDataAsList(List_AssetsWidget_TableHeaders,List_AssetsWidget_TableRows,xpath_AssetsListing);
	}
	
	public HashMap<String,Integer> getAssetCounts() throws Exception {
		return getCounts(List_AssetsWidget_AssetName,List_AssetsWidget_AssetCount);
	}
	
	public HashMap<String,Integer> getWidgetCounts() throws Exception {
		return getCounts(List_WidgetsWidget_WidgetName,List_WidgetsWidget_WidgetCount);
	}

	public List<LinkedHashMap<String, String>> getAssessmentList() throws Exception {
		return getTableDataAsList(List_AssessnemtWidget_TableHeaders,List_AssessmentWidget_TableRows,xpath_AssessmentListing);
	}

	public HashMap<String,Integer> getAvailableAssessmentList() throws Exception{
		//List<LinkedHashMap<String,String>> tableData = getAssessmentList();
		Iterator<LinkedHashMap<String, String>> iterator = getAssessmentList().iterator();
		HashMap<String,Integer> availableQuestionsMap = new LinkedHashMap<String,Integer>();

		while(iterator.hasNext()){
			LinkedHashMap<String,String> tableData = new LinkedHashMap<String,String>();
			tableData.putAll(iterator.next());
			availableQuestionsMap.put(tableData.get("Name"), Integer.parseInt(tableData.get("Available")));
		}
		return availableQuestionsMap;
	}

	public HashMap<String,Integer> getUsedAssessmentList() throws Exception{
		//List<LinkedHashMap<String,String>> tableData = getAssessmentList();
		Iterator<LinkedHashMap<String, String>> iterator = getAssessmentList().iterator();
		HashMap<String,Integer> availableQuestionsMap = new LinkedHashMap<String,Integer>();

		while(iterator.hasNext()){
			LinkedHashMap<String,String> tableData = new LinkedHashMap<String,String>();
			tableData.putAll(iterator.next());
			availableQuestionsMap.put(tableData.get("Name"), Integer.parseInt(tableData.get("Used")));
		}
		return availableQuestionsMap;
	}

	public int getTotalUsedAssessmentCount() throws Exception {
		return new ArrayList<>(getUsedAssessmentList().values()).stream().mapToInt(Integer::intValue).sum();
	}
	
	public HashMap<String,Integer> getWorkflowStatus(String nodeType) throws Exception {
		selectByText(SelectList_WorkflowStatus_Type, nodeType);
		return getCounts(List_WorkFlowStatus_TableHeaders,List_WorkFlowStatus_Count);
	}
	
	public List<LinkedHashMap<String, String>> getTaskList() throws Exception {
		scrollToElement(List_Tasks_TableRows.get(0));
		List<LinkedHashMap<String, String>> taskList =  getTableDataAsList(List_Tasks_TableHeaders,List_Tasks_TableRows,xpath_TaskListing);
		List<LinkedHashMap<String,String>> correctedTaskList = new ArrayList<LinkedHashMap<String,String>>();
		//taskList.forEach(item -> item.keySet().forEach(s -> System.out.println(s.replace("All", "").trim())));
		for(LinkedHashMap<String,String> map : taskList) {
			LinkedHashMap<String,String> updatedMap = new LinkedHashMap<String,String>();
			map.forEach((key,value) -> updatedMap.put(key.replace("All", "").trim(), value));
			correctedTaskList.add(updatedMap);
		}
		return correctedTaskList;
	}
	
	public ProjectUsageDashboardPage filterTaskByStatus(TaskStatus status) throws Exception {
		scrollToElement(Filter_TaskStatus);
		click(Filter_TaskStatus);
		click(getWebElementByExpectedText(List_TaskStatus_Options, status.toString()));
		waitUntilLoading();
		return this;
	}
	
	public ProjectUsageDashboardPage filterTaskBySeverity(TaskPriority severity) throws Exception {
		scrollToElement(Filter_TaskSeverity);
		click(Filter_TaskSeverity);
		click(getWebElementByExpectedText(List_TaskSeverity_Options, severity.toString()));
		waitUntilLoading();
		return this;
	}
	
	public ProjectUsageDashboardPage filterTaskByAssignee(String assignedTo) throws Exception {
		scrollToElement(Filter_TaskAssignedTo);
		click(Filter_TaskAssignedTo);
		click(getWebElementByExpectedText(List_TaskAssignedTo_Options, assignedTo));
		waitUntilLoading();
		Thread.sleep(2000);
		click(Filter_TaskAssignedTo);
		Thread.sleep(2000);
		return this;
	}
	
	public ProjectUsageDashboardPage filterTaskByCreator(String creator) throws Exception {
		scrollToElement(Filter_TaskCreator);
		click(Filter_TaskCreator);
		click(getWebElementByExpectedText(List_TaskCreator_Options, creator));
		waitUntilLoading();
		return this;
	}
	
	public List<LinkedHashMap<String, String>> getCommentsList() throws Exception {
		scrollToElement(List_Comments_TableRows.get(0));
		List<LinkedHashMap<String, String>> commentsList =  getTableDataAsList(List_Comments_TableHeaders,List_Comments_TableRows,xpath_CommentsListing);
		List<LinkedHashMap<String,String>> correctedCommentsList = new ArrayList<LinkedHashMap<String,String>>();
		//taskList.forEach(item -> item.keySet().forEach(s -> System.out.println(s.replace("All", "").trim())));
		for(LinkedHashMap<String,String> map : commentsList) {
			LinkedHashMap<String,String> updatedMap = new LinkedHashMap<String,String>();
			map.forEach((key,value) -> updatedMap.put(key.replace("All", "").trim(), value));
			correctedCommentsList.add(updatedMap);
		}
		return correctedCommentsList;
	}
	
	public ProjectUsageDashboardPage filterCommentsByCreator(String creator) throws Exception {
		scrollToElement(Filter_CommentsCreator);
		click(Filter_CommentsCreator);
		click(getWebElementByExpectedText(List_CommentsCreator_Options, creator));
		waitUntilLoading();
		return this;
	}
	
	private HashMap<String,Integer> getCounts(List<WebElement> keyElement, List<WebElement> valueElement ) throws Exception {
		return IntStream.range(0, keyElement.size())
				.collect(
						HashMap::new, 
						(m, i) -> {
							try {
								m.put(getText(keyElement.get(i)), Integer.parseInt(getText(valueElement.get(i))));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}, Map::putAll
						);
	}

    public AssessmentQuestionsDetailsPage clickTaskLinkToAssessment() throws Exception {
        click(First_Task_link);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToNewTab();
        return new AssessmentQuestionsDetailsPage();
    }

    public AssessmentQuestionsDetailsPage clickCommentLinkToAssessment() throws Exception {
        click(First_Comment_link);
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));
        switchToNewTab();
        return new AssessmentQuestionsDetailsPage();
    }
    
}
