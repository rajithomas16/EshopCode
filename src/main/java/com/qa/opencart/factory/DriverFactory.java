package com.qa.opencart.factory;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager op;
	
	public static ThreadLocal<WebDriver> tldriver= new ThreadLocal<WebDriver>();//java feature
	
	
	/**This method is to initialize the webdriver
	 * 
	 * @param browser
	 * @return the driver
	 *
	 */
	
	public WebDriver init_driver(Properties prop) {
		
		String browser=prop.getProperty("browser").trim();
		System.out.println("browser name:"+browser);
		highlight=prop.getProperty("highlight").trim();
		op =new OptionsManager(prop);
		
		
		if (browser.equalsIgnoreCase("chrome")) 
		{
			
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(op.getChromeOptions());	
			tldriver.set(new ChromeDriver(op.getChromeOptions()));
		} 
		else if(browser.equalsIgnoreCase("chrome")) 
		{
			
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver=WebDriverManager.firefoxdriver().create();//--new webdriver manager can create webdriver object --5.0
			
			driver=new FirefoxDriver(op.getFirefoxOptions());
			//tldriver.set(new FirefoxDriver(op.getFirefoxOptions()));
	
		}
		else if (browser.equalsIgnoreCase("safari")) 
		{
			driver=new SafariDriver();			
		
		}
		else
		{
		
			System.out.println();
	}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		//openURL(prop.getProperty("url"));
		
		URL url;
		try {
			url = new URL(prop.getProperty("url"));
			openURL(url);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		
		 return getDriver();
	}
	
	
	/**getdriver();
	 * it will return a thread local copy of webdriver**/
	
	public static synchronized WebDriver getDriver() {//JDK-1.8 feature--during parallel execution deadlock /problem when reading from same excel sheet can happen if we dont use thread local driver,like 100 test cases can be run parallelly 
		return tldriver.get();
	
	}
	
	/**
	 * This method is used to initialize properties and 
	 * 
	 * @return prop
	 */
	public Properties init_properties() {
		prop= new Properties();
		FileInputStream ip=null;
		
		String envName=System.getProperty("env");//qa//dev//uat
		
		if (envName==null) {
			System.out.println("Running on PROD env");
		
			try {
				ip=new FileInputStream("./src/test/resources/Configdata/config.properties");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			  
		}
			else {
				System.out.println("Running on the "+envName+"environment");
				try {
				switch (envName.toLowerCase()) {
				case "dev":
					
					ip=new FileInputStream("./src/test/resources/Configdata/dev.config.properties");					
					break;
				case "qa":
					ip=new FileInputStream("./src/test/resources/Configdata/qa.config.properties");
					break;
				case "uat":
					ip=new FileInputStream("./src/test/resources/Configdata/uat.config.properties");
					break;
				default:
					System.out.println("Pls pass right env");
					break;
				}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			}
			try {
				prop.load(ip);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		return prop;
	}
	
	/**take
	 * screenshots
	 * @return **/
	
	
	public String takeScreenshot() {
		
		File src=((TakesScreenshot)(getDriver())).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination=new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	/**
	 * launch URL
	 * @param URL
	 * */
	
	public void openURL(String url) {
		try {
		if (url==null) {
			throw new Exception("url is null");
		}
		}
		catch (Exception e) {
		}
		getDriver().get(url);
	}
	
	
	
	public void openURL(URL url) {
		try {
		if (url==null) {
			throw new Exception("url is null");
		}
		}
		catch (Exception e) {
		}
		getDriver().navigate().to(url);
	}
	//http://amazon.com/accpage/user.html
	public void openURL(String baseUrl,String path) {
		try {
		if (baseUrl==null) {
			throw new Exception("baseurl is null");
		}
		}
		catch (Exception e) {
		}
		getDriver().get(baseUrl+"/"+path);
	}
	//https://tutorialsninja.com/demo/index.php?route=account/login
	public void openURL(String baseUrl,String path,String queryparam) {
		try {
		if (baseUrl==null) {
			throw new Exception("baseurl is null");
		}
		}
		catch (Exception e) {
		}
		getDriver().get(baseUrl+"/"+path+"?"+queryparam);
	}
	
}
