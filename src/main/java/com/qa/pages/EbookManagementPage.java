package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;

public class EbookManagementPage extends CommonPage {

	@FindBy(xpath="//div[@class='from-container-row']/descendant::div[@class='page-title']")
	private WebElement PageTitle;

	@FindBy(xpath="//div[@class='page-title']/child::div[contains(@class,'pull-middle')]")
	private WebElement ProjectTitle;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;

	@FindBy(xpath="//button[normalize-space()='Create New Ebook']")
	private WebElement Button_CreateNewEbook;
	
	@FindBy(xpath="//a[@class='ng-binding']")
	private List<WebElement> List_EbookName;
	
	@FindBy(xpath="//input[@placeholder='Ebook Title']")
	private WebElement TextBox_EbookTitle;
	
	@FindBy(id="isbnId")
	private WebElement TextBox_ISBN;
	
	@FindBy(xpath="//input[@placeholder='Author']")
	private WebElement TextBox_Author;
	
	@FindBy(xpath="//input[@placeholder='Edition']")
	private WebElement TextBox_Edition;
	
	@FindBy(id="upload1")
	private WebElement Button_UploadCover1;
	
	@FindBy(id="upload2")
	private WebElement Button_UploadCover2;
	
	@FindBy(id="upload3")
	private WebElement Button_UploadCover3;
	
	@FindBy(xpath="//input[@placeholder='Search for assets']")
	private WebElement TextBox_searchAsset;
	
	@FindBy(xpath="//div[@class='box-theme']/child::div[@class='images-select lor-design']")
	private WebElement ListedAsset_RadioButton;
	
	@FindBy(id="submit_image")
	private WebElement Button_AddSelectedAsset;
	
	@FindBy(id="confirm_btn_add")
	private WebElement Button_Confirm;
	
	@FindBy(id="close_btn_add")
	private WebElement Button_Cancel;
	
	public EbookManagementPage() {
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
	
	public EbookManagementPage createNewEbook(String title, String ISBN, String Author, String Edition, String coverImageFileName) throws Exception {
		click(Button_CreateNewEbook);
		sendKeys(TextBox_EbookTitle, title);
		sendKeys(TextBox_ISBN, ISBN);
		sendKeys(TextBox_Author, Author);
		sendKeys(TextBox_Edition, Edition);
		click(Button_UploadCover1);
		click(ListedAsset_RadioButton);
		click(Button_AddSelectedAsset);
		click(Button_UploadCover2);
		click(ListedAsset_RadioButton);
		click(Button_AddSelectedAsset);
		click(Button_UploadCover3);
		click(ListedAsset_RadioButton);
		click(Button_AddSelectedAsset);
		click(Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public List<String> getListedEbooks() throws Exception{
		List<String> Ebooks = getText(List_EbookName);
		Ebooks.replaceAll(String::trim);
		return Ebooks;
	}
	
	public EbookChapterManagementPage openEbook(String ebookName) throws Exception {
		click(getWebElementByExpectedText(List_EbookName, ebookName));
		waitUntilLoading();
		return new EbookChapterManagementPage();
	}
}
