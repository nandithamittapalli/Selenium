package com.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class readdatafromexcel {
  @Test
  public void readdata() throws BiffException, IOException  {
	  
	  FileInputStream fis=new FileInputStream("C:\\Users\\Sysyetm53\\Desktop\\sampledata.xls");
	  Workbook wb=Workbook.getWorkbook(fis);
	  Sheet sh1= wb.getSheet("temp");
	  int numrows=sh1.getRows();
	  int numcols=sh1.getColumns();
	  for(int row=0;row<numrows;row++) {
		  System.out.print("row"+row+"\t");
		  for(int col=0;col<numcols;col++) {
			  String content=sh1.getCell(col, row).getContents();
			  System.out.print(content+"\t");
			  
		  }
		  System.out.println();
	  }
	  
  }
	  
}

