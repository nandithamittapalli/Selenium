import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class windowhandling {
	static WebDriver driver;

	@BeforeClass
	public static void startdriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void multipleWindowsHandling() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.irctc.co.in/nget/train-search");
		System.out.println("before  " + driver.getWindowHandles());
		driver.findElement(By.xpath("//label[text()='STAY']")).click();
		System.out.println("AfterClickingSTAY " + driver.getWindowHandles());
		driver.findElement(By.xpath("//label[text()='CAB']")).click();
		System.out.println("AfterclickingCABS " + driver.getWindowHandles());
		driver.findElement(By.xpath("//div[@class='iconsection']/ul/li/a/label[text()='FLIGHTS']")).click();
		System.out.println("AfterclickingFLIGHTS:" + driver.getWindowHandles());
		System.out.println(driver.getWindowHandles().size());
		Set<String> newset = driver.getWindowHandles();
		for (String handle : newset) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("IRCTC Hotels")) {
				System.out.println("in hotels");
				break;
			}
			
		} 
		driver.findElement(By.xpath("//div[@class='c-auto-cmpltr']/div/input")).sendKeys("hyderabad");
		Thread.sleep(5000);
		driver.close();
		System.out.println(driver.getWindowHandles().size());
		
		
		Set<String> newset1 = driver.getWindowHandles();
		for(String nwhandle: newset1) {
			driver.switchTo().window(nwhandle);
			
			if(driver.getTitle().equals("Book Cabs Nearby at Best Price | Hire Taxi Nearby Online at Olacabs.com")) {
				driver.getTitle();
			}
			if(driver.getCurrentUrl().equals("https://www.air.irctc.co.in/")) {
				driver.getTitle();
			}
		}
		
		
	}

	@AfterClass
	public static void closedriver() {
		driver.quit();
	}
}
