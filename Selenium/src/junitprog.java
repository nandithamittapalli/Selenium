
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class junitprog {
	static WebDriver driver;
	@BeforeClass
	public static void startdriver() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Before
	public void enterUrl() {
		driver.get("http://toolsqa.com/handling-alerts-using-selenium-webdriver/");
				
	}
	
	@Test
	public void Simplealert() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Simple Alert']")).click();
		WebDriverWait w=new WebDriverWait(driver, 30);
	    w.until(ExpectedConditions.alertIsPresent());
	    Alert a= driver.switchTo().alert();
	    System.out.println( a.getText());
	    Thread.sleep(5000);
	    a.accept();
	
	}
	@Test
	public void Confirmationok() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Confirm Pop up']")).click();
		WebDriverWait w2=new WebDriverWait(driver, 30);
		w2.until(ExpectedConditions.alertIsPresent());
		Alert a= driver.switchTo().alert();
		Thread.sleep(5000);
		System.out.println(a.getText());
		a.accept();
		String s1=driver.findElement(By.xpath("//span[@id='ConfirmOption']")).getText();
		System.out.println(s1);
	}
	@Test
	public void Confirmationcacncel() {
		driver.findElement(By.xpath("//button[text()='Confirm Pop up']")).click();;
		WebDriverWait w3= new WebDriverWait(driver, 30);
		w3.until(ExpectedConditions.alertIsPresent());
		Alert a= driver.switchTo().alert();
		System.out.println(a.getText());
		a.dismiss();
		String s=driver.findElement(By.xpath("//span[@id='ConfirmOption']")).getText();
		System.out.println(s);
		
	}
	@AfterClass
	public static void closedriver() {
		driver.close();
	}

}
