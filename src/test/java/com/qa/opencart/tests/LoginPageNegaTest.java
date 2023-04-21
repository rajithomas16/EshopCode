package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegaTest extends BaseTest {
	
@DataProvider//return type of dataprovider is object 2d array
public Object[][] wrongCredentialsdata() {
	return new Object[][]
	{
		{"test@@gmail.com","Selenium@12345"},
		{"tes11@gmail.com","Selenium@12345"},
		{"   ","Selenium@12345"},
		{"rajithomas12@gmail.com"," "}
	};
	
}
	
	@Test(dataProvider="wrongCredentialsdata")
	public void loginPageNegativeTest(String un,String pwd) {
	Assert.assertFalse(lp.doLoginwithWrongCred(un,pwd));//positive assert
	}

}
