package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class PDPPage {

	private WebDriver driver;
	private ElementUtil eleut;
	
	private By prodheader=By.cssSelector("div#content h1");
	private By prodimages=By.cssSelector("ul.thumbnails li img");
	
	private By prodmetadata=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	
	private By prodpricingdata=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	
	private By Qty=By.id("input-quantity");
	private By Addtocartbtn=By.id("button-cart");
	
	private By Successtext=By.cssSelector("div.alert.alert-success.alert-dismissible");
	
	public PDPPage(WebDriver driver) {
		this.driver=driver;
		eleut = new ElementUtil(driver);
	}
	
	public String getprodheader()
	{
		return eleut.dogetText(prodheader);
	}
	
	public int getprodimagescount()
	{
		return eleut.getElements(prodimages).size();
	}
	
	/**
	 * This method will collect product metadata and 
	 * pricing data in the form of hash map.
	 * 
	 * This method will return prodinfomap
	 * @return 
	 * @return
	 */
	
	public Map<String, String> getprodinfo()
	{
	Map<String, String> prodinfomap=new HashMap<String, String>();
	prodinfomap.put("name",getprodheader());
	
	List<WebElement> metadatalist=eleut.getElements(prodmetadata);
	System.out.println("Metadata list is :"+metadatalist.size());
	
	//metadata
	for (WebElement e : metadatalist) {
		//Brand-Apple
		String metarr[] =e.getText().split(":");
		String metakey=metarr[0].trim();
		String metavalue=metarr[1].trim();
		prodinfomap.put(metakey, metavalue);
		
	}
	
	//price
	List<WebElement> pricelist=eleut.getElements(prodpricingdata);
	String price=pricelist.get(0).getText().trim();
	String EXprice=pricelist.get(1).getText().trim();
	
	prodinfomap.put("price", price);
	prodinfomap.put("ExTaxPrice", EXprice);
	
	return prodinfomap;
	}
	
	public void SelectQuantity(String quantity)
	{
		eleut.Dosendkeys(Qty, quantity);
	}
	
	public void addtocart()
	{
		eleut.doclick(Addtocartbtn);
	}
	
	public String verifysucess()
	{
//		return eleut.dogetText(Successtext);  not working due to timeout issues, 
//		hence used waitforelementvisible util
		
		return eleut.waitforelementvisiblelocated(Successtext, 10).getText();

	}
	
}
