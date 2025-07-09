package com.qa.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.driver.DriverManager;
import com.qa.listeners.TestListener;

import io.github.sukgu.Shadow;

public class DriverActions extends TestListener {

	protected WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(),Duration.ofSeconds(60));
	protected JavascriptExecutor jsDriver = (JavascriptExecutor) DriverManager.getInstance().getDriver();
	protected Actions ActionHandler = new Actions(DriverManager.getInstance().getDriver());
	protected Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(DriverManager.getInstance().getDriver()).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

	/** Switching Frame by frame name or id 
	 * @throws Exception */
	protected void switchToFrame(String frameNameOrId) throws Exception {
		try {
			stepDetails("Switching to frame: ' "+frameNameOrId+" '");
			DriverManager.getInstance().getDriver().switchTo().frame(frameNameOrId);
		} catch(Throwable e) {
			throw new Exception("Frame with Id or name ' " + frameNameOrId +" ' Not Found " + e.getCause());
		}
	}

	/** Switching Frame by frame Element 
	 * @throws Exception */
	protected void switchToFrame(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Switching to frame: ' "+elementStringifier(element)+" '");
			DriverManager.getInstance().getDriver().switchTo().frame(element);
		} catch(Throwable e) {
			throw new Exception("Frame Not Found " + e);
		}
	}

	/** Switching to Default Content 
	 * @throws Exception */
	protected void switchToDefaultContent() throws Exception {
		try {
			stepDetails("Switching to Default Content");
			DriverManager.getInstance().getDriver().switchTo().defaultContent();
		} catch(Throwable e) {
			throw new Exception(e);
		}
	}
	
	/** Switching to Parent Frame 
	 * @throws Exception */
	protected void switchToParentFrame() throws Exception {
		try {
			stepDetails("Switching to Parent Frame");
			DriverManager.getInstance().getDriver().switchTo().parentFrame();
		} catch(Throwable e) {
			throw new Exception(e);
		}
	}

	/** Explicit Wait for Visibility. 
	 * @throws Exception */
	protected void explicitlyWait(WebElement element) throws Exception {
		try {
			stepDetails("Waiting for ' " + elementStringifier(element) + " ' to be available.");
			WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			throw new Exception("Not able to find element ' " + elementStringifier(element) + " '. Reason: " + e.getCause());
		}
	}

	/** Wait,highlight and Click Element 
	 * @throws Exception */
	protected void click(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Clicking on ' "+elementStringifier(element)+" '");
			//Highlighter.highLightElement(element);
			element.click();
		}catch (Exception e) {
			throw new Exception("Element " + element + " is not clickable because: " + e);
		}
	}

	/** Wait,highlight and Clear Element 
	 * @throws Exception */
	protected void clear(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Removing Text from ' "+elementStringifier(element)+" '");
			//Highlighter.highLightElement(element);
			element.clear();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	/** Method to clear value from textBox using attribute property 
	 * @throws Exception */
	protected void clearFieldByAttribute(WebElement element, String Attribute_Value) throws Exception{
		try {
			stepDetails("Removing Text from attribute ' "+Attribute_Value+" ' of element ' "+elementStringifier(element)+" '");
			while(!element.getAttribute(Attribute_Value).equals("")){
				element.sendKeys(Keys.BACK_SPACE);
			}
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	/** Wait, Highlight and SendKey Text. 
	 * @throws Exception */
	protected void sendKeys(WebElement element, String value) throws Exception {
		try {
			explicitlyWait(element);
			if(Thread.currentThread().getStackTrace()[2].getMethodName().equals("ValidLogin")) {
				stepDetails("Typing <'"+ maskSensitiveInfo(value.replaceAll("<'(.*?)'>", value))+"'> in field ' "+elementStringifier(element)+" '");
		        
			}else{
				stepDetails("Typing <'"+ value.toString() +"'> in field ' "+elementStringifier(element)+" '");	
			}
			
			//Highlighter.highLightElement(element);
			element.sendKeys(value);
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}

	private String maskSensitiveInfo(String value) {
	    // Replace all characters with asterisks
	    return value.replaceAll(".", "*");
	}

	/** Wait, Highlight and SendKey Keyboard.KEYS. 
	 * @throws Exception */
	protected void sendKeys(WebElement element, Keys key) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Pressing Key '"+key.toString()+"' in field '"+elementStringifier(element)+"'");
			//Highlighter.highLightElement(element);
			element.sendKeys(key);
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}
	/**
	 * Typing value in shadow textbox
	 * @param shadowRoot
	 * @param cssSelector
	 * @param value
	 * @throws Exception
	 */
	protected void sendKeys(String shadowRoot, String cssSelector, String value) throws Exception{
		try {
			stepDetails("Typing <'"+ value.toString() +"'> in SHADOW field ' "+cssSelector+" '");
			jsDriver.executeScript("document.querySelector('"+shadowRoot+"').shadowRoot.querySelector('"+cssSelector+"').value='"+value+"'");
			jsDriver.executeScript("document.querySelector('"+shadowRoot+"').shadowRoot.querySelector('"+cssSelector+"').dispatchEvent(new Event('input',{'bubbles':true}))");
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Typing value in nested shadow TextBox
	 * @param parentShadowRoot
	 * @param childShadowRoot
	 * @param cssSelector
	 * @param value
	 * @throws Exception
	 */
	protected void sendKeys(String parentShadowRoot, String childShadowRoot, String cssSelector, String value) throws Exception{
		try {
			stepDetails("Typing <'"+ value.toString() +"'> in SHADOW field ' "+cssSelector+" '");
			jsDriver.executeScript("document.querySelector('"+parentShadowRoot+"').shadowRoot.querySelector('"+childShadowRoot+"').shadowRoot.querySelector('"+cssSelector+"').value='"+value+"'");
			jsDriver.executeScript("document.querySelector('"+parentShadowRoot+"').shadowRoot.querySelector('"+childShadowRoot+"').shadowRoot.querySelector('"+cssSelector+"').dispatchEvent(new Event('input',{'bubbles':true}))");
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Hit Enter into shadow TextBox
	 * @param shadowRoot
	 * @param cssSelector
	 * @throws Exception
	 */
	protected void pressEnterToShadowElement(String shadowRoot, String cssSelector) throws Exception {
		try {
			stepDetails("Pressing Enter <'Enter'> in SHADOW field ' "+cssSelector+" '");
			jsDriver.executeScript("document.querySelector('"+shadowRoot+"').shadowRoot.querySelector('"+cssSelector+"').dispatchEvent(new KeyboardEvent('keydown', {'key': 'Enter'}))");
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Hit Enter into nested shadow TextBoxe
	 * @param shadowRoot
	 * @param cssSelector
	 * @throws Exception
	 */
	protected void pressEnterToNestedShadowElement(String parentShadowRoot,String childShadowRoot, String cssSelector) throws Exception {
		try {
			stepDetails("Pressing Enter <'Enter'> in SHADOW field ' "+cssSelector+" '");
			jsDriver.executeScript("document.querySelector('"+parentShadowRoot+"').shadowRoot.querySelector('"+childShadowRoot+"').shadowRoot.querySelector('"+cssSelector+"').dispatchEvent(new KeyboardEvent('keydown', {'key': 'Enter'}))");
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/** 
	 * Perform Mouse Over on WebElements
	 * @param element
	 * @throws Exception
	 */
	protected void mouseHover(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Performing MouseOver on Element  ' "+elementStringifier(element)+" '");
			//Highlighter.highLightElement(element);
			ActionHandler.moveToElement(element).build().perform();;
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Perform Double Click on WebElements
	 * @param element
	 * @throws Exception
	 */
	protected void doubleClick(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Performing Double Click on Element  ' "+elementStringifier(element)+" '");
			//Highlighter.highLightElement(element);
			ActionHandler.doubleClick(element).build().perform();;
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/** return text from WebElement  */
	protected String getText(WebElement element) throws Exception{
		try {
			explicitlyWait(element);
			stepDetails("Getting text from WebElement ' " + elementStringifier(element) + " '");
			//Highlighter.highLightElement(element);
			return element.getText();
		}catch (Throwable e) {
			return "";
		}
	}

	/** Method to get Text/Value of element by given attribute 
	 * @throws Exception */
	protected String getAttribute(WebElement element, String Attribute_Name) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Getting value of the attribute " + Attribute_Name + " of the WebElement " + elementStringifier(element));
			//Highlighter.highLightElement(element);
			return element.getAttribute(Attribute_Name);
		}catch (Throwable t) {
			throw new Exception("Element ' " + element + " ' doesn't have any attribute :" + Attribute_Name);
		}
	}

	/** Select the option from a drop down by Text 
	 * @throws Exception */
	protected void selectByText(WebElement element, String testData) throws Exception {
		try {
			explicitlyWait(element);
			click(element);
			stepDetails("Selecting  text <'" + testData + "'> from ' " + elementStringifier(element) + " '");
			Select select = new Select(element);
			select.selectByVisibleText(testData);
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/** Select the option from a drop down by value 
	 * @throws Exception */
	protected void selectByValue(WebElement element, String testData) throws Exception {
		try {
			explicitlyWait(element);
			click(element);
			stepDetails("Selecting  value <'" + testData + "'> from ' " + elementStringifier(element) + " '.");
			Select select = new Select(element);
			select.selectByValue(testData);
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/** It select the option from a drop down by index 
	 * @throws Exception */
	protected void selectByIndex(WebElement element, int index) throws Exception {
		try {
			explicitlyWait(element);
			click(element);
			stepDetails("Selecting  Index <'" + index + "'> from ' " + elementStringifier(element) + " '.");
			Select select = new Select(element);
			select.selectByIndex(index);
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	protected String getSelectedItemTextFromDropdown(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			//stepDetails("Getting Selected Item from Select List ' " + elementStringifier(element) + " '.");
			Select select = new Select(element);
			return select.getFirstSelectedOption().getText();
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/** Returns Array List containing element Texts for list of WebElements, mostly
	 * used for drop-downs and combo-box not having select tag 
	 * @throws Exception */
	protected ArrayList<String> getText(List<WebElement> elements) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		try {
			stepDetails("Getting  List of Text from ' " + elementStringifier(elements.get(0)) + " '.");
			for(WebElement e: elements) {
				Highlighter.highLightElement(e);
				list.add(e.getText());
			}
			return list;
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Returns WebElement having expected Text from List of WebElements
	 * @param elements
	 * @param value
	 * @return
	 * @throws Exception
	 */
	protected WebElement getWebElementByExpectedText(List<WebElement> elements, String value) throws Exception {
		try {
			stepDetails("Getting  Element Having Text <<'" + value +"'>> from ' " +elementStringifier(elements.get(0)) + " '.");
			for(WebElement e: elements) {
				if(e.getText().trim().equals(value))
					return e;
			}
			return null;
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Returns WebElement having Text Sub String from List of WebElements
	 * @param elements
	 * @param value
	 * @return
	 * @throws Exception
	 */
	protected WebElement getWebElementBySubString(List<WebElement> elements, String value) throws Exception {
		try {
			stepDetails("Getting  Element Having SubString <<'" + value +"'>> from ' " +elementStringifier(elements.get(0)) + " '");
			for(WebElement e: elements) {
				if(e.getText().trim().contains(value))
					return e;
			}
			return null;
		} catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/** To Check Element is Displayed or Not. 
	 * @throws Exception */
	protected boolean isDisplayed(WebElement element) throws Exception {
		//explicitlyWait(element);
		stepDetails("Checking if element ' "+ elementStringifier(element)+" ' is displayed");
		try {
			if (element.isDisplayed()) {
				//Highlighter.highLightElement(element);
				stepDetails("WebElement ' "+ elementStringifier(element)+" ' is visible on screen");
				return true;
			}
		}catch (Exception e) {
			stepDetails("WebElement is not visible on screen");
		}
		return false;
	}

	/** To Check Element is Selected or Not. 
	 * @throws Exception */
	protected boolean isSelected(WebElement element) throws Exception {
		explicitlyWait(element);
		stepDetails("Checking if element ' "+ elementStringifier(element)+" ' is selected");
		//Highlighter.highLightElement(element);
		if(element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	/** To Check Element is Enabled or Not. 
	 * @throws Exception */
	protected boolean isEnabled(WebElement element) throws Exception {
		explicitlyWait(element);
		stepDetails("Checking if element ' "+ elementStringifier(element)+" ' is enabled");
		//Highlighter.highLightElement(element);
		return element.isEnabled() ? true : false;
//		if (element.isEnabled()) {
//			return true;
//		} else {
//			return false;
//		}
	}	

	/** Get Current WindowHandle */
	public static String getCurrentWindow() {
		return DriverManager.getInstance().getDriver().getWindowHandle();
	}

	/** Get All Active Windows */
	private Set<String> getAllActiveWindows() {
		Set<String> ActiveTabs = DriverManager.getInstance().getDriver().getWindowHandles();
		return ActiveTabs;
	}

	/** Switch WebDriver to new window */
	public static WebDriver switchDriverToWindow(String windowHandle) {
		return DriverManager.getInstance().getDriver().switchTo().window(windowHandle);
	}

	/** Switch to new tab 
	 * @throws Exception */
	protected void switchToNewTab() throws Exception {
		stepDetails("Switching To New Window/Tab");
		String parentWindow = getCurrentWindow();
		Set<String> windowSet = getAllActiveWindows();
		for (String child : windowSet) {
			if (!parentWindow.equals(child)) {
				switchDriverToWindow(child);
			}
		}
	}

	/** Open New Tab 
	 * @throws Exception */
	protected void openNewTab() throws Exception {
		stepDetails("Opening New Window/Tab");
		jsDriver.executeScript("window.open()");
	}

	/** Close Current tab */
	protected void closeTab() {
		DriverManager.getInstance().getDriver().close();
	}

	/** Scroll Down in a Page */
	protected void scrollDown(int value) {
		jsDriver.executeScript("scroll(0, " + value + ")");
	}

	/** Scroll to Elements Location*/
	protected void scrollToElement(WebElement element) {
		jsDriver.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/** Click using JavaScriptExecutor Command
	 * @throws Exception */
	protected void clickByJsExecutor(WebElement element) throws Exception {
		try {
			stepDetails("Clicking on ' "+elementStringifier(element)+" '");
			jsDriver.executeScript("arguments[0].click();", element);
		}catch (Exception e) {
			throw new Exception("Element " + element + " is not clickable because: " + e);
		}
	}

	/** Default zoom in page */
	protected void DefaultZoom() {
		jsDriver.executeScript("document.body.style.zoom='100%'");
	}

	/** Zoom-In by 20% */
	protected void ZoomIn() {
		jsDriver.executeScript("document.body.style.zoom='120%'");
	}

	/** Zoom-out by 25% */
	protected void ZoomOut() {
		jsDriver.executeScript("document.body.style.zoom='75%'");
	}

	protected void displayHiddenInputField(String selector) {
		jsDriver.executeScript("document.querySelector('"+selector+"').style.visibility='‌​visible'");
	}

	/**
	 * Type text using Java Script Executor
	 * @param element
	 * @param inputText
	 * @throws Exception
	 */
	protected void sendKeysByJsExecutor(WebElement element, String inputText) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Typing <'"+ inputText.toString() +"'> in field ' "+elementStringifier(element)+" '");
			scrollToElement(element);
			//Highlighter.highLightElement(element);
			jsDriver.executeScript("arguments[0].textContent = arguments[1];", element, inputText);
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}
	
	protected void doubleClickByJsExecutor(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Performing Double Click on element ' "+elementStringifier(element)+" '");
			scrollToElement(element);
			//Highlighter.highLightElement(element);
			jsDriver.executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', { bubbles: true }));", element);
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Click and Hold Element.Can be Used for Drag and Drop
	 * @param element
	 * @throws Exception
	 */
	protected void clickAndHold(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Holding Element  ' "+elementStringifier(element)+" '");
			//Highlighter.highLightElement(element);
			ActionHandler.clickAndHold(element).build().perform();;
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * Store Web Table Data as List of Linked HashMaps
	 * @param tableHeaders
	 * @param rowList
	 * @param rowXpath
	 * @return
	 * @throws Exception 
	 */
	protected List<LinkedHashMap<String, String>> getTableDataAsList(List<WebElement> tableHeaders,List<WebElement> rowList, String rowXpath) throws Exception {	
		stepDetails("Fetching Data From WebTable");
		List<String> headerNames = new ArrayList<String>();
		for (WebElement header : tableHeaders) {
			headerNames.add(header.getText());
		}

		List<LinkedHashMap<String, String>> tableData = new ArrayList<LinkedHashMap<String, String>>();
		for (int i = 1; i <= rowList.size(); i++) {
			String specificRowXpath = rowXpath+"["+i+"]";
			List<WebElement> allColumnsEle = DriverManager.getInstance().getDriver().findElement(By.xpath(specificRowXpath))
					.findElements(By.tagName("td"));
			LinkedHashMap<String, String> eachRowData = new LinkedHashMap<>();
			for (int j = 0; j < allColumnsEle.size(); j++) {
				String cellValue = allColumnsEle.get(j).getText();
				eachRowData.put(headerNames.get(j), cellValue);
			}
			tableData.add(eachRowData);
		}
		return tableData;
	}

	/**
	 * Store Shadow Web Table Data as List of Linked HashMaps
	 * @param tableHeaders
	 * @param rowList
	 * @param rowXpath
	 * @return
	 * @throws Exception 
	 */
	protected List<LinkedHashMap<String, String>> getShadowTableDataAsList(List<WebElement> tableHeaders,List<WebElement> rowList, String rowXpath) throws Exception {	
		stepDetails("Fetching Data From WebTable under Shadow DOM");
		Shadow shadow = new Shadow(DriverManager.getInstance().getDriver());
		List<String> headerNames = new ArrayList<String>();
		for (WebElement header : tableHeaders) {
			String headerName = header.getText();
			headerNames.add(headerName);
		}
		List<LinkedHashMap<String, String>> tableData = new ArrayList<LinkedHashMap<String, String>>();
		for (int i = 1; i <= rowList.size(); i++) {
			String specificRowXpath = rowXpath+"["+i+"]";
			List<WebElement> allColumnsEle = shadow.findElementByXPath(specificRowXpath)
					.findElements(By.tagName("td"));
			LinkedHashMap<String, String> eachRowData = new LinkedHashMap<>();
			for (int j = 0; j < allColumnsEle.size(); j++) {
				String cellValue = allColumnsEle.get(j).getText();
				eachRowData.put(headerNames.get(j), cellValue);
			}
			tableData.add(eachRowData);
		}
		return tableData;
	}

	/**
	 * Move cursor to element. Can be Used for Drag and Drop
	 * @param element
	 * @throws Exception
	 */
	protected void moveToElementAndRelease(WebElement element) throws Exception {
		try {
			explicitlyWait(element);
			stepDetails("Moving Holded Element to  ' "+elementStringifier(element)+" '");
			//Highlighter.highLightElement(element);
			ActionHandler.moveToElement(element).pause(Duration.ofSeconds(1)).click().release().pause(Duration.ofSeconds(2)).build().perform();
		}catch (Throwable t) {
			throw new Exception(t);
		}
	}

	/**
	 * 
	 * @param element
	 * @return
	 * This will not work when using Xpath axes like ancestor, siblings etc because of the presence of ::
	 * Need to work on this in future
	 */
	protected HashMap<String,String> getElementLocator(WebElement element){
		HashMap<String,String> locator = new HashMap<String,String>();
		locator.put(element.toString().substring(element.toString().lastIndexOf(">")+1,element.toString().lastIndexOf(":")).trim(),
				element.toString().substring(element.toString().lastIndexOf(":")+1,element.toString().lastIndexOf("]")).trim());
		return locator;
	}

	protected void refreshCurrentPage() throws Exception{
		stepDetails("Refreshing Page having URL : '" +getCurrentURL()+"'");
		DriverManager.getInstance().getDriver().navigate().refresh();
	}
	
	protected String getCurrentURL() throws Exception {
		stepDetails("Getting Current URL");
		return DriverManager.getInstance().getDriver().getCurrentUrl();
	}
	
	protected void navigateBack() throws Exception{
		stepDetails("Navigating To previous page");
		DriverManager.getInstance().getDriver().navigate().back();
	}
	
	protected int getViewportHeight() throws Exception {
		stepDetails("Getting Viewport Height");
		return DriverManager.getInstance().getDriver().manage().window().getSize().getHeight();
	}
	
	protected int getViewportWidth() throws Exception {
		stepDetails("Getting Viewport Width");
		return DriverManager.getInstance().getDriver().manage().window().getSize().getWidth();
	}

	protected void enableHiddenInputTags() throws Exception {
		try {
			stepDetails("Making hidden input tags Visible");
			jsDriver.executeScript("document.querySelector(\"input[type='file']\").style.visibility=\"visible\"");
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	protected void updateOpacityforInputTags() throws Exception {
		try {
			stepDetails("Making Opacity = 1 for Input tags");
			jsDriver.executeScript("document.querySelector(\"input[type='file']\").style.opacity=1");
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	protected void updateOpacity(String elementCss) throws Exception {
		try {
			stepDetails("Making Opacity = 1 for Input tags");
			jsDriver.executeScript("document.querySelector('"+elementCss+"').style.opacity=1");
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	protected void updateGlossarySearchButtonCSS() throws Exception {
		try {
			stepDetails("Updating Glossary Search Buttons CSS");
			jsDriver.executeScript("var styles = {\n"
					+ "    \"position\": \"absolute\",\n"
					+ "    \"height\": \"32px\",\n"
					+ "    \"width\": \"35px\",\n"
					+ "    \"line-height\": \"32px\",\n"
					+ "    \"display\": \"block\",\n"
					+ "    \"right\": \"0\",\n"
					+ "    \"top\": \"0\",\n"
					+ "    \"font-size\": \"15px\",\n"
					+ "    \"text-align\": \"center\",\n"
					+ "    \"color\": \"#969696\",\n"
					+ "    \"cursor\": \"pointer\",\n"
					+ "};\n"
					+ "\n"
					+ "var obj = document.getElementById('search-glossary-results');\n"
					+ "Object.assign(obj.style, styles);");
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Remove UUID from element for logging and reporting
	 * @param element
	 * @return
	 */
	private String elementStringifier(WebElement element) {
		String pattern =  "\\[.*?\\] ->\\s*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(element.toString());
		return matcher.replaceAll("");
	}
	
	protected HashMap<Object,Object> createElementTextKeyValueHashMap(List<WebElement> keyList, List<WebElement> valueList ) throws Exception {
		return IntStream.range(0, keyList.size())
				.collect(
						HashMap::new, 
						(m, i) -> {
							try {
								m.put(getText(keyList.get(i)), getText(valueList.get(i)));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}, Map::putAll
						);
	}
}
