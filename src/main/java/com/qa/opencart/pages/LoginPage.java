package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import com.qa.opencart.pages.RegistrationPage;

public class LoginPage
{
	
private WebDriver driver;  //for capturing AccountsPage and RegistartionPage
//not using this driver for other action methods 
//as its calling driver from ElementUtil.
	
private ElementUtil eleut;

	//private By Locators-use private access modifiers to achieve
	//encapsulation concept , to use these private variables in public methods.
	private By usernamefld=By.id("input-email");
	private By passwordfld=By.id("input-password");
	private By loginbtn=By.xpath("//input[@type='submit']");
	private By forgotpasswordlink=By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private By registerlink=By.linkText("Register");
	private By loginerrortext =By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//Create Constructor so that it will be called when its object
	//is created and then we can pass the driver as parameter of the constructor
//	during object creation and that will be given to this.driver which is at class level
//	and that driver will be used for page actions, If constructor is not used
//	we get nullpointerexception as class variable driver is empty and we are trying
	//to assign it with page actions, hence null with pageactions always give
	//NPE.

	
	public LoginPage(WebDriver driver) {
		this.driver=driver; //to initialize driver for Accounts and Registration 
//	Pages or else it will give NPE as driver will not be initialized.
		eleut=new ElementUtil(driver); //create ref of ElementUtil class
	
	}
	
	
	//Page Actions-Public in nature to call private By locators
		@Step("getting login page title")
		public String getLoginPageTitle()
		{
//			return driver.getTitle();
			return eleut.waitfortitle(Constants.Login_Page_title, 5);
			//call this method from Elementutil class 
		}
		
		@Step("getting login page url")
		public String getLoginPageurl()
		{
//			return driver.getCurrentUrl();
			return eleut.waitforpageurl();
		}
		
		@Step("getting forgot pwd exists")
		public boolean isforgotpwdlinkexist()
		{
//			return driver.findElement(forgotpasswordlink).isDisplayed();
			return eleut.iseledisplayed(forgotpasswordlink);
		}	
		
		@Step("login with username: {0} and password: {1}")
		public AccountsPage doLogin(String username,String password)
		{
//			driver.findElement(usernamefld).sendKeys(username);
			eleut.Dosendkeys(usernamefld, username);
			eleut.Dosendkeys(passwordfld, password);
			eleut.doclick(loginbtn);
			
			return new AccountsPage(driver);
			
//			driver.findElement(passwordfld).sendKeys(password);
//			driver.findElement(loginbtn).click();
		}
		
		@Step("login with username: {0} and password: {1}")
		public boolean dologinwithwrongdata(String un,String pwd)
		{
			eleut.Dosendkeys(usernamefld, un);
			eleut.Dosendkeys(passwordfld, pwd);
			eleut.doclick(loginbtn);
			return eleut.iseledisplayed(loginerrortext);
		}
		
		@Step("Navigating to the registration page")
		public RegistrationPage navigatetoregisterpage()
		{
			eleut.doclick(registerlink);
			return new RegistrationPage(driver);
		}	
}
