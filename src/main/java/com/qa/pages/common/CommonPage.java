package com.qa.pages.common;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.PageBase;
import com.qa.driver.DriverManager;
import com.qa.pages.AdminSettingsPage;
import com.qa.pages.AdministrationOrganizationPage;
import com.qa.pages.BuildingBlocksPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.External_EmailSupport;
import com.qa.pages.LearningObjectRepositoryPage;
import com.qa.pages.LoginPage;
import com.qa.pages.MetaDataPage;

/* This page contains common elements across all the pages */

public class CommonPage extends PageBase {

	@FindBy(xpath="//ul[contains(@class,'navbar')]//a[normalize-space()='My Projects']")
	private WebElement NavigationBar_MyProjects;

	@FindBy(xpath="//ul[contains(@class,'navbar')]//a[normalize-space()='Metadata']")
	private WebElement NavigationBar_Metadata;

	@FindBy(xpath="//ul[contains(@class,'navbar')]//a[normalize-space()='Building Blocks']")
	private WebElement NavigationBar_BuildingBlocks;

	@FindBy(xpath="//ul[contains(@class,'navbar')]//a[normalize-space()='Learning Object Repository']")
	private WebElement NavigationBar_LOR;

	@FindBy(xpath="//div[@class='admin-menu']")
	private WebElement NavigationBar_Admin;

	@FindBy(xpath="//button[@id='profileDropdownMenu']")
	private WebElement DropDown_UserProfile;

	@FindBy(xpath="//ul[@id='header-dropdown']/child::li/child::a")
	private List<WebElement> DropDown_UserProfile_Values;

	@FindBy(xpath="//div[@class='from-container-row']")
	private WebElement PageContent;

	@FindBy(xpath="//div[contains(@class,'spinner')]")
	private WebElement Loader_Spinner;

	@FindBy(xpath="//div[@ng-show='show_success_msg']//button[@class='close']")
	private WebElement Button_CloseSuccessMsg;
	
	@FindBy(tagName="body")
	public WebElement body;

	public LoginPage Logout() throws Exception {
		//TestUtil.wait.until(ExpectedConditions.elementToBeClickable(DropDown_UserProfile));
		click(DropDown_UserProfile);
		click(getWebElementByExpectedText(DropDown_UserProfile_Values, "Log Out"));
		waitUntilLoading();
		return new LoginPage();
	}

	public void waitUntilLoading() {
		try {
			wait.until(ExpectedConditions.visibilityOf(Loader_Spinner));
			wait.until(ExpectedConditions.invisibilityOf(Loader_Spinner));
		}catch(TimeoutException te) {
			//logs.warn(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), te);
		}catch(StaleElementReferenceException se) {
			try {
				hardRefresh();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void waitForPageToCompletelyLoad() {
		wait.until(ExpectedConditions.invisibilityOf(Loader_Spinner));
		//wait.until(ExpectedConditions.visibilityOf(PageContent));
	}

	public DashboardPage clickOnMyProjects() throws Exception {
		click(NavigationBar_MyProjects);
		return new DashboardPage();
	}

	public MetaDataPage clickOnMetaData() throws Exception {
		click(NavigationBar_Metadata);
		return new MetaDataPage();
	}

	public BuildingBlocksPage clickOnBuildingBlocks() throws Exception {
		click(NavigationBar_BuildingBlocks);
		return new BuildingBlocksPage();
	}

	public LearningObjectRepositoryPage clickOnLOR() throws Exception {
		click(NavigationBar_LOR);
		return new LearningObjectRepositoryPage();
	}

	public AdminSettingsPage clickOnAdmin() throws Exception {
		click(NavigationBar_Admin);
		return new AdminSettingsPage();
	}

	public void clickOnPageBody() throws Exception {
		click(PageContent);
	}

	public AdministrationOrganizationPage clickOnGoToAdministration() throws Exception {
		click(DropDown_UserProfile);
		click(getWebElementByExpectedText(DropDown_UserProfile_Values, "Go to Administration"));
		return new AdministrationOrganizationPage();
	}

	public void removeSuccessMessageBanner() throws Exception {
		click(Button_CloseSuccessMsg);
	}

	public External_EmailSupport launchExternalEmailSupport(String url) throws Exception {
		openNewTab();
		switchToNewTab();
		DriverManager.getInstance().getDriver().get(url);
		return new External_EmailSupport();
	}

	public void closeCurrentTab() {
		closeTab();
	}
	
	public void hardRefresh() throws Exception {
		refreshCurrentPage();
		waitUntilLoading();
	}
	
	public String getActiveURL() throws Exception {
		return getCurrentURL();
	}
	
	public LoginPage launchFROSTinNewTab(String URL) throws Exception {
		openNewTab();
		switchToNewTab();
		DriverManager.getInstance().getDriver().get(URL);
		return new LoginPage();
	}

}
