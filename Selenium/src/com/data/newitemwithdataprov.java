package com.data;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class newitemwithdataprov {
  static WebDriver driver;
  @BeforeClass
  public void startDriver(){
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
      driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://192.168.1.168/pos/");
	  driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("admin@123");
	  driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
	  driver.findElement(By.xpath("//a[text()=' Items']")).click();
	  driver.findElement(By.xpath("//span[text()='New Item']")).click();
  }
  @BeforeMethod
  public void clearAllFields() {
	  driver.findElement(By.xpath("//input[@id='name']")).clear();
	  driver.findElement(By.xpath("//input[@id='category']")).clear();
	  driver.findElement(By.xpath("//input[@id='cost_price']")).clear();
	  driver.findElement(By.xpath("//input[@id='unit_price']")).clear();
  }
  @DataProvider
  public LinkedHashMap[] readData() throws BiffException, IOException {
	  FileInputStream fis=new FileInputStream("C:\\Users\\Sysyetm53\\Desktop\\sampledata.xls");
	  Workbook wb=Workbook.getWorkbook(fis);
	  Sheet sh= wb.getSheet("Item");
	  int numrows=sh.getRows();
	  int numcols=sh.getColumns();
	  LinkedHashMap[] mp=new LinkedHashMap[numrows-1];
	  for(int row=1;row<numrows;row++) {
		  LinkedHashMap<String, String> hm=new LinkedHashMap<String,String>();
		  for(int col=0;col<numcols;col++) {
			  String key=sh.getCell(col, 0).getContents();
			  String value=sh.getCell(col, row).getContents();
			  hm.put(key, value);
		  }
		  mp[row-1]=hm;
	  }
	  return mp;
  }
  @Test(dataProvider="readData")
  public void newItemValidations(LinkedHashMap<String,String> data) throws InterruptedException {
	  System.out.println(data);
	  String itemanme=data.get("ItemName");
	  String category=data.get("Category");
	  String costprice=data.get("Costprice");
	  String sellingprice=data.get("Sellingprice");
	  driver.findElement(By.xpath("//input[@id='name']")).sendKeys(itemanme);
	  driver.findElement(By.xpath("//input[@id='category']")).sendKeys(category);
	  driver.findElement(By.xpath("//input[@id='cost_price']")).sendKeys(costprice);
	  driver.findElement(By.xpath("//input[@id='unit_price']")).sendKeys(sellingprice);
	  driver.findElement(By.xpath("//input[@id='submitf']")).click();
	  Thread.sleep(5000);
	 
	  if(itemanme.isEmpty()) {
		  WebElement err=driver.findElement(By.xpath("//span[@for='name']"));
		  String experr="Item Name is a required field";
		  String acterr=err.getText();
		  if(err.isDisplayed()==true) {
			  System.out.println("error is displayed for nullitemname");
			  assertEquals(acterr, experr);
		  }	 
	  }
	  if(category.isEmpty()) {
		  WebElement err=driver.findElement(By.xpath("//span[@for='category']"));
		  String experr="Category is a required field";
		  String acterr=err.getText();
		  if(err.isDisplayed()==true) {
			  System.out.println("error is displayed for nullcategory");
			  assertEquals(acterr, experr);
		  }
	  }
	  if(costprice.isEmpty()) {
		  WebElement err=driver.findElement(By.xpath("//span[@for='cost_price']"));
		  String experr="Cost Price is a required field";
		  String acterr=err.getText();
		  if(err.isDisplayed()==true) {
			  System.out.println("error is displayed for nullcostprice");
			  assertEquals(acterr, experr);
		  }
	  }
	  if(sellingprice.isEmpty()) {
		  WebElement err=driver.findElement(By.xpath("//span[@for='unit_price']"));
		  String experr="Selling Price is a required field";
		  String acterr=err.getText();
		  if(err.isDisplayed()==true) {
			  System.out.println("error is displayed for nullsellingprice");
			  assertEquals(acterr, experr);
	      }
	  }
	  if(data.get("Description").equals("InvalidCostPrice")) {
		  WebElement err=driver.findElement(By.xpath("//span[@for='cost_price']"));
		  String experr="Cost price must be a number";
		  String acterr=err.getText();
		  if(err.isDisplayed()==true) {
			  System.out.println("error is displayed for invalid cost price");
			  assertEquals(acterr, experr);
	      }
	  }
	  if(data.get("Description").equals("InvalidSellingPrice")) {
		  WebElement err=driver.findElement(By.xpath("//span[@for='unit_price']"));
		  String experr="Unit price must be a number";
		  String acterr=err.getText();
		  if(err.isDisplayed()==true) {
			  System.out.println("error is displayed for invalid selling price");
			  assertEquals(acterr, experr);
	      }
	  }
  }
	  
  @AfterClass
  public void closeDriver() {
	  driver.close();
  }
}
