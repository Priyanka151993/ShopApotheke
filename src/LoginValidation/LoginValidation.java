package LoginValidation;

import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;


public class LoginValidation {
	
@Test(dataProvider = "credentials")

public void Verifylogincredentials( String Scenario, String Username, String Password) {
	
	System.setProperty("webdriver.chrome.driver", "chromedriver");
	
	WebDriver driver=  new ChromeDriver();
	
	driver.get("https://www.shop-apotheke.com/nl/login.htm#");
	
	driver.findElement(By.id("login-email")).sendKeys(Username);
	driver.findElement(By.id("login-password")).sendKeys(Password);
	driver.findElement(By.id("btn-login")).click();
	
	if(Scenario.equals("ValidIDPassword")) {
		
		// layout = driver.findElement(By.id("layout"));
		WebElement CustomerName = driver.findElement(By.xpath("//font[contains(text(),'Priyanka Ragupathy')]"));
		
		Assert.assertTrue(CustomerName.isDisplayed(),"Login not success");
	}
	
	else if (Scenario.equals("IncorrectUsername")) {
		
		
		String ErrorMessage = driver.findElement(By.xpath("//div[@class='l-flex__primary u-no-margin u-padding--ends m-Notification__message']")).getText();
		Assert.assertEquals(ErrorMessage, "E-mail address and/or password are incorrect. ");
		
	}
	
else if (Scenario.equals("IncorrectPassword")) {
		
		
		String ErrorMessage = driver.findElement(By.xpath("//div[@class='l-flex__primary u-no-margin u-padding--ends m-Notification__message']")).getText();
		Assert.assertEquals(ErrorMessage, "E-mail address and/or password are incorrect. ");
		
}
else if (Scenario.equals("InValidIDPassword")) {
	
	
	String ErrorMessage = driver.findElement(By.xpath("//div[@class='l-flex__primary u-no-margin u-padding--ends m-Notification__message']")).getText();
	Assert.assertEquals(ErrorMessage, "E-mail address and/or password are incorrect. ");
	
}
	
	
}

@DataProvider(name = "credentials")

public Object[][] getData() {
	
	return new Object[][] {
		
		{"ValidIDPassword","priyanka151993@gmail.com","Password@123"},
		{"IncorrectUsername","priyanka15@gmail.com","Password@123"},
		{"IncorrectPassword","priyanka151993@gmail.com","Password@"},
		{"InValidIDPassword","abc@gmail.com","Pass@123"},
		
	};
	
}

}
