
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;

public class junitprog2 {
	static WebDriver driver;
	@BeforeClass
	public static void startDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Before
	public void enterUrl() {
		driver.get("http://192.168.1.168/pos/");
	}
	@Test
	public void loginwithInvalidCred() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("asdg");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("asdg");
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		WebElement errmsg=driver.findElement(By.xpath("//div[@class='alert alert-danger']/div"));
		if(errmsg.isDisplayed()==true) {
			System.out.println("error is displayed");
		}
		String acterr=errmsg.getText();
		System.out.println(acterr);
		String experr="Invalid username/password";
	    assertEquals(experr, acterr);
	    
	    //error colour validation
	    String errcolor=errmsg.getCssValue("color");
	    System.out.println(errcolor);
	    String hex = Color.fromString(errcolor).asHex();
	    System.out.println(hex);
	    String experrclr="#a94442";
	    assertEquals(experrclr, hex);
		    
	}
	
	@Test
	public void loginwithValidCred() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("admin@123");
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		String expres1="http://192.168.1.168/pos/index.php/home";
		String actres1=driver.getCurrentUrl();
		assertEquals(expres1, actres1);
		driver.findElement(By.xpath("//li[@class='btn  ']/a/span")).click();
		
		
	}
	
	@Test
	public void loginwithNullvalues() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		String expres="http://192.168.1.168/pos/index.php/login";
		String actres=driver.getCurrentUrl();
		assertEquals(expres, actres);
	}
	@AfterClass
	public static void closedriver() {
		driver.close();
	}
	
}
