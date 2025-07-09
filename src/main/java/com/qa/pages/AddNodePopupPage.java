package com.qa.pages;

import java.util.Collections;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.KeyValuePair;
import com.qa.util.TestUtil;

import io.github.sukgu.support.FindElementBy;

public class AddNodePopupPage extends CommonPage {

	@FindBy(xpath="//div[contains(@class,'popup-boxes')]/descendant::div[contains(@class,'modal-header')]")
	private WebElement PopUp_Title;

	@FindBy(xpath="//div[@class='inner-modal-content tab-contents']")
	private WebElement PopUp_Body;

	@FindBy(id="details")
	private WebElement Tab_Details;

	@FindBy(id="metadata")
	private WebElement Tab_MetaData;

	/****************************************************************************
	 *  Folder Objects Starts Here
	 */
	@FindBy(id="section")
	private WebElement RadioButton_Folder;

	@FindBy(xpath="//input[@type='checkbox' and @ng-model='make_module']")
	private WebElement CheckBox_MakeAsModule;
	/****************************************************************************/

	/****************************************************************************
	 *  Content Screen Objects Starts Here
	 */
	@FindBy(id="page")
	private WebElement RadioButton_ContentScreen;

	@FindBy(xpath="//select[@ng-model='selected_option']")
	private WebElement SelectList_ContentScreenTemplate;
	/****************************************************************************/

	/****************************************************************************
	 *  Assessment Objects Starts Here
	 */
	@FindBy(id="assessment")
	private WebElement RadioButton_Assessment;

	@FindBy(id="assessment_dropdown")
	private WebElement SelectList_Assessment;

	@FindBy(id="allAssessmentId")
	private WebElement RadioButton_AllAssessmentItem;

	@FindBy(id="selectdAssessmentId")
	private WebElement RadioButton_SelectedAssessmentItem;

	@FindBy(id="addAssessmentModalBtn")
	private WebElement Button_SelectAssessment;

	@FindBy(xpath="//i[@id='spinner']")
	private WebElement Loader_spinner;

	@FindBy(xpath="//input[@ng-model='search_text']")
	private WebElement TextField_SearchItem;

	@FindBy(xpath="//button[@ng-click='search()']")
	private WebElement Button_Search;

	@FindBy(xpath="//input[contains(@name,'selected_assessment_id')]")
	private WebElement RadioButton_AllAssessmentItem_ListedAssessment;

	@FindBy(xpath="//input[contains(@name,'selected_assessment_item')]")
	private WebElement RadioButton_SelectedAssessmentItem_ListedAssessment;
	/****************************************************************************/

	/****************************************************************************
	 *  Assignment Objects Starts Here
	 */
	@FindBy(id="assignment")
	private WebElement RadioButton_Assignment;

	@FindBy(xpath="//select[@ng-model='assignment_source']")
	private WebElement SelectList_Assignment;

	@FindBy(id="select_assignmentt")
	private WebElement Button_SelectAssignment;

	@FindBy(xpath="//input[contains(@name,'selected_assignment_id')]")
	private WebElement RadioButton_ListedAssignment;
	/****************************************************************************/

	/****************************************************************************
	 *  Forum Objects Starts Here
	 */
	@FindBy(id="forum")
	private WebElement RadioButton_Forum;

	@FindBy(id="select_addForumModal")
	private WebElement Button_SelectForum;

	@FindBy(xpath="//input[contains(@name,'selected_forum_id')]")
	private WebElement RadioButton_ListedForum;
	/****************************************************************************/

	/****************************************************************************
	 *  Resource Objects Starts Here
	 */
	@FindBy(id="link")
	private WebElement RadioButton_Resource;

	@FindBy(id="linkName")
	private WebElement TextField_Resource_Name;

	@FindBy(id="ext_res_type_link")
	private WebElement SelectList_Resources;

	@FindBy(id="ext_res_url")
	private WebElement Resource_TextField_URL;

	@FindBy(id="addAssestModalBtn")
	private WebElement Resource_Button_SelectAsset;

	@FindBy(id="search_text_asset")
	private WebElement Resource_TextBox_SearchAsset;

	@FindBy(xpath="//button[contains(@class,'search_btn')]")
	private WebElement Resource_Button_Search;

	@FindBy(xpath="//div[@class='box-theme']")
	private WebElement Resource_ListedAsset;

	@FindBy(xpath="//div[@class='box-theme']/child::div[@class='images-select lor-design']")
	private WebElement Resource_ListedAsset_RadioButton;

	@FindBy(id="submit_image")
	private WebElement Resource_Button_AddSelectedAsset;

	@FindBy(xpath="//span[contains(@class,'asset_name')]")
	private WebElement Resource_SelectedAssetName;

	@FindBy(xpath="//span[contains(@class,'asset_type')]")
	private WebElement Resource_SelectedAssetType;

	@FindBy(id="deleteAsset")
	private WebElement Resource_DeleteSelectedAsset;
	/****************************************************************************/

	/****************************************************************************
	 *  Group Activity Objects Starts Here
	 */
	@FindBy(id="group_activity")
	private WebElement RadioButton_GroupActivity;

	@FindBy(id="groupactivity_dropdown")
	private WebElement SelectList_GroupActivity;

	@FindBy(xpath="//table[@class='pattern-list-table']/descendant::td[@class='tooltips ng-binding']")
	private WebElement Listed_childActivities;
	/****************************************************************************/

	/****************************************************************************
	 *  Ebook Objects Starts Here
	 */
	@FindBy(id="ebook")
	private WebElement RadioButton_Ebook;

	@FindBy(id="ebook_dropdown")
	private WebElement SelectList_SelectEbook;

	@FindBy(id="chapter_dropdown")
	private WebElement SelectList_SelectChapter;

	@FindBy(id="add_chapter")
	private WebElement Button_AddChapter;
	/****************************************************************************/

	/****************************************************************************
	 *  Metadata Objects Starts Here
	 */
	@FindElementBy(xpath="//a[@id='keyValuePairTab']")
	private WebElement Tab_KeyValuePair;

	@FindElementBy(xpath="//ng-select[@placeholder='Select a key']/div[contains(@class,'ui-select-container')]")
	private WebElement SelectBox_SelectAKey;

	@FindElementBy(xpath="//input[@placeholder='Select a key']")
	private WebElement TextBox_SelectAKey;

	@FindElementBy(xpath="//li[@role='menuitem']")
	private WebElement SearchedKey;

	@FindElementBy(xpath="//div[@class='ui-select-match']/span/span")
	private WebElement linkedKey;

	@FindElementBy(xpath="//ng-select[@placeholder='Select a value']/div[@class='ui-select-container dropdown open']")
	private WebElement SelectBox_SelectAValue;

	@FindElementBy(xpath="//li[@role='menuitem'][1]")
	private WebElement ListedValue;

	@FindElementBy(xpath="//input[@id='onDate']")
	private WebElement TextBox_DateValue;

	@FindElementBy(xpath="//div[@class='text-center dl-abdtp-day-view ']/div[2]/div[4]/div[role='gridcell']")
	private WebElement Calendar_Date;

	@FindElementBy(xpath="//timepicker/descendant::td[1]/a")
	private WebElement Button_Time_Hour_up; 

	@FindElementBy(xpath="//a[@id='taxonomyTab']")
	private WebElement Tab_Taxonomy;

	@FindElementBy(xpath="//a[@id='searchTaxonomyComponentBtn']")
	private WebElement Link_AddTaxonomy;

	@FindElementBy(xpath="//a[text()='Search']")
	private WebElement Tab_Search;

	@FindElementBy(xpath="//a[text()='Browse']")
	private WebElement Tab_Browse;

	@FindElementBy(css="input[name='searchValue']")
	private WebElement TextBox_SearchTaxonomy;

	@FindElementBy(xpath="//button[@id='searchTaxonomyBtn']")
	private WebElement Button_SearchTaxonomy;

	@FindElementBy(xpath="//div[@class='taxonomy-search-wrap']/div[2]/custom-list/div[@class='list-group list-group-flush']/custom-list-item/div[@class='list-group-item']/div[@class='form-check']/label")
	private WebElement Listed_Taxonomy;

	@FindElementBy(xpath="//button[@id='addSelectedTaxonomyBtn']")
	private WebElement Button_AddSelectedTaxonomy;

	@FindElementBy(xpath="//div[@class='taxonomy_preview taxonomy_edit']/span[@class='wordBreak taxonomy-title']")
	private WebElement linkedTaxonomy;

	@FindElementBy(xpath="//a[@id='tagsTab']")
	private WebElement Tab_Tags;

	@FindElementBy(xpath="//input[@placeholder='Select a tag']")
	private WebElement TextBox_SearchTag;

	@FindElementBy(xpath="//li[@role='menuitem']")
	private WebElement SearchedTag;

	@FindElementBy(xpath="//span[@class='ui-select-match']/descendant::span[contains(@class,'match-item')]/span")
	private WebElement linkedTag;
	/****************************************************************************/
	@FindBy(id="templateName")
	private WebElement TextField_TemplateName;

	@FindBy(id="vtw_toggle")
	private WebElement Toogle_VTW;

	@FindBy(id="vtw_id")
	private WebElement TextBox_VTWId;

	@FindBy(id="uuid_toggle")
	private WebElement Toogle_UUID;

	@FindBy(id="uuid")
	private WebElement TextBox_UUID;

	@FindBy(xpath="//button[text()='GENERATE NEW ID']")
	private WebElement Button_GenerateUUID;

	@FindBy(xpath="//button[@type='button'][normalize-space()='CANCEL']")
	private WebElement Button_Cancel;

	@FindBy(xpath="//button[normalize-space()='CONFIRM']")
	private WebElement Button_Confirm;

	@FindBy(xpath="//div[@class='modal-footer']/child::button[normalize-space()='CONFIRM']")
	private WebElement Button_SearchList_Confirm;

	public AddNodePopupPage() throws InterruptedException {
		waitUntilLoading();
	}

	public AddNodePopupPage openDetailsTab() throws Exception {
		click(Tab_Details);
		wait.until(ExpectedConditions.visibilityOf(RadioButton_Folder));
		return this;
	}

	public AddNodePopupPage openMetaDataTab() throws Exception {
		click(Tab_MetaData);
		wait.until(ExpectedConditions.visibilityOf(Tab_KeyValuePair));
		waitForShadowRootToLoad();
		return this;
	}

	public AddNodePopupPage createFolderNode(String NodeName, boolean isModule) throws Exception {
		click(RadioButton_Folder);
		setNodeName(NodeName);
		if(!isModule)
			click(CheckBox_MakeAsModule);
		return this;
	}

	public AddNodePopupPage createContentScreenNode(String NodeName) throws Exception {
		click(RadioButton_ContentScreen);
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(SelectList_ContentScreenTemplate,By.tagName("option")));
		selectByIndex(SelectList_ContentScreenTemplate, 0);
		setNodeName(NodeName);
		return this;
	}

	public void waitForSpinner() {
		try {
			wait.until(ExpectedConditions.visibilityOf(Loader_spinner));
			wait.until(ExpectedConditions.invisibilityOf(Loader_spinner));
		}catch(TimeoutException te) {
			//logs.error(this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(), te);
		}
	}

	public AddNodePopupPage createAssessmentNode(String NodeName, String AssessmentName) throws Exception {
		click(RadioButton_Assessment);
		setNodeName(NodeName);
		selectByIndex(SelectList_Assessment, 2);
		click(RadioButton_AllAssessmentItem);
		click(Button_SelectAssessment);
		waitForSpinner();
		sendKeys(TextField_SearchItem, AssessmentName);
		click(Button_Search);
		waitUntilLoading();
		click(RadioButton_AllAssessmentItem_ListedAssessment);
		click(Button_SearchList_Confirm);
		return this;
	}

	public AddNodePopupPage createAssignmentNode(String NodeName, String AssignmentName) throws Exception {
		click(RadioButton_Assignment);
		setNodeName(NodeName);
		click(Button_SelectAssignment);
		waitForSpinner();
		sendKeys(TextField_SearchItem, AssignmentName);
		click(Button_Search);
		waitUntilLoading();
		click(RadioButton_ListedAssignment);
		click(Button_SearchList_Confirm);
		return this;
	}

	public AddNodePopupPage createForumNode(String NodeName,String ForumName) throws Exception {
		click(RadioButton_Forum);
		setNodeName(NodeName);
		click(Button_SelectForum);
		waitForSpinner();
		sendKeys(TextField_SearchItem, ForumName);
		click(Button_Search);
		waitUntilLoading();
		click(RadioButton_ListedForum);
		click(Button_SearchList_Confirm);
		return this;
	}

	public AddNodePopupPage createResourceNode(String NodeName, resourceType type, String AssetNameOrURL) throws Exception {
		click(RadioButton_Resource);
		sendKeys(TextField_Resource_Name,NodeName);
		selectByValue(SelectList_Resources, type.toString());
		if(type.toString().equals(resourceType.FilefromAssetLibrary.toString()))
		{
			click(Resource_Button_SelectAsset);
			waitForSpinner();
			sendKeys(Resource_TextBox_SearchAsset, AssetNameOrURL.substring(0, 14));
			click(Resource_Button_Search);
			waitUntilLoading();
			click(Resource_ListedAsset_RadioButton);
			click(Resource_Button_AddSelectedAsset);
			waitUntilLoading();
		}
		else
			sendKeys(Resource_TextField_URL, AssetNameOrURL);
		return this;
	}

	public Map<String, String> getSelectedAssetDetails() throws Exception{
		return Collections.singletonMap(getText(Resource_SelectedAssetName),getText(Resource_SelectedAssetType));
	}

	public AddNodePopupPage createEbookNode(String Ebook, String Chapter) throws Exception {
		click(RadioButton_Ebook);
		selectByText(SelectList_SelectEbook, Ebook);
		waitUntilLoading();
		click(Button_AddChapter);
		selectByText(SelectList_SelectChapter, Chapter);
		return this;
	}

	public AddNodePopupPage createGroupActivityNode(String ActivityName) throws Exception {
		click(RadioButton_GroupActivity);
		selectByText(SelectList_GroupActivity, ActivityName);
		return this;
	}

	public String getListedChildActivities() throws Exception {
		return getText(Listed_childActivities);
	}

	public EditTOCPage clickOnCancel() throws Exception {
		click(Button_Cancel);
		return new EditTOCPage();
	}

	public EditTOCPage clickOnConfirm() throws Exception {
		scrollToElement(Button_Confirm);
		click(Button_Confirm);
		return new EditTOCPage();
	}

	private void setNodeName(String Name) throws Exception {
		sendKeys(TextField_TemplateName, Name);
	}

	public enum resourceType{
		URL("url"),
		ExternalTool("tool"),
		FilefromAssetLibrary("asset")
		;

		private final String resourceType;

		resourceType(String Type) {
			this.resourceType = Type;
		}

		@Override
		public String toString() {
			return resourceType;
		}
	}

	public AddNodePopupPage linkTaxonomyToNode(String Taxonomy) throws Exception {
		click(Tab_Taxonomy);
		Thread.sleep(1000);
		click(Tab_Taxonomy);
		click(Link_AddTaxonomy);
		click(TextBox_SearchTaxonomy);
		jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot"
				+ ".querySelector('lor-taxonomy-search').shadowRoot"
				+ ".querySelector('input[name=\"searchValue\"]').value='"+Taxonomy+"'");
		jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot"
				+ ".querySelector('lor-taxonomy-search').shadowRoot"
				+ ".querySelector('input[name=\"searchValue\"]').dispatchEvent(new Event('input',{'bubbles':true}))");
		click(Button_SearchTaxonomy);
		click(Listed_Taxonomy);
		click(wait.until(ExpectedConditions.visibilityOf(Button_AddSelectedTaxonomy)));
		return this;
	}

	public boolean isTaxonomyLinked(String Taxonomy) throws Exception {
		if(getText(linkedTaxonomy).contains(Taxonomy))
			return isDisplayed(linkedTaxonomy);
		else
			return false;
	}

	public AddNodePopupPage linkTagToNode(String tagTitle) throws Exception {
		click(Tab_Tags);
		Thread.sleep(1000);
		click(Tab_Tags);
		click(TextBox_SearchTag);
		jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[placeholder=\"Select a tag\"]').value='"+tagTitle+"'");
		jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[placeholder=\"Select a tag\"]').dispatchEvent(new Event('input',{'bubbles':true}))");
		jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[placeholder=\"Select a tag\"]').dispatchEvent(new KeyboardEvent('keydown', {'key': 'Enter'}))");
		click(SearchedTag);
		return this;
	}

	public boolean isTagLinked(String tagTitle) throws Exception {
		if(getText(linkedTag).contains(tagTitle))
			return isDisplayed(linkedTag);
		else
			return false;
	}

	public AddNodePopupPage linkKeyValueToNode(KeyValuePair keyType, String key) throws Exception {
		click(Tab_KeyValuePair);
		Thread.sleep(2000);
		click(SelectBox_SelectAKey);
		jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[placeholder=\"Select a key\"]').value='"+key+"'");
		jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[placeholder=\"Select a key\"]').dispatchEvent(new Event('input',{'bubbles':true}))");
		jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[placeholder=\"Select a key\"]').dispatchEvent(new KeyboardEvent('keydown', {'key': 'Enter'}))");
		//Thread.sleep(2000);
		click(SearchedKey);
		if(keyType.toString().equals(KeyValuePair.TEXT.toString())) {
			jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[id=\"usr\"]').value='"+TestUtil.faker.lorem().word()+"'");
			jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[id=\"usr\"]').dispatchEvent(new Event('input',{'bubbles':true}))");
		}else if(keyType.toString().equals(KeyValuePair.SELECT.toString())) {
			click(SelectBox_SelectAValue);
			click(ListedValue);
		}else if(keyType.toString().equals(KeyValuePair.DATE.toString())) {
			click(TextBox_DateValue);
			//clickByJsExecutor(Calendar_Date);
			jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[id=\"onDate\"]').removeAttribute(\"readonly\")");
			jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[id=\"onDate\"]').value=\"19-10-2020\"");
			jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot.querySelector('input[id=\"onDate\"]').dispatchEvent(new Event('input',{'bubbles':true}))");
		}else if(keyType.toString().equals(KeyValuePair.TIME.toString())) {
			click(Button_Time_Hour_up);
		}else if(keyType.toString().equals(KeyValuePair.NUMBER.toString())) {
			jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot."
					+ "querySelector('div[id=\"keyValuePairTab\"]>div:nth-child(1)>div:nth-child(2)>input[type=\"number\"]').value='"+TestUtil.faker.number().randomDigitNotZero()+"'");
			jsDriver.executeScript("document.querySelector('custom-metadata-picker').shadowRoot."
					+ "querySelector('div[id=\"keyValuePairTab\"]>div:nth-child(1)>div:nth-child(2)>input[type=\"number\"]').dispatchEvent(new Event('input',{'bubbles':true}))");
		}
		return this;
	}

	public boolean isKeyLinked(String key) throws Exception {
		if(getText(linkedKey).contains(key))
			return isDisplayed(linkedKey);
		else
			return false;
	}

	private void waitForShadowRootToLoad() {
		wait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return document.querySelector('custom-metadata-picker').shadowRoot.textContent"));
	}
}
