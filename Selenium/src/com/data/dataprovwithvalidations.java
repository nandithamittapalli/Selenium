package com.data;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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

public class dataprovwithvalidations {
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
  public HashMap[] readData() throws BiffException, IOException {
	  FileInputStream fis=new FileInputStream("C:\\Users\\Sysyetm53\\Desktop\\sampledata.xls");
	  Workbook wb=Workbook.getWorkbook(fis);
	  Sheet sh=wb.getSheet("temp");
	  int numrows=sh.getRows();
	  int numcols=sh.getColumns();
	  HashMap[] mp=new HashMap[numrows-1];
	  for(int row=1;row<numrows;row++) {
		  HashMap<String, String> hm=new HashMap<String,String>();
		  for(int col=0;col<numcols;col++) {
			  String key=sh.getCell(col,0).getContents();
			  String value=sh.getCell(col,row).getContents();
			  hm.put(key,value);
		  }
		  mp[row-1]=hm;
	  }
	  return mp;
  }
  @Test(dataProvider="readData")
  public void loginwithvalidations(HashMap<String, String> data) {
	  System.out.println(data);
	  String Usrname=data.get("Username");
	  String pwd=data.get("Password");
	  driver.findElement(By.xpath("//input[@id='username']")).sendKeys(Usrname);
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd);
	  driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
	  if(Usrname.isEmpty()||pwd.isEmpty()) {
		  String expres="http://192.168.1.168/pos/index.php/login";
		  String actres=driver.getCurrentUrl();
		  assertEquals(expres, actres);	
	  }
	  else if(data.get("Description").equals("Invalid cred")){
		  WebElement errmsg=driver.findElement(By.xpath("//div[@class='alert alert-danger']/div"));
		  if(errmsg.isDisplayed()==true) {
				System.out.println("error is displayed");
			}
		  else {
			  System.out.println("error is not displayed");
		  }
		  String acterr=errmsg.getText();
		  System.out.println(acterr);
		  String experr="Invalid username/password";
		  assertEquals(experr, acterr);
	  }
	  else {
		  String expres1="http://192.168.1.168/pos/index.php/home";
		  String actres1=driver.getCurrentUrl();
		  assertEquals(expres1, actres1);
		  driver.findElement(By.xpath("//li[@class='btn  ']/a/span")).click();
	  }
  }
  
  @AfterClass
  public void closeDriver() {
	  driver.close();
  }
}
