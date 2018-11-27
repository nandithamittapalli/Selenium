package com.data;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class drivernavigations {
static WebDriver driver;	
@BeforeClass
public void startDriver() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
@Test
public void navidatedriver() {
	driver.get("https://www.seleniumhq.org/");
	driver.findElement(By.xpath("//input[@id='q']")).sendKeys("homepage");
	driver.findElement(By.xpath("//a[text()='Projects']")).click();
	driver.findElement(By.xpath("//input[@id='q']")).sendKeys("projects");
	driver.findElement(By.xpath("//a[text()='Download']")).click();
	driver.findElement(By.xpath("//input[@id='q']")).sendKeys("downloads");
	driver.navigate().back();
	System.out.println(driver.findElement(By.xpath("//input[@id='q']")).getAttribute("value"));
	driver.navigate().forward();
	System.out.println(driver.findElement(By.xpath("//input[@id='q']")).getAttribute("value"));
}
@AfterClass
public void closeDriver() {
	driver.close();
}
}
