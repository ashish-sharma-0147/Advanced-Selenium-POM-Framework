package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.CourseEditorPage;
import com.qa.pages.GlossaryPage;
import com.qa.pages.LoginPage;
import com.qa.testdata.ProjectType;
import com.qa.util.CustomSoftAssert;
import com.qa.util.TestUtil;

/**
 * @author sharmaa11
 *
 */
public class GlossaryPageTest extends TestBase{
	
	LoginPage loginPage;
	GlossaryPage glossaryPage;
	CourseEditorPage courseEditorPage;
	
	private final boolean isEnabled = true;
	private String projectName;
	
	@BeforeMethod(alwaysRun=true)
	public void navigateToGlossaryPage(Method method) throws Exception {
		loginPage = new LoginPage();
		projectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		glossaryPage = loginPage.ValidLogin(configuration().username(), configuration().password()).
				clickOnCreateNewProject().createProject(projectName,
						ProjectType.OnlineCourse.toString(),
						ProjectType.AdaptiveLearning.toString(),
						TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph())
				.clickCreateAndConfigure().clickOnCreateTOC().clickOnMyProjects().searchProject(projectName).defineGlossary();
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"sanity","regression","glossaryManagement","GlossaryPageTest"}, description="Creating/Update/View/Delete Glossary")
	public void glossaryManagement() throws Exception {
		/********** Verifying Glossary Creation and listing **********/
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String term = TestUtil.faker.animal().name().trim()+TestUtil.faker.relationships().any();
		String description = TestUtil.faker.lorem().paragraph();
		String definitionRecordId = (TestUtil.faker.number().digits(10)).replaceAll("^0+","");
		String definitionTextRecord = TestUtil.faker.lorem().word();
		String source = TestUtil.faker.internet().ipV4Address();
		String updateText = TestUtil.faker.lorem().word();
		
		List<LinkedHashMap<String, String>> listedGlossary = glossaryPage.createNewGlossary(term, description, definitionRecordId, definitionTextRecord, source).clickOnConfirm()
				.searchGlossary(term).getListedGlossaries();
		softAssert.assertEquals(listedGlossary.get(0).get("Term"), term, "Glossary Term mismatch!!");
		softAssert.assertEquals(listedGlossary.get(0).get("Description"), description, "Glossary Description mismatch!!");
		softAssert.assertEquals(listedGlossary.get(0).get("Definition Record Id"), definitionRecordId, "Glossary Record ID mismatch!!");
		
		/********** Verifying Glossary Update **********/
		List<LinkedHashMap<String, String>> updatedGlossary  =glossaryPage.editGlossary(term+updateText, description+updateText, definitionTextRecord+updateText, source+updateText)
				.clickOnConfirm().getListedGlossaries();
		softAssert.assertEquals(updatedGlossary.get(0).get("Term"), term+updateText, "Glossary Term mismatch!!");
		softAssert.assertEquals(updatedGlossary.get(0).get("Description"), description+updateText, "Glossary Description mismatch!!");
		softAssert.assertEquals(updatedGlossary.get(0).get("Definition Record Id"), definitionRecordId, "Glossary Record ID mismatch!!");
		
		/********** Verifying Glossary Delete **********/
		String message = glossaryPage.deleteGlossary();
		softAssert.assertEquals(message, "Glossary has been deleted", "Glossary Delete Success Message mismatch!!");
		glossaryPage.confirmPopUp();
		glossaryPage.clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"sanity","regression","linkTextWithGlossaryTerm","GlossaryPageTest"}, 
			description="Link Glossaraies with exact Text and verify that no new linked Term is created.")
	public void linkTextWithGlossaryTerm() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String term = TestUtil.faker.animal().name().trim()+TestUtil.faker.relationships().any();
		String description = TestUtil.faker.lorem().paragraph();
		String definitionRecordId = TestUtil.faker.number().digits(10);
		String definitionTextRecord = TestUtil.faker.lorem().word();
		String source = TestUtil.faker.internet().ipV4Address();
		
		/********** Verifying Glossary Linking **********/
		courseEditorPage = glossaryPage.createNewGlossary(term, description, definitionRecordId, definitionTextRecord, source).clickOnConfirm().clickOnBackToToc().openContentScreen();
		HashMap<String,String> tooltipText = courseEditorPage.addTextToEditor(term).saveContent().selectAllTextFromEditor().clickOnAddLink().openGlossary()
		.selectListedGlossary().clickOnConfirm_AddLink().saveContent().toogleTestMode().getLinkedGlossaryToolTipDetails_TestMode();
		softAssert.assertEquals(tooltipText.get("Term"), term, "Term mismatch in Linked Text (Test Mode) !!");
		softAssert.assertEquals(tooltipText.get("Description"), description, "Description mismatch in Linked Text (Test Mode) !!");
		
		courseEditorPage.goBackToEditToc().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 3, enabled = isEnabled, groups = {"sanity","regression","createViewDeleteLinkedTerm","GlossaryPageTest"}, 
			description="Link Glossaraies with Text, verify new linked term creation and deletion")
	public void createViewDeleteLinkedTerm() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String term = TestUtil.faker.animal().name().trim()+TestUtil.faker.relationships().any();
		String description = TestUtil.faker.lorem().paragraph();
		String definitionRecordId = TestUtil.faker.number().digits(10);
		String definitionTextRecord = TestUtil.faker.lorem().word();
		String source = TestUtil.faker.internet().ipV4Address();
		String linkedTerm = TestUtil.faker.lorem().word();
		
		/********** Verifying Linked Term Creation **********/
		courseEditorPage = glossaryPage.createNewGlossary(term, description, definitionRecordId, definitionTextRecord, source).clickOnConfirm().clickOnBackToToc().openContentScreen();
		HashMap<String,String> tooltipText = courseEditorPage.addTextToEditor(linkedTerm).saveContent().selectAllTextFromEditor().clickOnAddLink().openGlossary().searchGlossary(term)
		.selectListedGlossary().clickOnConfirm_AddLink().saveContent().toogleTestMode().getLinkedGlossaryToolTipDetails_TestMode();
		
		softAssert.assertEquals(tooltipText.get("Term"), term, "Term mismatch in Linked Text (Test Mode) !!");
		softAssert.assertEquals(tooltipText.get("Description"), description, "Description mismatch in Linked Text (Test Mode) !!");
		
		/********** Verifying Linked Term Listing **********/
		glossaryPage = courseEditorPage.goBackToEditToc().confirmPopUp().clickOnMyProjects().searchProject(projectName).defineGlossary();
		HashMap<Object,Object> viewGlossary = glossaryPage.searchGlossary(term).viewPopUp_getGlossaryDetails();
		System.out.println(viewGlossary.keySet());
		softAssert.assertEquals(viewGlossary.get("Linked Terms :").toString(), linkedTerm, "Linked Term Mismatch/Missing in View Glossary!! ");
		
		/********** Verifying Linked Term Deletion **********/
		HashMap<Object,Object> updatedGlossary = glossaryPage.cancelPopUp().removeLinkedTerm(linkedTerm).clickOnConfirm().clearGlossarySearch().searchGlossary(term).viewPopUp_getGlossaryDetails();
		softAssert.assertEquals(updatedGlossary.get("Linked Terms :").toString(), "", "Linked Term reomved, but is still visible!! ");
		
		glossaryPage.cancelPopUp().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
}
