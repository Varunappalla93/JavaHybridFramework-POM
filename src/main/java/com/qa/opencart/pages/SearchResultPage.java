package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	 private WebDriver driver;
	private ElementUtil eleut;

	private By SearchItemResult = By.xpath("//div[@class='product-thumb']");
	private By ResultItems = By.cssSelector("div.product-thumb h4 a");

	public SearchResultPage(WebDriver driver) {
		 this.driver=driver;
		eleut = new ElementUtil(driver);
	}

	public int getprodresultscount() {
		return eleut.getElements(SearchItemResult).size();
	}

	public PDPPage selectproductfromresults(String prodname) {
		List<WebElement> resitemslist = eleut.getElements(ResultItems);
		System.out.println("Total no of items displayed are : " + prodname + " and size: " + resitemslist.size());
		for (WebElement e : resitemslist) {
			if (e.getText().equalsIgnoreCase(prodname)) {
				e.click();
				break;	
			}
		}
		return new PDPPage(driver);
	}

}
