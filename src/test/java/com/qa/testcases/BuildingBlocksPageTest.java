package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.BuildingBlocksPage;
import com.qa.pages.CourseEditorPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.EditTOCPage;
import com.qa.pages.LoginPage;
import com.qa.pages.PatternManagementPage;
import com.qa.testdata.ProjectType;
import com.qa.util.CustomSoftAssert;
import com.qa.util.TestUtil;

public class BuildingBlocksPageTest extends TestBase{
	
	LoginPage loginPage;
	BuildingBlocksPage buildingBlocksPage;
	DashboardPage dashBoardPage;
	EditTOCPage editTocPage;
	PatternManagementPage patternManagementPage;
	CourseEditorPage courseEditorPage;
	
	private final boolean isEnabled = true;
	private final String OnlinePatternName = TestUtil.faker.lorem().characters(10,20).trim().toUpperCase();
	private final String OnlinePatternContent = TestUtil.faker.lorem().sentence().trim();
	
	@BeforeMethod(alwaysRun=true)
	public void navigateToBuildingBlocksPage(Method method) throws Exception {
		loginPage = new LoginPage();
		buildingBlocksPage = loginPage.ValidLogin(configuration().username(), configuration().password()).clickOnBuildingBlocks();
				
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"sanity","BuildingBlocks"}, description="Creating Global pattern for EDUPUB projects")
	public void createGlobalEDUPUBpattern() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String PatternName = TestUtil.faker.lorem().characters(10,20).trim();
		String PatternContent = TestUtil.faker.lorem().sentence().trim();
		List<String> Patterns = buildingBlocksPage.createPattern(PatternName, PatternContent, ProjectType.EDUPUB).searchPattern(PatternName).getListedPatterns();
		softAssert.assertEquals(Patterns.get(0), PatternName, "Pattern is Missing in Building Blocks");
		
		String ProjectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		editTocPage = buildingBlocksPage.clickOnMyProjects().clickOnCreateNewProject().createProject(ProjectName,
				ProjectType.EDUPUB.toString(),null, TestUtil.faker.code().isbn13(),
				TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateAndConfigure().clickOnCreateTOC();
		patternManagementPage = editTocPage.clickOnManagePattern();
		List<String> GlobalPatterns = patternManagementPage.searchPattern(PatternName).getListedPatterns();
		softAssert.assertEquals(GlobalPatterns.get(0).replace("GLOBAL","").trim(), PatternName, "Pattern is Missing in Project");
		
		dashBoardPage = patternManagementPage.clickOnMyProjects();
		dashBoardPage.deleteProject(ProjectName);
		dashBoardPage.confirmPopUp();
		dashBoardPage.clickOnBuildingBlocks();
		String Message = buildingBlocksPage.deletePattern(PatternName);
		softAssert.assertEquals(Message, "Pattern has been deleted");
		buildingBlocksPage.confirmPopUp();
		softAssert.assertAll();
		
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"sanity","BuildingBlocks"}, description="Creating Global pattern for Online Course projects")
	public void createGlobalOnlineCoursePattern() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		//String PatternName = (TestUtil.faker.lorem().word().trim()+TestUtil.faker.lorem().word().trim()).toUpperCase();
		//String PatternContent = TestUtil.faker.lorem().sentence().trim();
		List<String> Patterns = buildingBlocksPage.createPattern(OnlinePatternName, OnlinePatternContent, ProjectType.OnlineCourse).searchPattern(OnlinePatternName).getListedPatterns();
		softAssert.assertEquals(Patterns.get(0), OnlinePatternName, "Pattern is Missing in Building Blocks");
		
		String ProjectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		editTocPage = buildingBlocksPage.clickOnMyProjects().clickOnCreateNewProject().createProject(ProjectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.AdaptiveLearning.toString(),
				TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateAndConfigure().clickOnCreateTOC();
		patternManagementPage = editTocPage.clickOnManagePattern();
		List<String> GlobalPatterns = patternManagementPage.searchPattern(OnlinePatternName).getListedPatterns();
		softAssert.assertEquals(GlobalPatterns.get(0).replace("GLOBAL","").trim(), OnlinePatternName, "Pattern is Missing in Project");
		
		dashBoardPage = patternManagementPage.clickOnMyProjects();
		dashBoardPage.deleteProject(ProjectName);
		
		softAssert.assertAll();
//		dashBoardPage.clickOnBuildingBlocks();
//		String Message = buildingBlocksPage.deletePattern(OnlinePatternName);
//		softassert.assertEquals(Message, "Pattern has been deleted");
		
	}
	
	@Test(priority = 3, enabled = isEnabled, groups = {"sanity","BuildingBlocks"}, dependsOnMethods ="createGlobalOnlineCoursePattern" , description="Creating Global Template")
	public void createGlobalTemplate() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String TemplateName = TestUtil.faker.nation().capitalCity().trim()+TestUtil.faker.nation().language().trim();
		String Message = buildingBlocksPage.clickOnTemplates().createTemplate(TemplateName);
		softAssert.assertEquals(Message, "Template has been created succesfully.");
		
		List<String> Templates = buildingBlocksPage.searchTemplate(TemplateName).getListedTemplates();
		softAssert.assertEquals(Templates.get(0), TemplateName, "Template Not Found");
		
		courseEditorPage = buildingBlocksPage.clickOnContent();
		List<String> PatternList = courseEditorPage.getAllPatterns();
		softAssert.assertTrue(PatternList.contains(OnlinePatternName), "Pattern is Missing in Course Editor");
		
		String ActualContent = courseEditorPage.AddPatternToEditor(OnlinePatternName).getDraggedPatternsContent();
		softAssert.assertEquals(ActualContent, OnlinePatternContent,"Pattern Content Mismatch");
		
		buildingBlocksPage = courseEditorPage.goBackToBuildingBlocks();
		String TemplateDeleteSuccessMessage = buildingBlocksPage.deleteTemplate(TemplateName);
		buildingBlocksPage.confirmPopUp();
		softAssert.assertEquals(TemplateDeleteSuccessMessage, "Template has been deleted");
		
		String DeleteMessage = buildingBlocksPage.clickOnPatterns().deletePattern(OnlinePatternName);
		softAssert.assertEquals(DeleteMessage, "Pattern has been deleted");
		buildingBlocksPage.confirmPopUp();
		softAssert.assertAll();
	}
	
	@Test(priority = 4, enabled = isEnabled, groups = {"sanity","BuildingBlocks"}, description="Searching Widget")
	public void searchWidget() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		List<String> widget = buildingBlocksPage.clickOnWidgets().searchWidget("tabtable").getListedWidget();
		softAssert.assertEquals(widget.size(), 1);
		softAssert.assertEquals(widget.get(0), "tabtable","Searched widget not found");
		softAssert.assertAll();
	}
	
}
