import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


public class NewTest2 {
  @Test
  public void f() {
	  System.out.println("In f2");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("BM IN TEST2");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("AM IN TEST2");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("BC IN TEST2");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("AC IN TEST2");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("BT IN TEST2");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("AT IN TEST2");
  }

 

}


