package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleut;

	private By accountsections = By.xpath("//div[@id='content']/h2");
	private By accheader = By.cssSelector("div#logo a");
	private By logoutlink = By.linkText("Logout");
	
	private By accountseplinks=By.xpath("//div[@id='content']/ul[@class='list-unstyled']/li/a");
	
	private By editaccinfolink = By.xpath("//a[contains(text(),'Edit your account information')]");
	
	//for search
	private By Searchfield=By.name("search");
	private By Searchbtn=By.cssSelector("div#search button");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver; 
		//to initialize driver for MyAccountInfoPage or
//		else it will give NPE as driver will not be initialized.
		eleut = new ElementUtil(driver);
	}

	public String getaccounttitle() {
		return eleut.waitfortitle(Constants.Accounts_Page_title, 5);
	}

	public String getaccounturl() {
		return eleut.waitforpageurl();
	}

	public String getAccountpageheader() {
		return eleut.dogetText(accheader);
	}

	// gives list of Strings
	public List<String> getAccountsections() {
		List<String> accsecvaluelist = new ArrayList<String>();

		List<WebElement> accseclist = eleut.waitforvisibilityofelements(accountsections, 5);
		for (WebElement e : accseclist) {
			accsecvaluelist.add(e.getText());
		}
		Collections.sort(accsecvaluelist); // to sort
		return accsecvaluelist;
	}
	
	public boolean isLogoutExist() {
		return eleut.iseledisplayed(logoutlink);
	}
	
	public List<String> getaccountindlinks()
	{
	List<String> sepaccountslist=new ArrayList<String>();
	
	List<WebElement> acclist = eleut.waitforvisibilityofallelementslocatedby(accountseplinks, 10);
		for (WebElement e : acclist)
		{
			sepaccountslist.add(e.getText());
		}
		return sepaccountslist;
	}
	
	//Search feature
	public SearchResultPage doSearch(String productname)
	{
		System.out.println("Searching the product");
		eleut.Dosendkeys(Searchfield, productname);
		eleut.doclick(Searchbtn);
		return new SearchResultPage(driver);
		
	}
	
	public MyAccountInfoPage editaccountinfo() {
		eleut.doclick(editaccinfolink);
		return new MyAccountInfoPage(driver);
	}
}