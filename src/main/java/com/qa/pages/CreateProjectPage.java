package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Optional;

import com.qa.pages.common.CommonPage;
import com.qa.util.TestUtil;

import io.github.sukgu.support.FindElementBy;

public class CreateProjectPage extends CommonPage{
	
	@FindBy(xpath="//h2[contains(@class,'page-heading')]")
	private WebElement Text_pageHeading;
	
	@FindBy(xpath="//button[text()='Create Project']/preceding-sibling::button[text()='Cancel']")
	private WebElement Button_Cancel;
	
	@FindBy(xpath="//button[text()='Create Project']")
	private WebElement Button_CreateNewProject;
	
	@FindBy(xpath="//button[text()='Create & Configure']")
	private WebElement Button_CreateAndConfigure;
	
	@FindBy(id="ProjectName")
	private WebElement TextField_ProjectName;
	
	@FindBy(id="projectType")
	private WebElement SelectList_projectType;
	
	@FindBy(id="projectSubType")
	private WebElement SelectList_OnlineCourse_projectSubType;
	
	@FindBy(xpath="//div[contains(@class,'theme-block')]")
	private WebElement Div_SelectTheme_ThemeBlock;
	
	@FindBy(name="themeType")
	private WebElement SelectList_EDUPUB_theme;
	
	@FindBy(id="isbnid")
	private WebElement TextField_ISBN;
	
	@FindBy(name="publicationDate")
	private WebElement TextField_EDUPUB_publicationDate;
	
	@FindBy(name="objectAuthor")
	private WebElement TextField_Author;
	
	@FindBy(xpath="//input[@aria-label='Discipline']")
	private WebElement TextField_Discipline;
	
	@FindBy(id="objectDescription")
	private WebElement TextField_Description;
	
	@FindBy(xpath="//input[@type='file' and @name='projectCover']")
	private WebElement FileInputField_Cover;
	
    @FindBy(css = ".btn.btn-primary.ng-binding")
    private WebElement Button_Save_Changes;

    @FindBy(css = "button.btn.btn-default[onclick=\"javascript:history.back(-1);\"][ng-show=\"project.project_permissions['project.update'].grant\"]")
    private WebElement Button_Cancel_Changes;

    @FindBy(id = "customProcess")
    private WebElement ToC_Btn;

    @FindBy(xpath ="//div[@id='theme6']")
    private WebElement Theme_Content;
	/* Handling Shadow Elements */
	/*@FindBy(tagName="custom-metadata-picker")
	private WebElement ShadowHost;
	
	@SuppressWarnings("unchecked")
	private List<SearchContext> ShadowRoot = (List<SearchContext>)(TestUtil.jsDriver.executeScript("return arguments[0].shadowRoot.children", ShadowHost));
	
	private WebElement shadowElement_DropDown_SelectKey = ShadowRoot.get(4).findElement(By.cssSelector("div[class='ui-select-match']"));;
	
	private List<WebElement> shadowElement_DropDown_SelectKey_List = ShadowRoot.get(4).findElements(By.cssSelector("#keyValuePairTab > div.form-row > div:nth-child(1) > ng-select > div > ul > li > div > a > div"));
	
	private WebElement vaule = ShadowRoot.get(4).findElement(By.cssSelector("div:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ng-select:nth-child(1) > div:nth-child(1) > ul:nth-child(3) > li:nth-child(3) > div:nth-child(1) > a:nth-child(1) > div:nth-child(1)"));
	*/
	@FindElementBy(css="a[id='keyValuePairTab']")
	private WebElement shadow_Metadata_Tab_KeyValuePair;
	
	@FindElementBy(css="a[id='taxonomyTab']")
	private WebElement shadow_Metadata_Tab_Taxonomy;
	
	@FindElementBy(css="a[id='tagsTab']")
	private WebElement shadow_Metadata_Tab_Tags;
	
	@FindElementBy(css="div[class='ui-select-match']")
	private WebElement shadow_MetaData_KeyValuePair_SelectKey;
	
	@FindElementBy(css="a[class='dropdown-item']")
	private List<WebElement> shadow_MetaData_KeyValuePair_SelectKey_List;
	
	@FindElementBy(css="a[id='addanotherbutton']")
	private WebElement shadow_MetaData_AddAnotherKeyValuePair;
	
	@FindElementBy(css="a[id='searchTaxonomyComponentBtn']")
	private WebElement shadow_Metadata_Taxonomy_Link_AddTaxonomy;
	
	@FindElementBy(css="div[role='tablist'] > a[role='tab']")
	private List<WebElement> shadow_Metadata_Taxonomy_Tabs;
	
	@FindElementBy(css="input[name='searchValue']")
	private WebElement shadow_Metadata_Taxonomy_TextBox_Search;
	
	@FindElementBy(css="custom-list:nth-child(1) > div[class='list-group list-group-flush'] > custom-list-item>div[class='list-group-item']>div[class='form-check'] > label")
	private	List<WebElement> shadow_Metadata_Taxonomy_List;
	
	public CreateProjectPage() {
		waitUntilLoading();
	}
	public String getPageHeading() throws Exception {
		return getText(Text_pageHeading);
	}
	
	public CreateProjectPage createProject(String ProjectName, String ProjectType,@Optional String ProjectSubType,@Optional String ISBN, String Author, String Discipline, String Description) throws Exception {
		setProjectName(ProjectName);
		setProjectType(ProjectType);
		if(ProjectType.equals("Online Course")) {
			setProjectSubType(ProjectSubType);
			setThemeCard();
		}
		if(ProjectType.equals("EDUPUB")) {
			setThemeDropdown("Learningmate Theme 1");
			setPublicationDate(TestUtil.getRandomDate("MM/dd/yyyy"));
		}
		if(ProjectType.equals("EDUPUB") || ProjectSubType.equals("Adaptive Learning"))
			setISBN(ISBN);
		setAuthor(Author);
		setDiscipline(Discipline);
		setDescription(Description);
		setCover(System.getProperty("user.dir")+TestUtil.updateFilePathSeparatorBasedOnOS("/src/main/resources/nonsolus-digital_elsevier.png"));
		return this;
	}
	
	public void setProjectName(String ProjectName) throws Exception {
		sendKeys(TextField_ProjectName, ProjectName);
	}
	
	public void setProjectType(String ProjectType) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(SelectList_projectType));
		selectByText(SelectList_projectType,ProjectType);
	}
	
	public void setProjectSubType(String ProjectSubType) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(SelectList_OnlineCourse_projectSubType));
		selectByText(SelectList_OnlineCourse_projectSubType,ProjectSubType);
	}
	
	public void setThemeDropdown(String ThemeName) throws Exception {
		selectByText(SelectList_EDUPUB_theme,ThemeName);
	}
	
	public void setISBN(String ISBNNumber) throws Exception{
		sendKeys(TextField_ISBN, ISBNNumber);
	}
	
	public void setAuthor(String AuthorName) throws Exception {
		sendKeys(TextField_Author, AuthorName);
	}
	
	public void setDiscipline(String Discipline) throws Exception{
		sendKeys(TextField_Discipline, Discipline);
	}
	
	public void setDescription(String Description) throws Exception {
		sendKeys(TextField_Description, Description);
	}
	
	public void setCover(String FilePath) throws Exception {
		sendKeys(FileInputField_Cover,FilePath);
	}
	
	public void setPublicationDate(String Date) throws Exception {
		sendKeys(TextField_EDUPUB_publicationDate, Date);
	}
	
	public void setThemeCard() throws Exception {
		click(Div_SelectTheme_ThemeBlock);
	}
	
	public DashboardPage clickCreateProject() throws Exception {
		click(Button_CreateNewProject);
		return new DashboardPage();
	}
	
	public ProjectImportPage clickCreateAndConfigure() throws Exception {
		click(Button_CreateAndConfigure);
		return new ProjectImportPage();
	}

     // Getter method for project name
     public String getProjectName() {
        return TextField_ProjectName.getAttribute("value");
    }

    // Getter method for author
    public String getAuthor() {
        return TextField_Author.getAttribute("value");
    }

    // Getter method for discipline
    public String getDiscipline() {
        return TextField_Discipline.getAttribute("value");
    }

    // Getter method for description
    public String getDescription() {
        return TextField_Description.getAttribute("value");
    }

    public void editProjectDetails(String newProjectName, String newAuthor, String newDiscipline, String newDescription) throws Exception {
        waitUntilLoading();
        TextField_ProjectName.clear();
        TextField_Author.clear();
        TextField_Discipline.clear();
        TextField_Description.clear();
        setProjectName(newProjectName);
        setAuthor(newAuthor);
        setDiscipline(newDiscipline);
        setDescription(newDescription);
        click(Button_Save_Changes);
        wait.until(ExpectedConditions.visibilityOf(ToC_Btn));
        clickOnMyProjects();
    }

    public void returnToDashboard() throws Exception {
        click(Button_Cancel_Changes);
    }

    public void waitForProjectPageToLoad() throws Exception {
        wait.until(ExpectedConditions.visibilityOf(Theme_Content));
    }
	
}
