package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {
	
	
	private WebDriver driver;//local driver of the LoginPage class
	private ElementUtils eleutil;
	
	public AccountsPage(WebDriver driver) //constructor 
	{
		this.driver=driver;
		eleutil= new ElementUtils(driver);
	}
	
	//3.By locators-private
	
	private By header= By.cssSelector("div#logo a");
	private By accountssections= By.cssSelector("div#content h2");
	private By searchField= By.name("search");
	private By searchbutton= By.xpath("//button[@class='btn btn-default btn-lg']");
	private By logoutlnk= By.linkText("Logout");
	
	//4.Page Actions
	
	
	
	public String getaccountpagetitle() {
		
		return eleutil.doGettitle(Constants.ACCOUNT_PAGE_TITLE,Constants.DEFAULT_PAGE_TIMEOUT);
	}
	public String getaccountpageheader() {
		return eleutil.doGetText(header);
	}
	public boolean islogoutpresent() {
		return eleutil.doIsDisplayed(logoutlnk);
	}
	public void logout() {
		if(islogoutpresent()) {
			eleutil.doClick(logoutlnk);
		}
	}
	public List<String> getaccountsectionlst() {
		
		List <WebElement> accsectionslist=eleutil.waitforElementsToBeVisible(accountssections, 10);
		
		List<String> accsectionstrings=new ArrayList<String>();
		
		for (WebElement e : accsectionslist) {
			
			String text=e.getText();
			accsectionstrings.add(text);
		}
		return accsectionstrings;
	}
	public boolean issearchpresent() {
		return eleutil.doIsDisplayed(searchField);
	}
	
	public SearchResultPage doSearch(String productname) {//page chaining
		System.out.println("Searching for :"+productname);
		eleutil.doSendKeys(searchField, productname);
		eleutil.doClick(searchbutton);
		
		return new SearchResultPage(driver);//search result page class object for page chaining
		
	}
}
