package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void accinfopagesetup() 
	{
		ap=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		myacinfo=ap.editaccountinfo();
	}
	
	@Test
	public void accpageinfo_titletest() {
		String accinfourl=myacinfo.accinfourl();
		Assert.assertEquals(accinfourl,Constants.AccountsInfo_Page_title,"Account Info Page title is wrong");
	}
	
	@Test
	public void accpageinfofirstnametest() {
	myacinfo.editfirstname();
	}
	
}
	
	
	
