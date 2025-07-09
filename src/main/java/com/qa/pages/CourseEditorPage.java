package com.qa.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.base.PageBase;
import com.qa.testdata.TaskPriority;
import com.qa.testdata.TaskStatus;

import io.github.sukgu.support.FindElementBy;

public class CourseEditorPage extends PageBase {
	
	@FindBy(xpath="//div[@class='loading-g']")
	private WebElement Loader;
	
	@FindBy(xpath="//button[@class='godashboard']")
	private WebElement Button_GoToFrost;
	
	@FindBy(xpath="//div[@class='lm-heading']")
	private WebElement Loader_leftPane;
	
	@FindBy(xpath="//section[@class='right-pane']/div[@class='menu-block ui-draggable' and contains(@style,'block')]")
	private List<WebElement> List_VisiblePatterns;
	
	@FindBy(xpath="//section[@class='right-pane']/div[@class='menu-block ui-draggable']")
	private List<WebElement> List_AllPatterns;
	
	@FindBy(id="nextArrow")
	private WebElement Button_pagination_next;
	
	@FindBy(id="prevArrow")
	private WebElement Button_pagination_previous;
	
	@FindBy(id="save_modal")
	private WebElement Button_Save;
	
	@FindBy(id="save_toc_id")
	private WebElement Popup_Version_Save;
	
	@FindBy(xpath="//div[@id='saveModal']//div[@class='modal-content']")
	private WebElement Popup_Save_Body;
	
	@FindBy(id="editor-frame")
	private WebElement iframe_editor;
	
	@FindBy(id="testCont")
	private WebElement iframe_testMode;
	
	@FindBy(xpath="//section[@class='container']/p")
	private WebElement test_editor_text;
	
	@FindBy(xpath="//section[@class='container']/p")
	private WebElement editor_starting_text;
	
	@FindBy(xpath="//section[@class='container']/h2")
	private WebElement editor_DraggedContent;
	
	@FindBy(id="btnA")
	private WebElement PopUp_SaveALert_Button_Yes;
	
	@FindBy(id="btnB")
	private WebElement PopUp_SaveAlert_Button_No;
	
	@FindBy(id="btnC")
	private WebElement PopUp_SaveAlert_Button_Cancel;
	
	@FindBy(xpath="//label[@class='switch-button-background']")
	private WebElement Button_Toogle_Test;
	
	@FindBy(id="tocBtn")
	private WebElement Button_TableOfContent;
	
	@FindBy(xpath="//ol[contains(@class,'dropdown-menu nested_with_switch')]")
	private WebElement List_TOCnodes;
	
	@FindBy(xpath="//button[@class='contPreview activeNode']")
	private WebElement Text_MiniTOC_ActiveNode;
	
	@FindBy(xpath="//button[@aria-label='Close']")
	private WebElement Button_closeTOCList;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement PopUp__NoChange_Button_OK;
	
	@FindBy(xpath="//section[@class='container']/p")
	private List<WebElement> List_Editor_Text;
	
	@FindBy(xpath="//a[@title='body element']")
	private WebElement Link_Tags_Body;
	
	@FindBy(xpath="//span[contains(@class,'osmosis_icon')]")
	private WebElement Toolbar_Icon_Osmosis;
	
	@FindBy(xpath="//label[text()='Osmosis Video']/parent::div/descendant::input")
	private WebElement OsmosisPopUp_TextBox_OsmosisVideo;
	
	@FindBy(xpath="//a[contains(@class,'button_cancel')]")
	private WebElement OsmosisPopUp_Button_Cancel;
	
	@FindBy(xpath="//a[contains(@class,'button_ok')]")
	private WebElement OsmosisPopUp_Button_Ok;
	
	@FindBy(id="node_title")
	private WebElement Label_NodeTitle;
	
	@FindBy(id="node_title_input")
	private WebElement TextBox_NodeTitle;
	
	@FindBy(id="save_note_title")
	private WebElement Button_NodeTitle_Save;
	
	@FindBy(id="savingnote")
	private WebElement TextBox_Savingnote;
	
	@FindBy(xpath="//a[@title='Expository images']")
	private WebElement Toolbar_Icon_ExpositoryImages;
	
	@FindBy(id ="versionHistory")
	private WebElement Button_VersionHistory;
	
	/******************** Test Mode OBJECTS ******************/
	@FindBy(xpath="//button[normalize-space()='Preview']")
	private WebElement TestMode_Button_Preview;
	
	@FindBy(id="term")
	private WebElement TestMode_ToolTip_Term;
	
	@FindBy(id="tip")
	private WebElement TestMode_ToolTip_Description;
	
	@FindBy(xpath="//p/a[@class='keyword']")
	private WebElement TestMode_Editor_LinkedText;
	
	@FindBy(xpath="//iframe[@title='Widget Brightcove']")
	private WebElement TestMode_iframe_BrightCove_Parent;
	
	@FindBy(id="videoframe")
	private WebElement TestMode_iframe_BrightCove_Player;
	
	@FindBy(xpath="//div[@class='vjs-play-control vjs-control vjs-button']")
	private WebElement TestMode_BrightCove_Button_Play;
	
	@FindBy(xpath="//div[@title='Pause']")
	private WebElement TestMode_BrightCove_Button_Pause;
	
	@FindBy(xpath="//div[@class='vjs-current-time-display']")
	private WebElement TestMode_BrightCove_Timer_ElapsedTime;
	
	@FindBy(xpath="//div[@class='vjs-duration-display']")
	private WebElement TestMode_BrightCove_Timer_FullDuration;
	
	@FindBy(id="title")
	private WebElement TestMode_Widget_BrightCove_Title;
	
	@FindBy(xpath="//iframe[@title='Widget Accordion']")
	private WebElement TestMode_iframe_Accordion;
	
	@FindBy(xpath="//div[@id='accordion']/descendant::a[@role='button']")
	private WebElement TestMode_Accordion_Button_Expand;
	
	@FindBy(xpath="//iframe[@class='content-video']")
	private WebElement TestMode_iframe_Accordion_BrightCove_Player;
	
	@FindBy(xpath="//iframe[@class='content-video']/following-sibling::h2")
	private WebElement TestMode_Accordion_BrightCove_Title;
	
	@FindElementBy(xpath="//iframe[@title='osmosis-video-iframe']")
	private WebElement TestMode_iframe_OsmosisPlayer;
	
	@FindElementBy(xpath="//div[@class='ui-video-VideoPlayerContainer']")
	private WebElement TestMode_Osmosis_Button_PlayOverlay;
	
	@FindElementBy(xpath="//button[@title='Play']")
	private WebElement TestMode_Osmosis_Button_Play;
	
	@FindElementBy(xpath="//button[@title='Pause']")
	private WebElement TestMode_Osmosis_Button_Pause;
	
	@FindElementBy(xpath="//span[@class='vjs-current-time-display']")
	private WebElement TestMode_Osmosis_Timer_ElapsedTime;
	
	@FindElementBy(xpath="//span[@class='vjs-duration-display']")
	private WebElement TestMode_Osmosis_Timer_FullDuration;
	
	@FindBy(xpath="//iframe[@data-widget-type='hotspot']")
	private WebElement TestMode_iframe_HotSpot;
	
	@FindBy(xpath="//div[@class='hotsspot-img']/child::img")
	private WebElement TestMode_HotSpot_Image;
	
	@FindBy(xpath="//div[@id='theElement-0']/child::div[contains(@id,'selector')]")
	private List<WebElement> TestMode_HotSpot_HotSpots;
	
	@FindBy(id="hotspot_labelDescription")
	private WebElement TestMode_HotSpot_Active_TitleAndDescription;
	
	@FindBy(id="hotspot-wrapper")
	private WebElement TestMode_HotSpotWrapper;
	
	/******************************************************/
	
	/******************** Preview Mode OBJECTS ******************/
	
	@FindBy(id="contentPreviewiframe")
	private WebElement PreviewMode_iframe_PreviewPanel;
	
	@FindBy(xpath="//div[@id='testPreviewModalHeader']/child::button[@class='close']")
	private WebElement PreviewMode_Button_Close;
	
	@FindBy(xpath="//a[contains(@class,'link-to-preview active')]")
	private WebElement PreviewMode_Active_Node;
	
	@FindBy(id="content_preview_iframe3")
	private WebElement PreviewMode_iframe_content;
	
	/******************************************************/
	
	/*************** Create/Add Link OBJECTS ************/
	
	@FindBy(xpath="//a[@title='Add Link']")
	private WebElement Icon_AddLink;
	
	@FindBy(xpath="//a[normalize-space()='URL']")
	private WebElement AddLinkPopUp_Tab_URL;
	
	@FindBy(id="urlHolder")
	private WebElement AddLinkPopUp_TextBox_URL;
	
	@FindBy(xpath="//a[normalize-space()='Advanced']")
	private WebElement AddLinkPopUp_Tab_Advanced;
	
	@FindBy(id="dropdownMenu2")
	private WebElement AddLinkPopUp_DropDown_SelectLinkType;
	
	@FindBy(xpath="//a[normalize-space()='Glossary']")
	private WebElement AddLinkPopUp_Tab_Glossary;
	
	@FindBy(xpath="//div[@class='spinner-div']//i[@id='spinner']")
	private WebElement AddLinkPopUp_Glossary_Loader;
	
	@FindBy(id="glosTerm")
	private WebElement AddLinkPopUp_Glossary_selectedText;
	
	@FindBy(id="glosDesc")
	private WebElement AddLinkPopUp_Glossary_TextBox_Search;
	
	@FindBy(id="search-glossary-results")
	private WebElement AddLinkPopUp_Glossary_Button_Search;
	
	@FindBy(id="definition-rec-id-input")
	private WebElement AddLinkPopUp_Glossary_TextBox_DefinitionRecordId;
	
	@FindBy(id="definition-text-rec-input")
	private WebElement AddLinkPopUp_Glossary_TextBox_DefinitionTextRecord;
	
	@FindBy(id="source-input")
	private WebElement AddLinkPopUp_Glossary_TextBox_Source;
	
	@FindBy(xpath="desc-input")
	private WebElement AddLinkPopUp_Glossary_TextBox_Description;
	
	@FindBy(xpath="//ul[@id='glossary-desc']/li")
	private WebElement AddLinkPopUp_Glossary_ListedSearch; //This can be updated to List in future if required
	
	@FindBy(xpath="//div[@class='modal-dialog modal-lg']//button[@class='btn btn-default'][normalize-space()='CANCEL']")
	private WebElement AddLinkPopUp_Button_Cancel;
	
	@FindBy(id="anchorCont")
	private WebElement AddLinkPopUp_Button_Confirm;
	/******************************************************/
	
	/******************** Widget OBJECTS ******************/
	
	@FindBy(id="widFrame")
	private WebElement iframe_Widget;
	
	@FindBy(xpath="//div[@class='lm-rochak']")
	private WebElement Widget_Body;
	
	@FindBy(xpath="//div[contains(@class,'toolBoxLarge to-copy')]")
	private WebElement Widget_Button_ReuseExisting;
	
	@FindBy(xpath="//div[contains(@class,'toolBoxLarge to-set')]")
	private WebElement Widget_Button_Edit;
	
	@FindBy(xpath="//div[contains(@class,'toolBoxLarge to-del')]")
	private WebElement Widget_Button_Delete;
	
	@FindBy(id="cancel-btn")
	private WebElement Widget_Button_Cancel;
	
	@FindBy(id="save-button")
	private WebElement Widget_Button_Save;
	
	@FindBy(xpath="//button[@class='btn btn-primary add']")
	private WebElement EditAccordion_AddSlide;
	
	@FindBy(xpath="//div[@id='exwidgetModal']//div[@class='modal-content']")
	private WebElement Widget_BrightCove_Body;
	
	@FindBy(xpath="//label[@for='videoid']")
	private WebElement Widget_BrightCove_Label_VideoId;
	
	@FindBy(id="videoid")
	private WebElement Widget_BrightCove_TextBox_VideoId;
	
	@FindBy(id="videotitle")
	private WebElement Widget_BrightCove_TextBox_VideoTitle;
	
	@FindBy(xpath="//label[text()='Label']/parent::div//iframe[@class='cke_wysiwyg_frame cke_reset']")
	private WebElement Widget_Accordion_iframe_Label;
	
	@FindBy(xpath="//label[text()='Description']/parent::div//iframe[@class='cke_wysiwyg_frame cke_reset']")
	private WebElement Widget_Accordion_iframe_Description;
	
	@FindBy(xpath="//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']/p")
	private WebElement Widget_Accordion_TextArea;
	
	@FindBy(xpath="//span[@class='cke_button_icon cke_button__brightcove_icon']")
	private WebElement Widget_Accordion_Toolbar_Icon_BrightCove;
	
	@FindBy(xpath="//label[text()='Brightcove Video ID']/parent::div//input")
	private WebElement Widget_Accordion_BrightCove_TextBox_VideoId;
	
	@FindBy(xpath="//label[text()='Brightcove Video Title']/parent::div//input")
	private WebElement Widget_Accordion_BrightCove_TextBox_VideoTitle;
	
	@FindBy(xpath="//a[contains(@class,'button_cancel')]")
	private WebElement Widget_Accordion_BrightCove_Button_Cancel;
	
	@FindBy(xpath="//a[contains(@class,'button_ok')]")
	private WebElement Widget_Accordion_BrightCove_Button_Ok;
	
	@FindBy(xpath="//a[contains(@class,'cke_button cke_button__osmosis')]")
	private WebElement Widget_Accordion_Toolbar_Icon_Osmosis;
	/******************************************************/
	
	/************ Expository Image realted OBJECTS ***********/
	
	@FindBy(xpath="//div[@id='cke_1_contents']//iframe[@id='editor-frame']")
	private WebElement ExpositoryImage_iframe_legendTextEditor;
	
	@FindBy(xpath="//br/parent::body[contains(@class,'cke_editable cke_editable_themed')]")
	private WebElement ExpositoryImage_TextBox_LegendText;
	
	@FindBy(xpath="//button[normalize-space()='Select Expository Image']")
	private WebElement ExpositoryImage_Button_SelectExpositoryImage;
	
	@FindBy(xpath="//button[normalize-space()='Select Image']")
	private WebElement ExpositoryImage_Button_SelectImage;
	
	@FindBy(xpath="//div[@id='expository_modal']//button[@class='btn btn-default'][normalize-space()='CANCEL']")
	private WebElement ExpositoryImage_Button_Cancel;
	
	@FindBy(id="expositorySubmit")
	private WebElement ExpositoryImage_Button_Continue;
	
	@FindBy(id="expository_select_asset_remove")
	private WebElement ExpositoryImage_Button_RemoveExpositoryImage;
	
	@FindBy(id="select_asset_remove")
	private WebElement ExpositoryImage_Button_RemoveImage;
	
	@FindBy(xpath="//div[@id='drop-block-expository']/span[contains(@class,'no_asset_selected')]")
	private WebElement ExpositoryImage_Text_NoImage1;
	
	@FindBy(xpath="//div[@id='drop-block-expository-image']/span[contains(@class,'no_asset_selected')]")
	private WebElement ExpositoryImage_Text_NoImage2;
	
	@FindBy(xpath="//button[@id='expositorySubmitEdit']")
	private WebElement ExpositoryImage_Button_Edit_Continue;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/child::h2")
	private WebElement AlertPopUp_HeaderText;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/child::p")
	private WebElement AlertPopUp_Message;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement AlertPopUp_Button_OK;
	
	@FindBy(xpath="//expository-image[@class='expository_image_tag']")
	private WebElement ExpositoryImageObject;
	
	@FindBy(xpath="//img[@class='expository']")
	private WebElement Image_ExpositoryImage;
	
	@FindBy(xpath="//expository-image-legend[@contenteditable='false']")
	private WebElement Text_ExpositoryImageLegendText;
	
	@FindBy(xpath="//span[@class='glyphicon glyphicon-edit tool']")
	private WebElement Button_EditExpositoryImage;
	
	@FindBy(xpath="//span[@title='Delete']")
	private WebElement Button_DeleteExpositoryImage;
	/******************************************************/
	
	/************ Image Search OBJECTS ***********/
	
	@FindBy(id="spinner")
	private WebElement ImageSearch_spinner;
	
	@FindBy(id="home-tab")
	private WebElement ImageSearch_Tab_PageRepository;
	
	@FindBy(id="profile-tab")
	private WebElement ImageSearch_Tab_LOR;
	
	@FindBy(xpath="//div[@class='tab-wrap']//button[@class='close pull-right']")
	private WebElement ImageSearch_Button_Close;
	
	@FindBy(id="global-asset-picker")
	private WebElement ImageSearch_PageRepository_TextBox_SearchImage;
	
	@FindBy(xpath="//div[@class='col-lg-8 col-md-4 col-sm-4 col-xs-4 Advanced-search']//button[@type='button'][normalize-space()='search']")
	private WebElement ImageSearch_PageRepository_Button_Search;
	
	@FindBy(xpath="//div[@class='advancemedia_search mt-3']//button[@class='btn btn-secondary pull-left']")
	private WebElement ImageSearch_PageRepository_Button_AdvanceSearch;
	
	@FindBy(xpath="//div[@id='asset-uploader']//input[@type='file']")
	private WebElement ImageSearch_PageRepository_Input_UploadFile;
	
	@FindBy(xpath="//label[@for='cbtestg_0']")
	private WebElement ImageSearch_PageRepository_SearchedImageRadioButton;
	
	@FindBy(xpath="//div[@class='block-img image']")
	private WebElement ImageSearch_PageRepository_SearchedImage;
	
	@FindBy(id="submit_image")
	private WebElement ImageSearch_PageRepository_Button_AddSelected;
	
	@FindBy(xpath="//div[@class='loader-bg']/child::div[@class='text']")
	private WebElement ImageSearch_progressText;
	
	@FindBy(xpath="//div[contains(@class,'selectmedia-text')]/descendant::h2")
	private WebElement ImageSearch_Text_NumberOfRecords;
	
	/******************************************************/
	
	/**************** HotSpot Widget Locators *************/
	
	@FindBy(id="exwidgetModal")
	private WebElement HotSpotWidget_Body;
	
	@FindBy(id="addmedia")
	private WebElement HotSpotWidget_Button_AddImage;
	
	@FindBy(xpath="//div[@id='map-area']/descendant::img")
	private WebElement HotspotWidget_uploaded_image;
	
	@FindBy(id="addhospotBtn")
	private WebElement HotspotWidget_Button_CreateHotSpot;
	
	@FindBy(id="alt")
	private WebElement HotSpotWidget_TextBox_AltText;
	
	@FindBy(xpath="//div[@class='FC-name pull-left']/child::span")
	private List<WebElement> HotSpotWidget_List_HotSopts;
	
	@FindBy(xpath="//div[@class='Slideline-container-block' and contains(@style,'block')]/descendant::iframe[contains(@title,'Rich Text Editor')]")
	private WebElement HotSpotWidget_iframe_TitleAndDescription;
	
	@FindBy(xpath="//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']/p")
	private WebElement HotSpotWidget_TextArea_TitleAndDescription;
	
	@FindBy(xpath="//div[@class='Slideline-container-block' and contains(@style,'block')]/descendant::input[@placeholder='loopHighlight']")
	private WebElement HotSpotWidget_TextField_hotspot_loopHighlight;
	
	@FindBy(xpath="//div[@class='FC-block active']/child::button[@class='close panel-del']")
	private WebElement HotSpotWidget_Button_DeleteHotSpot;
	
/*****************Task Comment Section Elements****************/
	
	@FindBy(xpath="(//a[normalize-space()='body'])[1]")
	private WebElement Button_Body;
	
	@FindBy(xpath="//div[@class='tab-content task']//input[@id='newAction']")
	private WebElement TextBox_Tasks;
	
	@FindBy(xpath="//div[@class='tab-content comment']//input[@id='newAction']")
	private WebElement Text_Comments;
	
	@FindBy(xpath="//textarea[@id='comment-textarea']")
	private WebElement TextArea_TasksComments;
	
	@FindBy(id="ticket_attachment_task")
	private WebElement InputFile_TaskAttachment;
	
	@FindBy(id="ticket_attachment_comment")
	private WebElement InputFile_CommentAttachment;
	//private String css_InputFile_CommentAttachment = "input[id=\"ticket_attachment_comment\"]";
	
	@FindBy(id="readytotest2")
	private WebElement SelectList_Users;
	
	@FindBy(id="readytotest")
	private WebElement SelectList_TaskStatus;
	
	@FindBy(id="readytotest1")
	private WebElement SelectList_TaskPriority;
	
	@FindBy(xpath="(//a[normalize-space()='Cancel'])[1]")
	private WebElement Button_TaskComment_Cancel;
	
	@FindBy(xpath="//a[normalize-space()='Submit']")
	private WebElement Button_TaskComment_Submit;
	
	@FindBy(xpath="//label[@class='action-link edit']")
	private WebElement Button_TaskEdit;
	
	@FindBy(xpath="//button[@class='confirm']")
	private WebElement Button_Confirm;
	
	@FindBy(xpath="//i[@class='fa fa-trash']")
	private WebElement Button_TaskDelete;
	
	@FindBy(xpath="//div[@id='please-wait' and @ng-show='pleaseWaitTask']")
	private WebElement Label_Task_PleaseWait;
	
	@FindBy(id="alert-message-task")
	private WebElement AlertMessage_Task;
	
	@FindBy(id="alert-message-comment")
	private WebElement AlertMessage_Comment;
	
	@FindBy(className = "fa-chevron-left")
	private WebElement Button_TaskPanel;
	
	/******************************************************/
	
	/**
	 * @throws Exception ****************************************************/
	
	//private final String osmosis_shadowRoot = "osmosis-video-component";
	
	public CourseEditorPage() throws Exception {
		try {
		wait.until(ExpectedConditions.visibilityOf(Loader));
		wait.until(ExpectedConditions.invisibilityOf(Loader));
	} catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
	}
	}
	
	public TemplateManagementPage goBackToTemplateManagement() throws Exception {
		click(Button_GoToFrost);
		if(isDisplayed(PopUp_SaveALert_Button_Yes))
		{
			click(PopUp_SaveALert_Button_Yes);
		}
		return new TemplateManagementPage();
	}
	
	public EditTOCPage goBackToEditToc() throws Exception {
		click(Button_GoToFrost);
		Thread.sleep(2000);
		if(isDisplayed(PopUp_SaveALert_Button_Yes)){
			click(PopUp_SaveALert_Button_Yes);
		}
		Thread.sleep(5000);
		return new EditTOCPage();
	}
	
	public BuildingBlocksPage goBackToBuildingBlocks() throws Exception {
		click(Button_GoToFrost);
		click(PopUp_SaveALert_Button_Yes);
		return new BuildingBlocksPage();
	}
	
	public List<String> getAllPatterns() throws Exception {
		Thread.sleep(4000);
		List<String> patterns = getText(List_VisiblePatterns);
		while (isEnabled(Button_pagination_next)) {
			click(Button_pagination_next);
			patterns.addAll(getText(List_VisiblePatterns));
		}
		//click(Button_pagination_next);
		//patterns.addAll(getText(List_VisiblePatterns));
		patterns.replaceAll(String::trim);
		click(Button_pagination_previous);
		return patterns;
	}
	
	public String getPattern(String Name) throws Exception {
		return getText(getWebElementByExpectedText(List_AllPatterns, Name));
	}
	
	public CourseEditorPage resetPatternList() throws Exception {
		while(isEnabled(Button_pagination_previous)){
			click(Button_pagination_previous);
		}
		return this;
	}
	

	public CourseEditorPage AddPatternToEditor(String PatternName) throws Exception {
		Thread.sleep(4000);
		boolean patternFound = false;
		do {
			try {
				clickAndHold(getWebElementByExpectedText(List_AllPatterns, PatternName));
				patternFound = true;
			} catch (Exception e) {
				if (isEnabled(Button_pagination_next)) {
					click(Button_pagination_next);
				} else {
					break; // Exit loop if no more pages are available
				}
			}
		} while (!patternFound);

		if (!patternFound) {
			throw new NoSuchElementException("Pattern with name '" + PatternName + "' not found in any page.");
		}

		switchToFrame(iframe_editor);
		moveToElementAndRelease(editor_starting_text);
		switchToDefaultContent();
		return this;
	}

	
	public String getDraggedPatternsContent() throws Exception {
		switchToFrame(iframe_editor);
		String content = getText(editor_DraggedContent);
		switchToDefaultContent();
		return content;
	}
	
	public CourseEditorPage addTextToEditor(String text) throws Exception {
		switchToFrame(iframe_editor);
		Thread.sleep(3000);
		//click(editor_starting_text);
		sendKeysByJsExecutor(editor_starting_text, text);
		//sendKeys(editor_starting_text, text);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage addTextToEditorAndWaitForAutoSave(String text) throws Exception {
		switchToFrame(iframe_editor);
		Thread.sleep(3000);
		sendKeysByJsExecutor(editor_starting_text, text);
		switchToDefaultContent();
		Thread.sleep(33000);//waiting for autosave to kick in, this is a temporary solution
		return this;
	}
	
	public CourseEditorPage saveContent() throws Exception {
		click(Button_Save);
		if(isDisplayed(AlertPopUp_Button_OK)){
			click(AlertPopUp_Button_OK);
		}
		click(Popup_Version_Save);
		wait.until(ExpectedConditions.invisibilityOf(Popup_Save_Body));
		return this;
	}
	
	public CourseEditorPage saveContentWithVersionNotes(String notes) throws Exception {
		click(Button_Save);
		if(isDisplayed(AlertPopUp_Button_OK)){
			click(AlertPopUp_Button_OK);
		}
		sendKeys(TextBox_Savingnote, notes);
		click(Popup_Version_Save);
		wait.until(ExpectedConditions.invisibilityOf(Popup_Save_Body));
		return this;
	}
	
	public CourseEditorPage closeNoChangePopup() throws Exception {
		click(PopUp__NoChange_Button_OK);
		return this;
	}
	
	public String getEditorText() throws Exception {
		switchToFrame(iframe_editor);
		String text = getText(editor_starting_text);
		switchToDefaultContent();
		return text;
	}
	
	public CourseEditorPage toogleTestMode() throws Exception {
		click(Button_Toogle_Test);
		Thread.sleep(6000);
		return this;
	}
	
	public String getEditorText_TestMode() throws Exception {
		switchToFrame(iframe_testMode);
		String text = getText(test_editor_text);
		switchToDefaultContent();
		return text;
	}
	
	public HashMap<String,String> getLinkedGlossaryToolTipDetails_TestMode() throws Exception {
		switchToFrame(iframe_testMode);
		click(TestMode_Editor_LinkedText);
		HashMap<String,String> glossaryToolTip = new HashMap<String,String>();
		glossaryToolTip.put("Term", getText(TestMode_ToolTip_Term));
		glossaryToolTip.put("Description", getText(TestMode_ToolTip_Description));
		switchToDefaultContent();
		return glossaryToolTip;
	}
	
	public CourseEditorPage clickOnTocMenu() throws Exception {
		click(Button_TableOfContent);
		return this;
	}
	
	public List<String> getTOCList() throws Exception {
		String list = getText(List_TOCnodes);
		String nodes[] = list.split("\\r?\\n");
		List<String> node_list = new ArrayList<String>();
		for(String node: nodes) {
			node_list.add(node);
		}
		click(Button_closeTOCList);
		Thread.sleep(2000);
		return node_list;
	}
	
	public CourseEditorPage selectAllTextFromEditor() throws Exception {
		switchToFrame(iframe_editor);
		clickByJsExecutor(editor_starting_text);
		switchToDefaultContent();
		clickByJsExecutor(Link_Tags_Body);
		return this;
	}
	
	public CourseEditorPage clickOnAddLink() throws Exception {
		click(Icon_AddLink);
		Thread.sleep(2000);
		return this;
	}
	
	public CourseEditorPage openGlossary() throws Exception {
		click(AddLinkPopUp_Tab_Glossary);
		wait.until(ExpectedConditions.visibilityOf(AddLinkPopUp_Glossary_Loader));
		wait.until(ExpectedConditions.invisibilityOf(AddLinkPopUp_Glossary_Loader));
		return this;
	}
	
	public CourseEditorPage searchGlossary(String searchTerm) throws Exception {
		clear(AddLinkPopUp_Glossary_TextBox_Search);
		sendKeys(AddLinkPopUp_Glossary_TextBox_Search, searchTerm);
		updateGlossarySearchButtonCSS();
		clickByJsExecutor(AddLinkPopUp_Glossary_Button_Search);
		return this;
	}
	
	public CourseEditorPage selectListedGlossary() throws Exception {
		click(AddLinkPopUp_Glossary_ListedSearch);
		return this;
	}
	
	public HashMap<String,String> getSelectedGlossaryDetails() throws Exception {
		HashMap<String,String> selectedGlossary = new HashMap<String,String>();
		selectedGlossary.put("Term", getText(AddLinkPopUp_Glossary_TextBox_Search));
		selectedGlossary.put("Definition Record Id", getText(AddLinkPopUp_Glossary_TextBox_DefinitionRecordId));
		selectedGlossary.put("Definition Text Record", getText(AddLinkPopUp_Glossary_TextBox_DefinitionTextRecord));
		selectedGlossary.put("Source", getText(AddLinkPopUp_Glossary_TextBox_Source));
		selectedGlossary.put("Description", getText(AddLinkPopUp_Glossary_TextBox_Description));
		return selectedGlossary;
	}
	
	public CourseEditorPage clickOnConfirm_AddLink() throws Exception {
		click(AddLinkPopUp_Button_Confirm);
		wait.until(ExpectedConditions.invisibilityOf(AddLinkPopUp_Button_Confirm));
		return this;
	}
	
	public CourseEditorPage test() throws Exception {
		switchToFrame(iframe_editor);
		click(Widget_Body);
		click(Widget_Button_Edit);
		Thread.sleep(3000);
		switchToDefaultContent();
		switchToFrame(iframe_Widget);
		click(EditAccordion_AddSlide);
		Thread.sleep(3000);
		return this;
	}
	
	public CourseEditorPage addBrightCoveWidget(String videoId, String videoTitle) throws Exception {
		AddPatternToEditor("BRIGHTCOVE");
		clickOnEditWidget();
		//wait.until(ExpectedConditions.visibilityOf(Widget_BrightCove_Label_VideoId));
		switchToFrame(iframe_Widget);
		//wait.until(ExpectedConditions.textToBePresentInElement(Widget_Button_Cancel,"Cancel"));
		//wait.until(ExpectedConditions.visibilityOf(Widget_BrightCove_Label_VideoId));
		sendKeys(Widget_BrightCove_TextBox_VideoId, videoId);
		sendKeys(Widget_BrightCove_TextBox_VideoTitle, videoTitle);
		click(Widget_Button_Save);
		switchToDefaultContent();
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.invisibilityOf(Widget_BrightCove_Body));
		return this;
	}
	
	public CourseEditorPage addBrightCoveVideoInAccordion(String label, String videoId, String videoTitle) throws Exception {
		AddPatternToEditor("ACCORDION");
		clickOnEditWidget();
		//wait.until(ExpectedConditions.visibilityOf(Widget_BrightCove_Label_VideoId));
		switchToFrame(iframe_Widget);
		//wait.until(ExpectedConditions.textToBePresentInElement(Widget_Button_Cancel,"Cancel"));
		//wait.until(ExpectedConditions.visibilityOf(Widget_BrightCove_Label_VideoId));
		switchToFrame(Widget_Accordion_iframe_Label);
		sendKeysByJsExecutor(Widget_Accordion_TextArea, label);
		switchToParentFrame();
		click(Widget_Accordion_Toolbar_Icon_BrightCove);
		//Thread.sleep(2000);
		sendKeys(Widget_Accordion_BrightCove_TextBox_VideoId, videoId);
		sendKeys(Widget_Accordion_BrightCove_TextBox_VideoTitle, videoTitle);
		click(Widget_Accordion_BrightCove_Button_Ok);
		//Thread.sleep(2000);
		click(Widget_Button_Save);
		switchToDefaultContent();
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.invisibilityOf(Widget_BrightCove_Body));
		return this;
	}
	
	public CourseEditorPage playBrightCoveVideoByWidget(String durationInSeconds) throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_BrightCove_Parent);
		switchToFrame(TestMode_iframe_BrightCove_Player);
		Thread.sleep(7000);
		click(TestMode_BrightCove_Button_Play);
		while(!getText(TestMode_BrightCove_Timer_ElapsedTime).equals(playerTimeHandler(durationInSeconds))) {
			mouseHover(TestMode_BrightCove_Timer_ElapsedTime);
		}
		
		wait.until(ExpectedConditions.textToBePresentInElement(TestMode_BrightCove_Timer_ElapsedTime, playerTimeHandler(durationInSeconds)));
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage playBrightCoveVideoByAccordion(String durationInSeconds) throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_Accordion);
		click(TestMode_Accordion_Button_Expand);
		switchToFrame(TestMode_iframe_Accordion_BrightCove_Player);
		Thread.sleep(7000);
		click(TestMode_BrightCove_Button_Play);
		while(!getText(TestMode_BrightCove_Timer_ElapsedTime).equals(playerTimeHandler(durationInSeconds))) {
			mouseHover(TestMode_BrightCove_Timer_ElapsedTime);
		}
		
		wait.until(ExpectedConditions.textToBePresentInElement(TestMode_BrightCove_Timer_ElapsedTime, playerTimeHandler(durationInSeconds)));
		switchToDefaultContent();
		return this;
	}
	
	public String getBrigtcoveVideoTitle_Widget() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_BrightCove_Parent);
		String videoTitle = getText(TestMode_Widget_BrightCove_Title);
		switchToDefaultContent();
		return videoTitle;
	}
	
	public int getBrightCoveVideoElapsedTime_Widget() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_BrightCove_Parent);
		switchToFrame(TestMode_iframe_BrightCove_Player);
		int time = convertTotalDurationToSeconds(getText(TestMode_BrightCove_Timer_ElapsedTime));
		switchToDefaultContent();
		return time;
	}
	
	public int getBrightCoveVideoDurationTime_Widget() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_BrightCove_Parent);
		switchToFrame(TestMode_iframe_BrightCove_Player);
		int time = convertTotalDurationToSeconds(getText(TestMode_BrightCove_Timer_FullDuration)); 
		switchToDefaultContent();
		return time;
	}
	
	public String getBrigtcoveVideoTitle_Accordion() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_Accordion);
		String videoTitle = getText(TestMode_Accordion_BrightCove_Title);
		switchToDefaultContent();
		return videoTitle;
	}
	
	public int getBrightCoveVideoElapsedTime_Accordion() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_Accordion);
		switchToFrame(TestMode_iframe_Accordion_BrightCove_Player);
		int time = convertTotalDurationToSeconds(getText(TestMode_BrightCove_Timer_ElapsedTime));
		switchToDefaultContent();
		return time;
	}
	
	public int getBrightCoveVideoDurationTime_Accordion() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_Accordion);
		switchToFrame(TestMode_iframe_Accordion_BrightCove_Player);
		int time = convertTotalDurationToSeconds(getText(TestMode_BrightCove_Timer_FullDuration)); 
		switchToDefaultContent();
		return time;
	}
	
	private int convertTotalDurationToSeconds(String duration) {
		String[] values = duration.split(":");
		return Integer.parseInt(values[0])*60+Integer.parseInt(values[1]);
	}
	
	private String playerTimeHandler(String durationInSeconds) {
		String time = (durationInSeconds.length()>1) ? "0:"+durationInSeconds : "0:0"+durationInSeconds;
		return time;
	}
	
	public CourseEditorPage addOsmosisVideoFromToolbar(String videoName) throws Exception {
		click(Toolbar_Icon_Osmosis);
		sendKeys(OsmosisPopUp_TextBox_OsmosisVideo, videoName);
		click(OsmosisPopUp_Button_Ok);
		Thread.sleep(3000);
		return this;
	}
	
	public CourseEditorPage addOsmosisVideoInAccordion(String label, String videoTitle) throws Exception {
		AddPatternToEditor("ACCORDION");
		clickOnEditWidget();
		switchToFrame(iframe_Widget);
		switchToFrame(Widget_Accordion_iframe_Label);
		sendKeysByJsExecutor(Widget_Accordion_TextArea, label);
		switchToParentFrame();
		click(Widget_Accordion_Toolbar_Icon_Osmosis);
		sendKeys(OsmosisPopUp_TextBox_OsmosisVideo, videoTitle);
		click(OsmosisPopUp_Button_Ok);
		click(Widget_Button_Save);
		switchToDefaultContent();
		Thread.sleep(3000);
		return this;
	}
	
	public CourseEditorPage playOsmosisVideo(String durationInSeconds) throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_OsmosisPlayer);
		Thread.sleep(7000);
		click(TestMode_Osmosis_Button_PlayOverlay);
		while(!getText(TestMode_Osmosis_Timer_ElapsedTime).equals(playerTimeHandler(durationInSeconds))) {
			mouseHover(TestMode_Osmosis_Timer_ElapsedTime);
		}
		wait.until(ExpectedConditions.textToBePresentInElement(TestMode_Osmosis_Timer_ElapsedTime, playerTimeHandler(durationInSeconds)));
		return this;
	}
	
	public CourseEditorPage playOsmosisVideo_Accordion(String durationInSeconds) throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_Accordion);
		click(TestMode_Accordion_Button_Expand);
		switchToFrame(TestMode_iframe_OsmosisPlayer);
		Thread.sleep(7000);
		click(TestMode_Osmosis_Button_PlayOverlay);
		while(!getText(TestMode_Osmosis_Timer_ElapsedTime).equals(playerTimeHandler(durationInSeconds))) {
			mouseHover(TestMode_Osmosis_Timer_ElapsedTime);
		}
		wait.until(ExpectedConditions.textToBePresentInElement(TestMode_Osmosis_Timer_ElapsedTime, playerTimeHandler(durationInSeconds)));
		switchToDefaultContent();
		return this;
	}
	
	public int getOsmosisElapsedTime_Toolbar() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_OsmosisPlayer);
		int time = convertTotalDurationToSeconds(getText(TestMode_BrightCove_Timer_ElapsedTime));
		switchToDefaultContent();
		return time;
	}
	
	public int getOsmosisVideoDurationTime_Toolbar() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_OsmosisPlayer);
		int time = convertTotalDurationToSeconds(getText(TestMode_BrightCove_Timer_FullDuration));
		switchToDefaultContent();
		return time;
	}
	
	public int getOsmosisVideoElapsedTime_Accordion() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_Accordion);
		switchToFrame(TestMode_iframe_OsmosisPlayer);
		int time = convertTotalDurationToSeconds(getText(TestMode_Osmosis_Timer_ElapsedTime));
		switchToDefaultContent();
		return time;
	}
	
	public int getOsmosisVideoDurationTime_Accordion() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_Accordion);
		switchToFrame(TestMode_iframe_OsmosisPlayer);
		int time = convertTotalDurationToSeconds(getText(TestMode_Osmosis_Timer_FullDuration)); 
		switchToDefaultContent();
		return time;
	}
	
	public String getNodeTitle() throws Exception {
		return getText(Label_NodeTitle);
	}
	
	public CourseEditorPage clickOnNodeTitle() throws Exception {
		Thread.sleep(4000);
		click(Label_NodeTitle);
		return this;
	}
	
	public boolean isTitleEditable() throws Exception {
		return TextBox_NodeTitle.isDisplayed();
	}
	
	public CourseEditorPage updateNodeTitle(String nodeTitle) throws Exception {
		clear(TextBox_NodeTitle);
		sendKeys(TextBox_NodeTitle, nodeTitle);
		click(Button_NodeTitle_Save);
		wait.until(ExpectedConditions.invisibilityOf(Button_NodeTitle_Save));
		Thread.sleep(2000);
		return this;
	}
	
	public String getActiveNodeTitleFromMiniTOC() throws Exception {
		click(Button_TableOfContent);
		wait.until(ExpectedConditions.visibilityOf(Text_MiniTOC_ActiveNode));
		scrollToElement(Text_MiniTOC_ActiveNode);
		return getText(Text_MiniTOC_ActiveNode);
	}
	
	public CourseEditorPage switchToPreviewMode() throws Exception {
		//toogleTestMode();
		click(TestMode_Button_Preview);
		Thread.sleep(4000);
		return this;
	}
	
	public String getActiveNode() throws Exception {
		//switchToFrame(iframe_testMode);
		switchToFrame(PreviewMode_iframe_PreviewPanel);
		String activeNode = getText(PreviewMode_Active_Node);
		switchToDefaultContent();
		return activeNode;
	}
	
	public CourseEditorPage closePreviewMode() throws Exception {
		//click(PreviewMode_Button_Close);
		Thread.sleep(2000);
		click(PreviewMode_Button_Close);
		Thread.sleep(2000);
		return this;		
	}
	
	public CourseEditorPage clickOnExpositoryImageIcon() throws Exception {
		click(Toolbar_Icon_ExpositoryImages);
		Thread.sleep(2000);
		return this;
	}
	
	public CourseEditorPage enterLegendText(String legendText) throws Exception {
		switchToFrame(ExpositoryImage_iframe_legendTextEditor);
		sendKeysByJsExecutor(ExpositoryImage_TextBox_LegendText, legendText);
		//sendKeys(ExpositoryImage_TextBox_LegendText, legendText);
		switchToDefaultContent();
		return this;
	}
	
	private void selectExpositoryImages(String imageToSelect) throws Exception {
		wait.until(ExpectedConditions.invisibilityOf(ImageSearch_spinner));
		sendKeys(ImageSearch_PageRepository_TextBox_SearchImage, imageToSelect);
		sendKeys(ImageSearch_PageRepository_TextBox_SearchImage, Keys.RETURN);
		wait.until(ExpectedConditions.textToBePresentInElement(ImageSearch_Text_NumberOfRecords, "1 Record"));
		click(ImageSearch_PageRepository_SearchedImageRadioButton);
		click(ImageSearch_PageRepository_Button_AddSelected);
	}
	
	public CourseEditorPage select1stExpositoryImage(String imageToSelect) throws Exception {
		click(ExpositoryImage_Button_SelectExpositoryImage);
		//enableHiddenInputTags();
		//sendKeys(ImageSearch_PageRepository_Input_UploadFile, System.getProperty("user.dir")+"/src/main/resources/"+imageToUpload);
		//wait.until(ExpectedConditions.textToBePresentInElement(ImageSearch_progressText, "Uploading 100%"));
		selectExpositoryImages(imageToSelect);
		return this;
	}
	
	public CourseEditorPage select2ndExpositoryImage(String imageToSelect) throws Exception {
		click(ExpositoryImage_Button_SelectImage);
		selectExpositoryImages(imageToSelect);
		return this;
	}
	
	public CourseEditorPage removeExpositoryImage1() throws Exception {
		click(ExpositoryImage_Button_RemoveExpositoryImage);
		wait.until(ExpectedConditions.textToBePresentInElement(ExpositoryImage_Text_NoImage1, "No expository image selected"));
		return this;
	}
	
	public CourseEditorPage removeExpositoryImage2() throws Exception {
		click(ExpositoryImage_Button_RemoveImage);
		wait.until(ExpectedConditions.textToBePresentInElement(ExpositoryImage_Text_NoImage2, "No image selected"));
		return this;
	}
	
	public CourseEditorPage submitExporitoryImage() throws Exception {
		click(ExpositoryImage_Button_Continue);
		return this;
	}
	
	public CourseEditorPage closeExpositoryImagePopUp() throws Exception {
		click(ExpositoryImage_Button_Cancel);
		return this;
	}
	
	public String getAlertMessage() throws Exception {
		return getText(AlertPopUp_Message);
	}
	
	public CourseEditorPage AlertPopUp_clickOnOK() throws Exception {
		click(AlertPopUp_Button_OK);
		Thread.sleep(2000);
		return this;
	}
	
	public String getExpositoryImageTitleFromContentScreen() throws Exception {
		switchToFrame(iframe_editor);
		String  imageTitle = getAttribute(Image_ExpositoryImage, "src");
		switchToDefaultContent();
		return imageTitle.substring(imageTitle.lastIndexOf("/")+38, imageTitle.lastIndexOf("?"));
	}
	
	public String getExpositoryImageLegendTextFromContentScreen() throws Exception {
		switchToFrame(iframe_editor);
		String legendText = getText(Text_ExpositoryImageLegendText);
		switchToDefaultContent();
		return legendText;
	}
	
	public String getExpositoryImageTitleFromTestMode() throws Exception {
		switchToFrame(iframe_testMode);
		String  imageTitle = getAttribute(Image_ExpositoryImage, "src");
		switchToDefaultContent();
		return imageTitle.substring(imageTitle.lastIndexOf("/")+38);
	}
	
	public String getExpositoryImageLegendTextFromTestMode() throws Exception {
		switchToFrame(iframe_testMode);
		String legendText = getText(Text_ExpositoryImageLegendText);
		switchToDefaultContent();
		return legendText;
	}
	
	public String getExpositoryImageTitleFromPreviewMode() throws Exception {
		switchToFrame(PreviewMode_iframe_PreviewPanel);
		switchToFrame(PreviewMode_iframe_content);
		String  imageTitle = getAttribute(Image_ExpositoryImage, "src");
		switchToDefaultContent();
		return imageTitle.substring(imageTitle.lastIndexOf("/")+38);
	}
	
	public String getExpositoryImageLegendTextFromPreviewMode() throws Exception {
		switchToFrame(PreviewMode_iframe_PreviewPanel);
		switchToFrame(PreviewMode_iframe_content);
		String legendText = getText(Text_ExpositoryImageLegendText);
		switchToDefaultContent();
		return legendText;
	}
	
	public CourseEditorPage clickOnEditExpositoryImage() throws Exception {
		switchToFrame(iframe_editor);
		click(ExpositoryImageObject);
		click(Button_EditExpositoryImage);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage submitEditedExpositoryImage() throws Exception {
		click(ExpositoryImage_Button_Edit_Continue);
		return this;
	}
	
	public CourseEditorPage deleteExpositoryImage() throws Exception {
		switchToFrame(iframe_editor);
		click(ExpositoryImageObject);
		click(Button_DeleteExpositoryImage);
		switchToDefaultContent();
		return this;
	}
	
	public Boolean isExpositoryImagePresentOnContentScreen() throws Exception {
		switchToFrame(iframe_editor);
		Boolean isImagePresent 	= isDisplayed(Image_ExpositoryImage),
				isTextPresent 	= isDisplayed(Text_ExpositoryImageLegendText);
		switchToDefaultContent();
		return (isImagePresent && isTextPresent)?true:false;
	}
	
	public Boolean isExpositoryImagePresentInTestMode() throws Exception {
		switchToFrame(iframe_testMode);
		Boolean isImagePresent 	= isDisplayed(Image_ExpositoryImage),
				isTextPresent 	= isDisplayed(Text_ExpositoryImageLegendText);
		switchToDefaultContent();
		return (isImagePresent && isTextPresent)?true:false;
	}
	
	public Boolean isExpositoryImagePresentInPreviewMode() throws Exception {
		switchToFrame(PreviewMode_iframe_PreviewPanel);
		switchToFrame(PreviewMode_iframe_content);
		Boolean isImagePresent 	= isDisplayed(Image_ExpositoryImage),
				isTextPresent 	= isDisplayed(Text_ExpositoryImageLegendText);
		switchToDefaultContent();
		return (isImagePresent && isTextPresent)?true:false;
	}
	
	
	public CourseEditorPage clickOnEditWidget() throws Exception {
		switchToFrame(iframe_editor);
		if(!isDisplayed(Widget_Button_Edit)){
			click(Widget_Body);
		}
		click(Widget_Button_Edit);
		Thread.sleep(7000);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage clickOnDeleteWidget() throws Exception {
		switchToFrame(iframe_editor);
		if(!isDisplayed(Widget_Button_Delete)){
			click(Widget_Body);
		}
		click(Widget_Button_Delete);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage closeWidget() throws Exception {
		switchToFrame(iframe_Widget);
		click(Widget_Button_Cancel);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage saveHotSpotWidget() throws Exception {
		switchToFrame(iframe_Widget);
		click(Widget_Button_Save);
		switchToDefaultContent();
		wait.until(ExpectedConditions.invisibilityOf(HotSpotWidget_Body));
		return this;
	}
	
	public Boolean isHotspotSaveButtonEnabled() throws Exception {
		switchToFrame(iframe_Widget);
		boolean value =  isEnabled(Widget_Button_Save);
		switchToDefaultContent();
		return value;
	}
	
	public CourseEditorPage hotSpot_addImage() throws Exception {
		switchToFrame(iframe_Widget);
		click(HotSpotWidget_Button_AddImage);
		switchToDefaultContent();
		wait.until(ExpectedConditions.visibilityOf(ImageSearch_PageRepository_SearchedImage));
		mouseHover(ImageSearch_PageRepository_SearchedImage);
		click(ImageSearch_PageRepository_SearchedImageRadioButton);
		click(ImageSearch_PageRepository_Button_AddSelected);
		switchToFrame(iframe_Widget);
		wait.until(ExpectedConditions.visibilityOf(HotspotWidget_uploaded_image));
		//Thread.sleep(5000);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage hotSpot_addAltText(String altText) throws Exception {
		switchToFrame(iframe_Widget);
		scrollToElement(HotSpotWidget_TextBox_AltText);
		sendKeys(HotSpotWidget_TextBox_AltText, altText);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage hotSpot_clickOnCreateHotSpot() throws Exception {
		switchToFrame(iframe_Widget);
		click(HotspotWidget_Button_CreateHotSpot);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage addHotSpotsOnImage(int numberOfHotSpots) throws Exception {
		switchToFrame(iframe_Widget);

	    // Calculate the offset range (keep within image)
	    int maxOffset = 40; // smaller than 50 to be safer
	    Random random = new Random();

	    // Scroll to make sure image is visible
	    scrollToElement(HotspotWidget_uploaded_image);

	    // Wait for scrolling to complete
	    Thread.sleep(500);

	    for (int i = 0; i < numberOfHotSpots; i++) {
	        try {
	            // Generate coordinates in a safer central region of the image
	            int xOffset = random.nextInt(maxOffset * 2) - maxOffset;
	            int yOffset = random.nextInt(maxOffset * 2) - maxOffset;

	            // Use Actions directly for more control
	            ActionHandler.moveToElement(HotspotWidget_uploaded_image)
	                  .moveByOffset(xOffset, yOffset)
	                  .click()
	                  .perform();

	            // Small delay between clicks
	            Thread.sleep(300);
	        } catch (Exception e) {
	            // If there's an error, try clicking the center of the image
	            ActionHandler.moveToElement(HotspotWidget_uploaded_image).click().perform();
	        }
	    }
	    switchToDefaultContent();
	    return this;
	}
	
	public CourseEditorPage hotSpot_addTitleAndloopHighlight(String hotSpot, String title, String loopHighlight) throws Exception {
		switchToFrame(iframe_Widget);
		click(getWebElementByExpectedText(HotSpotWidget_List_HotSopts, hotSpot));
		switchToFrame(HotSpotWidget_iframe_TitleAndDescription);
		sendKeysByJsExecutor(HotSpotWidget_TextArea_TitleAndDescription, title);
		switchToParentFrame();
		sendKeys(HotSpotWidget_TextField_hotspot_loopHighlight, loopHighlight);
		switchToDefaultContent();
		return this;
	}
	
	public CourseEditorPage hotSpot_deleteActiveHotSpot() throws Exception {
		switchToFrame(iframe_Widget);
		//click(getWebElementByExpectedText(HotSpotWidget_List_HotSopts, hotSpot));
		click(HotSpotWidget_Button_DeleteHotSpot);
		switchToDefaultContent();
		return this;
	}
	
	public String getHotSpotImageInTestMode() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_HotSpot);
		wait.until(ExpectedConditions.attributeToBeNotEmpty(TestMode_HotSpot_Image, "src"));
		Thread.sleep(4000);
		String image = getAttribute(TestMode_HotSpot_Image, "src");
		switchToDefaultContent();
		return image.substring(image.lastIndexOf("_")+1, image.indexOf("?"));
	}
	
	public List<String> getHotSpotTitlesInTestMode() throws Exception {
		switchToFrame(iframe_testMode);
		switchToFrame(TestMode_iframe_HotSpot);
		List<String> hotSpotTitles = new ArrayList<String>();
		for (WebElement hotspot : TestMode_HotSpot_HotSpots) {
			clickByJsExecutor(hotspot);
			String title = getText(TestMode_HotSpot_Active_TitleAndDescription);
			hotSpotTitles.add(title);
		}
		switchToDefaultContent();
		return hotSpotTitles;
	}
	
	public String getHotSpotImageInPreviewMode() throws Exception {
		switchToFrame(PreviewMode_iframe_PreviewPanel);
		switchToFrame(PreviewMode_iframe_content);
		switchToFrame(TestMode_iframe_HotSpot);
		wait.until(ExpectedConditions.attributeToBeNotEmpty(TestMode_HotSpot_Image, "src"));
		Thread.sleep(4000);
		String image = getAttribute(TestMode_HotSpot_Image, "src");
		switchToDefaultContent();
		return image.substring(image.lastIndexOf("_")+1, image.indexOf("?"));
	}
	
	public List<String> getHotSpotTitlesInPreviewMode() throws Exception {
		switchToFrame(PreviewMode_iframe_PreviewPanel);
		switchToFrame(PreviewMode_iframe_content);
		switchToFrame(TestMode_iframe_HotSpot);
		List<String> hotSpotTitles = new ArrayList<String>();
		for (WebElement hotspot : TestMode_HotSpot_HotSpots) {
			clickByJsExecutor(hotspot);
			String title = getText(TestMode_HotSpot_Active_TitleAndDescription);
			hotSpotTitles.add(title);
		}
		switchToDefaultContent();
		return hotSpotTitles;
	}
	
	public Boolean isHotSpotWidgetPresentInTestMode() throws Exception {
		switchToFrame(iframe_testMode);
		Boolean isHotSpotWrapperPresent = isDisplayed(TestMode_HotSpotWrapper);
		switchToDefaultContent();
		return isHotSpotWrapperPresent;
	}
	
	public Boolean isHotSpotWidgetPresentInPreviewMode() throws Exception {
		switchToFrame(PreviewMode_iframe_PreviewPanel);
		switchToFrame(PreviewMode_iframe_content);
		Boolean isHotSpotWrapperPresent = isDisplayed(TestMode_HotSpotWrapper);
		switchToDefaultContent();
		return isHotSpotWrapperPresent;
	}
	
	/** Loader is not visible some times, so not using this */
	protected void waitForPatternsToBeDisplayed() {
		//wait.until(ExpectedConditions.visibilityOf(Loader_leftPane));
		//wait.until(ExpectedConditions.invisibilityOf(Loader_leftPane));
	}
		
	public CourseEditorVersionHistoryPage clickOnVersionHistoryButton() throws Exception {
		//explicitlyWait(Button_VersionHistory);
		click(Button_VersionHistory);
		//do I use new here?
		return new CourseEditorVersionHistoryPage();
	}
	
	public CourseEditorPage clickOnBody() throws Exception{
		scrollToElement(Button_Body);
		click(Button_Body);
		return this;
	}
		
	public CourseEditorPage createTask(String description, String assignTo, TaskStatus status, TaskPriority priority, boolean attachment , String referenceAttachment) throws Exception {
		Thread.sleep(2000);
		click(wait.until(ExpectedConditions.visibilityOf(TextBox_Tasks)));
		sendKeys(wait.until(ExpectedConditions.elementToBeClickable(TextArea_TasksComments)), description);
		if(attachment) {
			updateOpacityforInputTags();
			sendKeys(InputFile_TaskAttachment, System.getProperty("user.dir")+"/src/main/resources/"+referenceAttachment);
		}
		selectByText(SelectList_Users, assignTo);
		selectByText(SelectList_TaskStatus, status.toString());
		selectByText(SelectList_TaskPriority, priority.toString());
		Thread.sleep(1000);//need to replace this with Framework level wait for click in Driver Actions
		click(Button_TaskComment_Submit);
		Thread.sleep(2000);
		return this;
	}
	
	public CourseEditorPage openTaskPanel() throws Exception{
		click(Button_TaskPanel);
		return this;
	}
	
	public CourseEditorPage closeRecentTask() throws Exception{
		Thread.sleep(2000);
		click(Button_TaskEdit);
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.textToBePresentInElement(AlertPopUp_HeaderText, "Want to edit the task?"));
		click(Button_Confirm);
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.invisibilityOf(AlertPopUp_HeaderText));
		selectByText(SelectList_TaskStatus,"Fixed");
		click(Button_TaskComment_Submit);
		return this;
	}
	
	public CourseEditorPage deleteRecentTask() throws Exception{
		Thread.sleep(2000);
		click(Button_TaskDelete);
		wait.until(ExpectedConditions.textToBePresentInElement(AlertPopUp_HeaderText, "Want to delete the task?"));
		Thread.sleep(1000);
		click(Button_Confirm);
		wait.until(ExpectedConditions.textToBePresentInElement(AlertPopUp_Message, "Task deleted successfully"));
		Thread.sleep(1000);
		click(Button_Confirm);
		return this;
	}
}
