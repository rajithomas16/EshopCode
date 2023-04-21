package com.qa.opencart.tests;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.qa.opencart.utils.Constants;
//every test method should have only one hard assertion or multiple soft assertions
public class productInfoPageTest extends BaseTest {
	@BeforeClass
	public void accPageSetup() {//use the accountspage object reference  ap is linked here from base test we have created reference
		
		ap=lp.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	
	}
	
	@Test(priority=1)
	public void productHeaderTest() {
		sp=ap.doSearch("MacBook");
		pinfo=sp.selectproduct("MacBook Pro");
		Assert.assertEquals(pinfo.getProductHeader(), "MacBook Pro");
		
}
	@Test(priority=2)
	public void productImagesCount() {
		sp=ap.doSearch("iMaC");
		pinfo=sp.selectproduct("iMaC");
		Assert.assertEquals(pinfo.getImagesCount(), Constants.IMAC_IMAGES_COUNT);
		
}
	@Test(priority=3)
	public void productInfoTest() {
		sp=ap.doSearch("MacBook");
		pinfo=sp.selectproduct("MacBook Pro"); 
		Map<String,String> actualProdInfoMap=pinfo.getProductInfo();
		actualProdInfoMap.forEach((k,v)-> System.out.println(k+ ":"+v));
		SoftAssert.assertEquals(actualProdInfoMap.get("productName"), "MacBook Pro");
		SoftAssert.assertEquals(actualProdInfoMap.get("Brand"), "Apple");
		SoftAssert.assertEquals(actualProdInfoMap.get("Product Code"), "Product 18");
		SoftAssert.assertEquals(actualProdInfoMap.get("Availability"), "In Stock");		
		SoftAssert.assertEquals(actualProdInfoMap.get("Reward Points"), "800");
		SoftAssert.assertAll();//compulsory to write
		
}
}