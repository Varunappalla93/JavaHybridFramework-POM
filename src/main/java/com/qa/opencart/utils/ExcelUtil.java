package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
private static Workbook book; //as Workbook.create gives Workbook reference
private static Sheet sheet; //getSheet gives Workbook reference
private static String TestDatasheet ="./src/test/resources/testData/democartdatadriven.xlsx";

// This class is for reading the excel sheet placed in the resources/TestData

	public static Object[][] getTestData(String sheetname)
	{
		Object data[][]=null; //assign to null
		
		try {
			FileInputStream fis=new FileInputStream(TestDatasheet);
			//Apache Poi class
			book=WorkbookFactory.create(fis);
			sheet=book.getSheet(sheetname);
			
		//assign data values here and iterate using loops to fill values
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

}