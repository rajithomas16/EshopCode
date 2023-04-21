package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;


@Listeners(TestAllureListener.class)
public class AccountPageTest extends BaseTest {
	//****every test method should call the page action and then assert***
	
	@BeforeClass
	public void accPageSetup() {//use the accountspage object reference  ap is linked here from base test we have created reference
		
		ap=lp.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	
	}
	@Test(priority=1)
	public void accPagetitleTest() {//use the accountspage object reference  ap is linked here from base test we have created reference
		String acttitle=ap.getaccountpagetitle();
		Assert.assertEquals(acttitle,Constants.ACCOUNT_PAGE_TITLE);
	
	}
	@Test(priority=2)
	public void accPageHeaderTest() {//use the accountspage object reference  ap is linked here from base test we have created reference
		String actpageheader=ap.getaccountpageheader();
		Assert.assertEquals(actpageheader,Constants.ACCOUNT_PAGE_HEADER);
	
	}
	@Test(priority=3)
	public void isLogoutExist() {//use the accountspage object reference  ap is linked here from base test we have created reference
		
		Assert.assertTrue(ap.islogoutpresent());
	
	}
	@Test(priority=4)
	public void acctSectionsTest() {//use the accountspage object reference  ap is linked here from base test we have created reference
		
		List<String> actualsectionlist=ap.getaccountsectionlst();
		Assert.assertEquals(actualsectionlist, Constants.getExpectedSectionList());
	
	}
	@DataProvider//2d Object array
	public Object[][] productdata() {
		return new Object[][] {//1 col and 3 rows
			{"MacBook"},
			{"Apple"},
			{"Samsung"},
			
		};
		
	}
	@Test(priority=5,dataProvider="productdata")
	public void searchTest(String productName) {//use the accountspage object reference  ap is linked here from base test we have created reference
		
		sp=ap.doSearch(productName);
		Assert.assertTrue(sp.productlistcount(productName)>0);
		
		//TDD-Test Driven Developement--write test and test drives the developement
	
	}
	
	@DataProvider//2d Object array
	public Object[][] productSelectdata() {
		return new Object[][] {//2 cols and 3 rows
			{"MacBook","MacBook Pro"},
			{"Apple","Apple Cinema 30"},
			{"Samsung","Samsung SyncMaster 941BW"},
			
		};
		
	}
	@Test(priority=6,dataProvider="productSelectdata")
	public void selectProductTest(String productName,String mainProductName){
		sp=ap.doSearch(productName);
		pinfo=sp.selectproduct(mainProductName);
		Assert.assertEquals(pinfo.getProductHeader(), mainProductName);
	}

}
