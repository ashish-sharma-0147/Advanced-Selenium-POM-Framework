package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;

public class CreatePasswordPage extends CommonPage {

	@FindBy(xpath="//h5")
	private WebElement pageHeading;
	
	@FindBy(name="password1")
	private WebElement TextBox_Password;
	
	@FindBy(name="password2")
	private WebElement TextBox_ConfirmPassword;
	
	@FindBy(xpath="//button[normalize-space()='Create new password']")
	private WebElement Button_CreateNewPassword;
	
	@FindBy(xpath="//form[@name='formData']/following-sibling::a[@class='new-account']")
	private WebElement link_BackToLogin;
	
	@FindBy(xpath="//h5[text()='Create Password']/parent::div/descendant::div[@role='alert']")
	private WebElement Alert_Message;
	
	public LoginPage clickOnBackToLogin() throws Exception {
		click(link_BackToLogin);
		return new LoginPage();
	}
	
	public CreatePasswordPage setNewPassword(String password, String confirmPassword) throws Exception {
		sendKeys(TextBox_Password, password);
		sendKeys(TextBox_ConfirmPassword, confirmPassword);
		click(Button_CreateNewPassword);
		waitUntilLoading();
		return this;
	}
	
	public String getAlertMessage() throws Exception {
		return getText(Alert_Message);
	}
}
