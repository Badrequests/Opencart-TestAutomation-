package drivers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Drivers {
	
	static WebDriver driver = null;

	public static WebDriver loadBrowser(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","./Users\\user\\Documents\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	public static WebDriver loadBrowser(String browserName,String URL) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","./Users\\user\\Documents\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static WebDriver getDriver(){
		return driver;
	}
	public static void main(String args[]){
		loadBrowser("firefox");
	}


}
