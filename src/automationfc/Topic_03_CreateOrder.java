package automationfc;

import org.testng.annotations.Test;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Topic_03_CreateOrder {
	WebDriver driver; // Call driver ra.
	
	 //@BeforeClass
	 //public void beforeClass() 
	 //{
		   
		 //System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Downloads\\chromedriver_win32");
		 //driver = new ChromeDriver(); // Call Chrome driver to open Chrome browser.
		 //driver.manage().window().maximize(); // Chrome window size = maximum
		 //driver.get("https://magento2-demo.magebit.com");
		 
	 //}
 
  @BeforeTest
  public void Open_HomepageURL()
  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\dell\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
	  driver = new ChromeDriver(); // Call Chrome driver to open Chrome browser.
	  driver.manage().window().maximize(); // Chrome window size = maximum
	  driver.get("https://magento2-demo.magebit.com");
	  
	  
  }
  @BeforeTest
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
  @BeforeTest
  public void TC_02_Loginsuccess()
  {
	  
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   driver.findElement(By.xpath("//input[@id='email']")).sendKeys("roni_cost@example.com");
 	   driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("roni_cost3@example.com");
 	   driver.findElement(By.xpath("//div[@class='login-container']//button[@type='submit']//Span")).click();
  }
  
  
  @Test
  public void TC_01_ProductDetailPage_Navigation() throws InterruptedException
  {
	  driver.findElement(By.xpath("//a[@title = 'Breathe-Easy Tank']")).click();
	  driver.findElement(By.xpath("//span[contains(text(),'Breathe-Easy Tank')]")).getText();
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
  		Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
      }
      catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.findElement(By.xpath("//div[contains(text(),'You added Breathe-Easy Tank to your')]//a[contains(text(),'shopping cart')]")).isDisplayed();
      try {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@class='action showcart']/child::span[2]")).click();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      // Verify chosen default address color
      driver.findElement(By.xpath("//div[@id='ui-id-1']")).isDisplayed();
      driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();
      String ExpectedPageTitle = "Shipping Address";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      String ActualPageTitle = driver.findElement(By.xpath("//div[contains(text(), 'Shipping Address')]")).getText();
  	  Assert.assertEquals(ActualPageTitle, ExpectedPageTitle);
  	  WebElement element = driver.findElement(By.xpath("//div[@class='shipping-address-items']/div[1]"));
  	 //obtain color in rgba
     String s = element.getCssValue("border-color");
    // convert rgba to hex
     String c = Color.fromString(s).asHex();
     System.out.println("Color is :" + s);
     System.out.println("Hex code for color:" + c);
     
     
     //Verify "Shipping Address" text
     String ExpectedTitle = "Shipping Address";
     //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     String ActualTitle = driver.findElement(By.xpath("//div[contains(text(), 'Shipping Address')]")).getText();
 	 Assert.assertEquals(ActualTitle, ExpectedTitle);
 	// Choose shipping method.
 	driver.findElement(By.xpath("//input[@name='ko_unique_1']")).click();
 	// Verify FE displays text = Order Summary
 	String ActualOrderTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
 	String ExpectedOrderTitle = "Order Summary";
 	Assert.assertEquals(ActualOrderTitle, ExpectedOrderTitle);
 	WebElement element2 = driver.findElement(By.xpath("//div[@class='title']/descendant::span[1]"));
 	element2.isDisplayed();
 	String ElementValue= element2.getText();
    System.out.println("Value of type attribute: "+ElementValue);
 	WebElement element3 = driver.findElement(By.xpath("//div[@class='title']/descendant::span[2]"));
	element3.isDisplayed();
	String ElementTitleValue= element3.getText();
	System.out.println("Value of type attribute: "+ElementTitleValue);
 	//try {
  		//Thread.sleep(3000);
 	     
 	//}
 	//catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		//e.printStackTrace();
  	//}
 		
 	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 	driver.findElement(By.xpath("//button[@type='submit']/child::span[text() ='Next']")).click();
 	// Verify FE display "Review & Payments" text
 	String ExpectedTitleElement = "Review & Payments";
 	String ActualTitleElement = driver.findElement(By.xpath("//span[text()='Review & Payments']")).getText();
 	Assert.assertEquals(ActualTitleElement,ExpectedTitleElement);
 	//String ExpectedTitleElement2 = "Payment Method";
 	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 	//String ActualTitleElement2 = driver.findElement(By.xpath("//div[contains(text(),'Payment Method')]")).getText();
 	try {
  		Thread.sleep(3000);
  		String  element4 =  driver.findElement(By.xpath("//div[contains(text(),'Payment Method')]")).getText();
  		System.out.println("Text is:" + element4);
      }
      catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
 	//Assert.assertEquals(ActualTitleElement2, ExpectedTitleElement2);	
 	String ExpectedTitleElement3 = "Check / Money order";
 	String ActualTitleElement3 = driver.findElement(By.xpath("//span[contains(text(), 'Check / Money order')]")).getText();
 	Assert.assertEquals(ActualTitleElement3, ExpectedTitleElement3);		
    driver.findElement(By.xpath("//span[contains(text(), 'My billing and shipping address are the same')]")).isDisplayed();
    driver.findElement(By.xpath("//input[@name='billing-address-same-as-shipping']")).isSelected();
    WebElement element1 = driver.findElement(By.xpath("//span[@data-th='Cart Subtotal']"));
    String typeValue= element1.getText();
    System.out.println("Value of type attribute: "+typeValue);
    try {
  		Thread.sleep(5000);
  		WebElement elementTitle = driver.findElement(By.xpath("//tr[@class='totals discount']/descendant::span[1]"));
  		String typeValue2= elementTitle.getText();
  	    System.out.println("Value of type attribute: "+typeValue2);
      }
    catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
    WebElement elementValue = driver.findElement(By.xpath("//tr[@class='totals discount']/descendant::span[3]"));
    String typeValue3= elementValue.getText();
    System.out.println("Discount value is: "+typeValue3);
    WebElement shippingTitle = driver.findElement(By.xpath("//tr[@class='totals shipping excl']/descendant::span[1]"));
    String ShippingTypevalue= shippingTitle.getText();
    System.out.println("Shipping title is: "+ShippingTypevalue);
    //Test auto cung phai check value by manual + eyes.
    WebElement ShippingPrice = driver.findElement(By.xpath("//tr[@class='totals shipping excl']/descendant::span[3]"));
    String ShippingPricevalue = ShippingPrice.getText();
    System.out.println("Shipping price is: "+ShippingPricevalue);
    WebElement TableRate = driver.findElement(By.xpath("//tr[@class='totals shipping excl']/descendant::span[2]"));
    String TableRatevalue = TableRate.getText();
    System.out.println("Text is: "+TableRatevalue);
    WebElement TaxElement = driver.findElement(By.xpath("//tr[@class='totals-tax']/child::th"));
    String Taxlable = TaxElement.getText();
    System.out.println("Text is: "+Taxlable);
    WebElement Taxvalue = driver.findElement(By.xpath("//tr[@class='totals-tax']/descendant::span"));
    String Taxdata = Taxvalue.getText();
    System.out.println("Text value is: "+Taxdata);
    WebElement Orderelement = driver.findElement(By.xpath("//strong[contains(text(), 'Order Total')]"));
    String OrderText = Orderelement.getText();
    System.out.println("Order text is: "+OrderText);
    WebElement OrdervalueElement = driver.findElement(By.xpath("//td[@data-th='Order Total']/descendant::span"));
    String Ordervalue = OrdervalueElement.getText();
    System.out.println("Order value is: "+Ordervalue);
    //Verify Order Sumarry
    WebElement totalordernumber = driver.findElement(By.xpath("//div[@class='title']//span[1]"));
    totalordernumber.isDisplayed();
    String totalorder = totalordernumber.getText();
    System.out.println("Total Order number:"+totalorder);
    WebElement itemnote = driver.findElement(By.xpath("//div[@class='title']//span[2]"));
    itemnote.isDisplayed();
    String itemnotecontent = itemnote.getText();
    System.out.println("Item note is:"+itemnotecontent);
    driver.findElement(By.xpath("//div[@class='title']")).click();
    WebElement Productitem = driver.findElement(By.xpath("//strong[@class='product-item-name']"));
    String ProductName = Productitem.getText();
    System.out.println("Product item name:"+ProductName);
    //Place Order
    try {
  		Thread.sleep(3000);
  		driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();
      }
      catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
    //Verify checkout success message
    WebElement Ordermessage = driver.findElement(By.xpath("//span[contains(text(),'Thank you for your purchase!')]"));
    String OrderTextmessage = Ordermessage.getText();
    System.out.println("Order success message:"+OrderTextmessage);
    WebElement Orderitemnumber = driver.findElement(By.xpath("//div[@class='checkout-success']/p[1]"));
    String Ordernumbermessage = Orderitemnumber.getText();
    System.out.println("Order number is:"+Ordernumbermessage);
    WebElement EmailNote = driver.findElement(By.xpath("//div[@class='checkout-success']/p[2]"));
    String EmailNoteMessage = EmailNote.getText();
    System.out.println("Email note is:"+EmailNoteMessage);
    WebElement button = driver.findElement(By.xpath("//span[contains(text(),'Continue Shopping')]"));
    button.isDisplayed();
    
 
  
  
    		
    		
    
    //issue gap hom nay: Bug Magento WaitTime,  Tai sao Xpath luc bat duoc, luc khong?
    //Check auto cung can phai compare bang mat.
    
    
 
    
   
    
  
 	
 	//div[@class='title']/descendant::span[1]
 	
 	//driver.findElement(By.xpath("span[1 and text() ='Item in Cart']")).isDisplayed();
 	
 	
 	// Check display correctly the quantity and text 
 	//----------------AND: Need to contain both conditions: quantity = '1' AND text = 'Item in Cart'
 	//div[@class='title']/descendant::span[1 and text() ='Item in Cart'] 
 	//----------------OR: Need to either 1 or 2 conditions: quantity = '1' OR  text = 'Item in Cart'
 	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 	//driver.findElement(By.xpath("//div[@class='title']/descendant::span[1 and text() ='Item in Cart']")).isDisplayed();
 	
 	//String ExpectedPrice = "$340.00";
 	//String ActualPrice = driver.findElement(By.xpath("//span[@class='cart-price']/child::span")).getText();
 	//Assert.assertEquals(ActualPrice, ExpectedPrice);
 	
 	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  

  @AfterTest
  public void afterTest() 
  {
	  //driver.quit();
  }

}
