package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final int DEFAULT_PAGE_TIMEOUT = 5;
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_HEADER = "Qafox.com";
	public static final int IMAC_IMAGES_COUNT =3;
	public static final int MACBOOK_IMAGES_COUNT =4;
	public static final String LOGIN_ERROR_MSSG = "No match for E-Mail Address and/or Password.";
	public static final String ACCOUNT_CREATION_SUCCESS = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "registration";
	
	
	public static List<String> getExpectedSectionList() 
	{
		List<String> expectedList=new ArrayList<String>();
		expectedList.add("My Account");
		expectedList.add("My Orders");
		expectedList.add("My Affiliate Account");
		expectedList.add("Newsletter");
		return expectedList;
		
	}
	

}
