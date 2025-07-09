package com.qa.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.util.TestUtil;

public class AssignmentsPage extends CommonPage {
	
	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title']")
	private WebElement PageTitle;

	@FindBy(xpath="//div[@class='page-title']/child::div[contains(@class,'pull-middle')]")
	private WebElement ProjectTitle;

	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//button[normalize-space()='Create New Assignment']")
	private WebElement Button_CreteNewAssignment;
	
	@FindBy(xpath="//td[@class='tooltips ng-binding']")
	private List<WebElement> List_AssignmentNames;
	
	@FindBy(xpath="//select[contains(@ng-model,'assignment_type')]")
	private WebElement PopUp_SelectList_Type;
	
	@FindBy(name="assignment_title")
	private WebElement PopUp_TextField_AssignmentName;
	
	@FindBy(xpath="//button[normalize-space()='Additional Files']")
	private WebElement PopUp_Button_AdditionalFiles;
	
	@FindBy(xpath="//span[contains(@id,'top') and @role='presentation']")
	private WebElement PopUp_DescriptionToolbar;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, assignment_description']")
	private WebElement PopUp_iframe;
	
	@FindBy(xpath="//body//p")
	private WebElement PopUp_iframe_textArea;
	
	@FindBy(xpath="//div[@id='addAssignment']/button[normalize-space()='CANCEL']")
	private WebElement PopUp_Button_Cancel;
	
	@FindBy(xpath="//div[@id='addAssignment']/button[@ng-show='showAdd']")
	private WebElement PopUp_Button_Confirm;
	
	public AssignmentsPage() {
		waitUntilLoading();
	}
	
	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}

	public AssignmentsPage clickCreateNewAssignment() throws Exception {
		click(Button_CreteNewAssignment);
		waitUntilLoading();
		return this;
	}
	
	public Set<String> getListedAssignments() throws Exception{
		List<String> Assignments = getText(List_AssignmentNames);
		Assignments.replaceAll(String::trim);
		return new HashSet<String>(Assignments);
	}
	
	public AssignmentsPage createAssignment(String Name) throws Exception {
		selectByIndex(PopUp_SelectList_Type, 1);
		sendKeys(PopUp_TextField_AssignmentName, Name);
		wait.until(ExpectedConditions.visibilityOf(PopUp_DescriptionToolbar));
		switchToFrame(PopUp_iframe);
		sendKeysByJsExecutor(PopUp_iframe_textArea, TestUtil.faker.lorem().paragraph());
		switchToDefaultContent();
		return this;
	}
	
	public AssignmentsPage clickOnConfirm() throws Exception {
		click(PopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public AssignmentsPage clickOnCancel() throws Exception {
		click(PopUp_Button_Cancel);
		return this;
	}
}
