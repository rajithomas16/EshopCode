package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtils{
		
	//if methods are made static in utilities,then one same driver in CMA and accessed by all tests and 
	//will delay too much as it is sequential execution,if non static each will have its own copy of drivers
		private WebDriver driver;
		private JavaScriptUtil jsutil;
		
		public ElementUtils(WebDriver driver) {//constructor called when an object of elemntutil class is created
			this.driver=driver;//driver in red here is chromedriver and it is passed to  var driver(in blue) and 
			//then used as driver by remaining methods down in blue
			jsutil=new JavaScriptUtil(driver);
			
		}
		
		public By getBy(String locatortype,String locatorvalue) {
			 
			 By locator=null;//what if no case is satisfied then it has to return null..so have to initialize to default value of class ..ie..null
			 
			 switch (locatortype.toLowerCase()) {
			case "id":
				 locator=By.id(locatorvalue);
				break;
			case "name":
				 locator=By.name(locatorvalue);
				break;
			case "classname":
				 locator=By.className(locatorvalue);
				break;
			case "linktext":
				 locator=By.linkText(locatorvalue);
				break;
			case "xpath":
				 locator=By.xpath(locatorvalue);
				break;
			case "cssselector":
				 locator=By.cssSelector(locatorvalue);
				break;

			default:
				System.out.println("pass right browser ");
				break;
			}
			 return locator;
			 
		 }
		//get element for single element from in element using by locator
		public WebElement getElement(By locator) {//to return the web element using locator as input
			WebElement element=driver.findElement(locator);
			if (Boolean.parseBoolean(DriverFactory.highlight)) {
				
				jsutil.flash(element);
			}		
			return element;
		}
		//get list of web elements from find elements using by locator
		public List<WebElement> getElements(By locator) {//to return the web element using locator as input
			
			return driver.findElements(locator);
		}
		//get element by string type and string value input
		public  WebElement getElement(String locatortype,String locatorvalue) {//for 7th approach
			
			 return driver.findElement(getBy(locatortype, locatorvalue));
		}
		//do send keys methods using by locator and value or test data for send keys
		
		public void doClear(By locator) {
			getElement(locator).clear();
			
		}
		public void doSendKeys(By locator,String value) {//action of sending value to input field using string value...no need to call getElemnt can call do sendkeys
			doClear(locator);
			getElement(locator).sendKeys(value);
		}
		//do sendkeys using locator type  and locator value and string value for send keys
		public void doSendKeys(String locatortype,String locatorvalue,String value) {//for 7th approach 
			
			getElement(locatortype,locatorvalue).sendKeys(value);
		}
		
		//do click by locator
		public void doClick(By Locator) {//wrapper method--our own  customized method using the selenium functions itself
			
			getElement(Locator).click();
		}	
		//do click string 
		public void doClick(String locatortype,String locatorvalue) {//wrapper method--our own  customized method using the selenium functions itself
			
			getElement(locatortype,locatorvalue).click();
			
			
		}
		//do get text by locator
		public String doGetText(By Locator) {
			
			return getElement(Locator).getText();
			
		}
		
		//get attribute value
		public String getattributevalue(By locator,String attrname) {
			
			return getElement(locator).getAttribute(attrname);
		}
		//do is displayed
		public boolean doIsDisplayed(By locator) {//to return the boolean flag using locator as input
			
			return getElement(locator).isDisplayed();
				}
		//print values of list
		public  void printArraylist(List<String> elist) {//utility to print all values
		
		for (String actuallist  : elist) {
			
			System.out.println(actuallist);
			
		}


		}
		//get count of all elements
		public  int getElementsCount(By locator) {
			
			return getElements(locator).size();


			}
		
		
		//utility method to return the list of attributes eg:alt src in img html dom 
				
		public  List<String> getAttribute(By locator, String attributename) {
			
			List<WebElement>elist= getElements(locator);
			
			List<String>eTextList= new ArrayList<String>();
			
			for (WebElement e : elist) {
				
				String attrValue=e.getAttribute(attributename);
				
				if (!attrValue.isBlank()) {
					eTextList.add(attrValue);
					
						}
				
			}
			 return eTextList;

			}
	
		public  String getAttributeval(By locator,String attrname) {
			 
			String value=getElement(locator).getAttribute(attrname);
			System.out.println(value);
			return value;
			
		}

		public  boolean IsElementExist(By locator) {
			
			int elecount=getElements(locator).size();
			if (elecount>=1) {
				System.out.println("ele found");
				return true;
				
			} else {
				System.out.println("ele not found");
				return false;
			}
			
			
		}
//*****************DROPDOWNS************************************************************
		
		public  void doDropdownselectbyindex(By locator,int index) {
			
			Select select=new Select(getElement(locator));
			select.selectByIndex(index);
		}
		public  void doDropdownselectbyvisibletext(By locator,String text) {
			
			Select select=new Select(getElement(locator));
			select.selectByVisibleText(text );
		}
		public void doDropdownselectbyvalue(By locator,String value) {
		
		Select select=new Select(getElement(locator));
		select.selectByValue(value);
	}
		public void doDropdownwithoutselectmethods(By locator,String value) {
			
			Select select=new Select(getElement(locator));
			List<WebElement>optionslist=select.getOptions();
			for (WebElement e : optionslist) {
				
				String text=e.getText();
				System.out.println(text);
				if (text.equals(value)) {
					e.click();
					break;
				}
				
			}
		}
		
	


//****************************LInks Utils******************************************
		
		//here we are not using by.tagname(a) approach as there an be many links on the page and it can cause performance issues

public  void  clickonlanglink(By locator,String linktext) {
	
	List<WebElement>linklist=getElements(locator);
	System.out.println("linklist.size()");
	
	for (WebElement e: linklist) {
		String text=e.getText().trim();
		if (text.equals("Français")) 
		{
			
			e.click();
			break;
			
		}
		
	}
}

public List<String> getlinks(By locator,String linktext) {
	
	List<WebElement>linklist=getElements(locator);
	List <String> linkstextlist= new ArrayList<String>();
	System.out.println("linklist.size()");
	
	for (WebElement e: linklist) {
		String text=e.getText().trim();
		 linkstextlist.add(text);

		}
	return linkstextlist;
	}
//**************Webtable util************


public  void printTable(By rowLocator,By colLocator,String beforeXpath,String afterXpath) {
	
	int rowcount=getElements(rowLocator).size();
	
	int colcount=getElements(colLocator).size();
	
	for (int row = 2; row <= rowcount; row++) {
		
		for (int col = 1; col <=colcount; col++) {
		
			String xpath=beforeXpath+row+afterXpath+col+"]";
			
			String text=doGetText(By.xpath(xpath));
			
			System.out.println(text+"     |         ");
			
		}
		System.out.println();//new cline after one row all cols are printed out//
	}
		
	}

//*********Actions class Util*******************//


public void doMoveToElement(By locator) {
	
	Actions act= new Actions(driver);
	
	act.moveToElement(getElement(locator));	
	
}

public void clickonChildMenu(By parentmenulocator,By childmenulocator) {
	doMoveToElement(parentmenulocator);
	doClick(childmenulocator);
	
}
public  void doActionsSendKeys(By locator,String value) {
	
	Actions act= new Actions(driver);
	
	act.sendKeys(getElement(locator), value).build().perform();
}
public  void doActionsSendKeysOnactiveElement(By locator,String value) {
	
	Actions act= new Actions(driver);
	
	act.click(getElement(locator)).sendKeys(value).build().perform();
	
}

public  void doActionClick(By locator) {
	
	Actions act= new Actions(driver);
	
	act.click(getElement(locator)).build().perform();
	/***
	Actions org.openqa.selenium.interactions.Actions.click(WebElement target)
	Clicks in the middle of the given element. Equivalent to: Actions.moveToElement(onElement).click()
	Parameters:target Element to click.***/
	//diff b/n actions click and webelement click //
	
}
//**********************Wait Utils************************//

public  void doSendKeyswithwait(By locator,String value,int timeOut) {
	waitforPresenceOfElementlocated(locator, timeOut).sendKeys(value);;
	
	
}
public WebElement waitforPresenceOfElementlocated(By locator,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

/**ExpectedCondition<WebElement> org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(By locator)


An expectation for checking that an element is present on the DOM of a page. 
This does notnecessarily mean that the element is visible.**/

	
}
public WebElement waitforPresenceOfElementlocated(By locator,int timeOut,int pollingTime) {
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(pollingTime));
	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

}
public  WebElement waitforElementToBeVisible(By locator,int timeOut) {
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}
public  WebElement waitforElementToBeVisible(By locator,int timeOut,int pollingTime) {
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(pollingTime));
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}
/***
ExpectedCondition<WebElement> org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By locator)


An expectation for checking that an element is present on the DOM of a page and visible.Visibility 
means that the element is not only displayed but also has a height and width that isgreater than 0.
**/

public  List<WebElement> waitforElementsToBeVisible(By locator,int timeOut) {
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

}

public  List<WebElement> waitforElementsToBeVisible(By locator,int timeOut,int pollingTime) {

WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(pollingTime));
return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

}
public List<String> getElementsTextListWithWait(By locator,int timeOut) {
	
	List<WebElement>elemlist=waitforElementsToBeVisible(locator, timeOut);
	
	List<String>textlist=new ArrayList<String>();
	for (WebElement e : elemlist) {
		
		String text=e.getText();
		textlist.add(text);
	
	}
	return textlist;
	
	
}

//***************************Wait for Non WebElements Utils********************//

public String doGettitle(String titlefraction,int timeOut) {
	if (waitfortitleContains(titlefraction, timeOut)) {
		return driver.getTitle();
		
	}
	return null;
}

public boolean waitfortitleContains(String titlefraction,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.titleContains(titlefraction));
}
public boolean waitfortitletobe(String title,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.titleIs(title));
}
public boolean waitforURLContains(String urlfraction,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.urlContains(urlfraction));
}
public  boolean waitforURLToBe(String url,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.urlToBe(url));
}
public  Alert waitForAlert(int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	return wait.until(ExpectedConditions.alertIsPresent());
}
public String getAlertText(int timeOut) {
	
	return waitForAlert(timeOut).getText();
}
public void doAlertAccept(int timeOut) {
	
	waitForAlert(timeOut).accept();
}
public void doAlertDismiss(int timeOut) {
	
	waitForAlert(timeOut);
}
public  void enterAlertText(String text,int timeOut) {
	
	 waitForAlert(timeOut).sendKeys(text);;
}

public  void waitforFrameByNameorID(String nameorID,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameorID));
}
public void waitforFrameByIndex(int Index,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Index));
}
public void waitforFrameByLocator(By locator,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
}
public void waitforFrameWebElement(WebElement frameelement,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameelement));
}
public  void clickelementwhenready (By locator,int timeOut) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
}
public void clickelementwhenready (By locator,int timeOut,int pollingTime) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(pollingTime));
	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
}

//*************Fleunt wait and WebDriverWait Utils***************

//fluentwait definitions--explicitly wait

public  WebElement waitforElementPresentUsingFluentWait(By locator,int timeOut,int pollingTime) {
	Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(timeOut))
			.pollingEvery(Duration.ofSeconds(pollingTime))//builder pattern--each method returns self
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.withMessage(Errors.ELEMENT_NOT_FOUND_ERROR_MSG);

	return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public  WebElement waitforElementusingWebDriverwait(By locator,int timeOut,int pollingTime) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				
				wait.pollingEvery(Duration.ofSeconds(pollingTime))//builder pattern--each method returns self
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage(Errors.ELEMENT_NOT_FOUND_ERROR_MSG);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));



}
	//***********************Custom Wait********************************//
	public WebElement retryingElement(By locator,int timeOut) {//to return the web element using locator as input
		
		WebElement element= null;
		int attempt=0;
		
		while (attempt<timeOut) {
			try {
			element=getElement(locator);
			break;
			}
			catch (NoSuchElementException e) {
				System.out.println("elemnt is not found: "+attempt+":"+locator);
				try {
					Thread.sleep(500);
					
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempt++;
		}
		if (element==null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("elem not found after :"+timeOut+"with intervel of +"+500+"ms");
			}
			
		}
		return element;
			
		
	}
public  WebElement retryingElement(By locator,int timeOut,int pollingtime) {//to return the web element using locator as input
		
		WebElement element= null;
		int attempt=0;
		
		while (attempt<timeOut) {
			try {
			element=getElement(locator);
			break;
			}
			catch (NoSuchElementException e) {
				System.out.println("elemnt is not found: "+attempt+":"+locator);
				try {
					Thread.sleep(pollingtime);
					
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempt++;
		}
		if (element==null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("elem not found after :"+timeOut+"with intervel of +"+pollingtime+"ms");
			}
			
		}
		return element;
			
		
	}

}



