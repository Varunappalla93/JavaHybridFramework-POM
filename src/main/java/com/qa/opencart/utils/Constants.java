package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	/**
	 * make final as its value cannot be overridden
	 */
	
	public static final String Login_Page_title="Account Login11";
	public static final String Login_Page_urlvalue="account/login";
	public static final String Accounts_Page_title="My Account";
	public static final String Accounts_Page_urlvalue="account/account";
	public static final String Accounts_Page_header="Your Store";
	
	//add String to list using as list.
	public static final List<String> Expected_accountSection_List=Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final String AccountsInfo_Page_title="My Account Information";
	
	
	public static final String Register_Successtext="Your Account Has Been Created";
	
	//Sheetnames
	public static final String Register_Sheet_name="RegisterData";
	
}
