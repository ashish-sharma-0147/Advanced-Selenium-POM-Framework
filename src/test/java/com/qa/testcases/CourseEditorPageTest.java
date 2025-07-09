/**
 * 
 */
package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AdministrationUsersPage;
import com.qa.pages.CourseEditorPage;
import com.qa.pages.CourseEditorVersionHistoryPage;
import com.qa.pages.CreatePasswordPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.EditTOCPage;
import com.qa.pages.External_EmailSupport;
import com.qa.pages.LoginPage;
import com.qa.pages.ProjectPreviewPage;
import com.qa.pages.ProjectUserManagementPage;
import com.qa.testdata.BrightCoveVideoID;
import com.qa.testdata.OsmosisVideos;
import com.qa.testdata.ProjectType;
import com.qa.testdata.UserRoles;
import com.qa.util.CustomSoftAssert;
import com.qa.util.TestUtil;

/**
 * @author sharmaa11
 *
 */
public class CourseEditorPageTest extends TestBase {
	
	LoginPage loginPage;
	DashboardPage dashBoardPage;
	CourseEditorPage courseEditorPage;
	ProjectPreviewPage projectPreviewPage;
	EditTOCPage editTocPage;
	AdministrationUsersPage adminUserPage;
	External_EmailSupport externalSite;
	CreatePasswordPage createPasswordPage;
	ProjectUserManagementPage projectUserManagementPage;
	CourseEditorVersionHistoryPage courseEditorVersionHistoryPage;
	
	private final boolean isEnabled = true;
	private final String projectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
	private final String Yopmail_URL = "https://yopmail.com/en/";
	
	@BeforeMethod(alwaysRun=true)
	public void navigateToCourseEditorPage(Method method) throws Exception {
		loginPage = new LoginPage();
		courseEditorPage = loginPage.ValidLogin(configuration().username(), configuration().password()).
				clickOnCreateNewProject().createProject(projectName,
						ProjectType.OnlineCourse.toString(),
						ProjectType.AdaptiveLearning.toString(),
						TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph())
				.clickCreateAndConfigure().clickOnCreateTOC().openContentScreen();
	}
	
	@Test(priority = 1, enabled = false, groups = {"CourseEditorPageTest","brightCoveWidgetVideoPlayer"}, description="Configure BrightCove Widget and verify video play functionality")
	public void brightCoveWidgetVideoPlayer() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String videoTitle = TestUtil.faker.funnyName().name();
		String duration = "19";
		courseEditorPage.addBrightCoveWidget(BrightCoveVideoID.Overview_of_the_Integumentary_System.toString(),videoTitle).toogleTestMode().playBrightCoveVideoByWidget(duration);
		
		softAssert.assertEquals(courseEditorPage.getBrightCoveVideoElapsedTime_Widget(),Integer.parseInt(duration), "Video Elapsed Time Mismatch!!");
		softAssert.assertEquals(courseEditorPage.getBrightCoveVideoDurationTime_Widget(), 229, "BrightCove : Video Duration Time Mismatch!!");
		softAssert.assertEquals(courseEditorPage.getBrigtcoveVideoTitle_Widget(), videoTitle, "BrightCove : Video Title Mismatch!!");
		courseEditorPage.toogleTestMode().goBackToEditToc().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 2, enabled = false, groups = {"CourseEditorPageTest","accordionBrightCovePlayer"}, description="Configure BrightCove Video From Accordions and verify video play functionality")
	public void accordionBrightCovePlayer() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String videoTitle = TestUtil.faker.funnyName().name();
		String accordionLabel = TestUtil.faker.animal().name(); 
		String duration = "10";
		courseEditorPage.addBrightCoveVideoInAccordion(accordionLabel,BrightCoveVideoID.Overview_of_the_Integumentary_System.toString(),videoTitle)
			.toogleTestMode().playBrightCoveVideoByAccordion(duration);
		
		softAssert.assertEquals(courseEditorPage.getBrightCoveVideoElapsedTime_Accordion(),Integer.parseInt(duration), "Video Elapsed Time Mismatch!!");
		softAssert.assertEquals(courseEditorPage.getBrightCoveVideoDurationTime_Accordion(), 229, "BrightCove : Video Duration Time Mismatch!!");
		softAssert.assertEquals(courseEditorPage.getBrigtcoveVideoTitle_Accordion(), videoTitle, "BrightCove : Video Title Mismatch!!");
		courseEditorPage.toogleTestMode().goBackToEditToc().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 3, enabled = false, groups = {"CourseEditorPageTest","editorOsmosisVideoPlayer"}, description="Configure Osmosis Video From Editor Toolbar and verify video play functionality")
	public void editorOsmosisVideoPlayer() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String duration = "10";
		courseEditorPage.addOsmosisVideoFromToolbar(OsmosisVideos.Anatomy_of_the_cranial_base.toString()).toogleTestMode().playOsmosisVideo(duration);
		
		softAssert.assertEquals(courseEditorPage.getOsmosisElapsedTime_Toolbar(),Integer.parseInt(duration), "Osmosis : Video Elapsed Time Mismatch!!");
		softAssert.assertEquals(courseEditorPage.getOsmosisVideoDurationTime_Toolbar(), 602, "Osmosis : Video Duration Time Mismatch!!");
		courseEditorPage.toogleTestMode().goBackToEditToc().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 4, enabled = false, groups = {"CourseEditorPageTest","accordionOsmosisVideoPlayer"}, description="Configure Osmosis Video In Accordion Toolbar and verify video play functionality")
	public void accordionOsmosisVideoPlayer() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String accordionLabel = TestUtil.faker.animal().name(); 
		String duration = "10";
		courseEditorPage.addOsmosisVideoInAccordion(accordionLabel,OsmosisVideos.Anatomy_of_the_cranial_base.toString()).toogleTestMode().playOsmosisVideo_Accordion(duration);
		
		softAssert.assertEquals(courseEditorPage.getOsmosisVideoElapsedTime_Accordion(),Integer.parseInt(duration), "Osmosis : Video Elapsed Time Mismatch!!");
		softAssert.assertEquals(courseEditorPage.getOsmosisVideoDurationTime_Accordion(), 602, "Osmosis : Video Duration Time Mismatch!!");
		courseEditorPage.toogleTestMode().goBackToEditToc().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 5, enabled = isEnabled, groups = {"CourseEditorPageTest","updateNodeTitle"}, description="Update Node Title from Course Editor")
	public void updateNodeTitle() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String nodeTitle = TestUtil.faker.animal().name()+TestUtil.faker.funnyName().name();
		String firstName = "Test_"+TestUtil.faker.name().firstName();
		String lastName = TestUtil.faker.name().lastName();
		String userName = TestUtil.faker.name().username();
		String email = TestUtil.getRandomEmail("Yopmail");
		String password = TestUtil.getRandomAlphanumericPassword();
		courseEditorPage.clickOnNodeTitle().updateNodeTitle(nodeTitle);
		
		softAssert.assertEquals(courseEditorPage.getActiveNodeTitleFromMiniTOC(), nodeTitle, "Updated Node Title Mismatch in MiniTOC!! ");
		softAssert.assertEquals(courseEditorPage.toogleTestMode().switchToPreviewMode().getActiveNode(), nodeTitle, "Updated Node Title Mismatch in Project Preview Mode!! ");
		softAssert.assertEquals(courseEditorPage.closePreviewMode().getNodeTitle(), nodeTitle, "Updated Node Title Mismatch in Course Editor/Test Mode!! ");
		
		editTocPage = courseEditorPage.goBackToEditToc();
		softAssert.assertTrue(editTocPage.confirmPopUp().getAllNodes().contains(nodeTitle), "Updated Node Title Mismatch in TOC!! ");
		
		/** Reviewer Role Test**/
		adminUserPage = editTocPage.clickOnGoToAdministration().openUsersTab();
		adminUserPage.clickOnCreateAccount().createUser("Authoring", firstName, lastName, userName, email).closePopUp();
		externalSite = adminUserPage.Logout().launchExternalEmailSupport(Yopmail_URL);
		createPasswordPage = externalSite.Yopmail_openInbox(email).Yopmail_openEmailHavingSubject("FROST - Registration").Yopmail_clickOnCreateYourPassword();
		editTocPage = createPasswordPage.setNewPassword(password, password).clickOnBackToLogin().ValidLogin(configuration().username(), configuration().password()).openProject(projectName);
		editTocPage.clickOnAssignModule().assignModule(userName, UserRoles.Reviewer.toString()).confirmPopUp()
		.closeAssignUserPopUp().Logout().ValidLogin(userName, TestUtil.encodeToBase64(password)).openProject(projectName);
		
		courseEditorPage = editTocPage.expandTOCnodes().openContentScreen();
		softAssert.assertFalse(courseEditorPage.clickOnNodeTitle().isTitleEditable(), "Test Failed!! Reviewer Should be unable to update node!!");
		/*************************/
		
		dashBoardPage = courseEditorPage.goBackToEditToc().confirmPopUp().clickOnMyProjects();
		editTocPage = dashBoardPage.Logout().ValidLogin(configuration().username(), configuration().password()).clickOnMyProjects().openProject(projectName);
		String alertMessageRemoveUser = editTocPage.clickOnAssignModule().unAssignModule(userName).getAlertMessage();
		
		softAssert.assertEquals(alertMessageRemoveUser, "User has been deleted");
		
		/** Author Role Test **/
		editTocPage.confirmPopUp().closeAssignUserPopUp().clickOnAssignModule().assignModule(userName, UserRoles.Author.toString()).confirmPopUp()
		.closeAssignUserPopUp().Logout().ValidLogin(userName, TestUtil.encodeToBase64(password)).openProject(projectName);
		
		courseEditorPage = editTocPage.expandTOCnodes().openContentScreen();
		courseEditorPage.clickOnNodeTitle().updateNodeTitle(nodeTitle);
		
		softAssert.assertEquals(courseEditorPage.getActiveNodeTitleFromMiniTOC(), nodeTitle, "Author Updated Node Title Mismatch in MiniTOC!! ");
		softAssert.assertEquals(courseEditorPage.toogleTestMode().switchToPreviewMode().getActiveNode(), nodeTitle, "Author Updated Node Title Mismatch in Project Preview Mode!! ");
		softAssert.assertEquals(courseEditorPage.closePreviewMode().getNodeTitle(), nodeTitle, "Author Updated Node Title Mismatch in Course Editor/Test Mode!! ");
		
		editTocPage = courseEditorPage.goBackToEditToc();
		softAssert.assertTrue(editTocPage.confirmPopUp().expandTOCnodes().getAllNodes().contains(nodeTitle), "Author Updated Node Title Mismatch in TOC!! ");
		/**********************/
		
		dashBoardPage = editTocPage.Logout().ValidLogin(configuration().username(), configuration().password());
		dashBoardPage.clickOnMyProjects().deleteProject(projectName);
		dashBoardPage.confirmPopUp();
		dashBoardPage.clickOnGoToAdministration().openUsersTab().searchUserBy("Email", email).deactivateUser();
		softAssert.assertAll();
	}
	
	@Test(priority = 6, enabled = isEnabled, groups = {"CourseEditorPageTest","addEditDeleteExpositoryImage"}, description="Add/Edit/Delete expository image in a content screen. Negative and Positive flow")
	public void addEditDeleteExpositoryImage() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String imageTitle = "nonsolus-digital_elsevier.png";
		String legendText = (TestUtil.faker.animal().name()+TestUtil.faker.funnyName().name()).trim();
		
		courseEditorPage.goBackToEditToc().confirmPopUp().clickOnManageAssets().uploadAsset(imageTitle).clickOnBackToToc().confirmPopUp().openContentScreen();
		
		String missingLegendTextErrorMessage = courseEditorPage.clickOnExpositoryImageIcon().submitExporitoryImage().getAlertMessage();
		softAssert.assertEquals(missingLegendTextErrorMessage, "Please enter the legend text.","Expository Image Error Message Mismatch!!");
		
		String missingExpositoryImageErrorMessage = courseEditorPage.AlertPopUp_clickOnOK().enterLegendText(legendText).submitExporitoryImage().getAlertMessage();
		softAssert.assertEquals(missingExpositoryImageErrorMessage, "Please select the expository image.","Expository Image Error Message Mismatch!!");
		
		String missingImage2ErrorMessage = courseEditorPage.AlertPopUp_clickOnOK().select1stExpositoryImage(imageTitle).submitExporitoryImage().getAlertMessage();
		softAssert.assertEquals(missingImage2ErrorMessage, "Please select the image.","Expository Image Error Message Mismatch!!");
		
		courseEditorPage.AlertPopUp_clickOnOK().select2ndExpositoryImage(imageTitle);
		missingExpositoryImageErrorMessage = courseEditorPage.removeExpositoryImage1().submitExporitoryImage().getAlertMessage(); 
		softAssert.assertEquals(missingExpositoryImageErrorMessage, "Please select the expository image.","Expository Image Error Message Mismatch!!");
		
		String actualTitle = courseEditorPage.AlertPopUp_clickOnOK().select1stExpositoryImage(imageTitle).submitExporitoryImage().getExpositoryImageTitleFromContentScreen();
		softAssert.assertEquals(actualTitle,imageTitle.replace("_", "-"),"Added Expository Image title mismatch!!"); //Replaced underscores with hyphens this happens in system while uploading an image;
		
		String actualLegendText = courseEditorPage.getExpositoryImageLegendTextFromContentScreen();
		softAssert.assertEquals(actualLegendText, legendText,"Added Expository Image Legend Text Mismatch!!");
		
		String newLegendText = (TestUtil.faker.funnyName().name()+TestUtil.faker.animal().name()).trim();
		actualLegendText = courseEditorPage.clickOnEditExpositoryImage().enterLegendText(newLegendText).submitEditedExpositoryImage().getExpositoryImageLegendTextFromContentScreen();
		softAssert.assertEquals(actualLegendText, newLegendText,"Expository Image Legend Text Mismatch After Edit!!");
		
		String testMode_legendText = courseEditorPage.saveContent().toogleTestMode().getExpositoryImageLegendTextFromTestMode();
		softAssert.assertEquals(testMode_legendText, newLegendText,"Expository Image Legend Text Mismatch In Test Mode!!");
		
		String testMode_ImageTitle = courseEditorPage.getExpositoryImageTitleFromTestMode();
		softAssert.assertEquals(testMode_ImageTitle,imageTitle.replace("_", "-"),"Expository Image title Mismatch in Test Mode!!");
		
		String previewMode_legendText = courseEditorPage.switchToPreviewMode().getExpositoryImageLegendTextFromPreviewMode();
		softAssert.assertEquals(previewMode_legendText, newLegendText,"Expository Image Legend Text Mismatch In Test Mode!!");
		
		String previewMode_ImageTitle = courseEditorPage.getExpositoryImageTitleFromPreviewMode();
		softAssert.assertEquals(previewMode_ImageTitle,imageTitle.replace("_", "-"),"Expository Image title Mismatch in Test Mode!!");
		
		boolean isPresent = courseEditorPage.closePreviewMode().toogleTestMode().deleteExpositoryImage().isExpositoryImagePresentOnContentScreen();
		softAssert.assertFalse(isPresent, "Expository Image still visible on content screen after deletion!!");
		
		isPresent = courseEditorPage.saveContent().toogleTestMode().isExpositoryImagePresentInTestMode();
		softAssert.assertFalse(isPresent, "Expository Image still visible in Test Mode after deletion!!");
		
		isPresent = courseEditorPage.switchToPreviewMode().isExpositoryImagePresentInPreviewMode();
		softAssert.assertFalse(isPresent, "Expository Image still visible in Preview Mode after deletion!!");
		
		courseEditorPage.closePreviewMode().toogleTestMode().goBackToEditToc().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 7, enabled = isEnabled, groups = {"CourseEditorPageTest","addEditPreviewHotSpot"}, description="Add/Edit/Delete hotspot widget. Negative and Positive flow")
	public void addEditPreviewHotSpot() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String imageTitle = "pexels-magda-ehlers-pexels-1319515.jpg";
		String imageAltText = (TestUtil.faker.animal().name()+TestUtil.faker.funnyName().name()).trim();
		String hotSpot1Title = (TestUtil.faker.funnyName().name()+TestUtil.faker.company().name()).trim();;
		String hotSpot1LoopHighlight = TestUtil.faker.funnyName().name();
		String hotSpot2Title = (TestUtil.faker.funnyName().name()+TestUtil.faker.company().name()).trim();;
		String hotSpot2LoopHighlight = TestUtil.faker.funnyName().name();
		String hotSpot3Title = (TestUtil.faker.funnyName().name()+TestUtil.faker.company().name()).trim();;
		String hotSpot3LoopHighlight = TestUtil.faker.funnyName().name();
		
		editTocPage = courseEditorPage.goBackToEditToc();
		editTocPage.confirmPopUp().clickOnManageAssets().uploadAsset(imageTitle).clickOnBackToToc().openContentScreen();
		
		softAssert.assertFalse(courseEditorPage.AddPatternToEditor("HOTSPOT").clickOnEditWidget().isHotspotSaveButtonEnabled(),"Hotspot Save Button is enabled without adding hotspot image!!");
		
		courseEditorPage.hotSpot_addImage().hotSpot_clickOnCreateHotSpot().
		addHotSpotsOnImage(1).hotSpot_addAltText(imageAltText).hotSpot_addTitleAndloopHighlight("1", hotSpot1Title, hotSpot1LoopHighlight);
		softAssert.assertFalse(courseEditorPage.isHotspotSaveButtonEnabled(),"Hotspot Save Button is enabled after adding only 1 hotspot!!");
		//Thread.sleep(5000);
		courseEditorPage.addHotSpotsOnImage(2);
		//Thread.sleep(5000);
		courseEditorPage.hotSpot_addTitleAndloopHighlight("2", hotSpot2Title, hotSpot2LoopHighlight).
		hotSpot_addTitleAndloopHighlight("3", hotSpot3Title, hotSpot3LoopHighlight).saveHotSpotWidget().saveContent();
		softAssert.assertEquals(courseEditorPage.toogleTestMode().getHotSpotImageInTestMode(), imageTitle.replace("_", "-"),"Hotspot Image Mismatch in Test Mode!!");
		
		List<String> List_test_hotSpotTitle = courseEditorPage.getHotSpotTitlesInTestMode();
		softAssert.assertEquals(List_test_hotSpotTitle.get(0), hotSpot1Title, "Hotspot 1 Title Mismatch in Test Mode!!");
		softAssert.assertEquals(List_test_hotSpotTitle.get(1), hotSpot2Title, "Hotspot 2 Title Mismatch in Test Mode!!");
		softAssert.assertEquals(List_test_hotSpotTitle.get(2), hotSpot3Title, "Hotspot 3 Title Mismatch in Test Mode!!");
		
		softAssert.assertEquals(courseEditorPage.switchToPreviewMode().getHotSpotImageInPreviewMode(), imageTitle.replace("_", "-"),"Hotspot Image Mismatch in Preview Mode!!");
		
		List<String> List_preview_HotSpotTitle = courseEditorPage.getHotSpotTitlesInPreviewMode();
		softAssert.assertEquals(List_preview_HotSpotTitle.get(0), hotSpot1Title, "Hotspot 1 Title Mismatch in Preview Mode!!");
		softAssert.assertEquals(List_preview_HotSpotTitle.get(1), hotSpot2Title, "Hotspot 2 Title Mismatch in Preview Mode!!");
		softAssert.assertEquals(List_preview_HotSpotTitle.get(2), hotSpot3Title, "Hotspot 3 Title Mismatch in Preview Mode!!");
		
		String newhotSpot1Title = (TestUtil.faker.funnyName().name()+TestUtil.faker.company().name()).trim();;
		String newhotSpot1LoopHighlight1 = TestUtil.faker.funnyName().name();
		String newhotSpot2Title = (TestUtil.faker.funnyName().name()+TestUtil.faker.company().name()).trim();;
		String newhotSpot2LoopHighlight = TestUtil.faker.funnyName().name();
		String newhotSpot3Title = (TestUtil.faker.funnyName().name()+TestUtil.faker.company().name()).trim();;
		String newhotSpot3LoopHighlight = TestUtil.faker.funnyName().name();
		
		courseEditorPage.closePreviewMode().toogleTestMode().clickOnEditWidget().hotSpot_addTitleAndloopHighlight("1", newhotSpot1Title, newhotSpot1LoopHighlight1).
		hotSpot_addTitleAndloopHighlight("2", newhotSpot2Title, newhotSpot2LoopHighlight).
		hotSpot_addTitleAndloopHighlight("3", newhotSpot3Title, newhotSpot3LoopHighlight).saveHotSpotWidget().saveContent().toogleTestMode();
		
		List<String> List_test_updatedhotSpotTitle = courseEditorPage.getHotSpotTitlesInTestMode();
		softAssert.assertEquals(List_test_updatedhotSpotTitle.get(0), newhotSpot1Title, "Hotspot 1 Updated Title Mismatch in Test Mode!!");
		softAssert.assertEquals(List_test_updatedhotSpotTitle.get(1), newhotSpot2Title, "Hotspot 2 Updated Title Mismatch in Test Mode!!");
		softAssert.assertEquals(List_test_updatedhotSpotTitle.get(2), newhotSpot3Title, "Hotspot 3 Updated Title Mismatch in Test Mode!!");
		
		softAssert.assertEquals(courseEditorPage.switchToPreviewMode().getHotSpotImageInPreviewMode(), imageTitle.replace("_", "-"),"Hotspot Image Mismatch in Preview Mode!!");
		
		List<String> List_preview_updatedHotSpotTitle = courseEditorPage.getHotSpotTitlesInPreviewMode();
		softAssert.assertEquals(List_preview_updatedHotSpotTitle.get(0), newhotSpot1Title, "Hotspot 1 Updated Title Mismatch in Preview Mode!!");
		softAssert.assertEquals(List_preview_updatedHotSpotTitle.get(1), newhotSpot2Title, "Hotspot 2 Updated Title Mismatch in Preview Mode!!");
		softAssert.assertEquals(List_preview_updatedHotSpotTitle.get(2), newhotSpot3Title, "Hotspot 3 Updated Title Mismatch in Preview Mode!!");
		
		courseEditorPage.closePreviewMode().toogleTestMode().clickOnEditWidget().hotSpot_deleteActiveHotSpot();
		softAssert.assertFalse(courseEditorPage.isHotspotSaveButtonEnabled(),"Hotspot Save Button is enabled after deleting hotspot!!");
		
		courseEditorPage.closeWidget().clickOnDeleteWidget().saveContent().toogleTestMode();
		softAssert.assertFalse(courseEditorPage.isHotSpotWidgetPresentInTestMode(),"Hotspot Widget still visible in Test Mode after deletion!!");
		
		softAssert.assertFalse(courseEditorPage.switchToPreviewMode().isHotSpotWidgetPresentInPreviewMode(),"Hotspot Widget still visible in Preview Mode after deletion!!");
		
		courseEditorPage.closePreviewMode().toogleTestMode().goBackToEditToc().confirmPopUp().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	 @Test(priority = 8, enabled = isEnabled, groups = {"verifyContentScreenVersioning","CourseEditorPageTest","Version"}, description="Verifying Content screen versioning test cases")
	    public void verifyContentScreenVersioning() throws Exception {
			CustomSoftAssert softAssert = new CustomSoftAssert();
			
			//Edit the content
			String newText = TestUtil.faker.lorem().paragraph();
			//courseEditorPage.addTextToEditor(newText);
	    	courseEditorVersionHistoryPage = courseEditorPage.addTextToEditor(newText).saveContent().clickOnVersionHistoryButton();
	    	
	    	//verify that it is correctly displayed
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getCurrentContent(), newText, "Current content mismatched in version history!!");
	    	softAssert.assertNotEquals(courseEditorVersionHistoryPage.getPreviousContent(), newText, "Previous content is same as current content!!");
	    	softAssert.assertFalse(courseEditorVersionHistoryPage.isNotesIconDisplayed(),"Notes icon should not be displayed for Version 2!!");
	    	
	    	String currentDate = courseEditorVersionHistoryPage.getCurrentDate();
	    	String[] versionHistory = courseEditorVersionHistoryPage.parseVersionText();
	    	
	    	//want to add a hard assert that the length of versionHistory is equal to 6 for safety reason
	    	
	    	softAssert.assertEquals(versionHistory[0], "Version 2", "Version number mismatched in version history!!");
	    	softAssert.assertEquals(versionHistory[1], currentDate, "Version 2 Date mismatched in version history!!");
	    	softAssert.assertEquals(versionHistory[2], configuration().username(), "User mismatched in version history!!");
	    	softAssert.assertEquals(versionHistory[3], "Version 1", "Version number mismatched in version history!!");
	    	softAssert.assertEquals(versionHistory[4], currentDate, "Version 1 Date mismatched in version history!!");
	    	//softAssert.assertEquals(versionHistory[5], "frostloradmin");
	    	
	    	//verify that the title and the most recent change have the same version and date
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getTitleVersion(), versionHistory[0] ,"Version page title not matching with the most recent versions card!!");
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getTitleDate(), versionHistory[1] ,"Version page title date not matching with the most recent versions card!!");

	    	courseEditorPage = courseEditorVersionHistoryPage.closeVersionHistoryPage();
	    	
	    	// verify autosave works
	    	String updatedText = TestUtil.faker.animal().name();
	    	courseEditorPage.addTextToEditorAndWaitForAutoSave(updatedText);
	    	courseEditorVersionHistoryPage = courseEditorPage.clickOnVersionHistoryButton();
	    	versionHistory = courseEditorVersionHistoryPage.parseVersionText();
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getCurrentContent(), updatedText, "Current content mismatched in version history after autosave!!");
	    	softAssert.assertEquals(versionHistory[0], "Version 3", "Version number mismatched in version history after autosave!!");
	    	
	    	//verify that the revert change works
	    	courseEditorPage= courseEditorVersionHistoryPage.clickOnRestoreVersionButton();
	    	softAssert.assertEquals(courseEditorPage.getEditorText(), newText, "Reverted content mismatched in course editor!!");
	    	
	    	//Verify Version Notes
	    	String contentText = TestUtil.faker.lorem().paragraph();
	    	String version4Note = TestUtil.faker.artist().name();
	    	
	    	courseEditorPage.addTextToEditor(contentText).saveContentWithVersionNotes(version4Note);
	    	courseEditorVersionHistoryPage = courseEditorPage.clickOnVersionHistoryButton();
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getVersionNotes(4).get(0), version4Note, "Version 4 Note does not match!!");
	    	
	    	courseEditorPage = courseEditorVersionHistoryPage.closeVersionHistoryPage();
	    	
	    	ArrayList<String> version5Notes = TestUtil.getArrayListWithRandomString(3);
	    	courseEditorPage.addTextToEditor(TestUtil.faker.lorem().paragraph()).saveContentWithVersionNotes(version5Notes.get(0));
	    	courseEditorPage.addTextToEditor(TestUtil.faker.lorem().paragraph()).saveContentWithVersionNotes(version5Notes.get(1));
	    	courseEditorPage.addTextToEditor(TestUtil.faker.lorem().paragraph()).saveContentWithVersionNotes(version5Notes.get(2));
	    	courseEditorVersionHistoryPage = courseEditorPage.clickOnVersionHistoryButton();
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getVersionNotes(5), version5Notes, "Version 5 notes list mismatch!!");
	    	
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getVersionNotes(4).size(), 1, "Version 4 should only have 1 note, but found more!!");
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getVersionNotes(3).size(), 0, "Version 3 shouldn't have any notes, but found some!!");
	    	softAssert.assertEquals(courseEditorVersionHistoryPage.getVersionNotes(2).size(), 0, "Version 2 shouldn't have any notes, but found some!!");
	    	
	    	courseEditorPage = courseEditorVersionHistoryPage.closeVersionHistoryPage();
			courseEditorPage.goBackToEditToc().clickOnMyProjects().deleteProject(projectName);
	    	softAssert.assertAll();
	    }
		
}
