package com.qa.testcases;
import static com.qa.config.ConfigurationManager.configuration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AdministrationUsersPage;
import com.qa.pages.AssessmentPage;
import com.qa.pages.AssessmentQuestionPage;
import com.qa.pages.AssessmentQuestionsDetailsPage;
import com.qa.pages.CreatePasswordPage;
import com.qa.pages.DashboardPage;
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
public class AssessmentQuestionDetailsPageTest extends TestBase{
	
    LoginPage loginPage;
	DashboardPage dashBoardPage;
	EditTOCPage editTocPage;
	AssessmentPage assessmentPage;
    AssessmentQuestionPage assessmentQuestionPage; 
    AssessmentQuestionsDetailsPage assessmentQuestionsDetailsPage;
    ProjectUsageDashboardPage projectUsageDashboardPage;
    AdministrationUsersPage adminUserPage;
	External_EmailSupport externalSite;
	CreatePasswordPage createPasswordPage;
	ProjectUserManagementPage projectUserManagementPage;
    private String projectName;

    private final boolean isEnabled = true;

    @BeforeMethod(alwaysRun=true)
    public void navigateToAssessmentQuestionPage() throws Exception {
        loginPage = new LoginPage();
		dashBoardPage = loginPage.ValidLogin(configuration().username(), configuration().password());
		projectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		editTocPage = dashBoardPage.clickOnCreateNewProject().createProject(projectName,
						ProjectType.OnlineCourse.toString(),
						ProjectType.AdaptiveLearning.toString(),
						TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph())
				.clickCreateAndConfigure().clickOnCreateTOC();
		assessmentPage = editTocPage.clickOnAssessment();
    }

	@Test(priority = 1, enabled = isEnabled, groups = {"verifyAssessmentQuestionDetailsPageTasksAndComments","regression","AssessmentQuestionDetailsPageTest"}, 
				description="Check if users can successfully create tasks and comments on Assessment Question Details page corresponding to each section")
	public void verifyAssessmentQuestionDetailsPageTasksAndComments() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();

		String AssessmentName = TestUtil.faker.lorem().word();		
		String questionJSONpackage = "bulk_upload_questions_new.js";
		Thread.sleep(2000);
		assessmentPage.clickCreateNewAssessment().setAssessmentName(AssessmentName).clickConfirm();
		assessmentQuestionPage = assessmentPage.openAssessment(AssessmentName);
		assessmentQuestionPage.uploadQuestionsJSON(questionJSONpackage);
		
        assessmentQuestionsDetailsPage = assessmentQuestionPage.clickOnEdit();
        boolean iconsVerified = assessmentQuestionsDetailsPage.verifyIcons();
        softAssert.assertTrue(iconsVerified, "Icons verification failed");
        assessmentQuestionsDetailsPage.clickQuestionStemIcon();
        assessmentQuestionsDetailsPage.createTaskAndComment("Question Stem: Task ", "Question Stem: Comment", TaskStatus.OPEN, TaskPriority.HIGH, 1);
        assessmentQuestionsDetailsPage.clickSampleAnswerIcon();
        assessmentQuestionsDetailsPage.createTaskAndComment("Sample Answer: Task", "Sample Answer: Comment", TaskStatus.OPEN, TaskPriority.HIGH, 2);
        assessmentQuestionsDetailsPage.clickFeedbackIcon();
        assessmentQuestionsDetailsPage.createTaskAndComment("Feedback: Task", "Feedback: Comment", TaskStatus.OPEN, TaskPriority.HIGH, 3);
        softAssert.assertTrue(assessmentQuestionsDetailsPage.verifyAllTasksAndComments(),"tasks verification failed");

        assessmentQuestionPage = assessmentQuestionsDetailsPage.backToAssessmentPage();
        editTocPage = assessmentQuestionPage.clickOnBackToToc();
        projectUsageDashboardPage = editTocPage.clickOnProjectUsageDashboard();
        assessmentQuestionsDetailsPage = projectUsageDashboardPage.clickTaskLinkToAssessment();
        softAssert.assertTrue(assessmentQuestionsDetailsPage.checkProjectUsageTaskLink(), "Project Usage task link verification failed");
        assessmentQuestionPage = assessmentQuestionsDetailsPage.backToAssessmentPage();
        editTocPage = assessmentQuestionPage.clickOnBackToToc();
        projectUsageDashboardPage = editTocPage.clickOnProjectUsageDashboard();
        assessmentQuestionsDetailsPage = projectUsageDashboardPage.clickCommentLinkToAssessment();
        softAssert.assertTrue(assessmentQuestionsDetailsPage.checkProjectUsageCommentLink(), "Project Usage comment link verification failed");
        softAssert.assertAll();
        dashBoardPage = assessmentQuestionsDetailsPage.clickOnMyProjects();
        dashBoardPage.deleteProject(projectName);
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"verifyUUIDandVTWID","AssessmentQuestionDetailsPageTest"}, description="Validate UUID during various assesment conditions")
	public void verifyUUIDandVTWID() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String AssessmentName = TestUtil.faker.cat().name();
		
		//added sleep as a temp fix
		Thread.sleep(2000);
		assessmentPage.clickCreateNewAssessment().setAssessmentName(AssessmentName).clickConfirm();
		assessmentQuestionsDetailsPage = assessmentPage.openAssessment(AssessmentName).uploadQuestionsJSON("bulk_upload_questions_new.js").clickOnEdit();
		
		//Thread.sleep(2000);
		String CurrentVTWID = assessmentQuestionsDetailsPage.getVTWID();
		String CurrentUUID = assessmentQuestionsDetailsPage.getUUID();
		
		softAssert.assertNotEquals(CurrentVTWID, "", "Current VTWID is empty");
		softAssert.assertNotEquals(CurrentUUID, "", "Current UUID is empty");
		
		String EditedVTWID = TestUtil.faker.internet().uuid();
		//Thread.sleep(2000);
		assessmentQuestionsDetailsPage.toggleVTWSetting().confirmPopup().manuallySetVTWID(EditedVTWID).generateUUID().clickOnUpdate();
		//Thread.sleep(2000);
		softAssert.assertEquals(assessmentQuestionsDetailsPage.getAlertMessage(), "Question has been successfully edited", "Updated ID success message mismatch");
		
		assessmentQuestionsDetailsPage.clickOk();
		
		String UpdatedVTWID = assessmentQuestionsDetailsPage.getVTWID();
		String UpdatedUUID = assessmentQuestionsDetailsPage.getUUID();
		
		softAssert.assertEquals(EditedVTWID, UpdatedVTWID, "VTWID did not update");
		softAssert.assertNotEquals(CurrentUUID, UpdatedUUID, "UUID did not update");
		
		assessmentQuestionsDetailsPage.clickNextButton().toggleVTWSetting().confirmPopup().manuallySetVTWID(UpdatedVTWID).clickOnUpdate();
		
		softAssert.assertNotEquals(assessmentQuestionsDetailsPage.getAlertMessage(), "UUID Exists.", "Duplicate VTW ID error message mismatch");
		
		assessmentQuestionsDetailsPage.clickOk();
		
		String firstName = "Test_"+TestUtil.faker.name().firstName();
		String lastName = TestUtil.faker.name().lastName();
		String userName = TestUtil.faker.name().username();
		String email = TestUtil.getRandomEmail("Yopmail");
		String password = TestUtil.getRandomAlphanumericPassword();
		final String Yopmail_URL = "https://yopmail.com/en/";

		adminUserPage = assessmentQuestionsDetailsPage.clickOnGoToAdministration().openUsersTab();
		adminUserPage.clickOnCreateAccount().createUser("Authoring", firstName, lastName, userName, email).closePopUp();
		externalSite = adminUserPage.Logout().launchExternalEmailSupport(Yopmail_URL);
		createPasswordPage = externalSite.Yopmail_openInbox(email).Yopmail_openEmailHavingSubject("FROST - Registration").Yopmail_clickOnCreateYourPassword();
		editTocPage = createPasswordPage.setNewPassword(password, password).clickOnBackToLogin().ValidLogin(configuration().username(), configuration().password()).openProject(projectName);
		
		//Reviewers cannot edit the IDs
		projectUserManagementPage = editTocPage.clickOnManageUsers();
		editTocPage = projectUserManagementPage.addUser(email, UserRoles.Reviewer).Logout().ValidLogin(userName, TestUtil.encodeToBase64(password)).openProject(projectName);
		assessmentQuestionPage = editTocPage.clickOnAssessment().openAssessment(AssessmentName);
		assessmentQuestionsDetailsPage = assessmentQuestionPage.clickOnEdit();
		softAssert.assertFalse(assessmentQuestionsDetailsPage.isIDEditDisplayed(), "UUID or VTWID can be edited by Reviewer!!");
		dashBoardPage = assessmentQuestionsDetailsPage.Logout().ValidLogin(configuration().username(), configuration().password());
		
		//Authors cannot edit the IDs
		projectUserManagementPage = dashBoardPage.openProject(projectName).clickOnManageUsers().deleteAllAssociatedUsers();
		softAssert.assertEquals(projectUserManagementPage.getAlertMessage().replace("×", "").trim(), "User(s) deleted successfully.", "User delete success message not displayed");
		
		editTocPage = projectUserManagementPage.addUser(email, UserRoles.Author).Logout().ValidLogin(userName, TestUtil.encodeToBase64(password)).openProject(projectName);
		
		assessmentQuestionPage = editTocPage.clickOnAssessment().openAssessment(AssessmentName);
		assessmentQuestionsDetailsPage = assessmentQuestionPage.clickOnEdit();
		
		softAssert.assertFalse(assessmentQuestionsDetailsPage.isIDEditDisplayed(), "UUID or VTWID can be edited by Author!!");

		dashBoardPage = assessmentQuestionsDetailsPage.Logout().ValidLogin(configuration().username(), configuration().password());
		
		dashBoardPage.deleteProject(projectName);
		dashBoardPage.confirmPopUp();
		dashBoardPage.clickOnGoToAdministration().openUsersTab().searchUserBy("Email", email).deactivateUser();
		
		softAssert.assertAll();
		
	}
}
