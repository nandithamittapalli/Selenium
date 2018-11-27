package com.data;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class mouseactions {
static WebDriver driver;
@BeforeClass
public void startDriver() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

}
@Test
public void mouseact() throws InterruptedException {
	driver.get("https://retail.onlinesbi.com/retail/login.htm");
	driver.findElement(By.xpath("//a[text()='CONTINUE TO LOGIN']")).click();
	Actions act=new Actions(driver);
	WebElement username=driver.findElement(By.xpath("//input[@id='username']"));
	act.contextClick(username).build().perform();
	driver.switchTo().alert().accept();
	Thread.sleep(3000);
	WebElement password=driver.findElement(By.xpath("//input[@id='label2']"));
	act.contextClick(password).build().perform();
	driver.switchTo().alert().accept();
	Thread.sleep(3000);
	username.sendKeys("232");
	password.sendKeys("ffds");
	driver.findElement(By.xpath("//input[@id='Button2']")).click();
	String acterr=driver.findElement(By.xpath("//div[@class='err_mssg']")).getText();
	String experr="Invalid Username or Password";
	assertEquals(acterr, experr);
	
}
@AfterClass
public void closeDriver() {
	driver.close();
}
}
