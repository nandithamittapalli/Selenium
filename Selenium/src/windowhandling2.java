import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class windowhandling2 {
	static WebDriver driver;

	@BeforeClass
	public static void startDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void multwindowhandling() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.irctc.co.in/nget/train-search");
		//driver.findElement(By.xpath("//div[@class='iconsection']/ul/li/a/label[text()='FLIGHTS']")).click();
		driver.findElement(By.xpath("//span[@class='allcircle circleone']")).click();
		driver.findElement(By.xpath("//label[text()='STAY']")).click();
		driver.findElement(By.xpath("//label[text()='CAB']")).click();
		driver.findElement(By.xpath("//label[text()='E-CATERING']")).click();
		driver.findElement(By.xpath("//label[text()='HOLIDAY PACKAGES']")).click();
		driver.findElement(By.xpath("//label[text()='TOURIST TRAIN']")).click();
		driver.findElement(By.xpath("//label[text()='HILL RAILWAYS']")).click();
		driver.findElement(By.xpath("//label[text()='CHARTER TRAIN']")).click();
		System.out.println(driver.getWindowHandles().size());
		Set<String> set1 = driver.getWindowHandles();
		for (String handle : set1) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("IRCTC FTR SERVICE")) {
				System.out.println("in charter trains");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a[text()='Payment Tariff']")).click();
				break;
			}
		}
		
		for (String handle : set1) {
			driver.switchTo().window(handle);
	        if (driver.getTitle().equals("Railways Tourism, Retiring Rooms, Holidays Customized Tours, LTC Tours, Maharajas, Mahaparinirvan")) {
				System.out.println("in holiday packages");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//i[@class='fa fa-facebook']")).click();
				break;
			}
		}
		
		for (String handle : set1) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("Book Cabs Nearby at Best Price | Hire Taxi Nearby Online at Olacabs.com")) {
				System.out.println("in cabs");
				System.out.println(driver.getTitle());
				driver.close();
				break;

			}
		}
		System.out.println(driver.getWindowHandles().size());//After closing cabs
		
		Set<String> set2 = driver.getWindowHandles();
		
		for (String handle : set2) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("Payment Tariff")) {
				System.out.println("in payment tariff");
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				System.out.println(driver.findElement(By.xpath("//div[@class='panel-heading']")).getText());
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
		
		for (String handle : set2) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("eCatering IRCTC: Order Food on Train Online, Food and Meal on Train, Tasty Food for Train Journey")) {
				System.out.println("in e-catering");
				driver.findElement(By.xpath("//input[@placeholder='Enter 10 digit PNR']")).sendKeys("4234sdf");
				driver.findElement(By.xpath("//button[@class='icon__1ceht']")).click();
				WebElement err=driver.findElement(By.xpath("//div[@class='err__271pm']"));
				if(err.isDisplayed()==true) {
					System.out.println("error message displayed");
				}
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
		
		Set<String> set3=driver.getWindowHandles();
		
		System.out.println(driver.getWindowHandles().size());
		
		for (String handle : set3) {
			driver.switchTo().window(handle);
			if (driver.getCurrentUrl().equals("https://www.air.irctc.co.in/")) {
				System.out.println("in flights");
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
		
		for (String handle : set3) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("IRCTC Hotels")) {
				System.out.println("in hotels");
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
		
		for (String handle : set3) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("Railways Tourism, Retiring Rooms, Holidays Customized Tours, LTC Tours, Maharajas, Mahaparinirvan")) {
				System.out.println("In holiday packages");
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
		Set<String> set4=driver.getWindowHandles();
		
		for (String handle : set4) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("Railways Tourism, Retiring Rooms, Holidays Customized Tours, LTC Tours, Maharajas, Mahaparinirvan")) {
				System.out.println("In tourist train");
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
		Set<String> set5=driver.getWindowHandles();
		for (String handle : set5) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("Railways Tourism, Retiring Rooms, Holidays Customized Tours, LTC Tours, Maharajas, Mahaparinirvan")) {
				System.out.println("In hill ");
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
		
		Set<String> set6=driver.getWindowHandles();
		for (String handle : set6) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("IRCTC Tourism - Home | Facebook")) {
				System.out.println("In facebook");
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
		
		for (String handle : set6) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals("IRCTC FTR SERVICE")) {
				System.out.println("In charter trains");
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				driver.close();
				break;
			}
		}
		System.out.println(driver.getWindowHandles().size());
	}//test

@AfterClass
public static void closeDriver() {
	System.out.println("After Class");
	System.out.println(driver.getWindowHandles().size());
}

}//class
