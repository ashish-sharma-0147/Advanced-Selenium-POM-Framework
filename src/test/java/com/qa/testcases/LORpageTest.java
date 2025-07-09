package com.qa.testcases;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.LearningObjectRepositoryPage;
import com.qa.pages.LoginPage;
import com.qa.testdata.ListPerPage;
import com.qa.util.CustomHardAssert;
import com.qa.util.CustomSoftAssert;

public class LORpageTest extends TestBase {
	
	LoginPage loginPage;
	LearningObjectRepositoryPage lorPage;
	
	private final boolean isEnabled = true;
	
	@BeforeMethod(alwaysRun=true)
	public void navigateToLORPage(Method method) throws Exception {
		loginPage = new LoginPage();
		lorPage = loginPage.ValidLogin(configuration().username(), configuration().password()).clickOnLOR();
				
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"sanity","LOR"}, description="Verifying the Thumbnail Image Links")
	public void verifyThumbnails() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		List<String> brokenImages = lorPage.setItemsPerPage(ListPerPage.TwentyFive).getListOfBrokenImages();
		Assert.assertFalse(brokenImages.size() > 0, brokenImages.toString());
	}
	
	@Test(priority = 2, enabled = false, groups = {"sanity","LOR","upload"}, description="Upload file into LOR")
	public void uploadFileTest() throws Exception {
		CustomSoftAssert softAssert = new CustomSoftAssert();
		String uploadSuccess = lorPage.uploadFile("nonsolus-digital_elsevier.png").getAlertMessage();
		softAssert.assertEquals(uploadSuccess, "1 file(s) uploaded successfully", "Content Upload message mismatch");
		
		List<String> listedContent = lorPage.searchRepository("nonsolus-digital_elsevier.png").getListedContents();
		softAssert.assertEquals(listedContent.size(), 1, "Error!! More than one content listed");
		softAssert.assertEquals(listedContent.get(0), "nonsolus-digital_elsevier.png", "Uploaded Content file name mismatch");
		
		String deleteSuccess = lorPage.deleteContent().getAlertMessage();
		softAssert.assertEquals(deleteSuccess, "user file has been deleted.", "Content Delete alert text mismatch");
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = false, groups = {"sanity","LOR"}, description="Preview LOR Content")
	public void contentPreviewTest() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		/** Need further work on designing this test case, Disabling for now*/
		String Message = lorPage.previewContent().getLORPreviewMessage();
		Boolean isEquals = Message.contains("Preview unavailable")||Message.contains("");
		Assert.assertFalse(isEquals, "Lor preview failed");
	}
}
