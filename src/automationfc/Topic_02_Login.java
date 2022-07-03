package automationfc;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Login {
	WebDriver driver; // Call driver ra.
	
 @BeforeClass
 public void beforeClass() 
 {
	   
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
	 driver = new ChromeDriver(); // Call Chrome driver to open Chrome browser.
	 driver.manage().window().maximize(); // Chrome window size = maximum
	 driver.get("https://magento2-demo.magebit.com");
	 
 }
 @Test
  public void TC_01_LoginPageLayout()
 {
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]")).isDisplayed();
	 driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]")).click();
	 String ExpectedPageTitle = "Customer Login";
	 String ActualPageTitle = driver.getTitle();
	 Assert.assertEquals(ActualPageTitle, ExpectedPageTitle);
	 driver.findElement(By.xpath("//input[@id='email']")).isDisplayed();
	 driver.findElement(By.xpath("//input[@title='Password']")).isDisplayed();
	 driver.findElement(By.xpath("//div[@class='login-container']//button[@type='submit']//Span")).isDisplayed();
 
}
 @Test
 public void TC_02_Loginsuccess()
 {
	  
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.findElement(By.xpath("//input[@id='email']")).sendKeys("roni_cost@example.com");
	   driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("roni_cost3@example.com");
	   driver.findElement(By.xpath("//div[@class='login-container']//button[@type='submit']//Span")).click();
 }
 @Test
 public void TC_03_LogOutsuccess()
 {
	   try 
	   {
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("//div[@class='panel header']//button")).click();
		  
	    } 
	   catch (InterruptedException e) 
	   {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	   try {
		Thread.sleep(5000);
        driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
	   }
	   // Mấu chot: thoi gian chay qua nhanh, nen thoi gian do tim bat Xpath khong kip
	   
	 catch (InterruptedException e) 
	   {
		 //TODO Auto-generated catch block
		e.printStackTrace();
	  }
	    
	   
	   //driver.findElement(By.xpath("//div[@class='panel header']//button")).click();
	   //driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
	  // WebElement elmentdisplayed = driver.findElement(By.xpath("//div[@class='panel header']//span[contains(text(),'Welcome')]"));
	   //By button_login = By.xpath("//div[@class='panel header']//descendant::button);
	   //By dropdown_list = By.xpath("//div[@class='panel header']//button");
	   //driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	   //By signout_button = By.xpath("//a[contains(text(),'Sign Out')]");
	   //if(elmentdisplayed.isDisplayed())
	  
	    //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	     
		 //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 //driver.findElement(signout_button).click();
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 //driver.findElements(By.xpath("//li[@class='customer-welcome active']//a[contains(text(),'Sign Out')]"))driver.get
		 
	   
	   //else
	   //{
		  // driver.navigate().refresh();
	   //Invalid Form Key. Please refresh the page.

	   //}
}

 public void TC_03_InvalidEmail()
 {
	 driver.findElement(By.cssSelector("input[title='Email']")).sendKeys("rona_cost@example.com");
	 driver.findElement(By.cssSelector("input[title='Password']")).sendKeys("roni_cost3@example.com");
	 driver.findElement(By.xpath("//div[@class='login-container']//button[@type='submit']//Span")).click();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later')]")).getText();
	 String ExpectedErrorMessage = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later";
	 //Assert.assertEquals(ActualPageTitle, ExpectedPageTitle);
	 Assert.assertTrue(ActualErrorMessage.contains(ExpectedErrorMessage));
 }
 
 
 public void TC_04_InvalidPassword()
 {
	 driver.findElement(By.cssSelector("input[title='Email']")).sendKeys("roni_cost3@example.com");
	 driver.findElement(By.cssSelector("input[title='Password']")).sendKeys("rona_cost@example.com");
	 driver.findElement(By.xpath("//div[@class='login-container']//button[@type='submit']//Span")).click();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 String ActualErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later')]")).getText();
	 String ExpectedErrorMessage = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later";
	 //Assert.assertEquals(ActualPageTitle, ExpectedPageTitle);
	 Assert.assertTrue(ActualErrorMessage.contains(ExpectedErrorMessage));
 }
 
 
 //public void TC_05_ForgotPassword()
 //{
	 //driver.findElement(By.linkText("Forgot Your Password?")).click();
	 //String ExpectedPageTitle = "Forgot Your Password?";
	 //String ActualPageTitle = driver.getTitle();
	 //Assert.assertEquals(ActualPageTitle, ExpectedPageTitle);
	 //driver.findElement(By.xpath("")
	 // Tim hieu ve: implicitlyWait, ExplicitlyWait, FluentWait, Get capcha
	 //driver.findElement(By.cssSelector("input[title='Password']")).
	 //https://www.youtube.com/watch?v=cDlxVfETBSQ (Handle Captcha In Selenium Using Tesseract OCR)
	 //https://www.youtube.com/watch?v=0pjzLMbtimk (Handle Captcha In Selenium Using Tesseract OCR
	 
	
 //}
 
	 
	 
	 
		

 
 
  @AfterClass
  public void afterClass() 
  {
	  //driver.quit();
  }

}
