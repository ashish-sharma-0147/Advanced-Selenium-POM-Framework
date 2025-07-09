package com.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.qa.pages.common.CommonPage_Admin;
import com.qa.util.BulkUploadCsvCreator;
import com.qa.util.TestUtil;

import io.github.sukgu.support.FindElementBy;

public class AdministrationBulkUploadPage extends CommonPage_Admin {
	
	@FindElementBy(css="#n-up-csv")
	private WebElement Button_UploadUserCsv;
	
	@FindElementBy(xpath="label[for='btnUpload']")
	private WebElement Button_Browse;
	
	@FindElementBy(css="#btnSaveUploaded")
	private WebElement Button_FinalUpload;
	
	@FindElementBy(xpath="//button[@class='btn btn-secondary']")
	private WebElement Button_Cancel;

	/**
	 * This is test method. 
	 * @throws IOException 
	 */
	public void addDataToCsv() throws IOException {
		BulkUploadCsvCreator.createTemplate(TestUtil.getRandomEmail("yopmail"), TestUtil.faker.name().username(),
				TestUtil.faker.name().firstName(), TestUtil.faker.name().lastName(), "Authoring");
		
	}
}
