package Param.Test_Ankita;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SubscriptionTest {
	WebDriver driver;
	HashMap<String, String> currencyByCountry = new HashMap<>();
	Set<String> packageContainer = new HashSet<String>();
		@BeforeClass
        void initialSetUp()
        {
        	currencyByCountry.put("Egypt", "Egyptian pound");
        	currencyByCountry.put("Jordan", "JOD");
        	currencyByCountry.put("Oman", "OMR");
        	currencyByCountry.put("UAE", "AED");
        	currencyByCountry.put("Algeria", "");
        	currencyByCountry.put("Djibouti", "");
        	currencyByCountry.put("Chad", "");
        	currencyByCountry.put("Iraq", "IQ");
        	currencyByCountry.put("Lebanon", "");
        	currencyByCountry.put("Morocco", "");
        	currencyByCountry.put("Tunisia", "TND");
        	currencyByCountry.put("Yemen", "");
        	currencyByCountry.put("Palestine", "");
        	
        	packageContainer.add(".packages.packages.Light");
        	packageContainer.add(".packages.packages.Classic");
        	packageContainer.add(".packages.packages.Premium");
        }
		
		@BeforeMethod
	    void setUp()
	    {
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ankit\\Downloads\\chromedriver_win321\\chromedriver.exe");
	        driver = new ChromeDriver();
	        //driver = new EdgeDriver();
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	       
	    }

	 
	 @Test()
	 void verifyCurrencyTest()
	 {
		 driver.get("https://subscribe.jawwy.tv/ae-en");
		 WebElement spanAfter = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/a[1]"));
		 spanAfter.click();
		 SoftAssert softAssert = new SoftAssert();
	     WebElement allCountry = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div/ul"));
	     List<WebElement> countryList = allCountry.findElements(By.tagName("li"));
	     Map<String,String> countryLinks = new HashMap<>();
	     for(WebElement country : countryList)
	     {
	    	 WebElement countryElement = country.findElement(By.className("countryName"));
	    	 String cntry = countryElement.getText();
	    	 WebElement countryLinkElement = country.findElement(By.className("countryFlags-link"));
	    	 String countryLink = countryLinkElement.getAttribute("href");
	    	 countryLinks.put(cntry, countryLink);
	     }
	     tearDown();
	     for(Entry<String, String> entry : countryLinks.entrySet())
	     {
	    	// WebElement countryElement = countryList.get(i).findElement(By.className("countryName"));
	    	 setUp();
	    	 String country = entry.getKey();
	    	 String countryLink = entry.getValue();
	    	 driver.get(countryLink);  
	    	 try
	    	 {
	    	 WebElement subscribtionIntro = driver.findElement(By.cssSelector(".subscription-intro.jawwy-user"));
	    	 WebElement currency = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[3]/div/div/div/section/div/div[1]/div[3]/div/span[3]"));
	    	 softAssert.assertEquals(currency.getText(),currencyByCountry.get(country), "Currency does not match for "+ country);
	    	 }
	    	 catch(org.openqa.selenium.NoSuchElementException ex)
	    	 {
	    		 softAssert.fail("Currency not present for "+ country);
	    	 }	    	 
	    	 tearDown();
	     }
	     softAssert.assertAll();
	 }
	 
	 @Test()
	 void verifyPackageTypeTest()
	 {
		 driver.get("https://subscribe.jawwy.tv/ae-en");
		 WebElement spanAfter = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/a[1]"));
		 spanAfter.click();
		 SoftAssert softAssert = new SoftAssert();
	     WebElement allCountry = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div/ul"));
	     List<WebElement> countryList = allCountry.findElements(By.tagName("li"));
	     Map<String,String> countryLinks = new HashMap<>();
	     for(WebElement country : countryList)
	     {
	    	 WebElement countryElement = country.findElement(By.className("countryName"));
	    	 String cntry = countryElement.getText();
	    	 WebElement countryLinkElement = country.findElement(By.className("countryFlags-link"));
	    	 String countryLink = countryLinkElement.getAttribute("href");
	    	 countryLinks.put(cntry, countryLink);
	     }
	     tearDown();
	     for(Entry<String, String> entry : countryLinks.entrySet())
	     {
	    	// WebElement countryElement = countryList.get(i).findElement(By.className("countryName"));
	    	 setUp();
	    	 String country = entry.getKey();
	    	 String countryLink = entry.getValue();
	    	 driver.get(countryLink);  
	    	
	    		for(String packageName : packageContainer)
	    		{
	    			try
	    			{
	    		 WebElement currentPackage = driver.findElement(By.cssSelector(packageName));
	    		
	    			}
	    			 catch(org.openqa.selenium.NoSuchElementException ex)
	   	    	 {
	   	    		 softAssert.fail(packageName.split("[.]",0)[3]+ " package not present for "+ country);
	   	    	 }	
	    		}	   
	    	 tearDown();
	     }
	     softAssert.assertAll();
	 }
	 
	 @Test()
	 void verifyPriceTest()
	 {
		 driver.get("https://subscribe.jawwy.tv/ae-en");
		 WebElement spanAfter = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/a[1]"));
		 spanAfter.click();
		 SoftAssert softAssert = new SoftAssert();
	     WebElement allCountry = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div/ul"));
	     List<WebElement> countryList = allCountry.findElements(By.tagName("li"));
	     Map<String,String> countryLinks = new HashMap<>();
	     for(WebElement country : countryList)
	     {
	    	 WebElement countryElement = country.findElement(By.className("countryName"));
	    	 String cntry = countryElement.getText();
	    	 WebElement countryLinkElement = country.findElement(By.className("countryFlags-link"));
	    	 String countryLink = countryLinkElement.getAttribute("href");
	    	 countryLinks.put(cntry, countryLink);
	     }
	     tearDown();
	     for(Entry<String, String> entry : countryLinks.entrySet())
	     {
	    	// WebElement countryElement = countryList.get(i).findElement(By.className("countryName"));
	    	 setUp();
	    	 String country = entry.getKey();
	    	 String countryLink = entry.getValue();
	    	 driver.get(countryLink);  
	    	
	    		for(String packageName : packageContainer)
	    		{
	    			try
	    			{
	    				
	    		 WebElement currentPackage = driver.findElement(By.cssSelector(packageName));
	    		 BigDecimal amount = new BigDecimal(currentPackage.findElement(By.className("price")).findElement(By.className("amount")).getText());
	    		 softAssert.assertEquals(amount.compareTo(BigDecimal.ZERO),1);	
	    			}
	    			 catch(org.openqa.selenium.NoSuchElementException ex)
	   	    	 {
	   	    		 softAssert.fail(packageName.split("[.]",0)[3]+ " price not valid for "+ country);
	   	    	 }	
	    		}	   
	    	 tearDown();
	     }
	     softAssert.assertAll();
	 }

	    @AfterMethod
	    void tearDown()
	    {
	        driver.quit();
	    }
}
