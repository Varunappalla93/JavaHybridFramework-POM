package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Base.BaseTest;

public class ProductInfoTest extends BaseTest {

	SoftAssert softAssert=new SoftAssert();
	
	@BeforeClass
	public void productinfosetup()
	{
		ap=lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] searchData() {
		return new Object[][] {{"Macbook"},
								{"iMac"},
								{"iPhone"}};
	}
	
	@Test(dataProvider="searchData")
	public void prodcounttest(String productname)
	{
		srp=ap.doSearch(productname);
		Assert.assertTrue(srp.getprodresultscount()>0);
	}
	
	@Test
	public void prodinfotest()
	{
	srp=ap.doSearch("iMac");
	pdpage=srp.selectproductfromresults("iMac");
	Assert.assertEquals(pdpage.getprodheader(),"iMac");
	}
	
	
	@Test
	public void prodimagestest()
	{
	srp=ap.doSearch("Macbook");
	pdpage=srp.selectproductfromresults("Macbook Pro");
	Assert.assertTrue(pdpage.getprodimagescount()==4);
	}
	
	@Test
	public void getproductinfotest()
	{
	srp=ap.doSearch("Macbook");
	pdpage=srp.selectproductfromresults("Macbook Pro");
	Map<String,String> actproductinfo=pdpage.getprodinfo();
	
	actproductinfo.forEach((k,v)->System.out.println(k+":"+v));
	
	softAssert.assertEquals(actproductinfo.get("name"),"MacBook Pro");
	softAssert.assertEquals(actproductinfo.get("Brand"),"Apple");
	softAssert.assertEquals(actproductinfo.get("Availability"),"Out Of Stock");
	softAssert.assertAll();
	//use soft assert to skip failed assertions, it will not terminate immediately like
//	hard assertions, but it will check other assertions and then get terminated.
	}
	
	@Test(enabled = false)
	public void addtocarttest()
	{
	srp=ap.doSearch("iMac");
	pdpage=srp.selectproductfromresults("iMac");
	pdpage.SelectQuantity("4");
	pdpage.addtocart();
	Assert.assertTrue(pdpage.verifysucess().contains("Success"));
	}
}

