package com.data;
import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class webtables {
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
@Test
public void searchCustomer() throws InterruptedException {
	driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("admin@123");
	driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
	
	driver.findElement(By.xpath("//a[text()=' Customers']")).click();
	WebElement custTable=driver.findElement(By.xpath("//table[@id='sortable_table']"));
	WebElement tableBody=custTable.findElement(By.tagName("tbody"));
	List<WebElement> tablerows=tableBody.findElements(By.tagName("tr"));
	System.out.println(tablerows.size());
	String expcustid="1052";
	WebElement expectedCustomerrow = null;
	for(WebElement row:tablerows) {
		List<WebElement> rowdata=row.findElements(By.tagName("td"));
		WebElement[] array = rowdata.toArray(new WebElement[rowdata.size()]);
		   if(array[1].getText().equals(expcustid)) {
				expectedCustomerrow=row;
				break;
			}
	}
	assertNotNull(expectedCustomerrow, 
				"The customer with id :" + expcustid + " is not prestent in the table");
	expectedCustomerrow.findElement(By.xpath("//*[text()='Edit']")).click();
	WebElement mobNumber=driver.findElement(By.xpath("//input[@id='phone_number']"));
	mobNumber.clear();
	mobNumber.sendKeys("4567894356");
	WebElement email=driver.findElement(By.xpath("//input[@id='email']"));
	email.clear();
	email.sendKeys("dsasf@mail.com");
	Thread.sleep(6000);
	driver.findElement(By.xpath("//input[@id='submitf']")).click();
}
@AfterMethod
public void closeDriver(){
	driver.close();
}
}
