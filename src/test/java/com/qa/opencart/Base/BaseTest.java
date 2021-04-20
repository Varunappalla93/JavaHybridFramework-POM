package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.MyAccountInfoPage;
import com.qa.opencart.pages.PDPPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

@Listeners(TestAllureListener.class) //if attachments are nt cmg properly in reports
public class BaseTest {

	DriverFactory df;
	
	// private can be accessed only within the same class
	
	//make public to access for AccountsPageTest class
	public WebDriver driver;
	
	public Properties prop;
	public LoginPage lp;
	public AccountsPage ap;
	public MyAccountInfoPage myacinfo;
	public SearchResultPage srp;
	public PDPPage pdpage;
	public RegistrationPage regpage;
	
	
	//Variables declared at class level , so its references can be called
	// created directly.
	
	//made AM as public for LoginPage and Properties as its references
//	can be used in other classes,i.e can be used in Test Classes after
	//extending.
	
//	as these variables is default by nature and if AM is default,
//	those variables cannot be used in other packages even after using extends keyword
	
	//if AM of variables is made protected, its references can be used only in its
	//parent and child classes using extends keyword.
	
	@Parameters({"browser"}) //to run from testng.xml of browser's choice.
	@BeforeTest
	public void setup(String browsername) { //String browsername //to run from testng.xml of browser's choice.
		df = new DriverFactory();
		prop=df.init_prop();
		//to set testng parameter browser with config browser
		prop.setProperty("browser", browsername); //to run from testng.xml of browser's choice.
		driver = df.init_driver(prop);
		lp = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
