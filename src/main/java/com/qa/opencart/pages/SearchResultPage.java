package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

public class SearchResultPage {
	
	private WebDriver driver;//local driver of the LoginPage class
	private ElementUtils eleutil;
	
	
	public SearchResultPage(WebDriver driver) //constructor 
	{
		this.driver=driver;
		eleutil= new ElementUtils(driver);
	}
	private By productelements= By.cssSelector("div.caption a");
	
	//4.Page Actions
	
	public int productlistcount(String productname) {
		int resultcount=eleutil.waitforElementsToBeVisible(productelements, 10, 2000).size();
		System.out.println("Search Result count:" +resultcount);
		return resultcount;
		
	}
	
	public ProductInfoPage selectproduct(String mainProductname) {
		
		System.out.println("Main Product is :" +mainProductname);
		List<WebElement> productslist=eleutil.waitforElementsToBeVisible(productelements, 10, 2000);
		for (WebElement e : productslist) {
			String text=e.getText();
		  if (text.equalsIgnoreCase(mainProductname)){
			  e.click();
			  break;
		  }
		  }
		  return new ProductInfoPage(driver);// on the fly creating pages/classes--TDD example
			
	}
	

}
