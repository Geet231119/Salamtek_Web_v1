package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class AdminProfileLoginPage extends BaseTest{
	By username = By.id("input-email");
	By password = By.id("input-password");
	By loginBtn = By.xpath("//button[contains(text(),'Log In')]");
	By adminProfileScreenIdentifier = By.xpath("//h3[contains(text(),'Welcome to SALAMTEK Dashboard panel')]");
	By labProfileScreenIdentifier = By.xpath("//h3[contains(text(),'Welcome to SALAMTEK Lab panel')]");
	By doctorProfileScreenIdentifier = By.xpath("//h3[contains(text(),'Welcome to SALAMTEK Doctor panel')]");
	By storeProfileScreenIdentifier = By.xpath("//h3[contains(text(),'Welcome to SALAMTEK Store panel')]");
	By dashboarddSection = By.xpath("//span[contains(text(),'Dashboard')]");
	
	public AdminProfileLoginPage(WebDriver driver) {
		super();
	}
	
	//Validate admin login
	public boolean verifyAdminLogin(String strUsername,String strPassword) throws Exception{	
		explicitWait_60(username);
		driver.findElement(username).sendKeys(strUsername);
		waitSometime();
		driver.findElement(password).sendKeys(strPassword);
		waitSometime();
		driver.findElement(loginBtn).click();
		//waitForSpecificTime(36000);
		waitForSpecificTime(10000);
		System.out.println("++++++++++++++++     "+driver.findElement(adminProfileScreenIdentifier).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(adminProfileScreenIdentifier).getText().trim().toUpperCase(), "WELCOME TO SALAMTEK DASHBOARD PANEL.");
		return true;
	}
	
	//Validate lab profile login
	public boolean verifyLabProfileLogin(String strUsername,String strPassword) throws Exception{	
		explicitWait(username);
		driver.findElement(username).sendKeys(strUsername);
		waitSometime();
		driver.findElement(password).sendKeys(strPassword);
		waitSometime();
		driver.findElement(loginBtn).click();
		waitForSpecificTime(36000);
		System.out.println("++++++++++++++++     "+driver.findElement(labProfileScreenIdentifier).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(labProfileScreenIdentifier).getText().trim().toUpperCase(), "WELCOME TO SALAMTEK LAB PANEL.");
		return true;
	}
	
	//Validate doctor profile login
	public boolean verifyDoctorProfileLogin(String strUsername,String strPassword) throws Exception{	
		explicitWait(username);
		driver.findElement(username).sendKeys(strUsername);
		waitSometime();
		driver.findElement(password).sendKeys(strPassword);
		waitSometime();
		driver.findElement(loginBtn).click();
		//waitForSpecificTime(36000);
		waitForSpecificTime(10000);
		System.out.println("++++++++++++++++     "+driver.findElement(doctorProfileScreenIdentifier).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(doctorProfileScreenIdentifier).getText().trim().toUpperCase(), "WELCOME TO SALAMTEK DOCTOR PANEL.");
		return true;
	}
	
	//Validate store profile login
	public boolean verifyStoreProfileLogin(String strUsername,String strPassword) throws Exception{	
		explicitWait(username);
		driver.findElement(username).sendKeys(strUsername);
		waitSometime();
		driver.findElement(password).sendKeys(strPassword);
		waitSometime();
		driver.findElement(loginBtn).click();
		waitForSpecificTime(36000);
		System.out.println("++++++++++++++++     "+driver.findElement(storeProfileScreenIdentifier).getText().trim().toUpperCase());
		Assert.assertEquals(driver.findElement(storeProfileScreenIdentifier).getText().trim().toUpperCase(), "WELCOME TO SALAMTEK STORE PANEL.");
		return true;
	}
}
