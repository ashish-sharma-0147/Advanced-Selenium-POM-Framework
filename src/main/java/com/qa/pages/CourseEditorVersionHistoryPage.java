package com.qa.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.driver.DriverManager;
import com.qa.pages.common.CommonPage;

public class CourseEditorVersionHistoryPage extends CommonPage{
	
	@FindBy(className="loading-g")
	private WebElement Loader;
	
	@FindBy(xpath="//button[normalize-space()='close']")
	private WebElement Button_Close;
	
	@FindBy(tagName = "del")
	private WebElement Text_Previous;
	
	@FindBy(tagName = "ins")
	private WebElement Text_Current;
	
	@FindBy(id="verAccordion")
	private WebElement Text_Version;
	
	@FindBy(id="datespan")
	private WebElement Text_Datespan;
	
	@FindBy(id="versionSpan")
	private WebElement Text_Versionspan;
	
	@FindBy(className = "Vrestore")
	private WebElement Button_RestoreVersion;
	
	@FindBy(className = "confirm")
	private WebElement Button_ConfirmRestore;
	
	@FindBy(className = "chat-icon")
	private WebElement Button_Notes;
	
	@FindBy(className = "chatText")
	private WebElement Text_Notes;
	
	
	public CourseEditorVersionHistoryPage() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(Loader));
		wait.until(ExpectedConditions.invisibilityOf(Loader));
	}

	public CourseEditorPage closeVersionHistoryPage() throws Exception{
		//explicitlyWait(Button_Close);
		click(Button_Close);
		return new CourseEditorPage();
	}
	
	public String getPreviousContent() throws Exception {
		return getText(Text_Previous);
	}
	
	public String getCurrentContent() throws Exception {
		return getText(Text_Current);
	}
	
	public String getTitleVersion() throws Exception{
		String versionText = getText(Text_Versionspan);
		return versionText.substring(0, versionText.indexOf("-")).trim();
	}
	
	public String getTitleDate() throws Exception {
		String dateFormat = "MMMM dd, yyyy";
		String datespan =getText(Text_Datespan);
		String date = "NotSet";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
        	date = formatter.format(formatter.parse(datespan));
        } catch (Exception e) {
            e.printStackTrace();
        }
		return date;
	}
	
	
	//This function only works in testing conditions where there have been 
	public String[] parseVersionText() throws Exception {
		String[] VersionText = getText(Text_Version).split("\n");
		String dateFormat = "MMMM dd, yyyy";
		String date = "NotSet";
		for(int i=0; i< (VersionText.length/3); i++) {
			
			//get a formatted date
				String datePart = VersionText[i*3].substring(VersionText[i*3].indexOf("-") + 2, VersionText[i*3].lastIndexOf(" ")).trim();
				SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		        try {
		        	date = formatter.format(formatter.parse(datePart));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		        //set the correct parts of the array
				VersionText[i*3 + 1] = date;
				VersionText[i*3] = VersionText[i*3].substring(0, 9);
		}
		return VersionText;
	}
	
	public String getCurrentDate() throws Exception{
		Date currentDate = new Date();
		String dateFormat = "MMMM dd, yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		//DateFormat formatterUTC = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(currentDate);
	}
	
	public boolean isNotesIconDisplayed() throws Exception {
		return isDisplayed(Button_Notes);
	}
	
	public ArrayList<String> getVersionNotes(int version) throws Exception{
		try {
		String buttonXpath = "//h4[contains(text(),'Version "+version+"')]/following-sibling::p/child::span";
		String notesXpath = "//h4[contains(text(),'Version "+version+"')]/following-sibling::div//span";
		DriverManager.getInstance().getDriver().findElement(By.xpath(buttonXpath)).click();
		return getText(DriverManager.getInstance().getDriver().findElements(By.xpath(notesXpath)));
		}
		catch(NoSuchElementException e) {
            return new ArrayList<String>();
        }
	}
	
	public CourseEditorPage clickOnRestoreVersionButton() throws Exception {
		click(Button_RestoreVersion);
		Thread.sleep(2000);
		click(Button_ConfirmRestore);
		return new CourseEditorPage();
	}
	

}
