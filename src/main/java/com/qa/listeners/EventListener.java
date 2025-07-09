package com.qa.listeners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;

import com.qa.util.ElementPropertyGetter;

public class EventListener implements WebDriverListener {
	
	private static Logger logs = Logger.getLogger("logs");
	
	@Override
	public void beforeAnyCall(Object target, Method method, Object[] args) {
		WebDriverListener.super.beforeAnyCall(target, method, args);
	}

	@Override
	public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
		WebDriverListener.super.afterAnyCall(target, method, args, result);
	}

	@Override
	public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
		logs.info(">>>>>Error Occured at '"+method.getName()+ "' Exception: "+e.getMessage()
		+" Target:'"+target.toString()+"' args: '"+args.toString());
	}

	@Override
	public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
		WebDriverListener.super.beforeAnyWebDriverCall(driver, method, args);
	}

	@Override
	public void afterAnyWebDriverCall(WebDriver driver, Method method, Object[] args, Object result) {
		WebDriverListener.super.afterAnyWebDriverCall(driver, method, args, result);
	}

	@Override
	public void beforeGet(WebDriver driver, String url) {
		logs.info(">>>>>Navigting to App URL: '"+url+"'");
		//System.out.println("Trying to Navigate to URL: '" + url + "'");
	}

	@Override
	public void afterGet(WebDriver driver, String url) {
		logs.info(">>>>>Navigted to App URL: '"+url+"'");
		//System.out.println("Navigated  to URL: '" + url + "'");
	}

	@Override
	public void beforeGetCurrentUrl(WebDriver driver) {
		logs.info(">>>>>Getting Current Url");
	}

//	@Override
//	public void afterGetCurrentUrl(String result, WebDriver driver) {
//		logs.info(">>>>>Got Current Url: "+result);
//	}

	@Override
	public void beforeGetTitle(WebDriver driver) {
		logs.info(">>>>>Getting Page Title");
	}

	@Override
	public void afterGetTitle(WebDriver driver, String result) {
		logs.info(">>>>>Got Page Title: "+result);
	}

	@Override
	public void beforeFindElement(WebDriver driver, By locator) {
		logs.info(">>>>>Trying to find Element: " + locator.toString());
		//System.out.println("Trying to find Element: " + locator.toString());
	}

	@Override
	public void afterFindElement(WebDriver driver, By locator, WebElement result) {
		logs.info(">>>>>Found Element: " + locator.toString());
		//System.out.println("Found Element: " + locator.toString());
	}

	@Override
	public void beforeFindElements(WebDriver driver, By locator) {
		//logs.info(">>>>>Trying to find Elements: " + locator.toString());
	}

	@Override
	public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
		//logs.info(">>>>>Found List of Elements: ");
		//result.forEach((element) -> logs.info(element.getText()));
	}

	@Override
	public void beforeGetPageSource(WebDriver driver) {
		WebDriverListener.super.beforeGetPageSource(driver);
	}

	@Override
	public void afterGetPageSource(WebDriver driver, String result) {
		WebDriverListener.super.afterGetPageSource(driver, result);
	}

	@Override
	public void beforeClose(WebDriver driver) {
		logs.info(">>>>>Closing Driver");
	}

	@Override
	public void afterClose(WebDriver driver) {
		logs.info(">>>>>Closed Driver");
	}

	@Override
	public void beforeQuit(WebDriver driver) {
		logs.info(">>>>>Quitting Driver");
		//System.out.println("Trying to Quit Driver");
	}

	@Override
	public void afterQuit(WebDriver driver) {
		logs.info(">>>>>Quitted Driver");
		//System.out.println("Quitted Driver");
	}

	@Override
	public void beforeGetWindowHandles(WebDriver driver) {
		logs.info(">>>>>Getting List Of Active Windows");
	}

	@Override
	public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
		logs.info(">>>>>Got Active Windows List: ");
		result.forEach((window) -> logs.info(window.toString()));
	}

	@Override
	public void beforeGetWindowHandle(WebDriver driver) {
		logs.info(">>>>>Getting Current Window Handle");
	}

	@Override
	public void afterGetWindowHandle(WebDriver driver, String result) {
		logs.info(">>>>>Got Current Window Handle: "+result);
	}

	@Override
	public void beforeExecuteScript(WebDriver driver, String script, Object[] args) {
		//logs.info(">>>>>Executing Java Script: "+script);
	}

	@Override
	public void afterExecuteScript(WebDriver driver, String script, Object[] args, Object result) {
		//logs.info(">>>>>Script Executed");
	}

	@Override
	public void beforeExecuteAsyncScript(WebDriver driver, String script, Object[] args) {
		WebDriverListener.super.beforeExecuteAsyncScript(driver, script, args);
	}

	@Override
	public void afterExecuteAsyncScript(WebDriver driver, String script, Object[] args, Object result) {
		WebDriverListener.super.afterExecuteAsyncScript(driver, script, args, result);
	}

	@Override
	public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
		logs.info(">>>>>Performing Action");
	}

	@Override
	public void afterPerform(WebDriver driver, Collection<Sequence> actions) {
		logs.info(">>>>>Performed Action");
	}

	@Override
	public void beforeResetInputState(WebDriver driver) {
		WebDriverListener.super.beforeResetInputState(driver);
	}

	@Override
	public void afterResetInputState(WebDriver driver) {
		WebDriverListener.super.afterResetInputState(driver);
	}

	@Override
	public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
		//System.out.println("Calling element '"+ element +"' with arguments '"+ args +"' having methodName '"+ method.getName() +"'");
	}

	@Override
	public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
		//System.out.println("Called element '"+ element +"' with arguments '"+ args +"' having methodName '"+ method.getName() +"'");
	}

	@Override
	public void beforeClick(WebElement element) {
		logs.info(">>>>>Clicking on: '" +element.toString() +"'");
		//System.out.println("Trying to click on element: '"+ element +"'");
	}

	@Override
	public void afterClick(WebElement element) {
		logs.info(">>>>>Clicked on: '"+ element.toString() +"'");
		//System.out.println("Clicked on element: '"+ element +"'");
		//ExtentManager.getInstance().getExtent().log(Status.INFO,"Clicked On:"+element.toString());
	}

	@Override
	public void beforeSubmit(WebElement element) {
		WebDriverListener.super.beforeSubmit(element);
	}

	@Override
	public void afterSubmit(WebElement element) {
		WebDriverListener.super.afterSubmit(element);
	}

	@Override
	public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
		logs.info(">>>>>Entering value: "+getString(keysToSend));
		//System.out.println("Entering value: "+getString(keysToSend));
	}

	@Override
	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		logs.info(">>>>>Entered value: "+getString(keysToSend));
		//System.out.println("Entered value: "+ getString(keysToSend));
		//ExtentManager.getInstance().getExtent().log(Status.INFO,"Entered Value '"+getString(keysToSend)+"' in field '"+element.toString()+"'");
	}

	@Override
	public void beforeClear(WebElement element) {
		logs.info(">>>>>Clearing Text From Element: '"+element+"'");
	}

	@Override
	public void afterClear(WebElement element) {
		logs.info(">>>>>Cleared Text From Element: '"+element+"'");
	}

	@Override
	public void beforeGetTagName(WebElement element) {
		WebDriverListener.super.beforeGetTagName(element);
	}

	@Override
	public void afterGetTagName(WebElement element, String result) {
		WebDriverListener.super.afterGetTagName(element, result);
	}

	@Override
	public void beforeGetAttribute(WebElement element, String name) {
		logs.info(">>>>>Getting Attribute: '"+name+"' From Element: '"+element+"'");
	}

	@Override
	public void afterGetAttribute(WebElement element, String name, String result) {
		logs.info(">>>>>Got Text: '"+result+"' From Attribute: '"+name+"'");
	}

	@Override
	public void beforeIsSelected(WebElement element) {
		WebDriverListener.super.beforeIsSelected(element);
	}

	@Override
	public void afterIsSelected(WebElement element, boolean result) {
		WebDriverListener.super.afterIsSelected(element, result);
	}

	@Override
	public void beforeIsEnabled(WebElement element) {
		WebDriverListener.super.beforeIsEnabled(element);
	}

	@Override
	public void afterIsEnabled(WebElement element, boolean result) {
		WebDriverListener.super.afterIsEnabled(element, result);
	}

	@Override
	public void beforeGetText(WebElement element) {
		logs.info("Trying to Get Text from element: \"+ element");
		//System.out.println("Trying to Get Text from element: "+ element);
	}

	@Override
	public void afterGetText(WebElement element, String result) {
		logs.info("Got Text '"+result +"' from ->"+ElementPropertyGetter.getPropertyText(element));
		//System.out.println("Got Text '"+result +"' from ->"+ElementPropertyGetter.getPropertyText(element));
		//ExtentManager.getInstance().getExtent().log(Status.INFO,"Got Text '"+result +"' from  '"+element.toString()+"'");
	}

	@Override
	public void beforeFindElement(WebElement element, By locator) {
		WebDriverListener.super.beforeFindElement(element, locator);
	}

	@Override
	public void afterFindElement(WebElement element, By locator, WebElement result) {
		WebDriverListener.super.afterFindElement(element, locator, result);
	}

	@Override
	public void beforeFindElements(WebElement element, By locator) {
		WebDriverListener.super.beforeFindElements(element, locator);
	}

	@Override
	public void afterFindElements(WebElement element, By locator, List<WebElement> result) {
		WebDriverListener.super.afterFindElements(element, locator, result);
	}

	@Override
	public void beforeIsDisplayed(WebElement element) {
		WebDriverListener.super.beforeIsDisplayed(element);
	}

	@Override
	public void afterIsDisplayed(WebElement element, boolean result) {
		WebDriverListener.super.afterIsDisplayed(element, result);
	}

	@Override
	public void beforeGetLocation(WebElement element) {
		WebDriverListener.super.beforeGetLocation(element);
	}

	@Override
	public void afterGetLocation(WebElement element, Point result) {
		WebDriverListener.super.afterGetLocation(element, result);
	}

	@Override
	public void beforeGetSize(WebElement element) {
		WebDriverListener.super.beforeGetSize(element);
	}

	@Override
	public void afterGetSize(WebElement element, Dimension result) {
		WebDriverListener.super.afterGetSize(element, result);
	}

	@Override
	public void beforeGetCssValue(WebElement element, String propertyName) {
		WebDriverListener.super.beforeGetCssValue(element, propertyName);
	}

	@Override
	public void afterGetCssValue(WebElement element, String propertyName, String result) {
		WebDriverListener.super.afterGetCssValue(element, propertyName, result);
	}

	@Override
	public void beforeAnyNavigationCall(Navigation navigation, Method method, Object[] args) {
		WebDriverListener.super.beforeAnyNavigationCall(navigation, method, args);
	}

	@Override
	public void afterAnyNavigationCall(Navigation navigation, Method method, Object[] args, Object result) {
		WebDriverListener.super.afterAnyNavigationCall(navigation, method, args, result);
	}

	@Override
	public void beforeTo(Navigation navigation, String url) {
		WebDriverListener.super.beforeTo(navigation, url);
	}

	@Override
	public void afterTo(Navigation navigation, String url) {
		WebDriverListener.super.afterTo(navigation, url);
	}

	@Override
	public void beforeTo(Navigation navigation, URL url) {
		WebDriverListener.super.beforeTo(navigation, url);
	}

	@Override
	public void afterTo(Navigation navigation, URL url) {
		WebDriverListener.super.afterTo(navigation, url);
	}

	@Override
	public void beforeBack(Navigation navigation) {
		logs.info(">>>>>Navigating Back");
	}

	@Override
	public void afterBack(Navigation navigation) {
		logs.info(">>>>>Navigated Back");
	}

	@Override
	public void beforeForward(Navigation navigation) {
		logs.info(">>>>>Navigating Forward");
	}

	@Override
	public void afterForward(Navigation navigation) {
		logs.info(">>>>>Navigated Forward");
	}

	@Override
	public void beforeRefresh(Navigation navigation) {
		logs.info(">>>>>Refreshing Page");
	}

	@Override
	public void afterRefresh(Navigation navigation) {
		logs.info(">>>>>Page Refreshed");
	}

	@Override
	public void beforeAnyAlertCall(Alert alert, Method method, Object[] args) {
		WebDriverListener.super.beforeAnyAlertCall(alert, method, args);
	}

	@Override
	public void afterAnyAlertCall(Alert alert, Method method, Object[] args, Object result) {
		WebDriverListener.super.afterAnyAlertCall(alert, method, args, result);
	}

	@Override
	public void beforeAccept(Alert alert) {
		logs.info(">>>>>Accepting Alert");
	}

	@Override
	public void afterAccept(Alert alert) {
		logs.info(">>>>>Accepted Alert");
	}

	@Override
	public void beforeDismiss(Alert alert) {
		logs.info(">>>>>Dismissing Alert");
	}

	@Override
	public void afterDismiss(Alert alert) {
		logs.info(">>>>>Dismissed Alert");
	}

	@Override
	public void beforeGetText(Alert alert) {
		logs.info(">>>>>Getting Text From Alert");
	}

	@Override
	public void afterGetText(Alert alert, String result) {
		logs.info(">>>>>Got Text: '"+result+"' From Alert");
	}

	@Override
	public void beforeSendKeys(Alert alert, String text) {
		logs.info(">>>>>Entering Text In Alert: '"+text+"'");
	}

	@Override
	public void afterSendKeys(Alert alert, String text) {
		logs.info(">>>>>Entered Text In Alert: '"+text+"'");
	}

	@Override
	public void beforeAnyOptionsCall(Options options, Method method, Object[] args) {
		WebDriverListener.super.beforeAnyOptionsCall(options, method, args);
	}

	@Override
	public void afterAnyOptionsCall(Options options, Method method, Object[] args, Object result) {
		WebDriverListener.super.afterAnyOptionsCall(options, method, args, result);
	}

	@Override
	public void beforeAddCookie(Options options, Cookie cookie) {
		WebDriverListener.super.beforeAddCookie(options, cookie);
	}

	@Override
	public void afterAddCookie(Options options, Cookie cookie) {
		WebDriverListener.super.afterAddCookie(options, cookie);
	}

	@Override
	public void beforeDeleteCookieNamed(Options options, String name) {
		WebDriverListener.super.beforeDeleteCookieNamed(options, name);
	}

	@Override
	public void afterDeleteCookieNamed(Options options, String name) {
		WebDriverListener.super.afterDeleteCookieNamed(options, name);
	}

	@Override
	public void beforeDeleteCookie(Options options, Cookie cookie) {
		WebDriverListener.super.beforeDeleteCookie(options, cookie);
	}

	@Override
	public void afterDeleteCookie(Options options, Cookie cookie) {
		WebDriverListener.super.afterDeleteCookie(options, cookie);
	}

	@Override
	public void beforeDeleteAllCookies(Options options) {
		logs.info(">>>>>Deleting Browser Cookies");
	}

	@Override
	public void afterDeleteAllCookies(Options options) {
		logs.info(">>>>>Deleted Browser Cookies");
	}

	@Override
	public void beforeGetCookies(Options options) {
		WebDriverListener.super.beforeGetCookies(options);
	}

	@Override
	public void afterGetCookies(Options options, Set<Cookie> result) {
		WebDriverListener.super.afterGetCookies(options, result);
	}

	@Override
	public void beforeGetCookieNamed(Options options, String name) {
		WebDriverListener.super.beforeGetCookieNamed(options, name);
	}

	@Override
	public void afterGetCookieNamed(Options options, String name, Cookie result) {
		WebDriverListener.super.afterGetCookieNamed(options, name, result);
	}

	@Override
	public void beforeAnyTimeoutsCall(Timeouts timeouts, Method method, Object[] args) {
		WebDriverListener.super.beforeAnyTimeoutsCall(timeouts, method, args);
	}

	@Override
	public void afterAnyTimeoutsCall(Timeouts timeouts, Method method, Object[] args, Object result) {
		WebDriverListener.super.afterAnyTimeoutsCall(timeouts, method, args, result);
	}

	@Override
	public void beforeImplicitlyWait(Timeouts timeouts, Duration duration) {
		WebDriverListener.super.beforeImplicitlyWait(timeouts, duration);
	}

	@Override
	public void afterImplicitlyWait(Timeouts timeouts, Duration duration) {
		WebDriverListener.super.afterImplicitlyWait(timeouts, duration);
	}

	@Override
	public void beforeSetScriptTimeout(Timeouts timeouts, Duration duration) {
		WebDriverListener.super.beforeSetScriptTimeout(timeouts, duration);
	}

	@Override
	public void afterSetScriptTimeout(Timeouts timeouts, Duration duration) {
		WebDriverListener.super.afterSetScriptTimeout(timeouts, duration);
	}

	@Override
	public void beforePageLoadTimeout(Timeouts timeouts, Duration duration) {
		//System.out.println("Waiting for Page to be loaded for: "+duration +" Seconds");
	}

	@Override
	public void afterPageLoadTimeout(Timeouts timeouts, Duration duration) {
		//System.out.println("Page loaded");
	}

	@Override
	public void beforeAnyWindowCall(Window window, Method method, Object[] args) {
		WebDriverListener.super.beforeAnyWindowCall(window, method, args);
	}

	@Override
	public void afterAnyWindowCall(Window window, Method method, Object[] args, Object result) {
		WebDriverListener.super.afterAnyWindowCall(window, method, args, result);
	}

	@Override
	public void beforeGetSize(Window window) {
		WebDriverListener.super.beforeGetSize(window);
	}

	@Override
	public void afterGetSize(Window window, Dimension result) {
		WebDriverListener.super.afterGetSize(window, result);
	}

	@Override
	public void beforeSetSize(Window window, Dimension size) {
		WebDriverListener.super.beforeSetSize(window, size);
	}

	@Override
	public void afterSetSize(Window window, Dimension size) {
		WebDriverListener.super.afterSetSize(window, size);
	}

	@Override
	public void beforeGetPosition(Window window) {
		WebDriverListener.super.beforeGetPosition(window);
	}

	@Override
	public void afterGetPosition(Window window, Point result) {
		WebDriverListener.super.afterGetPosition(window, result);
	}

	@Override
	public void beforeSetPosition(Window window, Point position) {
		WebDriverListener.super.beforeSetPosition(window, position);
	}

	@Override
	public void afterSetPosition(Window window, Point position) {
		WebDriverListener.super.afterSetPosition(window, position);
	}

	@Override
	public void beforeMaximize(Window window) {
		logs.info(">>>>>Maximing Window");
		//System.out.println("Trying to Maximize Window");
	}

	@Override
	public void afterMaximize(Window window) {
		logs.info(">>>>Window Maximized");
		//System.out.println("Maximized Window");
	}

	@Override
	public void beforeFullscreen(Window window) {
		logs.info(">>>>>Entering FullScreen");
	}

	@Override
	public void afterFullscreen(Window window) {
		logs.info(">>>>>Entered FullScreen");
	}
	
	/** This is to get String from given CharSequence array*/
	private String getString(CharSequence... sequence) {
		String sequenceText = new String();
		for (CharSequence cs : sequence) {
			sequenceText = cs.toString();
		}
		return sequenceText;
	}

}
