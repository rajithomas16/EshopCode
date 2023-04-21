package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass//precondition fpr registration pagetest-user should on the registration page//pagechaining
	public void registpagesetup() {
		rp= lp.doClickonRegistration();
	}
	
	@DataProvider
	public Object[][] getRegistrationdata() {
		
		return ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
		
		
	}
	public String getRandomEmail() {
		Random randomGenerator= new Random();
		String email="selenium"+randomGenerator.nextInt(100)+"@gmail.com";
		return email;
	}
	
	@Test(dataProvider ="getRegistrationdata" )
	public void userRegistrationTest(String firstName,String lastname,String telephone,String password,String subscribe) {
		
		Assert.assertTrue(rp.accountRegistration(firstName,lastname,telephone,getRandomEmail(),password,subscribe));
		
	}

}
