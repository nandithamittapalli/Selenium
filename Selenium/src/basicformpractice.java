import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class basicformpractice {
	
static WebDriver driver;

@BeforeClass
public static void startDriver() {
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium lib\\chromedriver_win32\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	
}

@Test
public void formfill() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("http://toolsqa.com/automation-practice-form/");
	driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("nanditha");
	driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("m");
	WebElement sex= driver.findElement(By.xpath("//input[@id='sex-1']"));
	if(!(sex.isSelected())) {
		sex.click();
	}
	WebElement exp= driver.findElement(By.xpath("//input[@id='exp-1']"));
	if(!(exp.isSelected())) {
		exp.click();
	}
	WebElement profession= driver.findElement(By.xpath("//input[@id='profession-0']"));
	if(!(profession.isSelected())) {
		profession.click();
	}
	WebElement auttool= driver.findElement(By.xpath("//input[@id='tool-2']"));
	if(!(auttool.isSelected())) {
		auttool.click();
	}
	Select s=new Select(driver.findElement(By.xpath("//select[@id='continents']")));
	s.selectByVisibleText("Australia");
	Select s1=new Select(driver.findElement(By.xpath("//select[@id='selenium_commands']")));
	s1.selectByVisibleText("Switch Commands");
	s1.selectByVisibleText("Navigation Commands");
	s1.deselectAll();
	Thread.sleep(10000);
	
}

@AfterClass
public static void closeDriver() {
	driver.close();
}
}
