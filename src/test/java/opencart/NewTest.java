package opencart;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.firefox.FirefoxDriver;		

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class NewTest {
	  private WebDriver driver;		
			@Test				
			public void testEasy() {	
				driver.get("https://demo.opencart.com/");  
				String title = driver.getTitle();				 
				AssertJUnit.assertTrue(title.contains("Your store")); 		
			}	
			@BeforeTest
			public void beforeTest() {	
			    driver = new FirefoxDriver();  
			}		
			@AfterTest
			public void afterTest() {
				driver.quit();			
			}		

}
