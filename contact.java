package vtiger1;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class contact {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	
	     FileInputStream fis = new FileInputStream("src\\test\\resources\\dataContact.properites.txt");
	
	     Properties p = new Properties();
	
	     p.load(fis);
	     
	     String LASTNAME = p.getProperty("LastName");
	     String ASSIGNEDTO = p.getProperty("AssignedTo");
	     
	    driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
	    driver.findElement(By.xpath("(//input[@type='radio'])[1]")).sendKeys(ASSIGNEDTO);
		
	}

}
