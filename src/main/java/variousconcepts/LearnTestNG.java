package variousconcepts;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LearnTestNG {

	public class LearnwindowHanle {
		

		WebDriver driver;
		String browser=null;
		String url;
		
	    @BeforeClass
		public void readconfig() {
			//BufferReader //InputStream //FileReader //Scanner
			
			Properties prop = new Properties();
			
			try {
				InputStream input=new FileInputStream("src/main/java/config/config.properties");
				prop.load(input);
				browser=prop.getProperty("browser");
				System.out.println("Browser used:" +browser);
				url = prop.getProperty("url");
				
				
			}	
			catch(IOException e) {
				
				e.printStackTrace();
			}
			}
		
		@BeforeMethod
		public void launchBrowser() {
           if(browser.equalsIgnoreCase("chrome")) {
	     
        	   System.setProperty("webdriver.chrome.driver", "C:\\Users\\shage\\Apr2021_selenium\\session5_Test_NG\\drivers\\chromedriver.exe");
		       driver = new ChromeDriver();
           }
           else if(browser.equalsIgnoreCase("Firefox"))
           {
			
			   System.setProperty("webdriver.gecko.driver", "C:\\Users\\shage\\Apr2021_selenium\\session5_Test_NG\\drivers\\geckodriver.exe");
			   driver=new FirefoxDriver();
           }
			
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
					
		}
		@Test
		public void LoginTest() throws InterruptedException {

		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong page!!!");	
			
			WebElement USER_NAME_ELEMENT=driver.findElement(By.xpath("//input[@id='username']"));
			WebElement PASSWORD_ELEMENT=driver.findElement(By.xpath("//input[@id='password']"));
			WebElement SUBMIT_BUTTON_ELEMENT=driver.findElement(By.xpath("//button[@name='login']"));
		
			
			//login data 
		String loginId="demo@techfios.com";
		String password="abc123";
		
		USER_NAME_ELEMENT.sendKeys(loginId);
		PASSWORD_ELEMENT.sendKeys(password);
		SUBMIT_BUTTON_ELEMENT.click();
		
		WebElement DASHBOARD_TITLE_ELEMENT=driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
		Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "Wrong page");
	
		
		
	
			
		}
	//@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	}	
	



}