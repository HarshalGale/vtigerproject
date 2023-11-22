package commonUtils;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	  public WebDriver driver;	

	
	  FileUtils futils = new FileUtils();
	  WebDriverUtils wUtils = new WebDriverUtils();
	  ExcelUtils eutils = new ExcelUtils();
	  JavaUtils jutils = new JavaUtils();
	 
		@BeforeSuite
		public void BSconfig()throws IOException {
			//System.out.println("connect to data base");
			Reporter.log("**connect to data base**",true);
		}

		@BeforeClass
		  //To read data From Properties file
		public void BCconfig() throws IOException{ 
		String BROWSER = futils.GetDataFromPropertyFile("browser");
		String URL = futils.GetDataFromPropertyFile("url");
		
		if(BROWSER.equalsIgnoreCase("Chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}else {
		      driver=new FirefoxDriver();
		}    
		  	wUtils.maximize(driver);
			wUtils.implicitwait(driver);
			
			 driver.get(URL);
       }
		
	    @BeforeMethod
		  //To read data From Properties file
	    public void BMconfig() throws IOException 
	    {
	        String USERNAME = futils.GetDataFromPropertyFile("username");
	        String PASSWORD = futils.GetDataFromPropertyFile("password");
	   
		//login to application
		 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 driver.findElement(By.id("submitButton")).click();
	    }
		 @AfterMethod
		    public void AMconfig() throws InterruptedException {
		        WebElement dropdown = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		        // Mouse hovering on admin image
		        
		    //     Thread.sleep(2000);
		        // To sign out
		         wUtils.action(driver, dropdown);
		         driver.findElement(By.xpath("//a[text()='Sign Out']")).click();      
		    }
		
		@AfterClass
		public void ACconfig(){
		driver.close();
			}

		@AfterSuite
		public void ASconfig()throws IOException {
			Reporter.log("Disconnect to data base",true);
		}
	}

	
