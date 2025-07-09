package com.qa.pages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.util.TestUtil;

public class ForumsPage extends CommonPage {

	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title']")
	private WebElement PageTitle;

	@FindBy(xpath="//div[@class='page-title']/child::div[contains(@class,'pull-middle')]")
	private WebElement ProjectTitle;

	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//button[normalize-space()='Create New Forum']")
	private WebElement Button_CreateNewForum;
	
	@FindBy(xpath="//td[@class='tooltips ng-binding']")
	private List<WebElement> List_ForumNames;
	
	@FindBy(name="forums_title")
	private WebElement PopUp_TextField_ForumName;
	
	@FindBy(xpath="//span[contains(@id,'top') and @role='presentation']")
	private WebElement PopUp_DescriptionToolbar;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, forums_description']")
	private WebElement PopUp_iframe;
	
	@FindBy(xpath="//body//p")
	private WebElement PopUp_iframe_textArea;
	
	@FindBy(xpath="//div[@id='addForum']/button[normalize-space()='CANCEL']")
	private WebElement PopUp_Button_Cancel;
	
	@FindBy(xpath="//div[@id='addForum']/button[@ng-show='showAdd']")
	private WebElement PopUp_Button_Confirm;
	
	public ForumsPage() {
		waitUntilLoading();
	}
	
	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}

	public ForumsPage clickCreateNewForum() throws Exception {
		click(Button_CreateNewForum);
		waitUntilLoading();
		return this;
	}
	
	public Set<String> getListedForums() throws Exception{
		List<String> Forums = getText(List_ForumNames);
		Forums.replaceAll(String::trim);
		return new HashSet<String>(Forums);
	}
	
	public ForumsPage createForum(String Name) throws Exception {
		sendKeys(PopUp_TextField_ForumName, Name);
		wait.until(ExpectedConditions.visibilityOf(PopUp_DescriptionToolbar));
		switchToFrame(PopUp_iframe);
		sendKeysByJsExecutor(PopUp_iframe_textArea, TestUtil.faker.lorem().paragraph());
		switchToDefaultContent();
		return this;
	}
	
	public ForumsPage clickOnConfirm() throws Exception {
		click(PopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public ForumsPage clickOnCancel() throws Exception {
		click(PopUp_Button_Cancel);
		return this;
	}
	
}
