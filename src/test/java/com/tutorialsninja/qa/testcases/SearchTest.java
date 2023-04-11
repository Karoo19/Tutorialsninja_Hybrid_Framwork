package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.testbase.TestBase;

public class SearchTest extends TestBase {
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		
		driver = iniializeBrowserandopenApplication("chrome");
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("ValidProduc"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		
	}
	
	@Test(priority=2)
	public void verifySearchwithInvalidproduct() {
		
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("InvalidProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("NoProductTextSearchResults"), "No product in search List is not displayed");
		
	}
	
	@Test(priority=3,dependsOnMethods= {"VerifySearchWithValidproduct"})
	public void verifySearchWithoutAnyproduct() {
		
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String actualSearchMessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("NoProductTextSearchResults"), "No product in search List is not displayed");
		
		
		
	}
	
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
