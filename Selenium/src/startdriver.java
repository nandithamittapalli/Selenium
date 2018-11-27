import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class startdriver {
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://toolsqa.com/handling-alerts-using-selenium-webdriver/");
		driver.findElement(By.xpath("//button[text()='Simple Alert']")).click();
		WebDriverWait w=new WebDriverWait(driver, 30);
		w.until(ExpectedConditions.alertIsPresent());
		Alert a=driver.switchTo().alert();
		Thread.sleep(30000);
		System.out.println(a.getText());
		a.accept();
		driver.close();
		
	

	
	}
}


