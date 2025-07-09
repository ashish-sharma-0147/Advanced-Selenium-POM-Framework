package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AdministrationUsersPage;
import com.qa.pages.AssessmentQuestionPage;
import com.qa.pages.CreatePasswordPage;
import com.qa.pages.EditTOCPage;
import com.qa.pages.External_EmailSupport;
import com.qa.pages.LoginPage;
import com.qa.pages.ProjectUsageDashboardPage;
import com.qa.pages.ProjectUserManagementPage;
import com.qa.testdata.ProjectType;
import com.qa.testdata.TaskPriority;
import com.qa.testdata.TaskStatus;
import com.qa.testdata.UserRoles;
import com.qa.util.CustomSoftAssert;
import com.qa.util.TestUtil;

public class ProjectUsageDashboardPageTest extends TestBase {
	
	LoginPage loginPage;
	EditTOCPage editTocPage;
	AssessmentQuestionPage assessmentQuestionPage;
	ProjectUsageDashboardPage projectUsageDashboardPage;
	AdministrationUsersPage adminUserPage;
	External_EmailSupport externalSite;
	CreatePasswordPage createPasswordPage;
	ProjectUserManagementPage projectUserManagementPage;
	
	private final boolean isEnabled = true;
	private String projectName;
	private final String Yopmail_URL = "https://yopmail.com/en/";
	@SuppressWarnings("unused")
	private final String Mailinator_URL = "https://www.mailinator.com/";

	@BeforeMethod(alwaysRun=true)
	public void navigateToTOCPage(Method method) throws Exception {
		loginPage = new LoginPage();
		projectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		editTocPage = loginPage.ValidLogin(configuration().username(), configuration().password()).
				clickOnCreateNewProject().createProject(projectName,
						ProjectType.OnlineCourse.toString(),
						ProjectType.AdaptiveLearning.toString(),
						TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph())
				.clickCreateAndConfigure().clickOnCreateTOC();
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"verifyAssessmentWidgetListingAndMappedAssessmentCount","regression","ProjectUsageDashboardPageTest"}, 
			description="Check Question types data in assessment widget")
	public void verifyAssessmentWidgetListingAndMappedAssessmentCount() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String AssessmentNode = TestUtil.faker.animal().name();
		String AssessmentName = TestUtil.faker.lorem().word();
		String questionJSONpackage = "bulk_upload_questions_new.js";
		assessmentQuestionPage = editTocPage.clickOnAssessment().clickCreateNewAssessment().setAssessmentName(AssessmentName).clickConfirm().openAssessment(AssessmentName);
		HashMap<String,Integer> questionTypesCreated = assessmentQuestionPage.uploadQuestionsJSON(questionJSONpackage).getUploadedQuestionsTypeData();
		
		editTocPage = assessmentQuestionPage.clickOnBackToToc();
		projectUsageDashboardPage = editTocPage.clickOnAddNode().createAssessmentNode(AssessmentNode, AssessmentName).clickOnConfirm().clickOnProjectUsageDashboard();
		
		softAssert.assertEquals(projectUsageDashboardPage.getAvailableAssessmentList(), questionTypesCreated, "Available Assessment data mismatch");
		softAssert.assertEquals(projectUsageDashboardPage.getUsedAssessmentList(), questionTypesCreated, "Used Assessment data mismatch");
		softAssert.assertEquals(Integer.parseInt(projectUsageDashboardPage.getItemCount().get("ASSESSMENT ITEMS").toString()), projectUsageDashboardPage.getTotalUsedAssessmentCount(), "Mapped Assessment count mismatch");
		
		projectUsageDashboardPage.clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"verifyTocNodeCounts","regression","ProjectUsageDashboardPageTest"}, 
			description="Compare Module/Lesson/Topic count in TOC with Dashboard count ")
	public void verifyTocNodeCounts() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String tocPackage = "slo-import-to-frost-ch8-test.csv";
		editTocPage.importTOCPackage(tocPackage, true).confirmPopUp().hardRefresh();
		editTocPage.hardRefresh();
		HashMap<String,Integer> nodeTypesCount = editTocPage.expandTOCnodes().getAllNodeTypeCount();
		
		projectUsageDashboardPage = editTocPage.clickOnProjectUsageDashboard();
		HashMap<String,Integer> dashboardItemCount = projectUsageDashboardPage.getItemCount();
		
		softAssert.assertEquals(dashboardItemCount.get("MODULES"), nodeTypesCount.get("MODULES"), "Module Count mismatch in Dashboard!");
		softAssert.assertEquals(dashboardItemCount.get("LESSONS"), nodeTypesCount.get("LESSON"), "Lessons Count mismatch in Dashboard!");
		softAssert.assertEquals(dashboardItemCount.get("TOPICS"), nodeTypesCount.get("TOPIC"), "Topics Count mismatch in Dashboard!");
		softAssert.assertEquals(dashboardItemCount.get("CONTENT SCREENS"), nodeTypesCount.get("CONTENT"), "Content Screen Count mismatch in Dashboard!");
		
		projectUsageDashboardPage.clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 3, enabled = isEnabled, groups = {"verifyUsersWidgetListing","regression","ProjectUsageDashboardPageTest"}, 
			description="Verify the listing of the assigned user in Users widget")
	public void verifyUsersWidgetListing() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String firstName = "Test_"+TestUtil.faker.name().firstName();
		String lastName = TestUtil.faker.name().lastName();
		String userName = TestUtil.faker.name().username();
		String email = TestUtil.getRandomEmail("Yopmail");
		String password = TestUtil.getRandomAlphanumericPassword();
		adminUserPage = editTocPage.clickOnGoToAdministration().openUsersTab();
		adminUserPage.clickOnCreateAccount().createUser("Authoring", firstName, lastName, userName, email).closePopUp();
		externalSite = adminUserPage.Logout().launchExternalEmailSupport(Yopmail_URL);
		createPasswordPage = externalSite.Yopmail_openInbox(email).Yopmail_openEmailHavingSubject("FROST - Registration").Yopmail_clickOnCreateYourPassword();
		editTocPage = createPasswordPage.setNewPassword(password, password).clickOnBackToLogin().ValidLogin(configuration().username(), configuration().password()).openProject(projectName);
		projectUserManagementPage = editTocPage.clickOnManageUsers();
		projectUsageDashboardPage = projectUserManagementPage.addUser(email, UserRoles.Author).clickOnBackToToc().clickOnProjectUsageDashboard();
		
		List<LinkedHashMap<String,String>> listedUsers = projectUsageDashboardPage.getUserList();
		softAssert.assertEquals(listedUsers.get(0).get("Username"), configuration().username(), "Project Creator username mismatch");
		softAssert.assertEquals(listedUsers.get(0).get("Role"), "Admin", "Project Creator Role mismatch");
		softAssert.assertEquals(listedUsers.get(1).get("Username"), userName, "Assigned User username mismatch");
		softAssert.assertEquals(listedUsers.get(1).get("Role").toUpperCase(), UserRoles.Author.toString().toUpperCase(), "Assigned User Role mismatch");
		
		projectUsageDashboardPage.clickOnGoToAdministration().openUsersTab().searchUserBy("Email", email).deactivateUser().confirmPopup().goToAuthoring().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 4, enabled = isEnabled, groups = {"verifyAssetsWidgetListing","regression","ProjectUsageDashboardPageTest"}, 
			description="Verify the listing of the assets in Assets widget")
	public void verifyAssetsListing() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String imageToUpload = "nonsolus-digital_elsevier.png";
		String docToUpload = "Import_Sample.csv";
		projectUsageDashboardPage = editTocPage.clickOnManageAssets().uploadAsset(imageToUpload).uploadAsset(docToUpload)
				.uploadAsset(imageToUpload).uploadAsset(docToUpload).clickOnBackToToc().clickOnProjectUsageDashboard();
		HashMap<String,Integer> assetMap = projectUsageDashboardPage.getAssetCounts();
		softAssert.assertEquals(Integer.parseInt(assetMap.get("IMAGES").toString()), 2, "Asset(image) count mismatch!!");
		softAssert.assertEquals(Integer.parseInt(assetMap.get("OTHER").toString()), 2, "Asset(doc) count mismatch!!");
		projectUsageDashboardPage.clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 5, enabled = isEnabled, groups = {"verifyWidgetsListing","regression","ProjectUsageDashboardPageTest"}, 
			description="Verify the listing of the widgets in Project Usage Dashboard")
	public void verifyWidgetListing() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		projectUsageDashboardPage = editTocPage.openContentScreen().AddPatternToEditor("ACCORDION").AddPatternToEditor("HOTSPOT").saveContent().goBackToEditToc().clickOnProjectUsageDashboard();
		HashMap<String,Integer> widgetMap = projectUsageDashboardPage.getWidgetCounts();
		softAssert.assertEquals(Integer.parseInt(widgetMap.get("accordion").toString()), 1, "Widget(Accordion) count mismatch");
		//softAssert.assertEquals(widgetMap.get("hotspot"), 1, "Widget(FlashCard) count mismatch");
		projectUsageDashboardPage.clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 6, enabled = isEnabled, groups = {"taskFilterAndListing","regression","ProjectUsageDashboardPageTest"}, 
			description="Verify the listing of tasks along with task filter")
	public void taskFilterAndListing() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String firstName = "Test_"+TestUtil.faker.name().firstName();
		String lastName = TestUtil.faker.name().lastName();
		String userName = TestUtil.faker.name().username();
		String email = TestUtil.getRandomEmail("Yopmail");
		String password = TestUtil.getRandomAlphanumericPassword();
		String attachment = "nonsolus-digital_elsevier.png";
		String task1Description = TestUtil.faker.dune().character();
		adminUserPage = editTocPage.clickOnGoToAdministration().openUsersTab();
		adminUserPage.clickOnCreateAccount().createUser("Authoring", firstName, lastName, userName, email).closePopUp();
		externalSite = adminUserPage.Logout().launchExternalEmailSupport(Yopmail_URL);
		createPasswordPage = externalSite.Yopmail_openInbox(email).Yopmail_openEmailHavingSubject("FROST - Registration").Yopmail_clickOnCreateYourPassword();
		editTocPage = createPasswordPage.setNewPassword(password, password).clickOnBackToLogin().ValidLogin(configuration().username(), configuration().password()).openProject(projectName);
		projectUserManagementPage = editTocPage.clickOnManageUsers();
		editTocPage = projectUserManagementPage.addUser(email, UserRoles.Author).clickOnBackToToc();
		
		projectUsageDashboardPage = editTocPage.clickOnProjectPreview().selectNode("Chapter Summary").openTaskCommentSection().
				createTask(task1Description, firstName+" "+lastName, TaskStatus.OPEN, TaskPriority.HIGH, false, attachment).
				createTask(TestUtil.faker.witcher().character(), firstName+" "+lastName, TaskStatus.NEED_CLARIFICATION, TaskPriority.MEDIUM, true, attachment).clickOnBackToTOC().clickOnProjectUsageDashboard();
		List<LinkedHashMap<String,String>> taskList =projectUsageDashboardPage.filterTaskByAssignee("All").getTaskList();
		softAssert.assertEquals(taskList.get(1).get("Status"), TaskStatus.OPEN.toString().toUpperCase(), "Listed task status mismatch!!");
		softAssert.assertEquals(taskList.get(1).get("Severity"), TaskPriority.HIGH.toString().toUpperCase(), "Listed task Severity/priority mismatch!!");
		softAssert.assertEquals(taskList.get(1).get("Task"), task1Description, "Listed task Description mismatch!!");
		softAssert.assertEquals(taskList.get(1).get("Assigned To"), firstName+" "+lastName, "Listed task assignee mismatch!!");
		
		projectUsageDashboardPage.clickOnGoToAdministration().openUsersTab().searchUserBy("Email", email).deactivateUser().confirmPopup().goToAuthoring().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 7, enabled = isEnabled, groups = {"commentFilterAndListing","regression","ProjectUsageDashboardPageTest"}, 
			description="Verify the listing of comments along with comments filter")
	public void commentFilterAndListing() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String firstName = "Test_"+TestUtil.faker.name().firstName();
		String lastName = TestUtil.faker.name().lastName();
		String userName = TestUtil.faker.name().username();
		String email = TestUtil.getRandomEmail("Yopmail");
		String password = TestUtil.getRandomAlphanumericPassword();
		String attachment = "nonsolus-digital_elsevier.png";
		String adminCommentDescription = TestUtil.faker.dune().character();
		String userCommentDescription = TestUtil.faker.dune().character();
		adminUserPage = editTocPage.clickOnGoToAdministration().openUsersTab();
		adminUserPage.clickOnCreateAccount().createUser("Authoring", firstName, lastName, userName, email).closePopUp();
		externalSite = adminUserPage.Logout().launchExternalEmailSupport(Yopmail_URL);
		createPasswordPage = externalSite.Yopmail_openInbox(email).Yopmail_openEmailHavingSubject("FROST - Registration").Yopmail_clickOnCreateYourPassword();
		editTocPage = createPasswordPage.setNewPassword(password, password).clickOnBackToLogin().ValidLogin(configuration().username(), configuration().password()).openProject(projectName);
		projectUserManagementPage = editTocPage.clickOnManageUsers();
		editTocPage = projectUserManagementPage.addUser(email, UserRoles.ProjectAdmin).Logout().ValidLogin(userName, TestUtil.encodeToBase64(password)).openProject(projectName);
		
		editTocPage.clickOnProjectPreview().selectNode("Chapter Summary").openTaskCommentSection().
				createComment(userCommentDescription).
				createComment(TestUtil.faker.witcher().character(), attachment).Logout().
				ValidLogin(configuration().username(), configuration().password()).openProject(projectName);
		projectUsageDashboardPage = editTocPage.clickOnProjectPreview().selectNode("Chapter Summary").openTaskCommentSection().
				createComment(adminCommentDescription).
				createComment(TestUtil.faker.witcher().character(), attachment).
				clickOnBackToTOC().clickOnProjectUsageDashboard();
		List<LinkedHashMap<String,String>> commentsList =projectUsageDashboardPage.getCommentsList();
		softAssert.assertEquals(commentsList.get(1).get("Comments"), adminCommentDescription, "Description Mismatched for Admin Comment!!");
		softAssert.assertEquals(commentsList.get(1).get("Creator"), "frostloradmin frostloradmin", "Creator Mismatched for Admin Comment!!");
		softAssert.assertEquals(commentsList.get(3).get("Comments"), userCommentDescription, "Description Mismatched for User Comment!!");
		softAssert.assertEquals(commentsList.get(3).get("Creator"), firstName+" "+lastName, "Creator Mismatched for User Comment!!");
		
		projectUsageDashboardPage.clickOnGoToAdministration().openUsersTab().searchUserBy("Email", email).deactivateUser().confirmPopup().goToAuthoring().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
}
