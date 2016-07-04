package com.automation.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateCustomer {
	public static  WebDriver driver;
	private  String baseUrl;
	static ReadExcelData readExcelData;
	String customerId=null;

	@BeforeClass
	public static void loadTestDataAndLaunchBrowser() throws Exception{
		readExcelData = new ReadExcelData();
		readExcelData.readExcelExecutionControllerFile("input/testData.xls",0);
		System.out.println(readExcelData.testParam);
		driver = new FirefoxDriver();
	}

	@Test(priority=1)
	public void login() throws Exception{
		baseUrl = readExcelData.testParam.get(0);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/V4/");
		driver.manage().window().maximize();
		sendKeysToElement(driver.findElement(By.name("uid")),readExcelData.testParam.get(1));
		sendKeysToElement(driver.findElement(By.name("password")),readExcelData.testParam.get(2));
		clickOnElement(driver.findElement(By.name("btnLogin")));
	}

	@Test(priority=2)
	public void creatCustomer() throws Exception {
		clickOnElement(driver.findElement(By.linkText("New Customer")));
		sendKeysToElement(driver.findElement(By.name("name")),readExcelData.testParam.get(3));
		sendKeysToElement(driver.findElement(By.id("dob")),readExcelData.testParam.get(4));
		sendKeysToElement(driver.findElement(By.name("addr")),readExcelData.testParam.get(5));
		sendKeysToElement(driver.findElement(By.name("city")),readExcelData.testParam.get(6));
		sendKeysToElement(driver.findElement(By.name("state")),readExcelData.testParam.get(7));
		sendKeysToElement(driver.findElement(By.name("pinno")),readExcelData.testParam.get(8));
		sendKeysToElement(driver.findElement(By.name("telephoneno")),readExcelData.testParam.get(9));
		sendKeysToElement(driver.findElement(By.name("emailid")), "test1"+randomNumber()+"@gmail.com");
		sendKeysToElement(driver.findElement(By.name("password")),readExcelData.testParam.get(10));
		//sendKeysToElement(driver.findElement(By.name("state")),"mngr38930");
		clickOnElement(driver.findElement(By.name("sub")));
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='customer']/tbody/tr[1]/td/p")).getText(), "Customer Registered Successfully!!!");
		customerId =driver.findElement(By.xpath("//*[@id='customer']/tbody/tr[4]/td[2]")).getText();
		System.out.println("Newly Created Customer Id :::::"+customerId);
		ReadExcelData.writeResultToExcel("input/testData.xls",1,customerId);
	}

	public int randomNumber(){
		long number = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;
		return (int) number;
	}
	
	
	public static void sendKeysToElement(WebElement webElemnet,String inputData){
		if(webElemnet.isDisplayed() && webElemnet.isEnabled()){
			webElemnet.clear();
			webElemnet.sendKeys(inputData);
		}
		else{
		}
	}

	public static void clickOnElement(WebElement webElemnet){
		if(webElemnet.isDisplayed() && webElemnet.isEnabled()){
			webElemnet.click();
		}
		else{
		}
	}

	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
