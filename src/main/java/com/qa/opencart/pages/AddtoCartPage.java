package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddtoCartPage {

	WebDriver driver;
	
	public AddtoCartPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By Cart=By.id("Cart");
	
	public void addtocart()
	{
		System.out.println("add to cart");
	}
}
