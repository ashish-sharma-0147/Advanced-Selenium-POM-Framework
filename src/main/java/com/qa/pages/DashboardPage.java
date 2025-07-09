package com.qa.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.ListPerPage;
import com.qa.util.TestUtil;

public class DashboardPage extends CommonPage{

	@FindBy(xpath="//h2[@class='page-heading']")
	private WebElement Text_PageHeading;

	@FindBy(xpath="//button[contains(@ng-click,'createProject')]")
	private WebElement Button_CreateNewProject;

	@FindBy(xpath="//input[@aria-label='Search Box']")
	private WebElement TextField_SearchBox;

	@FindBy(xpath="//button[@title='Project Type']")
	private WebElement Button_ProjectTypeFilter;

	@FindBy(xpath="//span[@class='multiselect-selected-text']")
	private WebElement Label_Button_ProjectType;

	@FindBy(xpath="//div[@class='multipleSelection']/descendant::button[contains(@title,'Project Type')]/following-sibling::ul//label")
	private List<WebElement> DropDown_List_ProjectType;

	@FindBy(xpath="//a[contains(@class,'grid-panel')]")
	private WebElement Widget_GridView;

	@FindBy(xpath="//a[contains(@class,'list-panel')]")
	private WebElement Widget_ListView;

	@FindBy(xpath="//div[@total-items='totalProjects']//div[contains(@class,'box-theme-title')]")
	private List<WebElement> Widget_ProjectName_List;

	@FindBy(xpath="//div[@total-items='totalProjects']")
	private WebElement ProjectName_Parent;

	private String ProjectNameXpath = "//div[contains(@class,'box-theme-title') and contains(text(),'xxxxxxxxxx')]/following-sibling::div[@class='theme-comments-control']//button[@aria-label='Project menu']";

	@FindBy(xpath="//div[@total-items='totalProjects']/descendant::div[contains(@class,'box-theme-title')]")
	private WebElement ProjectName;

	@FindBy(xpath="//button[@aria-label='Project menu']")
	private WebElement ProjectMenu;

	@FindBy(xpath="//div[@total-items='totalProjects']/descendant::span[contains(@class,'type-code')]")
	private List<WebElement> List_ProjectType;  

	@FindBy(xpath="//div[@class='box-theme active']//div[@class='plmenu open ng-scope']//ul[@role='menu']//li//a[@href='javascript:;'][normalize-space()='Edit Project']")
	private WebElement Link_EditProject;

	@FindBy(xpath="//div[@class='box-theme active']//div[@class='plmenu open ng-scope']//ul[@role='menu']//li//a[@href='javascript:;'][normalize-space()='Define Pattern']")
	private WebElement Link_DefinePattern;

	@FindBy(xpath="//div[@class='box-theme active']//div[@class='plmenu open ng-scope']//ul[@role='menu']//li//a[@href='javascript:;'][normalize-space()='Define Asset']")
	private WebElement Link_DefineAsset;

	@FindBy(xpath="//div[@class='box-theme active']//div[@class='plmenu open ng-scope']//ul[@role='menu']//li//a[@href='javascript:;'][normalize-space()='Define Glossary']")
	private WebElement Link_DefineGlossary;

	@FindBy(xpath="//div[@class='box-theme active']//div[@class='plmenu open ng-scope']//ul[@role='menu']//li//a[@href='javascript:;'][normalize-space()='Export List']")
	private WebElement Link_ExportList;

	@FindBy(xpath="//div[@class='box-theme active']//a[contains(text(),'Duplicate Project')]")
	private WebElement Link_DuplicateProject;

	@FindBy(xpath="//div[@class='box-theme active']//a[@class='ng-scope'][normalize-space()='Delete Project']")
	private WebElement Link_DeleteProject;

	@FindBy(xpath="//div[contains(@class,'sweet-alert')]")
	private WebElement PopUp_Body;

	@FindBy(xpath="//button[@class='cancel']")
	private WebElement PopUp_Button_Cancel;

	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/child::h2")
	private WebElement PopUp_Text_Header;

	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/child::p")
	private WebElement PopUp_Text_Message;

	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/button[@class='confirm']")
	private WebElement PopUp_Button_Confirm;

	@FindBy(xpath="//select[contains(@ng-model,'itemsPerPage')]")
	private WebElement SelectList_ItemsPerPage;

	@FindBy(xpath="//div[contains(@class,'result_filteration')]//label[contains(@aria-label,'Showing') and contains(@aria-label,'records')]")
	private WebElement Text_TableEntries_Footer;

	@FindBy(xpath="//dir-pagination-controls[@pagination-id='projectList']/child::ul[contains(@class,'pagination')]//a[contains(@ng-if,'pageNumber')]")
	private List<WebElement> Pagination_PageNumbers_List;

	@FindBy(xpath="//dir-pagination-controls[@pagination-id='projectList']/child::ul[contains(@class,'pagination')]//a[contains(@aria-label,'first')]")
	private WebElement Pagination_FirstPage;

	@FindBy(xpath="//dir-pagination-controls[@pagination-id='projectList']/child::ul[contains(@class,'pagination')]//a[contains(@aria-label,'previous')]")
	private WebElement Pagination_PreviousPage;

	@FindBy(xpath="//dir-pagination-controls[@pagination-id='projectList']/child::ul[contains(@class,'pagination')]//a[contains(@aria-label,'next')]")
	private WebElement Pagination_NextPage;

	@FindBy(xpath="//dir-pagination-controls[@pagination-id='projectList']/child::ul[contains(@class,'pagination')]//a[contains(@aria-label,'last')]")
	private WebElement Pagination_LastPage;

	@FindBy(xpath="//div[@role='alert']")
	private WebElement Text_AlertMessage;

	@FindBy(xpath = "//div[contains(@class, 'from-container-row')]")
	private WebElement Content_Loaded;

	public DashboardPage(){
		waitUntilLoading();
	}

	public String getPageHeading() throws Exception {
		return getText(Text_PageHeading);
	}	

	public String getSuccessMessage() throws Exception {
		return getText(Text_AlertMessage).replaceFirst("×", "").trim();
	}

	public CreateProjectPage clickOnCreateNewProject() throws Exception {
		click(Button_CreateNewProject);
		return new CreateProjectPage();
	}

	public void clickOnEditProject(String projectName) throws Exception {
		wait.until(ExpectedConditions.visibilityOf(Content_Loaded));
		projectName = (projectName.length()>20) ? projectName.substring(0,19) : projectName;
		searchProject(projectName);
		if(getText(ProjectName).contains(projectName))
		{
			click(ProjectMenu);
			click(Link_EditProject);
		}
	}

	public DashboardPage searchProject(String projectName) throws Exception {
		sendKeys(TextField_SearchBox, projectName);
		sendKeys(TextField_SearchBox, Keys.RETURN);
		waitUntilLoading();
		return this;
	}

	public EditTOCPage openProject(String projectName) throws Exception {
		searchProject(projectName);
		click(ProjectName);
		return new EditTOCPage();			
	}

	public String deleteProject(String projectName) throws Exception {
		try {
			searchProject(projectName);
			projectName = (projectName.length() > 20) ? projectName.substring(0, 19) : projectName; 
			if (getText(ProjectName).contains(projectName)) {
				click(ProjectMenu);
				//click(getProjectMenuElementByProjectName(getRefinedProjectName(projectName)));
				click(Link_DeleteProject);
				Thread.sleep(2000);
				confirmPopUp();
				wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//button[@class='confirm']"), "Yes, delete it!"));
				return getText(PopUp_Text_Message);
			} else {
				throw new IllegalStateException("Unable to Delete Project: Project not found!");
			}
		} catch (Exception e) {
			throw new IllegalStateException("Error occurred while deleting the project: " + e.getMessage(), e);
		}
	}

	public String deleteDuplicateProject(String projectName) throws Exception {
		try {
			searchProject(projectName);
			if(getText(ProjectName).substring(15,27).contains(projectName.substring(4,16)))
			{
				click(ProjectMenu);
				//click(getProjectMenuElementByProjectName(getRefinedProjectName(projectName)));
				click(Link_DeleteProject);
				Thread.sleep(2000);
				confirmPopUp();
				wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//button[@class='confirm']"), "Yes, delete it!"));
				return getText(PopUp_Text_Message);
			}
			else
				throw new IllegalStateException("Unable to Delete Project: Project not found!");
		} catch (Exception e) {
			throw new IllegalStateException("Error occurred while deleting the project: " + e.getMessage(), e);
		}
	}


	public void confirmPopUp() throws Exception {
		click(PopUp_Button_Confirm);
	}

	public DashboardPage clickProjectTypeFilter() throws Exception {
		click(Button_ProjectTypeFilter);
		return new DashboardPage();
	}

	public DashboardPage setProjectFilter(String ProjectType) throws Exception {
		click(getWebElementByExpectedText(DropDown_List_ProjectType, ProjectType));
		waitUntilLoading();
		click(Label_Button_ProjectType);
		return new DashboardPage();
	}

	public String getListedProjectsType() throws Exception {
		Set<String> typeList = new HashSet<String>();
		while(TestUtil.getCurrentMaxCount(Text_TableEntries_Footer)<=TestUtil.getTotalCount(Text_TableEntries_Footer))
		{
			String text = getText(Text_TableEntries_Footer);
			typeList.addAll(getText(List_ProjectType));
			if(TestUtil.getCurrentMaxCount(Text_TableEntries_Footer)!=TestUtil.getTotalCount(Text_TableEntries_Footer)){
				click(Pagination_NextPage);
				wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//div[contains(@class,'result_filteration')]//label[contains(@aria-label,'Showing') and contains(@aria-label,'records')]"), text));
			}else break;	
		}
		return typeList.toString().replace("[", "").replace("]", "").replace("Online Course -", "").trim();
	}

	public void setItemsPerPage(ListPerPage count) throws Exception {
		selectByValue(SelectList_ItemsPerPage, count.toString());
		waitUntilLoading();
	}

	public GlossaryPage defineGlossary() throws Exception {
		click(ProjectMenu);
		click(Link_DefineGlossary);
		return new GlossaryPage();
	}

	public ExportPage exportList() throws Exception{
		click(ProjectMenu);
		click(Link_ExportList);
		return new ExportPage();
	}

	/* This method is for testing, Remove before working on this page */
	public void projectSelect() throws Exception {
		//selectFromList(Widget_ProjectName_List, "4586_test");
		click(getProjectMenuElementByProjectName("4586_test"));
		click(Link_DuplicateProject);
		click(PopUp_Button_Cancel);
		confirmPopUp();
		try{Thread.sleep(3000);}catch(Exception e) {}
	}

	/**
	 * Method returning Project Menu element based on projectName 
	 * */
	private WebElement getProjectMenuElementByProjectName(String ProjectName) {
		return ProjectName_Parent.findElement(By.xpath(ProjectNameXpath.replace("xxxxxxxxxx", ProjectName)));
	}

	@SuppressWarnings("unused")
	private String getRefinedProjectName(String projectName) {
		if (projectName.length() > 27)  
			return projectName.substring(0, 26);
		else
			return projectName;
	}


}
