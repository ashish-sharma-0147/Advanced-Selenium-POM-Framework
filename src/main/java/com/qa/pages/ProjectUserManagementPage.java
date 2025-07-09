package com.qa.pages;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.pages.common.CommonPage;
import com.qa.testdata.UserRoles;

public class ProjectUserManagementPage extends CommonPage{

	@FindBy(xpath="//div[@class='pull-left']")
	private WebElement PageTitle;
	
	@FindBy(xpath="//button[normalize-space()='Back to TOC']")
	private WebElement Button_BackToToc;
	
	@FindBy(xpath="//button[normalize-space()='Add Users']")
	private WebElement Button_AddUsers;
	
	@FindBy(xpath="//button[normalize-space()='Delete Users']")
	private WebElement Button_DeleteUsers;
	
	@FindBy(id="checkAllUser")
	private WebElement CheckBox_CheckAll;
	
	@FindBy(xpath="//button[normalize-space()='Yes, delete it!']")
	private WebElement PopUp_Button_YesDelete;
	
	@FindBy(xpath="//button[normalize-space()='OK']")
	private WebElement PopUp_Button_Ok;
	
	@FindBy(name="search_text")
	private WebElement Popup_TextBox_SearchTerm;
	
	@FindBy(xpath="//select[@ng-model='user.role']")
	private WebElement Popup_SelectList_SelectRole;
	
	@FindBy(xpath="//a[normalize-space()='CANCEL']")
	private WebElement Popup_Button_Cancel;
	
	@FindBy(xpath="//button[normalize-space()='CONFIRM']")
	private WebElement Popup_Button_Confirm;
	
	@FindBy(xpath="//div[@ng-show='message']")
	private WebElement AlertMessage;
	
	@FindBy(xpath="//table[@id='tagTableId']/thead/tr/th")
	private List<WebElement> List_UserTableHeaders;
	
	@FindBy(xpath="//table[@id='tagTableId']/tbody/tr")
	private List<WebElement> List_UserTableRows;
	
	public ProjectUserManagementPage() {
		waitUntilLoading();
	}
	
	public String getPageTitle() throws Exception {
		return getText(PageTitle);
	}
	
	public EditTOCPage clickOnBackToToc() throws Exception {
		click(Button_BackToToc);
		return new EditTOCPage();
	}
	
	public ProjectUserManagementPage addUser(String searchTerm, UserRoles role) throws Exception {
		click(Button_AddUsers);
		waitUntilLoading();
		sendKeys(Popup_TextBox_SearchTerm, searchTerm);
		sendKeys(Popup_TextBox_SearchTerm, Keys.RETURN);
		waitUntilLoading();
		selectByValue(Popup_SelectList_SelectRole, role.toString());
		click(Popup_Button_Confirm);
		waitUntilLoading();
		return this;
	}
	
	public ProjectUserManagementPage deleteAllAssociatedUsers() throws Exception {
        click(CheckBox_CheckAll);
        click(Button_DeleteUsers);
        Thread.sleep(2000);
        click(PopUp_Button_YesDelete);
        waitUntilLoading();
        click(PopUp_Button_Ok);
        return this;
	}
       
	public String getAlertMessage() throws Exception {
		return getText(AlertMessage);
	}
	
	/**
	 * This method currently not returning user list because of current DOM structure. Need further work here.
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private List<LinkedHashMap<String, String>> getUserList() throws Exception {
		return getTableDataAsList(List_UserTableHeaders,List_UserTableRows,getElementLocator(List_UserTableRows.get(0)).get("xpath"));
	}
}
