package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;
import com.qa.util.TestUtil;

public class GroupActivityPage extends CommonPage {
	
	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title']")
	private WebElement PageTitle;
	
	@FindBy(xpath="//div[@class='pull-middle ng-binding']")
	private WebElement ProjectTitle;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//button[normalize-space()='Create New Group Activity']")
	private WebElement Button_CreateGroupActivity;
	
	@FindBy(xpath="//a[@class='ng-binding']")
	private List<WebElement> List_ActivityName;
	
	@FindBy(xpath="//input[@placeholder='Title']")
	private WebElement PopUp_TextField_Title;
	
	@FindBy(xpath="//input[@placeholder='Content Focus']")
	private WebElement PopUp_TextField_ContentFocus;
	
	@FindBy(id="learningDuration")
	private WebElement PopUp_TextField_LearningDuration;
	
	@FindBy(xpath="//a[@id='close_btn_add']")
	private WebElement PopUp_Button_Cancel;
	
	@FindBy(xpath="//button[@id='confirm_btn_add']")
	private WebElement PopUp_Button_Confirm;
	
	/*************** Child Activity Objects Start Here ***************/
	
	@FindBy(xpath="//button[normalize-space()='Back to GroupActivity']")
	private WebElement Button_BackToGroupActivity;
	
	@FindBy(xpath="//button[contains(text(),'Create New')]")
	private WebElement Button_CreateNewChildGroupActivity;
	
	@FindBy(name="activity_type")
	private WebElement child_PopUp_TextField_ActivityType;
	
	@FindBy(name="learning_duration")
	private WebElement child_PopUp_TextField_LearningDuration;
	
	@FindBy(name="text")
	private WebElement child_PopUp_TextField_text;
	
	@FindBy(xpath="//button[@class='btn btn-default']")
	private WebElement child_PopUp_Button_Cancel;
	
	@FindBy(xpath="//button[@id='confirm_btn_add']")
	private WebElement child_PopUp_Button_Confirm;
	
	@FindBy(xpath="//td[@class='tooltips ng-binding']")
	private List<WebElement> List_childActivityName;
	
	/*********************************************************************/
	
	public GroupActivityPage() {
		waitUntilLoading();
	}
	
	public String getPageTitle() throws Exception {
		return getText(PageTitle);
	}

	public String getProjectTitle() throws Exception {
		return getText(ProjectTitle);
	}
	
	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}
	
	public GroupActivityPage clickCreateNewGroupActivity() throws Exception {
		click(Button_CreateGroupActivity);
		waitUntilLoading();
		return this;
	}
	
	public GroupActivityPage createGroupActivity(String Name) throws Exception {
		sendKeys(PopUp_TextField_Title, Name);
		sendKeys(PopUp_TextField_ContentFocus, TestUtil.faker.address().fullAddress());
		sendKeys(PopUp_TextField_LearningDuration, TestUtil.faker.number().digit());
		click(PopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public GroupActivityPage openActivity(String Name) throws Exception {
		click(getWebElementByExpectedText(List_ActivityName, Name));
		waitUntilLoading();
		return this;
	}
	
	public GroupActivityPage clickCreateNewChildGroupActivity() throws Exception {
		click(Button_CreateNewChildGroupActivity);
		waitUntilLoading();
		return this;
	}
	
	public GroupActivityPage createChildGroupActivity(String Name) throws Exception {
		sendKeys(child_PopUp_TextField_ActivityType, Name);
		sendKeys(child_PopUp_TextField_LearningDuration, TestUtil.faker.number().digit());
		sendKeys(child_PopUp_TextField_text, TestUtil.faker.lorem().paragraph());
		click(child_PopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public EditTOCPage clickOnBackToGroupActivity() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}

}
