package com.data;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class fileupload {
 WebDriver driver;
 @BeforeClass
 public void startDriver()  {
	 System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 }
 @Test
 public void login() throws AWTException, InterruptedException {
	 driver.get("https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
	 driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("dsfsdf");
	 driver.findElement(By.xpath("//span[text()='Next']")).click();
	 driver.findElement(By.xpath("//input[@name='password']")).sendKeys("dgsdg");
	 driver.findElement(By.xpath("//div[@id='passwordNext']/content/span")).click();
	 driver.findElement(By.xpath("//div[text()='Compose']")).click();
	 driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']")).click();
	 uploadFile("C:\\Users\\System 17\\Desktop\\nanditha_manpres (2).docx");
	 Thread.sleep(5000);
 }
 
@AfterClass
public void closeDriver() {
	driver.close();
}

 public void uploadFile(String filelocation) throws AWTException {
	 setClipBoardData(filelocation);
	 Robot robot=new Robot();
	 robot.keyPress(KeyEvent.VK_CONTROL);
	 robot.keyPress(KeyEvent.VK_V);
	 robot.keyRelease(KeyEvent.VK_V);
	 robot.keyRelease(KeyEvent.VK_CONTROL);
	 robot.keyPress(KeyEvent.VK_ENTER);
	 robot.keyRelease(KeyEvent.VK_ENTER);
 }
 
 public void setClipBoardData(String fileloc) {
	StringSelection stringSelection = new StringSelection(fileloc);
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
 }
 
 

}
