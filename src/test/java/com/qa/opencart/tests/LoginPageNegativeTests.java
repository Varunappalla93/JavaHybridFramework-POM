package com.qa.opencart.tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;

//LoginPageTests extends BaseTest to get Before and AfterTest Methods
//and all the variables and methods.
//Inside test classes, we should not use any driver methods, utils etc
//just call the page methods and use assertions.


public class LoginPageNegativeTests extends BaseTest
{
	
	@DataProvider
	public Object[][] loginNegativeData() 
	{
		return new Object[][]  {
							{"test@gmail.com", "test@123"},
							{" " , "test@123"},
							{" ", " "}};
	}
	
	@Test(dataProvider="loginNegativeData")
	public void loginnegativetests(String username,String password)
	{
		lp.dologinwithwrongdata(username, password);
	}
}
