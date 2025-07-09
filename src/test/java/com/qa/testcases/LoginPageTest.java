package com.qa.testcases;



import static com.qa.config.ConfigurationManager.configuration;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.DashboardPage;
import com.qa.pages.ForgotPasswordPage;
import com.qa.pages.LoginPage;
import com.qa.util.CustomHardAssert;
import com.qa.util.TestUtil;

public class LoginPageTest extends TestBase{


	LoginPage loginPage;
	DashboardPage dashBoardPage;
	ForgotPasswordPage forgotPasswordPage;
	
	private final boolean isEnabled = true;


	@BeforeMethod(alwaysRun=true)
	public void navigateToLoginPage(Method method) throws Exception {
		//MyScreenRecorder.startRecording(method.getName());
		loginPage = new LoginPage();
	}

	@Test(priority = 1, enabled = isEnabled, groups = {"regression","Login"}, description="")
	public void InvalidLoginErrorTest() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		String ErrorMsg = loginPage.InvalidLogin(TestUtil.faker.gameOfThrones().character(), TestUtil.getRandomAlphanumericPassword());
		System.out.println("Java-Version::"+System.getProperty("java.version"));
		System.out.println(System.getProperty("java.specification.version"));
		System.out.println(System.getProperty("java.runtime.version"));
		Assert.assertEquals(ErrorMsg, "Login failed. Try again or contact your administrator.");
	}
	
	@Test(priority = 2, enabled = isEnabled, groups = {"regression","Login"}, description="")
	public void LoginWithoutCredentialsErrorTest() throws Exception {
		CustomHardAssert Assert = new CustomHardAssert();
		loginPage.clickLogin();
		String ErrorMsg = loginPage.getErrorMessage();
		Assert.assertEquals(ErrorMsg, "All fields are required.");
	}
	
	@Test(priority = 3, enabled = isEnabled, groups = {"regression","Login"}, description="")
	public void NeedHelpRedirectTest() throws Exception{
		CustomHardAssert Assert = new CustomHardAssert();
		forgotPasswordPage = loginPage.clickOnNeedHelp();
		String Title = forgotPasswordPage.getPageTitle();
		Assert.assertEquals(Title, "Forgot Password");
	}
	
	@Test(priority = 4, enabled = isEnabled, groups = {"sanity","Login"}, description="")
	public void ValidLoginTest() throws Exception{
		CustomHardAssert Assert = new CustomHardAssert();
		dashBoardPage = loginPage.ValidLogin(configuration().username(), configuration().password());
		String heading = dashBoardPage.getPageHeading();
		Assert.assertEquals(heading, "My Projects");
	}
	
	@Test(priority = 5, enabled = isEnabled, groups = {"sanity","Login"}, description="")
	public void LogOutTest() throws Exception{
		CustomHardAssert Assert = new CustomHardAssert();
		dashBoardPage = loginPage.ValidLogin(configuration().username(), configuration().password());
		loginPage = dashBoardPage.Logout();
		String pageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(pageTitle, "Elsevier FROST");
	}

}
