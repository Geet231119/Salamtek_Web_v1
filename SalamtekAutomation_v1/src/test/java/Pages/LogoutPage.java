package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Common.BaseTest;

public class LogoutPage extends BaseTest{
	//By myAccountLnk = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[2]/a[contains(text(),'MY ACCOUNT')]");
	By dashboardBtn = By.xpath("//*[@id=\"v-pills-tab\"]/button[1]");
	By myAccountLnk = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[2]/a[contains(text(),'MY ACCOUNT ')]");
	By signOutBtn = By.xpath("//button[contains(text(),'SIGN OUT')]");
	By signOutConfirmBtn = By.xpath("//*[@id=\"staticBackdropsign\"]/div/div/div[4]/button[2]");
	By signinLnk = By.xpath("//*[@id=\"navbarNav\"]/div[1]/ul/li[2]/a[contains(text(),'SIGN IN/ REGISTRATION')]");
	
	public LogoutPage(WebDriver driver) {
		super();
	}
	
	//Validate logout functionality
	public boolean verifyLogout() throws Exception{
		waitSometime();
		waitElementToBeClickable(myAccountLnk);
		clickUsingJS(myAccountLnk);
		waitForSpecificTime(7000);
		explicitWait_60(dashboardBtn);
		explicitWait(signOutBtn);
		moveToElement(driver.findElement(signOutBtn));
		driver.findElement(signOutBtn).click();
		explicitWait(signOutConfirmBtn);
		driver.findElement(signOutConfirmBtn).click();
		explicitWait(signinLnk);
		System.out.println("++++++++++++++++     "+driver.findElement(signinLnk).getText());
		Assert.assertEquals(driver.findElement(signinLnk).getText(), "SIGN IN/ REGISTRATION", "Sign in link is not present");
		return true;
	}
}
