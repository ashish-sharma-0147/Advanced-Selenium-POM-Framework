package com.qa.pages;

import org.openqa.selenium.WebElement;

import com.qa.pages.common.CommonPage_Admin;
import com.qa.testdata.OrganizationFilterType;
import com.qa.testdata.OrganizationType;

import io.github.sukgu.support.FindElementBy;

public class AdministrationOrganizationPage extends CommonPage_Admin {
	
	@FindElementBy(css="select[name='filter']")
	private WebElement SelectList_OrgFilter;
	
	@FindElementBy(css="#n-org-reset")
	private WebElement Button_ResetOrgFilter;
	
	@FindElementBy(xpath="//input[@placeholder='Search']")
	private WebElement TextBox_OrgSearch;
	private String TextBox_OrgSearchLocator = "input[placeholder=\"Search\"]";
	
	@FindElementBy(css="#n-org-serch")
	private WebElement Button_SearchOrg;
	
	@FindElementBy(css="#n-org-edit")
	private WebElement Button_EditOrg;
	
	@FindElementBy(css="#n-add-org")
	private WebElement Button_AddOrganization;
	
	@FindElementBy(css="#parentOrganizationLevel")
	private WebElement SelectList_ParentOrgLevel;
	
	@FindElementBy(css="#parentOrganization")
	private WebElement SelectList_ParentOrg;
	
	@FindElementBy(css="#organizationName")
	private WebElement TextBox_OrgName;
	private String TextBox_OrgNameLocator = "input[id=\"organizationName\"]";
	
	@FindElementBy(css="#organizationLevel")
	private WebElement SelectList_OrgLevel;
	
	@FindElementBy(css="#organizationType")
	private WebElement SelectList_OrgType;
	
	@FindElementBy(xpath="//div[@class='col-sm-9']/child::input[@type='radio'][1]")
	private WebElement RadioButton_ActiveOrg;
	
	@FindElementBy(xpath="//div[@class='col-sm-9']/child::input[@type='radio'][2]")
	private WebElement RadioButton_InActiveOrg;
	
	@FindElementBy(css="#n-org-add-save")
	private WebElement Button_SaveOrg;
	
	@FindElementBy(css="#n-org-add-cancel")
	private WebElement Button_CancelOrg;
	
	private String shadowRoot = "lm-ap-admin-portal-main";
	
	public AdministrationOrganizationPage() {
		waitUntilLoading();
	}
	
	public AdministrationOrganizationPage resetFilter() throws Exception {
		click(Button_ResetOrgFilter);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationOrganizationPage searchOrg(OrganizationFilterType filterBy, String searchString) throws Exception {
		selectByText(SelectList_OrgFilter, filterBy.toString());
		sendKeys(shadowRoot, TextBox_OrgSearchLocator, searchString);
		click(Button_SearchOrg);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationOrganizationPage clickOnAddOrganization() throws Exception {
		click(Button_AddOrganization);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationOrganizationPage addOrganization(String orgName, OrganizationType orgType) throws Exception {
		sendKeys(shadowRoot, TextBox_OrgNameLocator, orgName);
		selectByValue(SelectList_OrgType, orgType.toString());
		click(Button_SaveOrg);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationOrganizationPage ClickOnEditOrg() throws Exception {
		click(Button_EditOrg);
		waitUntilLoading();
		return this;
	}
	
	public AdministrationOrganizationPage editOrg(String orgName, OrganizationType orgType, String status) throws Exception {
		sendKeys(shadowRoot, TextBox_OrgNameLocator, orgName);
		selectByValue(SelectList_OrgType, orgType.toString());
		if(status.equalsIgnoreCase("Active"))
			click(RadioButton_ActiveOrg);
		else if (status.equalsIgnoreCase("Inactive"))
			click(RadioButton_InActiveOrg);
		click(Button_SaveOrg);
		waitUntilLoading();
		return this;
	}
	
	
}
