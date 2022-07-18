package automationfc;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_04_GuestUser_OrderFlow_update {
	WebDriver driver;
  
  @BeforeTest
  public void Open_HomepageURL() 
  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		 driver = new ChromeDriver(); // Call Chrome driver to open Chrome browser.
		 driver.manage().window().maximize(); // Chrome window size = maximum
		 driver.get("https://magento2-demo.magebit.com");
  }
  @Test
  public void TC_01_ProductDetailPage_Navigation() 
  {
	  driver.findElement(By.xpath("//a[@title = 'Breathe-Easy Tank']")).click();
	  driver.findElement(By.xpath("//span[contains(text(),'Breathe-Easy Tank')]")).getText();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[contains(text(),'XS')]")).click();
	  driver.findElement(By.xpath("//div[@data-option-tooltip-value='#ffd500']")).click();
	  WebElement QtyField = driver.findElement(By.name("qty"));
      String QtyVal = QtyField.getAttribute("value");
      String expectedVal = "1";
      String AddVal = "5";
      //QtyVal.equ
      if(QtyVal.equalsIgnoreCase(expectedVal))
      {
    	  driver.findElement(By.xpath("//input[@type='number']")).clear();
          driver.findElement(By.xpath("//input[@type='number']")).sendKeys(AddVal);
      }
	  else
       	System.out.println("The default quantity value is not correct --- " +expectedVal);
	  
	  // Xai ham Verify/AssertValue, get current value, if, else
      try {
  		Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
      }
      catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
      //new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@data-role='loader']"))));
      //new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-role='loader']"))));

      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.findElement(By.xpath("//div[contains(text(),'You added Breathe-Easy Tank to your')]//a[contains(text(),'shopping cart')]")).isDisplayed();
      try {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@class='action showcart']/child::span[2]")).click();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
      //new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='F']"))));
      //driver.findElement(By.xpath("//div[@id='F']/descendant::img[@alt='Loading...']")).isDisplayed(); 
      //driver.findElements(By.xpath("//div[@id='F']/descendant::img[@alt='Loading...']")).
       //driver.manage().timeouts().pageLoadTimeout(-1, TimeUnit.SECONDS);
  	  // new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='loader']"))));
     // new WebDriverWait(driver,20)
      FluentWait wait = new FluentWait(driver);
      //Specify the timeout of the wait
      wait.withTimeout(5000, TimeUnit.MILLISECONDS);
      //Specify polling time
     wait.pollingEvery(20, TimeUnit.MILLISECONDS);
      //Specify what exceptions to ignore.
     wait.ignoring(NoSuchElementException.class);
     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='F']/descendant::img[@alt='Loading...']")));
     //driver.findElement(By.xpath("//div[@id='F']/descendant::img[@alt='Loading...']")).vi
      String ExpectedPageTitle = "Shipping Address";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      String ActualPageTitle = driver.findElement(By.xpath("//div[contains(text(), 'Shipping Address')]")).getText();
  	  Assert.assertEquals(ActualPageTitle, ExpectedPageTitle);
  }
  // Check layout of Shipping page
  // Tim hieu ve loading icon
  // set time toan la mili giay thoi.
  @Test
  public void TC_02_ShippingPageLayout() 
  {
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='field required']/descendant::input[@id='customer-email']")).sendKeys("thienguest123@mailinator.com");
	  driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Thien");
	  driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Guest");
	  driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Guest Order");
	  driver.findElement(By.xpath("//input[@name='street[0]']")).sendKeys("123/456");
	  driver.findElement(By.xpath("//input[@name='street[1]']")).sendKeys("45 street");
	  driver.findElement(By.xpath("//input[@name='street[2]']")).sendKeys("District 4");
	  Select se = new Select(driver.findElement(By.xpath("//select[@name='country_id']")));
	  // Select the option with value "BY" (Country is Belarus)
	  se.selectByValue("BY");
      driver.findElement(By.xpath("//input[@name='region']")).sendKeys("Ho Chi Minh");
      driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Ho Chi Minh city");
      driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("723456");
      driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("0123456789");
      WebElement ShippingElement = driver.findElement(By.xpath("//div[contains(text(),'Shipping Methods')]"));
      String ShippingTitle = ShippingElement.getText();
      System.out.println("Shipping Title is: "+ShippingTitle);
       //Network co anh huong den bat pass hay failed Xpath ko? (Chac chan co nhung khong phai la van de chinh)
      // Nghien cuu: Pollingwait
	      // PollingTime: Set default wait Time for Implicit, Explicit Wait.
         // Tim hieu them ve ExplicitWait, PollingTime
         // Tim cac tai lieu hoc: Udemy,youtube, An, Anh Dam.
      //WebElement Price  = driver.findElement(By.xpath("//span[contains(text(),'$25.00')]"));
      //String PriceValue = Price.getText();
      //System.out.println(PriceValue);
      //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      if(driver.findElement(By.xpath("//span[contains(text(),'$25.00')]")).isDisplayed())
      {
    	  //driver.findElement(By.xpath("//span[contains(text(),'$25.00')]")).click();
    	  try {
    	  		Thread.sleep(5000);
    	  		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
    	      }
    	    catch (InterruptedException e) 
    	    {
    	  		// TODO Auto-generated catch block
    	  		e.printStackTrace();
    	    }
    	  
      }
      else
      {
    	  WebElement ErrrorTitle = driver.findElement(By.xpath("//div[contains(text(),'Sorry, no quotes are available for this order at this time')]"));
    	  String ErrorText = ErrrorTitle.getText();
    	  System.out.println("Error is: "+ErrorText);
      }
     
      
      WebElement shippingTitle = driver.findElement(By.xpath("//span[text()='Review & Payments']"));
      String ShippingTypevalue= shippingTitle.getText();
      System.out.println("Shipping title is: "+ShippingTypevalue);
      WebElement PaymentTitle =  driver.findElement(By.xpath("//div[text()='Payment Method']"));
      String PaymentHeader = PaymentTitle.getText();
      System.out.println("Payment title is: "+PaymentHeader);
      String ExpectedTitleElement3 = "Check / Money order";
   	  String ActualTitleElement3 = driver.findElement(By.xpath("//span[contains(text(), 'Check / Money order')]")).getText();
   	  Assert.assertEquals(ActualTitleElement3, ExpectedTitleElement3);
   	  driver.findElement(By.xpath("//span[contains(text(), 'My billing and shipping address are the same')]")).isDisplayed();
   	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   	  WebElement BillingElement = driver.findElement(By.xpath("//div[@class='checkout-billing-address']/child::div[2]"));
      String BillingInfo = BillingElement.getText();
      System.out.println("Billing FirstName is: "+BillingInfo);
      WebElement Ordersumarry = driver.findElement(By.xpath("//span[@class='title']"));
      String OrdersumarryTitle = Ordersumarry.getText();
      System.out.println("Order Sumaary Title: "+OrdersumarryTitle);
      WebElement CartSubtotal = driver.findElement(By.xpath("//tr[@class='totals sub']/child::th"));
      String CartTitle = CartSubtotal.getText();
      System.out.println("Cart Total text is: "+CartTitle);
      WebElement PriceElement = driver.findElement(By.xpath("//tr[@class='totals sub']/descendant::span"));
      String Pricevalue = PriceElement.getText();
      System.out.println("Cart Total text is: "+Pricevalue);
      WebElement ShippingPriceelement = driver.findElement(By.xpath("//tr[@class='totals shipping excl']/descendant::span[3]"));
      String ShippingPrice = ShippingPriceelement.getText();
      System.out.println("Shipping Price is: "+ShippingPrice);
      
    
    
  }
	  
  ///div[@class='checkout-billing-address']/child::div[2]/child::text()[1]
  
  

  @AfterTest
  public void afterTest() 
  {
	  
  }
 

}
