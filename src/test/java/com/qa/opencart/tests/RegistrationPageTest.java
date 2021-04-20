
package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void setupregister()
	{
		regpage=lp.navigatetoregisterpage();
	
	}

//	@Test //for single data
//	public void userregisterationformtest()
//	{
//		Assert.assertTrue(regpage.fillregisterform("Varun", "AN", "varun@gmail.com",
//				"8103234553", "Varun1993", "yes"));	
//		
//	}
	
	//testng dataprovider method
	@DataProvider
	public Object[][] getregisterdata()
	{
	Object[][] regdata=ExcelUtil.getTestData(Constants.Register_Sheet_name);
	return regdata;
	}
	
	
	//create random email id for tests to pass with different data
	//remove email from testdatasheet as its collecting email from getrandomnumber()
	public String getRandomNumber()
	{
		Random randomgenerator=new Random();
		String email="testautomation"+randomgenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	//to pass values from dataprovider
	@Test(dataProvider="getregisterdata")
	public void userregisterationformtest(String fname,String lname,
			String mobileno,String password,String subsribe)
	{
		Assert.assertTrue(regpage.fillregisterform(fname,lname,getRandomNumber(),
				mobileno,password,subsribe));	
		
	}
}
