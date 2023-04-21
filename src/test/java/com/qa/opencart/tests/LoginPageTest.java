package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EPIC ID:1020:Opencart Login Page Design")
@Story("US 101-Open cart testing-Login page design")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest{
	
	//POM model--should not call driver apis directly in test class
	
	@Description("Login Page Test")
	@Severity(SeverityLevel.NORMAL)
	
	@Test(priority=1)	
	public void loginPageTitleTest() {
		
		String actualtitle=lp.getloginpagetitle();
		System.out.println("Actual Title:"+actualtitle);
		Assert.assertEquals(actualtitle, Constants.LOGIN_PAGE_TITLE,Errors.ACCT_PAGE_HEADER_NOT_FOUND_ERROR_MSG);
	}
	
	@Description("Login Page URL test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)	
	public void loginPageUrlTest() {
		
		Assert.assertTrue(lp.getloginpageurl());
	}
	@Description("Forgot Password Test")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(priority=3)
	public void forgotpwdlinktest() {
				
		Assert.assertTrue(lp.isforgtpwdpresent());
	}
	@Description("Register link Test")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(priority=4,enabled=false)	//enabled---false--test will not participate
	public void registerlink() {
		
		Assert.assertTrue(lp.isregistlnkpreset());
	}
	
	@Description("Login Right credentials Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void logintest() {
		
		ap=lp.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		ap.getaccountpagetitle();
		Assert.assertEquals(ap.getaccountpagetitle(),Constants.ACCOUNT_PAGE_TITLE);
	}
		
	}


