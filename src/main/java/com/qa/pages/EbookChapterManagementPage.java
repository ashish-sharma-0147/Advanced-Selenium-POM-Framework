package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;

public class EbookChapterManagementPage extends CommonPage{
	
	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title']")
	private WebElement PageTitle;

	@FindBy(xpath="//div[@class='page-title']/child::div[contains(@class,'pull-middle')]")
	private WebElement ProjectTitle;
	
	@FindBy(xpath="//button[normalize-space()='Back to Ebook']")
	private WebElement Button_BackToEbook;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;

	@FindBy(xpath="//button[normalize-space()='Create New Chapter']")
	private WebElement Button_CreateNewChapter;
	
	@FindBy(xpath="//td[@class='tooltips ng-binding']")
	private List<WebElement> List_ChapterNames;
	
	@FindBy(name="chapter_title")
	private WebElement TextBox_ChapterTitle;
	
	@FindBy(name="chapter_number")
	private WebElement TextBox_ChapterNumber;
	
	@FindBy(name="chapter_page_from")
	private WebElement TextBox_ChapterFrom;
	
	@FindBy(name="chapter_page_to")
	private WebElement TextBox_ChapterTo;
	
	@FindBy(xpath="//button[@class='btn btn-default']")
	private WebElement Button_Cancel;
	
	@FindBy(xpath="//button[@class='btn btn-primary ng-scope']")
	private WebElement Button_Confirm;
	
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
	
	public EbookManagementPage clickOnBackToEbook() throws Exception {
		click(Button_BackToEbook);
		return new EbookManagementPage();
	}

	public EbookChapterManagementPage createNewChapter(String title, String chapterNumber, String pageFrom, String pageTo) throws Exception {
		click(Button_CreateNewChapter);
		waitUntilLoading();
		sendKeys(TextBox_ChapterTitle, title);
		sendKeys(TextBox_ChapterNumber, chapterNumber);
		sendKeys(TextBox_ChapterFrom, pageFrom);
		sendKeys(TextBox_ChapterTo, pageTo);
		click(Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public List<String> getListedChapters() throws Exception{
		List<String> chapters = getText(List_ChapterNames);
		chapters.replaceAll(String::trim);
		return chapters;
	}
}
