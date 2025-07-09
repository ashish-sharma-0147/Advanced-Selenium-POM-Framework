package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;

public class ForgotPasswordPage extends CommonPage {

	@FindBy(xpath="//div[contains(@class,'login_row')]")
	private WebElement PageContent;
	
	@FindBy(xpath="//h2[@class='login-sub-text']")
	private WebElement Text_PageHeading;
	
	@FindBy(id="f-email")
	private WebElement TextBox_Email;
	
	@FindBy(xpath="//button[text()='Send me  password']")
	private WebElement Button_SendMePassword;
	
	@FindBy(xpath="//a[@class='new-account']")
	private WebElement Link_BackToLogin;
	
	public ForgotPasswordPage() {
		wait.until(ExpectedConditions.visibilityOf(PageContent));
	}
	public String getPageTitle() throws Exception {
		return getText(Text_PageHeading);
	}
	
	public void getNonExistingEmailError() {
		
	}
}
