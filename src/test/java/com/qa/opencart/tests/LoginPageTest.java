package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//LoginPageTests extends BaseTest to get Before and AfterTest Methods
//and all the variables and methods.
//Inside test classes, we should not use any driver methods, utils etc
//just call the page methods and use assertions.

@Epic("Epic 450: Design Login Page for Demo-Cart")
@Story("US 100: Develop a feature with login page scenarios")
public class LoginPageTest extends BaseTest
{
	@Description("Login page title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginpagetitletest()
	{
		String title=lp.getLoginPageTitle();
		System.out.println("Login page title is "+title);
//		Assert.assertEquals(title, "Account Login");
		//call values from Constants class instead of hardcoding values
		Assert.assertEquals(title, Constants.Login_Page_title);
	}
	
	@Description("Login page url Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2) //enabled=false to skip the test
	public void loginpageurltest()
	{
		String url=lp.getLoginPageurl();
		//Assert.assertTrue(url.contains("account/login"));
		//call values from Constants class instead of hardcoding values
		Assert.assertTrue(url.contains(Constants.Login_Page_urlvalue));
	}
	
	@Description("Login page forgot password link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotpasswordexiststest()
	{
		boolean flag=lp.isforgotpwdlinkexist();
		Assert.assertTrue(flag);
	}
	
	@Description("Login Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void logintest()
	{
		lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
}
