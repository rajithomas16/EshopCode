package com.qa.opencart.utils;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	
	
	public JavaScriptUtil(WebDriver driver) {//constructor called when an object of elemntutil class is created
		this.driver=driver;//driver in red here is chromedriver and it is passed to class var driver(in blue) and 
		//then used as driver by remaining methods down in blue 
		
	}
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 4; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public String getTitleByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
	}

	public void refreshBrowserByJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().dismiss();
	}

	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);//driver.find.click and actions.click-->elemnt in page 
		//javascript.click--->inside DOM and try to click not avail on page-->not recomm
	}

	public void sendKeysUsingWithId(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}

	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}

	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void waitforPageLoaded() {//to return the web element using locator as input
		
		JavascriptExecutor js= (JavascriptExecutor) driver;//casting //double num=10.99,//convert num to int//int data=(int)num 
		//convert driver to javascriptexecutor
		
		String loadingstatus=js.executeScript("return document.readyState;").toString();
		if (loadingstatus.equals("complete")) {
			System.out.println("Page loading complete ");
		} else {
			for (int i=1; i <=20; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				loadingstatus=js.executeScript("return document.readyState;").toString();
				System.out.println("current page loading status is :"+loadingstatus);
				if (loadingstatus.equals("complete")) {
					break;					
				}
			}

		}
	}
	
	}


