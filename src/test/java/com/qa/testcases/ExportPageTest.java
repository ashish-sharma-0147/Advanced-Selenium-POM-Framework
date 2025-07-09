package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.CreateProjectPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.EditTOCPage;
import com.qa.pages.ExportPage;
import com.qa.pages.LoginPage;
import com.qa.testdata.ProjectExportTypes;
import com.qa.testdata.ProjectType;
import com.qa.testdata.WorkFlowStatus;
import com.qa.util.CustomSoftAssert;
import com.qa.util.TestUtil;


public class ExportPageTest extends TestBase{
	
	LoginPage loginPage;
	DashboardPage dashBoardPage;
	CreateProjectPage createProjectPage;
	EditTOCPage editTOCPage;
	ExportPage exportPage;
	
	String ProjectName = TestUtil.faker.artist().name()+TestUtil.faker.superhero().name()+TestUtil.faker.animal().name();
	
	private final boolean isEnabled = true;

	@BeforeMethod(alwaysRun=true)
	public void prepareProjectForExport(Method method) throws Exception {
		loginPage = new LoginPage();
        createProjectPage = loginPage.ValidLogin(configuration().username(), configuration().password()).clickOnCreateNewProject();		
    	editTOCPage = createProjectPage.createProject(ProjectName,
	               ProjectType.OnlineCourse.toString(),
	               ProjectType.AdaptiveLearning.toString(),
	               TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateAndConfigure().clickOnCreateTOC();
		editTOCPage.publishProject().clickOK().waitForProjectPublishToComplete();
		
		String tocPackage = "slo-import-to-frost-ch8-test.csv";
		editTOCPage.importTOCPackage(tocPackage, true).confirmPopUp().hardRefresh();

	}
	
	@Test(priority = 1, enabled = isEnabled, groups = {"sanity","verifyExportListing"}, description="verify export functionality and listing")
	public void verifyExportListing() throws Exception{
		CustomSoftAssert softAssert = new CustomSoftAssert();
		editTOCPage.updateWorkFlowStatusForAllModules(WorkFlowStatus.ReadyForExport);
		
		/***
		Removing the VTW export verification as it is now not supported in the Adaptive Lessons with the latest version of Frost.
		
		editTOCPage.exportCourse(ProjectExportTypes.VTW);		
		exportPage = editTOCPage.clickOnMyProjects().searchProject(ProjectName).exportList();

		LinkedHashMap<String, String> exportList = exportPage.getExportList();
		String getInitiatedAt = exportList.get("Initiated at");
		String getCreatedBy = exportList.get("Created By");

		softAssert.assertEquals(exportList.get("Project Name"), ProjectName, "VTW :: Project name is not matched in Export tab");
		softAssert.assertTrue(exportPage.isCorrectModuleDisplayedInExportList(), "VTW :: Module Names not matching in Export tab");
		softAssert.assertTrue(getInitiatedAt != null && !getInitiatedAt.isEmpty(), "VTW :: Initiated at is empty in Export tab");
		softAssert.assertTrue(getCreatedBy != null && !getCreatedBy.isEmpty(), "VTW :: Created by is empty in Export tab");
		
		// If else condition to handle the situation where exports gets completed sometimes without going to in progress state as the packages are small
		if(exportPage.getStatus().equals("Export in progress")){
			exportPage.openActiveExportsTab();
			softAssert.assertEquals(exportPage.getProjectNameFromActiveExportList(), ProjectName, "VTW :: Project name is not matched in Active Export Tab");
			softAssert.assertTrue(exportPage.isCorrectModuleDisplayedInActiveExportList(),"VTW :: Module Names not matching in Active Export Tab");
			exportPage.refreshPageUntilStatusIsCompleted();
			softAssert.assertEquals(exportPage.getStatus(), "Completed", "VTW :: Export Status is not Completed");
			exportPage.openActiveExportsTab();
			softAssert.assertEquals(exportPage.getExportAlertMessage(),"No Active Exports","VTW :: Active Export Message is not displayed");
		} else if (exportPage.getStatus().equals("Completed")) {
			exportPage.openActiveExportsTab();
			softAssert.assertEquals(exportPage.getExportAlertMessage(),"No Active Exports","VTW :: Active Export Message is not displayed");
		}
		
		exportPage = exportPage.clickOnBackToToc().exportCourse(ProjectExportTypes.QTI).clickOnMyProjects().searchProject(ProjectName).exportList();  *******/
		
		
		exportPage = editTOCPage.exportCourse(ProjectExportTypes.QTI).clickOnMyProjects().searchProject(ProjectName).exportList();
		LinkedHashMap<String, String> exportList  = exportPage.getExportList();
		 
		softAssert.assertEquals(exportList.get("Project Name"), ProjectName, "QTI :: Project name is not matched in Export tab");
		softAssert.assertTrue(exportPage.isCorrectModuleDisplayedInExportList(), "QTI ::  Module Names not matching in Export tab");
		softAssert.assertTrue(exportList.get("Initiated at") != null && !exportList.get("Initiated at").isEmpty(), "QTI :: Initiated at is empty in Export tab");
		softAssert.assertTrue(exportList.get("Created By") != null && !exportList.get("Created By").isEmpty(), "QTI :: Created by is empty in Export tab");
		
		if(exportPage.getStatus().equals("Export in progress")){
			exportPage.openActiveExportsTab();
			softAssert.assertEquals(exportPage.getProjectNameFromActiveExportList(), ProjectName, "QTI :: Project name is not matched in Active Export Tab");
			softAssert.assertTrue(exportPage.isCorrectModuleDisplayedInActiveExportList(), "QTI :: Module Names not matching in Active Export tab");
			exportPage.refreshPageUntilStatusIsCompleted();
			softAssert.assertEquals(exportPage.getStatus(), "Completed", "QTI :: Export Status is not Completed");
			exportPage.openActiveExportsTab();
			softAssert.assertEquals(exportPage.getExportAlertMessage(),"No Active Exports","QTI"
					+ ""
					+ " :: Active Export Message is not displayed");
		} else if (exportPage.getStatus().equals("Completed")) {
			exportPage.openActiveExportsTab();
			softAssert.assertEquals(exportPage.getExportAlertMessage(),"No Active Exports","QTI :: Active Export Message is not displayed");
		}

		exportPage.clickOnMyProjects().deleteProject(ProjectName);
		softAssert.assertAll();
	}
	


}
