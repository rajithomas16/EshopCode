package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {
	//Page class steps**
	
	//never right assertions in page actions write in testng class
	//1.declare private driver
	private WebDriver driver;//local driver of the LoginPage class,using driver in blue in page chaining so cant remove this 
	private ElementUtils eleutil;
	

	//2.to initialize the above webdriver driver ---create login page class constructor
	//this constr says-->when you craete object of login page class that time you have to 
	//give me the driver and that driver will be given to  var driver in blue
	
	public LoginPage(WebDriver driver) //constructor 
	{
		this.driver=driver;
		eleutil= new ElementUtils(driver);
	}
	//3.By locators-private
	
	private By emailid= By.id("input-email");
	private By password= By.id("input-password");
	private By loginbutton= By.cssSelector("input.btn.btn-primary");
	private By registerlnk= By.linkText("Register");
	private By forgotpwdlnk= By.linkText("Forgotten Password");
	private By warningwronglogin = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//4.Page Actions
	
	@Step("Getting login Page title")
	
	public String getloginpagetitle() {
		return eleutil.doGettitle(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_PAGE_TIMEOUT);
	}
	@Step("Getting login Page URL")
	public boolean getloginpageurl() {
		return eleutil.waitforURLContains(Constants.LOGIN_PAGE_URL_FRACTION,Constants.DEFAULT_PAGE_TIMEOUT);
	}
	@Step("forgot pw present")
	public boolean isforgtpwdpresent() {
		return eleutil.doIsDisplayed(forgotpwdlnk);
	}
	@Step("Register link title")
	public boolean isregistlnkpreset() {
		return eleutil.doIsDisplayed(registerlnk);
	}
	
	//PAGE CHAINING--this method should return object of next landing page
	@Step("Loggin in with username:{0} and password:{1}")
	public AccountsPage doLogin(String un,String pwd) {//private webelement inside the public mthod--encapsulation comcept
		System.out.println("Login using Username: "+un+" Password: "+pwd);
		eleutil.doSendKeys(emailid, un);
		eleutil.doSendKeys(password,pwd);
		eleutil.doClick(loginbutton);
		
		return new AccountsPage(driver);//accountspage class object for page chaining
		
	}
	@Step("Loggin in with wrong username:{0} and wrong password:{1}")
	public boolean doLoginwithWrongCred(String un,String pwd) {//private webelement inside the public mthod--encapsulation comcept
		System.out.println("Login using Wrong Username: "+un+" Password: "+pwd);
		eleutil.doSendKeys(emailid, un);
		eleutil.doSendKeys(password,pwd);
		eleutil.doClick(loginbutton);
		
		String errormssg = eleutil.doGetText(warningwronglogin);
		if (errormssg.contains(Constants.LOGIN_ERROR_MSSG)) {
			System.out.println("Login Not Successful" );
			return false;
		}
		return true;
	}
	
	@Step("Navgating to registration page")
	public RegistrationPage doClickonRegistration() {
		
		eleutil.doClick(registerlnk);
		return new RegistrationPage(driver);
	}
	}


