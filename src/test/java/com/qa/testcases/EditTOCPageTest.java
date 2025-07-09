package com.qa.testcases;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import static com.qa.config.ConfigurationManager.configuration;
import com.qa.pages.AddNodePopupPage;
import com.qa.pages.AddNodePopupPage.resourceType;
import com.qa.pages.AssessmentPage;
import com.qa.pages.AssessmentQuestionPage;
import com.qa.pages.AssetPage;
import com.qa.pages.CourseEditorPage;
import com.qa.pages.CreateProjectPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.EbookChapterManagementPage;
import com.qa.pages.EbookManagementPage;
import com.qa.pages.EditNodePopUpPage;
import com.qa.pages.EditTOCPage;
import com.qa.pages.LoginPage;
import com.qa.pages.PatternManagementPage;
import com.qa.pages.ProjectPreviewPage;
import com.qa.pages.TemplateManagementPage;
import com.qa.testdata.NodeType;
import com.qa.testdata.ProjectType;
import com.qa.testdata.QuestionType;
import com.qa.testdata.TaskPriority;
import com.qa.testdata.TaskStatus;
import com.qa.util.CustomHardAssert;
import com.qa.util.CustomSoftAssert;
import com.qa.util.TestUtil;

public class EditTOCPageTest extends TestBase{
	
	LoginPage loginPage;
	CreateProjectPage createProjectPage;
	DashboardPage dashBoardPage;
	EditTOCPage editTocPage;
	AddNodePopupPage addNodePage;
	PatternManagementPage patternManagementPage;
	TemplateManagementPage templateManagementPage;
	CourseEditorPage courseEditorPage;
	AssetPage assetPage;
	EbookManagementPage ebookPage;
	EbookChapterManagementPage ebookChapterPage;
	ProjectPreviewPage projectPreviewPage;
	EditNodePopUpPage editNodePopUpPage;
	AssessmentPage assessmentPage;
	AssessmentQuestionPage assessmentQuestionPage;
	
	private final boolean isEnabled = true;
	private String projectName;
	
	@BeforeMethod(alwaysRun=true)
	public void navigateToTOCPage(Method method) throws Exception {
		loginPage = new LoginPage();
		dashBoardPage = loginPage.ValidLogin(configuration().username(), configuration().password());
		projectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		editTocPage = dashBoardPage.clickOnCreateNewProject().createProject(projectName,
						ProjectType.OnlineCourse.toString(),
						ProjectType.AdaptiveLearning.toString(),
						TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph())
				.clickCreateAndConfigure().clickOnCreateTOC();
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"sanity","CreateNodes","editNodes","createAndUpdateNodes","CreateTemplate","CreatePattern"}, description="Creating and Updating Nodes in project")
	public void createAndUpdateNodes() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		/********** Verifying Folder Node Creation **********/
		String FolderNode = TestUtil.faker.color().name();
		softAssert.assertTrue(editTocPage.clickOnAddNode().createFolderNode(FolderNode,false).clickOnConfirm().getAllNodes().contains(FolderNode), "Folder Node Missing");
		String newFolderNode = TestUtil.faker.color().name()+TestUtil.faker.color().name();
		editTocPage.editNode(NodeType.Folder).updateFolderNode(newFolderNode, true).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newFolderNode), "Folder Node Update Failed!!");
		
		String newModuleNode = TestUtil.faker.color().name()+TestUtil.faker.cat().name();
		editTocPage.editModule().updateFolderNode(newModuleNode, false).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newModuleNode), "Module Node Update Failed!!");
		
		String lessonNodeName = TestUtil.faker.cat().name()+TestUtil.faker.color().name();
		editTocPage.addLessonNode().createFolderNode(lessonNodeName, true).clickOnConfirm();
		softAssert.assertTrue(editTocPage.expandTOCnodes().getAllNodes().contains(lessonNodeName), "Lesson Node Missing!!");
		
		String newLessonNodeName = TestUtil.faker.artist().name()+TestUtil.faker.color().name();
		editTocPage.editLesson().updateFolderNode(newLessonNodeName, false).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newLessonNodeName), "Lesson Node Update Failed!!");
		
		String topicNodeName = TestUtil.faker.cat().name()+TestUtil.faker.artist().name();
		editTocPage.addTopicNode().createFolderNode(topicNodeName, true).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(topicNodeName), "Topic Node Missing!!");
		
		String newTopicNodeName = TestUtil.faker.artist().name()+TestUtil.faker.cat().name();
		editTocPage.editTopic().updateFolderNode(newTopicNodeName, false).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newTopicNodeName), "Topic Node Update Failed!!");
		
		/********** Verifying Content Node Creation **********/
		Thread.sleep(3000);
		String ContentNode = TestUtil.faker.country().name();
		softAssert.assertTrue(editTocPage.clickOnAddNode().createContentScreenNode(ContentNode).clickOnConfirm().getAllNodes().contains(ContentNode), "Content Node Missing");
		String newContentNode = TestUtil.faker.country().name()+TestUtil.faker.country().name();
		editTocPage.editNode(NodeType.ContentScreen).updateContentScreenNode(newContentNode).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newContentNode), "Content Screen Update Failed!!");
		
		/********** Verifying Assessment Node Creation **********/
		String AssessmentNode = TestUtil.faker.animal().name();
		String FirstAssessmentName = TestUtil.faker.lorem().word();
		String SecondAssessmentName = TestUtil.faker.lorem().word();
		String FirstQuestionName = TestUtil.faker.lorem().word();
		String SecondQuestionName = TestUtil.faker.lorem().word();
		Boolean isAssessmentCreated = editTocPage.clickOnAssessment().clickCreateNewAssessment().setAssessmentName(FirstAssessmentName).clickConfirm()
				.clickCreateNewAssessment().setAssessmentName(SecondAssessmentName).clickConfirm()
				.openAssessment(FirstAssessmentName)
				.createQuestion(QuestionType.MCQ).createMCQQuestion(FirstQuestionName).closeSuccessPopUp().clickOnBackToAssessment()
				.openAssessment(SecondAssessmentName)
				.createQuestion(QuestionType.MCQ).createMCQQuestion(SecondQuestionName).closeSuccessPopUp().clickOnBackToToc()
				.clickOnAddNode().createAssessmentNode(AssessmentNode, FirstAssessmentName).clickOnConfirm().getAllNodes().contains(AssessmentNode);
		softAssert.assertTrue(isAssessmentCreated,"Assessment Node Missing");
		/** Disabled edit Assessment due to existing bug EAT-6232 
		String newAssessmentNode = TestUtil.faker.lorem().word()+TestUtil.faker.lorem().word();
		editTocPage.editNode(NodeType.Assessment).updateAssessmentNode(newAssessmentNode, SecondAssessmentName).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newAssessmentNode), "Assessment Node Update Failed!!");
		editNodePopUpPage = editTocPage.editNode(NodeType.Assessment);
		softAssert.assertEquals(editNodePopUpPage.getSelectedAssessment(), SecondAssessmentName , "Updated Assessment name mismatch!!");
		**/
		
		/********** Verifying Assignment Node Creation **********/
		String AssignmentNode = TestUtil.faker.job().position();
		String AssignmentName_1 = TestUtil.faker.job().field();
		String AssignmentName_2 = TestUtil.faker.job().field();
		Boolean isAssignmentCreated = editTocPage.clickOnAssignment().clickCreateNewAssignment().createAssignment(AssignmentName_1).clickOnConfirm()
				.clickCreateNewAssignment().createAssignment(AssignmentName_2).clickOnConfirm()
		.clickOnBackToToc().clickOnAddNode().createAssignmentNode(AssignmentNode, AssignmentName_1)
		.clickOnConfirm().getAllNodes().contains(AssignmentNode);
		softAssert.assertTrue(isAssignmentCreated,"Assignment Node Missing");
		String newAssignmentNode = TestUtil.faker.job().position()+TestUtil.faker.job().position();
		editTocPage.editNode(NodeType.Assignment).updateAssignmentNode(newAssignmentNode, AssignmentName_2).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newAssignmentNode), "Assignment Node Update Failed!!");
		editNodePopUpPage = editTocPage.editNode(NodeType.Assignment);
		softAssert.assertEquals(editNodePopUpPage.getSelectedAssignmentForum(), AssignmentName_2 , "Updated Assignment name mismatch!!");
		editNodePopUpPage.clickOnCancel();
		
		/********** Verifying Forum Node Creation **********/
		String ForumNode = TestUtil.faker.cat().name();
		String ForumName_1 = TestUtil.faker.cat().breed();
		String ForumName_2 = TestUtil.faker.cat().breed();
		Boolean isForumCreated = editTocPage.clickOnForum().clickCreateNewForum().createForum(ForumName_1).clickOnConfirm()
				.clickCreateNewForum().createForum(ForumName_2).clickOnConfirm()
				.clickOnBackToToc().clickOnAddNode().createForumNode(ForumNode, ForumName_1)
				.clickOnConfirm().getAllNodes().contains(ForumNode);
		softAssert.assertTrue(isForumCreated,"Forum Node Missing");
		String newForumNode = TestUtil.faker.cat().name()+TestUtil.faker.cat().name();
		editTocPage.editNode(NodeType.Forum).updateForumNode(newForumNode, ForumName_2).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newForumNode), "Forum Node Update Failed!!");
		editNodePopUpPage = editTocPage.editNode(NodeType.Forum);
		softAssert.assertEquals(editNodePopUpPage.getSelectedAssignmentForum(), ForumName_2 , "Updated Forum name mismatch!!");
		editNodePopUpPage.clickOnCancel();
				
		/********** Verifying Resource-URL Node Creation **********/
		Thread.sleep(3000);
		String ResourceURLNode = TestUtil.faker.rickAndMorty().character();
		String url = "https://google.com";
		Boolean isResourceURLCreated = editTocPage.clickOnAddNode().createResourceNode(ResourceURLNode, resourceType.URL, url).clickOnConfirm().getAllNodes().contains(ResourceURLNode);
		softAssert.assertTrue(isResourceURLCreated,"Resource-URL Node Missing");
		String newResourceURLNode = TestUtil.faker.rickAndMorty().character()+TestUtil.faker.rickAndMorty().character();
		String newUrl = "https://yahoo.com";
		editTocPage.editNode(NodeType.Url).updateResourceNode(newResourceURLNode, resourceType.URL, newUrl).clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(newResourceURLNode), "Resource(URL) Node Update Failed!!");
		editNodePopUpPage = editTocPage.editNode(NodeType.Url);
		softAssert.assertEquals(editNodePopUpPage.getSelectedResourceURL(), newUrl , "Updated Resource URL mismatch!!");
		editNodePopUpPage.clickOnCancel();
		
		/********** Verifying Resource-External Tool Node Creation **********/
		Thread.sleep(3000);
		String ResourceExternalNode = TestUtil.faker.rickAndMorty().character();
		Boolean isResourceExternalCreated = editTocPage.clickOnAddNode().createResourceNode(ResourceExternalNode, resourceType.ExternalTool, url).clickOnConfirm().getAllNodes().contains(ResourceExternalNode);
		softAssert.assertTrue(isResourceExternalCreated,"Resource-External Tool Node Missing");
		
		/********** Verifying Group Activity Node Creation **********/
		String ActivityName = TestUtil.faker.witcher().character();
		String ChildActivityName = TestUtil.faker.witcher().monster();
		addNodePage = editTocPage.clickOnGroupActivity().clickCreateNewGroupActivity().createGroupActivity(ActivityName).openActivity(ActivityName).clickCreateNewChildGroupActivity()
 		 		.createChildGroupActivity(ChildActivityName).clickOnBackToToc().clickOnAddNode().createGroupActivityNode(ActivityName);
		String ListedChildActivity = addNodePage.getListedChildActivities().trim();
		/********** Verifying Child Node Creation **********/
		softAssert.assertEquals(ListedChildActivity,ChildActivityName,"Child Node Not Listed");
 		Boolean isActivityCreated = addNodePage.clickOnConfirm().getAllNodes().contains(ActivityName);
 		softAssert.assertTrue(isActivityCreated,"Groups Activity Tool Node Missing");	
		
		/********** Verifying Pattern Creation **********/
		String PatternName = (TestUtil.faker.lorem().characters(5,10).trim()+TestUtil.faker.lorem().characters(1,8).trim()).toUpperCase();
		String PatternContent = TestUtil.faker.lorem().sentence().trim();
		patternManagementPage = editTocPage.clickOnManagePattern().createNewPattern(PatternName, PatternContent).clickOnConfirm().searchPattern(PatternName);
		List<String> Patterns = patternManagementPage.getListedPatterns();
		softAssert.assertEquals(Patterns.get(0), PatternName, "Pattern is Missing");
		patternManagementPage.clickOnBackToToc();
		
		/********** Verifying Template Creation and Pattern Presence **********/
		String TemplateName = TestUtil.faker.animal().name().trim()+TestUtil.faker.nation().language().trim()+TestUtil.faker.number().randomNumber(4, false);
		courseEditorPage = editTocPage.clickOnManageTemplate().createTemplate(TemplateName).clickOnConfirm();
		List<String> DraggablePatterns = courseEditorPage.getAllPatterns();
		softAssert.assertTrue(DraggablePatterns.contains(PatternName),"Pattern is Missing in Course Editor");
		String ActualContent = courseEditorPage.resetPatternList().AddPatternToEditor(PatternName).getDraggedPatternsContent();
		softAssert.assertEquals(ActualContent, PatternContent,"Pattern Content Mismatch");
		templateManagementPage = courseEditorPage.goBackToTemplateManagement().searchTemplate(TemplateName);
		List<String> Templates = templateManagementPage.getListedTemplates();
		softAssert.assertEquals(Templates.get(0), TemplateName, "Template is Missing");
		
		/********** Verifying Template Update **********/
		String newTemplateName = TestUtil.faker.cat().name().trim()+TestUtil.faker.nation().language().trim()+TestUtil.faker.cat().name().trim();
		templateManagementPage.editTemplate(newTemplateName);
		softAssert.assertEquals(templateManagementPage.getAlertMessage(), "Template has been updated succesfully.", "Template Update Aleart Message mismatch"); 
		List<String> lsitedTemplates = templateManagementPage.closeAlertPopUp().resetSearch().searchTemplate(newTemplateName).getListedTemplates();
		softAssert.assertEquals(lsitedTemplates.get(0), newTemplateName, "Updated Template is Missing");
		templateManagementPage.clickOnBackToToc();
		
		/********** Verifying Pattern Update **********/
		String newPatternName = (TestUtil.faker.lorem().characters(2,7).trim()+TestUtil.faker.lorem().characters(5,9).trim()).toUpperCase();
		String newPatternContent = TestUtil.faker.lorem().sentence().trim()+TestUtil.faker.lorem().sentence().trim();
		editTocPage.clickOnManagePattern().searchPattern(PatternName).updatePattern(newPatternName, newPatternContent).clickOnConfirm().resetSearch().searchPattern(newPatternName);
		List<String> Pattern = patternManagementPage.getListedPatterns();
		softAssert.assertEquals(Pattern.get(0), newPatternName, "Pattern Update Failed!!");
		patternManagementPage.clickOnBackToToc();
		
		/********** Verifying Updated Pattern Content **********/
		String tempTemplate = TestUtil.faker.cat().name().trim()+TestUtil.faker.nation().language().trim()+TestUtil.faker.lorem().characters(2,7).trim();
		courseEditorPage = editTocPage.clickOnManageTemplate().createTemplate(tempTemplate).clickOnConfirm();
		List<String> tempDraggablePatterns = courseEditorPage.getAllPatterns();
		softAssert.assertTrue(tempDraggablePatterns.contains(newPatternName),"Updated Pattern is Missing in Course Editor");
		String tempActualContent = courseEditorPage.resetPatternList().AddPatternToEditor(newPatternName).getDraggedPatternsContent();
		softAssert.assertEquals(tempActualContent, newPatternContent,"Updated Pattern Content Mismatch");
		courseEditorPage.goBackToTemplateManagement().clickOnBackToToc();
		
		
		
		/********** Verifying Duplicate Project Creation **********
		 * Disabling because of delay in copy process which leads to project delete issue
		List<String> NewProjectNodes = editTocPage.duplicateProject().expandTOCnodes().getAllNodes();
		List<String> DuplicateProjectNodes = editTocPage.clickOnMyProjects().openProject("_of_"+projectName).expandTOCnodes().getAllNodes();
		softAssert.assertEquals(DuplicateProjectNodes.size(), NewProjectNodes.size(),"Nodes Mismatch in Duplicate Project");
		editTocPage.clickOnMyProjects().deleteDuplicateProject("_of_"+projectName);
		editTocPage.waitUntilLoading();
		editTocPage.confirmPopUp();*/
		editTocPage.clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"sanity","upload","CreateResourceNode", "createEbook"}, description="Uploading asset and creating resource and ebook node")
	public void uploadAssetandCreateResourceNode() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String assetName = "nonsolus-digital_elsevier.png";
		assetPage = editTocPage.clickOnManageAssets();
		String listedAssett = assetPage.uploadAsset(assetName).getListedAsset().get(0);
		softAssert.assertEquals(listedAssett, "nonsolus-digital-elsevier.png");
		assetPage.clickOnBackToToc();
		
		/********** Verifying Resource-Asset Node Creation **********/
		String ResourceAssetNode = TestUtil.faker.rickAndMorty().character();
		addNodePage = editTocPage.clickOnAddNode();
		Map<String,String> selectedAsset = addNodePage.createResourceNode(ResourceAssetNode, resourceType.FilefromAssetLibrary, assetName).getSelectedAssetDetails();
		softAssert.assertEquals(selectedAsset.get("nonsolus-digital-elsevier.png"), "IMAGE", "AssetType Mismatch");
		editTocPage = addNodePage.clickOnConfirm();
		softAssert.assertTrue(editTocPage.getAllNodes().contains(ResourceAssetNode),"Resource Node with Asset is missing");
		
		/********** Verifying Ebook Node Creation **********/
		String EbookTitle = TestUtil.faker.space().planet().trim();
		String ChapterTitle = TestUtil.faker.space().moon().trim();
		ebookPage = editTocPage.clickOnEbook();
		String listedEbook = ebookPage.createNewEbook(EbookTitle, TestUtil.faker.code().isbn13(), TestUtil.faker.book().author(), TestUtil.faker.lorem().word(), assetName)
				.getListedEbooks().get(0).trim();
		softAssert.assertEquals(listedEbook, EbookTitle, "Ebook Missing");
		ebookChapterPage = ebookPage.openEbook(EbookTitle);
		String listedChapter = ebookChapterPage.createNewChapter(ChapterTitle, "5", TestUtil.faker.number().digit(), TestUtil.faker.number().digit())
				.getListedChapters().get(0).trim();
		softAssert.assertEquals(listedChapter, ChapterTitle, "Ebook Chapter Missing");
		softAssert.assertTrue(ebookChapterPage.clickOnBackToToc().clickOnAddNode().createEbookNode(EbookTitle, ChapterTitle).clickOnConfirm().getAllNodes().contains(EbookTitle),"Ebook Node Missing");
		editTocPage.hardRefresh();
		editTocPage.clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
	
	@Test(priority = 3, enabled = isEnabled, groups = {"sanity","LOR","publish"}, description="Publish project to LOR")
	public void LORPublish() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String FolderNode = TestUtil.faker.color().name();
		String SuccessMessage = editTocPage.clickOnAddNode().createFolderNode(FolderNode,false).clickOnConfirm().publishProject().getAlertMessage();
		Assert.assertEquals(SuccessMessage, "The course has been published. You will receive an email notification once the Publish process has completed. It will be available as a .fcd package in the Learning Object Repository.");
		editTocPage.confirmPopUp().hardRefresh();
		editTocPage.clickOnMyProjects().deleteProject(projectName);
	}
	
	@Test(priority = 4, enabled = isEnabled, groups = {"sanity","LOR","Export"}, description="Export Project")
	public void projectExportTOCSummary() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String FolderNode = TestUtil.faker.color().name();
		String Message = editTocPage.clickOnAddNode().createFolderNode(FolderNode,false).clickOnConfirm().exportTOCSummary().getAlertMessage();
		Assert.assertEquals(Message, "Export Started! The selected course or content is exporting now and you'll get an email with a download link shortly. This may take some time depending on the size of the course.");
		editTocPage.confirmPopUp().hardRefresh();
		editTocPage.clickOnMyProjects().deleteProject(projectName);
	}
	
	@Test(priority = 5, enabled = isEnabled, groups = {"sanity","contentScreenTestView"}, description="Check test mode is displaying text from content screen and All TOC nodes")
	public void contentScreenTestView() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		List<String> TOCNodes = editTocPage.expandTOCnodes().getAllNodes();
		courseEditorPage = editTocPage.openContentScreen();
		String text = TestUtil.faker.lorem().paragraph();
		String testModeEditorText = courseEditorPage.addTextToEditor(text).saveContent().toogleTestMode().getEditorText_TestMode();
		softAssert.assertEquals(testModeEditorText, text, "Content Text Mismatch in Test View");
		Thread.sleep(2000);
		List<String> testViewNodes = courseEditorPage.clickOnTocMenu().getTOCList();
		softAssert.assertEquals(testViewNodes, TOCNodes, "Node Count Mismatch in Test View");
		courseEditorPage.toogleTestMode().goBackToEditToc().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();
	}
	
    @Test(priority = 6, enabled = isEnabled, groups = {"sanity","projectPreview"}, description="Check project preview displaying text from content screen and listing All TOC nodes")
    public void projectPreview() throws Exception {
    	CustomSoftAssert softAssert = new CustomSoftAssert();
		List<String> TOCNodes = editTocPage.expandTOCnodes().getAllNodes();
		projectPreviewPage = editTocPage.clickOnProjectPreview();
		softAssert.assertEquals(projectPreviewPage.getEditorText(), "start creating your content", "Project Preview:  Content Screen text mismatch");
		List<String> projectPreviewNodes = projectPreviewPage.getTOCList();
		softAssert.assertEquals(projectPreviewNodes, TOCNodes, "Node Count Mismatch in project preview");
		softAssert.assertTrue(projectPreviewPage.isEditButtonDisplayed(),"Edit button not displayed");
		
		String updatedText = TestUtil.faker.lorem().paragraph();
		projectPreviewPage = projectPreviewPage.clickOnEdit().addTextToEditor(updatedText).saveContent().goBackToEditToc().confirmPopUp().clickOnProjectPreview();
		softAssert.assertEquals(projectPreviewPage.getEditorText(), updatedText, "Project Preview: Content Screen text mismatch after update!");
		
		projectPreviewPage.clickOnBackToTOC().clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();	
	}
	
	@Test(priority = 7, enabled = isEnabled, groups = {"sanity","importTocPackage"}, description="Verifying TOC import by import CSV package")
	public void importTocPackage() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String tocPackage = "slo-import-to-frost-ch8-test.csv";
		editTocPage.importTOCPackage(tocPackage, true).confirmPopUp().hardRefresh();
		//List<String> totalNodes= editTocPage.expandTOCnodes().getAllNodes();
		softAssert.assertEquals(editTocPage.expandTOCnodes().getAllNodes().size(), 13, "TOC Node count mismatch!");
		editTocPage.clickOnMyProjects().deleteProject(projectName);
		softAssert.assertAll();	
	}
	
	 @Test(priority = 8, enabled = isEnabled, groups = {"verifyAlertsForContentScreens","EditTOCPageTest","Alerts"}, description="Verifying alerts functionality for content screens")	 
	 public void verifyAlertsForContentScreens() throws Exception{
		 CustomSoftAssert softAssert = new CustomSoftAssert();	
		 editTocPage.deleteNode().confirmPopUp();
		 editTocPage.deleteNode().confirmPopUp();
		 String moduleNodeName = TestUtil.faker.color().name()+TestUtil.faker.color().name();
		
		 String lessonNodeName = TestUtil.faker.cat().name()+TestUtil.faker.color().name();
		 String topicNodeName = TestUtil.faker.cat().name()+TestUtil.faker.artist().name();
		 String screenName = TestUtil.faker.cat().name()+TestUtil.faker.ancient().hero();
		 editTocPage.clickOnAddNode().createFolderNode(moduleNodeName,true).clickOnConfirm();
		 editTocPage.addLessonNode().createFolderNode(lessonNodeName, true).clickOnConfirm();
		 editTocPage.expandTOCnodes().addTopicNode().createFolderNode(topicNodeName, true).clickOnConfirm();
		 editTocPage.addNodeUnderTopic().createContentScreenNode(screenName).clickOnConfirm().hardRefresh();;
		 softAssert.assertEquals(editTocPage.expandTOCnodes().getAlertStatus(),"No Open Tasks", "Content screen alert should be inactive!!");
		
		 String TaskName = TestUtil.faker.lorem().paragraph();
		 courseEditorPage = editTocPage.openContentScreen(); 
		 courseEditorPage.selectAllTextFromEditor().openTaskPanel().createTask(TaskName, "frostloradmin frostloradmin, admin", TaskStatus.OPEN, TaskPriority.HIGH, false, TaskName); 
		 editTocPage = courseEditorPage.goBackToEditToc().confirmPopUp();
		 editTocPage.hardRefresh();
		 softAssert.assertEquals(editTocPage.expandTOCnodes().getAlertStatus(),"Alert: Open Task Pending", "Content screen alert should be active!!");
		 
		 courseEditorPage = editTocPage.openContentScreen();
		 courseEditorPage.selectAllTextFromEditor().closeRecentTask();
		 courseEditorPage.createTask(TaskName, "frostloradmin frostloradmin, admin", TaskStatus.VERIFIED, TaskPriority.LOW, false, TaskName); 
		 courseEditorPage.createTask(TaskName, "frostloradmin frostloradmin, admin", TaskStatus.FIXED, TaskPriority.LOW, false, TaskName); 
		 courseEditorPage.createTask(TaskName, "frostloradmin frostloradmin, admin", TaskStatus.IGNORED, TaskPriority.LOW, false, TaskName); 
		 editTocPage = courseEditorPage.goBackToEditToc().confirmPopUp();
		 editTocPage.hardRefresh();
		 softAssert.assertEquals(editTocPage.expandTOCnodes().getAlertStatus(),"No Open Tasks", "Content Screen alert should be inactive after task status was changed!!");
		 
		 editTocPage.openContentScreen().selectAllTextFromEditor().createTask(TaskName, "frostloradmin frostloradmin, admin", TaskStatus.OPEN, TaskPriority.MEDIUM, false, TaskName)
		 	.goBackToEditToc().confirmPopUp().hardRefresh();;
		 softAssert.assertEquals(editTocPage.expandTOCnodes().getAlertStatus(),"Alert: Open Task Pending", "Content Screen alert should be active after new task was added with open status!!");		 
		 
		 editTocPage.openContentScreen().selectAllTextFromEditor().deleteRecentTask().goBackToEditToc().confirmPopUp().hardRefresh();
		 softAssert.assertEquals(editTocPage.expandTOCnodes().getAlertStatus(),"No Open Tasks", "Content screen alert should be inactive after Open task was deleted!!");
		 
		 editTocPage.clickOnProjectPreview().openTaskCommentSection().createTask(TaskName, "frostloradmin frostloradmin", TaskStatus.OPEN, TaskPriority.HIGH, false, TaskName)
		 	.clickOnBackToTOC().confirmPopUp().hardRefresh();;
		 softAssert.assertEquals(editTocPage.expandTOCnodes().getAlertStatus(),"Alert: Open Task Pending", "Content screen alert should be active after new task was added with open status in project preview!!");
		 
		 editTocPage.clickOnMyProjects().deleteProject(projectName);
	     softAssert.assertAll();
	 }
	 
	 
		@Test(priority = 9, enabled = isEnabled, groups = { "verifyAlertsForAssessmentScreens", "EditTOCPageTest","Alerts" }, description = "Verifying alerts functionality for assessment screens")
		public void verifyAlertsForAssessments() throws Exception {
			CustomSoftAssert softAssert = new CustomSoftAssert();
			String inactiveAlert = "color: rgb(128, 128, 128);";
			String activeAlert = "color: rgb(245, 29, 29);";
			String moduleNodeName = TestUtil.faker.color().name() + TestUtil.faker.color().name();
			String lessonNodeName = TestUtil.faker.cat().name() + TestUtil.faker.color().name();
			String topicNodeName = TestUtil.faker.cat().name() + TestUtil.faker.artist().name();
			String AssessmentNode = TestUtil.faker.animal().name();
			String FirstAssessmentName = TestUtil.faker.lorem().word();
			String FirstQuestionName = TestUtil.faker.lorem().word();
			String TaskName = TestUtil.faker.lorem().paragraph();
			
			editTocPage.deleteNode().confirmPopUp().deleteNode().confirmPopUp();	
			editTocPage.clickOnAddNode().createFolderNode(moduleNodeName, true).clickOnConfirm();
			editTocPage.addLessonNode().createFolderNode(lessonNodeName, true).clickOnConfirm();
			editTocPage.expandTOCnodes().addTopicNode().createFolderNode(topicNodeName, true).clickOnConfirm();
			editTocPage.clickOnAssessment().clickCreateNewAssessment().setAssessmentName(FirstAssessmentName)
					.clickConfirm().openAssessment(FirstAssessmentName).createQuestion(QuestionType.MCQ)
					.createMCQQuestion(FirstQuestionName).closeSuccessPopUp().clickOnBackToToc().expandTOCnodes()
					.addNodeUnderTopic().createAssessmentNode(AssessmentNode, FirstAssessmentName).clickOnConfirm();
			softAssert.assertEquals(editTocPage.getAssessmentNodeAlertStatus(), "No Open Tasks","Assessment alert in TOC should be inactive!!");

			assessmentQuestionPage = editTocPage.clickOnEditAssessmentNode();
			softAssert.assertEquals(assessmentQuestionPage.getAlertStatus(), inactiveAlert,"Alert in Assessment page should be inactive!!");
			
			assessmentQuestionPage.clickOnEdit().openTaskPanel().createTask(TaskName,"frostloradmin frostloradmin", TaskStatus.OPEN, TaskPriority.HIGH, false, TaskName).backToAssessmentPage();
			softAssert.assertEquals(assessmentQuestionPage.getAlertStatus(), activeAlert,"Alert in Assessment page should be active!!");

			editTocPage = assessmentQuestionPage.clickOnBackToToc();
			softAssert.assertEquals(editTocPage.expandTOCnodes().getAssessmentNodeAlertColor(),activeAlert ,"Assessment alert in TOC should be active!!");

			assessmentQuestionPage = editTocPage.clickOnEditAssessmentNode().clickOnEdit().closeRecentTask().backToAssessmentPage();
			softAssert.assertEquals(assessmentQuestionPage.getAlertStatus(), inactiveAlert,"Alert in Assessment page should be inactive, task has been fixed!!");

			editTocPage = assessmentQuestionPage.clickOnBackToToc().expandTOCnodes();
			softAssert.assertEquals(editTocPage.getAssessmentNodeAlertColor(),"","Assessment alert in TOC should be inactive, task has been fixed!!");

			// add an open task to an assessment, then create a whole new node and verify that alert is active
			TaskName = TestUtil.faker.lorem().paragraph();
			editTocPage.deleteAssessmentNode().confirmPopUp();
			editTocPage.clickOnAssessment().openAssessment(FirstAssessmentName).clickOnEdit().createTask(TaskName,"frostloradmin frostloradmin", TaskStatus.OPEN, TaskPriority.HIGH, false, TaskName).backToAssessmentPage().clickOnBackToToc();
			AssessmentNode = TestUtil.faker.animal().name();
			editTocPage.expandTOCnodes().addNodeUnderTopic().createAssessmentNode(AssessmentNode, FirstAssessmentName).clickOnConfirm();
			softAssert.assertEquals(editTocPage.getAssessmentNodeAlertStatus(), "Alert: Open Task Pending","Assessment alert in TOC should be active, added assessment with preexisting task!!");
		
			//lessons 
			editTocPage.collapseTOCnodes().deleteNode().confirmPopUp();
			editTocPage.clickOnAddNode().createFolderNode(moduleNodeName, true).clickOnConfirm();
			editTocPage.addLessonNode().createFolderNode(lessonNodeName, true).clickOnConfirm();
			editTocPage.expandTOCnodes();
			editTocPage.addTopicNode().createAssessmentNode(AssessmentNode, FirstAssessmentName).clickOnConfirm();
			softAssert.assertEquals(editTocPage.getAssessmentNodeAlertStatus(), "Alert: Open Task Pending","Lesson - Assessment alert in TOC should be active, added assessment with preexisting task!!");
			
			assessmentQuestionPage = editTocPage.clickOnEditAssessmentNode().clickOnEdit().closeRecentTask().backToAssessmentPage();
			softAssert.assertEquals(assessmentQuestionPage.getAlertStatus(), inactiveAlert,"Lesson - Alert in Assessment page should be inactive, task has been fixed!!");
			
			editTocPage = assessmentQuestionPage.clickOnBackToToc().expandTOCnodes();
			softAssert.assertEquals(editTocPage.getAssessmentNodeAlertColor(), "","Lesson - Assessment alert in TOC should be inactive, task has been fixed!!");
			
			assessmentQuestionPage = editTocPage.clickOnEditAssessmentNode().clickOnEdit().createTask(TaskName,"frostloradmin frostloradmin", TaskStatus.OPEN, TaskPriority.MEDIUM, false,null).backToAssessmentPage();
			softAssert.assertEquals(assessmentQuestionPage.getAlertStatus(), activeAlert,"Lesson - Alert in Assessment page should be active!!");
			
			editTocPage = assessmentQuestionPage.clickOnBackToToc().expandTOCnodes();
			softAssert.assertEquals(editTocPage.getAssessmentNodeAlertColor(), activeAlert,"Lesson - Assessment alert in TOC should be active!!");

			editTocPage.clickOnMyProjects().deleteProject(projectName);
			softAssert.assertAll();
		}
	
	
}
