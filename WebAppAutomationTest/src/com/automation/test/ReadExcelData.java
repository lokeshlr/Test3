package com.automation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ReadExcelData {

	public  List<String> testParam =null;
	public static void main(String[] args) throws IOException{
		writeResultToExcel("C:\\Users\\LY\\Desktop\\testData.xls",1,"31342");
	}

	public  void readExcelExecutionControllerFile(String filePath, int sheetnum) throws Exception{
		testParam = new ArrayList<>();
		FileInputStream file = new FileInputStream(new File(filePath));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(sheetnum);
		HSSFRow row =null;
		Cell cell = null;
		try {
			int number = sheet.getLastRowNum();
			int i = 0;
			while (i < number+1) {
				row = sheet.getRow(i);
				cell = row.getCell(0);
				int lastCellNumberInRow =row.getLastCellNum();
				String readTestCaseName = cell.getStringCellValue();
				//System.out.println(readTestCaseName);
				int j=1;
				if(readTestCaseName.contains("TestParameters")){
					while(j < lastCellNumberInRow){
						testParam.add(row.getCell(j).getStringCellValue());
						j++;
					}
				}
				i++;
			}
				//System.out.println(testParam);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void writeResultToExcel(String filePath ,int sheetnum,String customerId) throws IOException{
		FileInputStream file = new FileInputStream(new File(filePath));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(sheetnum);
		HSSFRow row =null;
		Cell cell = null;
		int i = 0;
			row = sheet.getRow(i);
			
			cell =row.getCell(1);
			System.out.println(cell.getStringCellValue());
			cell.setCellValue(customerId);			
		file.close();
		FileOutputStream outFile =new FileOutputStream(new File(filePath));
		workbook.write(outFile);
		outFile.close();
	}

}


