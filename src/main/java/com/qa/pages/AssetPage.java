package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;
import com.qa.util.TestUtil;

public class AssetPage extends CommonPage {

	@FindBy(xpath="//div[contains(@class,'page-title')]/descendant::h4[contains(@class,'title')]")
	private WebElement PageTitle;
	
	@FindBy(xpath="//div[@class='pull-middle ng-binding']")
	private WebElement ProjectTitle;
	
	@FindBy(xpath="//a[contains(@class,'grid-panel')]")
	private WebElement Widget_GridView;

	@FindBy(xpath="//a[contains(@class,'list-panel')]")
	private WebElement Widget_ListView;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//button[normalize-space()='Upload Assets']")
	private WebElement Button_UploadAssets;
	
	@FindBy(xpath="(//*[@type = 'file'])")
	private WebElement Input_File;
	
	@FindBy(xpath="//div[contains(@class,'selectedAsset')]")
	private WebElement DropDown_Sort;
	
	@FindBy(xpath="//ul[@class='sortOption']/li")
	private List<WebElement> List_Sort_Values;
	
	@FindBy(xpath="//div[contains(@class,'multiselectParent')]")
	private WebElement DropDown_Filter;
	
	@FindBy(xpath="//ul[contains(@class,'filterOptions')]/li")
	private List<WebElement> List_Filter_Values;
	
	@FindBy(xpath="//div[contains(@aria-label,'asset title')]")
	private List<WebElement> List_Uploaded_Assets;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/p")
	private WebElement PopUp_SuccessMessage;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/button[@class='confirm']")
	private WebElement PopUp_Button_OK;
	
	public AssetPage() {
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
	
	public void clickOnUploadAssets() throws Exception {
		click(Button_UploadAssets);
	}
	
	public AssetPage uploadAsset(String resourceToUpload) throws Exception {
		enableHiddenInputTags();
		String filePath = System.getProperty("user.dir")+TestUtil.updateFilePathSeparatorBasedOnOS("/src/main/resources/")+resourceToUpload;
		sendKeys(Input_File, filePath);
		Thread.sleep(2000);
		click(PopUp_Button_OK);
		waitUntilLoading();
		return this;
	}
	
	/** Test Method , Upload is blocked due to os restriction/
	public AssetPage uploadAsset(String resourceToUpload) throws Exception {
		clickOnUploadAssets();
		RobotFileHandler.uploadFile(System.getProperty("user.dir")+"/src/main/resources/"+resourceToUpload);
		//waitUntilLoading();
		Thread.sleep(2000);
		click(PopUp_Button_OK);
		return this;
	}*/
	
	public List<String> getListedAsset() throws Exception{
		return getText(List_Uploaded_Assets);
	}
	
	/** Test Method *
	public void sikuliUpload() throws Exception {
		Screen screen = new Screen();
		Pattern OSfileInputBox = new Pattern("/Users/sharmaa11/Desktop/FileInputBox.png");
		//Pattern OSDownloadsLink = new Pattern("/Users/sharmaa11/Desktop/DownloadsButton.png");
		Pattern OSOpenButton = new Pattern("/Users/sharmaa11/Desktop/OpenButton.png");
		Pattern OSFileToUpload = new Pattern("/Users/sharmaa11/Desktop/FileName.png");
  		Thread.sleep(2000); 
  		//screen.click(OSDownloadsLink);
  		screen.type(OSfileInputBox,"unique");
  		screen.click(OSFileToUpload);
  		screen.click(OSOpenButton);
	}*/
	
}
