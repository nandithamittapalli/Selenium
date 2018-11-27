package com.data;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class screenshot {
static WebDriver driver;
public static String Screenshotname;
@BeforeClass
public void startDriver() {
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
    driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
@BeforeMethod
public void enterURL() {
	driver.get("https://jqueryui.com/support/");
}
@Test
public void takeScreenshot() throws IOException, InterruptedException{
	
	File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");  
    Date date = new Date();  
    String datetime=formatter.format(date);
    System.out.println(datetime);  
	FileUtils.copyFile(screenshot, new File("D:\\Target\\test_"+datetime+".png"));
	Thread.sleep(5000);
}
@Test
public void moveslider() throws InterruptedException {
	driver.findElement(By.xpath("//a[text()='Slider']")).click();
	driver.switchTo().frame(0);
	WebElement slider=driver.findElement(By.xpath("//div[@id='slider']/span"));
	Actions act=new Actions(driver);
	act.dragAndDropBy(slider, 40, 0).build().perform();
	
	
	Thread.sleep(3000);
}
@Test
public void draganddrop() throws InterruptedException {
	driver.findElement(By.xpath("//a[text()='Droppable']")).click();
	driver.switchTo().frame(0);
	WebElement drag=driver.findElement(By.xpath("//div[@id='draggable']"));
	WebElement drop=driver.findElement(By.xpath("//div[@id='droppable']"));
	Actions act=new Actions(driver);
	act.dragAndDrop(drag, drop).build().perform();
	Thread.sleep(5000);
}
@AfterClass
public void closeDriver() {
	driver.close();
}
}
