package com.qa.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.PageBase;

public class External_EmailSupport extends PageBase {

	@FindBy(xpath="//td[@align='center']/a")
	private WebElement Button_createYourPassword;
	
	@FindBy(xpath="//td[@align='center']/a")
	private WebElement Button_Login;
	
	@FindBy(xpath="//input[@id='login']")
	private WebElement Yopmail_TextBox_Email;
	
	@FindBy(xpath="//div[@class='lms']")
	private List<WebElement> Yopmail_List_EmailSubjects;
	
	@FindBy(xpath="//div[@class='bname']")
	private WebElement Yopmail_EmailBanner;
	
	@FindBy(id="refresh")
	private WebElement Yopmail_Button_Refresh;
	
	@FindBy(id="search")
	private WebElement Mailinator_TextBox_EnterPublicEmail;
	
	@FindBy(xpath="//button[@value='Search for public inbox for free']")
	private WebElement Mailinator_Button_New_Go;
	
	@FindBy(id="inbox_field")
	private WebElement Mailinator_TextBox_Email;
	
	@FindBy(xpath="//table[contains(@class,'jambo_table')]/child::tbody/tr")
	private WebElement Mailinator_EmailToOpen;
	
	@FindBy(xpath="//table[@class='table-striped jambo_table']//td[3]")
	private List<WebElement> Mailinator_List_EmailSubjects;

	@FindBy(xpath="//button[@class='primary-btn']")
	private WebElement Mailinator_Button_GO;
	
	@FindBy(xpath="//a[@class='fz-18 ff-futura-demi p-l-15']")
	private WebElement Mailinator_Button_BackToInbox;
	
	@FindBy(xpath="//a[@class='nav-item'][normalize-space()='Home']")
	private WebElement Mailinator_Link_Home;
	
	@FindBy(xpath="//a[@class='wmlogoclick']")
	private WebElement Yopamil_Link_Home;
	
	@FindBy(xpath="//img[contains(@src,'logo')]")
	private WebElement Logo_FROST;
	
	private final String Yopmail_Inbox_Iframe = "ifinbox";
	private final String Yopmail_EmailBody_IFrame = "ifmail";
	private final String Mailinator_IFrame = "html_msg_body";
	
	public External_EmailSupport Yopmail_openInbox(String Email) throws Exception {
		clear(Yopmail_TextBox_Email);
		sendKeys(Yopmail_TextBox_Email, Email);
		sendKeys(Yopmail_TextBox_Email, Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(Yopmail_EmailBanner));
		Thread.sleep(2000);
		return this;
	}
	
	public External_EmailSupport Yopmail_openEmailHavingSubject(String subject) throws Exception {
		switchToDefaultContent();
		switchToFrame(Yopmail_Inbox_Iframe);
		click(getWebElementBySubString(Yopmail_List_EmailSubjects, subject));
		return this;
	}
	
	public External_EmailSupport Yopmail_RefreshInbox() throws Exception {
		click(Yopmail_Button_Refresh);
		switchToFrame(Yopmail_Inbox_Iframe);
		wait.until(ExpectedConditions.visibilityOf(Yopmail_List_EmailSubjects.get(0)));
		Thread.sleep(2000);
		return this;
	}
	
	public CreatePasswordPage Yopmail_clickOnCreateYourPassword() throws Exception {
		switchToDefaultContent();
		switchToFrame(Yopmail_EmailBody_IFrame);
		click(Button_createYourPassword);
		switchToNewTab();
		return new CreatePasswordPage();		
	}
	
	public LoginPage Yopmail_clickOnLogin() throws Exception {
		switchToDefaultContent();
		switchToFrame(Yopmail_EmailBody_IFrame);
		click(Button_Login);
		switchToNewTab();
		return new LoginPage();		
	}
	
	
	public External_EmailSupport Mailinator_openInbox(String Email) throws Exception {
		sendKeys(Mailinator_TextBox_EnterPublicEmail, Email);
		click(Mailinator_Button_New_Go);
		wait.until(ExpectedConditions.elementToBeClickable(Mailinator_EmailToOpen));
		//click(Mailinator_EmailToOpen);
		return this;
	}
	
	public External_EmailSupport Mailinator_openEmailHavingSubject(String subject) throws Exception {
		click(getWebElementBySubString(Mailinator_List_EmailSubjects, subject));
		return this;
	}
	
	public External_EmailSupport Mailinator_goBackToInbox() throws Exception {
		click(Mailinator_Button_BackToInbox);
		Thread.sleep(2000);
		return this;
	}
	
	public External_EmailSupport Mailinator_navigateToHome() throws Exception {
		click(Mailinator_Link_Home);
		return this;
	}
	
	public External_EmailSupport Yopmail_navigateToHome() throws Exception {
		click(Yopamil_Link_Home);
		return this;
	}
	
	public CreatePasswordPage Mailinator_clickOnCreateYourPassword() throws Exception {
		switchToFrame(Mailinator_IFrame);
		wait.until(ExpectedConditions.visibilityOf(Logo_FROST));
		wait.until(ExpectedConditions.elementToBeClickable(Button_createYourPassword));
		click(Button_createYourPassword);
		switchToNewTab();
		return new CreatePasswordPage();		
	}
	
	public LoginPage Mailinator_clickOnLogin() throws Exception {
		switchToFrame(Mailinator_IFrame);
		wait.until(ExpectedConditions.visibilityOf(Logo_FROST));
		wait.until(ExpectedConditions.elementToBeClickable(Button_Login));
		click(Button_Login);
		switchToNewTab();
		return new LoginPage();
	}
}
