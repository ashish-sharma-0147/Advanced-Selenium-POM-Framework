package com.qa.pages;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Optional;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.KeyValuePair;

public class MetaDataPage extends CommonPage {
	
	@FindBy(xpath="//div[@class='pull-middle']")
	private WebElement PageTitle;
	
	@FindBy(xpath="//a[text()='Tag']/parent::li")
	private WebElement Tab_Tag;
	
	@FindBy(xpath="//a[text()='Taxonomy']/parent::li")
	private WebElement Tab_Taxonomy;
	
	@FindBy(xpath="//a[text()='Key Value']/parent::li")
	private WebElement Tab_KeyValue;
	
	/****************************************************************************
	 *  Taxonomy Objects Starts Here
	 */
	
	@FindBy(xpath="//div[contains(@class,'taxonomyMngt')]")
	private WebElement Container_Taxonomy;
	
	@FindBy(xpath="//div[@class='pull-right']//button[@type='button'][contains(text(),'Create New')]")
	private WebElement Button_CreateNewTaxonomy;
	
	@FindBy(xpath="//button[@ng-click=\"importTaxonomyModal('')\"]")
	private WebElement Button_Import;
	
	@FindBy(xpath="//button[@ng-click='exportTaxonomy()']")
	private WebElement Button_Export;
	
	private String Xpath_Taxonomy = "//span[contains(@class,'taxonomy-title') and contains(text(),'xxxxxxxxxx')]";
	
	private String Xpath_ExpandTaxonomy = "/../button";
	
	private String Xpath_TaxonomyDropDown = "/following-sibling::span[contains(@class,'tinyPopup')]/child::button";
	
	private String Xpath_subTaxonomies = "/parent::div/parent::div[@class='toc-outer']/following-sibling::ol[@class='pregnant']/descendant::span[contains(@class,'taxonomy-title')]";
	
	@FindBy(xpath="//span[@class='wordBreak taxonomy-title ng-binding']")
	private List<WebElement> List_Taxonomies;
	
	@FindBy(xpath="//span[@class='dropdown toc-plus tinyPopup open']//div[@class='last']")
	private WebElement TaxonomyDropDown_Add;
	
	@FindBy(xpath="//span[@class='dropdown toc-plus tinyPopup open']//div[@class='Edit']")
	private WebElement TaxonomyDropDown_Edit;
	
	@FindBy(xpath="//span[@class='dropdown toc-plus tinyPopup open']//div[@class='Delete']")
	private WebElement TaxonomyDropDown_Delete;
	
	@FindBy(xpath="//button[@class='close']")
	private WebElement Button_PopUp_Cancel;
	
	@FindBy(name="taxonomy_text")
	private WebElement TaxonomyPopUp_TextArea_Statement;
	
	@FindBy(name="taxonomy_description")
	private WebElement TaxonomyPopUp_TextArea_Description;
	
	@FindBy(name="human_coding")
	private WebElement TaxonomyPopUp_TextBox_UserFacingId;
	
	@FindBy(name="vtw_id")
	private WebElement TaxonomyPopUp_TextBox_VTWid;
	
	@FindBy(name="uuid")
	private WebElement TaxonomyPopUp_TextBox_UUID;
	
	@FindBy(xpath="//label[@for='uuid_toggle']")
	private WebElement TaxonomyPopUp_toogle_UUID;
	
	@FindBy(xpath="//button[text()='GENERATE NEW ID']")
	private WebElement TaxonomyPopUp_Button_GenerateNewId;
	
	@FindBy(xpath="//button[normalize-space()='Close']")
	private WebElement TaxonomyPopUp_Button_Close;
	
	@FindBy(xpath="//button[normalize-space()='Add Node']")
	private WebElement TaxonomyPopUp_Button_AddNode;
	
	@FindBy(xpath="//p[@class='ng-binding']")
	private WebElement TaxonomyPopUp_Edit_UUID;
	/****************************************************************************/
	
	/****************************************************************************
	 *  Tag Objects Starts Here
	 */
	@FindBy(xpath="//button[normalize-space()='Create New Tag']")
	private WebElement Button_CreateNewTag;
	
	@FindBy(xpath="//div[contains(@ng-controller,'tag')]/descendant::div[contains(@class,'input-group search-area')]/child::input[@name='search_text']")
	private WebElement TextBox_SearchTag;
	
	@FindBy(xpath="//div[contains(@ng-controller,'tag')]/descendant::div[contains(@class,'input-group search-area')]/descendant::button")
	private WebElement Button_SearchTag;
	
	@FindBy(name="tag_title")
	private WebElement TagPopUp_TextBox_TagTitle;
	
	@FindBy(xpath="//button[contains(@class,'btn btn-default')][normalize-space()='CANCEL']")
	private WebElement TagPopUp_Button_Cancel;
	
	@FindBy(xpath="//button[@class='btn btn-primary'][normalize-space()='CONFIRM']")
	private WebElement TagPopUp_Button_Confirm;
	
	@FindBy(xpath="//table[@id='tagTableId']//th")
	private List<WebElement> TagTableHeaders;
	
	@FindBy(xpath="//tr[@pagination-id='tagListId']")
	private List<WebElement> TagTableRowList;
	
	@FindBy(xpath="//table[@id='tagTableId']/descendant::button[@title='Delete']")
	private WebElement Button_DeleteTag;
	/****************************************************************************/
	
	/****************************************************************************
	 *  KeyValue Objects Starts Here
	 */
	@FindBy(xpath="//button[normalize-space()='Create New Key Value']")
	private WebElement Button_CreateNewKeyValue;
	
	@FindBy(xpath="//div[contains(@ng-controller,'key_value')]//input[contains(@placeholder,'Enter search term')]")
	private WebElement TextBox_SearchKeyValue;
	
	@FindBy(xpath="//div[contains(@ng-controller,'key_value')]/descendant::div[contains(@class,'input-group search-area')]/descendant::button")
	private WebElement Button_SearchKeyValue;
	
	@FindBy(name="key_name")
	private WebElement KeyValuePopUp_TextBox_Key;
	
	@FindBy(xpath="//input[@type='radio']")
	private List<WebElement> KeyValuePair_InputType_List;
	
	@FindBy(xpath="//input[contains(@placeholder,'Value')]")
	private WebElement KeyValuePairPopUp_TextBox_Value;
	
	@FindBy(xpath="//button[normalize-space()='Add']")
	private WebElement KeyValuePair_Button_Add;
	
	@FindBy(xpath="//input[@type='radio' and @value='text_entry']")
	private WebElement KeyValuePopUp_RadioButton_Text;
	
	@FindBy(xpath="//input[@type='radio' and @value='select_list']")
	private WebElement KeyValuePopUp_RadioButton_Select;
	
	@FindBy(xpath="//input[@type='radio' and @value='date_entry']")
	private WebElement KeyValuePopUp_RadioButton_Date;
	
	@FindBy(xpath="//input[@type='radio' and @value='time_entry']")
	private WebElement KeyValuePopUp_RadioButton_Time;
	
	@FindBy(xpath="//input[@type='radio' and @value='number_entry']")
	private WebElement KeyValuePopUp_RadioButton_Number;
	
	@FindBy(xpath="//button[@class='btn btn-primary'][normalize-space()='CONFIRM']")
	private WebElement KeyValuePopUp_Button_Confirm;
	
	@FindBy(xpath="//button[@class='btn btn-default']")
	private WebElement KeyValuePopUp_Button_Cancel;
	
	@FindBy(xpath="//table[@id='keyValueTableId']//th")
	private List<WebElement> KeyValuePairTableHeader;
	
	@FindBy(xpath="//tr[@pagination-id='KeyValueListId']")
	private List<WebElement> KeyValuePairTableRowList;
	
	@FindBy(xpath="//table[@id='keyValueTableId']/descendant::button[@title='Delete']")
	private WebElement Button_DeleteKeyValue;
	/****************************************************************************/
	
//	@FindBy(xpath="//button[normalize-space()='Save']")
//	private WebElement PopUp_Button_Save;
	
	@FindBy(xpath="//button[@class='cancel']")
	private WebElement PopUp_Button_Delete_No;
	
	@FindBy(xpath="//button[@class='confirm']")
	private WebElement PopUp_Button_Delete_Confirm;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/p")
	private WebElement PopUp_AlertMessage;
	
	public MetaDataPage(){
		waitUntilLoading();
	}
	public String getPageTitle() throws Exception {
		return getText(PageTitle);
	}
	
	public MetaDataPage openTaxonomyTab() throws Exception {
		click(Tab_Taxonomy);
		return this;
	}
	
	public MetaDataPage openTagTab() throws Exception {
		click(Tab_Tag);
		return this;
	}
	
	public MetaDataPage openKeyValueTab() throws Exception {
		click(Tab_KeyValue);
		return this;
	}
	
	public MetaDataPage clickOnCreateNewTaxonomy() throws Exception {
		click(Button_CreateNewTaxonomy);
		waitUntilLoading();
		return this;
	}
	
	public MetaDataPage createTaxonomy(String Statement, String Description, String UserFacingId, String VTWId ) throws Exception {
		sendKeys(TaxonomyPopUp_TextArea_Statement, Statement);
		sendKeys(TaxonomyPopUp_TextArea_Description, Description);
		sendKeys(TaxonomyPopUp_TextBox_UserFacingId, UserFacingId);
		sendKeys(TaxonomyPopUp_TextBox_VTWid, VTWId);
		click(TaxonomyPopUp_Button_AddNode);
		waitUntilLoading();
		return this;
	}
	
	public boolean isTaxonomyPresent(String Taxonomy) throws Exception {
		return isDisplayed(getWebElementBySubString(List_Taxonomies, Taxonomy));
	}
	
	public MetaDataPage expandTaxonomy(String Taxonomy) throws Exception {
		scrollToElement(Container_Taxonomy.findElement(By.xpath(Xpath_Taxonomy.replace("xxxxxxxxxx", Taxonomy)+Xpath_ExpandTaxonomy)));
		click(Container_Taxonomy.findElement(By.xpath(Xpath_Taxonomy.replace("xxxxxxxxxx", Taxonomy)+Xpath_ExpandTaxonomy)));
		waitUntilLoading();
		return this;	
	}
	
	public MetaDataPage openTaxonomyDropDown(String Taxonomy) throws Exception {
		scrollToElement(Container_Taxonomy.findElement(By.xpath(Xpath_Taxonomy.replace("xxxxxxxxxx", Taxonomy))));
		click(Container_Taxonomy.findElement(By.xpath(Xpath_Taxonomy.replace("xxxxxxxxxx", Taxonomy)+Xpath_TaxonomyDropDown)));
		return this;
	}
	
	public List<WebElement> getChildTaxonomies(String ParentTaxonomy) {
		return Container_Taxonomy.findElements(By.xpath(Xpath_Taxonomy.replace("xxxxxxxxxx", ParentTaxonomy)+Xpath_subTaxonomies));
	}
	
	public MetaDataPage clickOnAdd() throws Exception {
		//scrollToElement(TaxonomyDropDown_Add);
		click(TaxonomyDropDown_Add);
		waitUntilLoading();
		return this;
	}
	
	public MetaDataPage clickOnEdit() throws Exception {
		scrollToElement(TaxonomyDropDown_Edit);
		click(TaxonomyDropDown_Edit);
		waitUntilLoading();
		return this;
	}
	
	private void clickOnDelete() throws Exception {
		scrollToElement(TaxonomyDropDown_Delete);
		click(TaxonomyDropDown_Delete);
	}

	public String deleteTaxonomy() throws Exception {
		clickOnDelete();
		Thread.sleep(1000);
		confirmPopUp();
		waitUntilLoading();
		return getText(PopUp_AlertMessage);
	}
	
	public void confirmPopUp() throws Exception {
		click(PopUp_Button_Delete_Confirm);
	}
	
	public int getUsageCount(String Taxonomy) throws Exception {
		String fullTaxonomyName = getText(getWebElementBySubString(List_Taxonomies, Taxonomy));
		String Numbers = fullTaxonomyName.replaceAll("[^-?0-9]+", " ");
		List<String> list = Arrays.asList(Numbers.trim().split(" "));
		return Integer.parseInt(list.get(list.size()-1));
	}
	
	public MetaDataPage createTag(String tagTitle) throws Exception {
		click(Button_CreateNewTag);
		sendKeys(TagPopUp_TextBox_TagTitle, tagTitle);
		click(TagPopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public MetaDataPage searchTag(String tagTitle) throws Exception {
		sendKeys(TextBox_SearchTag, tagTitle);
		click(Button_SearchTag);
		waitUntilLoading();
		return this;
	}
	
	public String getListedTagNames() throws Exception {
		StringBuffer sb = new StringBuffer();
		for(LinkedHashMap<String,String> map : getTableDataAsList(TagTableHeaders,TagTableRowList,getElementLocator(TagTableRowList.get(0)).get("xpath")) )
			sb.append(map.get("Tag"));
		return sb.toString();
	}
	
	public int getListedTagUsageCount() throws Exception {
		StringBuffer sb = new StringBuffer();
		for(LinkedHashMap<String,String> map : getTableDataAsList(TagTableHeaders,TagTableRowList,getElementLocator(TagTableRowList.get(0)).get("xpath")) )
			sb.append(map.get("Usage"));
		return Integer.parseInt(sb.toString());
	}
	
	public String deleteTag() throws Exception {
		click(Button_DeleteTag);
		Thread.sleep(1000);
		confirmPopUp();
		waitUntilLoading();
		return getText(PopUp_AlertMessage);
	}
	
	public MetaDataPage createKeyValuePair(String Key, KeyValuePair keyType, @Optional List<String> selectTypeValues) throws Exception {
		click(Button_CreateNewKeyValue);
		sendKeys(KeyValuePopUp_TextBox_Key, Key);
		if(keyType.toString().equals(KeyValuePair.TEXT.toString()))
			click(KeyValuePopUp_RadioButton_Text);
		else if(keyType.toString().equals(KeyValuePair.SELECT.toString())) {
			click(KeyValuePopUp_RadioButton_Select);
			addValuesForSelectKey(selectTypeValues);
		}else if(keyType.toString().equals(KeyValuePair.DATE.toString()))
			click(KeyValuePopUp_RadioButton_Date);
		else if(keyType.toString().equals(KeyValuePair.TIME.toString()))
			click(KeyValuePopUp_RadioButton_Time);
		else if(keyType.toString().equals(KeyValuePair.NUMBER.toString()))
			click(KeyValuePopUp_RadioButton_Number);
		click(KeyValuePopUp_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	private MetaDataPage addValuesForSelectKey(List<String> values) throws Exception {
		for(String value: values) {
			sendKeys(KeyValuePairPopUp_TextBox_Value,value);
			click(KeyValuePair_Button_Add);
		}
		return this;
	}
	
	public MetaDataPage searchKeyValuePair(String Key) throws Exception {
		sendKeys(TextBox_SearchKeyValue, Key);
		click(Button_SearchKeyValue);
		waitUntilLoading();
		return this;
	}
	
	public String getListedKeyValue() throws Exception {
		StringBuffer sb = new StringBuffer();
		for(LinkedHashMap<String,String> map : getTableDataAsList(KeyValuePairTableHeader,KeyValuePairTableRowList,getElementLocator(KeyValuePairTableRowList.get(0)).get("xpath")) )
			sb.append(map.get("Key"));
		return sb.toString();
	}
	
	public int getListedKeyValueUsageCount() throws Exception {
		StringBuffer sb = new StringBuffer();
		for(LinkedHashMap<String,String> map : getTableDataAsList(KeyValuePairTableHeader,KeyValuePairTableRowList,getElementLocator(KeyValuePairTableRowList.get(0)).get("xpath")) )
			sb.append(map.get("Usage"));
		return Integer.parseInt(sb.toString());
	}
	
	public String deleteKeyValue() throws Exception {
		click(Button_DeleteKeyValue);
		Thread.sleep(1000);
		confirmPopUp();
		waitUntilLoading();
		return getText(PopUp_AlertMessage);
	}

}
