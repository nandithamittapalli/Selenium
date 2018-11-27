package com.data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class dataproviderprog {
  static WebDriver driver;
  @BeforeClass
  public void startDriver() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
  }
  @BeforeMethod
  public void enterURL() {
	  driver.get("http://192.168.1.168/pos/");
  }
  @DataProvider
  public Map[] readData() throws BiffException, IOException {
	 FileInputStream fis=new FileInputStream("C:\\Users\\Sysyetm53\\Desktop\\sampledata.xls");
	 Workbook wb=Workbook.getWorkbook(fis);
	 Sheet sh=wb.getSheet("temp");
	 int rows=sh.getRows();
	 int cols=sh.getColumns();
	 Map[] m=new Map[rows-1];
	 for(int row=1;row<rows;row++) {
		 HashMap<String,String> hm=new HashMap<String, String>();
		 for(int col=0;col<cols;col++) {
			 String key=sh.getCell(col,0).getContents();
			 String value=sh.getCell(col, row).getContents();
			 hm.put(key, value);
		 }
		 m[row-1]=hm;
	 }
	 System.out.println(m);
	return m;
 }
  @Test(dataProvider="readData")
  public void login(Map<String, String> data) {
	  System.out.println("trying login using data: " + data);
	  driver.findElement(By.xpath("//input[@id='username']")).sendKeys(data.get("Username"));
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys(data.get("Password"));
	  driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
  }
  @AfterClass
  public void closeDriver() {
	  driver.close();
  }
}

