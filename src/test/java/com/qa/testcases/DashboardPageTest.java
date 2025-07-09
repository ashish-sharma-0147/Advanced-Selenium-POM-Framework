package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.CreateProjectPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.util.CustomHardAssert;
import com.qa.util.DataProviderClass;

public class DashboardPageTest extends TestBase{

	LoginPage loginPage;
	DashboardPage dashBoardPage;
	CreateProjectPage createNewProjectPage;

	private final boolean isEnabled = true;

	@BeforeMethod(alwaysRun=true)
	public void navigateToDashboardPage(Method method) throws Exception {
		loginPage = new LoginPage();
		dashBoardPage = loginPage.ValidLogin(configuration().username(), configuration().password());
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"regression","redirect"}, description="Redirect To Create New Project Page ")
	public void createNewProjectRedirectionTest() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String heading = dashBoardPage.clickOnCreateNewProject().getPageHeading();
		Assert.assertEquals(heading, "Create New Project");
	}

	@Test(priority = 2, enabled = isEnabled, dataProvider = "ProjectTypes", dataProviderClass = DataProviderClass.class, groups = {"sanity","Filter"}, description="Filter Project by Project Type")
	public void projectTypeFilterListing(String projectType) throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		dashBoardPage.clickProjectTypeFilter().setProjectFilter(projectType.toString());
		//dashBoardPage.clickOnPageBody();
		String FilteredProjectType = dashBoardPage.getListedProjectsType();
		Assert.assertEquals(FilteredProjectType, projectType.toString());
	}

	@Test(priority = 3, enabled = isEnabled, groups = {"sanity","redirect"}, description="Redirect to LOR Page")
	public void LORRedirection() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String pageTitle = dashBoardPage.clickOnLOR().getPageTitle();
		Assert.assertEquals(pageTitle, "Learning Object Repository");
	}

	@Test(priority = 4, enabled = isEnabled, groups = {"sanity","redirect"}, description="Redirect to Admin Settings Page")
	public void AdminSettingsRedirection() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String pageTitle = dashBoardPage.clickOnAdmin().getPageTitle().trim();
		Assert.assertEquals(pageTitle, "Settings");
	}

	@Test(priority = 5, enabled = isEnabled, groups = {"sanity","redirect"}, description="Redirect to Administration Page")
	public void AdministrationRedirection() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String pageTitle = dashBoardPage.clickOnGoToAdministration().getCopyRightText().trim();
		Assert.assertEquals(pageTitle, "All Rights Reserved. © 2019 LearningMate Solutions Pvt. Ltd");
	}

	@Test(priority = 6, enabled = isEnabled, groups = {"sanity","redirect"}, description="Redirect to Building Blocks Page")
	public void BuildingBlocksRedirection() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String pageTitle = dashBoardPage.clickOnBuildingBlocks().getPageTitle().trim();
		Assert.assertEquals(pageTitle, "Building Blocks");
	}

}
