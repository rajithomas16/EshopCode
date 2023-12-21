package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {
	private WebDriver driver;//local driver of the LoginPage class//can remove this as we are not using driver in this page directly but eleutil is using it
	private ElementUtils eleutil;
	
	
	private By productHeader=By.xpath("//div[@id='content']//h1");

	
	private By images=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By priceMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty=By.id("input-quantity");
	private By addtocartbutton=By.id("button-cart");
	
	private Map<String,String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) //constructor 
	{
		this.driver=driver;//can remove this as we are not using driver here directly but eleutil is using it
		eleutil= new ElementUtils(driver);
	}
	
	public String getProductHeader() {
		
	 String productHeaderText=eleutil.doGetText(productHeader);
	 
	 System.out.println("Product Name displayed is "+productHeaderText);
	 
	return productHeaderText;
		
	}
	
	public int getImagesCount() {
		
		int imagesCount=eleutil.waitforElementsToBeVisible(images, 10, 2000).size();
		return imagesCount;
		 
			
		}
	public Map<String, String> getProductInfo() {
		
		productInfoMap=new LinkedHashMap<String,String>();
		productInfoMap.put("productName",getProductHeader());
		getProductMetaData();//make it private
		getPriceMetaData();//make it private
		return productInfoMap;
	}
	
	private void getProductMetaData() {//private if made public testng will start calling them and it is already declared inside getProductinfo above
		List<WebElement>metadatalist=eleutil.getElements(productMetaData);		
		//METADATA 
		/**MacBook Pro
			Brand: Apple
			Product Code:Product 18
			Reward Points:800
			Availability:In Stock
*/
		for (WebElement e : metadatalist) {
			String text=e.getText();
			String meta[]=text.split(":");
			String metakey=meta[0].trim();
			String metavalue=meta[1].trim();
			
			productInfoMap.put(metakey, metavalue);//fill the hashmap one by one
			
		}
	}//ask S if this needs return type
	private void getPriceMetaData() {//private if made public testng will start calling them and it is already declared inside getProductinfo above
		
		List<WebElement> metaPriceList=eleutil.getElements(priceMetaData);		
		//METADATA 
		/**
			$2,000.00
		Ex Tax:$2,000.00
		 */
		String mainPrice=metaPriceList.get(0).getText();
		String extPrice=metaPriceList.get(1).getText();
			
			productInfoMap.put("price",mainPrice);//fill the hashmap for first one --key create ourown 
			productInfoMap.put("extprice",extPrice);
		}
		
	}

