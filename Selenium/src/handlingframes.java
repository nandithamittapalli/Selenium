import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class handlingframes {
static WebDriver driver;
@BeforeClass
public static void startDriver() {
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
}
@Test
public void checkboxframe() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("http://jqueryui.com/checkboxradio/");
	driver.findElement(By.xpath("//input[@name='s']")).sendKeys("selenium");
	// Switching to frame
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
	
	WebElement locpar=driver.findElement(By.xpath("//label[@for='radio-2']"));
	if(!(locpar.isSelected())){
		locpar.click();
	}
	Thread.sleep(3000);		
	
	WebElement strat2=driver.findElement(By.xpath("//label[@for='checkbox-1']"));
	if(!(strat2.isSelected())) {
		strat2.click();
	}
	Thread.sleep(3000);	
	
	WebElement bedtype=driver.findElement(By.xpath("//label[@for='checkbox-nested-2']"));
	if(!(bedtype.isSelected())) {
		bedtype.click();
	}
	Thread.sleep(3000);		
	//comeoutofframe
	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//input[@name='s']")).clear();
	driver.findElement(By.xpath("//input[@name='s']")).sendKeys("script");
	Thread.sleep(3000);		
}


@Test
public void selectmenuframe() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("http://jqueryui.com/selectmenu/");
	driver.findElement(By.xpath("//input[@name='s']")).sendKeys("selenium");
	//switch to frame
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
	//Select ss=new Select(driver.findElement(By.xpath("//span[@id='salutation-button']")));
	//ss.selectByVisibleText("Dr.");
	//Thread.sleep(5000);
}

@Test
public void Datepickerframe() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("http://jqueryui.com/datepicker/");
	//switching to frame
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
	
	driver.findElement(By.xpath("//input[@id='datepicker']")).click();
	int expyear=2016;
	System.out.println("expectedyear:" +expyear);
	String expmonth="June";
	System.out.println("expectedmonth: "+expmonth);
	String curyear=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[2]")).getText();
	int cury=Integer.parseInt(curyear);
	if(expyear>cury) {
	while(true) {
		String year=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[2]")).getText();
		int y=Integer.parseInt(year);
		String month=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[1]")).getText();
		if(y==expyear&&expmonth.equals(month)) {
			break;
		}
		WebElement next=driver.findElement(By.xpath("//span[text()='Next']"));
		next.click();
			
	}
	}else{
		while(true) {
			String year=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[2]")).getText();
			int y=Integer.parseInt(year);
			String month=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[1]")).getText();
			if(y==expyear&&expmonth.equals(month)) {
				break;
			}
			WebElement prev=driver.findElement(By.xpath("//span[text()='Prev']"));
			prev.click();
			
		}
	}
	driver.findElement(By.xpath("//a[text()=20]")).click();//i want to select date as 20
	Thread.sleep(5000);
	System.out.println(driver.findElement(By.xpath("//input[@id='datepicker']")).getAttribute("value"));
}
	



@AfterClass
public static void closeDriver() {
	driver.close();
}

	
}
