package com.qa.opencart.tests;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {//super class of all test class in src/test/java
	
	DriverFactory df;
	WebDriver driver;
	LoginPage lp;
	Properties prop;
	AccountsPage ap;//object reference for page chaining
	SearchResultPage sp;//object reference for page chaining
	ProductInfoPage pinfo;//object reference for page chaining
	SoftAssert SoftAssert;
	RegistrationPage rp;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_properties();
		driver=df.init_driver(prop);//single driver object referenced by multiple ref ariables,here driver gets new chromedriver() 
		//value from driverfactory
		lp=new LoginPage(driver);//the above same driver ref is given here and it goes to loginpage class constructor
		SoftAssert=new SoftAssert();//non static --have to create object initialization
		rp=new RegistrationPage(driver);
		
	}
	
	@AfterTest
	public void teardown() {
		
		driver.quit(); 
		
		
	}

}
