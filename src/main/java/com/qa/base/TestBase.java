package com.qa.base;

import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;
import java.time.Duration;

import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.qa.driver.DriverFactory;
import com.qa.driver.DriverManager;
import com.qa.listeners.TestListener;
import com.qa.reports.ExtentManager;
import com.qa.util.TestUtil;


public class TestBase extends TestListener{
	//public static Logger Log;
	//private final String URL = "https://frost-cert.health.elsevier.com/";
	//private WebDriver driver;
	//private WebDriverListener listener = new EventListener();
	protected ExtentTest extentTest;
	//protected CustomSoftAssert softAssert = new CustomSoftAssert();
	//protected CustomHardAssert Assert = new CustomHardAssert();

	public TestBase() {

		//Log = Logger.getLogger(this.getClass());
	}
	
	@BeforeSuite(alwaysRun = true)
	@Parameters("browser")
	public void prepareSuite(@Optional("firefox") String browser) {
		displayLogo();
		TestNG testNG = new TestNG();
		testNG.setUseDefaultListeners(false);
		logs.info("Running Groups: "+ System.getProperty("groups"));
		testNG.setGroups(System.getProperty("groups"));
		logs.info("Default reports are disabled");
		logs.info("Application URL : "+System.getProperty("application.url"));
		setExtentSystemInfo("Organization",configuration().MyOrg());
		setExtentSystemInfo("Team",configuration().MyTeam());
		setExtentSystemInfo("Project",configuration().MyProject());
		setExtentSystemInfo("Application URL",System.getProperty("application.url"));
		setExtentSystemInfo("Operating System",System.getProperty("os.name"));
		setExtentSystemInfo("Browser",browser);
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser"})
	public void setUpBase(Method caller, ITestResult result,@Optional("firefox") String browser) {
		DriverFactory driverFactory = new DriverFactory();
		//DriverManager.setWebDriver(driver);
		DriverManager.getInstance().setWebDriver((driverFactory.createInstance(browser)));
		
		extentTest = extent.createTest(getTestName(caller,result), getTestDescription(caller));
        test.set(extentTest);
        ExtentManager.getInstance().setExtent(test.get());
        DriverManager.getInstance().getDriver().manage().window().maximize();
        DriverManager.getInstance().getDriver().manage().deleteAllCookies();
        DriverManager.getInstance().getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        DriverManager.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
        DriverManager.getInstance().getDriver().get(System.getProperty("application.url"));
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws Exception
	{
		if (DriverManager.getInstance().getDriver() != null) {
			logs.info("Quitting Driver Instance");
			DriverManager.getInstance().quit();
		}
	}
	
	protected String getTestName(Method caller, ITestResult result) {
		Test testAnnotation = caller.getAnnotation(Test.class);
		if (!testAnnotation.testName().isEmpty() ) {
			return result.getTestClass().getName().substring(17) +"::"+ testAnnotation.testName();
		}else if (testAnnotation.testName().isEmpty()) {
			return result.getTestClass().getName().substring(17) +"::"+ caller.getName();
		} else {
			return caller.getName();
		}
	}

	protected String getTestDescription(Method caller) {
		Test testAnnotation = caller.getAnnotation(Test.class);
		if (testAnnotation != null) {
			return testAnnotation.description();
		}
		return "";
	}
	
	private void displayLogo() {
		try {
		System.out.println("");
        System.out.println("");
        System.out.println(
                " :::===== :::      :::===  :::===== :::  === ::: :::===== :::====       :::===== :::====  :::====  :::===  :::====");
        Thread.sleep(40);
        System.out.println(
                " :::      :::      :::     :::      :::  === ::: :::      :::  ===      :::      :::  === :::  === :::     :::====");
        Thread.sleep(40);
        System.out.println(
                " ======   ===       =====  ======   ===  === === ======   =======       ======   =======  ===  ===  =====    ===  ");
        System.out.println(
                " ===      ===          === ===       ======  === ===      === ===       ===      === ===  ===  ===     ===   ===  ");
        Thread.sleep(40);
        System.out.println(
                " ======== ======== ======  ========    ==    === ======== ===  ===      ===      ===  ===  ======  ======    ===  ");
        System.out.println(
                "                                                                                                                  ");
        Thread.sleep(40);
        System.out.println(
                "______________________________________________________________________________________________________________________________________________________________");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        Thread.sleep(300);
        System.out.println(
                " __  __    ________      ________   __  __   _________  ______   ___ __ __   ________   _________  ________  ______   ___   __      ");
        System.out.println(
                "/_/\\/_/\\  /_______/\\    /_______/\\ /_/\\/_/\\ /________/\\/_____/\\ /__//_//_/\\ /_______/\\ /________/\\/_______/\\/_____/\\ /__/\\ /__/\\    ");
        Thread.sleep(30);
        System.out.println(
                "\\:\\ \\:\\ \\ \\__.::._\\/    \\::: _  \\ \\\\:\\ \\:\\ \\\\__.::.__\\/\\:::_ \\ \\\\::\\| \\| \\ \\\\::: _  \\ \\\\__.::.__\\/\\__.::._\\/\\:::_ \\ \\\\::\\_\\\\  \\ \\   ");
        Thread.sleep(20);
        System.out.println(
                " \\:\\ \\:\\ \\   \\::\\ \\      \\::(_)  \\ \\\\:\\ \\:\\ \\  \\::\\ \\   \\:\\ \\ \\ \\\\:.      \\ \\\\::(_)  \\ \\  \\::\\ \\     \\::\\ \\  \\:\\ \\ \\ \\\\:. `-\\  \\ \\  ");
        System.out.println(
                "  \\:\\ \\:\\ \\  _\\::\\ \\__    \\:: __  \\ \\\\:\\ \\:\\ \\  \\::\\ \\   \\:\\ \\ \\ \\\\:.\\-/\\  \\ \\\\:: __  \\ \\  \\::\\ \\    _\\::\\ \\__\\:\\ \\ \\ \\\\:. _    \\ \\ ");
        System.out.println(
                "   \\:\\_\\:\\ \\/__\\::\\__/\\    \\:.\\ \\  \\ \\\\:\\_\\:\\ \\  \\::\\ \\   \\:\\_\\ \\ \\\\. \\  \\  \\ \\\\:.\\ \\  \\ \\  \\::\\ \\  /__\\::\\__/\\\\:\\_\\ \\ \\\\. \\`-\\  \\ \\");
        Thread.sleep(10);
        System.out.println(
                "    \\_____\\/\\________\\/     \\__\\/\\__\\/ \\_____\\/   \\__\\/    \\_____\\/ \\__\\/ \\__\\/ \\__\\/\\__\\/   \\__\\/  \\________\\/ \\_____\\/ \\__\\/ \\__\\/");
        System.out.println(""
        		+ "                                                                                                                                    ");
        System.out.println(
                "______________________________________________________________________________________________________________________________________________________________");
        System.out.println("");
        System.out.println("");
		}catch(Exception E) {
			
		}
	}
}