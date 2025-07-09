package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage_Admin;

import io.github.sukgu.support.FindElementBy;

public class AdministrationUsersPage extends CommonPage_Admin {
	
	@FindElementBy(css="select[name='role']")
	private WebElement SelectList_userRole;
	
	@FindElementBy(css="input[placeholder='Search']")
	private WebElement TextBox_Search;
	private String TextBox_SearchLocator = "input[placeholder=\"Search\"]";

	@FindElementBy(css="#n-user-srch")
	private WebElement Button_Search;
	
	@FindElementBy(css="#n-user-reset")
	private WebElement Button_ResetFilter;
	
	@FindElementBy(css="#n-user-add")
	private WebElement Button_CreateAccount;
	
	@FindElementBy(css="#n-user-edit")
	private WebElement Button_Edit;
	
	@FindElementBy(css="#n-user-actdac")
	private WebElement Button_Deactivate;
	
	@FindElementBy(css="#mat-select-0")
	private WebElement MultiSelectList_Create_Platform;
	
	@FindElementBy(css="#mat-option-0")
	private WebElement Option_Create_Admin;
	
	@FindElementBy(css="#mat-option-1")
	private WebElement Option_Create_Analytics;
	
	@FindElementBy(css="#mat-option-2")
	private WebElement Option_Create_Authoring;
	
	@FindElementBy(css="#mat-select-1")
	private WebElement MultiSelectList_Edit_Platform;
	
	@FindElementBy(css="#mat-option-3")
	private WebElement Option_Edit_Admin;
	
	@FindElementBy(css="#mat-option-4")
	private WebElement Option_Edit_Analytics;
	
	@FindElementBy(css="#mat-option-5")
	private WebElement Option_Edit_Authoring;
	
	@FindElementBy(css="input[placeholder='First Name']")
	private WebElement TextBox_FirstName;
	private String TextBox_FirstNameLocator = "input[placeholder=\"First Name\"]";
	
	@FindElementBy(css="input[placeholder='Last Name']")
	private WebElement TextBox_LastName;
	private String TextBox_LastNameLocator = "input[placeholder=\"Last Name\"]";
	
	@FindElementBy(css="input[placeholder='User Name']")
	private WebElement TextBox_UserName;
	private String TextBox_UserNameLocator = "input[placeholder=\"User Name\"]";
	
	@FindElementBy(css="input[placeholder='Email']")
	private WebElement TextBox_Email;
	private String TextBox_EmailLocator = "input[placeholder=\"Email\"]";
	
	@FindElementBy(css="#organizationLevel")
	private WebElement SelectList_OrgLevel;
	
	@FindElementBy(css="#organization")
	private WebElement SelectList_Organization;
	
	@FindElementBy(css="#n-user-add-save")
	private WebElement Button_Save;
	
	@FindElementBy(css="#n-user-add-cancel")
	private WebElement Button_Cancel;
	
	@FindElementBy(css="select[name='statusFilter']")
	private WebElement SelectList_status;
	
	@FindElementBy(css="#n-alert-conf")
	private WebElement Popup_Button_UserActiveDeactive;
	
	private String shadowRoot = "lm-ap-admin-portal-main";
	
	public AdministrationUsersPage() {
		waitUntilLoading();
	}
	
	public AdministrationUsersPage resetFilter() throws Exception {
		click(Button_ResetFilter);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationUsersPage searchUserBy(String filterBy, String searchString) throws Exception {
		selectByText(SelectList_userRole, filterBy);
		if(!filterBy.equalsIgnoreCase("Status")) {
			sendKeys(shadowRoot, TextBox_SearchLocator, searchString);
			click(Button_Search);
		}
		if(filterBy.equalsIgnoreCase("Status")) {
			selectByText(SelectList_status, searchString);
		}	
		waitUntilLoading();
		return this;
	}
	
	public AdministrationUsersPage clickOnCreateAccount() throws Exception {
		click(Button_CreateAccount);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationUsersPage createUser(String platform, String firstName, String lastName, String userName, String email) throws Exception {
		setPlatform(platform, "Create");
		setPersonalDetails(firstName, lastName, userName, email);
		click(Button_Save);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationUsersPage editUser(String platform, String firstName, String lastName, String userName, String email) throws Exception {
		setPlatform(platform, "EDIT");
		setPersonalDetails(firstName, lastName, userName, email);
		click(Button_Save);
		waitUntilLoading();
		return this;
	}
	
	private void setPlatform(String platform, String operation) throws Exception {
		if(operation.equalsIgnoreCase("Create")) {
			click(MultiSelectList_Create_Platform);
			if(platform.equalsIgnoreCase("Administration"))
				click(Option_Create_Admin);
			else if(platform.equalsIgnoreCase("Analytics"))
				click(Option_Create_Analytics);
			else if(platform.equalsIgnoreCase("Authoring"))
				click(Option_Create_Authoring);
			else {
				click(Option_Create_Admin);
				click(Option_Create_Analytics);
				click(Option_Create_Authoring);
			}
		}else if(operation.equalsIgnoreCase("Edit")) {
			click(MultiSelectList_Edit_Platform);
			if(platform.equalsIgnoreCase("Administration"))
				click(Option_Edit_Admin);
			else if(platform.equalsIgnoreCase("Analytics"))
				click(Option_Edit_Analytics);
			else if(platform.equalsIgnoreCase("Authoring"))
				click(Option_Edit_Authoring);
			else {
				click(Option_Edit_Admin);
				click(Option_Edit_Analytics);
				click(Option_Edit_Authoring);
			}
		}
		
	}
	
	private void setPersonalDetails(String firstName, String lastName, String userName, String email) throws Exception {
		sendKeys(shadowRoot, TextBox_FirstNameLocator, firstName);
		sendKeys(shadowRoot, TextBox_LastNameLocator, lastName);
		sendKeys(shadowRoot, TextBox_UserNameLocator, userName);
		sendKeys(shadowRoot, TextBox_EmailLocator, email);
		click(TextBox_Email);
	}
	
	public AdministrationUsersPage ClickOnEdit() throws Exception {
		click(Button_Edit);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationUsersPage deactivateUser() throws Exception {
		click(Button_Deactivate);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationUsersPage confirmPopup() throws Exception {
		click(Popup_Button_UserActiveDeactive);
		wait.until(ExpectedConditions.invisibilityOf(Popup_Button_UserActiveDeactive));
		Thread.sleep(3000);
		return this;
	}
}
