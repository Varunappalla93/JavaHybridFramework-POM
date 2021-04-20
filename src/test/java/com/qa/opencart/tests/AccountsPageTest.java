package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ErrorMessages;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accpagesetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		// ap=new AccountsPage(driver);
		// to use driver here, make driver public in BaseTest
		// but now no need, as doLogin method returns AccountsPage
		// and its reference can be used as page chaining, i.e. next
		// landing page.
	}

	@Test
	public void accpagetitletest() {
		String title = ap.getaccounttitle();
	Assert.assertEquals(title, Constants.Accounts_Page_title, "Account Page title is wrong");
	}

	@Test
	public void accpageurltest() {
		String url = ap.getaccounturl();
		Assert.assertTrue(url.contains(Constants.Accounts_Page_urlvalue));
	}

	@Test
	public void accpageheadertest() {
		String header = ap.getAccountpageheader();
		System.out.println("Header is " + header);
		Assert.assertEquals(header, Constants.Accounts_Page_header,ErrorMessages.ACC_page_headererror);
	}

	@Test
	public void accpagesectionstest() {
		List<String> sectionslist = ap.getAccountsections();

		// using stream concept
		sectionslist.stream().forEach(e -> System.out.println(e));

		Collections.sort(Constants.Expected_accountSection_List);
		Assert.assertEquals(sectionslist, Constants.Expected_accountSection_List);
	}

	@Test
	public void getaccountlinkstest()
	{
	List<String> accountslistst=ap.getaccountindlinks();
	for (String e : accountslistst) {
		System.out.println(e);
	}
	}
	
	@Test
	public void logoutlinktest() {
		Assert.assertTrue(ap.isLogoutExist());
	}
}
