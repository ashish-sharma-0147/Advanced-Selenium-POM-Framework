package com.qa.testcases;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import static com.qa.config.ConfigurationManager.configuration;
import com.qa.pages.CreateProjectPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.testdata.ProjectType;
import com.qa.util.CustomHardAssert;
import com.qa.util.TestUtil;

public class CreateProjectPageTest extends TestBase{
	LoginPage loginPage;
	CreateProjectPage createProjectPage;
	DashboardPage dashBoardPage;
	
	private final boolean isEnabled = true;
	
	@BeforeMethod(alwaysRun=true)
	public void navigateToCreateProjectPage(Method method) throws Exception {
		loginPage = new LoginPage();
		createProjectPage = loginPage.ValidLogin(configuration().username(), configuration().password()).clickOnCreateNewProject();
	}
	
	@Test(priority = 1, enabled = isEnabled, groups = {"sanity","createProject","createEDUPUBProject"}, description="Creating EDUPUB project")
	public void createEDUPUBProject() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String ProjectName = TestUtil.faker.artist().name()+TestUtil.faker.superhero().name()+TestUtil.faker.animal().name();
		dashBoardPage = createProjectPage.createProject(ProjectName,
				ProjectType.EDUPUB.toString(),null, TestUtil.faker.code().isbn13(),
				TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateProject();
		String EduPub = dashBoardPage.getSuccessMessage();
		Assert.assertEquals(EduPub, "Success! Project created successfully.");
		String Message = dashBoardPage.deleteProject(ProjectName);
		Assert.assertEquals(Message, "Project has been deleted.");
		dashBoardPage.confirmPopUp();
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"sanity","createProject","createObjectiveOrganizedProject"}, description="Creating Objective Organized project")
	public void createObjectiveOrganizedProject() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String ProjectName = TestUtil.faker.music().genre()+TestUtil.faker.superhero().name()+TestUtil.faker.animal().name();
		dashBoardPage = createProjectPage.createProject(ProjectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.ObjectiveOrganized.toString(),
				TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateProject();
		String ObjectiveOrganized = dashBoardPage.getSuccessMessage();
		Assert.assertEquals(ObjectiveOrganized, "Success! Project created successfully.");
		String Message = dashBoardPage.deleteProject(ProjectName);
		Assert.assertEquals(Message, "Project has been deleted.");
		dashBoardPage.confirmPopUp();
	}
	
	@Test(priority = 3, enabled = isEnabled, groups = {"sanity","createProject","createBookOrganizedProject"}, description="Creating Book Organized project")
	public void createBookOrganizedProject() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String ProjectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.artist().name();
		dashBoardPage = createProjectPage.createProject(ProjectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.BookOrganized.toString(),
				TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateProject();
		String BookOrganized = dashBoardPage.getSuccessMessage();
		Assert.assertEquals(BookOrganized, "Success! Project created successfully.");
		String Message = dashBoardPage.deleteProject(ProjectName);
		Assert.assertEquals(Message, "Project has been deleted.");
		dashBoardPage.confirmPopUp();
	}
	
	@Test(priority = 4, enabled = isEnabled, groups = {"sanity","createProject","createAdaptiveLearningProject"}, description="Creating Adaptive Learning project")
	public void createAdaptiveLearningProject() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String ProjectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		dashBoardPage = createProjectPage.createProject(ProjectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.AdaptiveLearning.toString(),
				TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateProject();
		String AdaptiveLearning = dashBoardPage.getSuccessMessage();
		Assert.assertEquals(AdaptiveLearning, "Success! Project created successfully.");
		String Message = dashBoardPage.deleteProject(ProjectName);
		Assert.assertEquals(Message, "Project has been deleted.");	
		dashBoardPage.confirmPopUp();
	}
	
	@Test(priority = 5, enabled = isEnabled, groups = {"sanity","createProject","createEvolveResourcesProject"}, description="Creating Evolve Resources project")
	public void createEvolveResourcesProject() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String ProjectName = TestUtil.faker.superhero().name()+TestUtil.faker.animal().name()+TestUtil.faker.artist().name();
		dashBoardPage = createProjectPage.createProject(ProjectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.EvolveResources.toString(),
				TestUtil.faker.code().isbn13(),
				TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateProject();
		String EvolveResources = dashBoardPage.getSuccessMessage();
		Assert.assertEquals(EvolveResources, "Success! Project created successfully.");
		String Message = dashBoardPage.deleteProject(ProjectName);
		Assert.assertEquals(Message, "Project has been deleted.");
		dashBoardPage.confirmPopUp();
	}

    @Test(priority = 6, enabled = isEnabled, groups = {"sanity","createProject", "regression","editProject"}, description="Editting the project")
	public void editProject() throws Exception {
        CustomHardAssert Assert = new CustomHardAssert();
    
        // Create a new project
        String originalProjectName = TestUtil.faker.superhero().name() + TestUtil.faker.animal().name() + TestUtil.faker.artist().name();
        dashBoardPage = createProjectPage.createProject(originalProjectName,
                ProjectType.OnlineCourse.toString(),
                ProjectType.EvolveResources.toString(),
                TestUtil.faker.code().isbn13(),
                TestUtil.faker.book().author(), 
                TestUtil.faker.book().genre(),
                TestUtil.faker.lorem().paragraph()).clickCreateProject();
        
        // Verify project creation success message
        String successMessage = dashBoardPage.getSuccessMessage();
        Assert.assertEquals(successMessage, "Success! Project created successfully.");
        
        // Edit the project details
        String newProjectName = TestUtil.faker.superhero().name() + TestUtil.faker.animal().name() + TestUtil.faker.artist().name();
        String newAuthor = TestUtil.faker.book().author();
        String newDiscipline = TestUtil.faker.book().genre();
        String newDescription = TestUtil.faker.lorem().paragraph();
        
        dashBoardPage.clickOnEditProject(originalProjectName);
        createProjectPage.editProjectDetails(newProjectName, newAuthor, newDiscipline, newDescription);
        
        // Verify project edit success message
        // String editSuccessMessage = dashBoardPage.getSuccessMessage();
        // Assert.assertEquals(editSuccessMessage, "Success! Project updated successfully.");
        
        // Verify the updated project details
        dashBoardPage.clickOnEditProject(newProjectName);
        createProjectPage.waitForProjectPageToLoad();
        Assert.assertEquals(createProjectPage.getProjectName(), newProjectName, "Project name mismatch");
        Assert.assertEquals(createProjectPage.getAuthor(), newAuthor, "Author mismatch");
        Assert.assertEquals(createProjectPage.getDiscipline(), newDiscipline, "Discipline mismatch");
        Assert.assertEquals(createProjectPage.getDescription(), newDescription, "Description mismatch");
        createProjectPage.returnToDashboard();
        // Clean up by deleting the project
        dashBoardPage.waitForPageToCompletelyLoad();
        String deleteMessage = dashBoardPage.deleteProject(newProjectName);
        Assert.assertEquals(deleteMessage, "Project has been deleted.");
        dashBoardPage.confirmPopUp();
    }
}
