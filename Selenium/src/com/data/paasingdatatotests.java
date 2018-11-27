package com.data;
import static org.testng.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class paasingdatatotests {
	static WebDriver driver;
	static String[][]logindata;
	@BeforeClass
	public static void startDriver() throws BiffException, IOException  {
		logindata=readdatafromexcel();
		print();
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	public static String[][] readdatafromexcel() throws BiffException, IOException{
		 FileInputStream fis=new FileInputStream("C:\\Users\\Sysyetm53\\Desktop\\sampledata.xls");
		  Workbook wb=Workbook.getWorkbook(fis);
		  Sheet sh1= wb.getSheet("temp");
		  int numrows=sh1.getRows();
		  int numcols=sh1.getColumns();
		 /* String[][] data = new String[numrows-1][numcols];
		  
		  for(int row=1;row<numrows;row++) {		  
			  for(int col=0;col<numcols;col++) {
				  data[row-1][col]=sh1.getCell(col, row).getContents();
			 }
		  }This is also correct*/
		  String[][] data=new String[numrows-1][numcols];
		  int rows=0;
		  int cols=0;
		  for(int row=1;row<numrows;row++) {	
			  cols=0;
			  for(int col=0;col<numcols;col++) {
				  data[rows][cols++]=sh1.getCell(col, row).getContents();
			 }
			  rows++;
		  }
		  return data;
		  
	}
	static void print() {
		int rows=logindata.length;
		int cols=logindata[0].length;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				System.out.print(logindata[i][j]+"\t");
			}
			System.out.println();
		}
		
	}
	@BeforeMethod
	public static void enterUrl() {
		driver.get("http://192.168.1.168/pos/");
	}
	@Test
	public void loginwithInvalidCred() {
		
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(logindata[0][2]);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(logindata[0][3]);
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		WebElement errmsg=driver.findElement(By.xpath("//div[@class='alert alert-danger']/div"));
		if(errmsg.isDisplayed()==true) {
			System.out.println("error is displayed");
		}
		String acterr=errmsg.getText();
		System.out.println(acterr);
		String experr="Invalid username/password";
	    assertEquals(experr, acterr);
	    
	    //error colour validation
	    String errcolor=errmsg.getCssValue("color");
	    System.out.println(errcolor);
	    String hex = Color.fromString(errcolor).asHex();
	    System.out.println(hex);
	    String experrclr="#a94442";
	    assertEquals(experrclr, hex);
		    
	}
	
	@Test
	public void loginwithValidCred() {
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(logindata[1][2]);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(logindata[1][3]);
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		String expres1="http://192.168.1.168/pos/index.php/home";
		String actres1=driver.getCurrentUrl();
		assertEquals(expres1, actres1);
		driver.findElement(By.xpath("//li[@class='btn  ']/a/span")).click();
		
		
	}
	
	@Test
	public void loginwithNullvalues() {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(logindata[2][2]);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(logindata[2][3]);
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		String expres="http://192.168.1.168/pos/index.php/login";
		String actres=driver.getCurrentUrl();
		assertEquals(expres, actres);
	}
	@AfterClass
	public static void closedriver() {
		driver.close();
	}
	
}

