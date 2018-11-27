package com.data;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class multipletabsusingonedriver {
static WebDriver driver;
@BeforeClass
public void startDriver() {
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
@Test
public void multtabs() {
	driver.get("https://www.google.com/");
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
	driver.get("https://www.naukri.com/manual-testing-jobs-in-hyderabad-secunderabad");	
}
/*@AfterClass
public void closeDriver() {
	driver.quit();
}*/
}
