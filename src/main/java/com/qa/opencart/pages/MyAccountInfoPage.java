package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class MyAccountInfoPage {
	
	private ElementUtil eleut;
	private By firstname=By.xpath("//input[@id='input-firstname']");
	
	public MyAccountInfoPage(WebDriver driver) {
		eleut = new ElementUtil(driver);
	}

	
	public String accinfourl() {
		return eleut.waitfortitle(Constants.AccountsInfo_Page_title, 5);
	}
	
	public void editfirstname() {
		eleut.Dosendkeys(firstname, "Narasimha");
	}
	
	
}
