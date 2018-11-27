package com.data;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class webtables2 {
	
	private static WebDriver driver;

	@BeforeClass
	public static void startDriver() throws BiffException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void enterUrl() {
		driver.get("http://192.168.1.168/pos/");
	}

	@AfterClass
	public static void closedriver() {
		driver.close();
	}
	
	
	@Test
	public void test() {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("admin@123");
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		
		driver.findElement(By.xpath("//a[text()=' Customers']")).click();
		String expectedCustId = "1078";
		WebElement customersTable = driver.findElement(By.xpath("//table[@id='sortable_table']"));
		WebElement tablebody = customersTable.findElement(By.tagName("tbody"));
		List<WebElement> rowelements = tablebody.findElements(By.tagName("tr"));
		System.out.println("no of rows present in the table is: " + rowelements.size());
		WebElement expectedCustomerrow = null;
		for (WebElement row : rowelements) {
			List<WebElement> colmns = row.findElements(By.tagName("td"));
			WebElement[] array = colmns.toArray(new WebElement[colmns.size()]);
			System.out.println(array[1].getText());
			if(expectedCustId.equals(array[1].getText().trim())) {
				expectedCustomerrow = row;
				break;
			}
		}
		
		assertNotNull(expectedCustomerrow, 
				"The customer with id :" + expectedCustId + " is not prestent in the table");
		System.out.println("*******************************");
		List<WebElement> expectcustcolms = expectedCustomerrow.findElements(By.tagName("td"));
		for(int i=0;i<expectcustcolms.size();i++) {
			System.out.println(expectcustcolms.get(i).getText());
		}
 
        System.out.println("*******************************");
	}

}

