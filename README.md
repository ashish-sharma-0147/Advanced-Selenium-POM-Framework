# Introduction
Selenium TestNG framework is a combination of Selenium WebDriver and TestNG testing framework that is used for automated web testing. 

This framework provides an efficient and effective way to test web applications by utilizing the features of both Selenium and TestNG. 

In addition, the Page Factory design pattern is used to create page objects that represent the web pages of the application. 

This documentation provides a detailed explanation of the Selenium TestNG framework with Page Factory.

# Dependencies/Tools


The framework is built using the following dependencies:

Selenium WebDriver: The Selenium WebDriver is a browser automation tool that allows us to control the browser actions programmatically. We can use this to perform actions such as clicking, typing, and navigating between pages.

TestNG: TestNG is a testing framework that is used for writing and running tests. It provides features such as grouping, parallel execution, data-driven testing, and reporting.

PageFactory: PageFactory is a design pattern used to create page objects that represent the web pages of the application. Page objects contain the elements of the page, and their methods provide the actions that can be performed on those elements.

Sukgu Maven dependency: Sukgu is a library used for handling elements part of Shadow DOM.
Extent Report: Extent Report is a reporting framework that provides detailed and visually appealing reports for the test results.

# Below are few dependencies and tools used in the framework
Git

Java

Maven

IDE

Jenkins

AWS Device Farm (Optional)

AWS S3 bucket (Optional)

Installing Dependencies/Tools


# Framework Structure
The framework has different packages such as:

Base: This package contains the base class which initializes the WebDriver and the PageFactory.

Pages: This package contains the page classes that represent the web pages of the application.

Tests: This package contains the test classes that contain the TestNG tests.

Utils: This package contains the utility classes that provide helper methods for the tests.

# Page Factory Implementation:
The Page Factory design pattern is used to create page objects that represent the web pages of the application. In the Pages package, there are different classes that represent the web pages of the application. For example, the LoginPage class represents the login page of the application.

In each page class, we define the web elements of the page using the @FindBy annotation.

*TestNG Implementation:

The TestNG framework is used to write and run the tests. In the Tests package, there are different test classes that contain the TestNG tests. For example, the LoginTest class contains the TestNG tests related to the login functionality.

In each test class, we define the tests using the @Test annotation.
 
# Browser Drivers
No driver binaries are required to run the suite. Browser binaries are managed by WebDriverManager.

WebDriverManager is an open-source Java library that carries out the management (i.e., download, setup, and maintenance) of the drivers required by Selenium WebDriver (e.g., chromedriver, geckodriver, msedgedriver, etc.) in a fully automated manner. 

In addition, WebDriverManager provides other relevant features, such as the capability to discover browsers installed in the local system, building WebDriver objects (such as ChromeDriver, FirefoxDriver, EdgeDriver, etc.)

Browsers are required to be installed in the system

# Interacting With Web Elements
@FindBy annotation is used for locating the element or a list of elements. 
All the selenium provided locators can be used to locate the element.


@FindBy(id = "textField") WebElement textField;

@FindBy(tagName = "a") List<WebElement> links;
 
@FindElementBy annotation is used for locating elements present in Shadow DOM.
Only XPath and CSS locators can be used to locate shadow elements.


@FindBy(css = "#h3") WebElement h3;

@FindElementBy(css = "#inside") List<WebElement> insides;

@FindElementBy(xpath = "//body") WebElement bodyByXPath;
   
For more details refer the link:
   
GitHub - sukgu/shadow-automation-selenium: This project focuses on automation of multi-level shadow root dom using java selenium. You can embed this plugin in your java selenium project. 
   
click(WebElement) to click the element
   
sendKeys(WebElement,String) to type the String in the WebElement
   
sendKeys(WebElement,Keys) to send Key events to the WebElement
   
sendKeys(String shadowRoot, String cssSelector, String value) will type text in shadow textfields 
   
getText(WebElement) will return the text from WebElement
   
getText(List <WebElement>) will return List of text from the WebElement List
   
Methods required to interact with the UI elements are present in com/qa/util/DriverActions.java
 
