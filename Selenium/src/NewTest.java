import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {
	  @Test
	  public void f1() {
		  System.out.println("In f");
	  }
	  @BeforeMethod
	  public void beforeMethod() {
		  System.out.println("BM IN TEST");
	  }

	  @AfterMethod
	  public void afterMethod() {
		  System.out.println("AM IN TEST");
	  }

	  @BeforeClass
	  public void beforeClass() {
		  System.out.println("BC IN TEST");
	  }

	  @AfterClass
	  public void afterClass() {
		  System.out.println("AC IN TEST");
	  }

	  @BeforeTest
	  public void beforeTest() {
		  System.out.println("BT IN TEST");
	  }

	  @AfterTest
	  public void afterTest() {
		  System.out.println("AT IN TEST");
	  }

	  @BeforeSuite
	  public void beforeSuite() {
		  System.out.println("BS IN TEST");
	  }

	  @AfterSuite
	  public void afterSuite() {
		  System.out.println("AS IN TEST");
	  }

}
