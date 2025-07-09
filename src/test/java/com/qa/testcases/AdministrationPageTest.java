package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AdministrationOrganizationPage;
import com.qa.pages.AdministrationUsersPage;
import com.qa.pages.CreatePasswordPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.EditTOCPage;
import com.qa.pages.External_EmailSupport;
import com.qa.pages.LoginPage;
import com.qa.testdata.OrganizationFilterType;
import com.qa.testdata.OrganizationType;
import com.qa.testdata.ProjectType;
import com.qa.util.CustomSoftAssert;
import com.qa.util.DriverActions;
import com.qa.util.TestUtil;

public class AdministrationPageTest extends TestBase{

	LoginPage loginPage;
	AdministrationOrganizationPage adminOrgPage;
	AdministrationUsersPage adminUserPage;
	External_EmailSupport externalSite;
	CreatePasswordPage createPasswordPage;
	EditTOCPage editTocPage;
	DashboardPage dashBoardPage;

	private final boolean isEnabled = true;
	
	private final String Yopmail_URL = "https://yopmail.com/en/";
	@SuppressWarnings("unused")
	private final String Mailinator_URL = "https://www.mailinator.com/";
	
	String firstName = "Test_"+TestUtil.faker.name().firstName();
	String lastName = TestUtil.faker.name().lastName();
	String userName = TestUtil.faker.name().username();
	String email = TestUtil.getRandomEmail("yopmail");
	String password = TestUtil.getRandomAlphanumericPassword();
	String orgName,orgId,user_Name;

	@BeforeMethod(alwaysRun=true)
	public void navigateToAdminPage(Method method) throws Exception {
		loginPage = new LoginPage();
		adminOrgPage = loginPage.ValidLogin(configuration().username(), configuration().password()).clickOnGoToAdministration();
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"sanity","Admin","Administration","Organization"}, description="Admin Organization Management (Organization Creation and Search by Name/Id/Type/Level)")
	public void organizationManagementTest() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		/********** Verifying Organization Creation **********/
		orgName = TestUtil.faker.company().name().trim();
		String alertMessage = adminOrgPage.clickOnAddOrganization().addOrganization(orgName, OrganizationType.Other).getAlertMessage();
		softAssert.assertEquals(alertMessage, "Organization saved successfully", "Org Creation Alert Message Text Mismatch");
		adminOrgPage.closePopUp();
		
		/********** Verifying Organization Search and Edit/Update **********/
		orgId = adminOrgPage.searchOrg(OrganizationFilterType.Name, orgName).getListedTableContent().get(0).get("ORGANIZATION ID");
		String message = adminOrgPage.ClickOnEditOrg().editOrg(orgName, OrganizationType.Other, "Inactive").getAlertMessage();
		softAssert.assertEquals(message, "Organization saved successfully", "Org Edit Alert Message Text Mismatch");
		adminOrgPage.closePopUp();
		
		/********** Filter By Level **********/
		softAssert.assertEquals(adminOrgPage.searchOrg(OrganizationFilterType.Level, "1").getListedTableContent().get(0).get("ORGANIZATION LEVEL"),"Level 1");
		adminOrgPage.resetFilter();
		
		/********** Filter By Type **********/
		softAssert.assertEquals(adminOrgPage.searchOrg(OrganizationFilterType.Type, "Others").getListedTableContent().get(0).get("ORGANIZATION TYPE"),"Others");
		adminOrgPage.resetFilter();
		
		/********** Filter By Id **********/
		softAssert.assertEquals(adminOrgPage.searchOrg(OrganizationFilterType.Id, orgId).getListedTableContent().get(0).get("ORGANIZATION ID"),orgId);
		softAssert.assertAll();
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"sanity","Admin","Administration","User"}, description="Admin User Management (User Creation, Search by Name/Organization/Status and Deactivate)")
	public void userManagementTest() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		/********** Verifying User Creation **********/
		String firstName = TestUtil.faker.name().firstName();
		String lastName = TestUtil.faker.name().lastName();
		String userName = TestUtil.faker.name().username();
		String email = TestUtil.getRandomEmail("yopmail");
		adminUserPage = adminOrgPage.openUsersTab();
		String alertMessage = adminUserPage.clickOnCreateAccount().createUser("Authoring", firstName, lastName, userName, email).getAlertMessage();
		softAssert.assertEquals(alertMessage, "User created successfully", "User Creation Alert Message Mismatch");
		adminUserPage.closePopUp();
		
		/********** Filter By Name **********/
		adminUserPage = adminOrgPage.openUsersTab();
		softAssert.assertEquals(adminUserPage.searchUserBy("Name", firstName+" "+lastName).getListedTableContent().get(0).get("NAME"),firstName+" "+lastName);
		adminUserPage.resetFilter();
		
		/********** Filter By Organization **********/
		softAssert.assertEquals(adminUserPage.searchUserBy("Organization", "LearningMate").getListedTableContent().get(0).get("ORGANIZATION"),"LearningMate");
		adminUserPage.resetFilter();
		
		/********** Filter By Status **********/
		//softAssert.assertEquals(adminUserPage.searchUserBy("Status", "Deactivated").getListedTableContent().get(0).get("Active"),"No");
		
		/********** Verifying User Search and Edit/Update **********/
		user_Name = adminUserPage.searchUserBy("Email", email).getListedTableContent().get(0).get("NAME");
		String message = adminUserPage.ClickOnEdit().editUser("All", firstName, lastName, userName, email).getAlertMessage();
		softAssert.assertEquals(message, "User updated successfully", "User Update Alert Message Mismatch");
		adminUserPage.closePopUp();
		String deactiveMessage = adminUserPage.deactivateUser().getAlertMessage();
		softAssert.assertEquals(deactiveMessage, "Account deactivated successfully", "User Deactivate Alert Message Mismatch");
		softAssert.assertAll();
	}
	
	@Test(priority = 3, enabled = isEnabled, groups = {"sanity","Admin","Administration","User","EmailNotification"}, description="Verifying email notification for user creation")
	public void userCreationEmailNotificationTest() throws Exception{
		CustomSoftAssert softAssert = new CustomSoftAssert();
		adminUserPage = adminOrgPage.openUsersTab();
		adminUserPage.clickOnCreateAccount().createUser("Authoring", firstName, lastName, userName, email).closePopUp();
		externalSite = adminUserPage.Logout().launchExternalEmailSupport(Yopmail_URL);
		String OldWindow = DriverActions.getCurrentWindow();
		createPasswordPage = externalSite.Yopmail_openInbox(email).Yopmail_openEmailHavingSubject("FROST - Registration").Yopmail_clickOnCreateYourPassword();
		String message = createPasswordPage.setNewPassword(password, password).getAlertMessage();
		softAssert.assertEquals(message, "Password created successfully. Try to login.", "Success message mismatch");
		createPasswordPage.closeCurrentTab();
		DriverActions.switchDriverToWindow(OldWindow);
		externalSite.Yopmail_navigateToHome();
		Thread.sleep(5000);
		String heading = externalSite.Yopmail_openInbox(email).Yopmail_openEmailHavingSubject("Password Generated").Yopmail_clickOnLogin().ValidLogin(userName, TestUtil.encodeToBase64(password)).getPageHeading();
		//String heading = createPasswordPage.clickOnBackToLogin().ValidLogin(userName, password).getPageHeading();
		softAssert.assertEquals(heading, "My Projects","Dashboard page heading mismatch");
		
//		externalSite = adminUserPage.Logout().launchExternalEmailSupport(Yopmail_URL);
//		String OldWindow = DriverActions.getCurrentWindow();
//		createPasswordPage = externalSite.Yopmail_openInbox(email).Yopmail_openEmailHavingSubject("FROST - Registration").Yopmail_clickOnCreateYourPassword();	
//		String message = createPasswordPage.setNewPassword(password, password).getAlertMessage();
//		softAssert.assertEquals(message, "Password created successfully. Try to login.", "Success message mismatch");
//		createPasswordPage.closeCurrentTab();
//		DriverActions.switchDriverToWindow(OldWindow);
//		String heading = externalSite.Yopmail_RefreshInbox().Yopmail_openEmailHavingSubject("Password Generated").Yopmail_clickOnLogin().ValidLogin(userName, password).getPageHeading();
//		//String heading = createPasswordPage.clickOnBackToLogin().ValidLogin(userName, password).getPageHeading();
//		softAssert.assertEquals(heading, "My Projects","Dashboard page heading mismatch");
		softAssert.assertAll();
	}
	
	@Test(priority = 4, enabled = isEnabled, groups = {"sanity","Admin","Administration","User","Workflow"}, dependsOnMethods="userCreationEmailNotificationTest", description="Assigning Folder module to user")
	public void assignModuleToUser() throws Exception{
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String projectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		String ModuleName = "Course Introduction";
		editTocPage = adminOrgPage.goToAuthoring().clickOnCreateNewProject().createProject(projectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.AdaptiveLearning.toString(),
				TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph())
		.clickCreateAndConfigure().clickOnCreateTOC();
		String message = editTocPage.clickOnAssignModule().assignModule(userName, "author").getAlertMessage();
		softAssert.assertEquals(message, "User has been assigned successfully", "User assign success text mismatch");
		editTocPage.confirmPopUp();
		List<HashMap<String,String>> map = editTocPage.getSelectedUsersWithRole();
		softAssert.assertTrue(map.get(0).containsKey(userName), "User Name not listed");
		softAssert.assertEquals(map.get(0).get(userName), "author", "User Role mismatch");
		loginPage = editTocPage.closeAssignUserPopUp().Logout();
		String URL = loginPage.getActiveURL();
		loginPage.launchFROSTinNewTab(URL);
		softAssert.assertEquals(loginPage.ValidLogin(userName, TestUtil.encodeToBase64(password)).openProject(projectName).getAllNodes().get(0), ModuleName, "Assigned Module Missing");
		loginPage = editTocPage.Logout();
		loginPage.hardRefresh();
		dashBoardPage = loginPage.ValidLogin(configuration().username(), configuration().password());
		dashBoardPage.deleteProject(projectName);
		dashBoardPage.confirmPopUp();
		editTocPage.clickOnGoToAdministration().openUsersTab().searchUserBy("Email", email).deactivateUser();
		softAssert.assertAll();
	}
	
}
