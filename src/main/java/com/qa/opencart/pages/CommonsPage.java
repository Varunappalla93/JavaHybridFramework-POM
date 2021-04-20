package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {
ElementUtil eleut;
	//search
	//add to cart
	
	private By search=By.xpath("input[placeholder='Search']");
	
	public CommonsPage(WebDriver driver) {
		eleut=new ElementUtil(driver);
	}
	
	
}
