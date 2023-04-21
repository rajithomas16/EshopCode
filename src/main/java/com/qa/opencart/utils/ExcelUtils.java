 package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	public static final String TEST_DATA_SHEET_PATH="C:/Users/rajit/OneDrive/Desktop/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	/**Generic utility for any kind of 
	 * read operation from excel sheet
	 * reading excel shee row and col  and storing it in a data array
	 * **/
	
	public static Object[][] getTestData(String sheetName) 	
	{
		Object data[][]=null;
		
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);//to connect with the file path
			book=WorkbookFactory.create(ip);
			sheet=book.getSheet(sheetName);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0;  i< sheet.getLastRowNum(); i ++) {
			for (int j = 0; j <sheet.getRow(0).getLastCellNum() ; j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j);				
			}
			
		}
	return 	data;
	}
}
