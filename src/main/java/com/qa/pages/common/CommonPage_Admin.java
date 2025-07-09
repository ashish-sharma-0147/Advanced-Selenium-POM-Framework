package com.qa.pages.common;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.PageBase;
import com.qa.pages.AdministrationBulkUploadPage;
import com.qa.pages.AdministrationOrganizationPage;
import com.qa.pages.AdministrationUsersPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;

import io.github.sukgu.support.FindElementBy;

public class CommonPage_Admin extends PageBase{

	@FindElementBy(css=".copy-right-text")
	private WebElement Text_copyRight;

	@FindElementBy(xpath="//div[@class='header-top']/descendant::a[@class='dropdown-toggle nav-link']")
	private WebElement Admin_Dropdown;

	@FindElementBy(css="#d-go-auth")
	private WebElement DropDown_Option_GoToAuthoring;

	@FindElementBy(css="#d-go-analytics")
	private WebElement DropDown_Option_GoToAnalytics;

	@FindElementBy(css="#d-go-profile")
	private WebElement DropDown_Option_Profile;

	@FindElementBy(css="#d-logout")
	private WebElement DropDown_Option_Logout;

	@FindElementBy(xpath="//div[@class='loader']")
	private WebElement Loader;

	@FindElementBy(css="#n-organization")
	private WebElement Tab_Organization;

	@FindElementBy(css="#n-user")
	private WebElement Tab_Users;

	@FindElementBy(css="#n-cources")
	private WebElement Tab_Resources;

	@FindElementBy(css="#n-lti")
	private WebElement Tab_LTIConfig;

	@FindElementBy(css="#n-lti")
	private WebElement Tab_BulkUpload;

	@FindElementBy(xpath="//div[@class='sweetAlert']/child::p")
	private WebElement Alert_Message;

	@FindElementBy(css="#n-alert-conf")
	private WebElement Button_AlertOk;

	@FindElementBy(xpath="//table[@class='table table-striped']/thead/tr/th")
	protected List<WebElement> List_ShadowTable_Headers;
	
	@FindElementBy(xpath="//table[@class='table table-striped']/tbody/tr[contains(@class,'ng-star-inserted')]")
	protected List<WebElement> List_ShadowTable_Rows;

	protected void waitUntilLoading() {
		try {
			wait.until(ExpectedConditions.visibilityOf(Loader));
			wait.until(ExpectedConditions.invisibilityOf(Loader));
		}catch(TimeoutException te) {
			//logs.error(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), te);
		}
	}

	public DashboardPage goToAuthoring() throws Exception {
		click(Admin_Dropdown);
		click(DropDown_Option_GoToAuthoring);
		return new DashboardPage();
	}

	public LoginPage Logout() throws Exception {
		click(Admin_Dropdown);
		click(DropDown_Option_Logout);
		return new LoginPage();
	}
	public String getCopyRightText() throws Exception {
		return getText(Text_copyRight);
	}

	public AdministrationOrganizationPage openOrganizationTab() throws Exception {
		click(Tab_Organization);
		return new AdministrationOrganizationPage();
	}

	public AdministrationUsersPage openUsersTab() throws Exception {
		click(Tab_Users);
		return new AdministrationUsersPage();
	}

	public AdministrationOrganizationPage openResourceTab() throws Exception {
		click(Tab_Resources);
		return new AdministrationOrganizationPage();
	}

	public AdministrationOrganizationPage openLTIConfigTab() throws Exception {
		click(Tab_LTIConfig);
		return new AdministrationOrganizationPage();
	}

	public AdministrationBulkUploadPage openBulkUploadTab() throws Exception {
		click(Tab_BulkUpload);
		return new AdministrationBulkUploadPage();
	}

	public void closePopUp() throws Exception {
		click(Button_AlertOk);
		waitUntilLoading();
	}
	public String getAlertMessage() throws Exception {
		return getText(Alert_Message);
	}
	
	public List<LinkedHashMap<String, String>> getListedTableContent() throws Exception {
		return getShadowTableDataAsList(List_ShadowTable_Headers,List_ShadowTable_Rows,getElementLocator(List_ShadowTable_Rows.get(0)).get("xpath"));	
	}
}
