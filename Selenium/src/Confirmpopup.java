import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Confirmpopup {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://toolsqa.com/handling-alerts-using-selenium-webdriver/");	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()='Confirm Pop up']")).click();
		WebDriverWait w=new WebDriverWait(driver,30);
		w.until(ExpectedConditions.alertIsPresent());
		Alert a= driver.switchTo().alert();
		Thread.sleep(5000);;
		System.out.println(a.getText());
		//a.accept();
		a.dismiss();
		String s=driver.findElement(By.xpath("//span[@id='ConfirmOption']")).getText();
		System.out.println(s);
		driver.close();
		
		
	}

}
