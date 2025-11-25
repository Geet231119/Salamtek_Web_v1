package Pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Common.BaseTest;

public class LoginPage extends BaseTest{
	By signinLnk = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[2]/a[contains(text(),'SIGN IN/ REGISTRATION')]");
	By continueWithEmailBtn = By.id("continueByEmailPhone");
	By username = By.id("focusInput");
	//By submitBtn = By.xpath("//*[@id=\"exampleModalToggle\"]/div/div/div/div/div[2]/div/div/form/div/div[2]/button[contains(text(),'LOGIN')]");
	By password = By.xpath("//input[@type='password']");
	By loginBtn = By.xpath("//*[@id=\"exampleModalToggle\"]/div/div/div/div/div[2]/div/div/form/div/div[2]/button[contains(text(),'LOGIN')]");
	By verifyMyAccount = By.xpath("//a[contains(text(),'MY ACCOUNT')]");
	By signOutBtn = By.xpath("//button[contains(text(),'SIGN OUT')]");
	By signOutConfirmBtn = By.xpath("//*[@id=\"staticBackdropsign\"]/div/div/div[4]/button[2]");
	
	//Facebook related elements
	By facebookBtn = By.xpath("//app-login/div[1]/div/div/div/div/div[2]/div/div/form/div[1]/button[contains(text(),'LOGIN WITH YOUR FACEBOOK ACCOUNT')]");
	By emailTxt = By.id("email");
	By passwordTxt = By.id("pass");
	By fbLoginBtn = By.name("login");
	By continueAsBtn = By.xpath("//span[contains(text(),'Continue as Geethu')]");
	
	public LoginPage(WebDriver driver) {
		super();
	}
	
	//Validate login functionality
	public boolean verifyLogin(String strUsername,String strPassword) throws Exception{
		waitForSpecificTime(5000);
		List<WebElement> targetElement =  driver.findElements(signinLnk);
		try {
			System.out.println("Size of elements: "+targetElement.size());
	        if(targetElement.size() == 0) {
	        			driver.navigate().refresh();
	        			waitForPageLoad();
	        			moveToElement(driver.findElement(verifyMyAccount));
	        			driver.findElement(verifyMyAccount).click();
	        			waitSometime();
	        			explicitWait(signOutBtn);
	        			moveToElement(driver.findElement(signOutBtn));
	        			driver.findElement(signOutBtn).click();
	        			explicitWait(signOutConfirmBtn);
	        			driver.findElement(signOutConfirmBtn).click();
	        			explicitWait(signinLnk);
	        }
		}
		catch (NoSuchElementException e) {
            System.out.println("Exception in finding the element:" + e.getMessage());
        }
		driver.navigate().refresh();
		waitSometime();
		waitSometime();
		clickUsingJS(signinLnk);
		waitSometime();
		driver.findElement(continueWithEmailBtn).click();
		waitSometime();
		explicitWait(username);
		driver.findElement(username).sendKeys(strUsername);
		waitSometime();
		driver.findElement(loginBtn).click();
		waitSometime();
		explicitWait(password);
		driver.findElement(password).sendKeys(strPassword);
		waitSometime();
		driver.findElement(loginBtn).click();
		System.out.println("++++++++++++++++     "+driver.findElement(verifyMyAccount).getText());
		Assert.assertEquals(driver.findElement(verifyMyAccount).getText(),"MY ACCOUNT", "MyAccount is not present");
		return true;
	}
}
