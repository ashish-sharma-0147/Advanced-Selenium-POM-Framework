package com.qa.pages;

import static com.qa.util.HttpConnectionUtil.getBrokenLinks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.ListPerPage;

import io.github.sukgu.support.FindElementBy;

public class LearningObjectRepositoryPage extends CommonPage{

	@FindBy(xpath="//section[contains(@class,'wrapper')]")
	private WebElement PageContent;

	@FindElementBy(xpath="//h1[contains(@class,'page-title-heading')]")
	private WebElement Text_PageTitle;

	@FindElementBy(css="#newbtn")
	private WebElement Button_New;

	@FindElementBy(css="img[class='img-fluid img-thumbnail']")
	private List<WebElement> List_ImageThumbnails;

	@FindElementBy(xpath="//button[@id='resultPerPage']")
	private WebElement Button_showItemsPerPage;

	@FindElementBy(xpath="//a[contains(@id,'perpage')]")
	private List<WebElement> DropDown_Menu_perPage; 

	@FindElementBy(xpath="//div[@id='pagination-number']")
	private WebElement Text_TableEntries_Footer;

	@FindElementBy(css="a[aria-label='Next']")
	private WebElement Pagination_NextPage;

	@FindElementBy(css="#UploadBtn")
	private WebElement Button_Upload;
	
	@FindElementBy(css="#uploaddocs")
	private WebElement Link_UploadFiles;
	
	@FindElementBy(css="#downloaddocs")
	private WebElement Link_BulkUpload;
	
	@FindBy(xpath="//div[@id='swal2-content']")
	private WebElement Alert_Message;
	
	@FindBy(xpath="//button[@id='confirm']")
	private WebElement Alert_Button_Ok_Confirm;
	
	@FindBy(xpath="//button[@id='cancel']")
	private WebElement Alert_Button_Cancel;
	
	@FindElementBy(css="#searchbar")
	private WebElement TextBox_SearchRepository;
	private String TextBox_SearchRepository_Locator = "input[id=\"searchbar\"]";
	
	@FindElementBy(css="#findbtn")
	private WebElement Button_Find;
	
	@FindElementBy(xpath="//a[@id='showContent']")
	private List< WebElement> Listed_ContentNames; 
	
	@FindElementBy(xpath="//button[@id='list-view-more']")
	private WebElement Button_ViewMore;
	
	@FindElementBy(css="#btnEdit0")
	private WebElement Link_Edit;
	
	@FindElementBy(css="#btnDelete0")
	private WebElement Link_Delete;
	
	@FindElementBy(xpath="//lm-fcd-player//p")
	private WebElement Message_LorPreview;
	
	private final String shadowRoot_parent = "custom-lor-container";
	private final String shadowRoot_child = "custom-advance-search";
	
	public LearningObjectRepositoryPage() {
		waitUntilLoading();
		waitForLORcontentToLoad();
	}

	public String getPageTitle() throws Exception {
		return getText(Text_PageTitle);
	}


	public List<String> getListOfBrokenImages() throws Exception {
		return getBrokenLinks(getListedObjectsImageSource());
	}

	private List<String> getListedObjectsImageSource() throws Exception {
		List<String> ImageSrcs = new ArrayList<String>();		
		while(getCurrentPageNumber(Text_TableEntries_Footer)<=/*getTotalPages(Text_TableEntries_Footer)*/10)
		{
			List_ImageThumbnails.parallelStream().forEach(e -> ImageSrcs.add(e.getAttribute("src")));
			if(getCurrentPageNumber(Text_TableEntries_Footer)!=getTotalPages(Text_TableEntries_Footer)){
				clickByJsExecutor(Pagination_NextPage);
				waitForLORcontentToLoad();
			}else break;	
		}
		return ImageSrcs;
	}

	public LearningObjectRepositoryPage setItemsPerPage(ListPerPage count) throws Exception {
		click(Button_showItemsPerPage);
		click(getWebElementByExpectedText(DropDown_Menu_perPage, count.toString()));
		waitForLORcontentToLoad();
		return this;
	}
	
	public LearningObjectRepositoryPage uploadFile(String resourceToUpload ) throws Exception {
		click(Button_Upload);
		//click(Link_UploadFiles);
		sendKeys("custom-lor-container", "custom-advance-search", "input[type=\"file\"]", "/sharmaa11/Documents/Clone/hcm-digital-authoring-automated-testing/src/main/resources/"+resourceToUpload);
		//RobotFileHandler.uploadFile(System.getProperty("user.dir")+"/src/main/resources/"+resourceToUpload);
		return this;
	}
	
	public String getAlertMessage() throws Exception {
		String message = getText(Alert_Message);
		click(Alert_Button_Ok_Confirm);
		return message;
	}
	
	public LearningObjectRepositoryPage searchRepository(String searchString) throws Exception {
		sendKeys(shadowRoot_parent,shadowRoot_child, TextBox_SearchRepository_Locator, searchString);
		click(Button_Find);
		//pressEnterToNestedShadowElement(shadowRoot_parent,shadowRoot_child, TextBox_SearchRepository_Locator);
		waitForLORcontentToLoad();
		return this;
	}
	
	public List<String> getListedContents() throws Exception{
		return getText(Listed_ContentNames);
	}
	
	public LearningObjectRepositoryPage deleteContent() throws Exception {
		click(Button_ViewMore);
		click(Link_Delete);
		Thread.sleep(2000);
		click(Alert_Button_Ok_Confirm);
		return this;
	}
	
	public LearningObjectRepositoryPage deleteContent(String fileName) throws Exception {
		searchRepository(fileName);
		click(Button_ViewMore);
		click(Link_Delete);
		Thread.sleep(2000);
		click(Alert_Button_Ok_Confirm);
		return this;
	}

	private void waitForLORcontentToLoad() {
		wait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return document.querySelector('custom-lor-container').shadowRoot.querySelector('#pagination-number').textContent"));
	}

	private int getTotalPages(WebElement element) {
		String TableEntriesText = element.getText();
		TableEntriesText = TableEntriesText.replaceAll("[^-?0-9]+", " ");
		int TotalTableEntries = Integer.parseInt(Arrays.asList(TableEntriesText.trim().split(" ")).get(1));
		return TotalTableEntries;
	}

	private int getCurrentPageNumber(WebElement element) {
		String TableEntriesText = element.getText();
		TableEntriesText = TableEntriesText.replaceAll("[^-?0-9]+", " ");
		int TotalTableEntries = Integer.parseInt(Arrays.asList(TableEntriesText.trim().split(" ")).get(0));
		return TotalTableEntries;
	}
	
	public LearningObjectRepositoryPage previewContent() throws Exception {
		click(Listed_ContentNames.get(0));
		waitForLORcontentToLoad();
		return this;
	}
	
	public String getLORPreviewMessage() throws Exception {
		return getText(Message_LorPreview);
	}
}
