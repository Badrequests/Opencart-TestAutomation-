package testcase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;


import drivers.Drivers;
import demopage.LoginPage;
import demopage.HomePage;
import demopage.CheckoutPage;
import util.Capture;


public class Apptest {

	static WebDriver driver;
	static HomePage home;
	static LoginPage login;
	static CheckoutPage checkout;
	int addresscount=1;
	int qtycount=1;
	

	static ExtentReports extent=new ExtentReports();
	static ExtentTest logger;

	@BeforeClass
	public static void launchBrowser(){    	
		 driver = Drivers.loadBrowser("chrome", "http://demo.opencart.com/");	
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 home = new HomePage(driver);
		 login = new LoginPage(driver);
		 checkout = new CheckoutPage(driver);
		 
	}
	
	@BeforeMethod
	public void setup(Method method) {
  	logger=extent.createTest(method.getName());
	}
	
	@AfterMethod
	public void checkStatus(ITestResult result) {
		try {
			if(result.getStatus()== ITestResult.FAILURE) {
				String path = Capture.captureScreenshot(driver);
				logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		extent.flush();
	}
	
	@Test(priority = 0)
	public void launchBrowserTest() {
		if(driver == null)
			logger.log(Status.FAIL, "Launch Browser Failure");
		else
			logger.log(Status.PASS, "Launch Browser Success");
		extent.flush();		
	}

	@Test(priority=1)
	public void login(){
		home.clickMyaccount();
		home.clickLogin();
		
		logger.log(Status.INFO, "Entering Login Details...");		
		login.setUsername("vivek1@gmail.com");
		login.setPassword("12345");
		login.clickLogin();
		
		String title=driver.getTitle();
		
		if(title.equals("My Account")) // verify title to check if user is successfully logged in or not.
			logger.log(Status.PASS, "Login Successfull");
		else
			logger.log(Status.FAIL, "Login Failure");
	}
	
	@Test(priority = 2)
	public void browseProduct() {
		home.clickDesktop();
		logger.log(Status.INFO, "Selects Desktop");		
		
		home.clickShowAllDesktops();
		logger.log(Status.INFO, "Selects Show all Desktop");		
		
		home.selectProduct("HP LP3065");			
		logger.log(Status.INFO, "Product 'HP LP3065' Selected");
	}
	

	  
	@Test(priority = 4)
	public void nevigateToCheckout() throws InterruptedException {
		
		home.viewCart(); 
		Thread.sleep(3000);
		logger.log(Status.INFO, "Cart Displayed");
		
		home.clickCheckouts(); 
		Thread.sleep(3000); 
		logger.log(Status.INFO, "Nevigated to checkout page");
	}



	@Test(priority=6)
	public void enterDeliveryDetails() throws InterruptedException{
		checkout.enterDeliveryDetails();
		logger.log(Status.INFO, "Delivery Details Entered");
		Thread.sleep(5000);
	}
	
	@Test(priority=7)
	public void enterDeliveryMethod() throws InterruptedException{
		checkout.enterDeliveryMethod();
		logger.log(Status.INFO, "Delivery Method Details Entered");
		Thread.sleep(3000);
	}
	
	@Test(priority = 8)
	public void acceptTerms() throws InterruptedException{
		driver.findElement(By.cssSelector("#collapse-payment-method > div > div.buttons > div > input[type=checkbox]:nth-child(2)")).click();
		logger.log(Status.INFO, "Terms and condition Accepted.");
		checkout.clickStep5Continue();
		Thread.sleep(3000);
		checkout.confirmOrder();
		logger.log(Status.INFO, "Order is confirmed.");
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void closeBrowser(){
		driver.close();
	}


	

}
