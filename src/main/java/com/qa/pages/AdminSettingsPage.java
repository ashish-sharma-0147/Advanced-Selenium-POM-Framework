package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;

public class AdminSettingsPage extends CommonPage{
	
	@FindBy(xpath="//div[@class='page-title']/child::div[@class='pull-middle']")
	private WebElement Text_PageTitle;
	
	@FindBy(xpath="//button[text()='Create New Role']")
	private WebElement Button_CreateNewRole;
	
	public AdminSettingsPage() {
		waitUntilLoading();
	}

	public String getPageTitle() throws Exception {
		return getText(Text_PageTitle);
	}
}
