package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tutorialsninja.qa.testbase.TestBase;
import com.tutorialsninja.qa.utils.utilities;

public class RegisterTest extends TestBase {
	
	WebDriver driver;
    SoftAssert softassert = new SoftAssert();
	
	@BeforeMethod
	public void setUp() {
		
		driver = iniializeBrowserandopenApplication("chrome");
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	
	}
	
	
	@Test(priority=1)
	public void VerifyRegisteringAnAccountUsingValidCredentials() {
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("FirestName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("LastName"));
		driver.findElement(By.id("input-email")).sendKeys(utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[starts-with(@id,'input-telephone')]")).sendKeys(dataProp.getProperty("TelephoneNumber"));
		driver.findElement(By.xpath("//input[starts-with(@id,'input-password')]")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[starts-with(@id,'input-confirm')]")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[starts-with(@name,'agree')]")).click();
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		
		String actualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessMessage, dataProp.getProperty(("AccountSuccessfullCreated"),"account success page is not display"));
		
	}
	
	
	@Test(priority=2)
	public void VerifyRegisteringanAccountbyprovidingAllfields() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("FirestName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("LastName"));
		driver.findElement(By.id("input-email")).sendKeys(utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[starts-with(@id,'input-telephone')]")).sendKeys(dataProp.getProperty("TelephoneNumber"));
		driver.findElement(By.xpath("//input[starts-with(@id,'input-password')]")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[starts-with(@id,'input-confirm')]")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[starts-with(@name,'agree')]")).click();
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		String actualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessMessage,dataProp.getProperty(("AccountSuccessfullCreated")));
		
    }
	
	
	@Test(priority=3)
	public void VerifyRegisteringanAccountbyregistringWithExistingEmailAdress() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("FirestName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("LastName"));
		driver.findElement(By.id("input-email")).sendKeys("karima10@gmail.com");
		driver.findElement(By.xpath("//input[starts-with(@id,'input-telephone')]")).sendKeys(dataProp.getProperty("TelephoneNumber"));
		driver.findElement(By.xpath("//input[starts-with(@id,'input-password')]")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[starts-with(@id,'input-confirm')]")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[starts-with(@name,'agree')]")).click();
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		
		String actualSuccessMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		Assert.assertEquals(actualSuccessMessage,dataProp.getProperty("DuplicateEmailWarning"));
		

	}
	
	
	@Test(priority=3)
	public void VerifyRegisteringanAccountWithoutEntringAnydetails() {
		
		
		driver.findElement(By.xpath("//input[starts-with(@class,'btn btn-primary')]")).click();
		
		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,' alert-dismissible')]")).getText();
		Assert.assertEquals(actualPrivacyPolicyWarning,dataProp.getProperty("PrivacyWarning"), "policy Warning Message is not displayed");
		
		String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarning,dataProp.getProperty("FirstNameWarning"), "First Name Warning Message is not displayed");
		
		String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("LastNameWarninig"),"Last Name Warning Message is not displayed");
		
		String actualEmailMessageWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailMessageWarning,dataProp.getProperty("EmailWarning"), "Email Warning Message is not displayed");
		
		String actualTelephoneMessageWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephoneMessageWarning,dataProp.getProperty("TelephoneNumberWarning"), "Telephone Warning Message is not displayed");
		
		String actualPasswordMessageWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordMessageWarning,dataProp.getProperty("PasswordWarning"), "Password Warning Message is not displayed");
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
	
	
	
}
