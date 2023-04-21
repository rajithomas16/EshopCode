package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

public class RegistrationPage {
	
	private WebDriver driver;//can remove this as we are not using driver in this page directly but eleutil is using it
	private ElementUtils eleutil;
	
	
	private By firstname= By.id("input-firstname");
	private By lastname= By.id("input-lastname");
	private By email= By.id("input-email");
	private By telephone= By.id("input-telephone");
	private By passwrd= By.id("input-password");
	private By passwrdconfirm= By.id("input-confirm");
	private By subscibeYes= By.cssSelector("label.radio-inline:nth-of-type(1) input");
	private By subscribeNo= By.cssSelector("label.radio-inline:nth-of-type(2) input");
	private By agreecheckbox= By.name("agree");
	private By continuebutton= By.cssSelector("input.btn.btn-primary");
	private By successmessg= By.cssSelector("div#content h1");
	
	private By registerlnk= By.linkText("Register");
	private By logoutlnk= By.linkText("Logout");
	
	
	
	public RegistrationPage(WebDriver driver) {
		
		this.driver=driver;//can remove this as we are not using driver here directly but eleutil is using it
		eleutil = new ElementUtils(driver);
		
	}
	
	public boolean accountRegistration(String firstName,String lastname,String email,String telephone,String password,String subscribe )
	{
		eleutil.doSendKeys(this.firstname, firstName);//The most common use of the this keyword is to eliminate the confusion between class attributes and parameters with the same name 
		//(because a class attribute is shadowed by a method or constructor parameter).
		eleutil.doSendKeys(this.lastname, lastname);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.passwrd, password);
		eleutil.doSendKeys(this.passwrdconfirm, password);
		
		if (subscribe.equals("Yes")) {
			eleutil.doClick(subscibeYes);
			
		}
		else {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(agreecheckbox);
		eleutil.doClick(continuebutton);
		String mssg=eleutil.waitforElementToBeVisible(successmessg,5,1000).getText();
		
		if(mssg.contains(Constants.ACCOUNT_CREATION_SUCCESS)) {
			eleutil.doClick(logoutlnk);
			eleutil.doClick(registerlnk);
			return true;
		}
		return false;
	}
}
