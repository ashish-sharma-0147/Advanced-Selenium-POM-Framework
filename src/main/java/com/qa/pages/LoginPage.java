package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;
import com.qa.util.TestUtil;

public class LoginPage extends CommonPage {

	@CacheLookup
	@FindBy(id="username")
	private WebElement TextBox_UserName;
	
	@CacheLookup
	@FindBy(id="password")
	private WebElement TextBox_Password;
	
	@CacheLookup
	@FindBy(xpath="//button[normalize-space()='Login']")
	private WebElement Button_Login;
	
	@FindBy(xpath="//div[contains(@class,'alert-danger') and @role='alert']")
	private WebElement AlertContainer_ErrorMsg;

	@FindBy(xpath="//a[contains(@class,'forgotpass')]")
	private WebElement Link_NeedHelp;
	
	@FindBy(xpath="//span[@class='logotxt']")
	private WebElement Text_ElsevierLogoText;
	
	public String getLoginPageTitle() throws Exception {
		return getText(Text_ElsevierLogoText);
	}
	
	public DashboardPage ValidLogin(String userName, String password) throws Exception {
		sendKeys(TextBox_UserName, userName);
		sendKeys(TextBox_Password, TestUtil.decodeBase64(password));
		clickLogin();
		return new DashboardPage();
	}
	public String InvalidLogin(String userName, String password) throws Exception {
		sendKeys(TextBox_UserName, userName);
		sendKeys(TextBox_Password, password);
		clickLogin();
		return getErrorMessage();
	}
	
	public ForgotPasswordPage clickOnNeedHelp() throws Exception {
		click(Link_NeedHelp);
		return new ForgotPasswordPage();
	}
	
	public void clickLogin() throws Exception {
		click(Button_Login);
	}
	
	public String getErrorMessage() throws Exception {
		//wait.until(ExpectedConditions.visibilityOf(AlertContainer_ErrorMsg));
		return getText(AlertContainer_ErrorMsg);
	}
	
}
