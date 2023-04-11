package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.testbase.TestBase;
import com.tutorialsninja.qa.utils.utilities;



public class LoginTest extends TestBase {
	
	public LoginTest() {
		super();
	}
	
	WebDriver driver;
    SoftAssert softassert = new SoftAssert();
	
	@BeforeMethod
	public void setUp() {
		
		driver = iniializeBrowserandopenApplication("chrome");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
	
		
	}
	
	@Test(priority=1,dataProvider= "supplyTestData")
	public void VerifyLoggingintoTheApplicationUsingValidCredentials(String email, String password) {
		
		driver.findElement(By.xpath("//input[starts-with(@id,'input-email')]")).sendKeys(email);
		driver.findElement(By.xpath("//input[starts-with(@id,'input-password')]")).sendKeys(password);
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		softassert.assertAll();
		
		
	}
	
	@DataProvider
	public Object[][] supplyTestData(){
		Object[][] data= {{"karima10@gmail.com","Karima@123"},
				{"karima11@gmail.com","Karima@123"},
				{"karima12@gmail.com","Karima@123"},
				{"karima13@gmail.com","Karima@123"},
				{"karima14@gmail.com","Karima@123"},
				{"karima15@gmail.com","Karima@123"}};
		
		return data;
	}
	
	
	@Test(priority=2)
	public void VerifyLoggingintoTheApplicationUsingInvalidUsernameAndValidPassword() {
		
		driver.findElement(By.xpath("//input[starts-with(@id,'input-email')]")).sendKeys(utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[starts-with(@id,'input-password')]")).sendKeys("validPassword");
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage=driver.findElement(By.xpath("//div[starts-with(@class,'alert alert-danger alert-dismissible')]")).getText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordDoNotMatchWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"warning message is not correct");
		softassert.assertAll();
		

}
	
	@Test(priority=3)
	public void VerifyLoggingintoTheApplicationUsingValidUsernameAndInvalidPasswordd() {
		
		driver.findElement(By.xpath("//input[starts-with(@id,'input-email')]")).sendKeys(utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[starts-with(@id,'input-password')]")).sendKeys("Karima@1234");
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage=driver.findElement(By.xpath("//div[starts-with(@class,'alert alert-danger alert-dismissible')]")).getText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordDoNotMatchWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"warning message is not correct");
		softassert.assertAll();
		

}
	
	@Test(priority=4)
	public void VerifyLoggingintoTheApplicationUsingEmptyUsernameAndValidPassworddd() {
		
		
		driver.findElement(By.xpath("//input[starts-with(@id,'input-password')]")).sendKeys("validPassword");
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage=driver.findElement(By.xpath("//div[starts-with(@class,'alert alert-danger alert-dismissible')]")).getText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordDoNotMatchWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"warning message is not correct");
		softassert.assertAll();
		

}
	
	@Test(priority=5)
	public void VerifyLoggingintoTheApplicationUsingValidUsernameAndEmptyPassworddd() {
		
		driver.findElement(By.xpath("//input[starts-with(@id,'input-email')]")).sendKeys(utilities.generateEmailWithTimeStamp());
		
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage=driver.findElement(By.xpath("//div[starts-with(@class,'alert alert-danger alert-dismissible')]")).getText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordDoNotMatchWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"warning message is not correct");
		softassert.assertAll();
		

}
	
	@Test(priority=6)
	public void VerifyLoggingintoTheApplicationUsingEmptyUsernameAndEmptyPassworddd() {
		
		
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		SoftAssert softassert = new SoftAssert();
		String actualWarningMessage=driver.findElement(By.xpath("//div[starts-with(@class,'alert alert-danger alert-dismissible')]")).getText();
		String expectedWarningMessage=dataProp.getProperty("emailPasswordDoNotMatchWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"warning message is not correct");
		softassert.assertAll();
		

}
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}