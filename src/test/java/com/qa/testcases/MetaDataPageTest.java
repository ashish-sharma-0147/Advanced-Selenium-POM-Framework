package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;
import static com.qa.util.TestUtil.faker;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AddNodePopupPage;
import com.qa.pages.DashboardPage;
import com.qa.pages.EditTOCPage;
import com.qa.pages.LoginPage;
import com.qa.pages.MetaDataPage;
import com.qa.testdata.KeyValuePair;
import com.qa.testdata.ProjectType;
import com.qa.util.CustomHardAssert;
import com.qa.util.CustomSoftAssert;
import com.qa.util.TestUtil;

public class MetaDataPageTest extends TestBase{

	LoginPage loginPage;
	MetaDataPage metaDataPage;
	EditTOCPage editTocPage;
	AddNodePopupPage addNodePopUp;
	DashboardPage dashboardPage;

	private final boolean isEnabled = true;

	@BeforeMethod(alwaysRun=true)
	public void navigateToMetaData(Method method) throws Exception {
		loginPage = new LoginPage();
		metaDataPage = loginPage.ValidLogin(configuration().username(), configuration().password()).clickOnMetaData();
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"sanity","Metadata","createLinkDeleteTaxonomy"}, description="Creating New Taxonomy and subTaxonomy,link it to Project Node and Deleting Taxonomy")
	public void createLinkDeleteTaxonomy() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String Taxonomy, subTaxonomy;
		int usageCount;
		String Statement = faker.zelda().character().trim()+faker.cat().name().trim();
		String Description = faker.lorem().sentence();
		String UserFacingId = faker.lorem().fixedString(13)+faker.color().name();
		String VTWId = (faker.lorem().fixedString(13)+faker.color().name()).replace(" ", "").toUpperCase();
		Taxonomy = VTWId;
		Boolean isPresent = metaDataPage.clickOnCreateNewTaxonomy().createTaxonomy(Statement, Description, UserFacingId, VTWId).isTaxonomyPresent(Taxonomy);
		Assert.assertTrue(isPresent, "Taxonomy Not Listed");
		
		String subStatement = faker.animal().name().trim()+faker.cat().name().trim();
		String subDescription = faker.lorem().sentence();
		String subUserFacingId = faker.lorem().fixedString(13)+faker.color().name();
		String subVTWId = (faker.lorem().fixedString(13)+faker.color().name()).replace(" ", "").toUpperCase();
		subTaxonomy = subStatement;
		Boolean isSubPresent = metaDataPage.openTaxonomyDropDown(Taxonomy).clickOnAdd().createTaxonomy(subStatement, subDescription, subUserFacingId, subVTWId).isTaxonomyPresent(subTaxonomy);
		Assert.assertTrue(isSubPresent, "subTaxonomy Not Listed");
		
		usageCount = metaDataPage.getUsageCount(subTaxonomy);
		softAssert.assertEquals(usageCount, 0, "Initial Usage count is not 0");
		
		String ProjectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		editTocPage = metaDataPage.clickOnMyProjects().clickOnCreateNewProject().createProject(ProjectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.AdaptiveLearning.toString(),
				TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateAndConfigure().clickOnCreateTOC();
		addNodePopUp = editTocPage.clickOnAddNode();
		boolean isLinked = addNodePopUp.openMetaDataTab().linkTaxonomyToNode(subTaxonomy).isTaxonomyLinked(subTaxonomy);
		softAssert.assertTrue(isLinked, "Taxonomy Not Linked");
		
		String FolderNode = TestUtil.faker.color().name();
		softAssert.assertTrue(addNodePopUp.openDetailsTab().createFolderNode(FolderNode, false).clickOnConfirm().getAllNodes().contains(FolderNode), "Folder Node With Linked Taxonomy is Missing");
		
		metaDataPage = editTocPage.clickOnMetaData();
		int currentUsageCount = metaDataPage.expandTaxonomy(Taxonomy).getUsageCount(subTaxonomy);
		softAssert.assertEquals(currentUsageCount, 1, "Current Taxonomy Usage Count Mismatch");
		
		dashboardPage = metaDataPage.clickOnMyProjects();
		dashboardPage.deleteProject(ProjectName);
		dashboardPage.confirmPopUp();
		
		String message = dashboardPage.clickOnMetaData().openTaxonomyDropDown(Taxonomy).deleteTaxonomy();
		softAssert.assertEquals(message, "Taxonomy has been deleted");
		dashboardPage.confirmPopUp();
		softAssert.assertAll();
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"sanity","Metadata","createLinkDeleteTag"},  description="Create, Link and Delete new Tag")
	public void createLinkDeleteTag() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		CustomSoftAssert softAssert = new CustomSoftAssert();
		final String tagTitle = (faker.color().name()+faker.country().name()).trim();
		String ListedTag = metaDataPage.openTagTab().createTag(tagTitle).searchTag(tagTitle).getListedTagNames();
		Assert.assertEquals(ListedTag, tagTitle, "Created Tag not listed");
		
		int initialUsage = metaDataPage.getListedTagUsageCount();
		softAssert.assertEquals(initialUsage, 0, "Initial Usage count mismatch");
		
		String ProjectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		editTocPage = metaDataPage.clickOnMyProjects().clickOnCreateNewProject().createProject(ProjectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.AdaptiveLearning.toString(),
				TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateAndConfigure().clickOnCreateTOC();
		addNodePopUp = editTocPage.clickOnAddNode();
		boolean isLinked = addNodePopUp.openMetaDataTab().linkTagToNode(tagTitle).isTagLinked(tagTitle);
		softAssert.assertTrue(isLinked, "Tag Not Linked");
		
		String FolderNode = TestUtil.faker.color().name();
		softAssert.assertTrue(addNodePopUp.openDetailsTab().createFolderNode(FolderNode, false).clickOnConfirm().getAllNodes().contains(FolderNode), "Folder Node With Linked Tag is Missing");
		
		metaDataPage = editTocPage.clickOnMetaData();
		int currentUsageCount = metaDataPage.openTagTab().searchTag(tagTitle).getListedTagUsageCount();
		softAssert.assertEquals(currentUsageCount, 1, "Current Tag Usage Count Mismatch");

		dashboardPage = metaDataPage.clickOnMyProjects();
		dashboardPage.deleteProject(ProjectName);
		dashboardPage.confirmPopUp();
		String message = dashboardPage.clickOnMetaData().openTagTab().searchTag(tagTitle).deleteTag();
		softAssert.assertEquals(message, "Tag has been deleted");
		dashboardPage.confirmPopUp();
		softAssert.assertAll();
		
	}
	
	@Test(priority = 3, enabled = isEnabled, groups = {"sanity","Metadata","createLinkDeleteKeyValuePair"},  description="Create, Link and Delete KeyValue Pair")
	public void createLinkDeleteKeyValuePair() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		CustomSoftAssert softAssert = new CustomSoftAssert();
		final String key = (faker.color().name()+faker.superhero().name()).trim();
		String ListedKey = metaDataPage.openKeyValueTab().createKeyValuePair(key, KeyValuePair.NUMBER, faker.lorem().words(3)).searchKeyValuePair(key).getListedKeyValue();
		Assert.assertEquals(ListedKey, key, "Key not Found");
		
		int initialUsage = metaDataPage.getListedKeyValueUsageCount();
		softAssert.assertEquals(initialUsage, 0, "Initial Usage count mismatch");
		
		String ProjectName = TestUtil.faker.animal().name()+TestUtil.faker.superhero().name()+TestUtil.faker.music().genre();
		editTocPage = metaDataPage.clickOnMyProjects().clickOnCreateNewProject().createProject(ProjectName,
				ProjectType.OnlineCourse.toString(),
				ProjectType.AdaptiveLearning.toString(),
				TestUtil.faker.code().isbn13(),TestUtil.faker.book().author(), TestUtil.faker.book().genre(),TestUtil.faker.lorem().paragraph()).clickCreateAndConfigure().clickOnCreateTOC();
		addNodePopUp = editTocPage.clickOnAddNode();
		boolean isLinked = addNodePopUp.openMetaDataTab().linkKeyValueToNode(KeyValuePair.NUMBER,key).isKeyLinked(key);
		softAssert.assertTrue(isLinked, "key Not Linked");
		
		String FolderNode = TestUtil.faker.color().name();
		softAssert.assertTrue(addNodePopUp.openDetailsTab().createFolderNode(FolderNode, false).clickOnConfirm().getAllNodes().contains(FolderNode), "Folder Node With Linked Key Value is Missing");
		
		metaDataPage = editTocPage.clickOnMetaData();
		int currentUsageCount = metaDataPage.openKeyValueTab().searchKeyValuePair(key).getListedKeyValueUsageCount();
		softAssert.assertEquals(currentUsageCount, 1, "Current Key Usage Count Mismatch");

		dashboardPage = metaDataPage.clickOnMyProjects();
		dashboardPage.deleteProject(ProjectName);
		dashboardPage.confirmPopUp();
		
		String message = dashboardPage.clickOnMetaData().openKeyValueTab().searchKeyValuePair(key).deleteKeyValue();
		softAssert.assertEquals(message, "Key has been deleted");
		dashboardPage.confirmPopUp();
		softAssert.assertAll();
	}

}
